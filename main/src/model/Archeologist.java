package model;

/**
 * Represents an Archeologist pawn in the game.
 */
public class Archeologist extends Pawn {

    /**
     * Constructs an Archeologist pawn for a specific player.
     *
     * @param player The player thst this archeologist belongs.
     */
    public Archeologist(Player player) {
        super(player);
    }

    /** <b>Observer</b>
     * <p>
     * <b>Postcondition:</b> Determines if this pawn is an archeologist.
     *
     * @return True always, because this class represents an archeologist.
     */
    public boolean isArcheologist() { return true; }

}
