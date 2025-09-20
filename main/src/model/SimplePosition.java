package model;

/**
 * Represents a simple position on a path in the game.
 * Simple positions allow basic actions.
 */
public class SimplePosition extends Position {

    /**
     * Constructs a simple position on a specific path.
     *
     * @param path The path this position belongs to.
     * @param index The index of this position on the path.
     * @param points The points associated with this position.
     */
    public SimplePosition(Path path, int index, int points) {
        super(path, index, points);
    }

}
