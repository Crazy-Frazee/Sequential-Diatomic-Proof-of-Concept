package mod3d;

/**
 *
 * @author File_Deleted
 */

public abstract class Suborbital {
    /**
     * the suborbital's name (ex. 2px or sigma*)
     */
    private String fullName;
    /**
     * the suborbital's energy level, in eV's
     */
    private double energy;
    /**
     * how many electrons are in the suborbital - a suborbital can only have 0, 1, or 2 electrons
     */
    private int electrons;
    
    /**
     * constructor for an unnamed suborbital
     * @param position the suborbital's energy level, in eV's
     * @param spin how many electrons are in the suborbital
     */
    public Suborbital(double position, int spin) {
        this.fullName = null;
        this.energy = position;
        this.electrons = spin;
    }
    
    /**
     * mutator method for the suborbital's name
     * @param combo what you're renaming the suborbital to
     */
    public void setName(String combo) {
        this.fullName = combo;
    }
    
    /**
     * getter method for the suborbital's name
     * @return the suborbital's name
     */
    public String getFullName() {
        return this.fullName;
    }
    
    /**
     * getter method for the suborbital's energy level
     * @return the suborbital's energy level in eV's
     */
    public double getEnergy() {
        return this.energy;
    }
    
    /**
     * getter method for the number of electrons in the suborbital
     * @return the number of electrons in the suborbital
     */
    public int getElectrons() {
        return this.electrons;
    }
    
    /**
     * adds an electron to the suborbital, if it has the space for one more
     */
    public void addElectron() {
        if ((this.electrons == 0) || (this.electrons == 1)) {
            this.electrons++;
        }
    }
    
    /**
     * removes an electron from the suborbital, if it has one to remove
     */
    public void removeElectron() {
        if ((this.electrons == 1) || (this.electrons == 2)) {
            this.electrons--;
        }
    }
}
