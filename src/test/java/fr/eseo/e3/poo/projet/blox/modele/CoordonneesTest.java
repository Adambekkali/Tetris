package fr.eseo.e3.poo.projet.blox.modele;

// ANTI-PLAGIAT : Code généré avec assistance IA (Claude, Anthropic) dans le cadre du projet pédagogique Falling Blox

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests JUnit 5 pour la classe {@link Coordonnees}.
 */
class CoordonneesTest {

    private Coordonnees c1;
    private Coordonnees c2;

    @BeforeEach
    void setUp() {
        c1 = new Coordonnees(3, 7);
        c2 = new Coordonnees(3, 7);
    }

    // -------------------------------------------------------------------------
    // Constructeur & accesseurs
    // -------------------------------------------------------------------------

    @Test
    void testGetAbscisse() {
        assertEquals(3, c1.getAbscisse());
    }

    @Test
    void testGetOrdonnee() {
        assertEquals(7, c1.getOrdonnee());
    }

    // -------------------------------------------------------------------------
    // Mutateurs
    // -------------------------------------------------------------------------

    @Test
    void testSetAbscisse() {
        c1.setAbscisse(10);
        assertEquals(10, c1.getAbscisse());
    }

    @Test
    void testSetOrdonnee() {
        c1.setOrdonnee(15);
        assertEquals(15, c1.getOrdonnee());
    }

    // -------------------------------------------------------------------------
    // toString
    // -------------------------------------------------------------------------

    @Test
    void testToStringFormat() {
        // Format attendu : "(x, y)" avec une espace après la virgule
        assertEquals("(3, 7)", c1.toString());
    }

    @Test
    void testToStringZero() {
        assertEquals("(0, 0)", new Coordonnees(0, 0).toString());
    }

    @Test
    void testToStringNegative() {
        assertEquals("(-1, -5)", new Coordonnees(-1, -5).toString());
    }

    // -------------------------------------------------------------------------
    // equals
    // -------------------------------------------------------------------------

    @Test
    void testEqualsSymmetric() {
        assertEquals(c1, c2);
        assertEquals(c2, c1);
    }

    @Test
    void testEqualsReflexive() {
        assertEquals(c1, c1);
    }

    @Test
    void testEqualsNull() {
        assertNotEquals(null, c1);
    }

    @Test
    void testEqualsDifferentType() {
        assertNotEquals("(3, 7)", c1);
    }

    @Test
    void testNotEqualsDifferentAbscisse() {
        assertNotEquals(c1, new Coordonnees(4, 7));
    }

    @Test
    void testNotEqualsDifferentOrdonnee() {
        assertNotEquals(c1, new Coordonnees(3, 8));
    }

    // -------------------------------------------------------------------------
    // hashCode
    // -------------------------------------------------------------------------

    @Test
    void testHashCodeConsistency() {
        assertEquals(c1.hashCode(), c1.hashCode());
    }

    @Test
    void testHashCodeEqualObjects() {
        assertEquals(c1.hashCode(), c2.hashCode());
    }
}
