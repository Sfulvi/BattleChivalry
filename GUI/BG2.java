import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class BG2 {
    private String general1;
    private String general2;

    public BG2(String general1, String general2) {
        this.general1 = general1;
        this.general2 = general2;
        initializeBgGUI();
    }

    private void initializeBgGUI() {
        JFrame game = new JFrame("Battle Ground"); //tutta la finestra
        game.setLayout(new BorderLayout());

        //scacchiera sullo sfondo
        JPanel backgroundGrid = new JPanel(); 
        backgroundGrid.setLayout(new GridLayout(9, 15));
        //scacchiera trasparente
        JPanel troopsGrid = new JPanel();
        troopsGrid.setLayout(new GridLayout(9, 15));
        troopsGrid.setOpaque(false);

        //bordi scacchiera
        Border gridBorder = BorderFactory.createLineBorder(Color.BLACK, 3); //colore,spessore
        Border cellBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
        Border bgBorder = BorderFactory.createLineBorder(Color.WHITE, 15);
        //dimensione celle
        int cellSize = 70;
        Dimension cellDimension = new Dimension(cellSize, cellSize);

        for (int y = 0; y <= 8; y++) {
            for (int x = 0; x <= 14; x++) {
                JPanel bgCell = new JPanel();
                bgCell.setPreferredSize(cellDimension);
                bgCell.setBorder(cellBorder);

                Color baseGreen = Color.decode("#92D050"); //colore delle caselle considerate basi
                if (x <= 2 || x >= 12) {
                    bgCell.setBackground(baseGreen);
                } else {
                    bgCell.setBackground(Color.WHITE);
                }
                backgroundGrid.add(bgCell);

                JPanel troopCell = new JPanel();
                troopCell.setPreferredSize(cellDimension);
                troopCell.setBorder(cellBorder);
                troopCell.setOpaque(false); //se cambi in true si vede che queste celle sono sopra
                troopsGrid.add(troopCell);
            }
        }
        backgroundGrid.setBorder(gridBorder);
        troopsGrid.setBorder(gridBorder);

        //importazione e ridimensionamento icone
        int iconsSize = 85;
        ImageIcon rechargeOriginal = new ImageIcon("images\\recharge.png"); 
        Image scaledRecharge = rechargeOriginal.getImage().getScaledInstance(iconsSize, iconsSize, Image.SCALE_SMOOTH);
        ImageIcon rechargeIcon = new ImageIcon(scaledRecharge);

        ImageIcon attackOriginal = new ImageIcon("images\\attack.png");
        Image scaledAttack = attackOriginal.getImage().getScaledInstance(iconsSize, iconsSize, Image.SCALE_SMOOTH);
        ImageIcon attackIcon = new ImageIcon(scaledAttack);

        ImageIcon moveOriginal = new ImageIcon("images\\move.png");
        Image scaledMove = moveOriginal.getImage().getScaledInstance(iconsSize, iconsSize, Image.SCALE_SMOOTH);
        ImageIcon moveIcon = new ImageIcon(scaledMove);

        //importazione statistiche giocatore e truppe
        /*  W.I.P.
        Player playerStats = new Player();
        int playerAP = playerStats.getApDone();
        //questa viene stampata nella label playerAPLabel qui sotto
        */

        /* BOTTONI */
        int actionButtonsSize = 180;
        Dimension actionButtonsDimension = new Dimension(actionButtonsSize, actionButtonsSize);
        int compassButtonsSize = 60;
        Dimension compassButtonsDimension = new Dimension(compassButtonsSize, compassButtonsSize);


        JButton rechargeButton = new JButton("Recharge", rechargeIcon);
            rechargeButton.setBackground(Color.WHITE);
            rechargeButton.setPreferredSize(actionButtonsDimension);
            rechargeButton.setFocusPainted(false);
        JButton attackButton = new JButton("Attack", attackIcon);
            attackButton.setBackground(Color.WHITE);
            attackButton.setPreferredSize(actionButtonsDimension);
            attackButton.setFocusPainted(false);
        JButton moveButton = new JButton("Move", moveIcon);
            moveButton.setBackground(Color.WHITE);
            moveButton.setPreferredSize(actionButtonsDimension);
            moveButton.setFocusPainted(false);

        JButton compassButtonNW = new JButton("NW");
            compassButtonNW.setSize(compassButtonsDimension);
            compassButtonNW.setBackground(Color.WHITE);
            compassButtonNW.setFocusPainted(false);
        JButton compassButtonN = new JButton("N");
            compassButtonN.setSize(compassButtonsDimension);
            compassButtonN.setBackground(Color.WHITE);
            compassButtonN.setFocusPainted(false);
        JButton compassButtonNE = new JButton("NE"); 
            compassButtonNE.setSize(compassButtonsDimension);
            compassButtonNE.setBackground(Color.WHITE);
            compassButtonNE.setFocusPainted(false);
        JButton compassButtonE = new JButton("E");
            compassButtonE.setSize(compassButtonsDimension);
            compassButtonE.setBackground(Color.WHITE);
            compassButtonE.setFocusPainted(false);
        JButton compassButtonSE = new JButton("SE");
            compassButtonSE.setSize(compassButtonsDimension);
            compassButtonSE.setBackground(Color.WHITE);
            compassButtonSE.setFocusPainted(false);
        JButton compassButtonS = new JButton("S");
            compassButtonS.setSize(compassButtonsDimension);
            compassButtonS.setBackground(Color.WHITE);
            compassButtonS.setFocusPainted(false);
        JButton compassButtonSW = new JButton("SW");
            compassButtonSW.setSize(compassButtonsDimension);
            compassButtonSW.setBackground(Color.WHITE);
            compassButtonSW.setFocusPainted(false);
        JButton compassButtonW = new JButton("W");
            compassButtonW.setSize(compassButtonsDimension);
            compassButtonW.setBackground(Color.WHITE);
            compassButtonW.setFocusPainted(false);

        /* LABELS */
        Dimension statsLabelsDimension = new Dimension(100, 70);

        JLabel mainTitle = new JLabel("  VS  ");
            mainTitle.setFont(mainTitle.getFont().deriveFont(Font.BOLD, 45));
        JLabel general1Label = new JLabel(general1);
            general1Label.setFont(general1Label.getFont().deriveFont(Font.BOLD, 38));
        JLabel general2Label = new JLabel(general2);
            general2Label.setFont(general2Label.getFont().deriveFont(Font.BOLD, 38));


        JLabel statsTitle = new JLabel("Unit's statistics:");
            statsTitle.setHorizontalAlignment(SwingConstants.CENTER);
            statsTitle.setFont(statsTitle.getFont().deriveFont(Font.BOLD, 24));

        JLabel unitName = new JLabel("Unit: ");
            unitName.setPreferredSize(statsLabelsDimension);
        JLabel unitHp = new JLabel("HP: ");
            unitHp.setPreferredSize(statsLabelsDimension);
        JLabel unitStamina = new JLabel("Stamina: ");
            unitStamina.setPreferredSize(statsLabelsDimension);
        JLabel unitRange = new JLabel("Range: ");
            unitRange.setPreferredSize(statsLabelsDimension);
        JLabel unitAtk = new JLabel("Attack: ");
            unitAtk.setPreferredSize(statsLabelsDimension);
        JLabel unitMov = new JLabel("Movement:");
            unitMov.setPreferredSize(statsLabelsDimension);
        JLabel unitAC = new JLabel("Action cost: ");
            unitAC.setPreferredSize(statsLabelsDimension);

        JLabel playerAPLabel = new JLabel("Action Points left: " /* + playerAP */+ "     ");
            playerAPLabel.setPreferredSize(new Dimension(200, 20));
            playerAPLabel.setFont(playerAPLabel.getFont().deriveFont(Font.BOLD, 16));
            playerAPLabel.setVerticalAlignment(SwingConstants.CENTER);
            playerAPLabel.setAlignmentX(Component.CENTER_ALIGNMENT);


        //divido la gui in due, la parte destra contiene le statistiche e la parte sinistra contiene il resto

        /* PANNELLI */ 

        //metà sinistra
        JPanel leftHalf = new JPanel();
            leftHalf.setLayout(new BorderLayout());
            leftHalf.setBorder(bgBorder);

            //pannello per il titolo
            JPanel titlePanel = new JPanel();
                
                titlePanel.setBackground(Color.WHITE);
            titlePanel.add(general1Label);
            titlePanel.add(mainTitle);
            titlePanel.add(general2Label);
        leftHalf.add(titlePanel, BorderLayout.NORTH);

            //layered pane per sovrapporre le due scacchiere
            JLayeredPane grids = new JLayeredPane();
                int gridsWidth = (cellSize * 15 + 6), gridsHeight = (cellSize * 9 + 6); //dimensione celle X numero celle + 2 bordi
                grids.setPreferredSize(new Dimension(gridsWidth, gridsHeight));
                backgroundGrid.setBounds(0, 0, backgroundGrid.getPreferredSize().width, backgroundGrid.getPreferredSize().height);
                troopsGrid.setBounds(0, 0, backgroundGrid.getPreferredSize().width, backgroundGrid.getPreferredSize().height);
            grids.add(backgroundGrid, 1);
            grids.add(troopsGrid, 0);
        leftHalf.add(grids, BorderLayout.CENTER);

            //pannello per le azioni
            JPanel bottomPanel = new JPanel();
                bottomPanel.setPreferredSize(new Dimension(0, 190));
                bottomPanel.setBackground(Color.WHITE);
                bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
                //pannello azioni con i bottoni di attacco e movimento
                JPanel actionsContainer = new JPanel();
                    actionsContainer.setBackground(Color.WHITE);
                    JPanel actionsPanel = new JPanel();
                        actionsPanel.setBackground(Color.WHITE);
                        int actPanWidth = (actionButtonsSize*4+20), actPanHeight = actionButtonsSize + 10 ; //3 elementi e 2 spaziature da 10
                        actionsPanel.setPreferredSize(new Dimension(actPanWidth, actPanHeight));
                        actionsPanel.setBorder(BorderFactory.createEmptyBorder());
                        actionsPanel.setLayout(new FlowLayout());
                        //griglia con i bottoni che fungono da bussola
                        JPanel compassGrid = new JPanel();
                            compassGrid.setLayout(new GridLayout(3, 3));
                            compassGrid.setPreferredSize(new Dimension(compassButtonsSize*3, compassButtonsSize*3));
                        compassGrid.add(compassButtonNW);
                        compassGrid.add(compassButtonN);
                        compassGrid.add(compassButtonNE);
                        compassGrid.add(compassButtonE);
                        JPanel compassCenter = new JPanel();
                            compassCenter.setSize(compassButtonsDimension);
                            compassCenter.setBackground(Color.WHITE);
                        compassGrid.add(compassCenter);
                        compassGrid.add(compassButtonSE);
                        compassGrid.add(compassButtonS);
                        compassGrid.add(compassButtonSW);
                        compassGrid.add(compassButtonW);
                    //componenti pannello azioni
                    actionsPanel.add(rechargeButton);
                    actionsPanel.add(attackButton);
                    actionsPanel.add(moveButton);
                    actionsPanel.add(compassGrid);
                actionsContainer.add(actionsPanel);
            bottomPanel.add(actionsContainer);
            bottomPanel.add(Box.createHorizontalStrut(30));
            bottomPanel.add(playerAPLabel);
        leftHalf.add(bottomPanel, BorderLayout.SOUTH);
        game.add(leftHalf, BorderLayout.CENTER);
    
        //pannello con elementi interattivi
        JPanel rightHalf = new JPanel();
        rightHalf.setLayout(new BorderLayout());

            //pannello che mostra le statistiche della truppa selezionata
            JPanel statsPanel = new JPanel();
                statsPanel.setPreferredSize(new Dimension(300, 700));
                statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
                statsPanel.setBackground(Color.WHITE);
            statsPanel.add(Box.createVerticalStrut(100));
            statsPanel.add(statsTitle);
            statsPanel.add(Box.createVerticalStrut(20));
            statsPanel.add(unitName);
            statsPanel.add(Box.createVerticalStrut(20));
            statsPanel.add(unitHp);
            statsPanel.add(Box.createVerticalStrut(20));
            statsPanel.add(unitStamina);
            statsPanel.add(Box.createVerticalStrut(20));
            statsPanel.add(unitRange);
            statsPanel.add(Box.createVerticalStrut(20));
            statsPanel.add(unitAtk);
            statsPanel.add(Box.createVerticalStrut(20));
            statsPanel.add(unitMov);
            statsPanel.add(Box.createVerticalStrut(20));
            statsPanel.add(unitAC);

        rightHalf.add(statsPanel, BorderLayout.CENTER);
        game.add(rightHalf, BorderLayout.EAST);
        
        game.setResizable(false); //finestra non ridimensionabile
        game.pack(); //adatta in automatico la dimensione della finestra
        game.setLocationRelativeTo(null); //centra la finestra
        game.setVisible(true);
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) {
        new BG2("General1", "General2");
    }
}