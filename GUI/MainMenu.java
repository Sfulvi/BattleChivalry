import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu {
    static String selectedGeneral1;
    static String selectedGeneral2;
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
        JLabel titleLabel = new JLabel("Select the generals");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 34));

        JLabel player1Label = new JLabel("              Player 1..");
        JLabel player2Label = new JLabel("              Player 2..");

        //combo box dei generali del primo giocatore
        general1CB.setSelectedItem(null);
        general1CB.addActionListener(e -> {
            JComboBox cb = (JComboBox) e.getSource();
            selectedGeneral1 = (String) cb.getSelectedItem();
            selectedGeneral1 = String.format("%-" + generalsStringLength + "s", selectedGeneral1);
        });

        //combo box dei generali del secondo giocatore
        general2CB.setSelectedItem(null);
        general2CB.addActionListener(e -> {
            JComboBox cb = (JComboBox) e.getSource();
            selectedGeneral2 = (String) cb.getSelectedItem();
            selectedGeneral2 = String.format("%" + generalsStringLength + "s", selectedGeneral2);
        });

        //action listener del bottone che fa iniziare la partita
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //errore generali non selezionati
                if (selectedGeneral1 == null || selectedGeneral2 == null) {
                    JOptionPane.showMessageDialog(menu, "You must select both generals before starting.", "Error!",
                    JOptionPane.WARNING_MESSAGE);
                } else {
                    SwingUtilities.invokeLater(() -> { //avvio della schermata di gioco
                        new BattleGroundGUI(selectedGeneral1, selectedGeneral2);
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
        generalsPanel.setPreferredSize(new Dimension(400, 200));
        generalsPanel.setLayout(new GridLayout(2, 2));
        generalsPanel.setBackground(Color.WHITE);
        generalsPanel.add(player1Label);//0:0
        generalsPanel.add(player2Label);//0:1
        JPanel g1Panel = new JPanel();
        g1Panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        g1Panel.setBackground(Color.WHITE);
        g1Panel.add(general1CB);
        generalsPanel.add(g1Panel);//1:0
        JPanel g2Panel = new JPanel();
        g2Panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        g2Panel.setBackground(Color.WHITE);
        g2Panel.add(general2CB);
        generalsPanel.add(g2Panel);//1:1
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
