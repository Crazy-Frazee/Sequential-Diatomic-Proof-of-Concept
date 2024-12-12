package mod3d;

/**
 *
 * @author File_Deleted
 */
public class Nitrogen extends Element {
    /**
     * creates a new nitrogen atom, along with its atomic orbitals
     */
    public Nitrogen() {
        super(56e-12, 3.04, 5);
        this.fixName("Nitrogen");
        
        AO N2s = new AO(2, -25.6, 2, "s", null);
        AO N2px = new AO(2, -13.2, 1, "p", "x");
        AO N2py = new AO(2, -13.2, 1, "p", "y");
        AO N2pz = new AO(2, -13.2, 1, "p", "z");
        
        this.addOrbital(N2s);
        this.addOrbital(N2px);
        this.addOrbital(N2py);
        this.addOrbital(N2pz);
    }
}
