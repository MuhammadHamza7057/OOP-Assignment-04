import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FacultyDashboard {
    private JComboBox cbchoice;
    private JPanel FacultyDashboard;

    public FacultyDashboard() {

        cbchoice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("FacultyDashboard");
        frame.setContentPane(new FacultyDashboard().FacultyDashboard);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
