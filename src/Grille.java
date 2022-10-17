public class Grille {
    private final int nDimension;
    private final char libre;
    private char[][] grille;

    public Grille(int nDimension, char libre) {
        this.nDimension = nDimension;
        this.libre = libre;//"." libre doit etre un point, une espace vide, dans un console, et un bouton avec rien dans l'interface graphique

        grille = new char[nDimension][nDimension];
        initialiserPlateau();
    }

    private void initialiserPlateau (){
        for(int ligne = 0; ligne < nDimension; ++ligne){
            for(int colonne = 0; colonne < nDimension; ++colonne){
                grille[ligne][colonne] = libre;
            }
        }
    }

    /**
     Accesseur d'une case du plateau
     @param ligne numéro de ligne
     @param colonne numéro de colonne
     @return la valeaur en (ligne,colonne) de la Grille
     @throws ArrayIndexOutOfBoundsException  si la ligne ou la colonne n'est pas dans [o..nDimension[
     */
    public  char evalCase(int ligne, int colonne){
        assert(0 <= ligne && ligne < nDimension && 0 <= colonne && colonne < nDimension);
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
        assert(0 <= ligne && ligne < nDimension && 0 <= colonne && colonne < nDimension);
        grille[ligne][colonne] = valeur;
    }

    /**
     Test de case dans un plateau
     @param nLigne numéro de ligne
     @param nColonne numéro de colonne
     @return true(vrai) si (nLigne, nColonne) est dans le plateau
     */
    public boolean dansPlateau(int nLigne, int nColonne){
        return (0 <= nLigne && nLigne < nDimension && 0 <= nColonne && nColonne <nDimension);
    }

}
