import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfaceGraphique extends JFrame{
    //GUI Widgets
    private Grille grille;

    JTextField dimensionTF;
    JPanel buttonsPanel;
    private GridLayout buttonsLayout;
    private JButton reinitialiserButton;
    private JButton[][] bouttons;
    
    public InterfaceGraphique(){
        super("Morpion");
        grille=new Grille(3,' ');



        //Lire le panneau du contenu et définir ses paramètres
        Container conteneur=getContentPane();
        conteneur.setLayout(new FlowLayout());

        //Définir les champs de texte
        dimensionTF=new JTextField(String.format("%s",grille.getDimension()),5);
        conteneur.add(dimensionTF);



        reinitialiserButton=new JButton("Réinitialiser");
        conteneur.add(reinitialiserButton);

        bouttons=new JButton[grille.getDimension()][grille.getDimension()];
        buttonsLayout=new GridLayout(grille.getDimension(), grille.getDimension());


        buttonsPanel = new JPanel();
        creerBoutons();
        ajouterBoutons();
        donnerEvenementAuBoutons();

        

        
        Handler handler=new Handler();
        
        reinitialiserButton.addActionListener(handler);

        buttonsPanel.setBackground(Color.darkGray);
        buttonsPanel.setLayout(buttonsLayout);
        conteneur.add(buttonsPanel);

    }

    /**
     * Redimmensionner le layout (Grid) en ligne et en colonne
     */
    private void redimensionnerBoutonsLayout(){
        buttonsLayout.setColumns(grille.getDimension());
        buttonsLayout.setRows(grille.getDimension());
    }

    /**
     * La creation du tableau de boutons,
     * et le remplissage du tableau avec les boutons
     */
    void creerBoutons(){
        bouttons=new JButton[grille.getDimension()][grille.getDimension()];
        for(int x=0;x<grille.getDimension();x++){
            for(int y=0;y<grille.getDimension();y++){
                bouttons[x][y]=new JButton(String.format("%s",grille.evalCase(x,y)));
            }
        }
    }

    /**
     * L'ajout de boutons dans le panel
     * Le panel doit d'abord etre vidé
     */
    void ajouterBoutons(){
        buttonsPanel.removeAll();
        for(int x=0;x<grille.getDimension();x++){
            for(int y=0;y<grille.getDimension();y++){
                buttonsPanel.add(bouttons[x][y]);
            }
        }
    }
    
    /**
     * Tous les boutons doivent réagir à l'événement de click
     */
    void donnerEvenementAuBoutons(){
        Handler handler=new Handler();
        for(int x=0;x<grille.getDimension();x++){
            for(int y=0;y<grille.getDimension();y++){
                bouttons[x][y].addActionListener(handler);
            }
        }
    }
    /**
     * Créer le tableau de boutons pour le jeu
     */
    private void actualiser(){
        for(int x=0;x<grille.getDimension();x++){
            for(int y=0;y<grille.getDimension();y++){
                bouttons[x][y].setText(String.format("%s",grille.evalCase(x,y)));
            }
        }
    }

    /**
     * Les boutons doivent être désactivés une fois le jeu est fini
     */
    private void desactiverLesBoutons(){
        for(int x=0;x<grille.getDimension();x++){
            for(int y=0;y<grille.getDimension();y++){
                bouttons[x][y].setEnabled(false);
            }
        }
    }

    /**
     * Les boutons doivent être réactivés une fois le jeu commence
     */
    private void reactiverLesBoutons(){
        for(int x=0;x<grille.getDimension();x++){
            for(int y=0;y<grille.getDimension();y++){
                bouttons[x][y].setEnabled(true);
            }
        }
    }


    /**
    * La fonction pour initialiser le jeu et recommencer au début
    * */
    private void initialiser(){
        grille.initialiserPlateau();
        actualiser();
    }

    /**
     * La classe interne inscrite pour recevoir les événements d'actions de boutons
     */
    private class Handler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}

