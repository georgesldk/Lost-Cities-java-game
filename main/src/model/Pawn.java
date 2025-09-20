package model;

import controller.Controller;
import view.View;

import javax.swing.*;

/**
 * Represents a pawn in the game.
 * Pawns are controlled by players and interact with paths and positions on the board.
 */
public class Pawn {

    private Position thisPawnPosition;
    private Path thisPawnPath;
    private final Player thisPawnOwner;
    private boolean immobilized;

    /**
     * Constructs a Pawn belonging to a specific player.
     *
     * @param thisPawnOwner The player who owns this pawn.
     */
    public Pawn(Player thisPawnOwner) {
        this.thisPawnOwner = thisPawnOwner;
    }

    /** <b>Accessor</b>
     * <p>
     * <b>Postcondition:</b> Retrieves the owner of this pawn.
     *
     * @return The player who owns this pawn.
     */
    public Player getThisPawnOwner() {
        return thisPawnOwner;
    }

    /** <b>Accessor</b>
     * <p>
     * <b>Postcondition:</b> Retrieves the path associated with this pawn.
     *
     * @return The path this pawn is on.
     */
    public Path getThisPawnPath() {
        return thisPawnPath;
    }

    /** <b>Accessor</b>
     * <p>
     * <b>Postcondition:</b> Retrieves the position of this pawn.
     *
     * @return The position of this pawn.
     */
    public Position getThisPawnPosition() {
        return thisPawnPosition;
    }

    /** <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Sets the path for this pawn.
     *
     * @param thisPawnPath The path to set for this pawn.
     */
    public void setThisPawnPath(Path thisPawnPath) {
        this.thisPawnPath = thisPawnPath;
    }

    /** <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Sets the position for this pawn.
     *
     * @param thisPawnPosition The position to set for this pawn.
     */
    public void setThisPawnPosition(Position thisPawnPosition) {
        this.thisPawnPosition = thisPawnPosition;
    }

    /** <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Moves this pawn to a new position.
     */
    public void moveThisPawn(Path path, int steps, Board board, Controller controller, View view) {

        if (path==null) {
            //System.out.println("Invalid path.");
            return;
        }
        if ( this.cantTouchThis())  {

            //System.out.println("Pawn is immobilizd and cant move.");
            JOptionPane.showMessageDialog(null, "Your Theseus pawn is immobilized and canmt move.");
            return;

        }

//        if (thisPawnPosition==null) {
//            System.out.println("Pawn position error");
//            return;
//        }

        Position[] pathPositions = path.getPath();
        int currNumber = thisPawnPosition.getIndex() - 1;

        int dir;
        if (steps>0){
            dir = 1;
        }
        else {
            dir= -1;
        }

        int tilesCount= Math.abs(steps);

         for (int i=0; i<tilesCount; i++)   {

            int dest = currNumber + dir;
            if (dest<0 || dest>=pathPositions.length) {

                //System.out.println("Pawn reached edges.");
                 break; //cant go b4 beggining or past end (deep huh?)
            }

            Position nextPosition = pathPositions[dest];
            this.setThisPawnPosition(nextPosition);
            currNumber = dest;

            //System.out.println("Pawn moved at" + nextPosition.getIndex());

            controller.handleFindings(this, nextPosition);

            if (controller.isGameOver() )  {
                controller.gameOver(); //over check
                break;

            }
        }

        //this.getThisPawnOwner().getThisPlayerPawns();
    }


    /** <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Reveals the pawn to other players.
     */
    public void revealThisPawn() {
        return;
        //would be nice if i did it wouldn't it?!?1
    }

    /** <b>Observer</b>
     * <p>
     * <b>Postcondition:</b> Checks if the pawn is visible.
     *
     * @return True if the pawn is visible; false otherwise.
     */
    public boolean isThisPawnVisible() {
        return false; //same
    }


    /** <b>Accessor</b>
     * <p>
     * <b>Postcondition:</b> Retrieves the point value of the pawn's current position.
     *
     * @return The point value of the pawn's current position.
     */
    public int getCurrentPositionPoints() {
        return 0; // just use pawn.getThisPawnPosition.getPoints
    }


    /** <b>Observer</b>
     * <p>
     * <b>Postcondition:</b> Checks if the pawn is immune to Minotaur regular action (theseus).
     *
     * @return True if the pawn is immune; false otherwise.
     */
    public boolean cantTouchThis() {
        return immobilized;
    }

    /**
     * <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Makes Theseus immobilized.
     *
     * @param immobilized Bool true if theseus is fckn frozen ot false if not.
     */
    public void immobilize(boolean immobilized) {
        this.immobilized = immobilized;
    }

    /**
     * <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Resets immobilization effect fam.
     */
    public void remobilize() {
        this.immobilized = false;
    }
}
