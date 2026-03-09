package GUI;

import game.Battlefield;
import game.Game;
import game.Player;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.Border;
import piece.SiegeMachines;
import piece.Troops;
import piece.Units;

public class BattleGroundGUI {

    private Battlefield battlefield;

    private String general1, general2;
    private Player player1, player2;

    private JFrame game;

    private JPanel selectedCell = null;
    private JPanel gameGrid;
    private JPanel leftSide;

    private JLabel unitName, unitHp, unitStamina, unitRange, unitAtk, unitMov, unitStandby, activePlayer, playerAPLabel;

    public BattleGroundGUI(Battlefield battlefield, String general1, String general2, Player player1, Player player2, Game controller) {
        this.battlefield = battlefield;
        this.general1 = general1;
        this.general2 = general2;
        this.player1 = player1;
        this.player2 = player2;
        initializeBgGUI(battlefield, controller);
    }

    //funzione che importa le icone
    private Map<String, ImageIcon> imagesUpload() {
        Map<String, ImageIcon> images = new HashMap<>();
        int iconsSize = 85;
        int unitsSize = 60;

        //assegno chiavi alle immagini e richiamo resizeIcon
        images.put("recharge", resizeIcon("img/icons/recharge.png", iconsSize, iconsSize));
        images.put("attack", resizeIcon("img/icons/attack.png", iconsSize, iconsSize));
        images.put("move", resizeIcon("img/icons/move.png", iconsSize, iconsSize));
        images.put("hourglass", resizeIcon("img/icons/hourglass.png", iconsSize, iconsSize));

        images.put("archer", resizeIcon("img/troops/archer.png", unitsSize, unitsSize));
        images.put("armored", resizeIcon("img/troops/armored.png", unitsSize, unitsSize));
        images.put("cavalry", resizeIcon("img/troops/cavalry.png", unitsSize, unitsSize));
        images.put("engineer", resizeIcon("img/troops/engineer.png", unitsSize, unitsSize));
        images.put("infantry", resizeIcon("img/troops/infantry.png", unitsSize, unitsSize));

        images.put("suntzu", resizeIcon("img/generals/sunTzu.png", unitsSize, unitsSize));
        images.put("nobunagaoda", resizeIcon("img/generals/nobunagaOda.png", unitsSize, unitsSize));
        images.put("kingarthur", resizeIcon("img/generals/kingArthur.png", unitsSize, unitsSize));
        images.put("juliuscaesar", resizeIcon("img/generals/juliusCaesar.png", unitsSize, unitsSize));
        images.put("leonida", resizeIcon("img/generals/leonidas.png", unitsSize, unitsSize));
        images.put("ragnar", resizeIcon("img/generals/ragnar.png", unitsSize, unitsSize));
        images.put("ghandi", resizeIcon("img/generals/ghandi.png", unitsSize, unitsSize));

        images.put("ballista", resizeIcon("img/siege/ballista.png", unitsSize, unitsSize));
        images.put("cannon", resizeIcon("img/siege/cannon.png", unitsSize, unitsSize));
        images.put("catapult", resizeIcon("img/siege/catapult.png", unitsSize, unitsSize));

        images.put("berserker", resizeIcon("img/specials/berserker.png", unitsSize, unitsSize));
        images.put("gladiator", resizeIcon("img/specials/gladiator.png", unitsSize, unitsSize));
        images.put("knight", resizeIcon("img/specials/knight.png", unitsSize, unitsSize));
        images.put("musketeer", resizeIcon("img/specials/musketeer.png", unitsSize, unitsSize));
        images.put("samurai", resizeIcon("img/specials/samurai.png", unitsSize, unitsSize));
        images.put("spartan", resizeIcon("img/specials/spartan.png", unitsSize, unitsSize));

        return images;
    }

    //ridimensiono le icone in una funzione apposita invece di farlo per ogni immagine
    private ImageIcon resizeIcon(String path, int width, int height) {
        ImageIcon original = new ImageIcon(path);
        Image scaled = original.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }

    //funzione che carica e aggiorna la scacchiera
    private JPanel uploadBG(Battlefield battlefield, int cellSize, Game controller) {
        //scacchiera
        JPanel gameGrid = new JPanel();
        gameGrid.setLayout(new GridLayout(9, 15));
        //bordi scacchiera
        Border gridBorder = BorderFactory.createLineBorder(Color.BLACK, 3); //colore,spessore
        Border cellBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
        //dimensione celle
        Dimension cellDimension = new Dimension(cellSize, cellSize);
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 15; x++) {
                JPanel bgCell = new JPanel();
                bgCell.setPreferredSize(cellDimension);
                bgCell.setBorder(cellBorder);
                Color baseGreen = Color.decode("#92D050"); //colore delle caselle considerate basi
                if (x <= 2 || x >= 12) {
                    bgCell.setBackground(baseGreen);
                } else {
                    bgCell.setBackground(Color.WHITE);
                }

                // carica icona giusta in base al nome dell'unità presente in quella cella
                Units currentUnit = battlefield.getUnit(x, y);
                String currentUnitName = null;

                if (currentUnit != null) {
                    currentUnitName = currentUnit.getClass().getSimpleName().toLowerCase();
                }

                ImageIcon unitIcon = getCurrentIcon(currentUnitName);
                JLabel unitLabel = new JLabel(unitIcon);
                if (currentUnit != null && currentUnit.isHost()) {
                    unitLabel.setOpaque(true);
                    unitLabel.setBackground(Color.BLUE);
                } else {
                    unitLabel.setOpaque(true);
                    unitLabel.setBackground(Color.RED);
                }
                bgCell.add(unitLabel);

                bgCell.putClientProperty("y", y);
                bgCell.putClientProperty("x", x);

                bgCell.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        JPanel cell = (JPanel) e.getSource();

                        //ripristino il bordo della cella selezionata in precedenza
                        if (selectedCell != null) {
                            selectedCell.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                        }

                        //evidenzio la cella selezionata
                        cell.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                        selectedCell = cell;

                        int cx = (int) cell.getClientProperty("x");
                        int cy = (int) cell.getClientProperty("y");

                        System.out.println("cell: X=" + cx + " Y=" + cy);

                        if (battlefield.getUnit(cx, cy) == null) {
                            System.out.println("No unit");
                        } else {
                            System.out.println("Unit: " + battlefield.getUnit(cx, cy).getClass().getSimpleName().toLowerCase());
                        }

                    }
                });

                gameGrid.add(bgCell);

                bgCell.addMouseListener(controller);
            }
        }

        gameGrid.setBorder(gridBorder);
        return gameGrid;
    }

    private final Map<String, ImageIcon> images = imagesUpload();

    private ImageIcon getCurrentIcon(String currentUnit) {
        return images.get(currentUnit);
    }

    private void initializeBgGUI(Battlefield battlefield, Game controller) {

        //do nomi alle icone dei bottoni
        ImageIcon rechargeIcon = images.get("recharge");
        ImageIcon attackIcon = images.get("attack");
        ImageIcon moveIcon = images.get("move");
        ImageIcon turnIcon = images.get("hourglass");

        game = new JFrame("Battle Ground"); //tutta la finestra
        game.setLayout(new BorderLayout());

        int cellSize = 70;
        gameGrid = uploadBG(battlefield, cellSize, controller);

        //importazione statistiche giocatore e truppe
        //W.I.P.

        /* 
        PlayerTest playerStats = new PlayerTest();
        int playerAP = playerStats.getApDone();
        //questa viene stampata nella label playerAPLabel qui sotto
         */

 /* BOTTONI */
        int actionButtonsSize = 50;
        Dimension actionButtonsDimension = new Dimension(actionButtonsSize, actionButtonsSize);
        int compassButtonsSize = 55;
        Dimension compassButtonsDimension = new Dimension(compassButtonsSize, compassButtonsSize);

        JButton rechargeButton = new JButton("", rechargeIcon);
        rechargeButton.setActionCommand("recharge");
        rechargeButton.setBackground(Color.WHITE);
        rechargeButton.setMaximumSize(actionButtonsDimension);
        rechargeButton.setFocusPainted(false);
        JButton attackButton = new JButton("", attackIcon);
        attackButton.setActionCommand("attack");
        attackButton.setBackground(Color.WHITE);
        attackButton.setMaximumSize(actionButtonsDimension);
        attackButton.setFocusPainted(false);
        JButton moveButton = new JButton("", moveIcon);
        moveButton.setActionCommand("move");
        moveButton.setBackground(Color.WHITE);
        moveButton.setMaximumSize(actionButtonsDimension);
        moveButton.setFocusPainted(false);
        JButton skipTurnButton = new JButton("", turnIcon);
        skipTurnButton.setActionCommand("skipturn");
        skipTurnButton.setBackground(Color.WHITE);
        skipTurnButton.setMaximumSize(actionButtonsDimension);
        skipTurnButton.setFocusPainted(false);

        JButton compassButtonNW = new JButton("NW");
        compassButtonNW.setActionCommand("1");
        compassButtonNW.setSize(compassButtonsDimension);
        compassButtonNW.setBackground(Color.WHITE);
        compassButtonNW.setFocusPainted(false);
        JButton compassButtonN = new JButton("N");
        compassButtonN.setActionCommand("2");
        compassButtonN.setSize(compassButtonsDimension);
        compassButtonN.setBackground(Color.WHITE);
        compassButtonN.setFocusPainted(false);
        JButton compassButtonNE = new JButton("NE");
        compassButtonNE.setActionCommand("3");
        compassButtonNE.setSize(compassButtonsDimension);
        compassButtonNE.setBackground(Color.WHITE);
        compassButtonNE.setFocusPainted(false);
        JButton compassButtonE = new JButton("E");
        compassButtonE.setActionCommand("4");
        compassButtonE.setSize(compassButtonsDimension);
        compassButtonE.setBackground(Color.WHITE);
        compassButtonE.setFocusPainted(false);
        JButton compassButtonSE = new JButton("SE");
        compassButtonSE.setActionCommand("5");
        compassButtonSE.setSize(compassButtonsDimension);
        compassButtonSE.setBackground(Color.WHITE);
        compassButtonSE.setFocusPainted(false);
        JButton compassButtonS = new JButton("S");
        compassButtonS.setActionCommand("6");
        compassButtonS.setSize(compassButtonsDimension);
        compassButtonS.setBackground(Color.WHITE);
        compassButtonS.setFocusPainted(false);
        JButton compassButtonSW = new JButton("SW");
        compassButtonSW.setActionCommand("7");
        compassButtonSW.setSize(compassButtonsDimension);
        compassButtonSW.setBackground(Color.WHITE);
        compassButtonSW.setFocusPainted(false);
        JButton compassButtonW = new JButton("W");
        compassButtonW.setActionCommand("8");
        compassButtonW.setSize(compassButtonsDimension);
        compassButtonW.setBackground(Color.WHITE);
        compassButtonW.setFocusPainted(false);

        /* LABELS */
        Dimension statsLabelsDimension = new Dimension(100, 30);
        Font generalsFont = new Font("Monospaced", Font.BOLD, 40);

        JLabel general1Label = new JLabel(general1);
        general1Label.setFont(generalsFont);
        JLabel VSLabel = new JLabel("  VS  ");
        VSLabel.setFont(generalsFont);
        VSLabel.setFont(VSLabel.getFont().deriveFont(Font.BOLD, 60));
        JLabel general2Label = new JLabel(general2);
        general2Label.setFont(generalsFont);

        JLabel statsTitle = new JLabel("Unit's statistics:");
        statsTitle.setHorizontalAlignment(SwingConstants.LEFT);
        statsTitle.setFont(statsTitle.getFont().deriveFont(Font.BOLD, 24));

        unitName = new JLabel("Unit: ");
        unitName.setPreferredSize(statsLabelsDimension);
        unitName.setFont(statsTitle.getFont().deriveFont(0, 20));
        unitHp = new JLabel("HP: ");
        unitHp.setPreferredSize(statsLabelsDimension);
        unitHp.setFont(statsTitle.getFont().deriveFont(0, 20));
        unitStamina = new JLabel("Stamina: ");
        unitStamina.setPreferredSize(statsLabelsDimension);
        unitStamina.setFont(statsTitle.getFont().deriveFont(0, 20));
        unitRange = new JLabel("Range: ");
        unitRange.setPreferredSize(statsLabelsDimension);
        unitRange.setFont(statsTitle.getFont().deriveFont(0, 20));
        unitAtk = new JLabel("Attack: ");
        unitAtk.setPreferredSize(statsLabelsDimension);
        unitAtk.setFont(statsTitle.getFont().deriveFont(0, 20));
        unitMov = new JLabel("Movement: ");
        unitMov.setPreferredSize(statsLabelsDimension);
        unitMov.setFont(statsTitle.getFont().deriveFont(0, 20));
        unitStandby = new JLabel("Standby: ");
        unitStandby.setPreferredSize(statsLabelsDimension);
        unitStandby.setFont(statsTitle.getFont().deriveFont(0, 20));
        activePlayer = new JLabel("Turno: " + this.player1.getName());
        activePlayer.setPreferredSize(statsLabelsDimension);
        activePlayer.setFont(activePlayer.getFont().deriveFont(0, 20));
        playerAPLabel = new JLabel("Action Points left: " + this.player1.getApDone());
        playerAPLabel.setPreferredSize(statsLabelsDimension);
        playerAPLabel.setFont(playerAPLabel.getFont().deriveFont(0, 20));

        /*
    divido la gui in 3
    la parte destra contiene le statistiche e i bottoni
    la parte sinistra contiene il campo e il titolo
         */

 /* PARTI E PANNELLI */
        //parte sinistra
        leftSide = new JPanel();
        leftSide.setLayout(new BorderLayout());
        Border lsBorder = BorderFactory.createLineBorder(Color.WHITE, 15);
        leftSide.setBorder(lsBorder);
        //pannello per il titolo
        JPanel mainTitle = new JPanel();
        mainTitle.setPreferredSize(new Dimension(90, 100));
        mainTitle.setLayout(new BorderLayout());
        mainTitle.setBackground(Color.WHITE);
        JPanel versusContainer = new JPanel();
        versusContainer.setBackground(Color.WHITE);
        versusContainer.add(VSLabel);
        mainTitle.add(general1Label, BorderLayout.WEST);
        mainTitle.add(versusContainer, BorderLayout.CENTER);
        mainTitle.add(general2Label, BorderLayout.EAST);
        leftSide.add(mainTitle, BorderLayout.NORTH);
        leftSide.add(gameGrid, BorderLayout.CENTER);
        game.add(leftSide, BorderLayout.WEST);

        //parte destra
        JPanel rightSide = new JPanel();
        rightSide.setLayout(new BorderLayout());
        Border rsBorder = BorderFactory.createLineBorder(Color.WHITE, 15);
        rightSide.setBorder(rsBorder);
        rightSide.setBackground(Color.WHITE);
        rightSide.setPreferredSize(new Dimension(250, 0));

        //pannello che mostra le statistiche della truppa selezionata
        JPanel statsPanel = new JPanel();
        statsPanel.setPreferredSize(new Dimension(200, 730));
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
        statsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        statsPanel.setBackground(Color.WHITE);

        //griglia con i bottoni che fungono da bussola
        JPanel compassGrid = new JPanel();
        compassGrid.setLayout(new GridLayout(3, 3));
        compassGrid.setMaximumSize(new Dimension(compassButtonsSize * 5, compassButtonsSize * 5));
        compassGrid.setAlignmentX(Component.LEFT_ALIGNMENT);
        compassGrid.add(compassButtonNW);
        compassGrid.add(compassButtonN);
        compassGrid.add(compassButtonNE);
        compassGrid.add(compassButtonW);
        JPanel compassCenter = new JPanel();
        compassCenter.setSize(compassButtonsDimension);
        compassCenter.setBackground(Color.WHITE);
        compassGrid.add(compassCenter);
        compassGrid.add(compassButtonE);
        compassGrid.add(compassButtonSW);
        compassGrid.add(compassButtonS);
        compassGrid.add(compassButtonSE);

        JPanel actionGrid = new JPanel();
        actionGrid.setLayout(new GridLayout(2, 2));
        actionGrid.setMaximumSize(new Dimension(actionButtonsSize * 5, actionButtonsSize * 5));
        actionGrid.setAlignmentX(Component.LEFT_ALIGNMENT);
        actionGrid.add(moveButton);
        actionGrid.add(attackButton);
        actionGrid.add(rechargeButton);
        actionGrid.add(skipTurnButton);

        statsPanel.add(Box.createVerticalStrut(100));
        statsPanel.add(statsTitle);
        statsPanel.add(Box.createVerticalStrut(5));
        statsPanel.add(unitName);
        statsPanel.add(Box.createVerticalStrut(5));
        statsPanel.add(unitHp);
        statsPanel.add(Box.createVerticalStrut(5));
        statsPanel.add(unitStamina);
        statsPanel.add(Box.createVerticalStrut(5));
        statsPanel.add(unitRange);
        statsPanel.add(Box.createVerticalStrut(5));
        statsPanel.add(unitAtk);
        statsPanel.add(Box.createVerticalStrut(5));
        statsPanel.add(unitMov);
        statsPanel.add(Box.createVerticalStrut(5));
        statsPanel.add(unitStandby);
        statsPanel.add(Box.createVerticalStrut(10));
        statsPanel.add(compassGrid);
        statsPanel.add(Box.createVerticalStrut(20));
        statsPanel.add(actionGrid);
        statsPanel.add(Box.createVerticalStrut(10));
        statsPanel.add(activePlayer);
        statsPanel.add(Box.createVerticalStrut(10));
        statsPanel.add(playerAPLabel);
        rightSide.add(statsPanel, BorderLayout.NORTH);

        game.add(rightSide, BorderLayout.EAST);

        rechargeButton.addActionListener(controller);
        attackButton.addActionListener(controller);
        moveButton.addActionListener(controller);
        skipTurnButton.addActionListener(controller);

        compassButtonNW.addActionListener(controller);
        compassButtonN.addActionListener(controller);
        compassButtonNE.addActionListener(controller);
        compassButtonW.addActionListener(controller);
        compassButtonE.addActionListener(controller);
        compassButtonSW.addActionListener(controller);
        compassButtonS.addActionListener(controller);
        compassButtonSE.addActionListener(controller);

        game.setResizable(false); //finestra non ridimensionabile
        game.pack(); //adatta in automatico la dimensione della finestra
        game.setLocationRelativeTo(null); //centra la finestra
        game.setVisible(true);
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void updateGUI(Game controller) {
        gameGrid = uploadBG(battlefield, 70, controller);
        leftSide.remove(1);
        leftSide.add(gameGrid, BorderLayout.CENTER);
    }

    public void showUnitStats(int x, int y) {
        Units unit = this.battlefield.getUnit(x, y);

        if (unit != null) {
            if (unit instanceof Troops) {
                Troops troop = (Troops) unit;

                this.unitName.setText("Unit: " + troop.getName());
                this.unitHp.setText("HP: " + troop.getHP());
                this.unitStamina.setText("Stamina: " + troop.getStamina());
                this.unitRange.setText("Range: " + troop.getRange());
                this.unitAtk.setText("Attack: " + troop.getAtk());
                this.unitMov.setText("Movement: " + troop.getMov());
            } else {
                SiegeMachines siegeMachine = (SiegeMachines) unit;

                this.unitName.setText("Unit: " + siegeMachine.getName());
                this.unitAtk.setText("Attack: " + siegeMachine.getAtk());
                this.unitStandby.setText("Standby: " + siegeMachine.getStandby());
            }
        }
    }

    public void updateActivePlayer(boolean host) {
        if (host) {
            this.activePlayer.setText("Turno: " + this.player1.getName());
        } else {
            this.activePlayer.setText("Turno: " + this.player2.getName());
        }

        updatePlayerAP(host);
    }

    public void updatePlayerAP(boolean host) {
        if (host) {
            this.playerAPLabel.setText("Action Points left: " + this.player1.getApDone());
        } else {
            this.playerAPLabel.setText("Action Points left: " + this.player2.getApDone());
        }
    }

    public void closeGame() {
        this.game.dispose();
        System.exit(0);
    }
}
