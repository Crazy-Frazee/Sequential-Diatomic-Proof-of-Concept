package mod3d;

/**
 *
 * @author File_Deleted
 */

import java.util.ArrayList;

public class Element {
    /**
     * the name of the element
     */
    private String name;
    /**
     * ArrayList containing all the element's VALENCE atomic orbitals
     */
    private ArrayList<AO> lines;
    /**
     * (calculated) atomic radius of an atom of this element, IN METERS
     */
    private double radius;
    /**
     * electronegativity of the element, on a scale from 0 to 4
     */
    private double electronegativity;
    /**
     * number of valence electrons the element has
     */
    private int column;
    
    /**
     * constructor for a new atom
     * filling in the name and atomic orbitals is a job delegated to the subclasses
     * @param size the (calculated) radius of an atom of this element, IN METERS
     * @param attraction the electronegativity of the element, on a scale from 0 to 4
     * @param valence the number of valence electrons the element has
     */
    public Element(double size, double attraction, int valence) {
        this.name = null;
        this.lines = new ArrayList<AO>();
        this.radius = size;
        this.electronegativity = attraction;
        this.column = valence;
    }
    
    /**
     * getter method for the element's name
     * @return the name of the element
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * setter method for the element's name
     * SHOULD ONLY BE USED BY THE SUBCLASSES - NOT BY MAIN OR BONDER
     * @param symbol the name of the element - NOT its atomic symbol
     */
    public void fixName(String symbol) {
        this.name = symbol;
    }
    
    /**
     * getter method for the entire list of the element's atomic orbitals
     * @return the entire ArrayList of the element's atomic orbitals
     */
    public ArrayList<AO> getLines() {
        return this.lines;
    }
    
    /**
     * getter method for how many atomic orbitals the element has
     * This method is primarily used to provide a value for an iterator variable.
     * @return the number of atomic orbitals the element has
     */
    public int getLinesSize() {
        return this.lines.size();
    }
    
    /**
     * getter method for the element's atomic radius
     * @return the (calculated) radius of an atom of this element (in meters)
     */
    public double getRadius() {
        return this.radius;
    }
    
    /**
     * getter method for the element's electronegativity
     * @return the electronegativity of the element (on a scale from 0 to 4)
     */
    public double getElectro() {
        return this.electronegativity;
    }
    
    /**
     * getter method for the number of valence electrons in the element
     * @return the number of valence electrons the element has
     */
    public int getColumn() {
        return this.column;
    }
    
    /**
     * searches the ArrayList for an atomic orbital with a specified name
     * If the provided name isn't found, this method returns null.
     * @param identifier the name of the atomic orbital you intend to find
     * @return the AO object representing that atomic orbital (if it exists) or null (if it doesn't exist)
     */
    public AO getLine(String identifier) {
        AO output = null;
        for (AO scanning: this.lines) {
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
     * appends an atomic orbital to the end of the element's ArrayList
     * SHOULD ONLY BE USED BY THE SUBCLASSES - NOT BY MAIN OR BONDER
     * @param toAdd the AO object representing the atomic orbital to be added
     */
    public void addOrbital(AO toAdd) {
        this.lines.add(toAdd);
    }
}
