package fr.eseo.e3.poo.projet.blox.modele;

// ANTI-PLAGIAT : Code généré avec assistance IA (Claude, Anthropic) dans le cadre du projet pédagogique Falling Blox

import fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.ITetromino;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests JUnit 5 pour la classe {@link ITetromino}.
 */
class ITetrominoTest {

    private ITetromino piece;

    @BeforeEach
    void setUp() {
        // Référence en (3, 5) → horizontal : [2,5] [3,5] [4,5] [5,5]
        piece = new ITetromino(new Coordonnees(3, 5), Couleur.CYAN);
    }

    // -------------------------------------------------------------------------
    // Forme initiale
    // -------------------------------------------------------------------------

    @Test
    void testNbElements() {
        assertEquals(4, piece.getElements().length);
    }

    @Test
    void testElementReference() {
        assertEquals(3, piece.getElements()[0].getCoordonnees().getAbscisse());
        assertEquals(5, piece.getElements()[0].getCoordonnees().getOrdonnee());
    }

    @Test
    void testFormeHorizontaleInitiale() {
        Element[] e = piece.getElements();
        // [0] référence (3,5)
        assertEquals(new Coordonnees(3,  5), e[0].getCoordonnees());
        // [1] gauche du référent (2,5)
        assertEquals(new Coordonnees(2,  5), e[1].getCoordonnees());
        // [2] droite du référent (4,5)
        assertEquals(new Coordonnees(4,  5), e[2].getCoordonnees());
        // [3] 2 à droite du référent (5,5)
        assertEquals(new Coordonnees(5,  5), e[3].getCoordonnees());
    }

    @Test
    void testCouleurParDefaut() {
        ITetromino def = new ITetromino(new Coordonnees(0, 0));
        for (Element e : def.getElements()) {
            assertEquals(Couleur.CYAN, e.getCouleur());
        }
    }

    // -------------------------------------------------------------------------
    // Rotation anti-horaire (sensHoraire = false)
    // -------------------------------------------------------------------------

    @Test
    void testRotationAntiHoraireReferencePasBouge() throws Exception {
        piece.tourner(false);
        assertEquals(3, piece.getElements()[0].getCoordonnees().getAbscisse());
        assertEquals(5, piece.getElements()[0].getCoordonnees().getOrdonnee());
    }

    @Test
    void testRotationAntiHorairePasseEnVertical() throws Exception {
        piece.tourner(false);
        Element[] e = piece.getElements();
        // Après rotation anti-horaire depuis horizontal :
        // (x,y)->(y,-x) en relatif : (-1,0)->(0,1) ; (1,0)->(0,-1) ; (2,0)->(0,-2)
        assertEquals(new Coordonnees(3, 6), e[1].getCoordonnees()); // était (2,5) → rel (-1,0) → (0,1)
        assertEquals(new Coordonnees(3, 4), e[2].getCoordonnees()); // était (4,5) → rel (1,0) → (0,-1)
        assertEquals(new Coordonnees(3, 3), e[3].getCoordonnees()); // était (5,5) → rel (2,0) → (0,-2)
    }

    @Test
    void testQuatreRotationsAntiHoraireRetourneFormeInitiale() throws Exception {
        int[][] initial = snapshot(piece);
        for (int i = 0; i < 4; i++) {
            piece.tourner(false);
        }
        assertArrayEquals(initial, snapshot(piece),
            "4 rotations anti-horaire doivent ramener la forme initiale");
    }

    // -------------------------------------------------------------------------
    // Rotation horaire (sensHoraire = true)
    // -------------------------------------------------------------------------

    @Test
    void testRotationHoraireReferencePasBouge() throws Exception {
        piece.tourner(true);
        assertEquals(3, piece.getElements()[0].getCoordonnees().getAbscisse());
        assertEquals(5, piece.getElements()[0].getCoordonnees().getOrdonnee());
    }

    @Test
    void testRotationHorairePasseEnVertical() throws Exception {
        piece.tourner(true);
        Element[] e = piece.getElements();
        // Après rotation horaire : (-y, x) → (-1,0)→(0,-1) ; (1,0)→(0,1) ; (2,0)→(0,2)
        assertEquals(new Coordonnees(3, 4), e[1].getCoordonnees());
        assertEquals(new Coordonnees(3, 6), e[2].getCoordonnees());
        assertEquals(new Coordonnees(3, 7), e[3].getCoordonnees());
    }

    @Test
    void testQuatreRotationsHoraireRetourneFormeInitiale() throws Exception {
        int[][] initial = snapshot(piece);
        for (int i = 0; i < 4; i++) {
            piece.tourner(true);
        }
        assertArrayEquals(initial, snapshot(piece),
            "4 rotations horaires doivent ramener la forme initiale");
    }

    // -------------------------------------------------------------------------
    // toString
    // -------------------------------------------------------------------------

    @Test
    void testToStringContientNomClasse() {
        assertTrue(piece.toString().contains("ITetromino"));
    }

    // -------------------------------------------------------------------------
    // Utilitaire
    // -------------------------------------------------------------------------

    private int[][] snapshot(ITetromino p) {
        int[][] coords = new int[4][2];
        for (int i = 0; i < 4; i++) {
            coords[i][0] = p.getElements()[i].getCoordonnees().getAbscisse();
            coords[i][1] = p.getElements()[i].getCoordonnees().getOrdonnee();
        }
        return coords;
    }
}
