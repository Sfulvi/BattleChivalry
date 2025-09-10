import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class BattleGroundGUI {
    private String general1;
    private String general2;

    public BattleGroundGUI(String general1, String general2) {
        this.general1 = general1;
        this.general2 = general2;
        initializeBgGUI();
    }

    //funzione che importa le icone
    private Map<String, ImageIcon> imagesUpload() {
        Map<String, ImageIcon> images = new HashMap<>();
        int iconsSize = 85;
        int unitsSize = 60;
        
        //assegno chiavi alle immagini e richiamo resizeIcon
        images.put("recharge", resizeIcon("../img/icons/recharge.png",        iconsSize, iconsSize));
        images.put("attack", resizeIcon("\"../img/icons/attack.png",            iconsSize, iconsSize));
        images.put("move", resizeIcon("\"../img/icons/move.png",                iconsSize, iconsSize));

        images.put("archer", resizeIcon("../img/troops/archer.png",           unitsSize, unitsSize));
        images.put("armored", resizeIcon("../img/troops/armored.png",         unitsSize, unitsSize));
        images.put("cavalry", resizeIcon("../img/troops/cavalry.png",         unitsSize, unitsSize));
        images.put("engineer", resizeIcon("../img/troops/engineer.png",       unitsSize, unitsSize));
        images.put("infantry", resizeIcon("../img/troops/infantry.png",       unitsSize, unitsSize));

        images.put("suntzu", resizeIcon("../img/generals/sunTzu.png",         unitsSize, unitsSize));
        images.put("nobunaga", resizeIcon("../img/generals/nobunagaOda.png",  unitsSize, unitsSize));
        images.put("arthur", resizeIcon("../img/generals/kingArthur.png",     unitsSize, unitsSize));
        images.put("julius", resizeIcon("../img/generals/juliusCaesar.png",   unitsSize, unitsSize));
        images.put("leonidas", resizeIcon("../img/generals/leonidas.png",     unitsSize, unitsSize));
        images.put("ragnar", resizeIcon("../img/generals/ragnar.png",         unitsSize, unitsSize));
        images.put("ghandi", resizeIcon("../img/generals/ghandi.png",         unitsSize, unitsSize));

        images.put("catapult", resizeIcon("../img/siege/catapult.png",        unitsSize, unitsSize));

        images.put("special", resizeIcon("../img/specials/special.png",       unitsSize, unitsSize));
        
        return images;
    }

    //ridimensiono le icone in una funzione apposita invece di farlo per ogni immagine
    private ImageIcon resizeIcon(String path, int width, int height) {
        ImageIcon original = new ImageIcon(path);
        Image scaled = original.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }

    //funzione che carica e aggiorna la scacchiera
    private JPanel uploadBG(int cellSize) {
        //carico la matrice
        TestMatrix provaMat = new TestMatrix();
        String[][] matrice = provaMat.getUnitsMatrix();
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

                String currentUnit = matrice[y][x];
                ImageIcon unitIcon = getCurrentIcon(currentUnit);
                JLabel unitLabel = new JLabel(unitIcon);
                bgCell.add(unitLabel);
                gameGrid.add(bgCell);
            } 
        }                 
        gameGrid.setBorder(gridBorder);
        return gameGrid;
    }

    Map<String, ImageIcon> iconsImages = imagesUpload(); //do i nomi alle icone delle unità
        ImageIcon archerIcon = iconsImages.get("archer");
        ImageIcon armoredIcon = iconsImages.get("armored");
        ImageIcon cavalryIcon = iconsImages.get("cavalry");
        ImageIcon engineerIcon = iconsImages.get("engineer");
        ImageIcon infantryIcon = iconsImages.get("infantry");
        ImageIcon suntzuIcon = iconsImages.get("suntzu");
        ImageIcon nobunagaIcon = iconsImages.get("nobunaga");
        ImageIcon arthurIcon = iconsImages.get("arthur");
        ImageIcon juliusIcon = iconsImages.get("julius");
        ImageIcon leonidasIcon = iconsImages.get("leonidas");
        ImageIcon ragnarIcon = iconsImages.get("ragnar");
        ImageIcon ghandiIcon = iconsImages.get("ghandi");
        ImageIcon catapultIcon = iconsImages.get("catapult");
        ImageIcon specialIcon = iconsImages.get("special"); //temporanea in attesa delle truppe speciali

    private ImageIcon getCurrentIcon(String currentUnit){
        ImageIcon currentIcon = iconsImages.get(currentUnit);
        return currentIcon;
    }

    private void initializeBgGUI() {

        //do nomi alle icone dei bottoni
        Map<String, ImageIcon> buttonsImages = imagesUpload();
        ImageIcon rechargeIcon = buttonsImages.get("recharge");
        ImageIcon attackIcon = buttonsImages.get("attack");
        ImageIcon moveIcon = buttonsImages.get("move");

        JFrame game = new JFrame("Battle Ground"); //tutta la finestra
        game.setLayout(new BorderLayout());

        int cellSize = 70;
        JPanel gameGrid = uploadBG(cellSize);

        //MouseListener
        gameGrid.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //coordinate relative al panel gameGrid
                int x = e.getX();
                int y = e.getY();
                //calcolo per capire quale cella è stata cliccata
                /* sta cafonata è l'unico modo per far si che il bordo della casella sia considerato parte di quella casella, 
                il -1 alla fine fa si che la prima casella ha coordinate 0;0 , suggerimenti sono ben accetti*/
                int cellX = (x + cellSize - 1)/cellSize - 1;
                int cellY = (y + cellSize - 1)/cellSize - 1;
                System.err.println("cell: X=" + cellX + "Y=" + cellY);
            }
        });

        //importazione statistiche giocatore e truppe
        //W.I.P.

        /* 
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
        Font generalsFont = new Font("Monospaced", Font.BOLD, 40);

        JLabel general1Label = new JLabel(general1);
        general1Label.setFont(generalsFont);
        JLabel VSLabel = new JLabel("  VS  ");
            VSLabel.setFont(generalsFont);
            VSLabel.setFont(VSLabel.getFont().deriveFont(Font.BOLD, 60));
        JLabel general2Label = new JLabel(general2);
            general2Label.setFont(generalsFont);


        JLabel statsTitle = new JLabel("Unit's statistics:");
            statsTitle.setHorizontalAlignment(SwingConstants.CENTER);
            statsTitle.setFont(statsTitle.getFont().deriveFont(Font.BOLD, 24));

        JLabel unitName = new JLabel("Unit: ");
            unitName.setPreferredSize(statsLabelsDimension);
            unitName.setFont(statsTitle.getFont().deriveFont(0, 20));
        JLabel unitHp = new JLabel("HP: ");
            unitHp.setPreferredSize(statsLabelsDimension);
            unitHp.setFont(statsTitle.getFont().deriveFont(0, 20));
        JLabel unitStamina = new JLabel("Stamina: ");
            unitStamina.setPreferredSize(statsLabelsDimension);
            unitStamina.setFont(statsTitle.getFont().deriveFont(0, 20));
        JLabel unitRange = new JLabel("Range: ");
            unitRange.setPreferredSize(statsLabelsDimension);
            unitRange.setFont(statsTitle.getFont().deriveFont(0, 20));
        JLabel unitAtk = new JLabel("Attack: ");
            unitAtk.setPreferredSize(statsLabelsDimension);
            unitAtk.setFont(statsTitle.getFont().deriveFont(0, 20));
        JLabel unitMov = new JLabel("Movement:");
            unitMov.setPreferredSize(statsLabelsDimension);
            unitMov.setFont(statsTitle.getFont().deriveFont(0, 20));
        JLabel unitAC = new JLabel("Action cost: ");
            unitAC.setPreferredSize(statsLabelsDimension);
            unitAC.setFont(statsTitle.getFont().deriveFont(0, 20));

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
                Border lhBorder = BorderFactory.createLineBorder(Color.WHITE, 15);
            leftHalf.setBorder(lhBorder);
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
        leftHalf.add(mainTitle, BorderLayout.NORTH);
        leftHalf.add(gameGrid, BorderLayout.CENTER);

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
            statsPanel.add(Box.createVerticalStrut(110));
            statsPanel.add(statsTitle);
            statsPanel.add(Box.createVerticalStrut(40));
            statsPanel.add(unitName);
            statsPanel.add(Box.createVerticalStrut(40));
            statsPanel.add(unitHp);
            statsPanel.add(Box.createVerticalStrut(40));
            statsPanel.add(unitStamina);
            statsPanel.add(Box.createVerticalStrut(40));
            statsPanel.add(unitRange);
            statsPanel.add(Box.createVerticalStrut(40));
            statsPanel.add(unitAtk);
            statsPanel.add(Box.createVerticalStrut(40));
            statsPanel.add(unitMov);
            statsPanel.add(Box.createVerticalStrut(40));
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
        SwingUtilities.invokeLater(() -> {
            new BattleGroundGUI("", "");
        });
    }
}