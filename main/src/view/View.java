package view;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

/**
 * The View class manages the graphical user interface (GUI) for the game.
 * It provides components to display the board, player information, and game status.
 */
public class View {

    private static final int FRAME_WIDTH = 1100;
    private static final int FRAME_HEIGHT = 700;

    private static final int CARD_BUTTON_WIDTH = 65;
    private static final int CARD_BUTTON_HEIGHT = 92;

    public JFrame mainFrame;
    public JMenuBar menuBar;
    public JLayeredPane boardPanel;
    public JPanel playerPanel1;
    public JPanel playerPanel2;
    public JLabel infoBox, Statues1, Statues2, Pawns1, Pawns2, Score1, Score2;
    public JLabel infoBox2, infoBox3;
    public JButton discardedDeck;
    public JButton[][] pathButtons;
    public JButton Fresco1, Fresco2;
    public JButton[] Hand1, Hand2, Play1, Play2;
    public JButton showPawnsButton;


    /**
     * Constructs a View that initializes GUI elements.
     */
    public View() {
        initialize();
    }

    /** <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Initializes graphical components of the view.
     */
    private void initialize() {

        int frameWidth = FRAME_WIDTH;
        int frameHeight = FRAME_HEIGHT;
        int topPanelHeight = (int) (frameHeight * 0.2);
        int boardPanelHeight = (int) (frameHeight * 0.6);
        int bottomPanelHeight = (int) (frameHeight * 0.2);

        mainFrame = new JFrame("MINOAN PALACES GAME");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setPreferredSize(new Dimension(frameWidth+25, frameHeight+80));
        mainFrame.pack();
        mainFrame.setResizable(false);
        mainFrame.setLayout(null);

        //Player1 Panel
        playerPanel1 = new JPanel(null);
        playerPanel1.setBackground(Color.WHITE);
        playerPanel1.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.RED));
        playerPanel1.setOpaque(true);
        playerPanel1.setBounds(0, 0, frameWidth, topPanelHeight);

        JPanel handPanel1 = new JPanel(null);
        handPanel1.setOpaque(false);
        handPanel1.setBounds(10, 10, (frameWidth/2)+20, topPanelHeight-20);
        playerPanel1.add(handPanel1);

        int handPanel1X = handPanel1.getX();
        int handPanel1Y = handPanel1.getY();
        int handPanel1Width = handPanel1.getWidth();
        int handPanel1Height = handPanel1.getHeight();

        JPanel playPanel1 = new JPanel(null);
        playPanel1.setOpaque(false);
        playPanel1.setBounds(handPanel1X + handPanel1Width + 38, handPanel1Y, handPanel1Width, handPanel1Height);
        playerPanel1.add(playPanel1);

        //buttons for hand of player 1
        Hand1 = new JButton[8];
        int cardButtonX = 5;
        int cardButtonY = 5;
        int cardButtonSpacing = 5;

        for (int i = 0; i < 8; i++) {
            Hand1[i] =new JButton("Card " + (i+1));
            Hand1[i].setName("Card" + (i+1));
            Hand1[i].setBounds(cardButtonX, cardButtonY, CARD_BUTTON_WIDTH, CARD_BUTTON_HEIGHT);

            handPanel1.add(Hand1[i]);
            cardButtonX+= CARD_BUTTON_WIDTH+cardButtonSpacing;
        }

        //play area of p1
        Color[] borderColors = {Color.RED, Color.YELLOW, Color.GRAY, Color.BLUE};
        String[] buttonTexts = {"KNOSSOS", "MALIA", "FAISTOS", "ZAKROS"};

        Play1 = new JButton[4];
        cardButtonX = 5;
        for (int i=0; i<4; i++) {
            Play1[i] = new JButton(buttonTexts[i]);
            Play1[i].setBounds(cardButtonX, cardButtonY, CARD_BUTTON_WIDTH, CARD_BUTTON_HEIGHT);
            Play1[i].setBackground(Color.WHITE);
            Play1[i].setOpaque(true);
            Play1[i].setBorder(BorderFactory.createLineBorder(borderColors[i], 2));
            Play1[i].setFocusPainted(false);
            playPanel1.add(Play1[i]);
            cardButtonX += CARD_BUTTON_WIDTH + cardButtonSpacing;
        }

        Fresco1 = new JButton("P1 Frescoes");
        Fresco1.setBounds(950, 50, 100, 25);
        playerPanel1.add(Fresco1);

        Statues1 = new JLabel("Player1 Statues: 0");
        Statues1.setBounds(950, 100, 100, 15);
        Statues1.setForeground(Color.BLACK);
        Statues1.setFont(new Font("Arial", Font.PLAIN, 10));
        playerPanel1.add(Statues1);

        Score1 = new JLabel("Player1 Score: 0");
        Score1.setBounds(950, 15, 100, 15);
        Score1.setForeground(Color.BLACK);
        Score1.setFont(new Font("Arial", Font.PLAIN, 10));
        playerPanel1.add(Score1);

        Pawns1 = new JLabel("Player1 available pawns: all");
        Pawns1.setBounds(12, 125, 400, 15);
        Pawns1.setForeground(Color.BLACK);
        Pawns1.setFont(new Font("Arial", Font.PLAIN, 10));
        playerPanel1.add(Pawns1);

        JPanel snakeImage1 = new JPanel() {
            private Image backgroundImage;
            {
                URL imageURL = getClass().getResource("/project_assets/images/findings/snakes.jpg");
                if (imageURL != null) {
                    backgroundImage = new ImageIcon(imageURL).getImage();
                }
            }
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage!=null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        snakeImage1.setBounds(1050, 95, 25, 25);
        playerPanel1.add(snakeImage1);

        JPanel containerPanel = new JPanel(null);
        containerPanel.setBounds(645, 102, 235, 25);
        containerPanel.setOpaque(false);

        final String[] imagePaths2 = {
                "/project_assets/images/findings/ring.jpg",
                "/project_assets/images/findings/kosmima.jpg",
                "/project_assets/images/findings/diskos.jpg",
                "/project_assets/images/findings/ruto.jpg"
        };

        int panelWidth = 25;
        int panelHeight = 25;
        int spacing = 45;

        for (int i = 0; i < 4; i++) {
            int index= i;

            JPanel imagePanel3 = new JPanel() {
                private Image backgroundImage;
                {
                    URL imageURL = getClass().getResource(imagePaths2[index]);
                    if (imageURL != null) {
                        backgroundImage = new ImageIcon(imageURL).getImage();
                    }
                }
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (backgroundImage != null) {
                        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                    }
                    // Gray overlay
                    Graphics2D g2d = (Graphics2D) g.create();
                    g2d.setColor(new Color(128, 128, 128, 128));
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                    g2d.dispose();
                }
            };
            int x= (panelWidth+spacing)*i;
            imagePanel3.setBounds(x, 0, panelWidth, panelHeight);
            imagePanel3.setLayout(null);
            containerPanel.add(imagePanel3);
        }
        playerPanel1.add(containerPanel);

        mainFrame.add(playerPanel1);

        //main board panel
        boardPanel = new JLayeredPane() {
            Image bgImage;
            {
                URL imageURL = getClass().getResource("/project_assets/images/background.jpg");
                if (imageURL != null) {
                    bgImage = new ImageIcon(imageURL).getImage();
                }
            }
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (bgImage != null) {
                    g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        boardPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY));
        boardPanel.setOpaque(false);
        boardPanel.setLayout(null);
        boardPanel.setBounds(0, topPanelHeight, frameWidth, boardPanelHeight);


        showPawnsButton = new JButton("Pawns");
        showPawnsButton.setBounds(106, 130, 100, 25);
        boardPanel.add(showPawnsButton);


        String[][] imagePaths = {
                {"/project_assets/images/paths/knossos.jpg", "/project_assets/images/paths/knossosPalace.jpg", "/project_assets/images/paths/knossos2.jpg"},
                {"/project_assets/images/paths/malia.jpg", "/project_assets/images/paths/maliaPalace.jpg", "/project_assets/images/paths/malia2.jpg"},
                {"/project_assets/images/paths/phaistos.jpg", "/project_assets/images/paths/phaistosPalace.jpg", "/project_assets/images/paths/phaistos2.jpg"},
                {"/project_assets/images/paths/zakros.jpg", "/project_assets/images/paths/zakrosPalace.jpg", "/project_assets/images/paths/zakros2.jpg"}
        };



        int buttonSize = 80;
        int buttonSpacing = 5;
        int startX = 300;
        int startY = 50;

        pathButtons = new JButton[4][9];
        for (int i=0; i<4; i++) {
            for (int j=0; j<9; j++) {
                pathButtons[i][j] = new JButton();
                pathButtons[i][j].setName("path: " + (i + 1) + "," + (j + 1));
                pathButtons[i][j].setPreferredSize(new Dimension(buttonSize, buttonSize));

                String imagePath;
                if (j==1 || j==3 || j==5 || j==7) {
                    imagePath = imagePaths[i][2];
                } else {
                    imagePath = (j< 8) ? imagePaths[i][0] : imagePaths[i][1];
                }

                URL iconURL = getClass().getResource(imagePath);
                if (iconURL != null) {
                    ImageIcon icon = new ImageIcon(iconURL);
                    Image scaledImg = icon.getImage().getScaledInstance(buttonSize, buttonSize, Image.SCALE_SMOOTH);
                    pathButtons[i][j].setIcon(new ImageIcon(scaledImg));
                } else {
                    pathButtons[i][j].setText("X");
                }

                int x = startX + j * (buttonSize+buttonSpacing);
                int y = startY + i* (buttonSize+buttonSpacing);
                pathButtons[i][j].setBounds(x, y, buttonSize, buttonSize);


                boardPanel.add(pathButtons[i][j], JLayeredPane.DEFAULT_LAYER);
            }
        }


        discardedDeck = new JButton();
        discardedDeck.setBounds(125, 175, CARD_BUTTON_WIDTH, CARD_BUTTON_HEIGHT);

        String deckImagePath = "/project_assets/images/cards/backCard.jpg";
        URL deckIconURL = getClass().getResource(deckImagePath);
        if (deckIconURL != null) {
            ImageIcon icon = new ImageIcon(deckIconURL);
            Image scaledImg = icon.getImage().getScaledInstance(CARD_BUTTON_WIDTH, CARD_BUTTON_HEIGHT, Image.SCALE_SMOOTH);
            discardedDeck.setIcon(new ImageIcon(scaledImg));
        }
        boardPanel.add(discardedDeck, JLayeredPane.PALETTE_LAYER);

        infoBox = new JLabel("Game Info", SwingConstants.CENTER);
        infoBox.setPreferredSize(new Dimension(100, 25));
        infoBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        infoBox.setOpaque(true);
        infoBox.setBackground(Color.LIGHT_GRAY);
        infoBox.setForeground(Color.BLACK);
        infoBox.setBounds(75, 275, 150, 25);
        boardPanel.add(infoBox, JLayeredPane.PALETTE_LAYER);

        infoBox2 = new JLabel("Game Info", SwingConstants.CENTER);
        infoBox2.setPreferredSize(new Dimension(100, 25));
        infoBox2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        infoBox2.setOpaque(true);
        infoBox2.setBackground(Color.LIGHT_GRAY);
        infoBox2.setForeground(Color.BLACK);
        infoBox2.setBounds(75, 300, 150, 25);
        boardPanel.add(infoBox2, JLayeredPane.PALETTE_LAYER);
        infoBox2.setText("Pawns on Checkpoints: 0");

        infoBox3 = new JLabel("Game Info", SwingConstants.CENTER);
        infoBox3.setPreferredSize(new Dimension(100, 25));
        infoBox3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        infoBox3.setOpaque(true);
        infoBox3.setBackground(Color.LIGHT_GRAY);
        infoBox3.setForeground(Color.BLACK);
        infoBox3.setBounds(75, 325, 150, 25);
        boardPanel.add(infoBox3, JLayeredPane.PALETTE_LAYER);
        infoBox3.setText("Cards left: 84");

        JLabel label = new JLabel("-20 POINTS     -15 POINTS     -10 POINTS     5 POINTS     10 POINTS     15 POINTS     30 POINTS     35 POINTS     50 POINTS");
        label.setBounds(300, 10, 900, 15);
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Arial", Font.PLAIN, 13));
        JLabel label2 = new JLabel("                                                                                                                              CHECKPOINT!");
        label2.setBounds(300, 28, 900, 15);
        label2.setForeground(Color.BLACK);
        label2.setFont(new Font("Arial", Font.PLAIN, 13));

        boardPanel.add(label, JLayeredPane.DRAG_LAYER);
        boardPanel.add(label2, JLayeredPane.DRAG_LAYER);

        mainFrame.add(boardPanel);

        //Player Panel 2
        playerPanel2 = new JPanel(null);
        playerPanel2.setBackground(Color.WHITE);
        playerPanel2.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GREEN));
        playerPanel2.setOpaque(true);

        playerPanel2.setBounds(0, topPanelHeight + boardPanelHeight, frameWidth, bottomPanelHeight);

        JPanel handPanel2 = new JPanel(null);
        handPanel2.setOpaque(false);

        handPanel2.setBounds(10, 10, (frameWidth / 2) + 20, bottomPanelHeight - 20);
        playerPanel2.add(handPanel2);

        int handPanel2X = handPanel2.getX();
        int handPanel2Y = handPanel2.getY();
        int handPanel2Width = handPanel2.getWidth();
        int handPanel2Height = handPanel2.getHeight();

        JPanel playPanel2 = new JPanel(null);
        playPanel2.setOpaque(false);
        playPanel2.setBounds(handPanel2X + handPanel2Width + 38, handPanel2Y, handPanel2Width, handPanel2Height);
        playerPanel2.add(playPanel2);

        Hand2 = new JButton[8];
        cardButtonX = 5;
        cardButtonY = 5;
        cardButtonSpacing = 5;

        for (int i = 0; i < 8; i++) {
            Hand2[i] = new JButton("Card " + (i+1));
            Hand2[i].setName("Card" + (i+1));
            Hand2[i].setBounds(cardButtonX, cardButtonY, CARD_BUTTON_WIDTH, CARD_BUTTON_HEIGHT);
            handPanel2.add(Hand2[i]);
            cardButtonX += CARD_BUTTON_WIDTH + cardButtonSpacing;
        }

        Play2 = new JButton[4];
        cardButtonX = 5;
        for (int i = 0; i < 4; i++) {
            Play2[i] = new JButton(buttonTexts[i]);
            Play2[i].setBounds(cardButtonX, cardButtonY, CARD_BUTTON_WIDTH, CARD_BUTTON_HEIGHT);
            Play2[i].setBackground(Color.WHITE);
            Play2[i].setOpaque(true);
            Play2[i].setBorder(BorderFactory.createLineBorder(borderColors[i], 2));
            Play2[i].setFocusPainted(false);
            playPanel2.add(Play2[i]);
            cardButtonX += CARD_BUTTON_WIDTH + cardButtonSpacing;
        }

        Fresco2 = new JButton("P2 Frescoes");
        Fresco2.setBounds(950, 50, 100, 25);
        playerPanel2.add(Fresco2);

        Statues2 = new JLabel("Player2 Statues: 0");
        Statues2.setBounds(950, 100, 100, 15);
        Statues2.setForeground(Color.BLACK);
        Statues2.setFont(new Font("Arial", Font.PLAIN, 10));
        playerPanel2.add(Statues2);

        Score2 = new JLabel("Player2 Score: 0");
        Score2.setBounds(950, 15, 100, 15);
        Score2.setForeground(Color.BLACK);
        Score2.setFont(new Font("Arial", Font.PLAIN, 10));
        playerPanel2.add(Score2);

        Pawns2 = new JLabel("Player1 available pawns: all");
        Pawns2.setBounds(12, 125, 400, 15);
        Pawns2.setForeground(Color.BLACK);
        Pawns2.setFont(new Font("Arial", Font.PLAIN, 10));
        playerPanel2.add(Pawns2);

        JPanel snakeImage2 = new JPanel() {
            private Image backgroundImage;
            {
                URL imageURL = getClass().getResource("/project_assets/images/findings/snakes.jpg");
                if (imageURL != null) {
                    backgroundImage = new ImageIcon(imageURL).getImage();
                }
            }
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        snakeImage2.setBounds(450, 90, 25, 25);
        playPanel2.add(snakeImage2);

        JPanel containerPanel2 = new JPanel(null);
        containerPanel2.setBounds(645, 102, 235, 25);
        containerPanel2.setOpaque(false);

        final String[] imagePaths3 = {
                "/project_assets/images/findings/ring.jpg",
                "/project_assets/images/findings/kosmima.jpg",
                "/project_assets/images/findings/diskos.jpg",
                "/project_assets/images/findings/ruto.jpg"
        };

        panelWidth = 25;
        panelHeight = 25;
        spacing = 45;

        for (int i = 0; i < 4; i++) {
            int index = i;
            JPanel imagePanel4 = new JPanel() {
                private Image backgroundImage;
                {
                    URL imageURL = getClass().getResource(imagePaths3[index]);
                    if (imageURL != null) {
                        backgroundImage = new ImageIcon(imageURL).getImage();
                    }
                }
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (backgroundImage != null) {
                        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                    }
                    Graphics2D g2d = (Graphics2D) g.create();
                    g2d.setColor(new Color(128, 128, 128, 128));
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                    g2d.dispose();
                }
            };
            int x = (panelWidth + spacing) * i;
            imagePanel4.setBounds(x, 0, panelWidth, panelHeight);
            imagePanel4.setLayout(null);
            containerPanel2.add(imagePanel4);
        }
        playerPanel2.add(containerPanel2);

        mainFrame.add(playerPanel2);

        //menu (shhhhh it does nothing)
        menuBar = new JMenuBar();
        JMenuItem newGame = new JMenuItem("New Game");
        JMenuItem saveGame = new JMenuItem("Save Game");
        JMenuItem continueGame = new JMenuItem("Continue Saved Game");
        JMenuItem exitGame = new JMenuItem("Exit Game");
        menuBar.add(newGame);
        menuBar.add(saveGame);
        menuBar.add(continueGame);
        menuBar.add(exitGame);
        mainFrame.setJMenuBar(menuBar);

        mainFrame.setVisible(true);
    }

    /** <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Renders the board and updates all its components to match the current game state.
     *
     * @param board The board object representing the game's state.
     */
    public void render(Board board) {
        //doesnt get used, initialize() does all the lifting
    }

    /** <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Updates the view to reflect changes in the specified player's state.
     *
     * @param player The player whose state has changed.
     */
    public void updatePlayer(Player player) {
        //doesnt get used, i update things on player panels more manually
        //like updateHands(), updateScore()... etc
    }

    /** <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Updates the graphical display of the player's hand.
     *
     * @param player The player whose hand is to be updated.
     */
    public void updateHands(Player player) {
        JPanel playerPanel;
        JButton[] handButtons;

        if (player.getPlayerName().equals("player1")) {
            playerPanel = playerPanel1;
            handButtons = Hand1;
        } else if (player.getPlayerName().equals("player2")) {
            playerPanel = playerPanel2;
            handButtons = Hand2;
        } else {
            return;
        }

        JPanel handPanel = (JPanel) playerPanel.getComponent(0);
        ArrayList<Card> hand = player.getHand();

        for (int i=0; i<handButtons.length; i++) {
            JButton button = handButtons[i];

            if (i<hand.size()) {

                Card card = hand.get(i);
                String imagePath = card.getImagePath();

                button.putClientProperty("card", card); //associate card with its button

                if (imagePath!=null && !imagePath.isEmpty()) {

                    URL imageUrl = getClass().getResource(imagePath);

                    if (imageUrl!=null) {

                        ImageIcon cardIcon = new ImageIcon(imageUrl);
                        int buttonWidth = CARD_BUTTON_WIDTH;
                        int buttonHeight = CARD_BUTTON_HEIGHT;
                        int imgWidth = cardIcon.getIconWidth();
                        int imgHeight = cardIcon.getIconHeight();


                        double widthScale = (double) buttonWidth/imgWidth;
                        double heightScale = (double) buttonHeight/imgHeight;
                        double scale = Math.min(widthScale, heightScale);


                        int newWidth = (int) (imgWidth * scale);
                        int newHeight = (int) (imgHeight * scale);

                        Image image = cardIcon.getImage();
                        Image scaledImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                        cardIcon = new ImageIcon(scaledImage);

                        button.setIcon(cardIcon);
                        button.setText("");
                    }
                }
                button.setVisible(true);
            } else {
                button.setIcon(null);
                button.setText("");
                button.setVisible(false);
                button.putClientProperty("card", null); //clear any previous card property
            }
        }

        handPanel.revalidate();
        handPanel.repaint();
    }

    /** <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Updates the display of a pawn's position on the board.
     *
     * @param pawn The pawn whose position has changed.
     */
    public void updatePawn(Pawn pawn) {
        //did not do pawn graphics, press the Pawns button to see where pawns at ;)
    }

    /** <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Updates the display of a player's score on the board.
     *
     * @param player The player whose score has changed.
     */
    public void updateScore(Player player) {
        if (player.getPlayerName().equals("player1")) {
            Score1.setText("Player1 Score: " + player.getPlayerScore());
        } else if (player.getPlayerName().equals("player2")) {
            Score2.setText("Player2 Score: " + player.getPlayerScore());
        }
    }

    /** <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Updates the display of a player's statues amount on the board.
     *
     * @param player The player whose statues amount has changed.
     */
    public void updateStatues(Player player) {
        if (player.getPlayerName().equals("player1")) {
            Statues1.setText("Player1 Statues: " + player.getSnakeGoddessStatues());
        } else if (player.getPlayerName().equals("player2")) {
            Statues2.setText("Player2 Statues: " + player.getSnakeGoddessStatues());
        }
    }


    /**
     * <b>Observer</b>
     * <p>
     * <b>Postcondition:</b> Makes a new window showing all frescoes photographed by this player.
     *
     * @param player The player whose frescoes are displaying in the window.
     */
    public void frescoDisplay(Player player) {

        JFrame frescoFrame = new JFrame(player.getPlayerName()+ " Frescoes");
        frescoFrame.setSize(600, 400);
        frescoFrame.setLocationRelativeTo(mainFrame);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JScrollPane scrollPane = new JScrollPane(panel);
        frescoFrame.add(scrollPane);

        ArrayList<Fresco> frescoes = player.getFrescos();
        //get player's frescoes
        if (frescoes.isEmpty()) {
            JLabel label = new JLabel("No Frescoes photographed yet.");
            label.setHorizontalAlignment(SwingConstants.CENTER);
            panel.add(label);

        } else {
            for (Fresco fresco : frescoes) {
                JLabel frescoLabel = new JLabel();
                String imagePath = fresco.getImagePath();

                ImageIcon frescoIcon = null;
                if (imagePath != null && !imagePath.isEmpty()) {
                    URL imageUrl = getClass().getResource(imagePath);
                    if (imageUrl != null) {
                        frescoIcon = new ImageIcon(imageUrl);
                    }
                }

                //scale and set the image icon
                if (frescoIcon != null) {
                    Image scaledImg = frescoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                    frescoLabel.setIcon(new ImageIcon(scaledImg));
                }

                frescoLabel.setToolTipText("Worth: " + fresco.getPointValue() + " points");
                panel.add(frescoLabel);
            }
        }

        frescoFrame.setVisible(true);
    }

    /** <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Updates the display of the discard deck.
     *
     * @param discardedPile The discard pile
     */
    public void updateDiscardDeck(ArrayList<Card> discardedPile) {
        if (!discardedPile.isEmpty()) {
            Card lastDiscarded = discardedPile.get(discardedPile.size() - 1);
            String imagePath = lastDiscarded.getImagePath();

            if (imagePath != null && !imagePath.isEmpty()) {
                URL imageUrl = getClass().getResource(imagePath);
                if (imageUrl != null) {
                    ImageIcon cardIcon = new ImageIcon(imageUrl);
                    Image scaledImg = cardIcon.getImage().getScaledInstance(
                            discardedDeck.getWidth(), discardedDeck.getHeight(), Image.SCALE_SMOOTH);
                            discardedDeck.setIcon(new ImageIcon(scaledImg));
                            discardedDeck.setText("");
                } else {
                    discardedDeck.setIcon(null);
                    discardedDeck.setText("Discarded Cards");
                }
            }
        } else {
            discardedDeck.setIcon(null);
            discardedDeck.setText("Discarded Cards");
        }
    }

    /** <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Updates the information box with a message.
     *
     * @param message The message to display in the information box.
     */
    public void updateInfo(String message) {
        if (infoBox != null) {
            infoBox.setText(message);
            infoBox.revalidate();
            infoBox.repaint();
        }
    }

    /** <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Updates the information box with a message.
     *
     * @param message The message to display in the information box.
     */
    public void updateInfo2(String message) {
        if (infoBox2 != null) {
            infoBox2.setText(message);
            infoBox2.revalidate();
            infoBox2.repaint();
        }
    }

    /** <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Updates the information box with a message.
     *
     * @param message The message to display in the information box.
     */
    public void updateInfo3(String message) {
        if (infoBox3 != null) {
            infoBox3.setText(message);
            infoBox3.revalidate();
            infoBox3.repaint();
        }
    }

    /**
     * <b>Observer</b>
     * <p>
     * <b>Postcondition:</b> Makes tabel of all pawns on the board currently , with theiur owners, types, paths, positions
     *
     * @param pawns The list of pawns to show.
     */

    public void showPawns(ArrayList<Pawn> pawns) {
        if (pawns.isEmpty()) {
            JOptionPane.showMessageDialog(mainFrame, "No pawns are currently on any paths.", "Pawns on Paths", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        //names
        String[] columnNames = {"Player", "Pawn Type", "Path", "Position"};

        //get data
        String[][] data = new String[pawns.size()][4];

        for (int i=0; i<pawns.size(); i++) {
            Pawn p = pawns.get(i);

            Path path = p.getThisPawnPath();
            Palace palace = (path != null) ? path.getPalace() : null;

            data[i][0] = p.getThisPawnOwner().getPlayerName();
            data[i][1] = p.getClass().getSimpleName();
            data[i][2] = (palace!=null) ? palace.getName().toString() : "error";
            data[i][3] = (p.getThisPawnPosition() != null) ? String.valueOf(p.getThisPawnPosition().getIndex()) : "error";
        }


        //create table
        JTable table = new JTable(data, columnNames);
        table.setEnabled(false); // Make the table read-only
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);

        JDialog dialog = new JDialog(mainFrame, "Pawns on Paths", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(mainFrame);
        dialog.add(scrollPane);
        dialog.setVisible(true);
    }

    /**
     * <b>Accessor</b>
     * <p>
     * <b>Postcondition:</b> Retrieves the card associated with the specified button in the player's hand.
     *
     * @param cardButton The button representing the selected card.
     * @return The card associated with the button, or null if no card is found.
     */
    public Card getSelectedCard(JButton cardButton) {
        return (Card) cardButton.getClientProperty("card");
    }

    /**
     * <b>Observer</b>
     * <p>
     * <b>Postcondition:</b> Prompts the player to choose a pawn to place on a path.
     *
     * @param pawnOptions The array of pawn options available for placement.
     * @return The index of the selected pawn, or -1 if no selection is made.
     */
    public int placePawn(String[] pawnOptions) {
        return JOptionPane.showOptionDialog(
                null,
                "Choose a pawn to place on this path:",
                "Place Pawn",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                pawnOptions,
                pawnOptions[0]
        );
    }

    /** <b>Transformer(mutative)</b>
     * <p>
     * <b>Postcondition:</b> Updates the display of rare findings taken/destroyed by a pawn.
     *
     * @param pawn The pawn that has discovered a rare finding.
     */
    public void updateRareFinding(Pawn pawn) {
        //i dont even know wat i was thinking this would do...
    }

    /**
     * <b>Accessor</b>
     * <p>
     * <b>Postcondition:</b> Retrieves the buttons representing player 1's hand.
     *
     * @return An array of buttons for player 1's hand.
     */

    public JButton[] getHand1() {
        return Hand1;
    }

    /**
     * <b>Accessor</b>
     * <p>
     * <b>Postcondition:</b> Retrieves the buttons representing player 2's hand.
     *
     * @return An array of buttons for player 2's hand.
     */
    public JButton[] getHand2() {
        return Hand2;
    }

    /**
     * <b>Accessor</b>
     * <p>
     * <b>Postcondition:</b> Retrieves the buttons representing player 1's play area.
     *
     * @return An array of buttons for player 1's play area.
     */
    public JButton[] getPlay1() {
        return Play1;
    }

    /**
     * <b>Accessor</b>
     * <p>
     * <b>Postcondition:</b> Retrieves the buttons representing player 2's play area.
     *
     * @return An array of buttons for player 2's play area.
     */
    public JButton[] getPlay2() {
        return Play2;
    }

    /**
     * <b>Accessor</b>
     * <p>
     * <b>Postcondition:</b> Retrieves the button for the discard deck.
     *
     * @return The button representing the discard deck.
     */
    public JButton getDiscardedDeck() {
        return discardedDeck;
    }

    /**
     * <b>Accessor</b>
     * <p>
     * <b>Postcondition:</b> Retrieves the button for player 1's frescoes.
     *
     * @return The button representing player 1's frescoes.
     */
    public JButton getFresco1() {
        return Fresco1;
    }

    /**
     * <b>Accessor</b>
     * <p>
     * <b>Postcondition:</b> Retrieves the button for player 2's frescoes.
     *
     * @return The button representing player 2's frescoes.
     */
    public JButton getFresco2() {
        return Fresco2;
    }

    /**
     * <b>Accessor</b>
     * <p>
     * <b>Postcondition:</b> Retrieves the 2D array of buttons representing the paths on the board.
     *
     * @return A 2D array of buttons for the paths.
     */
    public JButton[][] getPathButtons() {
        return pathButtons;
    }
}
