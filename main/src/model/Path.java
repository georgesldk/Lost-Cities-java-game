package model;

import java.util.ArrayList;

/**
 * Represents a path on the game board.
 * Paths are associated with palaces and contain positions for pawns to traverse.
 */
public class Path {

    private final Palace palace;
    private final Position[] path = new Position[9];
    private ArrayList<Pawn> pawns;
    //never used
    //i have a mathod in controller that does the equivalent
    private int[] CurrentPlayersOnPath = {0, 0};

    /**
     * Constructs a Path associated with a specific palace.
     *
     * @param palace The palace associated with this path.
     */
    public Path(Palace palace) {
        this.palace = palace;
        this.pawns = new ArrayList<>();
    }

    /** <b>Accessor</b>
     * <p>
     * <b>Postcondition:</b> Retrieves the palace associated with this path.
     *
     * @return The palace associated with this path.
     */
    public Palace getPalace() { return palace; }

    /** <b>Accessor</b>
     * <p>
     * <b>Postcondition:</b> Retrieves the array of positions on this path.
     *
     * @return An array of positions on this path.
     */
    public Position[] getPath() { return path; }

    public void setPath(Position[] positions) {
        //set path points and findings, board uses this in initialize paths method
        System.arraycopy(positions, 0, path, 0, positions.length);
    }

    /** <b>Accessor</b>
     * <p>
     * <b>Postcondition:</b> Retrieves the list of pawns on this path.
     *
     * @return A list of pawns currently on this path.
     */
    public ArrayList<Pawn> getPawns() { return pawns; }

    /** <b>Observer</b>
     * <p>
     * <b>Postcondition:</b> Retrieves the number of players currently on this path.
     *
     * @return The number of players currently on this path.
     */
    public int getCurrentPlayersOnPath() {
        return 0;
        //never used too
    }

    /** <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Adds a pawn to this path.
     *
     * @param pawn The pawn to be added to the path.
     * @param player The player who owns the pawn.
     */
    public void addPawn(Pawn pawn, Player player) {
        //never used too
    }
}
