import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BatteryGUI {
    private static Battery battery;
    private static JLabel batteryLabel;
    private static JLabel visualLabel;
    private static JLabel indicatorDotLabel;
    private static Timer dischargeTimer;
    private static Timer chargeTimer;
    private static JToggleButton toggleButton;
    private static JButton onButton;
    private static JButton offButton;

    public static void main(String[] args) {
        battery = new Battery(100);

        JFrame frame = new JFrame("Battery Percentage Simulator");
        frame.setSize(400, 400); // Maintain the original window size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.DARK_GRAY);

        visualLabel = new JLabel(battery.displayVisualRepresentation(), SwingConstants.CENTER);
        visualLabel.setBounds(95, 10, 200, 30);
        visualLabel.setForeground(Color.GREEN);
        frame.add(visualLabel);

        batteryLabel = new JLabel(battery.displayStatus(), SwingConstants.CENTER);
        batteryLabel.setBounds(99, 50, 200, 30);
        batteryLabel.setForeground(Color.WHITE);
        frame.add(batteryLabel);

        indicatorDotLabel = new JLabel(".");
        indicatorDotLabel.setFont(new Font("Arial", Font.PLAIN, 50));
        indicatorDotLabel.setBounds(190, 7, 50, 50);
        indicatorDotLabel.setForeground(Color.RED);
        frame.add(indicatorDotLabel);

        ImageIcon imageIcon = new ImageIcon("/home/yobi/4vaughn/src/BARTSAD.jpeg");
        Image scaledImage = imageIcon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
        imageLabel.setBounds(50, 90, 300, 200);
        frame.add(imageLabel);

        toggleButton = new JToggleButton("Plug in Charger");
        toggleButton.setBounds(45, 300, 140, 30);
        frame.add(toggleButton);

        onButton = new JButton("On");
        onButton.setBounds(210, 300, 60, 30);
        frame.add(onButton);

        offButton = new JButton("Off");
        offButton.setBounds(275, 300, 60, 30); // Adjusted to fit below the image
        frame.add(offButton);

        JButton overloadButton = new JButton("Overload");
        overloadButton.setBounds(150, 340, 100, 30); // Adjusted to fit below the image
        overloadButton.setBackground(Color.RED);
        overloadButton.setForeground(Color.WHITE);
        frame.add(overloadButton);

        toggleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (toggleButton.isSelected()) {
                    toggleButton.setText("Unplug Charger");
                    dischargeTimer.stop();
                    chargeTimer.start();
                } else {
                    toggleButton.setText("Plug in Charger");
                    chargeTimer.stop();
                    dischargeTimer.start();
                }
            }
        });

        onButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                battery.turnOn();
                indicatorDotLabel.setForeground(Color.GREEN);
            }
        });

        offButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                battery.turnOff();
                indicatorDotLabel.setForeground(Color.RED);
            }
        });

        overloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "BOOM your phone overloaded!");
                System.exit(0);
            }
        });

        dischargeTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                battery.dischargeByOne();
                updateBatteryLabel();
            }
        });

        chargeTimer = new Timer(800, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                battery.chargeByOne();
                updateBatteryLabel();
            }
        });

        dischargeTimer.start();
        frame.setVisible(true);
    }

    private static void updateBatteryLabel() {
        batteryLabel.setText(battery.displayStatus());
        visualLabel.setText(battery.displayVisualRepresentation());

        if (battery.isEmpty()) {
            int response = JOptionPane.showOptionDialog(
                    null,
                    "Battery is empty!",
                    "Warning",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.WARNING_MESSAGE,
                    null,
                    new Object[]{"Plug in Charger"},
                    "Plug in Charger"
            );

            if (response == 0) {
                toggleButton.setSelected(true);
                toggleButton.setText("Unplug Charger");
                dischargeTimer.stop();
                chargeTimer.start();
                battery.turnOff();
                indicatorDotLabel.setForeground(Color.RED);
            }
        }
    }
}
