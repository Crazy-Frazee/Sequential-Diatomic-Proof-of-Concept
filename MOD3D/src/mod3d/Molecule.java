package mod3d;

/**
 *
 * @author File_Deleted
 */

import java.util.ArrayList;

public class Molecule {
    /**
     * the conventional name of the molecule
     */
    private String name;
    /**
     * ArrayList containing all of the molecule's molecular orbitals
     */
    private ArrayList<MO> lines;
    /**
     * ArrayList containing all the constituent elements that make up the molecule
     */
    private ArrayList<Element> atoms;
    /**
     * the element of the "central" atom in the molecule; this element's radius and
     * electronegativity will be used in calculating bond energy levels
     */
    private Element central;
    /**
     * the total number of valence electrons in the molecule
     */
    private int electrons;
    
    /**
     * constructor for a new molecule
     * @param formula the common name (NOT the chemical formula) of the molecule that you wish to build
     */
    public Molecule(String formula) {
        this.name = formula;
        this.lines = new ArrayList<MO>();
        this.atoms = new ArrayList<Element>();
        this.electrons = 0;
    }
    
    /**
     * getter method for the molecule's name
     * @return the common name of the molecule
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * setter method for the molecule's name
     * @param idcode the name of the molecule; what you want to rename the molecule in question
     */
    public void rename(String idcode) {
        this.name = idcode;
    }
    
    /**
     * getter method for the entire list of the molecule's molecular orbitals
     * @return the entire ArrayList of the molecule's molecular orbitals
     */
    public ArrayList<MO> getLines() {
        return this.lines;
    }
    
    /**
     * getter method for how many molecular orbitals the molecule has
     * This method is primarily used to provide a value for an iterator variable.
     * @return the number of molecular orbitals the molecule has
     */
    public int getLinesSize() {
        return this.lines.size();
    }
    
    /**
     * searches the ArrayList for an molecular orbital with a specified name
     * If the provided name isn't found, this method returns null.
     * @param identifier the name of the molecular orbital you intend to find
     * @return the MO object representing that molecular orbital (if it exists) or null (if it doesn't exist)
     */
    public MO getLine(String identifier) {
        MO output = null;
        for (MO scanning: this.lines) {
            if (scanning.getFullName().equals(identifier)) {
                output = scanning;
            }
        }
        if (output != null) {
            return output;
        }
        else {
            System.out.println("ERROR: " + this.name + " does not have a " + identifier + " orbital.");
            return null;
        }
    }
    
    /**
     * appends a molecular orbital to the end of the molecule's ArrayList (of molecular orbitals)
     * @param toadd the MO object representing the molecular orbital to be added
     */
    public void addOrbital(MO toadd) {
        this.lines.add(toadd);
    }
    
    /**
     * getter method for the "central" atom in the molecule
     * @return the element of the atom that is the molecule's "central" atom
     */
    public Element getCenter() {
        return this.central;
    }
    
    /**
     * setter method for the "central" atom in the molecule
     * @param focus the element of the atom that is the molecule's "central" atom
     */
    public void establishCenter(Element focus) {
        this.central = focus;
    }
    
    /**
     * appends an a element to the end of the molecule's ArrayList (of constituent elements)
     * @param toadd the Element object representing the atom/element to be added
     */
    public void addElement(Element toadd) {
        this.atoms.add(toadd);
        this.electrons = this.electrons + toadd.getColumn();
    }
    
    /**
     * getter method for number of valence electrons in molecule
     * @return the total number of valence electrons in the molecule
     */
    public int getElectrons() {
        return this.electrons;
    }
    
    /**
     * renames the needed orbitals to signify a rotation of the molecule
     * thus changing the z-axis and allowing a new bond to be conceptualized
     * @param orient the current name of the axis you wish to make the new z-axis
     */
    public void rotate(String orient) {
        for (MO orbit: this.getLines()) {
            if ((orbit.getStrength().equals("pi")) && (orbit.getPolarity().equals(orient))) {
                orbit.setStrength("sigma");
                orbit.setPolarity("z");
                continue;
            }
            if ((orbit.getStrength().equals("sigma")) && (orbit.getPolarity().equals("z"))) {
                orbit.setStrength("pi");
                orbit.setPolarity(orient);
                if (orbit.getSymmetry().equals(" ")) {
                    orbit.setSymmetry("b");
                }
                if (orbit.getSymmetry().equals("*")) {
                    orbit.setSymmetry("a");
                }
            }
        }
    }
}