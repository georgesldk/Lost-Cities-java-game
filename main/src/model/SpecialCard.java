package model;

/**
 * Represents a special card in the game.
 * Special cards belong to specific palaces and perform unique actions.
 */
public abstract class SpecialCard extends Card {

    /**
     * Constructs a special card associated with a specific palace.
     *
     * @param palace The palace associated with this card.
     */
    public SpecialCard(Palace palace) {
        super(palace);
    }

    /** <b>Observer</b>
     * <p>
     * <b>Postcondition:</b> Checks if this special card is a Minotaur card.
     *
     * @return True if the card is a Minotaur card, false otherwise.
     */
    public boolean isMinotaur() {
        return false;
    }

    /** <b>Observer</b>
     * <p>
     * <b>Postcondition:</b> Checks if this special card is an Ariadne card.
     *
     * @return True if the card is an Ariadne card, false otherwise.
     */
    public boolean isAriadne() {
        return false;
    }
}
