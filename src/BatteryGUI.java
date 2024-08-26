import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BatteryGUI {
    private static Battery battery;
    private static JLabel batteryLabel;

    public static void main(String[] args) {
        battery = new Battery(100);

        JFrame frame = new JFrame("Battery Percentage Simulator");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        batteryLabel = new JLabel(battery.displayStatus(), SwingConstants.CENTER);
        batteryLabel.setBounds(50, 30, 200, 30);
        frame.add(batteryLabel);

        JButton chargeButton = new JButton("Charge to 100%");
        chargeButton.setBounds(50, 80, 200, 30);
        frame.add(chargeButton);

        chargeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                battery.chargeToFull();
                updateBatteryLabel();
            }
        });

        frame.setVisible(true);

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                battery.dischargeByOne();
                updateBatteryLabel();
            }
        });
        timer.start();
    }

    private static void updateBatteryLabel() {
        batteryLabel.setText(battery.displayStatus());
        if (battery.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Battery is empty!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
}
