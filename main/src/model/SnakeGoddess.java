package model;

/**
 * Represents a Snake Goddess statue in the game.
 * Snake Goddess findings have a description and an image.
 */
public class SnakeGoddess extends Finding {

    /**
     * Constructs a Snake Goddess statue with a description and image.
     *
     * @param description The description of the finding.
     * @param imagePath The path to the image representing the finding.
     */
    public SnakeGoddess(String description, String imagePath) {
        super(description, imagePath);
    }

    /** <b>Observer</b>
     * <p>
     * <b>Postcondition:</b> Checks if this finding is a Snake Goddess.
     *
     * @return True, as this is a Snake Goddess statue.
     */
    public boolean isSnakeGoddess() { return true; }
}
