package fr.eseo.e3.poo.projet.blox.vue;

// ANTI-PLAGIAT : Code généré avec assistance IA (Claude, Anthropic) dans le cadre du projet pédagogique Falling Blox

import fr.eseo.e3.poo.projet.blox.modele.Puits;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Vue principale du puits de jeu.
 *
 * <p>Hérite de {@link JPanel}. Fond blanc, grille gris clair.
 * Délègue l'affichage de la pièce à {@link VuePiece} et du tas à {@link VueTas}.</p>
 *
 * <p>S'enregistre comme {@link PropertyChangeListener} auprès du puits
 * pour se redessiner automatiquement à chaque changement d'état.</p>
 */
public class VuePuits extends JPanel implements PropertyChangeListener {

    private static final long serialVersionUID = 1L;

    /** Taille en pixels d'une cellule de la grille. */
    public static final int TAILLE_CELLULE = 30;

    /** Couleur de fond du puits. */
    private static final Color COULEUR_FOND = Color.WHITE;

    /** Couleur des lignes de la grille. */
    private static final Color COULEUR_GRILLE = new Color(220, 220, 220);

    // -------------------------------------------------------------------------
    // Champs
    // -------------------------------------------------------------------------

    private final Puits    puits;
    private final VuePiece vuePiece;
    private final VueTas   vueTas;

    // -------------------------------------------------------------------------
    // Constructeur
    // -------------------------------------------------------------------------

    /**
     * Crée la vue associée au puits donné.
     *
     * @param puits le modèle du puits à afficher
     */
    public VuePuits(Puits puits) {
        this.puits    = puits;
        this.vuePiece = new VuePiece(puits);
        this.vueTas   = new VueTas(puits);

        // Abonnement aux changements du modèle
        puits.addPropertyChangeListener(this);

        setBackground(COULEUR_FOND);
        setPreferredSize(new Dimension(
            puits.getLargeur() * TAILLE_CELLULE,
            puits.getHauteur() * TAILLE_CELLULE));
    }

    // -------------------------------------------------------------------------
    // Accesseur
    // -------------------------------------------------------------------------

    /**
     * Retourne le modèle du puits associé à cette vue.
     *
     * @return le {@link Puits} sous-jacent
     */
    public Puits getPuits() {
        return puits;
    }

    // -------------------------------------------------------------------------
    // Affichage
    // -------------------------------------------------------------------------

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int largeur = puits.getLargeur();
        int hauteur = puits.getHauteur();

        // --- Fond blanc ---
        g.setColor(COULEUR_FOND);
        g.fillRect(0, 0, getWidth(), getHeight());

        // --- Grille gris clair ---
        g.setColor(COULEUR_GRILLE);
        for (int col = 0; col <= largeur; col++) {
            g.drawLine(col * TAILLE_CELLULE, 0, col * TAILLE_CELLULE, hauteur * TAILLE_CELLULE);
        }
        for (int ligne = 0; ligne <= hauteur; ligne++) {
            g.drawLine(0, ligne * TAILLE_CELLULE, largeur * TAILLE_CELLULE, ligne * TAILLE_CELLULE);
        }

        // --- Tas ---
        vueTas.afficher(g, TAILLE_CELLULE);

        // --- Pièce actuelle ---
        vuePiece.afficher(g, TAILLE_CELLULE);
    }

    // -------------------------------------------------------------------------
    // PropertyChangeListener
    // -------------------------------------------------------------------------

    /**
     * Déclenche un repaint à chaque notification de changement du modèle.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
    }
}
