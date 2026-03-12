package fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos;

// ANTI-PLAGIAT : Code généré avec assistance IA (Claude, Anthropic) dans le cadre du projet pédagogique Falling Blox

import fr.eseo.e3.poo.projet.blox.modele.BloxException;
import fr.eseo.e3.poo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.poo.projet.blox.modele.Couleur;
import fr.eseo.e3.poo.projet.blox.modele.Element;
import fr.eseo.e3.poo.projet.blox.modele.pieces.Tetromino;

/**
 * Tétromino en forme de carré (pièce O).
 *
 * <pre>
 *  [0][1]
 *  [2][3]
 * </pre>
 *
 * L'élément de référence (index 0) est en haut à gauche.
 * Ce tétromino ne tourne pas (symétrie parfaite).
 */
public class OTetromino extends Tetromino {

    /** Couleur utilisée par défaut si aucune n'est précisée. */
    public static final Couleur COULEUR_PAR_DEFAUT = Couleur.JAUNE;

    /**
     * Construit un OTetromino avec une couleur personnalisée.
     *
     * @param coordonnees position de l'élément de référence (index 0)
     * @param couleur     couleur des 4 éléments
     */
    public OTetromino(Coordonnees coordonnees, Couleur couleur) {
        super(new Element[]{
            // index 0 – élément de référence (haut gauche)
            new Element(new Coordonnees(coordonnees.getAbscisse(),     coordonnees.getOrdonnee()),     couleur),
            // index 1 – haut droite
            new Element(new Coordonnees(coordonnees.getAbscisse() + 1, coordonnees.getOrdonnee()),     couleur),
            // index 2 – bas gauche
            new Element(new Coordonnees(coordonnees.getAbscisse(),     coordonnees.getOrdonnee() + 1), couleur),
            // index 3 – bas droite
            new Element(new Coordonnees(coordonnees.getAbscisse() + 1, coordonnees.getOrdonnee() + 1), couleur)
        });
    }

    /**
     * Construit un OTetromino avec la couleur par défaut ({@link #COULEUR_PAR_DEFAUT}).
     *
     * @param coordonnees position de l'élément de référence
     */
    public OTetromino(Coordonnees coordonnees) {
        this(coordonnees, COULEUR_PAR_DEFAUT);
    }

    /**
     * La pièce O est invariante par rotation : cette méthode ne fait rien.
     *
     * @param sensHoraire ignoré
     * @throws BloxException jamais levée ici
     */
    @Override
    public void tourner(boolean sensHoraire) throws BloxException {
        // La pièce O (carré) est symétrique : aucune rotation nécessaire.
    }
}
