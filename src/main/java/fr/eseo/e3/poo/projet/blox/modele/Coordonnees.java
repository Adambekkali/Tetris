package fr.eseo.e3.poo.projet.blox.modele;

// ANTI-PLAGIAT : Code généré avec assistance IA (Claude, Anthropic) dans le cadre du projet pédagogique Falling Blox

import java.util.Objects;

/**
 * Représente une paire de coordonnées (abscisse, ordonnée) dans le puits.
 */
public class Coordonnees {

    private int abscisse;
    private int ordonnee;

    /**
     * Constructeur avec abscisse et ordonnée.
     *
     * @param abscisse la colonne (axe X)
     * @param ordonnee la ligne   (axe Y, croissant vers le bas)
     */
    public Coordonnees(int abscisse, int ordonnee) {
        this.abscisse = abscisse;
        this.ordonnee = ordonnee;
    }

    // -------------------------------------------------------------------------
    // Accesseurs / mutateurs
    // -------------------------------------------------------------------------

    public int getAbscisse() {
        return abscisse;
    }

    public void setAbscisse(int abscisse) {
        this.abscisse = abscisse;
    }

    public int getOrdonnee() {
        return ordonnee;
    }

    public void setOrdonnee(int ordonnee) {
        this.ordonnee = ordonnee;
    }

    // -------------------------------------------------------------------------
    // Redéfinitions Object
    // -------------------------------------------------------------------------

    /**
     * Format imposé par l'Assignment Centre : {@code (x, y)}.
     */
    @Override
    public String toString() {
        return "(" + abscisse + ", " + ordonnee + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Coordonnees)) {
            return false;
        }
        Coordonnees other = (Coordonnees) obj;
        return abscisse == other.abscisse && ordonnee == other.ordonnee;
    }

    @Override
    public int hashCode() {
        return Objects.hash(abscisse, ordonnee);
    }
}
