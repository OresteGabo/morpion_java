import javax.swing.*;
import java.awt.*;

public class InterfaceGraphique extends JFrame{
    private JLabel label;
    private Grille grille;
    public InterfaceGraphique(Grille grille){
        this.grille=grille;
    }
}