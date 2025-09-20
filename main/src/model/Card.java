package model;

/**
 * Represents a card in the game.
 * Cards belong to specific palaces.
 */
public abstract class Card {

    protected Palace palace;

    /**
     * Constructs a card associated with a specific palace.
     *
     * @param palace The palace associated with this card.
     */
    public Card(Palace palace) {
        this.palace = palace;
    }

    /** <b>Accessor</b>
     * <p>
     * <b>Postcondition:</b> Retrieves the palace associated with this card.
     *
     * @return The palace associated with this card.
     */
    public Palace getPalace() {
        return palace;
    }


    /**
     * <b>Accessor</b>
     * <p>
     * <b>Postcondition:</b> Retrieves the file path of the image.
     *
     * @return A string representing the image path.
     */
    public String getImagePath() {
        return null;
    }
}
