package fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos;

// ANTI-PLAGIAT : Code généré avec assistance IA (Claude, Anthropic) dans le cadre du projet pédagogique Falling Blox

import fr.eseo.e3.poo.projet.blox.modele.BloxException;
import fr.eseo.e3.poo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.poo.projet.blox.modele.Couleur;
import fr.eseo.e3.poo.projet.blox.modele.Element;
import fr.eseo.e3.poo.projet.blox.modele.pieces.Tetromino;

/**
 * Tétromino en forme de barre (pièce I).
 *
 * <p>Position horizontale initiale :</p>
 * <pre>
 *  [1][0][2][3]
 * </pre>
 *
 * <p>L'élément de référence (index 0) est le 2e élément depuis la gauche.</p>
 *
 * <p>La rotation s'effectue en 3 étapes :</p>
 * <ol>
 *   <li>Translation à l'origine (soustraction des coords de référence).</li>
 *   <li>Application de la matrice de rotation 2D (coordonnées-écran, Y vers le bas).</li>
 *   <li>Translation inverse (ajout des coords de référence).</li>
 * </ol>
 *
 * <p>En coordonnées-écran (Y croissant vers le bas) :</p>
 * <ul>
 *   <li>Rotation horaire    (sensHoraire=true)  : {@code (x, y) -> (-y, x)}</li>
 *   <li>Rotation anti-horaire (sensHoraire=false): {@code (x, y) -> (y, -x)}</li>
 * </ul>
 */
public class ITetromino extends Tetromino {

    /** Couleur utilisée par défaut si aucune n'est précisée. */
    public static final Couleur COULEUR_PAR_DEFAUT = Couleur.CYAN;

    /**
     * Construit un ITetromino avec une couleur personnalisée.
     *
     * @param coordonnees position de l'élément de référence (index 0)
     * @param couleur     couleur des 4 éléments
     */
    public ITetromino(Coordonnees coordonnees, Couleur couleur) {
        super(new Element[]{
            // index 0 – élément de référence (2e depuis la gauche)
            new Element(new Coordonnees(coordonnees.getAbscisse(),     coordonnees.getOrdonnee()), couleur),
            // index 1 – à gauche du référent
            new Element(new Coordonnees(coordonnees.getAbscisse() - 1, coordonnees.getOrdonnee()), couleur),
            // index 2 – à droite du référent
            new Element(new Coordonnees(coordonnees.getAbscisse() + 1, coordonnees.getOrdonnee()), couleur),
            // index 3 – 2 cases à droite du référent
            new Element(new Coordonnees(coordonnees.getAbscisse() + 2, coordonnees.getOrdonnee()), couleur)
        });
    }

    /**
     * Construit un ITetromino avec la couleur par défaut ({@link #COULEUR_PAR_DEFAUT}).
     *
     * @param coordonnees position de l'élément de référence
     */
    public ITetromino(Coordonnees coordonnees) {
        this(coordonnees, COULEUR_PAR_DEFAUT);
    }

    /**
     * Fait pivoter la pièce de 90° autour de son élément de référence.
     *
     * <p>Algorithme :</p>
     * <ol>
     *   <li>Pour chaque élément (sauf référence), calcule la position relative.</li>
     *   <li>Applique la rotation 2D en coordonnées-écran.</li>
     *   <li>Recalcule la position absolue.</li>
     * </ol>
     *
     * @param sensHoraire {@code true} = rotation horaire, {@code false} = anti-horaire
     * @throws BloxException jamais levée directement ici
     */
    @Override
    public void tourner(boolean sensHoraire) throws BloxException {
        int refX = elements[0].getCoordonnees().getAbscisse();
        int refY = elements[0].getCoordonnees().getOrdonnee();

        for (int i = 1; i < elements.length; i++) {
            // Étape 1 : translation à l'origine
            int relX = elements[i].getCoordonnees().getAbscisse() - refX;
            int relY = elements[i].getCoordonnees().getOrdonnee() - refY;

            int newRelX;
            int newRelY;

            if (sensHoraire) {
                // Rotation horaire en coords-écran : (x, y) -> (-y, x)
                newRelX = -relY;
                newRelY =  relX;
            } else {
                // Rotation anti-horaire en coords-écran : (x, y) -> (y, -x)
                newRelX =  relY;
                newRelY = -relX;
            }

            // Étape 3 : translation inverse
            elements[i].getCoordonnees().setAbscisse(refX + newRelX);
            elements[i].getCoordonnees().setOrdonnee(refY + newRelY);
        }
    }
}
