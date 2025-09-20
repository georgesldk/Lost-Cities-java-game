package model;

/**
 * Represents a Minotaur card in the game.
 * Minotaur cards belong to a specific palace and can move opponents' pawns backward.
 */
public class Minotaur extends SpecialCard {

    /**
     * Constructs a Minotaur card associated with a specific palace.
     *
     * @param palace The palace associated with this card.
     */
    public Minotaur(Palace palace) {
        super(palace);
    }

    /** <b>Observer</b>
     * <p>
     * <b>Postcondition:</b> Determines if this SpecialCard is a Minotaur.
     *
     * @return True always, because this class represents a Minotaur.
     */
    public boolean isMinotaur() { return true; }

    /**
     * <b>Accessor</b>
     * <p>
     * <b>Postcondition:</b> Returns a string representation of the MinotaurCrd.
     *
     * @return A string of the  Ariadne Card
     */
    @Override
    public String toString() {
        return "MinCard :"+ palace;
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
            return "/project_assets/images/cards/knossosMin.jpg";
        } else if (this.palace.getName() == Palace.PalaceName.MALIA) {
            return "/project_assets/images/cards/maliaMin.jpg";
        } else if (this.palace.getName() == Palace.PalaceName.FAISTOS) {
            return "/project_assets/images/cards/phaistosMin.jpg";
        } else if (this.palace.getName() == Palace.PalaceName.ZAKROS) {
            return "/project_assets/images/cards/zakrosMin.jpg";
        } else {
            return "Could not fetch image path";
        }
    }
}
