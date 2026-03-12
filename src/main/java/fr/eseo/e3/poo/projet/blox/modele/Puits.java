package fr.eseo.e3.poo.projet.blox.modele;

// ANTI-PLAGIAT : Code généré avec assistance IA (Claude, Anthropic) dans le cadre du projet pédagogique Falling Blox

import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.Tetromino;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Le puits est le terrain de jeu principal.
 * Il gère la pièce en cours de chute, la pièce suivante, le tas
 * et notifie la vue via {@link PropertyChangeSupport}.
 *
 * <p>Dimensions valides :</p>
 * <ul>
 *   <li>Largeur : [{@value #LARGEUR_MIN}, {@value #LARGEUR_MAX}]</li>
 *   <li>Hauteur : [{@value #HAUTEUR_MIN}, {@value #HAUTEUR_MAX}]</li>
 * </ul>
 */
public class Puits {

    // -------------------------------------------------------------------------
    // Constantes
    // -------------------------------------------------------------------------

    public static final int LARGEUR_PAR_DEFAUT = 10;
    public static final int HAUTEUR_PAR_DEFAUT = 20;
    public static final int LARGEUR_MIN = 5;
    public static final int LARGEUR_MAX = 15;
    public static final int HAUTEUR_MIN = 15;
    public static final int HAUTEUR_MAX = 25;

    /** Nom de la propriété écoutée par la vue pour la pièce actuelle. */
    public static final String PROP_PIECE_ACTUELLE  = "pieceActuelle";
    /** Nom de la propriété écoutée par la vue pour la pièce suivante. */
    public static final String PROP_PIECE_SUIVANTE  = "pieceSuivante";
    /** Nom de la propriété écoutée par la vue pour le tas. */
    public static final String PROP_TAS             = "tas";

    // -------------------------------------------------------------------------
    // Champs
    // -------------------------------------------------------------------------

    private final int largeur;
    private final int hauteur;

    private Piece pieceActuelle;
    private Piece pieceSuivante;

    private final Tas tas;
    private final PropertyChangeSupport pcs;

    // -------------------------------------------------------------------------
    // Constructeurs
    // -------------------------------------------------------------------------

    /**
     * Crée un puits avec des dimensions personnalisées.
     *
     * @param largeur largeur du puits en colonnes
     * @param hauteur hauteur du puits en lignes
     * @throws IllegalArgumentException si les dimensions sont hors des limites autorisées
     */
    public Puits(int largeur, int hauteur) {
        if (largeur < LARGEUR_MIN || largeur > LARGEUR_MAX) {
            throw new IllegalArgumentException(
                "Largeur invalide : " + largeur + ". Attendu entre "
                + LARGEUR_MIN + " et " + LARGEUR_MAX + ".");
        }
        if (hauteur < HAUTEUR_MIN || hauteur > HAUTEUR_MAX) {
            throw new IllegalArgumentException(
                "Hauteur invalide : " + hauteur + ". Attendu entre "
                + HAUTEUR_MIN + " et " + HAUTEUR_MAX + ".");
        }
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.tas = new Tas(this);
        this.pcs = new PropertyChangeSupport(this);
    }

    /**
     * Crée un puits avec les dimensions par défaut
     * ({@value #LARGEUR_PAR_DEFAUT} × {@value #HAUTEUR_PAR_DEFAUT}).
     */
    public Puits() {
        this(LARGEUR_PAR_DEFAUT, HAUTEUR_PAR_DEFAUT);
    }

    // -------------------------------------------------------------------------
    // Accesseurs
    // -------------------------------------------------------------------------

    public int getLargeur() {
        return largeur;
    }

    public int getHauteur() {
        return hauteur;
    }

    public Piece getPieceActuelle() {
        return pieceActuelle;
    }

    public Piece getPieceSuivante() {
        return pieceSuivante;
    }

    public Tas getTas() {
        return tas;
    }

    // -------------------------------------------------------------------------
    // Mutateurs avec notification PropertyChange
    // -------------------------------------------------------------------------

    /**
     * Définit la pièce actuelle et la positionne en haut au centre du puits.
     * Notifie les écouteurs de la propriété {@value #PROP_PIECE_ACTUELLE}.
     *
     * @param piece nouvelle pièce active
     */
    public void setPieceActuelle(Piece piece) {
        Piece ancienne = this.pieceActuelle;
        this.pieceActuelle = piece;

        if (piece != null) {
            try {
                // Position initiale : centré en haut
                piece.setPosition(largeur / 2, 0);
            } catch (BloxException e) {
                // Game over : la pièce ne peut pas être placée
            }
        }

        pcs.firePropertyChange(PROP_PIECE_ACTUELLE, ancienne, this.pieceActuelle);
    }

    /**
     * Définit la pièce suivante.
     * Notifie les écouteurs de la propriété {@value #PROP_PIECE_SUIVANTE}.
     *
     * @param piece prochaine pièce à jouer
     */
    public void setPieceSuivante(Piece piece) {
        Piece ancienne = this.pieceSuivante;
        this.pieceSuivante = piece;
        pcs.firePropertyChange(PROP_PIECE_SUIVANTE, ancienne, this.pieceSuivante);
    }

    // -------------------------------------------------------------------------
    // Logique de jeu
    // -------------------------------------------------------------------------

    /**
     * Fait descendre la pièce actuelle d'une ligne.
     * Si elle ne peut plus descendre (sortie du puits ou collision),
     * elle est intégrée au tas et la pièce suivante devient la pièce actuelle.
     *
     * @throws BloxException si le jeu est terminé (nouvelle pièce ne peut pas être placée)
     */
    public void gravite() throws BloxException {
        if (pieceActuelle == null) {
            return;
        }

        // Tentative de déplacement vers le bas
        try {
            pieceActuelle.deplacerDe(0, 1);
            verifierPositionPiece(pieceActuelle);
        } catch (BloxException e) {
            // Impossible de descendre : annulation du déplacement
            try {
                pieceActuelle.deplacerDe(0, -1);
            } catch (BloxException ignored) {
                // Ne devrait pas arriver
            }

            // Intégration de la pièce au tas
            tas.ajouterElements(pieceActuelle.getElements());
            pcs.firePropertyChange(PROP_TAS, null, tas);

            // Passage à la pièce suivante
            Piece prochaine = pieceSuivante;
            setPieceSuivante(Tetromino.genererTetromino(largeur));
            setPieceActuelle(prochaine);
        }
    }

    /**
     * Déplace la pièce actuelle horizontalement si le déplacement est valide.
     *
     * @param deltaX nombre de colonnes (négatif = gauche, positif = droite)
     * @throws BloxException si le déplacement est invalide
     */
    public void deplacerPieceHorizontalement(int deltaX) throws BloxException {
        if (pieceActuelle == null) {
            return;
        }
        pieceActuelle.deplacerDe(deltaX, 0);
        try {
            verifierPositionPiece(pieceActuelle);
        } catch (BloxException e) {
            pieceActuelle.deplacerDe(-deltaX, 0);
            throw e;
        }
    }

    /**
     * Vérifie que tous les éléments de la pièce se trouvent dans les limites
     * du puits et ne chevauchent pas le tas.
     *
     * @param piece la pièce à valider
     * @throws BloxException en cas de sortie du puits ou de collision
     */
    private void verifierPositionPiece(Piece piece) throws BloxException {
        for (Element e : piece.getElements()) {
            int x = e.getCoordonnees().getAbscisse();
            int y = e.getCoordonnees().getOrdonnee();

            if (x < 0 || x >= largeur || y >= hauteur) {
                throw new BloxException(
                    "Sortie du puits en (" + x + ", " + y + ")",
                    BloxException.BLOX_SORTIE_PUITS);
            }
            if (tas.contient(x, y)) {
                throw new BloxException(
                    "Collision avec le tas en (" + x + ", " + y + ")",
                    BloxException.BLOX_COLLISION);
            }
        }
    }

    // -------------------------------------------------------------------------
    // Gestion des écouteurs PropertyChange
    // -------------------------------------------------------------------------

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(propertyName, listener);
    }

    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(propertyName, listener);
    }

    // -------------------------------------------------------------------------
    // Redéfinition Object
    // -------------------------------------------------------------------------

    @Override
    public String toString() {
        return "Puits de largeur " + largeur + " et de hauteur " + hauteur;
    }
}
