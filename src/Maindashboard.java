import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Maindashboard extends JDialog {
    private JButton btnfacultymember;
    private JButton btnstudent;
    private JPanel Maindashboard;
    private JPanel FacultyRegistration;
    private JPanel StudentRegistration;

    public Maindashboard(JFrame parent) {
        super(parent, "Main Dashboard", true);
        setSize(500, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(Maindashboard);
        setModal(true);
        setLocationRelativeTo(parent);
        //cbchoice.additem("1");



       /* btnfacultymember.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        });*/


        btnstudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StudentRegistration studentRegistration = new StudentRegistration(null);
                studentRegistration.setVisible(true);

            }
        });
        btnfacultymember.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FacultyRegistration facultyRegistration = new FacultyRegistration(null);
                facultyRegistration.setVisible(true);
            }
        });

        setVisible(true);
    }


    public static void main(String[] args) {
        Maindashboard dialog = new Maindashboard(null);
        dialog.pack();
        //dialog.setVisible(true);


    }
}
