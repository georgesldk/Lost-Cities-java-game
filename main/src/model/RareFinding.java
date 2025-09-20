package model;

/**
 * Represents a rare finding in the game.
 * Rare findings have a description, an image, and a point value.
 */
public class RareFinding extends Finding {

    private int points;

    /**
     * Constructs a rare finding with a description, image, and points.
     *
     * @param description The description of the finding.
     * @param imagePath The path to the image representing the finding.
     * @param points The points associated with this finding.
     */
    public RareFinding(String description, String imagePath, int points) {
        super(description, imagePath);
        this.points = points;
    }

    /** <b>Accessor</b>
     * <p>
     * <b>Postcondition:</b> Retrieves the point value of this finding.
     *
     * @return The point value of this finding.
     */
    public int getPointValue() {
        return points;
    }

    /** <b>Observer</b>
     * <p>
     * <b>Postcondition:</b> Checks if this finding is a rare finding.
     *
     * @return True, as this is a rare finding.
     */
    public boolean isRareFinding() {
        return true;
    }
}
