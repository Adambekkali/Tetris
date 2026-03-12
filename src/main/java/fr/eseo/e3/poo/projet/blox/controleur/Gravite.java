package fr.eseo.e3.poo.projet.blox.controleur;

// ANTI-PLAGIAT : Code généré avec assistance IA (Claude, Anthropic) dans le cadre du projet pédagogique Falling Blox

import fr.eseo.e3.poo.projet.blox.modele.BloxException;
import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.vue.VuePuits;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Contrôleur de gravité : fait descendre automatiquement la pièce active
 * à intervalle régulier grâce à un {@link javax.swing.Timer}.
 *
 * <p>À chaque tick du timer, {@link Puits#gravite()} est appelé, puis
 * la vue est redessinée.</p>
 */
public class Gravite implements ActionListener {

    /** Intervalle par défaut entre deux descentes (en millisecondes). */
    public static final int INTERVALLE_PAR_DEFAUT = 500;

    private final Puits    puits;
    private final VuePuits vuePuits;
    private final Timer    timer;

    // -------------------------------------------------------------------------
    // Constructeurs
    // -------------------------------------------------------------------------

    /**
     * Crée le contrôleur de gravité avec un intervalle personnalisé.
     *
     * @param puits     le modèle du puits
     * @param vuePuits  la vue à redessiner
     * @param intervalle intervalle en ms entre deux descentes
     */
    public Gravite(Puits puits, VuePuits vuePuits, int intervalle) {
        this.puits    = puits;
        this.vuePuits = vuePuits;
        this.timer    = new Timer(intervalle, this);
    }

    /**
     * Crée le contrôleur de gravité avec l'intervalle par défaut ({@value #INTERVALLE_PAR_DEFAUT} ms).
     *
     * @param puits    le modèle du puits
     * @param vuePuits la vue à redessiner
     */
    public Gravite(Puits puits, VuePuits vuePuits) {
        this(puits, vuePuits, INTERVALLE_PAR_DEFAUT);
    }

    // -------------------------------------------------------------------------
    // Contrôle du timer
    // -------------------------------------------------------------------------

    /** Démarre la gravité automatique. */
    public void demarrer() {
        timer.start();
    }

    /** Arrête la gravité automatique. */
    public void arreter() {
        timer.stop();
    }

    /** Retourne le timer sous-jacent (utile pour les tests). */
    public Timer getTimer() {
        return timer;
    }

    // -------------------------------------------------------------------------
    // ActionListener
    // -------------------------------------------------------------------------

    /**
     * Appelé à chaque tick du timer : fait descendre la pièce d'une ligne
     * puis redessine la vue.
     *
     * @param e événement timer (ignoré)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            puits.gravite();
        } catch (BloxException ex) {
            // Transition normale (pièce posée, nouvelle générée) : déjà gérée dans Puits
        }
        vuePuits.repaint();
    }
}
