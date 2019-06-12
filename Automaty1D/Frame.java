import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {


    Screen s;
    Automat a;


    public Frame(Automat a, int w, int steps){

        super("Cellular automata");
        int width = w * 10;
        int height = steps*10;

        this.setSize(width, height);

        int frameWidth = this.getSize().width;
        int frameHeight = this.getSize().height;

        this.setLocation((width-frameWidth)/2, (height-frameHeight)/2);


        this.setResizable(true);


        this.a = a;

        this.setDefaultCloseOperation(2);

        init(width);
    }



    public void init(int width){
        this.setLocationRelativeTo(null);
        this.setLayout(new GridLayout(1,1,0,0));

        s = new Screen(a);
        s.setPreferredSize(new Dimension(width, 2000));
        add(s);
        setVisible(true);
    }

}
