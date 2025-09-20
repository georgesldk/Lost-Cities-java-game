package model;

/**
 * Represents a finding in the game.
 * Findings have a description and an associated image.
 */
public abstract class Finding {

    private String description;
    private String imagePath;

    /**
     * Constructs a new finding with a description and an image path.
     *
     * @param description The description of the finding.
     * @param imagePath The path to the image representing the finding.
     */
    public Finding(String description, String imagePath) {
        this.description = description;
        this.imagePath = imagePath;
    }

    /** <b>Accessor</b>
     * <p>
     * <b>Postcondition:</b> Retrieves the description of the finding.
     *
     * @return The description of the finding.
     */
    public String getDescription() { return description; }

    /** <b>Accessor</b>
     * <p>
     * <b>Postcondition:</b> Retrieves the image path of the finding.
     *
     * @return The image path of the finding.
     */
    public String getImagePath() { return imagePath; }
}
