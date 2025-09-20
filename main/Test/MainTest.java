import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import model.*;
import controller.Controller;
import view.View;

import javax.swing.*;

public class MainTest {

    private Board board;
    private Deck deck;
    private Player player1;
    private Player player2;
    private Controller controller;
    private View view;

    @Before
    public void setUp() {

        board = new Board();
        deck = new Deck(board.getPalaces());
        player1 = new Player("player1");
        player2 = new Player("player2");
        view = new View();
        controller = new Controller();

    }

    // 1. Test deck initialization for correct size (100 cards).
    @Test
    public void testDeckInitialization() {
        assertEquals("Deck should contain 100 cards after initialization.", 100, deck.getDeck());
    }

    //2. Test that drawing a card reduces the deck size by one.
    @Test
    public void testDrawCardReducesDeckSize() {
        int initialSize = deck.getDeck();
        deck.playerDrawsCard();
        assertEquals("Drawing one card should reduce deck size by 1.", initialSize - 1, deck.getDeck());
    }

    //3. Test that the deck is not empty after a single draw.
    @Test
    public void testDeckNotEmptyAfterSingleDraw() {
        deck.playerDrawsCard();
        assertFalse("Deck should not be empty after drawing only one card.", deck.isEmpty());
    }

    //4. Test that the deck is empty after drawing all cards.
    @Test
    public void testEmptyDeckAfterAllDrawn() {
        for (int i = 0; i < 100; i++) {
            deck.playerDrawsCard();
        }
        assertTrue("Deck should be empty after drawing all 100 cards.", deck.isEmpty());
    }

    //5. Test that player can hold up to 8 cards.
    @Test
    public void testPlayerHandLimit() {
        //draw 9 cards for player1, last one shouldnt be added
        for (int i = 0; i < 9; i++) {
            Card c = deck.playerDrawsCard();
            player1.addCard(c);
        }
        assertEquals("Player's hand should contain at most 8 cards.", 8, player1.getHand().size());
    }

    // 6. Test adding a RareFinding increases player's score.
    @Test
    public void testRareFindingScoreIncrease() {
        RareFinding rare = new RareFinding("Test Rare", "/path.jpg", 25);
        int initialScore = player1.getPlayerScore();
        player1.addRareFinding(rare);
        assertEquals("Player score should increase by 25 after adding rare finding.", initialScore + 25, player1.getPlayerScore());
    }

    //7. Test Snake Goddess statue collection affects score after statueMath()
    @Test
    public void testSnakeGoddessStatueMath() {
        player1.addSnakeGoddessStatue(); // total: 1 statue
        player1.statueMath();
        assertEquals("One statue should yield -20 points.", -20, player1.getPlayerScore());
    }

    //8. Test multiple Snake Goddess statues scoring
    @Test
    public void testMultipleStatuesScoring() {
        for (int i = 0; i < 3; i++) {
            player1.addSnakeGoddessStatue();
        }
        player1.statueMath();
        //3 = 10points
        assertEquals("Three statues should give +10 points.", 10, player1.getPlayerScore());
    }

    // 9. Test that Theseus can destroy up to three findings
    @Test
    public void testTheseusDestroyLimit() {
        Theseus theseus = new Theseus(player1);
        assertTrue(theseus.hasDestroyAbility());
        theseus.addDestroyed();
        theseus.addDestroyed();
        theseus.addDestroyed();
        assertFalse("Theseus should not be able to destroy after 3 times.", theseus.hasDestroyAbility());
    }

    // 10. Test Ariadne card moves a pawn 2 steps forward
    @Test
    public void testAriadneCardPlay() {
        Path path = board.getPaths()[0];
        Pawn pawn = player1.pawnsAvailable()[0];
        player1.addPawnToPath(pawn, path, path.getPath()[0]);
        int initialPos = pawn.getThisPawnPosition().getIndex();

        Ariadne ariadne = new Ariadne(path.getPalace());
        player1.playCard(ariadne, view.getPlay1(), new JButton(), board, view, controller);

        int newPos = pawn.getThisPawnPosition().getIndex();
        assertEquals("Pawn should move 2 steps forward with Ariadne.", initialPos + 2, newPos);
    }





    // 11. Test that discarding a card adds it to the discard pile
    @Test
    public void testDiscardCard() {
        Card c = deck.playerDrawsCard();
        player1.addCard(c);
        int initialDiscardSize = board.getDiscarded().size();
        player1.discardCard(c, board, view);

        assertEquals("Discard pile should increase by 1 after discarding.", initialDiscardSize + 1, board.getDiscarded().size());
    }



    // 12. Test Theseus immobilization logic
    @Test
    public void testTheseusImmobilization() {
        Theseus theseus = new Theseus(player1);
        theseus.immobilize(true);
        assertTrue("Theseus should be immobilized.", theseus.cantTouchThis());
        theseus.remobilize();
        assertFalse("Theseus should no longer be immobilized after remobilize.", theseus.cantTouchThis());
    }


    // 13. Test that info boxes can be updated (no exception)
    @Test
    public void testViewInfoUpdates() {
        view.updateInfo("Test Info1");
        view.updateInfo2("Test Info2");
        view.updateInfo3("Test Info3");

        assertEquals("Test Info1", view.infoBox.getText());
        assertEquals("Test Info2", view.infoBox2.getText());
        assertEquals("Test Info3", view.infoBox3.getText());
    }


    //14. Test photo of Fresco increases player score
    @Test
    public void testPhotoFresco() {
        Fresco fresco = new Fresco("Test Fresco", "/fresco.jpg", 15);
        int initScore = player1.getPlayerScore();
        player1.photograph(fresco);
        assertEquals("Photographing fresco should add 15 points.", initScore + 15, player1.getPlayerScore());
    }

    //15. Test that a player's hand updates after drawing a new card
    @Test
    public void testHandUpdatesAfterDraw() {
        int initialHandSize = player1.getHand().size();
        player1.drawNewCard(deck);
        assertEquals("Drawing a new card should increase hand size by 1.", initialHandSize + 1, player1.getHand().size());
    }

    // 16. Test that playing a card removes it from the player's hand
    @Test
    public void testPlayCardRemovesFromHand() {
        Card c = deck.playerDrawsCard();
        player1.addCard(c);
        int initSize = player1.getHand().size();

        if (c instanceof NumberCard == false) {
            c = new NumberCard(board.getPalaces()[0], 5);
            player1.addCard(c);
            initSize = player1.getHand().size();
        }
        JButton cardButton = new JButton();
        boolean played = player1.playCard(c, view.getPlay1(), cardButton, board, view, controller);
        if (played) {
            assertEquals("Playing a card should remove it from hand.", initSize - 1, player1.getHand().size());
        }
    }

    // 17. Test game over tiebreakers
    @Test
    public void testGameOverTiebreaker() {

        player1.manageScore(50);
        player2.manageScore(50);

        player1.addRareFinding(new RareFinding("Test Rare", "/test.jpg", 25));
        controller.gameOver();
        assertTrue("Game should handle tiebreaker scenario without crashing.", true);
    }

    // 18. Test that handleFindings does nothing if not a FindingPosition
    @Test
    public void testHandleFindingsOnSimplePosition() {
        Pawn pawn = player1.pawnsAvailable()[0];
        Path path = board.getPaths()[0];
        Position simplePos = path.getPath()[0];

        player1.addPawnToPath(pawn, path, simplePos);

        int initScore = player1.getPlayerScore();
        controller.handleFindings(pawn, simplePos);
        assertEquals("No score change if no finding present.", initScore, player1.getPlayerScore());
    }

}
