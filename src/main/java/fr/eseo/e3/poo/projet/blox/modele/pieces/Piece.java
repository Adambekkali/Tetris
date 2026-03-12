package fr.eseo.e3.poo.projet.blox.modele.pieces;

// ANTI-PLAGIAT : Code généré avec assistance IA (Claude, Anthropic) dans le cadre du projet pédagogique Falling Blox

import fr.eseo.e3.poo.projet.blox.modele.BloxException;
import fr.eseo.e3.poo.projet.blox.modele.Element;

/**
 * Contrat commun à toutes les pièces du jeu.
 * Une pièce est composée de 4 éléments (tétromino).
 */
public interface Piece {

    /**
     * Positionne la pièce de façon que son élément de référence (index 0)
     * se trouve aux coordonnées données.
     *
     * @param abscisse nouvelle abscisse de l'élément de référence
     * @param ordonnee nouvelle ordonnée de l'élément de référence
     * @throws BloxException si la position est invalide
     */
    void setPosition(int abscisse, int ordonnee) throws BloxException;

    /**
     * Retourne le tableau des 4 éléments constituant la pièce.
     * L'élément d'index 0 est l'élément de référence (pivot).
     *
     * @return tableau de 4 {@link Element}
     */
    Element[] getElements();

    /**
     * Déplace la pièce d'un vecteur (deltaX, deltaY).
     *
     * @param deltaX déplacement horizontal
     * @param deltaY déplacement vertical
     * @throws BloxException si le déplacement amène la pièce hors du puits
     *                       ou en collision
     */
    void deplacerDe(int deltaX, int deltaY) throws BloxException;

    /**
     * Fait tourner la pièce autour de son élément de référence.
     *
     * @param sensHoraire {@code true} pour une rotation horaire,
     *                    {@code false} pour anti-horaire
     * @throws BloxException si la rotation est invalide
     */
    void tourner(boolean sensHoraire) throws BloxException;
}
