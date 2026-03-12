package fr.eseo.e3.poo.projet.blox.controleur;

// ANTI-PLAGIAT : Code généré avec assistance IA (Claude, Anthropic) dans le cadre du projet pédagogique Falling Blox

import fr.eseo.e3.poo.projet.blox.modele.BloxException;
import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.vue.VuePuits;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

/**
 * Contrôleur gérant le déplacement horizontal de la pièce à la souris
 * et la descente accélérée via la molette.
 *
 * <ul>
 *   <li>Déplacement horizontal : la pièce suit la colonne de la souris
 *       (uniquement lors d'un changement de colonne dans la grille).</li>
 *   <li>Molette : chaque cran fait descendre la pièce d'une ligne
 *       (appel à {@link Puits#gravite()}).</li>
 * </ul>
 */
public class PieceDeplacement extends MouseAdapter {

    private final VuePuits vuePuits;

    /**
     * Crée le contrôleur de déplacement associé à la vue donnée.
     *
     * @param vuePuits la vue du puits sur laquelle écouter les événements souris
     */
    public PieceDeplacement(VuePuits vuePuits) {
        this.vuePuits = vuePuits;
    }

    // -------------------------------------------------------------------------
    // Déplacement horizontal à la souris
    // -------------------------------------------------------------------------

    /**
     * Déplace la pièce horizontalement si la souris se trouve dans une colonne
     * différente de la colonne actuelle de l'élément de référence.
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        Puits puits = vuePuits.getPuits();
        if (puits.getPieceActuelle() == null) {
            return;
        }

        int tailleCellule = VuePuits.TAILLE_CELLULE;
        // Colonne cible (en coordonnées-grille)
        int colonneViee = e.getX() / tailleCellule;
        // Colonne actuelle de l'élément de référence (index 0)
        int colonneActuelle = puits.getPieceActuelle()
                                   .getElements()[0]
                                   .getCoordonnees()
                                   .getAbscisse();

        if (colonneViee != colonneActuelle) {
            int delta = colonneViee - colonneActuelle;
            try {
                puits.deplacerPieceHorizontalement(delta);
            } catch (BloxException ex) {
                // Déplacement invalide (sortie du puits) : on ignore
            }
            vuePuits.repaint();
        }
    }

    // -------------------------------------------------------------------------
    // Descente accélérée à la molette
    // -------------------------------------------------------------------------

    /**
     * Fait descendre la pièce d'autant de lignes que le nombre de crans molette.
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        Puits puits = vuePuits.getPuits();
        if (puits.getPieceActuelle() == null) {
            return;
        }

        int nbCrans = Math.abs(e.getWheelRotation());
        for (int i = 0; i < nbCrans; i++) {
            try {
                puits.gravite();
            } catch (BloxException ex) {
                // Fin de descente normale : ignorée
            }
        }
        vuePuits.repaint();
    }
}
