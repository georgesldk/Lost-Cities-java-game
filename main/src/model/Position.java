package model;

/**
 * Represents a position on a path in the game.
 * Positions have points and an index, and they belong to a specific path.
 */
public abstract class Position {

    private final Path path;
    private final int index;
    private final int points;

    /**
     * Constructs a position on a specific path.
     *
     * @param path The path this position belongs to.
     * @param index The index of this position on the path.
     * @param points The points associated with this position.
     */
    public Position(Path path, int index, int points) {
        this.path = path;
        this.index = index;
        this.points = points;
    }

    /** <b>Accessor</b>
     * <p>
     * <b>Postcondition:</b> Retrieves the index of this position on the path.
     *
     * @return The index of this position.
     */
    public int getIndex() {
        return this.index;
    }

    /** <b>Accessor</b>
     * <p>
     * <b>Postcondition:</b> Retrieves the path associated with this position.
     *
     * @return The path of this position.
     */
    public Path getPath() {
        return path;
    }

    /** <b>Accessor</b>
     * <p>
     * <b>Postcondition:</b> Retrieves the points associated with this position.
     *
     * @return The points of this position.
     */
    public int getPoints() {
        return points;
    }
}
