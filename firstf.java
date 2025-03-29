import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class firstf extends JFrame {
    private JPanel main;
    private JLabel title;
    private JLabel description;
    private JLabel set;
    private JLabel lab1;
    private JLabel lab2;
    private JLabel lab3;
    private JButton butt;
    private JSpinner  spinner1;
    private JSpinner spinner3;
    private JSpinner spinner2;

    public firstf() {
        setTitle("POMOLOOM App");
        setSize(750, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(main);
        butt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int workDuration = (int)  spinner1.getValue();
                int breakTime = (int) spinner3.getValue();
                int cycleCount= (int) spinner2.getValue();

                JOptionPane.showMessageDialog(null,
                        "Let's Study!\nWork Time: " + workDuration + " min\nBreak Time: " + breakTime + " min\nCycles: " + cycleCount);
                int w = (Integer) spinner1.getValue();
                int b = (Integer) spinner2.getValue();
                int c = (Integer) spinner3.getValue();

                new second(workDuration, breakTime, cycleCount).setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        new firstf().setVisible(true);
    }
}