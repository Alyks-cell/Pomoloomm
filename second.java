import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class second extends JFrame {
    private JPanel main;
    private JProgressBar bar;
    private JButton pause;
    private JButton breakB;
    private JButton reset;
    private JLabel workLabel;
    private JLabel stat;
    private JLabel one;
    private JLabel q1;
    private JLabel q2;
    private JLabel q3;
    private JLabel q4;

    private int workTime;
    private int breakTime;
    private int cycleCount;
    private int currentCycle = 1;
    private boolean isWorkSession = true;
    private boolean isPaused = false;

    private Timer timer;
    private int totalTimeInSeconds;
    private int elapsedTime = 0;

    public second(int workTime, int breakTime, int cycleCount) {
        this.workTime = workTime;
        this.breakTime = breakTime;
        this.cycleCount = cycleCount;

        setTitle("POMOLOOM App");
        setSize(850, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(main);

        totalTimeInSeconds = this.workTime * 60;
        bar.setMaximum(totalTimeInSeconds);

        updateStatusLabel();

        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!isPaused) {
                    elapsedTime++;
                    bar.setValue(elapsedTime);
                    updateStatusLabel();

                    if (elapsedTime >= totalTimeInSeconds) {
                        playSound("C:/Users/HP/IdeaProjects/first/index/alarmm.wav");

                        if (isWorkSession) {
                            JOptionPane.showMessageDialog(second.this, "Work session completed! Time for a break.");
                            isWorkSession = false;
                            totalTimeInSeconds = breakTime * 60;
                        } else {
                            JOptionPane.showMessageDialog(second.this, "Break is over. Back to work!");
                            isWorkSession = true;
                            currentCycle++;
                            if (currentCycle > cycleCount) {
                                timer.stop();
                                JOptionPane.showMessageDialog(second.this, "Congratulations! You've completed all cycles.");


                                new third(workTime, breakTime, cycleCount).setVisible(true);
                            }
                            totalTimeInSeconds = workTime * 60;
                        }

                        elapsedTime = 0;
                        bar.setMaximum(totalTimeInSeconds);
                        bar.setValue(0);
                        updateStatusLabel();
                    }
                }
            }
        });

        timer.start();

        pause.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                isPaused = !isPaused;
                pause.setText(isPaused ? "Resume" : "Pause");
            }
        });

        breakB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                isWorkSession = false;
                elapsedTime = 0;
                totalTimeInSeconds = breakTime * 60;
                bar.setMaximum(totalTimeInSeconds);
                bar.setValue(0);
                updateStatusLabel();
            }
        });

        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timer.stop();
                currentCycle = 1;
                isWorkSession = true;
                elapsedTime = 0;
                totalTimeInSeconds = workTime * 60;
                bar.setMaximum(totalTimeInSeconds);
                bar.setValue(0);
                updateStatusLabel();
                timer.start();
            }
        });
    }

    private void updateStatusLabel() {
        int remainingTime = totalTimeInSeconds - elapsedTime;
        int minutes = remainingTime / 60;
        int seconds = remainingTime % 60;
        workLabel.setText(String.format("%02d:%02d", minutes, seconds));
        stat.setText(isWorkSession ? "Work Session" : "Break Time");
        one.setText("Cycle: " + currentCycle + "/" + cycleCount);
    }

    private void playSound(String alarmm) {
        try {
            File soundFile = new File(alarmm);
            if (soundFile.exists()) {
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                clip.start();
            } else {
                System.err.println("Sound file not found: " + alarmm);
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }}

