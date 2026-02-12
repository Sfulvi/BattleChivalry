package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import game.Battlefield;
import piece.Units;

public class BattleGroundGUI {
    
    private Battlefield battlefield;
    private String general1;
    private String general2;
    private JPanel selectedCell = null;

    public BattleGroundGUI(Battlefield battlefield, String general1, String general2) {
        this.battlefield = battlefield;
        this.general1 = general1;
        this.general2 = general2;
        initializeBgGUI(battlefield);
    }

    //funzione che importa le icone
    private Map<String, ImageIcon> imagesUpload() {
        Map<String, ImageIcon> images = new HashMap<>();
        int iconsSize = 85;
        int unitsSize = 60;
        
        //assegno chiavi alle immagini e richiamo resizeIcon
        images.put("recharge", resizeIcon("img/icons/recharge.png",        iconsSize, iconsSize));
        images.put("attack", resizeIcon("img/icons/attack.png",            iconsSize, iconsSize));
        images.put("move", resizeIcon("img/icons/move.png",                iconsSize, iconsSize));

        images.put("archer", resizeIcon("img/troops/archer.png",           unitsSize, unitsSize));
        images.put("armored", resizeIcon("img/troops/armored.png",         unitsSize, unitsSize));
        images.put("cavalry", resizeIcon("img/troops/cavalry.png",         unitsSize, unitsSize));
        images.put("engineer", resizeIcon("img/troops/engineer.png",       unitsSize, unitsSize));
        images.put("infantry", resizeIcon("img/troops/infantry.png",       unitsSize, unitsSize));

        images.put("suntzu", resizeIcon("img/generals/sunTzu.png",         unitsSize, unitsSize));
        images.put("nobunagaoda", resizeIcon("img/generals/nobunagaOda.png",  unitsSize, unitsSize));
        images.put("kingarthur", resizeIcon("img/generals/kingArthur.png",     unitsSize, unitsSize));
        images.put("juliuscaesar", resizeIcon("img/generals/juliusCaesar.png",   unitsSize, unitsSize));
        images.put("leonida", resizeIcon("img/generals/leonidas.png",     unitsSize, unitsSize));
        images.put("ragnar", resizeIcon("img/generals/ragnar.png",         unitsSize, unitsSize));
        images.put("ghandi", resizeIcon("img/generals/ghandi.png",         unitsSize, unitsSize));

        images.put("ballista", resizeIcon("img/specials/special1.png",        unitsSize, unitsSize));
        images.put("cannon", resizeIcon("img/specials/special1.png",        unitsSize, unitsSize));
        images.put("catapult", resizeIcon("img/siege/catapult.png",        unitsSize, unitsSize));

        images.put("berserker", resizeIcon("img/specials/berserker.png",       unitsSize, unitsSize));
        images.put("gladiator", resizeIcon("img/specials/gladiator.png",       unitsSize, unitsSize));
        images.put("knight", resizeIcon("img/specials/knight.png",       unitsSize, unitsSize));
        images.put("musketeer", resizeIcon("img/specials/musketeer.png",       unitsSize, unitsSize));
        images.put("samurai", resizeIcon("img/specials/samurai.png",       unitsSize, unitsSize));
        images.put("spartan", resizeIcon("img/specials/spartan.png",       unitsSize, unitsSize));
        
        return images;
    }

    //ridimensiono le icone in una funzione apposita invece di farlo per ogni immagine
    private ImageIcon resizeIcon(String path, int width, int height) {
        ImageIcon original = new ImageIcon(path);
        Image scaled = original.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }

    //funzione che carica e aggiorna la scacchiera
    private JPanel uploadBG(Battlefield battlefield, int cellSize) {
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

                if (currentUnit != null)
                    currentUnitName = currentUnit.getClass().getSimpleName().toLowerCase();

                ImageIcon unitIcon = getCurrentIcon(currentUnitName);
                JLabel unitLabel = new JLabel(unitIcon);
                bgCell.add(unitLabel);

                bgCell.putClientProperty("x", x);
                bgCell.putClientProperty("y", y);

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

                        if (battlefield.getUnit(cy, cx) == null) {
                            System.out.println("No unit");
                        } else{
                            System.out.println("Unit: " + battlefield.getUnit(cy, cx).getClass().getSimpleName().toLowerCase());
                        }
                    
                    }                    
                });

                gameGrid.add(bgCell);
            }
        }
                
        gameGrid.setBorder(gridBorder);
        return gameGrid;
    }

private final Map<String, ImageIcon> images = imagesUpload();


private ImageIcon getCurrentIcon(String currentUnit){
    return images.get(currentUnit);
}


private void initializeBgGUI(Battlefield battlefield) {

    //do nomi alle icone dei bottoni
    ImageIcon rechargeIcon = images.get("recharge");
    ImageIcon attackIcon   = images.get("attack");
    ImageIcon moveIcon     = images.get("move");


    JFrame game = new JFrame("Battle Ground"); //tutta la finestra
    game.setLayout(new BorderLayout());

    int cellSize = 70;
    JPanel gameGrid = uploadBG(battlefield, cellSize);

        //importazione statistiche giocatore e truppe
        //W.I.P.

        /* 
        PlayerTest playerStats = new PlayerTest();
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
                    compassGrid.add(compassButtonW);
                    JPanel compassCenter = new JPanel();
                        compassCenter.setSize(compassButtonsDimension);
                        compassCenter.setBackground(Color.WHITE);
                    compassGrid.add(compassCenter);
                    compassGrid.add(compassButtonE);
                    compassGrid.add(compassButtonSW);
                    compassGrid.add(compassButtonS);
                    compassGrid.add(compassButtonSE);
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
    
    /*public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new BattleGroundGUI("", "");
        });
    }*/
}