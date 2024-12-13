package demineur;

import javax.swing.*;
import java.awt.*;

public class FenetreInterfaceGraphique extends JFrame {

    private GrilleDeJeu grilleDeJeu;
    private JPanel gridPanel;

    public FenetreInterfaceGraphique(GrilleDeJeu grilleDeJeu) {
        this.grilleDeJeu = grilleDeJeu;
        initComponents();
        setSize(1920, 1080);
        setResizable(false);
    }

    private void initComponents() {
        JButton jButton1 = new JButton();
        JButton jButton2 = new JButton();
        JButton jButton3 = new JButton();
        JLabel titleL = new JLabel();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        titleL.setText("Démineur");
        titleL.setFont(new Font("Arial", Font.BOLD, 56));
        jButton1.setText("Entrer dans le jeu");
        jButton1.addActionListener(this::jButton1ActionPerformed);
        jButton2.setText("Paramètres");
        jButton2.addActionListener(this::jButton2ActionPerformed);
        jButton3.setText("Sortir du jeu");
        jButton3.addActionListener(this::jButton3ActionPerformed);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(titleL)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jButton3)
                                        .addComponent(jButton2)
                                        .addComponent(jButton1))
                                .addContainerGap(99, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(titleL)
                                .addComponent(jButton1)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2)
                                .addGap(18, 18, 18)
                                .addComponent(jButton3)
                                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        getContentPane().removeAll();
        revalidate();
        repaint();

        // Create a new game instance
        Partie partie = new Partie(grilleDeJeu.getLignes(), grilleDeJeu.getColonnes(), grilleDeJeu.getNombreDeBombes(), grilleDeJeu.getVies());

        // Display the menu options
        JLabel label = new JLabel("Choix :");
        JButton option1 = new JButton("Révéler une cellule");
        JButton option2 = new JButton("Afficher le nombre de vies restantes");
        JButton option3 = new JButton("Quitter la partie");
        int Lignes = partie.getLignes();
        int Colonnes = partie.getColonnes();

        option1.addActionListener(_ -> enableCellSelection(partie));
        option2.addActionListener(_ -> JOptionPane.showMessageDialog(this, "Il vous reste " + partie.getVies() + " vies."));
        option3.addActionListener(_ -> System.exit(0));

        // Add content to the page
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        getContentPane().add(label);
        getContentPane().add(CreateGrid(Lignes, Colonnes));
        getContentPane().add(option1);
        getContentPane().add(option2);
        getContentPane().add(option3);
        revalidate();
        repaint();
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        // Créer un panneau de paramètres
        JPanel panel = new JPanel(new GridLayout(4, 2));
        int vies = grilleDeJeu.getVies();
        int lignes = grilleDeJeu.getLignes();
        int colonnes = grilleDeJeu.getColonnes();
        int bombes = grilleDeJeu.getNombreDeBombes();

        // Création des champs de saisie pour le nombre de lignes, colonnes et bombes
        JTextField lignesField = new JTextField(String.valueOf(lignes));
        JTextField colonnesField = new JTextField(String.valueOf(colonnes));
        JTextField bombesField = new JTextField(String.valueOf(bombes));
        JTextField viesField = new JTextField(String.valueOf(vies));

        // Ajouter les composants au panneau
        panel.add(new JLabel("Nombre de lignes :"));
        panel.add(lignesField);
        panel.add(new JLabel("Nombre de colonnes :"));
        panel.add(colonnesField);
        panel.add(new JLabel("Nombre de bombes :"));
        panel.add(bombesField);
        panel.add(new JLabel("Nombre de vies :"));
        panel.add(viesField);

        // Afficher la boîte de dialogue pour entrer les paramètres
        int option = JOptionPane.showConfirmDialog(this, panel, "Modifier les paramètres", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            try {
                // Lire les valeurs des champs de texte
                lignes = Integer.parseInt(lignesField.getText());
                colonnes = Integer.parseInt(colonnesField.getText());
                bombes = Integer.parseInt(bombesField.getText());
                vies = Integer.parseInt(viesField.getText());

                // Vérification que les valeurs sont valides
                if (lignes > 0 && colonnes > 0 && bombes > 0) {
                    // Créer un nouveau jeu avec les paramètres modifiés
                    grilleDeJeu = new GrilleDeJeu(lignes, colonnes, bombes, vies);

                    // Stockez la taille actuelle de la fenêtre
                    Dimension currentSize = getSize();

                    // Réinitialiser l'interface graphique
                    getContentPane().removeAll();
                    revalidate();
                    repaint();

                    // Afficher le menu principal
                    initComponents();
                    revalidate();
                    repaint();

                    // Restaurez la taille de la fenêtre
                    setSize(currentSize);
                } else {
                    JOptionPane.showMessageDialog(this, "Les valeurs doivent être supérieures à 0.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Veuillez entrer des nombres valides.");
            }
        }
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    private JPanel CreateGrid(int L, int C) {
        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(L, C));
        gridPanel.setPreferredSize(new Dimension(500, 500));


        for (int i = 0; i < L; i++) {
            for (int j = 0; j < C; j++) {
                JButton CelluleButton = new JButton();
                CelluleButton.setText("");
                CelluleButton.setPreferredSize(new Dimension(50, 50));
                gridPanel.add(CelluleButton);
            }
        }
        return gridPanel;
    }

    private void enableCellSelection(Partie partie) {
        Cellule[][] grille = partie.getGrille();
        Component[] components = getContentPane().getComponents();
        JPanel gridPanel = (JPanel) components[1]; // Assuming the grid is the second component

        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[i].length; j++) {
                JButton cellButton = (JButton) gridPanel.getComponent(i * grille[i].length + j);
                int finalI = i;
                int finalJ = j;
                cellButton.addActionListener(_ -> {
                    if (!grille[finalI][finalJ].estRevelee()) {
                        revealCellWithLife(finalI, finalJ, cellButton, partie);
                    }
                });
            }
        }
    }

    private void revealCellWithLife(int x, int y, JButton cellButton, Partie partie) {
        Cellule[][] grille = partie.getGrille();
        Cellule cell = grille[x][y];
        if (cell.estRevelee()) {
            return; // Already revealed
        }

        cell.reveler();
        cellButton.setEnabled(false); // Disable button after revealing

        if (cell.contientBombe()) {
            partie.perdreVie();
            cellButton.setText("💣"); // Indicate bomb
            cellButton.setBackground(Color.RED); // Couleur du texte (blanc)
            JOptionPane.showMessageDialog(this, "Vous avez cliqué sur une bombe ! Il vous reste " + partie.getVies() + " vies.");
            if (partie.getVies() <= 0) {
                JOptionPane.showMessageDialog(this, "PERDUUUU ! Vous avez perdu toutes vos vies.");
                System.exit(0);
            }
            return; // Stop further propagation
        }

        int adjacentBombs = cell.getBombesAdjacentes();
        cellButton.setText(adjacentBombs > 0 ? String.valueOf(adjacentBombs) : "");
        cellButton.setBackground(Color.BLUE);

        if (adjacentBombs == 0) {
            int[] directions = {-1, 0, 1};
            for (int di : directions) {
                for (int dj : directions) {
                    if (di == 0 && dj == 0) continue;
                    int newx = x + di;
                    int newy = y + dj;
                    if (newx >= 0 && newx < grille.length && newy >= 0 && newy < grille[0].length) {
                        revealCellWithLife(newx, newy, (JButton) gridPanel.getComponent(newx * grille[0].length + newy), partie);
                    }
                }
            }
        }

        if (partie.toutesLesCellulesRevelees()) {
            JOptionPane.showMessageDialog(this, "Félicitations ! Vous avez gagné !");
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        // Set the Nimbus look and feel
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FenetreInterfaceGraphique.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        // Create and display the form
        GrilleDeJeu grilledejeu = new GrilleDeJeu(10, 10, 10, 3);
        EventQueue.invokeLater(() -> new FenetreInterfaceGraphique(grilledejeu).setVisible(true));
    }
}
