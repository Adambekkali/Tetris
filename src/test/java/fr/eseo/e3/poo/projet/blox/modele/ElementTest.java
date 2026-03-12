package fr.eseo.e3.poo.projet.blox.modele;

// ANTI-PLAGIAT : Code généré avec assistance IA (Claude, Anthropic) dans le cadre du projet pédagogique Falling Blox

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests JUnit 5 pour la classe {@link Element}.
 */
class ElementTest {

    private Element element;

    @BeforeEach
    void setUp() {
        element = new Element(new Coordonnees(5, 10), Couleur.ROUGE);
    }

    // -------------------------------------------------------------------------
    // Constructeur avec Coordonnees
    // -------------------------------------------------------------------------

    @Test
    void testConstructeurAvecCoordonnees() {
        assertEquals(5,  element.getCoordonnees().getAbscisse());
        assertEquals(10, element.getCoordonnees().getOrdonnee());
        assertEquals(Couleur.ROUGE, element.getCouleur());
    }

    // -------------------------------------------------------------------------
    // Constructeur de commodité (int, int, Couleur)
    // -------------------------------------------------------------------------

    @Test
    void testConstructeurDeCommode() {
        Element e = new Element(3, 8, Couleur.BLEU);
        assertEquals(3,          e.getCoordonnees().getAbscisse());
        assertEquals(8,          e.getCoordonnees().getOrdonnee());
        assertEquals(Couleur.BLEU, e.getCouleur());
    }

    // -------------------------------------------------------------------------
    // Accesseurs / mutateurs
    // -------------------------------------------------------------------------

    @Test
    void testSetCouleur() {
        element.setCouleur(Couleur.VERT);
        assertEquals(Couleur.VERT, element.getCouleur());
    }

    @Test
    void testSetCoordonnees() {
        Coordonnees nouvelles = new Coordonnees(1, 2);
        element.setCoordonnees(nouvelles);
        assertEquals(nouvelles, element.getCoordonnees());
    }

    // -------------------------------------------------------------------------
    // toString
    // -------------------------------------------------------------------------

    @Test
    void testToStringFormat() {
        // Format attendu : "(x, y) - COULEUR"
        assertEquals("(5, 10) - ROUGE", element.toString());
    }

    @Test
    void testToStringToutesCouleurs() {
        for (Couleur c : Couleur.values()) {
            Element e = new Element(0, 0, c);
            assertTrue(e.toString().endsWith("- " + c.name()),
                "toString devrait se terminer par '- " + c.name() + "'");
        }
    }

    // -------------------------------------------------------------------------
    // getCouleurPourAffichage via Couleur
    // -------------------------------------------------------------------------

    @Test
    void testCouleurPourAffichageNonNull() {
        assertNotNull(element.getCouleur().getCouleurPourAffichage());
    }

    @Test
    void testRougeEstCouleurRed() {
        assertEquals(java.awt.Color.RED, Couleur.ROUGE.getCouleurPourAffichage());
    }
}
