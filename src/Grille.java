/**
 * @author : MUHIRWA GABO Oreste (muhirwa.g.oreste@gmail.com)
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Grille {
    private int dimension;
    private final char libre;
    private char[][] grille;
    private int indiceJoueurActuel;
    private ArrayList<Joueur> joueurs=new ArrayList<Joueur>();
    

    public Grille(int dimension, char libre) {
        assert(dimension>2);
        this.dimension = dimension;
        initialiserJoueurs();
this.libre = libre;//"." libre doit etre un point, une espace vide, dans le console de l'IDE, et un bouton avec rien dans l'interface graphique

        grille = new char[dimension][dimension];
        initialiserPlateau();
    }


    /**
     * Le plateau est remis à l'état initial avec toutes les valeurs libre
     */
    public void initialiserPlateau (){
        grille=new char[dimension][dimension];
        indiceJoueurActuel=0;
        for(int ligne = 0; ligne < dimension; ++ligne){
            for(int colonne = 0; colonne < dimension; ++colonne){
                grille[ligne][colonne] = libre;
            }
        }
    }

    /**
     * L'initialisation du tableau de joueurs
     * Le tableau doit avoir mon 2 joueurs
     */
    public void initialiserJoueurs(){
        joueurs.clear();
        try {
            ajouterJoueur(new Joueur('X'));
            ajouterJoueur(new Joueur('O'));
        } catch (JoueurExisteException e) {
            e.printStackTrace();
        }
    }

   /**
    * Ajouter un nouveau joueur
    * @param joueur Le nouveau joueur à ajouter
    * @throws JoueurExisteException On ne doit pas ajouter un joueur qui est déja dedans
    */
    public void ajouterJoueur(Joueur joueur) throws JoueurExisteException {
        assert(!joueurs.contains(joueur));

        for(int x=0;x<joueurs.size();x++){
            if(joueur.equals(joueurs.get(x))){
                throw new JoueurExisteException("On ne peux pas avoir 2 joueurs qui ont le meme symbole");
            }
        }
        joueurs.add(joueur);
        
    }
    /**
     Accesseur d'une case du plateau
     @param ligne numéro de ligne
     @param colonne numéro de colonne
     @return la valeur en (ligne, colonne) de la Grille
     */
    public  char evalCase(int ligne, int colonne){
        assert(0 <= ligne && ligne < dimension && 0 <= colonne && colonne < dimension);
        char c;
        try{
            c=  grille[ligne][colonne];}
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("ArrayIndexOutOfBoundsException , ligne ou colonne a invalide");
            c='#';
        }
        return c;
    }

    /**
     Modifieur d'une case du plateau
     @param ligne numéro de ligne
     @param colonne numéro de colonne
     @param valeur la nouvelle valeur
     */
    public void modifierCase(int ligne, int colonne, char valeur){
        assert(0 <= ligne && ligne < dimension && 0 <= colonne && colonne < dimension);
        grille[ligne][colonne] = valeur;
    }

    /**
     Test de case dans un plateau
     @param nLigne numéro de ligne
     @param nColonne numéro de colonne
     @return true(vrai) si (nLigne, nColonne) est dans le plateau
     */
    public boolean dansPlateau(int nLigne, int nColonne){
        return (0 <= nLigne && nLigne < dimension && 0 <= nColonne && nColonne <dimension);
    }


    /**
     Affichage du plateau
     @param nbEspaces nombre d'espaces entre les valeurs
      @param nbEspacesCote nombres d'espaces à coté des valeurs
      @deprecated
     */
    public void afficherPlateau(int nbEspaces,int nbEspacesCote){
        assert(nbEspaces>=0 && nbEspacesCote>=0);
        System.out.println("             Morpion version 1.0");

        //Hashtag pour le mur du haut
        System.out.print(" ");
        for(int x=0;x<(nbEspaces*(dimension-1))+(nbEspacesCote*2)+dimension+4;x++){
            System.out.print("#");
        }
        System.out.print("\n #");
        for(int x=0;x<nbEspacesCote+2;x++){
            System.out.print(' ');
        }
        for(int x=0;x<dimension;x++){
            System.out.print(x);
            for(int y=0;y<((x==dimension-1)? nbEspacesCote:nbEspaces);y++){
                System.out.print(' ');
            }
        }System.out.println('#');




        //Le debut de la ligne qui dessine l'underscore
        System.out.print(" # X");
        for(int x=0;x<nbEspacesCote;x++){
            System.out.print(' ');
        }
        /*
        * nbEspaces* (dimension-1) vont nous donner ne nombre d'underscores à mettre, pour couvrir les espaces entre les element du grille
        * (nbEspacesMin+dimension) vont ajouter les underscores à la place des elements du grille, ainsi que le nombre despaceMin à la fin
        * */
        for(int x=0;x<(nbEspaces*(dimension-1))+(dimension);x++) {
            System.out.print("_");
        }
        for(int x=0;x<nbEspacesCote;x++){
            System.out.print(' ');
        }
        System.out.println("#");
        //Fin de la ligne des underscores



        //Début de la ligne qui affiche les valeurs de la grille
        for(int i = 0; i < dimension; ++i){
            System.out.print(" # "+i);
            for(int x=0;x<nbEspacesCote;x++){
                System.out.print(' ');
            }
            for(int j = 0; j < dimension; ++j){
                System.out.print(grille[i][j]);
                for(int x=0;x<((j==dimension-1)? nbEspacesCote:nbEspaces);x++){
                    System.out.print(' ');
                }
            }
            System.out.println("#");
        }
        //Fin de la ligne qui affiche les valeurs de la grille


        //Hashtag pour le mur du bas
        System.out.print(" ");
        for(int x=0;x<(nbEspaces*(dimension-1))+(nbEspacesCote*2)+dimension+4;x++){
            System.out.print("#");
        }
        System.out.println();

    }


    /**
     @param joueur Lettre du joueur(X ou O)
     @deprecated
     */

    private void jouer (char joueur){
        int colonne, ligne;
        Scanner keyboard = new Scanner(System.in);
        System.out.print("x:");
        ligne = keyboard.nextInt();

        System.out.print("y:");
        colonne = keyboard.nextInt();


        while(! dansPlateau(ligne,colonne)){
            System.out.print("ERREUR !");
            System.out.print("x:");
            ligne = keyboard.nextInt();

            System.out.print("y:");
            colonne = keyboard.nextInt();
        }
        modifierCase(ligne,colonne,joueur);
    }

    /**
     @return true (vrai) si le plateau est rempli, donc plus de place pour jouer
    */
    public boolean plateauBloque() {
        for(int i = 0; i < dimension; ++i){
            for(int j = 0; j < dimension; ++j){
                if(evalCase(i,j) == libre) return false;
            }
        }
        return true;
    }

    /**
     @return X ou O en alternant, chacun son tour
     @deprecated la fonction n'est plus utile, sauf dans la version 1 de la console
     */
    private char joueurSuivant (char joueur) {
        if(joueur == 'X') return 'O';
        return 'X';
    }

    /**
     @return un joueur sur une liste chainée circulaire
     */
    private Joueur joueurSuivant (){
        if(indiceJoueurActuel+1<joueurs.size())
            return joueurs.get(indiceJoueurActuel+1);
        else
            return joueurs.get(0);
    }

    /**
     * L'indice du joueur actuel change, pour le suivant ou le 1er si le précédent était le dernier de la liste.
     */
    public void passerAuJoueurSuivant(){
        if(indiceJoueurActuel+1<joueurs.size())
            indiceJoueurActuel++;
        else
            indiceJoueurActuel=0;
    }

    public Joueur getJoueurActuel(){
        return joueurs.get(indiceJoueurActuel);
    }



    /**
     Teste un alignement de vertical ou horizontal
     @param joueur numéro de joueur
     @return Vrai si alignement, Faux sinon
     */
    private boolean alignementHV(char joueur){
        assert(joueur == 'X' || joueur == 'O');
        for(int x=0;x<dimension;x++){
            for(int y=0;y<dimension-2;y++){
                if(evalCase(x,y)==joueur && evalCase(x,y+1)==joueur && evalCase(x,y+2)==joueur )
                    return true;
                if(evalCase(y,x)==joueur && evalCase(y+1,x)==joueur && evalCase(y+2,x)==joueur )
                    return true;
            }
        }
        return false;
    }

    /**
     Teste un alignement dans le diagonal
     @param joueur numéro de joueur
     @return Vrai si alignement, Faux sinon
     */
    private boolean alignementDiagonal(char joueur){
        assert(joueur == 'X' || joueur == 'O');
        for(int x=0;x<dimension-2;x++){
            for(int y=0;y<dimension-2;y++){
                if(evalCase(x,y)==joueur && evalCase(x+1,y+1)==joueur && evalCase(x+2,y+2)==joueur )
                    return true;
            }
        }
        for(int x=dimension-1;x>1;x--){
            for(int y=dimension-1;y>1;y--){
                if(evalCase(x,y)==joueur && evalCase(x-1,y-1)==joueur && evalCase(x-2,y-2)==joueur )
                    return true;
            }
        }
        return false;
    }

    /**
     Teste si joueur a gagné
     @param joueur numéro de joueur
     @return Vrai si joueur a gagné, faux sinon
     */
    public boolean victoire(char joueur){
        assert (joueur == 'O' || joueur == 'X');
        return alignementHV(joueur) || alignementDiagonal(joueur);
    }

    /**
     Teste si joueur a gagné
     @return Vrai si joueur a gagné, faux sinon
     */
    public boolean victoire(){
        return  alignementHV(getJoueurActuel().getSymbole()) || alignementDiagonal(getJoueurActuel().getSymbole());
    }

    /**
     * @deprecated
     */
    public void lancer(){

        initialiserPlateau();
        afficherPlateau(4,5);
        char joueur = 'X';
        boolean finJeu = false;
        while (! finJeu){
            jouer(joueur);
            afficherPlateau(5,5);
            if(victoire(joueur)){
                System.out.println(String.format("Le joueur:%s gagne ",joueur));
                finJeu = true;
            }
            else if (plateauBloque()){
                System.out.println("Perdant");
                finJeu = true;
            }
            else{
                joueur = joueurSuivant (joueur);
            }
        }
    }
    /**
     La dimension de la grille
     Cette fonctionsera Deprecated, apres avoir mise en place la grille rectangle
     @return Dimension de la grille
     */
    public int getDimension(){
        return dimension;
    }

    /**
     Affectation de la dimension,
     Le jeu doit recommencer une fois la dimension changer
     @param dimension nouvelle dimension
     */
    public void setDimension(int dimension){
        assert(dimension>2);
        this.dimension=dimension;
        initialiserPlateau();

    }
}

