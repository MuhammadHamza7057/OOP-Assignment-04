import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FacultyDashboard extends JDialog{
    private JComboBox<String> cbchoice;
    private JPanel FacultyDashboard;
    private JButton btnback;
    private JButton btnOK;
    private JPanel AddCourse;
    private JPanel RemoveCourse;
    private JPanel AddMakeupClass;
    private JPanel Sectionwise;
    private JPanel Daywise;
    private JPanel Roomwise;
    private JPanel Teacherwise;
    private JPanel Genarte;

    public FacultyDashboard(JFrame parent) {
        super(parent, "Faculty Dashboard", true);
        setSize(500, 550);
        setLocationRelativeTo(null);
        setContentPane(FacultyDashboard);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModal(true);
        setLocationRelativeTo(parent);
        //ComboBox Code
        cbchoice.addItem("Select Choice");
        cbchoice.addItem("Genarte Time Table");
        cbchoice.addItem("Search Time Table Teacher Wise");
        cbchoice.addItem("Search Room Wise");
        //cbchoice.addItem("Search Course Wise");
        cbchoice.addItem("Search Section Wise");
        cbchoice.addItem("Search Day Wise");
        cbchoice.addItem("Add Course");
        cbchoice.addItem("Remove Course");
        cbchoice.addItem("Add MakeUp Class");







        cbchoice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnback.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Facultylogin facultylogin = new Facultylogin(null);
                facultylogin.setVisible(true);
                dispose();
            }
        });
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String choice = cbchoice.getSelectedItem().toString();
                if (choice.equals("Select Choice")) {
                    JOptionPane.showMessageDialog(null, "Please select a choice");
                    return;
                }
                if (choice.equals("Genarte Time Table")) {
                    Genarte genarte = new Genarte(null);
                    genarte.setVisible(true);
                    //JOptionPane.showMessageDialog(null, "Time Table Generated");
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
                if (choice.equals("Add Course")) {
                    //JOptionPane.showMessageDialog(null, "Course Added");
                    AddCourse addCourse = new AddCourse(null);
                    addCourse.setVisible(true);

                    return;

                }
                if (choice.equals("Remove Course")) {
                    //JOptionPane.showMessageDialog(null, "Course Removed");
                    RemoveCourse removeCourse = new RemoveCourse(null);
                    removeCourse.setVisible(true);
                    return;
                }
                if (choice.equals("Add MakeUp Class")) {
                    AddMakeupClass addMakeupClass = new AddMakeupClass(null);
                    addMakeupClass.setVisible(true);
                    //JOptionPane.showMessageDialog(null, "MakeUp Class Added");
                    return;
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("FacultyDashboard");
        frame.setContentPane(new FacultyDashboard(null).FacultyDashboard);
       // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        //frame.setVisible(true);
    }


}
