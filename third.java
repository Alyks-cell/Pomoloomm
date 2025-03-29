import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class third extends JFrame {
    private JPanel main;
    private JLabel summary;
    private JLabel completed;
    private JLabel total;
    private JLabel qo;
    private JTextField notesArea;
    private JButton startButton;
    private JButton saveButton;
    private JButton exitButton;

    public third(int completedCycles, int totalCycles, int totalMinutes) {
        setContentPane(main);
        setTitle("Session Summary");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // 🛠️ Ensure these are NOT null. If they are, fix in GUI Designer.
        if (completed != null)
            completed.setText("📌 Completed Cycles: ✅ " + totalMinutes + " / " + totalCycles);
        else
            System.out.println("❗ 'completed' label not initialized.");

        if (total != null)
            total.setText("📊 Total Focus Time: " + completedCycles + " minutes");
        else
            System.out.println("❗ 'total' label not initialized.");

        // 🔁 Restart session - go back to second form
        startButton.addActionListener(e -> {
            dispose();
            new second(25, 5, 4).setVisible(true);
        });

        // 💾 Save notes
        saveButton.addActionListener(e -> {
            String notes = notesArea.getText();
            JOptionPane.showMessageDialog(this, "Notes saved:\n" + notes);
            // Optionally write to file
        });

        // 🚪 Exit
        exitButton.addActionListener(e -> {
            System.exit(0);
        });
    }
}
