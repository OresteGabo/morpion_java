public class Joueur{
    private char symbole;
    private int score;
    public Joueur(char symbole){
        this.symbole=symbole;
        score=0;
    }

    /**
     Quand le joueur gagne, il faut incrementer sa note.
     */
    public void incrementerScore(){
        score++;
    }

    /**
     Le symbole du joueur
     Ce symbole sera utilisé comme étiquette sur les boutons
     @return symbole du joueur
     */
    public char getSymbole() {
        return symbole;
    }

    /**
     La note du joueur
     @return la note du joueur
     */
    public int getScore() {
        return score;
    }
}