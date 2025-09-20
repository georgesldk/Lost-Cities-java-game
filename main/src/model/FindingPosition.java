package model;

/**
 * Represents a position on a path that contains a finding.
 * Allows interaction with the finding at this position.
 */
public class FindingPosition extends Position {

    private Finding finding;

    /**
     * Constructs a FindingPosition on a specific path.
     *
     * @param path The path where this position exists.
     * @param index The index of the position on the path.
     * @param points The points associated with this position.
     */
    public FindingPosition(Path path, int index, int points) {
        super(path, index, points);
    }

    /** <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Sets the finding for this position.
     *
     * @param finding The finding to be set at this position.
     */
    public void setFinding(Finding finding) { this.finding = finding; }

    /** <b>Accessor</b>
     * <p>
     * <b>Postcondition:</b> Retrieves the finding at this position.
     *
     * @return The finding at this position.
     */
    public Finding getFinding() { return finding; }
}
