package fr.eseo.e3.poo.projet.blox.modele;

// ANTI-PLAGIAT : Code généré avec assistance IA (Claude, Anthropic) dans le cadre du projet pédagogique Falling Blox

/**
 * Représente un bloc élémentaire (une case) du jeu, caractérisé par
 * ses coordonnées dans le puits et sa couleur.
 */
public class Element {

    private Coordonnees coordonnees;
    private Couleur couleur;

    /**
     * Constructeur principal.
     *
     * @param coordonnees position de l'élément dans le puits
     * @param couleur     couleur de l'élément
     */
    public Element(Coordonnees coordonnees, Couleur couleur) {
        this.coordonnees = coordonnees;
        this.couleur = couleur;
    }

    /**
     * Constructeur de commodité utilisant des coordonnées entières.
     *
     * @param abscisse colonne de l'élément
     * @param ordonnee ligne de l'élément
     * @param couleur  couleur de l'élément
     */
    public Element(int abscisse, int ordonnee, Couleur couleur) {
        this(new Coordonnees(abscisse, ordonnee), couleur);
    }

    // -------------------------------------------------------------------------
    // Accesseurs / mutateurs
    // -------------------------------------------------------------------------

    public Coordonnees getCoordonnees() {
        return coordonnees;
    }

    public void setCoordonnees(Coordonnees coordonnees) {
        this.coordonnees = coordonnees;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    // -------------------------------------------------------------------------
    // Redéfinition Object
    // -------------------------------------------------------------------------

    /**
     * Format imposé par l'Assignment Centre : {@code (x, y) - COULEUR}.
     */
    @Override
    public String toString() {
        return coordonnees.toString() + " - " + couleur.name();
    }
}
