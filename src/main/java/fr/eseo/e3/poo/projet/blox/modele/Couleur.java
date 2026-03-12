package fr.eseo.e3.poo.projet.blox.modele;

// ANTI-PLAGIAT : Code généré avec assistance IA (Claude, Anthropic) dans le cadre du projet pédagogique Falling Blox

import java.awt.Color;

/**
 * Enumération des couleurs disponibles pour les pièces du jeu Falling Blox.
 * Chaque couleur est associée à une java.awt.Color pour l'affichage graphique.
 */
public enum Couleur {

    ROUGE(Color.RED),
    ORANGE(Color.ORANGE),
    BLEU(Color.BLUE),
    VERT(Color.GREEN),
    JAUNE(Color.YELLOW),
    CYAN(Color.CYAN),
    VIOLET(Color.MAGENTA);

    private final Color couleurPourAffichage;

    Couleur(Color couleurPourAffichage) {
        this.couleurPourAffichage = couleurPourAffichage;
    }

    /**
     * Retourne la couleur AWT associée à cette valeur d'énumération.
     *
     * @return la java.awt.Color correspondante
     */
    public Color getCouleurPourAffichage() {
        return couleurPourAffichage;
    }
}
