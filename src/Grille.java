public class Grille {
    private final int nDimension;
    private final char libre;
    private char[][] grille;


    private void initialiserPlateau (){
        for(int i = 0; i < nDimension; ++i){
            for(int j = 0; j < nDimension; ++j){
                grille[i][j] = libre;
            }
        }
    }

}
