package fr.eseo.e3.poo.projet.blox.modele;

// ANTI-PLAGIAT : Code généré avec assistance IA (Claude, Anthropic) dans le cadre du projet pédagogique Falling Blox

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Représente le tas de blocs accumulés au fond du puits.
 * Lors de l'initialisation avec {@code construireTas}, des blocs sont
 * placés aléatoirement sur les dernières lignes du puits.
 */
public class Tas {

    private final List<Element> elements;
    private final Puits puits;

    private static final Random RANDOM = new Random();

    // -------------------------------------------------------------------------
    // Constructeurs
    // -------------------------------------------------------------------------

    /**
     * Crée un tas vide associé au puits donné.
     *
     * @param puits le puits qui contient ce tas
     */
    public Tas(Puits puits) {
        this.puits = puits;
        this.elements = new ArrayList<>();
    }

    /**
     * Crée un tas pré-rempli sur {@code nbLignes} lignes depuis le bas,
     * en remplissant {@code nbColonnes} colonnes depuis la gauche.
     *
     * @param puits      le puits contenant ce tas
     * @param nbLignes   nombre de lignes remplies (depuis le bas)
     * @param nbColonnes nombre de colonnes remplies (depuis la gauche)
     */
    public Tas(Puits puits, int nbLignes, int nbColonnes) {
        this(puits);
        construireTas(nbLignes, nbColonnes);
    }

    // -------------------------------------------------------------------------
    // Méthodes publiques
    // -------------------------------------------------------------------------

    /**
     * Retourne une vue non modifiable de la liste des éléments du tas.
     *
     * @return liste en lecture seule des éléments
     */
    public List<Element> getElements() {
        return Collections.unmodifiableList(elements);
    }

    /**
     * Ajoute au tas les éléments d'une pièce qui vient de se poser.
     * Crée de nouveaux objets {@link Element} (copie défensive).
     *
     * @param elementsAAjouter éléments de la pièce posée
     */
    public void ajouterElements(Element[] elementsAAjouter) {
        for (Element e : elementsAAjouter) {
            elements.add(new Element(
                new Coordonnees(e.getCoordonnees().getAbscisse(),
                                e.getCoordonnees().getOrdonnee()),
                e.getCouleur()
            ));
        }
    }

    /**
     * Vérifie si une case (x, y) est occupée par un élément du tas.
     *
     * @param abscisse colonne à tester
     * @param ordonnee ligne à tester
     * @return {@code true} si la case est occupée
     */
    public boolean contient(int abscisse, int ordonnee) {
        for (Element e : elements) {
            if (e.getCoordonnees().getAbscisse() == abscisse
                    && e.getCoordonnees().getOrdonnee() == ordonnee) {
                return true;
            }
        }
        return false;
    }

    // -------------------------------------------------------------------------
    // Méthode privée
    // -------------------------------------------------------------------------

    /**
     * Remplit le tas aléatoirement sur les {@code nbLignes} dernières lignes
     * du puits, en remplissant exactement {@code nbColonnes} colonnes depuis
     * la gauche sur chaque ligne.
     *
     * @param nbLignes   nombre de lignes à remplir (depuis le bas)
     * @param nbColonnes nombre de colonnes à remplir par ligne
     */
    private void construireTas(int nbLignes, int nbColonnes) {
        int hauteur  = puits.getHauteur();
        int largeur  = puits.getLargeur();
        Couleur[] couleurs = Couleur.values();

        int ligneDebut = hauteur - nbLignes;

        for (int ligne = ligneDebut; ligne < hauteur; ligne++) {
            for (int col = 0; col < Math.min(nbColonnes, largeur); col++) {
                Couleur couleur = couleurs[RANDOM.nextInt(couleurs.length)];
                elements.add(new Element(col, ligne, couleur));
            }
        }
    }
}
