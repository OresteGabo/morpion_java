import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Morpion extends JFrame{
    //GUI Widgets
    private Grille grille;

    JTextField dimensionTF;
    JPanel buttonsPanel;
    private GridLayout buttonsLayout;
    private JButton reinitialiserButton;
    private JButton[][] bouttons;
    
    public Morpion(){
        super("Morpion");
        grille=new Grille(3,' ');



        //Lire le panneau du contenu et définir ses paramètres
        Container conteneur=getContentPane();
        conteneur.setLayout(new BorderLayout());

        //Définir les champs de texte
        dimensionTF=new JTextField(String.format("%s",grille.getDimension()),5);

        JPanel topPanel=new JPanel(new BorderLayout());
        topPanel.add(new JLabel("Dimension"),BorderLayout.WEST);
        topPanel.add(dimensionTF,BorderLayout.CENTER);



        reinitialiserButton=new JButton("Réinitialiser");
        topPanel.add(reinitialiserButton,BorderLayout.EAST);
        
        conteneur.add(topPanel,BorderLayout.NORTH);

        bouttons=new JButton[grille.getDimension()][grille.getDimension()];
        buttonsLayout=new GridLayout(grille.getDimension(), grille.getDimension(),3,3);


        buttonsPanel = new JPanel();
        creerBoutons();
        ajouterBoutons();
        donnerEvenementAuBoutons();

        

        
        Handler handler=new Handler();
        
        reinitialiserButton.addActionListener(handler);

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
        int newD=Integer.parseInt(dimensionTF.getText());
        grille.setDimension(newD);
        grille.initialiserPlateau();

        bouttons=null;
        creerBoutons();
        ajouterBoutons();
        donnerEvenementAuBoutons();

        reactiverLesBoutons();
        actualiser();
        redimensionnerBoutonsLayout();
    }

    /**
     * La classe interne inscrite pour recevoir les événements d'actions de boutons
     */
    private class Handler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == reinitialiserButton) {
                initialiser();
                buttonsPanel.updateUI();
            }else{
                for(int x=0;x<grille.getDimension();x++){
                    for(int y=0;y<grille.getDimension();y++){
                        if(e.getSource()==bouttons[x][y]){
                            System.out.println(String.format("Source of the click is x:%d  y:%d",x,y));
                            grille.modifierCase(x,y,grille.getJoueurActuel().getSymbole());

                            bouttons[x][y].setText(""+grille.evalCase(x,y));

                            if(grille.victoire()){
                                grille.getJoueurActuel().incrementerScore();
                                System.out.println(String.format("Le joueur:%s gagne ",grille.getJoueurActuel().getSymbole()));
                                desactiverLesBoutons();
                            }else if(grille.plateauBloque()){
                                System.out.println("Perdant");
                                desactiverLesBoutons();
                            }else{
                                grille.passerAuJoueurSuivant();
                            }
                        }
                    }
                }
            }
        }
    }
}

