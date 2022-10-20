import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfaceGraphique extends JFrame{
    //GUI Widgets
    private Grille grille;

    private boolean joueurX=true;
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


        JPanel buttonsPanel = new JPanel();

        /**
         * La creation du tableau de boutons
         */
        for(int x=0;x<grille.getDimension();x++){
            for(int y=0;y<grille.getDimension();y++){
                bouttons[x][y]=new JButton(String.format("%s",grille.evalCase(x,y)));
            }
        }

        /**
         * Lajout de boutons dans le panel
         */
        for(int x=0;x<grille.getDimension();x++){
            for(int y=0;y<grille.getDimension();y++){
                buttonsPanel.add(bouttons[x][y]);
            }
        }
        buttonsPanel.setBackground(Color.darkGray);
        buttonsPanel.setLayout(buttonsLayout);
        conteneur.add(buttonsPanel);

    }

}

