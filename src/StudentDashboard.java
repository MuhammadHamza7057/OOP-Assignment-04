import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentDashboard extends JDialog{
    private JPanel StudentDashboard;
    private JComboBox <String> cbchoice;
    private JButton btnSearch;
    private JPanel Sectionwise;
    private JPanel Daywise;
    private JPanel Roomwise;
    private JPanel Teacherwise;

    public StudentDashboard(JFrame parent) {
        super(parent, "Student Dashboard", true);
        setSize(500, 550);
        setLocationRelativeTo(null);
        setContentPane(StudentDashboard);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModal(true);
        setLocationRelativeTo(parent);


        //ComboBox Code for Student Dashboard
        cbchoice.addItem("Select Choice");
        cbchoice.addItem("Search Time Table Teacher Wise");
        cbchoice.addItem("Search Room Wise");
        cbchoice.addItem("Search Section Wise");
        cbchoice.addItem("Search Day Wise");




        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String choice = cbchoice.getSelectedItem().toString();
                if (choice.equals("Select Choice")) {
                    JOptionPane.showMessageDialog(null, "Please select a choice");
                    return;
                }
                if (choice.equals("Search Time Table Teacher Wise")) {
                    Teacherwise teacherwise = new Teacherwise(null);
                    teacherwise.setVisible(true);
                    //JOptionPane.showMessageDialog(null, "Time Table Teacher Wise");
                    return;
                }
                if (choice.equals("Search Room Wise")) {
                    Roomwise roomwise = new Roomwise(null);
                    roomwise.setVisible(true);


                    //JOptionPane.showMessageDialog(null, "Room Wise");
                    return;
                }

                if (choice.equals("Search Section Wise")) {
                    Sectionwise sectionwise = new Sectionwise(null);
                    sectionwise.setVisible(true);
                    // JOptionPane.showMessageDialog(null, "Section Wise");
                    return;
                }
                if (choice.equals("Search Day Wise")) {
                    Daywise daywise = new Daywise(null);
                    daywise.setVisible(true);
                    //JOptionPane.showMessageDialog(null, "Day Wise");
                    return;
                }


            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("StudentDashboard");
        frame.setContentPane(new StudentDashboard(null).StudentDashboard);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
      //  frame.setVisible(true);
    }
}
