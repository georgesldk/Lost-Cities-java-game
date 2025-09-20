package model;

/**
 * Represents an Ariadne card in the game.
 * Ariadne cards are special and move a pawn two positions forward.
 */
public class Ariadne extends SpecialCard {

    /**
     * Constructs an Ariadne card that belongs to a specific palace.
     *
     * @param palace The palace associated with this card.
     */
    public Ariadne(Palace palace) {
        super(palace);
    }

    /** <b>Observer</b>
     * <p>
     * <b>Postcondition:</b> Determines if this SpecialCard is an Ariadne.
     *
     * @return True always, because this class represents an Ariadne.
     */
    public boolean isAriadne() { return true; }


    /**
     * <b>Accessor</b>
     * <p>
     * <b>Postcondition:</b> Returns a string representation of the AriadneCard.
     *
     * @return A string of the  Ariadne Card
     */
    @Override
    public String toString() {
        return "AriCard :"+ palace;
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
            return "/project_assets/images/cards/knossosAri.jpg";
        } else if (this.palace.getName() == Palace.PalaceName.MALIA) {
            return "/project_assets/images/cards/maliaAri.jpg";
        } else if (this.palace.getName() == Palace.PalaceName.FAISTOS) {
            return "/project_assets/images/cards/phaistosAri.jpg";
        } else if (this.palace.getName() == Palace.PalaceName.ZAKROS) {
            return "/project_assets/images/cards/zakrosAri.jpg";
        } else {
            return "Could not fetch image path";
        }
    }

}
