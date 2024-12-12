package mod3d;

/**
 *
 * @author File_Deleted
 */
public class Carbon extends Element {
    /**
     * creates a new carbon atom, along with its atomic orbitals
     */
    public Carbon() {
        super(67e-12, 2.55, 4);
        this.fixName("Carbon");
        
        AO C2s = new AO(2, -19.4, 2, "s", null);
        AO C2px = new AO(2, -10.7, 1, "p", "x");
        AO C2py = new AO(2, -10.7, 0, "p", "y");
        AO C2pz = new AO(2, -10.7, 1, "p", "z");
        
        this.addOrbital(C2s);
        this.addOrbital(C2px);
        this.addOrbital(C2py);
        this.addOrbital(C2pz);
    }
}
