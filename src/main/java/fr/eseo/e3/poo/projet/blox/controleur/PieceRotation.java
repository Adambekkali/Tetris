package fr.eseo.e3.poo.projet.blox.controleur;

// ANTI-PLAGIAT : Code généré avec assistance IA (Claude, Anthropic) dans le cadre du projet pédagogique Falling Blox

import fr.eseo.e3.poo.projet.blox.modele.BloxException;
import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.vue.VuePuits;

import javax.swing.SwingUtilities;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Contrôleur gérant la rotation de la pièce active par clic souris.
 *
 * <ul>
 *   <li>Clic gauche  → rotation anti-horaire ({@code sensHoraire = false})</li>
 *   <li>Clic droit   → rotation horaire      ({@code sensHoraire = true})</li>
 * </ul>
 */
public class PieceRotation extends MouseAdapter {

    private final VuePuits vuePuits;

    /**
     * Crée le contrôleur de rotation associé à la vue donnée.
     *
     * @param vuePuits la vue du puits sur laquelle écouter les clics
     */
    public PieceRotation(VuePuits vuePuits) {
        this.vuePuits = vuePuits;
    }

    /**
     * Traite un clic souris et fait tourner la pièce actuelle.
     *
     * @param e événement souris
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        Puits puits = vuePuits.getPuits();
        if (puits.getPieceActuelle() == null) {
            return;
        }

        try {
            if (SwingUtilities.isLeftMouseButton(e)) {
                // Clic gauche : rotation anti-horaire
                puits.getPieceActuelle().tourner(false);
            } else if (SwingUtilities.isRightMouseButton(e)) {
                // Clic droit : rotation horaire
                puits.getPieceActuelle().tourner(true);
            }
        } catch (BloxException ex) {
            // Rotation invalide : ignorée
        }
        vuePuits.repaint();
    }
}
