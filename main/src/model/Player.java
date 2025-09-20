package model;

import controller.Controller;
import view.View;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a player in the game.
 * Players control pawns, collect findings, and manage a hand of cards and score.
 */
public class Player {

    private String playerName;
    private int playerScore;
    private int snakeGoddessStatues;

    private ArrayList<Pawn> pawns;
    private ArrayList<Card> hand;
    private ArrayList<Fresco> playerFrescos;
    private ArrayList<RareFinding> playerFindings;

    /**
     * Constructs a player in the game.
     * Initializes the player's pawns, hand, and findings.
     *
     * @param playerName Name of the player.
     */
    public Player(String playerName) {
        this.playerName = playerName;
        this.playerScore = 0;
        this.snakeGoddessStatues = 0;
        this.pawns = new ArrayList<>();
        this.hand = new ArrayList<>();
        this.playerFrescos = new ArrayList<>();
        this.playerFindings = new ArrayList<>();

        //app pawns
        for (int i = 0; i < 3; i++) {
            pawns.add(new Archeologist(this));
        }
        pawns.add(new Theseus(this));
    }

    /** <b>Accessor</b>
     * <p>
     * <b>Postcondition:</b> Retrieves the name of the player.
     *
     * @return Name of the player.
     */
    public String getPlayerName() {
        return playerName;
    }

    /** <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Sets the name of the player.
     *
     * @param playerName New name of the player.
     */
    public void setPlayerName(String playerName) { this.playerName = playerName; }

    /** <b>Accessor</b>
     * <p>
     * <b>Postcondition:</b> Retrieves the player's current score.
     *
     * @return Score of the player.
     */
    public int getPlayerScore() { return playerScore; }


    /** <b>Accessor</b>
     * <p>
     * <b>Postcondition:</b> Retrieves the player's current hand.
     *
     * @return Arraylist hand of the player.
     */
    public ArrayList<Card> getHand() { return hand; }

    /** <b>Accessor</b>
     * <p>
     * <b>Postcondition:</b> Retrieves the number of Snake Goddess statues collected.
     *
     * @return Number of statues collected.
     */
    public int getSnakeGoddessStatues() { return snakeGoddessStatues; }

    /** <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Increments the number of Snake Goddess statues collected.
     */
    public void addSnakeGoddessStatue() {
        this.snakeGoddessStatues++;
        System.out.println(playerName + " collected a Snake Goddess Statue (+10 points). Total Statues: " + this.snakeGoddessStatues);
    }

    /** <b>Accessor</b>
     * <p>
     * <b>Postcondition:</b> Retrieves the player's current frescoes.
     *
     * @return Arraylist frescoes of the player.
     */
    public ArrayList<Fresco> getFrescos() { return playerFrescos; }

    /** <b>Accessor</b>
     * <p>
     * <b>Postcondition:</b> Retrieves the player's current rare findings.
     *
     * @return Arraylist rare findings of the player.
     */
    public ArrayList<RareFinding> getRareFindings() { return playerFindings; }

    /** <b>Observer</b>
     * <p>
     * <b>Postcondition:</b> Checks if a fresco has already been photographed.
     *
     * @param fresco Fresco to check.
     * @return True if the fresco has been photographed, false otherwise.
     */
    public boolean hasPhotographed(Finding fresco) {
        return false;
        //not used
    }

    /** <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Adds points to the player's score.
     *
     * @param points Number of points to add.
     */
    public void manageScore(int points) {
        this.playerScore += points;
        //System.out.println(playerName + " gained " + points + " points. Total now: " + this.playerScore);
    }

    /** <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Adds a RF to the player.
     *
     * @param precious The Rare FInding to add.
     */
    public void addRareFinding(RareFinding precious) {
        this.playerFindings.add(precious);
        manageScore(precious.getPointValue());
        //System.out.println(playerName + " collected Rare Finding: " + precious.getDescription());
    }

    /** <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Adds a card to the player's hand.
     *
     * @param card The card to add.
     */
    public void addCard(Card card) {
        if (hand.size() < 8) {
            hand.add(card);
            //System.out.println(playerName + " received card: " + card.toString());
        } else {
            System.out.println("Hand is full. Cannot add more cards.");
        }
    }

    /** <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Removes a card from the player's hand.
     *
     * @param card The card to remove.
     */
    public void removeCard(Card card) {
        hand.remove(card);
        //System.out.println(playerName + " discarded card: " + card.toString());
    }

    /**
     * <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Discards the specified card from the player's hand,
     * updates the discard pile, and updates the changes in the view.
     *
     * @param selectedCard The card to be discarded.
     * @param board The board to update the discard pile.
     * @param view The view to update the discard pile.
     */
    public void discardCard(Card selectedCard, Board board, View view) {
        //System.out.println(this.playerName + " is discarding card: " + selectedCard);
        board.addToDiscarded(selectedCard);
        view.updateDiscardDeck(board.getDiscarded());
        //System.out.println("Card discarded: " + selectedCard);
        this.getHand().remove(selectedCard);
    }

    /**
     * <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Plays the specified card, updates the play area,
     * modifies the game state as required, and updates the view.
     *
     * @param selectedCard The card to be played.
     * @param playArea The array of buttons representing the player's play area.
     * @param cardButton The button associated with the card being played.
     * @param board The game board .
     * @param view The view .
     * @param controller The controller.
     * @return If success TRUE else False
     */
    public boolean playCard(Card selectedCard, JButton[] playArea, JButton cardButton, Board board, View view, Controller controller) {
        //System.out.println(this.playerName + " is attempting to play card: " + selectedCard);

        Palace.PalaceName palaceName = selectedCard.getPalace().getName();

        int palaceIndex;

        switch (palaceName) {
            case KNOSSOS: palaceIndex = 0; break;
            case MALIA:   palaceIndex = 1; break;
            case FAISTOS: palaceIndex = 2; break;
            case ZAKROS:  palaceIndex = 3; break;
            default:
                System.out.println("Error getting palace of this card: " + selectedCard);
                return false;
        }

        //System.out.println("Determined palace index: " + palaceIndex + " for card: " + selectedCard);

        JButton whereToPlay = playArea[palaceIndex];
        Card lastPlayedCard = (Card) whereToPlay.getClientProperty("card"); //get last card played on the play area button

        boolean successFlag = false;


        if (selectedCard instanceof NumberCard) {
            successFlag = handleNumberCard((NumberCard) selectedCard, lastPlayedCard, whereToPlay, palaceIndex, cardButton, board, view, controller);

        } else if(selectedCard instanceof Ariadne) {

            successFlag = handleAriadneCard(palaceIndex, board, view, controller);

        } else if(selectedCard instanceof Minotaur) {
            successFlag = handleMinotaurCard(palaceIndex, board, view, controller);

        } else{

            System.out.println("Error playing card: " + selectedCard);
        }

        if (successFlag) {
            this.removeCard(selectedCard);
            //System.out.println("Card removed from " + this.playerName + "'s hand: " + selectedCard);
        }

        return successFlag;


    }

    /**
     * <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Draws a new card from the deck and adds it to the player's hand.
     *
     * @param deck The deck.
     */
    public void drawNewCard(Deck deck) {
        Card newCard = deck.playerDrawsCard();
        if (newCard != null) {
            addCard(newCard);
            //System.out.println(this.playerName + " drew a new card: " + newCard);

        } else {
            //JOptionPane.showMessageDialog(null, "The deck is empty, no card could be drawn.");
            System.out.println("Deck is empty. " + this.playerName + " could not draw a new card.");
        }
    }

    /**
     * <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Handles the logic for playing a NumberCard.
     *
     * @param numberCard The NumberCard being played.
     * @param lastPlayedCard The last card played in the play area.
     * @param targetPlayButton The button in the play area where the card's appropriate palace is.
     * @param palaceIndex The index of the palace of the play area button.
     * @param cardButton The button of with the card.
     * @param board The game board.
     * @param view The view.
     * @param controller The controller.
     * @return True if NumberCard was played successfully else fasle
     */
    private boolean handleNumberCard(NumberCard numberCard, Card lastPlayedCard, JButton targetPlayButton, int palaceIndex, JButton cardButton, Board board, View view, Controller controller) {

        //System.out.println(this.playerName + " is handling NumberCard: " + numberCard);
        if (lastPlayedCard==null) {
            //we are in first card case, must place pawn
            targetPlayButton.putClientProperty("card", numberCard);
            targetPlayButton.setIcon(cardButton.getIcon());
            targetPlayButton.setText("");
            //System.out.println("First card played on palace index " + palaceIndex + ": " + numberCard);

            //check if theseus ia already placed
            boolean theseusPlaced = false;
            for (Pawn pawn   : pawnsOnPaths()) {
                if (pawn instanceof Theseus) {
                    theseusPlaced = true;
                    break;
                }
            }

            //System.out.println(this.playerName + " has Theseus placed: " + theseusPlaced);

            String[] pawnOptions;
            if (theseusPlaced) {
                pawnOptions = new String[]{"Archaeologist"};
            } else {
                pawnOptions = new String[]{"Archaeologist", "Theseus"};
            }



            int pawnChoice= view.placePawn(pawnOptions);

            if (pawnChoice>=0) {


                Pawn dasPawn= selectPawn(pawnOptions[pawnChoice]);

                if (dasPawn!=null) {

                    Path dasPath= board.getPaths()[palaceIndex];

                    Position startingPosition = dasPath.getPath()[0];

                    this.addPawnToPath(dasPawn, dasPath, startingPosition);

                    //this.getThisPlayerPawns();

                } else {
                    System.out.println("No available pawns of the selected type for " + this.playerName);
                    return false;
                }


            }else {
                System.out.println(this.playerName + " canceled pawn");
                return false;
            }
            return true;

        } else if(lastPlayedCard instanceof NumberCard) {

            NumberCard lastNumberCard = (NumberCard) lastPlayedCard;

            if (lastNumberCard.getNumber()<=numberCard.getNumber()) {

                targetPlayButton.putClientProperty("card", numberCard); //attach car to button
                targetPlayButton.setIcon(cardButton.getIcon());
                targetPlayButton.setText("");

                Path selectedPath= board.getPaths()[palaceIndex];
                Pawn pawnOnPath = controller.getPawnOnPath(this, selectedPath);


                if (pawnOnPath!=null) {

                    if (!pawnOnPath.cantTouchThis()) {
                        pawnOnPath.moveThisPawn(selectedPath, 1, board, controller, view);
                        return true;
                    } else {
                        JOptionPane.showMessageDialog(null, "Your Theseus pawn is immobilized and cannot move.");
                        return false;

                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "No pawn found.");
                    return false;
                }
            } else {

                JOptionPane.showMessageDialog(null, "You can't play this card. It's too low in value.");
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Something went wrong card stacking. idk mate.");
            return false;


        }
    }


    /**
     * <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Handles the logic for playing an AriadneCard.
     *
     * @param palaceIndex The index of the palace of the play area button.
     * @param board The game board.
     * @param view The view.
     * @param controller The controller.
     * @return True if Ariadne was played successfully else false.
     */
    private boolean handleAriadneCard(int palaceIndex, Board board, View view, Controller controller) {
        //System.out.println(this.playerName + " is playing an Ariadne card on palace index: " + palaceIndex);


        Path selectedPath = board.getPaths()[palaceIndex];

        Pawn pawnOnPath = controller.getPawnOnPath(this, selectedPath);

        if (pawnOnPath==null) {

            JOptionPane.showMessageDialog(null, "There is no pawn on this path to play an Ariadne card on.");
            return false;
        }
        if (pawnOnPath.cantTouchThis()) {
            JOptionPane.showMessageDialog(null, "Your Theseus pawn is immobilized and cannot move.");
            return false;
        }

        //System.out.println(this.playerName + " played an Ariadne card. Moving pawn 2 steps forward.");
        pawnOnPath.moveThisPawn(selectedPath, 2, board, controller, view); //move 2 tiles
        return true;
    }



    /**
     * <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Handles the logic for playing a MinotaurCard,
     * applying its effects to the game state, and reflecting the changes in the view.
     *
     * @param palaceIndex The index of the palace of the play area button.
     * @param board The game board.
     * @param view The view.
     * @param controller The controller.
     * @return True if Minotaur was played successfully else false.
     */
    private boolean handleMinotaurCard(int palaceIndex, Board board, View view, Controller controller) {


        //System.out.println(this.playerName + " is attempting to play a Minotaur card on palace index: " + palaceIndex);

        Path selectedPath = board.getPaths()[palaceIndex];
        ArrayList<Pawn> enemyPawns = controller.getEnemyPawnsOnPath(this, selectedPath); //get enemy pawns to check for pawns on path

        if (enemyPawns.isEmpty()) {

            JOptionPane.showMessageDialog(null, "There are no enemy pawns on this path to play a Minotaur card.");
            return false;

         }

        boolean endOfPath=false;


        for (Pawn pawn : enemyPawns)   {

            if (pawn.getThisPawnPosition().getIndex()>=7)  {

                endOfPath = true;
                break; //dont do anything if pawn is after checkpoint (pos 7+)
            }
        }


        if (endOfPath) {
            JOptionPane.showMessageDialog(null, "Cannot use Minotaur card when enemy pawns are over the checkpoint");
            return false;

        }


        //System.out.println(this.playerName + " played a Minotaur card.");
        minoHelper(selectedPath, board, view, controller);
        return true;

    }

    /**
     * <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Logic foe Minotaur Card behavior
     * @param path The path of the pawn affected by the Minotaur.
     * @param board The game board.
     * @param view The view.
     * @param controller The controller.
     */
    private void minoHelper(Path path, Board board, View view, Controller controller) {


        if (controller.getTurn()==controller.player1 && this==controller.player1)  {

            for (Pawn pawn:controller.player2.pawnsOnPaths() ) {

                if (pawn.getThisPawnPath()==path) {

                    if (pawn instanceof Theseus) {

                        pawn.immobilize(true);
                        JOptionPane.showMessageDialog(null, controller.player2.getPlayerName() + "'s Theseus has been immobilized for one turn");

                    }else if (pawn instanceof Archeologist) {
                        pawn.moveThisPawn(path, -2, board, controller, view);

                    }
                }
            }
        } else if (controller.getTurn()==controller.player2 && this == controller.player2)  {

            for (Pawn pawn:controller.player1.pawnsOnPaths()) {

                if (pawn.getThisPawnPath()==path) {

                    if (pawn instanceof Theseus) {
                        //theseus gets immoblilized
                        pawn.immobilize(true);
                        JOptionPane.showMessageDialog(null, controller.player1.getPlayerName() + "'s Theseus has been immobilized for one turn");
                    }else if (pawn instanceof Archeologist) {
                        pawn.moveThisPawn(path, -2, board, controller, view);
                        //archeologist goes back 2 tiles

                    }
                }
            }
        }
    }


//    private Pawn selectPawn(String option) {
//
//        if (option.equals("Archaeologist")) {
//            return Arrays.stream(pawnsAvailable())
//                    .filter(p -> p instanceof Archeologist && p.getThisPawnPosition() == null)
//                    .findFirst()
//                    .orElse(null);
//        } else if (option.equals("Theseus")) {
//            return Arrays.stream(pawnsAvailable())
//                    .filter(p -> p instanceof Theseus && p.getThisPawnPosition() == null)
//                    .findFirst()
//                    .orElse(null);
//        }
//        return null;
//    }

    /**
     * <b>Accessor</b>
     * <p>
     * <b>Postcondition:</b> Selects and returns a pawn.
     *
     * @param option The string name of th pawn.
     * @return The selected pawn/null if not found.
     */
    private Pawn selectPawn(String option) {
        for (Pawn pawn : pawnsAvailable()) {
            if (option.equals("Archaeologist") && pawn instanceof Archeologist && pawn.getThisPawnPosition()==null) {
                return pawn;

            } else if (option.equals("Theseus") && pawn instanceof Theseus && pawn.getThisPawnPosition()==null)  {
                return pawn;

            }
        }
        return null;
    }


//    public Pawn[] pawnsOnPaths() {
//        return pawns.stream().filter(p -> p.getThisPawnPosition() != null).toArray(Pawn[]::new);
//    }


    /** <b>Accessor</b>
     * <p>
     * <b>Postcondition:</b> Retrieves the player's pawns currently on paths.
     *
     * @return Array of pawns currently on paths.
     */
    public Pawn[] pawnsOnPaths() {
        List<Pawn> pawnsOnPaths = new ArrayList<>();
        for (Pawn pawn : pawns) {
            if (pawn.getThisPawnPosition() != null) {
                pawnsOnPaths.add(pawn);
            }
        }
        return pawnsOnPaths.toArray(new Pawn[0]);
    }

    /** <b>Accessor</b>
     * <p>
     * <b>Postcondition:</b> Retrieves the player's pawns currently on paths.
     *
     * @return Array of pawns currently on paths.
     */
    public Pawn[] pawnsAvailable() {
        return pawns.toArray(new Pawn[0]);
    }

    /**
     * <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Adds the  pawn to the  path at the SPECIFIED position,
     *
     * @param pawn The pawn.
     * @param path The path.
     * @param position The specific position.
     */
    public void addPawnToPath(Pawn pawn, Path path, Position position) {

        if (pawn.getThisPawnPath()!=null) {
            System.out.println("Pawn is already placed.");
            return;
        }

        pawn.setThisPawnPath(path);
        pawn.setThisPawnPosition(position);
        path.getPawns().add(pawn);


    }

    /** <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Adds a fresco to the player's collection.
     *
     * @param fresco Fresco to add.
     */
    public void photograph(Fresco fresco) {

        if(!playerFrescos.contains(fresco)) {

            playerFrescos.add(fresco);
             manageScore(fresco.getPointValue());

        }
    }

    /**
     * <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Calculates player's Statue score
     * <p>
     *
     */
    public void statueMath() {

        switch (this.snakeGoddessStatues) {
            case 0:  break;
            case 1:  manageScore(-20); break;
            case 2:  manageScore(-15); break;
            case 3:  manageScore(10); break;
            case 4:  manageScore(15); break;
            case 5:  manageScore(30); break;
            case 6:  manageScore(50); break;
            default: manageScore(50); break; //idk what to put at default mane
        }
    }

    /**
     * <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Calculates player's pawn positions' score
     * <p>
     *
     */
    public void pawnPosMath() {

        for  ( Pawn pawn:  pawnsOnPaths()) {

            Position pos= pawn.getThisPawnPosition();

            if (pos!=null) {

                int tilePoints= pos.getPoints();

                if (pawn instanceof Theseus) { //double that shiiiii

                    manageScore(tilePoints * 2);


                } else if (pawn instanceof Archeologist) {
                    manageScore(tilePoints);
                }
            }
        }
    }

    /**
     * <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Calculates player's additive statue and pos' score
     * <p>
     *
     */
    public void moreMath() {
        statueMath();
        pawnPosMath();
    }



//    /**
//     * <b>Observer</b>
//     * <p>
//     * <b>Postcondition:</b> Lists all pawns of the player,
//     *
//     */
//    public void getThisPlayerPawns() {
//
//        for (Pawn pawn:this.pawnsOnPaths())
//        {
//             if (pawn.getThisPawnPath()!=null && pawn.getThisPawnPosition()!=null) {
//                System.out.println("- " + pawn.getClass().getSimpleName()
//                        + " on path " + pawn.getThisPawnPath().getPalace().getName()
//                        + " at position " + pawn.getThisPawnPosition().getIndex()
//                        + " | Frozen: " + pawn.cantTouchThis());
//            } else {
//                System.out.println("- " + pawn.getClass().getSimpleName() + " is not placed on any path.");
//            }
//        }
//    }
}
