package mod3d;

/**
 *
 * @author File_Deleted
 */
public class MOD3D {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Hydrogen h = new Hydrogen();
        Carbon c = new Carbon();
        Nitrogen n = new Nitrogen();
        Oxygen o = new Oxygen();
        Molecule w = Bonder.bond(h, o, "water", 1);
        w.establishCenter(o);
        Bonder.bond(h, w);
        Bonder.rollCall(w);
        Molecule a = Bonder.bond(h, n, "ammonia", 1);
        a.establishCenter(n);
        Bonder.bond(h, a);
        /*Bonder.bond(h, a);
        Bonder.rollCall(a);
        Molecule m = Bonder.bond(h, c, "methane", 1);
        m.establishCenter(c);
        for (int r = 0; r < 3; r++) {
            Bonder.bond(h, m);
        }
        Bonder.rollCall(m);*/
        /*Molecule nn = Bonder.bond(n, n, "nitrogen gas", 3);
        Bonder.rollCall(nn);*/
    }
}
