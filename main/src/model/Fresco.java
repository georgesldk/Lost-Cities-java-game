package model;

/**
 * Represents a fresco finding in the game.
 * Frescos have point values and unique interactions.
 */
public class Fresco extends Finding {

    private int points;

    /**
     * Constructs a fresco with a description, image path, and point value.
     *
     * @param description The description of the fresco.
     * @param imagePath The path to the image representing the fresco.
     * @param points The point value of the fresco.
     */
    public Fresco(String description, String imagePath, int points) {
        super(description, imagePath);
        this.points = points;
    }

    /** <b>Accessor</b>
     * <p>
     * <b>Postcondition:</b> Retrieves the point value of the fresco.
     *
     * @return The point value of the fresco.
     */
    public int getPointValue() { return points; }

    /** <b>Observer</b>
     * <p>
     * <b>Postcondition:</b> Determines if this finding is a fresco.
     *
     * @return True walyes beacuse this class represents a fresco.
     */
    public boolean isFresco() { return true; }


}
