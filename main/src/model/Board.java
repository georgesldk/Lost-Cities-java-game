package model;

import java.util.ArrayList;
import java.util.Collections;


/**
 * Represents the game board.
 */
public class Board {

    private final Path[] paths = new Path[4];
    private ArrayList<Card> Discarded;
    private ArrayList<Finding> Findings ;
    private Palace[] palaces;

    /**
     * Constructs a new game board with initialized paths and an empty discard pile.
     */
    public Board() {
        this.Discarded = new ArrayList<>();
        this.Findings = new ArrayList<>();
        initializePalaces();
        initializePaths();
        addFindings();
    }

    /** <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Initializes the palaces.
     */
    public void initializePalaces() {

        palaces = new Palace[] { new Palace(Palace.PalaceName.KNOSSOS), new Palace(Palace.PalaceName.MALIA), new Palace(Palace.PalaceName.FAISTOS), new Palace(Palace.PalaceName.ZAKROS)};
        //System.out.println("Palaces initialized:");

//        for (int i = 0; i < palaces.length; i++) {
//            System.out.println(" - " + palaces[i].getName());
//        }

    }

    /** <b>Accessor</b>
     * <p>
     * <b>Postcondition:</b> Retrieves the palaces.
     *
     * @return The palaces array.
     */
    public Palace[] getPalaces() {
        return palaces;
    }

    /** <b>Accessor</b>
     * <p>
     * <b>Postcondition:</b> Returns the list of discarded cards.
     *
     * @return ArrayList of discarded cards.
     */
    public ArrayList<Card> getDiscarded() { return Discarded; }

    /** <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Adds a card to the discard pile.
     *
     * @param card The card to be added to the discard pile.
     */
    public void addToDiscarded(Card card) {
        Discarded.add(card);
    }

    /** <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Removes a card from the discard pile.
     *
     * @param card The card to be removed from the discard pile.
     */
    public void getFromDiscarded(Card card) {
        Discarded.remove(card);
        //I Thought players could grab back card from the discarded pile
        //so this got never used
    }

    /** <b>Accessor</b>
     * <p>
     * <b>Postcondition:</b> Retrieves the paths on the board.
     *
     * @return Array of paths on the board.
     */
    public Path[] getPaths() { return paths; }

    /** <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Adds findings across the board paths.
     */
    public void addFindings() {

        ArrayList<Finding> findings = new ArrayList<>();
        //add rares
        findings.add(new RareFinding("Phaistos Disk", "/project_assets/images/findings/diskos.jpg", 35));
        findings.add(new RareFinding("Ring of Minos", "/project_assets/images/findings/ring.jpg", 25));
        findings.add(new RareFinding("Malia Jewel", "/project_assets/images/findings/kosmima.jpg", 25));
        findings.add(new RareFinding("Zakros Rhyton", "/project_assets/images/findings/ruto.jpg", 25));
        //add frescoes
        findings.add(new Fresco("Fresco 1", "/project_assets/images/findings/fresco1_20.jpg", 20));
        findings.add(new Fresco("Fresco 2", "/project_assets/images/findings/fresco2_20.jpg", 20));
        findings.add(new Fresco("Fresco 3", "/project_assets/images/findings/fresco3_15.jpg", 15));
        findings.add(new Fresco("Fresco 3", "/project_assets/images/findings/fresco3_15.jpg", 15));
        findings.add(new Fresco("Fresco 4", "/project_assets/images/findings/fresco4_20.jpg", 20));
        findings.add(new Fresco("Fresco 5", "/project_assets/images/findings/fresco5_15.jpg", 15));
        findings.add(new Fresco("Fresco 6", "/project_assets/images/findings/fresco6_15.jpg", 15));
        //add statues
        for (int i = 0; i < 10; i++) {
            findings.add(new SnakeGoddess("Snake Goddess Statue", "/path/to/image.jpg"));
        }

        Collections.shuffle(findings);


        //distribute correctly the points to positions
        //and the findings to finding positions
        for (Path path : paths) {

            Position[] positions = path.getPath();

            int[] findingPositions = {2, 4, 6, 8, 9};

            for (int j : findingPositions) {

                if (!findings.isEmpty()) {
                    Position position = positions[j-1];

                    if (position instanceof FindingPosition) {
                        Finding finding = findings.remove(0);
                        ((FindingPosition) position).setFinding(finding);

                        //TO COMMENT OUT
//                        System.out.println("Finding Created: " +
//                                "Name = " + finding.getDescription() +
//                                ", Type = " + finding.getClass().getSimpleName() +
//                                ", Path = " + path.getPalace().getName() +
//                                ", Position = " + j);
                    }
                }
            }
        }
    }

    /** <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Initializes the paths.
     */
    private void initializePaths() {
        int[] points = {-20, -15, -10, 5, 10, 15, 30, 35, 50};

        for (int i=0; i<paths.length; i++) {

            paths[i] =new Path(new Palace(Palace.PalaceName.values()[i]));

            Position[] positions = new Position[9];

            for (int j=0; j<positions.length; j++) {

                if (j==1 || j==3 || j==5 || j==7 || j==8) {
                    positions[j] = new FindingPosition(paths[i], j+1, points[j]);

                }else {
                    positions[j]= new SimplePosition(paths[i], j+1, points[j]);

                }
            }

            paths[i].setPath(positions);

        }
    }

}
