package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

import game.Battlefield;
import game.Game;
import game.Player;
import piece.Generals;
import piece.generals.Ghandi;
import piece.generals.JuliusCaesar;
import piece.generals.KingArthur;
import piece.generals.Leonida;
import piece.generals.NobunagaOda;
import piece.generals.Ragnar;
import piece.generals.SunTzu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu {
    static String selectedGeneral1; 
    static String selectedGeneral2; //stringhe che passo a player
    static String GUIselectedGeneral1;
    static String GUIselectedGeneral2; //stringhe formattate per la GUI
    static String player1Name;
    static String player2Name; //nomi giocatori
    static int generalsStringLength = 13;

    public static void main(String[] args) {

        JFrame menu = new JFrame("Main Menu");
        menu.setLayout(new BorderLayout());

        /* componenti dell'interfaccia */

        //combo box
        String[] generals = { "Sun Tzu", "Nobunaga Oda", "King Arthur", "Julius Caesar", "Leonidas", "Ragnar", "Ghandi" };
        //i due menù a tendina usano la stessa stringa
        JComboBox<String> general1CB = new JComboBox<>(generals);
        JComboBox<String> general2CB = new JComboBox<>(generals);

        //bottoni
        JButton startButton = new JButton("Fight!");
        startButton.setFont(startButton.getFont().deriveFont(Font.BOLD, 30));
        startButton.setFocusPainted(false);

        //labels
        JLabel titleLabel = new JLabel("Select your names and the generals");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 26));

        //text field nome giocatori
        JTextField p1NameField = new JTextField("", 10);
        JTextField p2NameField = new JTextField("", 10);
        
        //impedisco di scrivere oltre 15 caratteri per il nome giocatori
        DocumentFilter charLimitFilter = new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, javax.swing.text.AttributeSet attr) throws javax.swing.text.BadLocationException {
                if (fb.getDocument().getLength() + string.length() <= 15) {
                    super.insertString(fb, offset, string, attr);
                }
            }
            
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, javax.swing.text.AttributeSet attrs) throws javax.swing.text.BadLocationException {
                if (fb.getDocument().getLength() - length + text.length() <= 15) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        };
        
        //applico il filtro ai text field
        PlainDocument doc1 = (PlainDocument) p1NameField.getDocument();
        doc1.setDocumentFilter(charLimitFilter);
        PlainDocument doc2 = (PlainDocument) p2NameField.getDocument();
        doc2.setDocumentFilter(charLimitFilter);

        //combo box dei generali del primo giocatore
        general1CB.setSelectedItem(null);
        general1CB.addActionListener(e -> {
            JComboBox<?> cb = (JComboBox<?>) e.getSource();
            selectedGeneral1 = (String) cb.getSelectedItem();
            GUIselectedGeneral1 = String.format("%-" + generalsStringLength + "s", selectedGeneral1);
        });

        //combo box dei generali del secondo giocatore
        general2CB.setSelectedItem(null);
        general2CB.addActionListener(e -> {
            JComboBox<?> cb = (JComboBox<?>) e.getSource();
            selectedGeneral2 = (String) cb.getSelectedItem();
            GUIselectedGeneral2 = String.format("%" + generalsStringLength + "s", selectedGeneral2);
        });

        //action listener del bottone che fa iniziare la partita
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //errore generali non selezionati
                if (selectedGeneral1 == null || selectedGeneral2 == null) {
                    JOptionPane.showMessageDialog(menu, "You must select both generals before starting.", "Error!",
                    JOptionPane.WARNING_MESSAGE);
                } else if (p1NameField.getText().trim().isEmpty() || p2NameField.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(menu, "You must enter both player names.", "Error!",
                    JOptionPane.WARNING_MESSAGE);
                } else {
                    player1Name = p1NameField.getText();
                    player2Name = p2NameField.getText();
                    SwingUtilities.invokeLater(() -> {
                        //creazione dei player
                        int maxAP = game.Player.getMaxAp();
                        Player player1 = new Player(player1Name, true, maxAP, null);
                        Player player2 = new Player(player2Name, false, maxAP, null);
                        
                        //creazione e assegnazione delle istanze dei generali
                        Generals general1 = switch (selectedGeneral1) {
                            case "Sun Tzu" -> new SunTzu(true);
                            case "Nobunaga Oda" -> new NobunagaOda(true);
                            case "King Arthur" -> new KingArthur(true);
                            case "Julius Caesar" -> new JuliusCaesar(true);
                            case "Leonidas" -> new Leonida(true);
                            case "Ragnar" -> new Ragnar(true);
                            case "Ghandi" -> new Ghandi(true);
                            default -> throw new IllegalArgumentException("Generale non valido");
                        };
                        player1.setGeneral(general1);

                        Generals general2 = switch (selectedGeneral2) {
                            case "Sun Tzu" -> new SunTzu(false);
                            case "Nobunaga Oda" -> new NobunagaOda(false);
                            case "King Arthur" -> new KingArthur(false);
                            case "Julius Caesar" -> new JuliusCaesar(false);
                            case "Leonidas" -> new Leonida(false);
                            case "Ragnar" -> new Ragnar(false);
                            case "Ghandi" -> new Ghandi(false);
                            default -> throw new IllegalArgumentException("Generale non valido");
                        };
                        player2.setGeneral(general2);
                        
                        //creazione di battlefield vuoto e avvio di Game
                        Battlefield battlefield = new Battlefield();
                        new Game(player1, player2, battlefield, GUIselectedGeneral1, GUIselectedGeneral2);
                        
                        //chiusura di questa pagina
                        menu.dispose();
                    });

                }
            }
        });

        JPanel titlePanel = new JPanel();
        titlePanel.setPreferredSize(new Dimension(400, 80));
        titlePanel.setBackground(Color.WHITE);
        titlePanel.add(titleLabel);
        menu.add(titlePanel, BorderLayout.NORTH);

        JPanel generalsPanel = new JPanel();
        generalsPanel.setPreferredSize(new Dimension(500, 200));
        generalsPanel.setLayout(new GridLayout(2, 2));
        generalsPanel.setBackground(Color.WHITE);
        JPanel p1NamePanel = new JPanel();
        p1NamePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        p1NamePanel.setBackground(Color.WHITE);
        p1NamePanel.add(p1NameField);
        generalsPanel.add(p1NamePanel);
        JPanel p2NamePanel = new JPanel();
        p2NamePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        p2NamePanel.setBackground(Color.WHITE);
        p2NamePanel.add(p2NameField);
        generalsPanel.add(p2NamePanel);
        JPanel g1Panel = new JPanel();
        g1Panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        g1Panel.setBackground(Color.WHITE);
        g1Panel.add(general1CB);
        generalsPanel.add(g1Panel);
        JPanel g2Panel = new JPanel();
        g2Panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        g2Panel.setBackground(Color.WHITE);
        g2Panel.add(general2CB);
        generalsPanel.add(g2Panel);
        menu.add(generalsPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(400, 100));
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(startButton);
        menu.add(buttonPanel, BorderLayout.SOUTH);

        menu.setResizable(false); //finestra non ridimensionabile
        menu.pack(); //adatta in automatico la dimensione della finestra
        menu.setLocationRelativeTo(null); //centra la finestra
        menu.setVisible(true);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
