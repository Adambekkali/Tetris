package fr.eseo.e3.poo.projet.blox.vue;

// ANTI-PLAGIAT : Code généré avec assistance IA (Claude, Anthropic) dans le cadre du projet pédagogique Falling Blox

import fr.eseo.e3.poo.projet.blox.modele.Element;
import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Responsable du dessin de la pièce actuellement en chute dans le puits.
 *
 * <p>L'élément de référence (index 0) est dessiné avec une teinte plus claire
 * grâce au facteur {@link #MULTIPLIER_TEINTE}, afin de le distinguer visuellement.</p>
 */
public class VuePiece {

    /**
     * Facteur multiplicatif appliqué aux composantes R, G, B de la couleur
     * de l'élément de référence pour l'éclaircir.
     */
    public static final float MULTIPLIER_TEINTE = 1.3f;

    private final Puits puits;

    /**
     * Crée une VuePiece associée au puits donné.
     *
     * @param puits le puits contenant la pièce à afficher
     */
    public VuePiece(Puits puits) {
        this.puits = puits;
    }

    /**
     * Dessine la pièce actuelle dans le contexte graphique fourni.
     *
     * @param g            contexte graphique de la VuePuits
     * @param tailleCellule taille en pixels d'une cellule de la grille
     */
    public void afficher(Graphics g, int tailleCellule) {
        Piece piece = puits.getPieceActuelle();
        if (piece == null) {
            return;
        }

        Element[] elements = piece.getElements();
        for (int i = 0; i < elements.length; i++) {
            Element elem = elements[i];
            int x = elem.getCoordonnees().getAbscisse() * tailleCellule;
            int y = elem.getCoordonnees().getOrdonnee() * tailleCellule;

            Color couleurBase = elem.getCouleur().getCouleurPourAffichage();
            Color couleurDessiner;

            if (i == 0) {
                // Élément de référence : teinte plus claire
                couleurDessiner = eclaircir(couleurBase, MULTIPLIER_TEINTE);
            } else {
                couleurDessiner = couleurBase;
            }

            g.setColor(couleurDessiner);
            g.fill3DRect(x, y, tailleCellule, tailleCellule, true);

            // Contour
            g.setColor(couleurDessiner.darker());
            g.draw3DRect(x, y, tailleCellule - 1, tailleCellule - 1, true);
        }
    }

    // -------------------------------------------------------------------------
    // Méthode utilitaire
    // -------------------------------------------------------------------------

    /**
     * Éclaircit une couleur en multipliant chaque composante par {@code facteur}.
     * Les valeurs sont bornées à 255.
     *
     * @param base    couleur d'origine
     * @param facteur facteur d'éclaircissement (> 1 pour éclaircir)
     * @return couleur éclaircie
     */
    static Color eclaircir(Color base, float facteur) {
        int r = Math.min(255, (int) (base.getRed()   * facteur));
        int g = Math.min(255, (int) (base.getGreen() * facteur));
        int b = Math.min(255, (int) (base.getBlue()  * facteur));
        return new Color(r, g, b);
    }
}
