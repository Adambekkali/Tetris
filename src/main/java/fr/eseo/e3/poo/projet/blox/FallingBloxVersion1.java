package fr.eseo.e3.poo.projet.blox;

// ANTI-PLAGIAT : Code généré avec assistance IA (Claude, Anthropic) dans le cadre du projet pédagogique Falling Blox

import fr.eseo.e3.poo.projet.blox.controleur.Gravite;
import fr.eseo.e3.poo.projet.blox.controleur.PieceDeplacement;
import fr.eseo.e3.poo.projet.blox.controleur.PieceRotation;
import fr.eseo.e3.poo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.modele.pieces.Tetromino;
import fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.OTetromino;
import fr.eseo.e3.poo.projet.blox.vue.PanneauInformation;
import fr.eseo.e3.poo.projet.blox.vue.VuePuits;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;

/**
 * Point d'entrée du jeu Falling Blox (version 1).
 *
 * <p>Disposition (BorderLayout) :</p>
 * <ul>
 *   <li>Centre : {@link VuePuits} (zone de jeu)</li>
 *   <li>Est    : {@link PanneauInformation} (pièce suivante)</li>
 * </ul>
 *
 * <p>La fenêtre est centrée et non redimensionnable.</p>
 */
public class FallingBloxVersion1 {

    /**
     * Lance le jeu Falling Blox dans l'EDT Swing.
     *
     * @param args arguments de la ligne de commande (non utilisés)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // --- Modèle ---
            Puits puits = new Puits(); // 10 × 20 par défaut

            // Génération des deux premières pièces
            puits.setPieceSuivante(Tetromino.genererTetromino(puits.getLargeur()));
            puits.setPieceActuelle(
                new OTetromino(new Coordonnees(puits.getLargeur() / 2, 0))
            );

            // --- Vues ---
            VuePuits vuePuits = new VuePuits(puits);
            PanneauInformation panneauInfo = new PanneauInformation(puits);

            // --- Contrôleurs ---
            PieceDeplacement deplacement = new PieceDeplacement(vuePuits);
            vuePuits.addMouseMotionListener(deplacement);
            vuePuits.addMouseWheelListener(deplacement);

            PieceRotation rotation = new PieceRotation(vuePuits);
            vuePuits.addMouseListener(rotation);

            Gravite gravite = new Gravite(puits, vuePuits);

            // --- Fenêtre principale ---
            JFrame frame = new JFrame("Falling Blox");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            frame.add(vuePuits,      BorderLayout.CENTER);
            frame.add(panneauInfo,   BorderLayout.EAST);
            frame.setResizable(false);
            frame.pack();
            frame.setLocationRelativeTo(null); // centrage à l'écran
            frame.setVisible(true);

            // Démarrage de la gravité automatique
            gravite.demarrer();
        });
    }
}
