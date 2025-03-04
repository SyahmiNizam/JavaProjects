import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;

public class TrafficLightQueue extends JFrame {
    private Queue<String> carQueue = new LinkedList<>();
    private JLabel trafficLightLabel;
    private JPanel carPanel;
    private String[] lightStates = {"RED", "YELLOW", "GREEN"};
    private int currentLightState = 0;
    private Timer moveCarTimer;

    public TrafficLightQueue() {
        // Set up the JFrame
        setTitle("Traffic Light Queue");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Traffic Light Panel
        JPanel trafficLightPanel = new JPanel();
        trafficLightLabel = new JLabel(lightStates[currentLightState], SwingConstants.CENTER);
        trafficLightLabel.setFont(new Font("Arial", Font.BOLD, 32));
        trafficLightLabel.setOpaque(true);
        trafficLightLabel.setBackground(Color.RED);
        trafficLightPanel.add(trafficLightLabel);
        add(trafficLightPanel, BorderLayout.NORTH);


        // Car Movement Panel
        carPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawCars(g);
            }
        };
        carPanel.setPreferredSize(new Dimension(600, 200));
        add(carPanel, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        JButton addCarButton = new JButton("Add Car");
        JButton removeCarButton = new JButton("Remove Car");
        JButton changeLightButton = new JButton("Change Light");
        buttonPanel.add(addCarButton);
        buttonPanel.add(removeCarButton);
        buttonPanel.add(changeLightButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Button Actions
        addCarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carQueue.add("Car " + (carQueue.size() + 1));
                carPanel.repaint();
                carPanel.setFont(new Font("Arial", Font.PLAIN, 20));
            }
        });

        removeCarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (lightStates[currentLightState].equals("GREEN") && !carQueue.isEmpty()) {
                    carQueue.poll();
                    carPanel.repaint();
                } else {
                    JOptionPane.showMessageDialog(TrafficLightQueue.this, "Cannot remove car. Light is not GREEN or queue is empty.");
                }
            }
        });

        changeLightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentLightState = (currentLightState + 1) % lightStates.length;
                trafficLightLabel.setText(lightStates[currentLightState]);
                switch (currentLightState) {
                    case 0:
                        trafficLightLabel.setBackground(Color.RED);
                        break;
                    case 1:
                        trafficLightLabel.setBackground(Color.YELLOW);
                        break;
                    case 2:
                        trafficLightLabel.setBackground(Color.GREEN);
                        break;
                }

                if (currentLightState == 2) {
                    // Start the car movement when light is green
                    startCarMovement();
                } else {
                    // Stop the car movement when light is not green
                    if (moveCarTimer != null && moveCarTimer.isRunning()) {
                        moveCarTimer.stop();
                    }
                }
            }
        });
    }

    private void drawCars(Graphics g) {
        int carWidth = 120;
        int carHeight = 60;
        int xPosition = 10;

        for (String car : carQueue) {
            g.setColor(Color.BLUE);
            g.fillRect(xPosition, 70, carWidth, carHeight);
            g.setColor(Color.WHITE);
            g.drawString(car, xPosition + 40, 100);
            xPosition += carWidth + 20;
        }
    }

    private void startCarMovement() {
        moveCarTimer = new Timer(900, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!carQueue.isEmpty()) {
                    carQueue.poll(); // Remove the car from the queue
                    carPanel.repaint(); // Repaint the panel to show car movement
                } else {
                    moveCarTimer.stop(); // Stop the timer when the queue is empty
                }
            }
        });
        moveCarTimer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TrafficLightQueue().setVisible(true);
            }
        });
    }
}
