package fr.eseo.e3.poo.projet.blox.modele;

// ANTI-PLAGIAT : Code généré avec assistance IA (Claude, Anthropic) dans le cadre du projet pédagogique Falling Blox

import fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.OTetromino;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests JUnit 5 pour la classe {@link OTetromino}.
 */
class OTetrominoTest {

    private OTetromino piece;

    @BeforeEach
    void setUp() {
        piece = new OTetromino(new Coordonnees(3, 2), Couleur.JAUNE);
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
        // Élément 0 = référence = position donnée au constructeur
        assertEquals(3, piece.getElements()[0].getCoordonnees().getAbscisse());
        assertEquals(2, piece.getElements()[0].getCoordonnees().getOrdonnee());
    }

    @Test
    void testFormeCarree() {
        Element[] e = piece.getElements();
        // [0] haut gauche  (3,2)
        assertEquals(new Coordonnees(3, 2), e[0].getCoordonnees());
        // [1] haut droite  (4,2)
        assertEquals(new Coordonnees(4, 2), e[1].getCoordonnees());
        // [2] bas gauche   (3,3)
        assertEquals(new Coordonnees(3, 3), e[2].getCoordonnees());
        // [3] bas droite   (4,3)
        assertEquals(new Coordonnees(4, 3), e[3].getCoordonnees());
    }

    @Test
    void testCouleurParDefaut() {
        OTetromino def = new OTetromino(new Coordonnees(0, 0));
        for (Element e : def.getElements()) {
            assertEquals(Couleur.JAUNE, e.getCouleur());
        }
    }

    // -------------------------------------------------------------------------
    // Rotation (invariante)
    // -------------------------------------------------------------------------

    @Test
    void testRotationHoraireNeffacePasLaForme() throws Exception {
        int[][] avant = snapshot(piece);
        piece.tourner(true);
        int[][] apres = snapshot(piece);
        assertArrayEquals(avant, apres,
            "OTetromino ne doit pas bouger après une rotation horaire");
    }

    @Test
    void testRotationAntiHoraireNeffacePasLaForme() throws Exception {
        int[][] avant = snapshot(piece);
        piece.tourner(false);
        int[][] apres = snapshot(piece);
        assertArrayEquals(avant, apres,
            "OTetromino ne doit pas bouger après une rotation anti-horaire");
    }

    // -------------------------------------------------------------------------
    // déplacerDe
    // -------------------------------------------------------------------------

    @Test
    void testDeplacerDe() throws Exception {
        piece.deplacerDe(2, 3);
        assertEquals(5, piece.getElements()[0].getCoordonnees().getAbscisse());
        assertEquals(5, piece.getElements()[0].getCoordonnees().getOrdonnee());
        assertEquals(6, piece.getElements()[1].getCoordonnees().getAbscisse());
        assertEquals(6, piece.getElements()[3].getCoordonnees().getOrdonnee());
    }

    // -------------------------------------------------------------------------
    // toString
    // -------------------------------------------------------------------------

    @Test
    void testToStringContientNomClasse() {
        assertTrue(piece.toString().contains("OTetromino"));
    }

    // -------------------------------------------------------------------------
    // Utilitaire
    // -------------------------------------------------------------------------

    private int[][] snapshot(OTetromino p) {
        int[][] coords = new int[4][2];
        for (int i = 0; i < 4; i++) {
            coords[i][0] = p.getElements()[i].getCoordonnees().getAbscisse();
            coords[i][1] = p.getElements()[i].getCoordonnees().getOrdonnee();
        }
        return coords;
    }
}
