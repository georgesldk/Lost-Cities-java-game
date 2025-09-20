package model;

/**
 * Represents a palace in the game.
 * Palaces have specific names and are associated with paths and cards.
 */
public class Palace {

    /**
     * Enum representing the names of the palaces in the game.
     */
    public enum PalaceName {
        KNOSSOS,
        MALIA,
        FAISTOS,
        ZAKROS
    }

    private final PalaceName name;

    /**
     * Constructs a Palace with a specific name.
     *
     * @param name The name of the palace.
     */
    public Palace(PalaceName name) {
        this.name = name;
    }

    /** <b>Accessor</b>
     * <p>
     * <b>Postcondition:</b> Retrieves the name of the palace.
     *
     * @return The name of the palace.
     */
    public PalaceName getName() {
        return name;
    }

    /**
     * <b>Accessor</b>
     * <p>
     * <b>Postcondition:</b> Returns a string representation of the palace.
     *
     * @return A string of the Palace.
     */
    @Override
    public String toString() {
        return name.toString();
    }
}
