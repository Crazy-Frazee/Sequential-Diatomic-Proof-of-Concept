package mod3d;

/**
 *
 * @author File_Deleted
 */

import java.util.Collections;

public class Bonder {
    /**
     * constructor for all the supplemental methods that would clog up the other classes
     */
    public Bonder() {
    }
    
    /**
     * creates the resulting diatomic molecule from two atoms
     * @param a an element/atom to become part of the molecule
     * @param b the other element/atom to become part of the molecule
     * @param name the common name of the molecule that you wish to build
     * @param degree the bond order (single, double, or triple) of the bond that you wish to form
     * CURRENTLY INCOMPLETE PLS IGNORE FOR NOW
     * @return the molecule that results from bonding these two atoms
     */
    public static Molecule bond(Element a, Element b, String name, int degree) {
        //find two orbitals that bond
        int aLines = a.getLinesSize();
        int bLines = b.getLinesSize();
        int i;
        int j = 0;
        double possibility;
        double champion = Double.MAX_VALUE;
        AO left = null;
        AO right = null;
        //best case: two compatible pz orbitals are found
        for (i = 0; i < aLines; i++) {
            if ((a.getLines().get(i).getType().equals("p"))
                    && (a.getLines().get(i).getSubtype().equals("z"))) {
                for (j = 0; j < bLines; j++) {
                    if ((b.getLines().get(j).getType().equals("p"))
                            && (b.getLines().get(j).getSubtype().equals("z"))) {
                        possibility = Math.abs((a.getLines().get(i).getEnergy()) - (b.getLines().get(j).getEnergy()));
                        if (possibility < champion) {
                            champion = possibility;
                            left = a.getLines().get(i);
                            right = b.getLines().get(j);
                        }
                    }
                }
            }
        }
        if ((left == null) && (right == null)) {
            //other valid case: a compatible pz and s orbital, or two compatible s orbitals, are found
            for (i = 0; i < aLines; i++) {
                if (((a.getLines().get(i).getType().equals("p"))
                        && (a.getLines().get(i).getSubtype().equals("z")))
                        || (a.getLines().get(i).getType().equals("s"))) {
                    for (j = 0; j < bLines; j++) {
                        if (((b.getLines().get(j).getType().equals("p"))
                                && (b.getLines().get(j).getSubtype().equals("z")))
                                || (b.getLines().get(j).getType().equals("s"))) {
                            possibility = Math.abs((a.getLines().get(i).getEnergy()) - (b.getLines().get(j).getEnergy()));
                            if (possibility < champion) {
                                champion = possibility;
                                left = a.getLines().get(i);
                                right = b.getLines().get(j);
                            }
                        }
                    }
                }
            }
        }
        if ((left == null) && (right == null)) {
            //no compatible orbitals were found
            return null;
        }
        MO bonding, antibonding;
        //calculate energies of the bonding and antibonding orbitals
        if ((left.getEnergy()) == (right.getEnergy())) {
            if ((a.getElectro()) > (b.getElectro())) {
                double energies[] = calculate(a, b, left, right);
                bonding = new MO(energies[0], 0, "sigma", "z", " ");
                antibonding = new MO(energies[1], 0, "sigma", "z", "*");
            }
            else {
                double energies[] = calculate(b, a, right, left);
                bonding = new MO(energies[0], 0, "sigma", "z", " ");
                antibonding = new MO(energies[1], 0, "sigma", "z", "*");
            }
        }
        else if ((left.getEnergy()) > (right.getEnergy())) {
            double energies[] = calculate(b, a, right, left);
            bonding = new MO(energies[0], 0, "sigma", "z", " ");
            antibonding = new MO(energies[1], 0, "sigma", "z", "*");
        }
        else {
            double energies[] = calculate(a, b, left, right);
            bonding = new MO(energies[0], 0, "sigma", "z", " ");
            antibonding = new MO(energies[1], 0, "sigma", "z", "*");
        }
        if ((left.getType().equals("s")) && (right.getType().equals("s"))) {
            //establishes a new pseudo-direction for s orbital bonds
            bonding.setPolarity("s");
            antibonding.setPolarity("s");
        }
        Molecule output = new Molecule(name);
        //create the molecule's list of orbitals
        output.addElement(a);
        output.addElement(b);
        /* this is for forming double and triple bonds - incomplete; please ignore
        MO pibondx = null;
        MO pibondy = null;
        MO piantix = null;
        MO piantiy = null;
        if (degree > 1) {
            AO piL = a.getLine(a.getLines().get(i).getRow() + "px");
            AO piR = b.getLine(b.getLines().get(j).getRow() + "px");
            double pinergies[] = calculate(a, b, piL, piR);
            pibondx = new MO(pinergies[0], 0, "pi", "x", " ");
            pibondy = new MO(pinergies[0], 0, "pi", "y", " ");
            piantix = new MO(pinergies[1], 0, "pi", "x", "*");
            piantiy = new MO(pinergies[1], 0, "pi", "y", "*");
            
        }*/
        MO non;
        //add all remaining orbitals to molecule as nonbonding orbitals
        for (i = 0; i < aLines; i++) {
            if ((a.getLines().get(i).getFullName()).equals(left.getFullName())) {
                continue;
            }
            /* this is for forming double and triple bonds - incomplete; please ignore
            if (degree > 1) {
                if ((a.getLines().get(i).getFullName()).equals())
            }*/
            non = new MO(a.getLines().get(i).getEnergy(), 0, typeConvert(a.getLines().get(i)), a.getLines().get(i).getSubtype(),"0");
            output.addOrbital(non);
        }
        for (j = 0; j < bLines; j++){
            if ((b.getLines().get(j).getFullName()).equals(right.getFullName())) {
                continue;
            }
            non = new MO(b.getLines().get(j).getEnergy(), 0, typeConvert(b.getLines().get(j)), b.getLines().get(j).getSubtype(),"0");
            output.addOrbital(non);
        }
        output.addOrbital(bonding);
        output.addOrbital(antibonding);
        /* this is for forming double and triple bonds - incomplete; please ignore
        if (degree > 1) {
            output.addOrbital(pibondx);
            output.addOrbital(pibondy);
            output.addOrbital(piantix);
            output.addOrbital(piantiy);
        }*/
        // sort all the orbitals by energy level and add correct amount of electrons
        sort(output);
        electronFill(output);
        //establishes a new pseudo-direction for completely spherical orbitals
        for (MO quickfix : output.getLines()) {
            if (quickfix.getPolarity() == null) {
                quickfix.setPolarity("s");
            }
        }
        return output;
    }
    
    /**
     * bonds a new atom to a molecule
     * @param a the element/atom to be added to the molecule
     * @param B the proto-molecule containing all the other atoms in the resulting molecule
     */
    public static void bond(Element a, Molecule B) {
        B.rotate("x"); //does not belong here; please fix
        //find two orbitals that bond
        int aLines = a.getLinesSize();
        int bLines = B.getLinesSize();
        int i;
        int j = 0;
        double possibility;
        double champion = Double.MAX_VALUE;
        AO left = null;
        MO right = null;
        //best case: two compatible "pz" orbitals are found
        for (i = 0; i < aLines; i++) {
            if ((a.getLines().get(i).getType().equals("p"))
                    && (a.getLines().get(i).getSubtype().equals("z"))) {
                for (j = 0; j < bLines; j++) {
                    if ((B.getLines().get(j).getStrength().equals("sigma"))
                            && (B.getLines().get(j).getPolarity().equals("z"))
                            && (B.getLines().get(j).getSymmetry().equals("0"))) {
                        possibility = Math.abs((a.getLines().get(i).getEnergy()) - (B.getLines().get(j).getEnergy()));
                        if (possibility < champion) {
                            champion = possibility;
                            left = a.getLines().get(i);
                            right = B.getLines().get(j);
                        }
                    }
                }
            }
        }
        if ((left == null) && (right == null)) {
            //other valid case: a compatible "pz" and "s" orbital are found
            for (i = 0; i < aLines; i++) {
                if (((a.getLines().get(i).getType().equals("p"))
                        && (a.getLines().get(i).getSubtype().equals("z")))
                        || (a.getLines().get(i).getType().equals("s"))) {
                    for (j = 0; j < bLines; j++) {
                        if (((B.getLines().get(j).getStrength().equals("sigma"))
                                && (B.getLines().get(j).getPolarity().equals("z"))
                                && (B.getLines().get(j).getSymmetry().equals("0")))
                                || ((B.getLines().get(j).getStrength().equals("sigma"))
                                && (B.getLines().get(j).getSymmetry().equals("0")))) {
                            possibility = Math.abs((a.getLines().get(i).getEnergy()) - (B.getLines().get(j).getEnergy()));
                            if (possibility < champion) {
                                champion = possibility;
                                left = a.getLines().get(i);
                                right = B.getLines().get(j);
                            }
                        }
                    }
                }
            }
        }
        if ((left == null) && (right == null)) {
            //no compatible orbitals were found
            return;
        }
        MO bonding, antibonding;
        //calculate energies of the bonding and antibonding orbitals
        if ((left.getEnergy()) == (right.getEnergy())) {
            if ((a.getElectro()) > (B.getCenter().getElectro())) {
                double energies[] = calculate(a, B.getCenter(), left, right);
                bonding = new MO(energies[0], 0, "sigma", "z", " ");
                antibonding = new MO(energies[1], 0, "sigma", "z", "*");
            }
            else {
                double energies[] = calculate(B.getCenter(), a, right, left);
                bonding = new MO(energies[0], 0, "sigma", "z", " ");
                antibonding = new MO(energies[1], 0, "sigma", "z", "*");
            }
        }
        else if ((left.getEnergy()) > (right.getEnergy())) {
            double energies[] = calculate(B.getCenter(), a, right, left);
            bonding = new MO(energies[0], 0, "sigma", "z", " ");
            antibonding = new MO(energies[1], 0, "sigma", "z", "*");
        }
        else {
            double energies[] = calculate(a, B.getCenter(), left, right);
            bonding = new MO(energies[0], 0, "sigma", "z", " ");
            antibonding = new MO(energies[1], 0, "sigma", "z", "*");
        }
        if ((left.getType().equals("s")) && (right.getPolarity().equals("s"))) {
            //maintains the spherical pseudo-direction for qualifying orbitals
            bonding.setPolarity("s");
            antibonding.setPolarity("s");
        }
        //create the molecule's list of orbitals
        B.addElement(a);
        /* this is for forming double and triple bonds - incomplete; please ignore
        MO pibondx = null;
        MO pibondy = null;
        MO piantix = null;
        MO piantiy = null;
        if (degree > 1) {
            AO piL = a.getLine(a.getLines().get(i).getRow() + "px");
            AO piR = b.getLine(b.getLines().get(j).getRow() + "px");
            double pinergies[] = calculate(a, b, piL, piR);
            pibondx = new MO(pinergies[0], 0, "pi", "x", " ");
            pibondy = new MO(pinergies[0], 0, "pi", "y", " ");
            piantix = new MO(pinergies[1], 0, "pi", "x", "*");
            piantiy = new MO(pinergies[1], 0, "pi", "y", "*");
            
        }*/
        MO non;
        for (i = 0; i < aLines; i++) {
            if ((a.getLines().get(i).getFullName()).equals(left.getFullName())) {
                continue;
            }
            /* this is for forming double and triple bonds - incomplete; please ignore
            if (degree > 1) {
                if ((a.getLines().get(i).getFullName()).equals())
            }*/
            non = new MO(a.getLines().get(i).getEnergy(), 0, typeConvert(a.getLines().get(i)), a.getLines().get(i).getSubtype(),"0");
            B.addOrbital(non);
        }
        B.getLines().remove(right);
        B.addOrbital(bonding);
        B.addOrbital(antibonding);
        /* this is for forming double and triple bonds - incomplete; please ignore
        if (degree > 1) {
            output.addOrbital(pibondx);
            output.addOrbital(pibondy);
            output.addOrbital(piantix);
            output.addOrbital(piantiy);
        }*/
        // sort all the orbitals by energy level
        sort(B);
        //adds the correct number of electrons to the molecule/its orbitals
        for (MO strip : B.getLines()) {
            while (strip.getElectrons() > 0) {
                strip.removeElectron();
            }
        }
        electronFill(B);
        for (MO quickfix : B.getLines()) {
            //maintains the spherical pseudo-direction for qualifying orbitals
            if (quickfix.getPolarity() == null) {
                quickfix.setPolarity("s");
            }
        }
    }
    
    /**
     * bonds two molecules together to make a larger, singular molecule
     * @param A one of the proto-molecules that will join to form the larger molecule
     * @param B the other proto-molecule that will join to form the larger molecule
     */
    public static void bond(Molecule A, Molecule B) {
        
    }
    
    /**
     * approximation formula for calculating the energy levels of a bonding and antibonding orbital
     * @param l the atom whose orbital participating in the bond has the lower energy level
     * @param h the atom whose orbital participating in the bond has the higher energy level
     * @param b the orbital participating in the bond with the lower energy level
     * @param a the orbital participating in the bond with the higher energy level
     * @return[0] the energy level of the bonding orbital
     * @return[1] the energy level of the antibonding orbital
     */
    public static double[] calculate(Element l, Element h, Suborbital b, Suborbital a) {
        //approximate distance between nuclei
        double[] output = new double[2];
        double a0 = 5.29e-11;
        double comp1 = (h.getRadius() + l.getRadius());
        double comp2 = (h.getRadius() * l.getRadius());
        double comp3 = Math.sqrt(h.getElectro());
        double comp4 = Math.sqrt(l.getElectro());
        double comp5 = (h.getElectro() * h.getRadius());
        double comp6 = (l.getElectro() * l.getRadius());
        comp3 = comp3 - comp4;
        comp3 = Math.pow(comp3, 2);
        comp2 = comp2*comp3;
        comp5 = comp5 + comp6;
        comp2 = comp2 / comp5;
        double R = comp1 - comp2;
        
        //estimate percentage of orbital overlapping
        comp1 = R / a0;
        comp2 = Math.pow(R, 2);
        comp3 = Math.pow(a0, 2);
        comp3 = comp3 * 3;
        comp2 = comp2 / comp3;
        comp2 = 1 + (comp1 + comp2);
        comp4 = -comp1;
        comp4 = Math.pow(Math.E, comp4);
        double S = comp4 * comp2;
        if ((S < 0) || S > 1) {
            System.out.println("Whoa, fatal error - S is " + S);
        }
        
        //approximate Hamiltonian (whatever that is) of atoms
        comp1 = (b.getEnergy()) * (a.getEnergy());
        comp2 = Math.sqrt(comp1);
        comp3 = S * comp2;
        double H = -1.75 * comp3;
        
        //compute energy levels given all the previous approximations
        if ((l.getName().equals(h.getName()) && (a.getEnergy() == b.getEnergy()))) {
            //bond is homonuclear
            comp1 = a.getEnergy() - H;
            comp2 = a.getEnergy() + H;
            comp3 = 1 + S;
            comp4 = 1 - S;
            output[0] = comp2 / comp3;
            output[1] = comp1 / comp4;
        }
        else {
            //bond is heteronuclear
            comp1 = (a.getEnergy()) - (b.getEnergy());
            comp2 = (b.getEnergy() * S);
            comp3 = (a.getEnergy() * S);
            comp2 = H - comp2;
            comp3 = H - comp3;
            comp2 = Math.pow(comp2, 2);
            comp3 = Math.pow(comp3, 2);
            comp4 = comp2 / comp1;
            comp5 = comp3 / comp1;
            output[0] = (b.getEnergy()) - comp4;
            output[1] = (a.getEnergy()) + comp5;
        }
        
        return output;
    }
    
    /**
     * converts an atomic orbital's type terminology to a molecular orbital's type terminology
     * @param r the orbital being analyzed
     * @return the corresponding type if it was a molecular orbital
     */
    public static String typeConvert(AO r) {
        switch (r.getType()) {
            case "s":
                return "sigma";
            case "p":
                return "pi";
            case "d":
                return "delta";
            default:
                System.out.println("ERROR: AO " + r.getFullName() + " doesn't have a valid type!");
                return null;
        }
    }
    
    /**
     * reorders the molecular orbitals in the ArrayList so that they're sorted from lowest energy level to highest energy level
     * @param orbs the molecule whose ArrayList needs sorting
     */
    public static void sort(Molecule orbs) {
        int j, champion;
        for (int i = 0; i < orbs.getLines().size(); i++) {
            champion = i;
            for (j = i; j < orbs.getLines().size(); j++) {
                if ((orbs.getLines().get(j).getEnergy()) < (orbs.getLines().get(champion).getEnergy())) {
                    champion = j;
                }
            }
            Collections.swap(orbs.getLines(), i, champion);
        }
    }
    
    /**
     * fills a molecule's orbitals with the correct number of electrons
     * @param orbs the molecule that needs to be filled with valence electrons
     * @return the same molecule, now filled with the correct number of valence electrons
     */
    public static Molecule electronFill(Molecule orbs) {
        // fill in the molecule's electrons
        int iter = 0;
        int looper, identicals;
        int remaining = orbs.getElectrons();
        MO unfilled;
        while (remaining > 0) {
            unfilled = orbs.getLines().get(iter);
            while ((unfilled.getElectrons() < 2) && (remaining > 0)) {
                unfilled.addElectron();
                remaining--;
                if ((unfilled.getElectrons() == 1) && (remaining > 0)) {
                    identicals = 1;
                    for (looper = iter + 1; looper < orbs.getLines().size(); looper++) {
                        if ((remaining > 0) && (orbs.getLines().get(looper).getEnergy() == unfilled.getEnergy())) {
                            identicals++;
                            orbs.getLines().get(looper).addElectron();
                            remaining--;
                        }
                    }
                    for (int k = 0; k < identicals; k++) {
                        if (remaining > 0) {
                            orbs.getLines().get(iter + k).addElectron();
                            remaining--;
                        }
                    }
                }
            }
            iter++;
        }
        return orbs;
    }
    
    /**
     * prints out a list of all a molecule's molecular orbitals
     * @param orbs the molecule whose orbitals should be displayed
     */
    public static void rollCall(Molecule orbs) {
        if (orbs == null) {
            System.out.println("Something about this molecule won't let it exist.");
        }
        else {
        int totalElectrons = 0;
            System.out.println("Molecular orbitals in " + orbs.getName() + ":");
            for (MO line : orbs.getLines()) {
                System.out.print("a" + printConvert(line) + " orbital");
                System.out.println(" at " + line.getEnergy() + " eVs, with " + line.getElectrons() + " electrons");
                totalElectrons = totalElectrons + line.getElectrons();
            }
            System.out.println("Total electrons in molecule: " + totalElectrons);
            System.out.println("");
        }
    }
    
    /**
     * converts the variables of a molecular orbital into a more user-friendly terminology
     * @param stuff the orbital about to be printed
     * @return what should be printed for that orbital
     */
    public static String printConvert(MO stuff) {
        if (stuff.getPolarity().equals("s")) {
            switch (stuff.getSymmetry()) {
                case " ":
                    return (" bonding " + stuff.getStrength());
                case "b":
                    return (" bonding " + stuff.getStrength());
                case "0":
                    return (" nonbonding " + stuff.getStrength());
                case "*":
                    return ("n antibonding " + stuff.getStrength());
                case "a":
                    return ("n antibonding " + stuff.getStrength());
                default:
                    return "???";
            }
        }
        else {
            switch (stuff.getSymmetry()) {
                case " ":
                    return (" bonding " + stuff.getStrength() + " " + stuff.getPolarity());
                case "b":
                    return (" bonding " + stuff.getStrength()+ " " + stuff.getPolarity());
                case "0":
                    return (" nonbonding " + stuff.getStrength()+ " " + stuff.getPolarity());
                case "*":
                    return ("n antibonding " + stuff.getStrength()+ " " + stuff.getPolarity());
                case "a":
                    return ("n antibonding " + stuff.getStrength()+ " " + stuff.getPolarity());
                default:
                    return "???";
            }
        }
    }
}
