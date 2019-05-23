import javax.swing.*;
import java.awt.*;

public class Screen extends JPanel {

    public Automat a;
    private int span = 10;

    public Screen(Automat a){
        this.a=a;
        repaint();
    }

    public void paint(Graphics g){
        for(int i=0; i<a.getArray().length; i++){
            for(int j=0; j<a.getArray()[i].length; ++j){
                if(a.getArray()[i][j]==0)
                g.drawRect(j+span*j, i+span*i, span, span );
                else g.fillRect(j+span*j,i+span*i, span, span);
            }
        }
    }
}
