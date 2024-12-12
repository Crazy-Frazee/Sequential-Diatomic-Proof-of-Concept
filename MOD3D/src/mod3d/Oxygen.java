package mod3d;

/**
 *
 * @author fraze
 */
public class Oxygen extends Element {
    /**
     * creates a new oxygen atom, along with its atomic orbitals
     */
    public Oxygen() {
        super(48e-12, 3.44, 6);
        this.fixName("Oxygen");
        
        AO O2s = new AO(2, -32.4, 2, "s", null);
        AO O2px = new AO(2, -15.9, 2, "p", "x");
        AO O2py = new AO(2, -15.9, 1, "p", "y");
        AO O2pz = new AO(2, -15.9, 1, "p", "z");
        
        this.addOrbital(O2s);
        this.addOrbital(O2px);
        this.addOrbital(O2py);
        this.addOrbital(O2pz);
    }
}
