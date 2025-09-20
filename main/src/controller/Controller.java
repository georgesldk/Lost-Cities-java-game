package controller;

import model.*;
import view.View;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Controller manages the game logic, interactions, and overall flow of the game.
 */
public class Controller {

    private final View view;
    public final Player player1;
    public final Player player2;
    private final Board board;
    private final Deck deck;
    private Player currentPlayer;
    private Clip player1Clip;
    private Clip player2Clip;


    /**
     * Constructs a Controller that initializes players, the board, the deck, and the view.
     */
    public Controller() {


        this.view = new View();
        this.player1 = new Player("player1");
        this.player2 = new Player("player2");
        this.board = new Board();
        this.deck = new Deck(board.getPalaces());
        setupActionListeners();

        Random random = new Random();
        currentPlayer = random.nextBoolean() ? player1 : player2;
        view.updateInfo("It's "  + currentPlayer.getPlayerName() + "'s turn");

        player1Clip = loadClip("project_assets/music/Player1.wav");
        player2Clip = loadClip("project_assets/music/Player2.wav");
        musicaly();

        // System.out.println("first player is " + currentPlayer.getPlayerName());
        play();
    }



    /** <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Initializes the game.
     */
    public void initialize() {
        //DIDNT GET USED
    }

    /** <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Starts the game.
     */
    public void play() {

        // System.out.println("game satrted");

        for (int i=0; i<8; i++) {

            Card card1 = deck.playerDrawsCard();
            if (card1!=null) {
                player1.addCard(card1);

                //System.out.println("Player1 card " + card1);
            }

            Card card2 = deck.playerDrawsCard();
            if (card2!=null) {
                player2.addCard(card2);
                //System.out.println("Player2 card: "+ card2);
            }
        }
        //Update the hands in the view
        view.updateHands(player1);
        view.updateHands(player2);
    }

    /** <b>Accessor</b>
     * <p>
     * <b>Postcondition:</b> Returns the player whose turn it is.
     *
     * @return The current player.
     */
    public Player getTurn() {
        return currentPlayer;
    }

    /** <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Ends the game and performs cleanup actions.
     */
    public void gameOver() {

        view.updateInfo("Game over go home ya dinguses");

        finalScore();
        Player winner = isWinner();

        if (winner!=null) {
            JOptionPane.showMessageDialog(null, "Game over. Winner" + winner.getPlayerName());
        } else {
            JOptionPane.showMessageDialog(null, "Game over. Y'all tied up, what a pickle...");
        }
    }

    /** <b>Observer</b>
     * <p>
     * <b>Postcondition:</b> Checks whether the game is over.
     *
     * @return True if the game has ended, false otherwise.
     */
    public boolean isGameOver() {

        // first we check for empty dekc
        if ( deck.isEmpty() ) {
            // System.out.println("Deck empty. game over");
            return true;
        }

        // else 4+ pawns at 7+ path positions
        int maxFlag=0;

        for (Pawn pawn : player1.pawnsOnPaths()) {
            if (pawn.getThisPawnPosition()!=null && pawn.getThisPawnPosition().getIndex()>=7) {
                maxFlag++; // the pawn is on the board and its pos index is over 7 we increment
            }
        }
        for (Pawn pawn : player2.pawnsOnPaths()) {
            if (pawn.getThisPawnPosition()!=null && pawn.getThisPawnPosition().getIndex()>=7)  {
                maxFlag++;
            }
        }


        if (maxFlag>=4) {
            //System.out.println("4+ pawns at 7+ pos index. game over ");
            return true;
        }


        return false;
    }

    /** <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Handles interactions with the view and updates the game state.
     */
    private void handleView() {
        //DID NOT USE IT. USED A MORE MODULAR (MORE METHODS) APROACH.
    }

    /** <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Handles Findings when a pawn interacts with a position with a Finding on it.
     *
     * @param pawn The pawn interacting with the position.
     * @param position The position being interacted with.
     */
    public void handleFindings(Pawn pawn, Position position) {

        if (!(position instanceof FindingPosition)) {
            // dont do nothing id pos doesnt have a finding
            return;
        }

        FindingPosition thisPos = (FindingPosition) position; //cast pos to get the finding
        Finding finding = thisPos.getFinding();

        if ( finding==null) {
            // System.out.println("Finding retrieval error");
            return;
        }

        //Archeologist behavior
        if ( pawn instanceof Archeologist) {
            String[] options = {"Excavate", "Ignore"};

            //excavate yes or no window
            int choice = JOptionPane.showOptionDialog(
                    null,
                    "Archaeologist stumbled onto something. Excavate it?",
                    "Archaeologist Action",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            if (choice == JOptionPane.YES_OPTION) {

                pawn.revealThisPawn(); //never got around to implement this :(

                //System.out.println("Archaeologist on pos got " +finding.getDescription());
                //now if its one of the rares

                if (finding instanceof RareFinding) {

                    RareFinding rare= (RareFinding) finding; //cast
                    pawn.getThisPawnOwner().addRareFinding(rare); //add it to player

                    //System.out.println("C + "collected Rare Finding: " +rare.getDescription());

                    JOptionPane.showMessageDialog(null, "Player excavated the " + rare.getDescription() + "\n Got: "+rare.getPointValue() +" points.");
                    view.updateScore(pawn.getThisPawnOwner()); //update the score
                    thisPos.setFinding(null); //remove from pos


                    //
                }else if (finding instanceof SnakeGoddess) {
                    pawn.getThisPawnOwner().addSnakeGoddessStatue();
                    //System.out.println(pawn.getThisPawnOwner().getPlayerName() + " found a snake goddess statue.");
                    JOptionPane.showMessageDialog(null, pawn.getThisPawnOwner().getPlayerName() + " found a snake goddess statue.");

                    view.updateScore(pawn.getThisPawnOwner());
                    view.updateStatues(pawn.getThisPawnOwner());
                    thisPos.setFinding(null); //remove again

                } else if (finding instanceof Fresco) {
                    Fresco fresco = (Fresco) finding;



                    pawn.getThisPawnOwner().photograph(fresco);
                    System.out.println(pawn.getThisPawnOwner().getPlayerName() +" photographed Fresco: " + fresco.getDescription());

                    JOptionPane.showMessageDialog(null, "You photographed a Fresco: " + fresco.getDescription() + "\n Got: " + fresco.getPointValue()+ " points");
                    view.updateScore(pawn.getThisPawnOwner());

                }


                //remove fiding if its not a fresco
                if (! (finding instanceof Fresco)) {
                    thisPos.setFinding(null);
                }

            }

        //now logic for theseus pawns
        } else if (pawn instanceof Theseus) {

            Theseus theseus = (Theseus) pawn;

            if (!theseus.hasDestroyAbility()) {
                //System.out.println("Theseus cant destroy no more. (3+)");
                return;
            }
            String[] options = {"Destroy", "Ignore"};
            int choice = JOptionPane.showOptionDialog(
                    null,
                    "Theseus found something. Destroy it? (3 times max...).",
                    "Theseus Action",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE,
                    null,
                    options,
                    options[0]
            );
            if (choice == JOptionPane.YES_OPTION) {
                pawn.revealThisPawn(); //not implemented but it would be here

                //System.out.println("Theseus on pos "+finding.getDescription());
                thisPos.setFinding(null); //"destroy the finding"
                theseus.addDestroyed(); //increment destroy count

                //System.out.println("Theseus destroyed" + finding.getDescription() +". des count at  " +theseus.getDestroy());

                JOptionPane.showMessageDialog(null, "Theseus destroyed the finding! Destroy count at " +theseus.getDestroy() + "/3 ");

            }
        }


        if (isGameOver()) {
            gameOver();
        }
    }

    /**
     * <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Handles the play or discard card action of the player.
     *
     * @param player The player doing the action
     * @param cardButton The button of the selected card
     * @param playArea The play area buttons of the current player
     */
    private void handleCardAction(Player player, JButton cardButton, JButton[] playArea) {


        if  (!isPlayerTurn(player)) {
            JOptionPane.showMessageDialog(null, "It's not your turn!");
            return;
        }

        Card selectedCard = view.getSelectedCard(cardButton);
        if (selectedCard==null) {
            System.out.println("Card not found in the hand. Something went wrong.");
            return;
        }

        //System.out.println(player.getPlayerName() + " chose card " + selectedCard);
        String[] options = {"Play", "Discard"};
        int choice = JOptionPane.showOptionDialog(
                null,
                "Play this card or discard it",
                "Card Action",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]
        );

        boolean saulgoodfam= false;

        //discard
        if (choice == JOptionPane.NO_OPTION) {

            player.discardCard(selectedCard, board, view);
            saulgoodfam = true;

            //System.out.println(player.getPlayerName() + " discarded " + selectedCard);

        }else if (choice == JOptionPane.YES_OPTION) {

            saulgoodfam = player.playCard(selectedCard, playArea, cardButton, board, view, this);

//            if (saulgoodfam) {
//                System.out.println(player.getPlayerName() + " played the card: " + selectedCard);
//            } else {
//                System.out.println("Unexpected error playing card of " +player.getPlayerName() + selectedCard);
//            }

        } else {
            //System.out.println("PLayer canceled action");
            return;
        }

        if (saulgoodfam) {

            player.drawNewCard(deck); //draw new card after playin
            view.updateHands(player);
            int xxxx= checkPawns();
            int yyyy= deck.getDeck();
            view.updateInfo2("Pawns on Checkpoints: "+xxxx);
            view.updateInfo3("Cards left: "+yyyy);

            switcheroo();

            //GAME OVER CHECK
            if (isGameOver()) {
                gameOver();
            }

        }else {
            JOptionPane.showMessageDialog(null, "Cant play this soecific card. Please select another card.");
        }


    }

    /**
     * <b>Accessor</b>
     * <p>
     * <b>Postcondition:</b> Return list of enemy (not currentPlayer's) pawns in a specific path
     *
     * @param currentPlayer Current player duuuh.
     * @param path path to check for enemy pawns.
     * @return The enemy pawns list.
     */
    public ArrayList<Pawn> getEnemyPawnsOnPath(Player currentPlayer, Path path) {

        ArrayList<Pawn> theOpps = new ArrayList<>();

        if (currentPlayer!=player1)  {

            for (Pawn pawn: player1.pawnsOnPaths()) {

                if (pawn.getThisPawnPath() == path) {
                    theOpps.add(pawn);
                }
            }
        }
        // Check player2's pawns if currentPlayer is not player2
        if (currentPlayer != player2)
        {
            for (Pawn pawn : player2.pawnsOnPaths() ) {

                if (pawn.getThisPawnPath() == path) {
                    theOpps.add(pawn);
                }
            }
        }
        //System.out.println("enemy pawns on"+ path.getPalace().getName()+ "amount of "+ theOpps.size());

        return theOpps;
    }

    /**
     * <b>Accessor</b>
     * <p>
     * <b>Postcondition:</b> Gt player's pawn on this specific path
     *
     * @param player Playes who's pawn we return
     * @param path Path to search.
     * @return Either the pawn found, or null if none are found.
     */
    public Pawn getPawnOnPath(Player player, Path path) {

        for (Pawn pawn : player.pawnsOnPaths())  {

            if (pawn.getThisPawnPath()==path) {
                return pawn;
            }
        }
        return null;
    }

    /**
     * <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> CCalculates the 2 players' scores and updates the view with em
     */
    private void finalScore() {
        //System.out.println("crunching numbers...");
        player1.moreMath();
        player2.moreMath();

        view.updateScore(player1);
        view.updateScore(player2);
        view.updateStatues(player1);
        view.updateStatues(player2);

        System.out.println("Player1 score "+ player1.getPlayerScore());
        System.out.println("Player2 score "+ player2.getPlayerScore());
    }

    /**
     * <b>Observer</b>
     * <p>
     * <b>Postcondition:</b> Appoints the CHAMPION (also checks for tiebreakers)
     *
     * @return The n who won, null for the rare case of an actual tie...
     */
    private Player isWinner() {

        int score1= player1.getPlayerScore();
        int score2= player2.getPlayerScore();

        System.out.println("P1 points " + score1+ "and  P2 points"+ score2);

        if ( score1>score2) {

            System.out.println("P1 wins with bigger score.");
            return player1;

        } else if (score2>score1) {

            System.out.println("P2 wins with bigger score.");
            return player2;

        } else {
            //tiebrakers checking sh*t

            int myPreciousP1 = player1.getRareFindings().size();
            int myPreciousP2 = player2.getRareFindings().size();
            System.out.println("tiebreaker 1 P1 rares " +myPreciousP1+"and P2 rares "+ myPreciousP2);


            if (myPreciousP1 > myPreciousP2) {
                //System.out.println("P1 wins with more rare findings");
                JOptionPane.showMessageDialog(null, "P1 wins with more rare findings.", "Game Result", JOptionPane.INFORMATION_MESSAGE);

                return player1;

            } else if (myPreciousP2>myPreciousP1) {
                //System.out.println("P2 wins with more rare findings.");
                JOptionPane.showMessageDialog(null, "P2 wins with more rare findings.", "Game Result", JOptionPane.INFORMATION_MESSAGE);

                return player2;
            }

            int snapP1 = player1.getFrescos().size();
            int snapP2 = player2.getFrescos().size();
            System.out.println("tiebreaker 2 P1 frewscos " + snapP1+ " P2 frescoes"+snapP2);

            if (snapP1>snapP2) {
                //System.out.println("P1 wins with more frescoes.");
                JOptionPane.showMessageDialog(null, "P1 wins with more frescoes.", "Game Result", JOptionPane.INFORMATION_MESSAGE);

                return player1;

            } else if (snapP2 >snapP1) {
                //System.out.println("P2 wins with more  frescoes.");
                JOptionPane.showMessageDialog(null, "P2 wins with more frescoes.", "Game Result", JOptionPane.INFORMATION_MESSAGE);

                return player2;

            }

            int goddessP1= player1.getSnakeGoddessStatues();

            int goddessP2 = player2.getSnakeGoddessStatues();
            System.out.println("tiebreaker 2 P1 Statues " + goddessP1+ " and P2 " + goddessP2);


            if (goddessP1 > goddessP2) {
                //System.out.println("Player1 wins based on most Snake Goddess Statues.");
                JOptionPane.showMessageDialog(null, "P1 wins based on most Snake Goddess Statues.", "Game Result", JOptionPane.INFORMATION_MESSAGE);

                return player1;
            } else if (goddessP2 > goddessP1) {
                //System.out.println("Player2 wins based on most Snake Goddess Statues.");
                JOptionPane.showMessageDialog(null, "P2 wins based on most Snake Goddess Statues.", "Game Result", JOptionPane.INFORMATION_MESSAGE);

                return player2;
            }

            //System.out.println("Congrats y'all tied, how did you even get here...");
            JOptionPane.showMessageDialog(null, "Congrats y'all tied, how did you even get here...", "Game Result", JOptionPane.INFORMATION_MESSAGE);

            return null; //im so tired at this point
        }
    }

    /**
     * <b>Observer</b>
     * <p>
     * <b>Postcondition:</b> Checks for if this is a specific player's turn
     *
     * @param player The player we chewck
     * @return True if its the player's turn, false if its not
     */
    private boolean isPlayerTurn(Player player) {
        return (currentPlayer==player);
    }

    /**
     * <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Switches the turn to the other player and updates the view.
     */
    private void switcheroo() {

        Player prev = currentPlayer;

        currentPlayer = (currentPlayer==player1) ? player2 : player1;

        view.updateInfo("It's " + currentPlayer.getPlayerName() + "'s turn!");
        //System.out.println("Turn switched");

        musicaly();

        //if turn change minotaured affected theseus's
        for (Pawn pawn : prev.pawnsOnPaths()) {

            if (pawn.cantTouchThis()) {
                pawn.remobilize();
            }
        }
    }


    /**
     * <b>Accessor</b>
     * <p>
     * <b>Postcondition:</b> Checks if there are 4+ pawns over the checkpoints
     *
     * @return Pawns over checkpoints amount
     */
    private int checkPawns() {
        int imAlmostDone = 0;

        for (Pawn pawn : player1.pawnsOnPaths()) {

            Position pos = pawn.getThisPawnPosition();
            if (pos!=null && pos.getIndex()>=7) {

                imAlmostDone++;
            }
        }
        for (Pawn pawn : player2.pawnsOnPaths()) {

            Position pos = pawn.getThisPawnPosition();
            if (pos!=null && pos.getIndex() >= 7) {
                imAlmostDone++;

            }
        }
        return imAlmostDone;
    }

    /**
     * <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Set up action listeners
     */
    private void setupActionListeners() {
        //Hand1 listeners
        addActionListeners(view.getHand1(), e -> {
            JButton source = (JButton) e.getSource();
            //System.out.println("Player1's hand button clicked: " + source.getText());
            handleCardAction(player1, source, view.getPlay1());
        });

        //Hand2 listeners
        addActionListeners(view.getHand2(), e -> {
            JButton source = (JButton) e.getSource();
            //System.out.println("Player2's hand button clicked: " + source.getText());
            handleCardAction(player2, source, view.getPlay2());
        });

        //Play1 listeners
        addActionListeners(view.getPlay1(), e -> {
            JButton source = (JButton) e.getSource();
            //System.out.println("Player1's play area button clicked: " + source.getText());
            source.setBackground(Color.YELLOW);
        });

        //Play2 listeners
        addActionListeners(view.getPlay2(), e -> {
            JButton source = (JButton) e.getSource();
            //System.out.println("Player2's play area button clicked: " + source.getText());
            source.setBackground(Color.YELLOW);
        });

        //Single buttons
        addActionListener(view.getDiscardedDeck(), e -> {
            //System.out.println("Discard Pile Button clicked.");
        });
        addActionListener(view.getFresco1(), e -> {
            //System.out.println("Fresco1 Button clicked.");
            view.frescoDisplay(player1);
        });
        addActionListener(view.getFresco2(), e -> {
            //System.out.println("Fresco2 Button clicked.");
            view.frescoDisplay(player2);
        });

        //Path buttons
        addActionListeners(view.getPathButtons(), e -> {

            JButton source = (JButton) e.getSource();
            String buttonName = source.getName();
            //System.out.println("Path Button clicked: " + buttonName);

            String[] parts = buttonName.replace("path: ", "").split(",");

            if (parts.length != 2) {
                //System.out.println("Invalid path button name format.");
                return;
            }
            try {

                int i = Integer.parseInt(parts[0].trim()) - 1;
                int j = Integer.parseInt(parts[1].trim()) - 1;

                Path path = board.getPaths()[i];
                Position position = path.getPath()[j];

                //System.out.println("Clicked position " + position.getIndex() + " on path of " + path.getPalace().getName());

                ArrayList<Pawn> pawnsAtPosition = new ArrayList<>();

                for (Pawn pawn : player1.pawnsOnPaths()) {

                    if (pawn.getThisPawnPosition() == position) {
                        pawnsAtPosition.add(pawn);
                    }
                }
                for (Pawn pawn : player2.pawnsOnPaths()) {
                    if (pawn.getThisPawnPosition() == position) {
                        pawnsAtPosition.add(pawn);
                    }
                }

                if (!pawnsAtPosition.isEmpty()) {
                    System.out.println("Pawns here:");

                    for (Pawn p : pawnsAtPosition) {
                        System.out.println(" - " + p.getClass().getSimpleName() + " owned by " + p.getThisPawnOwner().getPlayerName());
                    }
                } else {
                    System.out.println("No pawns here");
                }

            } catch (NumberFormatException ex) {
                System.out.println("Error parsing path button name: " + ex.getMessage());
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println("Path index out of bounds: " + ex.getMessage());
            }
        });

        view.showPawnsButton.addActionListener(e -> {
            //System.out.println("Show pawns button clicked.");
            ArrayList<Pawn> allPawnsOnPaths = new ArrayList<>();

            allPawnsOnPaths.addAll(Arrays.asList(player1.pawnsOnPaths()));
            allPawnsOnPaths.addAll(Arrays.asList(player2.pawnsOnPaths()));

            view.showPawns(allPawnsOnPaths);
        });
    }


    /**
     * <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Adds the specified ActionListener to an array of JButton objects.
     *
     * @param buttons The array of buttons to which the ActionListener is added.
     * @param action The ActionListener to attach to each button.
     */
    private void addActionListeners(JButton[] buttons, ActionListener action) {
        for (JButton button : buttons) {
            if (button != null) {
                button.addActionListener(action);
            }
        }
    }

    /**
     * <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Adds the specified ActionListener to a single JButton object.
     *
     * @param button The button to which the ActionListener is added.
     * @param action The ActionListener to attach to the button.
     */
    private void addActionListener(JButton button, ActionListener action) {
        if (button != null) {
            button.addActionListener(action);
        }
    }

    /**
     * <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Adds the specified ActionListener to a 2D array of JButton objects.
     *
     * @param buttons The 2D array of buttons to which the ActionListener is added.
     * @param action The ActionListener to attach to each button.
     */
    private void addActionListeners(JButton[][] buttons, ActionListener action) {
        if (buttons == null) return;
        for (JButton[] row : buttons) {
            if (row != null) {
                for (JButton button : row) {
                    if (button != null) {
                        button.addActionListener(action);
                    }
                }
            }
        }
    }




    /**
     * <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Playe Audio clips.
     * <p>
     */
    private void musicaly() {
        if (currentPlayer==player1)    {

            if (player2Clip!=null && player2Clip.isRunning()) {
                player2Clip.stop();
            }
            if (player1Clip != null) {
                player1Clip.setFramePosition(0);
                player1Clip.start();

            }
        }else{

            if (player1Clip!=null && player1Clip.isRunning()) {
                player1Clip.stop();
            }
            if (player2Clip != null) {
                player2Clip.setFramePosition(0);
                player2Clip.start();

            }
        }
    }


    /**
     * <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Loads audio Clip from path.
     *
     * @param resourcePath The file path to the audio clip.
     * @return A Clip object.
     */
    private Clip loadClip(String resourcePath) {
        try {
            InputStream audioSrc = getClass().getClassLoader().getResourceAsStream(resourcePath);
            InputStream bufferedIn = new BufferedInputStream(audioSrc);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(bufferedIn);

            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            return clip;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
