package fr.eseo.e3.poo.projet.blox.vue;

// ANTI-PLAGIAT : Code généré avec assistance IA (Claude, Anthropic) dans le cadre du projet pédagogique Falling Blox

import fr.eseo.e3.poo.projet.blox.modele.Element;
import fr.eseo.e3.poo.projet.blox.modele.Puits;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

/**
 * Responsable du dessin du tas (blocs accumulés au fond du puits).
 *
 * <p>Chaque élément du tas est dessiné avec une nuance plus sombre que la
 * couleur d'origine, grâce au facteur {@link #MULTIPLIER_NUANCE}.</p>
 */
public class VueTas {

    /**
     * Facteur multiplicatif appliqué aux composantes R, G, B de la couleur
     * d'un élément du tas pour l'assombrir.
     */
    public static final float MULTIPLIER_NUANCE = 0.7f;

    private final Puits puits;

    /**
     * Crée une VueTas associée au puits donné.
     *
     * @param puits le puits dont le tas doit être affiché
     */
    public VueTas(Puits puits) {
        this.puits = puits;
    }

    /**
     * Dessine tous les éléments du tas dans le contexte graphique fourni.
     *
     * @param g             contexte graphique de la VuePuits
     * @param tailleCellule taille en pixels d'une cellule de la grille
     */
    public void afficher(Graphics g, int tailleCellule) {
        List<Element> elements = puits.getTas().getElements();

        for (Element elem : elements) {
            int x = elem.getCoordonnees().getAbscisse() * tailleCellule;
            int y = elem.getCoordonnees().getOrdonnee() * tailleCellule;

            Color couleurBase    = elem.getCouleur().getCouleurPourAffichage();
            Color couleurNuancee = assombrir(couleurBase, MULTIPLIER_NUANCE);

            g.setColor(couleurNuancee);
            g.fill3DRect(x, y, tailleCellule, tailleCellule, true);

            // Contour
            g.setColor(couleurNuancee.darker());
            g.draw3DRect(x, y, tailleCellule - 1, tailleCellule - 1, true);
        }
    }

    // -------------------------------------------------------------------------
    // Méthode utilitaire
    // -------------------------------------------------------------------------

    /**
     * Assombrit une couleur en multipliant chaque composante par {@code facteur}.
     * Les valeurs sont bornées à 0.
     *
     * @param base    couleur d'origine
     * @param facteur facteur d'assombrissement (< 1 pour assombrir)
     * @return couleur assombrie
     */
    static Color assombrir(Color base, float facteur) {
        int r = Math.max(0, (int) (base.getRed()   * facteur));
        int g = Math.max(0, (int) (base.getGreen() * facteur));
        int b = Math.max(0, (int) (base.getBlue()  * facteur));
        return new Color(r, g, b);
    }
}
