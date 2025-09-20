package model;

public class Theseus extends Pawn {

    private int destroy = 0;  // track how many times Theseus has destroyed a finding

    public Theseus(Player player) {
        super(player);
    }

    /** <b>Accessor</b>
     * @return The doubled point value of the current position.
     */
    @Override
    public int getCurrentPositionPoints() {

        if (getThisPawnPosition() != null) {
            return getThisPawnPosition().getPoints() * 2;
        }
        return 0;
    }

    /** <b>Observer</b>
     * @return True always, as this is a Theseus pawn.
     */
    public boolean isTheseus() {
        return true;
    }


    /**
     * <b>Accessor</b>
     * <p>
     * <b>Postcondition:</b> Gets the DEstory count of Theseus
     *
     * @return Int representing the destroy count.
     */
    public int getDestroy() {
        return destroy;
    }

    /**
     * <b>Transformer</b>
     * <p>
     * <b>Postcondition:</b> Increments the destroy count of Theseus
     *
     */
    public void addDestroyed() {
        destroy++;
    }


    /**
     * <b>Observer</b>
     * <p>
     * <b>Postcondition:</b> Checks if Theseus can still use Hakai (destroy)
     *
     * @return True if he can else false
     */
    public boolean hasDestroyAbility() {
        return destroy<3;
    }
}
