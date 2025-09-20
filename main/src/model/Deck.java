package model;

import java.util.ArrayList;

/**
 * Represents the deck of cards used in the game.
 * The deck contains all cards and supports operations such as drawing and shuffling.
 */
public class Deck {
    private final ArrayList<Card> deck;

    /**
     * Constructs a new deck, initializes it with cards, and shuffles it.
     */
    public Deck(Palace[] palaces) {
        this.deck = new ArrayList<>();
        fill(palaces);
        shuffle();
    }

    /**
     * <b>Accessor</b>
     * <p>
     * <b>Postcondition:</b> Returns the cards in the deck.
     *
     * @return int amount of cards in the deck.
     */
    public int getDeck() {
        return deck.size();
    }

    /** <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Fills the deck with the required cards for each palace.
     */
    public void fill(Palace[] palaces) {
        for (Palace palace : palaces)  {

            for (int i=1; i<=10; i++) {

                NumberCard card1 = new NumberCard(palace, i);
                NumberCard card2 = new NumberCard(palace, i);

                deck.add(card1);
                deck.add(card2);
            }
            for (int i=1; i<=3; i++) {

                Ariadne card = new Ariadne(palace);
                deck.add(card);
            }
            for (int i=1; i<=2; i++ ) {

                Minotaur card = new Minotaur(palace);
                deck.add(card);

            }
        }
        //System.out.println("Deck filled.");
    }

    /** <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Shuffles the deck to randomize the card order.
     */
    public void shuffle() {
        java.util.Collections.shuffle(deck);
        //System.out.println("Deck shuffled.");
    }

    /** <b>Observer</b>
     * <p>
     * <b>Postcondition:</b> Checks if the deck is empty.
     *
     * @return True if the deck is empty; false otherwise.
     */
    public boolean isEmpty() {
        return deck.isEmpty();
    }

    /** <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Allows a player to draw a card from the deck.
     *
     * @return The drawn card, or null if the deck is empty.
     */
    public Card playerDrawsCard() {
        if (isEmpty()) {
            return null;
        }
        return deck.remove(deck.size() - 1);
    }
}
