/**
 * @author : MUHIRWA GABO Oreste (muhirwa.g.oreste@gmail.com)
 * @version 2.0
 */

import java.util.Objects;
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

    @Override
    public String toString() {
        return String.format("Joueur:%s  Score:%d",symbole,score);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Joueur joueur)) return false;
        return getSymbole() == joueur.getSymbole();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSymbole());
    }
}