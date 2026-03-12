package fr.eseo.e3.poo.projet.blox.vue;

// ANTI-PLAGIAT : Code généré avec assistance IA (Claude, Anthropic) dans le cadre du projet pédagogique Falling Blox

import fr.eseo.e3.poo.projet.blox.modele.Element;
import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Panneau d'information latéral affichant la prochaine pièce à jouer.
 *
 * <p>Taille de la zone de prévisualisation : {@value #TAILLE_PREVIEW} × {@value #TAILLE_PREVIEW} px.</p>
 */
public class PanneauInformation extends JPanel implements PropertyChangeListener {

    private static final long serialVersionUID = 1L;

    /** Taille de la zone de prévisualisation de la pièce suivante (en pixels). */
    public static final int TAILLE_PREVIEW = 70;

    /** Taille d'une cellule dans la zone de prévisualisation. */
    private static final int TAILLE_CELLULE_PREVIEW = 14;

    private final Puits puits;

    /** Sous-panneau dédié au dessin de la pièce suivante. */
    private final PreviewPanel previewPanel;

    // -------------------------------------------------------------------------
    // Constructeur
    // -------------------------------------------------------------------------

    /**
     * Crée le panneau d'information associé au puits donné.
     *
     * @param puits le puits dont la pièce suivante sera affichée
     */
    public PanneauInformation(Puits puits) {
        this.puits = puits;
        puits.addPropertyChangeListener(this);

        setLayout(new BorderLayout(0, 4));
        setBackground(Color.LIGHT_GRAY);
        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        // Étiquette titre
        JLabel titre = new JLabel("Suivant", JLabel.CENTER);
        titre.setFont(titre.getFont().deriveFont(Font.BOLD, 13f));
        add(titre, BorderLayout.NORTH);

        // Zone de prévisualisation
        previewPanel = new PreviewPanel();
        add(previewPanel, BorderLayout.CENTER);
    }

    // -------------------------------------------------------------------------
    // PropertyChangeListener
    // -------------------------------------------------------------------------

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (Puits.PROP_PIECE_SUIVANTE.equals(evt.getPropertyName())) {
            previewPanel.repaint();
        }
    }

    // -------------------------------------------------------------------------
    // Sous-panneau de dessin
    // -------------------------------------------------------------------------

    /**
     * Panneau interne dédié au rendu de la pièce suivante.
     */
    private class PreviewPanel extends JPanel {

        private static final long serialVersionUID = 1L;

        PreviewPanel() {
            setPreferredSize(new Dimension(TAILLE_PREVIEW, TAILLE_PREVIEW));
            setBackground(Color.WHITE);
            setBorder(BorderFactory.createLineBorder(Color.GRAY));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Piece pieceSuivante = puits.getPieceSuivante();
            if (pieceSuivante == null) {
                return;
            }

            // Calcul du centre de la zone de prévisualisation
            int centreX = getWidth()  / 2;
            int centreY = getHeight() / 2;

            Element[] elements = pieceSuivante.getElements();
            // Coordonnée de référence de la pièce
            int refX = elements[0].getCoordonnees().getAbscisse();
            int refY = elements[0].getCoordonnees().getOrdonnee();

            for (int i = 0; i < elements.length; i++) {
                Element elem = elements[i];
                int relX = elem.getCoordonnees().getAbscisse() - refX;
                int relY = elem.getCoordonnees().getOrdonnee() - refY;

                int px = centreX + relX * TAILLE_CELLULE_PREVIEW - TAILLE_CELLULE_PREVIEW / 2;
                int py = centreY + relY * TAILLE_CELLULE_PREVIEW - TAILLE_CELLULE_PREVIEW / 2;

                Color couleur = elem.getCouleur().getCouleurPourAffichage();
                if (i == 0) {
                    couleur = VuePiece.eclaircir(couleur, VuePiece.MULTIPLIER_TEINTE);
                }

                g.setColor(couleur);
                g.fill3DRect(px, py, TAILLE_CELLULE_PREVIEW, TAILLE_CELLULE_PREVIEW, true);
                g.setColor(couleur.darker());
                g.draw3DRect(px, py, TAILLE_CELLULE_PREVIEW - 1, TAILLE_CELLULE_PREVIEW - 1, true);
            }
        }
    }
}
