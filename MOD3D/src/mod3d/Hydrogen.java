package mod3d;

/**
 *
 * @author File_Deleted
 */
public class Hydrogen extends Element {
    /**
     * creates a new hydrogen atom, along with its atomic orbitals
     */
    public Hydrogen() {
        super(53e-12, 2.2, 1);
        this.fixName("Hydrogen");
        
        AO H1s = new AO(1, -13.6, 1, "s", null);
        
        this.addOrbital(H1s);
    }
}
