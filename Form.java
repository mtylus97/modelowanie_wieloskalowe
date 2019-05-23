import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Form {
    private JPanel panel;
    private JComboBox ruleComboBox;
    private JTextField widthField;
    private JTextField stepsField;
    private JButton drawButton;


    public Form() {
        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rule, steps, width;

                rule = Integer.parseInt((String) Objects.requireNonNull(ruleComboBox.getSelectedItem()));
                steps = Integer.parseInt(stepsField.getText());
                width = Integer.parseInt(widthField.getText());
                System.out.println("Width:" + width +" Steps:" + steps + " Rule:" + rule);

                Automat automat = new Automat(steps, width, rule);

              //  Screen s = new Screen(automat);
                //drawingPanel.add(s);

                new Frame(automat, width, steps);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Cellular automata");


        frame.setContentPane(new Form().panel);
        frame.setDefaultCloseOperation(3);


        frame.pack();
        frame.setVisible(true);
    }
}
