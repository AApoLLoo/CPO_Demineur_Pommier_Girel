/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package demineur;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author pommi
 */
public class FenetreInterfaceGraphique extends javax.swing.JFrame{

    private final GrilleDeJeu grilleDeJeu;
    private JPanel gridPanel;

    public FenetreInterfaceGraphique(GrilleDeJeu grilleDeJeu) {
        this.grilleDeJeu = grilleDeJeu;
        initComponents();
        setSize(1920, 1080);
        setResizable(false);
        getContentPane().setBackground(new Color(0x1D2F5D));

    }

    /**
     *
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JButton jButton1 = new JButton();
        JButton jButton2 = new JButton();
        JButton jButton3 = new JButton();
        JLabel titleL = new JLabel();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        titleL.setText("Démineur");
        titleL.setFont(new Font("Arial", Font.BOLD, 56));
        titleL.setForeground(new Color(126, 100, 44));
        jButton1.setText("Entrer dans le jeu");
        jButton1.addActionListener(this::jButton1ActionPerformed);
        jButton2.setText("Paramètres");
        jButton2.addActionListener(this::jButton2ActionPerformed);
        jButton3.setText("Sortir du jeu");
        jButton3.addActionListener(this::jButton3ActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(titleL)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButton3)
                                        .addComponent(jButton2)
                                        .addComponent(jButton1))
                                .addContainerGap(99, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        getContentPane().removeAll();
        revalidate();
        repaint();

        // Create a new game instance
        Partie partie = new Partie(10, 10, 10, 3);

        // Display the menu options
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
        getContentPane().add(CreateGrid(Lignes, Colonnes));
        getContentPane().add(option1);
        getContentPane().add(option2);
        getContentPane().add(option3);
        revalidate();
        repaint();
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt){
            // Créer un panneau de paramètres
            JPanel panel = new JPanel(new GridLayout(4, 2));
            panel.setBackground(new Color(162, 42, 42));  // Fond blanc
            panel.setBorder(BorderFactory.createTitledBorder("Paramètres du jeu")); // Encadrer les options

            // Création des champs de saisie pour le nombre de lignes, colonnes et bombes
            JTextField lignesField = new JTextField(String.valueOf(grilleDeJeu.getLignes()));
            JTextField colonnesField = new JTextField(String.valueOf(grilleDeJeu.getColonnes()));
            JTextField bombesField = new JTextField(String.valueOf(grilleDeJeu.getNombreDeBombes()));
            JTextField viesField = new JTextField(String.valueOf(grilleDeJeu.getVies()));

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
                    int lignes = Integer.parseInt(lignesField.getText());
                    int colonnes = Integer.parseInt(colonnesField.getText());
                    int bombes = Integer.parseInt(bombesField.getText());
                    int vies = Integer.parseInt(viesField.getText());

                    // Vérification que les valeurs sont valides
                    if (lignes > 0 && colonnes > 0 && bombes > 0) {
                        // Créer un nouveau jeu avec les paramètres modifiés
                        GrilleDeJeu nouvelleGrille = new GrilleDeJeu(lignes, colonnes, bombes, vies);
                        FenetreInterfaceGraphique nouvelleFenetre = new FenetreInterfaceGraphique(nouvelleGrille);
                        nouvelleFenetre.setVisible(true);
                        this.dispose(); // Fermer la fenêtre actuelle
                    } else {
                        JOptionPane.showMessageDialog(this, "Les valeurs doivent être supérieures à 0.");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Veuillez entrer des nombres valides.");
                }
        }

    }
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        System.exit(0);
    }
    private JPanel CreateGrid(int L, int C) {
        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(L, C));
        gridPanel.setPreferredSize(new Dimension(500, 500));

        for (int i = 0; i < L; i++) {
            for (int j = 0; j < C; j++) {
                JButton CelluleButton = new JButton();
                CelluleButton.setText(""); // Pas de texte dans le bouton pour la grille

                // Définir la taille du bouton
                CelluleButton.setPreferredSize(new Dimension(50, 50));

                // Appliquer les améliorations visuelles
                CelluleButton.setBackground(new Color(141, 23, 23)); // Couleur de fond douce
                CelluleButton.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // Bordure légère
                CelluleButton.setFont(new Font("Arial", Font.PLAIN, 18));  // Taille de texte ajustée
                CelluleButton.setFocusable(false); // Enlever le focus

                // Ajouter le bouton à la grille
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
                        Revelerunecellule(finalI, finalJ, cellButton, partie);
                    }
                });
            }
        }
    }
    private void Revelerunecellule(int x, int y, JButton cellButton, Partie partie) {
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
                    if (di == 0 && dj == 0) continue; // Skip current cell
                    int newx = x + di;
                    int newy = y + dj;
                    if (newx >= 0 && newx < grille.length && newy >= 0 && newy < grille[0].length) {
                        Revelerunecellule(newx, newy, (JButton) gridPanel.getComponent(newx * grille[0].length + newy), partie);
                    }
                }
            }
        }

        if (partie.toutesLesCellulesRevelees()) {
            JOptionPane.showMessageDialog(this, "Félicitations ! Vous avez gagné !");
            System.exit(0);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FenetreInterfaceGraphique.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        GrilleDeJeu grilledejeu = new GrilleDeJeu(10, 10, 10, 3);
        java.awt.EventQueue.invokeLater(() -> new FenetreInterfaceGraphique(grilledejeu).setVisible(true));
    }

}

