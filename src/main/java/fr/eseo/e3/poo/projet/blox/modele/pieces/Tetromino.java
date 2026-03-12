package fr.eseo.e3.poo.projet.blox.modele.pieces;

// ANTI-PLAGIAT : Code généré avec assistance IA (Claude, Anthropic) dans le cadre du projet pédagogique Falling Blox

import fr.eseo.e3.poo.projet.blox.modele.BloxException;
import fr.eseo.e3.poo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.poo.projet.blox.modele.Couleur;
import fr.eseo.e3.poo.projet.blox.modele.Element;
import fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.ITetromino;
import fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.OTetromino;

import java.util.Random;

/**
 * Classe abstraite commune à tous les tétrominoes.
 * Gère le tableau de 4 éléments et les opérations de déplacement.
 * L'élément de référence (pivot de rotation) est toujours à l'index 0.
 */
public abstract class Tetromino implements Piece {

    /** Tableau des 4 éléments constituant le tétromino. */
    protected Element[] elements;

    private static final Random RANDOM = new Random();

    /**
     * Constructeur protégé, appelé par les sous-classes.
     *
     * @param elements tableau de 4 éléments déjà positionnés
     */
    protected Tetromino(Element[] elements) {
        if (elements == null || elements.length != 4) {
            throw new IllegalArgumentException("Un tétromino doit avoir exactement 4 éléments.");
        }
        this.elements = elements;
    }

    // -------------------------------------------------------------------------
    // Implémentation de Piece
    // -------------------------------------------------------------------------

    @Override
    public Element[] getElements() {
        return elements;
    }

    /**
     * Positionne l'élément de référence (index 0) aux coordonnées données
     * et déplace tous les autres éléments en conséquence.
     */
    @Override
    public void setPosition(int abscisse, int ordonnee) throws BloxException {
        int deltaX = abscisse - elements[0].getCoordonnees().getAbscisse();
        int deltaY = ordonnee - elements[0].getCoordonnees().getOrdonnee();
        deplacerDe(deltaX, deltaY);
    }

    /**
     * Déplace tous les éléments du tétromino d'un vecteur (deltaX, deltaY).
     * Pas de validation de limites ici ; c'est le rôle du Puits.
     */
    @Override
    public void deplacerDe(int deltaX, int deltaY) throws BloxException {
        for (Element e : elements) {
            e.getCoordonnees().setAbscisse(e.getCoordonnees().getAbscisse() + deltaX);
            e.getCoordonnees().setOrdonnee(e.getCoordonnees().getOrdonnee() + deltaY);
        }
    }

    // -------------------------------------------------------------------------
    // Utilitaire – génération aléatoire
    // -------------------------------------------------------------------------

    /**
     * Génère aléatoirement un tétromino positionné en haut au centre du puits.
     *
     * @param largeur largeur du puits (pour centrer la pièce)
     * @return un nouveau tétromino aléatoire
     */
    public static Tetromino genererTetromino(int largeur) {
        Coordonnees pos = new Coordonnees(largeur / 2, 0);
        Couleur[] couleurs = Couleur.values();
        Couleur couleur = couleurs[RANDOM.nextInt(couleurs.length)];

        int type = RANDOM.nextInt(2);
        if (type == 0) {
            return new OTetromino(pos, couleur);
        } else {
            return new ITetromino(pos, couleur);
        }
    }

    // -------------------------------------------------------------------------
    // Redéfinition Object
    // -------------------------------------------------------------------------

    /**
     * Format :
     * <pre>
     * NomClasse :
     *     (x1, y1) - COULEUR1
     *     (x2, y2) - COULEUR2
     *     ...
     * </pre>
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(getClass().getSimpleName() + " :\n");
        for (Element e : elements) {
            sb.append("\t").append(e.toString()).append("\n");
        }
        return sb.toString();
    }
}
