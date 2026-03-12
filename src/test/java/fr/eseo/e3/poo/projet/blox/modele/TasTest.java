package fr.eseo.e3.poo.projet.blox.modele;

// ANTI-PLAGIAT : Code généré avec assistance IA (Claude, Anthropic) dans le cadre du projet pédagogique Falling Blox

import fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.OTetromino;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests JUnit 5 pour la classe {@link Tas}.
 */
class TasTest {

    private Puits puits;
    private Tas   tas;

    @BeforeEach
    void setUp() {
        puits = new Puits(10, 20);
        tas   = puits.getTas();
    }

    // -------------------------------------------------------------------------
    // Tas vide
    // -------------------------------------------------------------------------

    @Test
    void testTasVideInitialement() {
        assertTrue(tas.getElements().isEmpty());
    }

    @Test
    void testContientRetourneFalseSurTasVide() {
        assertFalse(tas.contient(0, 0));
    }

    // -------------------------------------------------------------------------
    // ajouterElements
    // -------------------------------------------------------------------------

    @Test
    void testAjouterElementsIncrementeLeNombre() {
        OTetromino piece = new OTetromino(new Coordonnees(5, 18), Couleur.ROUGE);
        tas.ajouterElements(piece.getElements());
        assertEquals(4, tas.getElements().size());
    }

    @Test
    void testContientApresAjout() {
        OTetromino piece = new OTetromino(new Coordonnees(5, 18), Couleur.ROUGE);
        tas.ajouterElements(piece.getElements());
        assertTrue(tas.contient(5, 18));
        assertTrue(tas.contient(6, 18));
        assertTrue(tas.contient(5, 19));
        assertTrue(tas.contient(6, 19));
    }

    @Test
    void testAjouterFaitUneCopieDEfensive() {
        OTetromino piece = new OTetromino(new Coordonnees(5, 18), Couleur.ROUGE);
        Element[] elementsOrigine = piece.getElements();
        tas.ajouterElements(elementsOrigine);
        // Modifier les coordonnées originales ne doit pas affecter le tas
        elementsOrigine[0].getCoordonnees().setAbscisse(0);
        elementsOrigine[0].getCoordonnees().setOrdonnee(0);
        assertTrue(tas.contient(5, 18),
            "Le tas doit être indépendant des éléments source (copie défensive)");
    }

    // -------------------------------------------------------------------------
    // construireTas (constructeur avec nbLignes, nbColonnes)
    // -------------------------------------------------------------------------

    @Test
    void testConstruireTasNombreElements() {
        Tas tasPrerempli = new Tas(puits, 3, 5);
        // 3 lignes × 5 colonnes = 15 éléments
        assertEquals(15, tasPrerempli.getElements().size());
    }

    @Test
    void testConstruireTasElementsDansLesPuits() {
        Tas tasPrerempli = new Tas(puits, 2, puits.getLargeur());
        for (Element e : tasPrerempli.getElements()) {
            int x = e.getCoordonnees().getAbscisse();
            int y = e.getCoordonnees().getOrdonnee();
            assertTrue(x >= 0 && x < puits.getLargeur(), "x hors limites : " + x);
            assertTrue(y >= 0 && y < puits.getHauteur(), "y hors limites : " + y);
        }
    }

    @Test
    void testConstruireTasLignesDuBas() {
        int nbLignes = 3;
        Tas tasPrerempli = new Tas(puits, nbLignes, 1);
        for (Element e : tasPrerempli.getElements()) {
            int y = e.getCoordonnees().getOrdonnee();
            assertTrue(y >= puits.getHauteur() - nbLignes,
                "Les éléments doivent être dans les " + nbLignes + " dernières lignes");
        }
    }

    // -------------------------------------------------------------------------
    // Immuabilité de la liste retournée
    // -------------------------------------------------------------------------

    @Test
    void testGetElementsRetourneVueNonModifiable() {
        assertThrows(UnsupportedOperationException.class,
            () -> tas.getElements().add(new Element(0, 0, Couleur.ROUGE)));
    }
}
