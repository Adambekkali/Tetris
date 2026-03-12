package fr.eseo.e3.poo.projet.blox.modele;

// ANTI-PLAGIAT : Code généré avec assistance IA (Claude, Anthropic) dans le cadre du projet pédagogique Falling Blox

import fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.OTetromino;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests JUnit 5 pour la classe {@link Puits}.
 */
class PuitsTest {

    private Puits puits;

    @BeforeEach
    void setUp() {
        puits = new Puits(10, 20);
    }

    // -------------------------------------------------------------------------
    // Constructeur & dimensions
    // -------------------------------------------------------------------------

    @Test
    void testDimensionsParDefaut() {
        Puits def = new Puits();
        assertEquals(Puits.LARGEUR_PAR_DEFAUT, def.getLargeur());
        assertEquals(Puits.HAUTEUR_PAR_DEFAUT, def.getHauteur());
    }

    @Test
    void testDimensionsPersonnalisees() {
        assertEquals(10, puits.getLargeur());
        assertEquals(20, puits.getHauteur());
    }

    @Test
    void testLargeurMinInvalide() {
        assertThrows(IllegalArgumentException.class,
            () -> new Puits(Puits.LARGEUR_MIN - 1, 20));
    }

    @Test
    void testLargeurMaxInvalide() {
        assertThrows(IllegalArgumentException.class,
            () -> new Puits(Puits.LARGEUR_MAX + 1, 20));
    }

    @Test
    void testHauteurMinInvalide() {
        assertThrows(IllegalArgumentException.class,
            () -> new Puits(10, Puits.HAUTEUR_MIN - 1));
    }

    @Test
    void testHauteurMaxInvalide() {
        assertThrows(IllegalArgumentException.class,
            () -> new Puits(10, Puits.HAUTEUR_MAX + 1));
    }

    @Test
    void testDimensionsLimitesBasses() {
        assertDoesNotThrow(() -> new Puits(Puits.LARGEUR_MIN, Puits.HAUTEUR_MIN));
    }

    @Test
    void testDimensionsLimitesHautes() {
        assertDoesNotThrow(() -> new Puits(Puits.LARGEUR_MAX, Puits.HAUTEUR_MAX));
    }

    // -------------------------------------------------------------------------
    // Pièce actuelle & suivante
    // -------------------------------------------------------------------------

    @Test
    void testPieceActuelleInitialementNull() {
        assertNull(puits.getPieceActuelle());
    }

    @Test
    void testPieceSuivanteInitialementNull() {
        assertNull(puits.getPieceSuivante());
    }

    @Test
    void testSetPieceActuellePositionneEnHaut() {
        OTetromino piece = new OTetromino(new Coordonnees(0, 0), Couleur.ROUGE);
        puits.setPieceActuelle(piece);
        assertNotNull(puits.getPieceActuelle());
        // La pièce doit être repositionnée en haut du puits (ordonnée 0)
        assertEquals(0, puits.getPieceActuelle().getElements()[0].getCoordonnees().getOrdonnee());
    }

    @Test
    void testSetPieceSuivante() {
        OTetromino piece = new OTetromino(new Coordonnees(0, 0), Couleur.BLEU);
        puits.setPieceSuivante(piece);
        assertNotNull(puits.getPieceSuivante());
    }

    // -------------------------------------------------------------------------
    // Tas
    // -------------------------------------------------------------------------

    @Test
    void testTasInitialementVide() {
        assertTrue(puits.getTas().getElements().isEmpty());
    }

    // -------------------------------------------------------------------------
    // Gravité
    // -------------------------------------------------------------------------

    @Test
    void testGraviteDescendLaPiece() throws BloxException {
        OTetromino piece = new OTetromino(new Coordonnees(5, 0), Couleur.VERT);
        puits.setPieceActuelle(piece);
        // Après setPieceActuelle, la pièce est en y=0
        int yAvant = puits.getPieceActuelle().getElements()[0].getCoordonnees().getOrdonnee();
        puits.gravite();
        int yApres = puits.getPieceActuelle().getElements()[0].getCoordonnees().getOrdonnee();
        assertEquals(yAvant + 1, yApres);
    }

    // -------------------------------------------------------------------------
    // toString
    // -------------------------------------------------------------------------

    @Test
    void testToStringContientDimensions() {
        String str = puits.toString();
        assertTrue(str.contains("10"));
        assertTrue(str.contains("20"));
    }

    // -------------------------------------------------------------------------
    // PropertyChangeListener
    // -------------------------------------------------------------------------

    @Test
    void testAddRemovePropertyChangeListener() {
        java.beans.PropertyChangeListener listener = evt -> {};
        assertDoesNotThrow(() -> {
            puits.addPropertyChangeListener(listener);
            puits.removePropertyChangeListener(listener);
        });
    }

    @Test
    void testPropertyChangeFiredOnSetPieceActuelle() {
        boolean[] fired = {false};
        puits.addPropertyChangeListener(Puits.PROP_PIECE_ACTUELLE, evt -> fired[0] = true);
        puits.setPieceActuelle(new OTetromino(new Coordonnees(5, 0), Couleur.ROUGE));
        assertTrue(fired[0], "propertyChange devrait être déclenché pour PROP_PIECE_ACTUELLE");
    }

    @Test
    void testPropertyChangeFiredOnSetPieceSuivante() {
        boolean[] fired = {false};
        puits.addPropertyChangeListener(Puits.PROP_PIECE_SUIVANTE, evt -> fired[0] = true);
        puits.setPieceSuivante(new OTetromino(new Coordonnees(5, 0), Couleur.BLEU));
        assertTrue(fired[0], "propertyChange devrait être déclenché pour PROP_PIECE_SUIVANTE");
    }
}
