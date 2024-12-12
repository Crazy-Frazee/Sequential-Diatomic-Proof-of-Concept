package mod3d;

/**
 *
 * @author File_Deleted
 */
public class MO extends Suborbital {
    /**
     * the type of bond this suborbital makes (ex. sigma, pi, delta, etc.)
     */
    private String strength;
    /**
     * the spatial orientation of the suborbital (ex. x, y, z, etc.)
     */
    private String polarity;
    /**
     * the bonding status of the suborbital (ex. bonding, antibonding, nonbonding, etc.)
     */
    private String symmetry;
    
    /**
     * constructor for a molecular orbital/suborbital also sets its name
     * @param position the suborbital's energy level, in eV's
     * @param spin the number of electrons currently in the suborbital
     * @param greek the type of bond this suborbital makes (ex. sigma, pi, delta, etc.)
     * @param direction the spatial orientation of the suborbital (ex. x, y, z, etc.)
     * @param balance the bonding status of the suborbital (ex. bonding, antibonding, nonbonding, etc.)
     */
    public MO(double position, int spin, String greek, String direction, String balance) {
        super(position, spin);
        this.strength = greek;
        this.polarity = direction;
        this.symmetry = balance;
        if (direction == null) {
            this.setName(greek + balance);
        }
        else {
            this.setName(greek + direction + balance);
        }
    }
    
    /**
     * getter method for the suborbital's type of bond
     * @return the type of bond this suborbital makes (usually either sigma, pi, or delta)
     */
    public String getStrength() {
        return this.strength;
    }
    
    /**
     * setter method for the suborbital's type of bond
     * @param rotation the new type of bond this orbital makes (in relation to the z-axis)
     */
    public void setStrength(String rotation) {
        this.strength = rotation;
    }
    
    /**
     * getter method for the suborbital's spatial orientation
     * @return the suborbital's spatial orientation identifier (ex. x, y, z, etc.)
     */
    public String getPolarity() {
        return this.polarity;
    }
    
    /**
     * setter method for the suborbital's spatial orientation
     * @param rotation the new orientation in space of the suborbital
     */
    public void setPolarity(String rotation) {
        this.polarity = rotation;
    }
    
    /**
     * getter method for the suborbital's bonding status
     * @return the bonding status of the suborbital (ex. bonding, antibonding, nonbonding, etc.)
     */
    public String getSymmetry() {
        return this.symmetry;
    }
    
    /**
     * setter method for the suborbital's bonding status
     * @param nextStep the new bonding status that the suborbital is becoming
     */
    public void setSymmetry(String nextStep) {
        this.symmetry = nextStep;
    }
}
