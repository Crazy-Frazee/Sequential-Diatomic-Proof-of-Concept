package mod3d;

/**
 *
 * @author File_Deleted
 */
public class AO extends Suborbital {
    /**
     * the row on the Periodic Table that this suborbital is from
     */
    private int row;
    /**
     * the type of suborbital this suborbital is (ex. s, p, d, etc.)
     */
    private String type;
    /**
     * the orientation of this suborbital in space (ex. x, y, z, xy, z2, etc.)
     */
    private String subtype;
    
    /**
     * constructor for an atomic orbital/suborbital - also sets its name
     * @param level the row on the Periodic Table that this suborbital is from
     * @param position the suborbital's energy level, in eV's
     * @param spin the number of electrons currently in the suborbital
     * @param shape the type of suborbital this suborbital is (ex. s, p, d, etc.)
     * @param orientation the orientation of this suborbital in space (ex. x, y, z, xy, z2, etc.)
     */
    public AO(int level, double position, int spin, String shape, String orientation) {
        super(position, spin);
        this.row = level;
        this.type = shape;
        if (shape.equals("s")) {
            this.subtype = null;
            this.setName("" + level + shape);
        }
        else {
            this.subtype = orientation;
            this.setName(level + shape + orientation);
        }
    }
    
    /**
     * getter method for the suborbital's row on the Periodic Table
     * @return the row on the Periodic Table that this suborbital is from
     */
    public int getRow() {
        return this.row;
    }
    
    /**
     * getter method for the suborbital's type
     * @return the suborbital's type - either s, p, d, or f
     */
    public String getType() {
        return this.type;
    }
    
    /**
     * getter method for the suborbital's spatial orientation
     * @return the suborbital's spatial orientation identifier (ex. x, y, z, xy, z2, etc.)
     */
    public String getSubtype() {
        return this.subtype;
    }
}
