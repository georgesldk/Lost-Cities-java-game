package model;

/**
 * Represents a numbered card in the game.
 * Numbered cards have a specific value and belong to a palace.
 */
public class NumberCard extends Card {

    private final int number;

    /**
     * Constructs a NumberCard with a specific palace and numerical value.
     *
     * @param palace The Palace instance associated with this card.
     * @param number The numerical value of the card.
     */
    public NumberCard(Palace palace, int number) {
        super(palace);
        this.number = number;
    }

    /** <b>Accessor</b>
     * <p>
     * <b>Postcondition:</b> Retrieves the numerical value of the card.
     *
     * @return The number of the card.
     */
    public int getNumber() {
        return number;
    }

    /** <b>Observer</b>
     * <p>
     * <b>Postcondition:</b> Compares this card with another card to determine playability.
     *
     * @param comparedCard The card to compare with.
     * @return A positive value if this card can be played over the compared card.
     */
    public int compareCards(Card comparedCard) {
        return this.number - ((NumberCard)comparedCard).getNumber();
    }

    @Override
    public String toString() {
        return "NumCard :"+ palace + "-"+number;
    }

    /**
     * <b>Accessor</b>
     * <p>
     * <b>Postcondition:</b> Retrieves the file path of the image.
     *
     * @return A string representing the image path.
     */
    @Override
    public String getImagePath() {
        if (this.palace.getName() == Palace.PalaceName.KNOSSOS) {
            return "/project_assets/images/cards/knossos"+number+".jpg";
        } else if (this.palace.getName() == Palace.PalaceName.MALIA) {
            return "/project_assets/images/cards/malia"+number+".jpg";
        } else if (this.palace.getName() == Palace.PalaceName.FAISTOS) {
            return "/project_assets/images/cards/phaistos"+number+".jpg";
        } else if (this.palace.getName() == Palace.PalaceName.ZAKROS) {
            return "/project_assets/images/cards/zakros"+number+".jpg";
        } else {
            return "Could not fetch image path";
        }
    }
}
