# Sequential-Diatomic-Proof-of-Concept

Environment Setup:
... Honestly, I don't know. I wrote the code in Java using Netbeans, and I just uploaded the entire Netbeans project.
Maybe have Netbeans already set up on your machine?


The Important Files Where Most of the Program is Contained:
A. [MOD3D.java](MOD3D.java) - This is the main script. Currently, all it does is initialize all the elements,
    generates a water molecule, and displays its orbitals.
B. [Bonder.java](Bonder.java) - This is the meat of the program. It contains the vast majority of the methods for actually
    creating molecules, bonding, and whatnot. Said methods include:
    a. bonding atoms of two Elements together
    b. bonding an atom of an Element to a Molecule
    c. bonding two Molecules together (not yet implemented)
    d. calculating the energy levels of a bonding/antibonding molecular orbital pair
    e. sorting the MOs in a Molecule from lowest energy level to highest energy level
    f. filling a Molecule/its orbitals with the correct number of electrons
    g. printing information about a Molecule's Molecular Orbital Diagram to the terminal
C. [Suborbital.java](Suborbital.java) - This is the parent class for AO and MO.
D. [AO.java](AO.java) - provides all the source code for an AO (Atomic Orbital) object
E. [MO.java](MO.java) - provides all the source code for an MO (Molecular Orbital) object
F. [Element.java](Element.java) - This is the parent class for all the different Element classes
G. [Molecule.java](Molecule.java) - provides all the source code for a Molecule object (as well as a few of the methods
    that will allow you to do things with it)
H. [Hydrogen.java](Hydrogen.java), [Carbon.java](Carbon.java), [Nitrogen.java](Nitrogen.java), and [Oxygen.java](Oxygen.java)
    - the source code for each different type of Element available at the moment

Bugs To Be Addressed First:
1. The rotate method should be called from Main, not Bonder - you won't always swap the x and z axes, after all.
2. The code for differentiating between a single, double, and triple bond is not yet completed. It has been commented out
    for now, but it is incomplete and will fatally crash the program if uncommented.