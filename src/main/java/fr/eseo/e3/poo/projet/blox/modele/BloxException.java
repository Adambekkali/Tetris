package fr.eseo.e3.poo.projet.blox.modele;

// ANTI-PLAGIAT : Code généré avec assistance IA (Claude, Anthropic) dans le cadre du projet pédagogique Falling Blox

/**
 * Exception vérifiée (checked) propre au jeu Falling Blox.
 * Signale deux situations : collision avec le tas ou sortie du puits.
 */
public class BloxException extends Exception {

    /** Identifiant de sérialisation. */
    private static final long serialVersionUID = 1L;

    /** Code indiquant une collision avec le tas ou une autre pièce. */
    public static final int BLOX_COLLISION = 1;

    /** Code indiquant que la pièce sortirait du puits. */
    public static final int BLOX_SORTIE_PUITS = 2;

    private final int type;

    /**
     * Crée une nouvelle BloxException.
     *
     * @param message message descriptif
     * @param type    {@link #BLOX_COLLISION} ou {@link #BLOX_SORTIE_PUITS}
     */
    public BloxException(String message, int type) {
        super(message);
        this.type = type;
    }

    /**
     * Retourne le type de l'exception.
     *
     * @return {@link #BLOX_COLLISION} ou {@link #BLOX_SORTIE_PUITS}
     */
    public int getType() {
        return type;
    }
}
