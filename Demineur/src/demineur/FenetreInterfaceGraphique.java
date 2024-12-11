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
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt){
    }
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        System.exit(0);
    }
    private JPanel CreateGrid(int L, int C) {
        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(L, C));
        gridPanel.setPreferredSize(new Dimension(500, 500));

        Cellule[][] grille = grilleDeJeu.getGrille();

        for (int i = 0; i < L; i++) {
            for (int j = 0; j < C; j++) {
                JButton CelluleButton = new JButton();
                CelluleButton.setText(""); // Initially blank
                int finalI = i;
                int finalJ = j;
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
                cellButton.addActionListener(e -> {
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
            cellButton.setText("*"); // Indicate bomb
            cellButton.setBackground(Color.RED);
            JOptionPane.showMessageDialog(this, "Vous avez cliqué sur une bombe ! Il vous reste " + partie.getVies() + " vies.");
            if (partie.getVies() <= 0) {
                JOptionPane.showMessageDialog(this, "PERDUUUU ! Vous avez perdu toutes vos vies.");
                System.exit(0);
            }
            return; // Stop further propagation
        }

        int adjacentBombs = cell.getBombesAdjacentes();
        cellButton.setText(adjacentBombs > 0 ? String.valueOf(adjacentBombs) : "");
        cellButton.setBackground(Color.LIGHT_GRAY);

        if (adjacentBombs == 0) {
            int[] directions = {-1, 0, 1};
            for (int di : directions) {
                for (int dj : directions) {
                    if (di == 0 && dj == 0) continue; // Skip current cell
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
        GrilleDeJeu grilledejeu = new GrilleDeJeu(10, 10, 10);
        java.awt.EventQueue.invokeLater(() -> new FenetreInterfaceGraphique(grilledejeu).setVisible(true));
    }

}

