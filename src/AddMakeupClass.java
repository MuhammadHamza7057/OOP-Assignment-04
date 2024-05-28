import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class AddMakeupClass extends JDialog {
    private JTextField tfcoursename;
    private JTextField tfcourseid;
    private JComboBox <String> cbcoursetype;
    private JTextField tfteacher;
    private JComboBox <String> cbroom;
    private JComboBox <String> cbsection;
    private JComboBox <String> cbtiming;
    private JComboBox <String> cbcredithour;
    private JComboBox <String> cbday;
    private JButton btnadd;
    private JPanel AddMakeupClass;

    public AddMakeupClass(JFrame parent) {
        super(parent, "Add Makeup Class", true);
        setSize(500, 600);
        setLocationRelativeTo(null);
        setContentPane(AddMakeupClass);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModal(true);
        setLocationRelativeTo(parent);


        //ComboBox Code course type

        cbcoursetype.addItem("Select Course Type");
        cbcoursetype.addItem("Theory");
        cbcoursetype.addItem("Lab");

        //ComboBox Code select room
        cbroom.addItem("Select Room");
        cbroom.addItem("4-01");
        cbroom.addItem("4-02");
        cbroom.addItem("4-17");
        cbroom.addItem("4-18");
        cbroom.addItem("4-19");


        //ComboBox Code section
        cbsection.addItem("Select Section");
        cbsection.addItem("BSE-2A");
        cbsection.addItem("BSE-2B");
        cbsection.addItem("BSE-4A");
        cbsection.addItem("BSE-6A");
        cbsection.addItem("BSE-8A");


        //ComboBox Code timing
        cbtiming.addItem("Select Timing");
        cbtiming.addItem("8:30-9:25");
        cbtiming.addItem("9:30-10:25");
        cbtiming.addItem("10:30-11:25");
        cbtiming.addItem("11:30-12:25");
        cbtiming.addItem("12:30-1:25");
        cbtiming.addItem("1:30-2:25");
        cbtiming.addItem("2:30-3:25");
        cbtiming.addItem("3:30-4:25");
        cbtiming.addItem("4:30-5:25");


        //ComboBox Code credit hour
        cbcredithour.addItem("Select Credit Hour");
        cbcredithour.addItem("0");
        cbcredithour.addItem("1");
        cbcredithour.addItem("2");
        cbcredithour.addItem("3");



        //ComboBox Code day
        cbday.addItem("Select Day");
        cbday.addItem("Monday");
        cbday.addItem("Tuesday");
        cbday.addItem("Wednesday");
        cbday.addItem("Thursday");
        cbday.addItem("Friday");



        btnadd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String coursename = tfcoursename.getText();
                String courseid = tfcourseid.getText();
                String coursetype = cbcoursetype.getSelectedItem().toString();
                String teacher = tfteacher.getText();
                String room = cbroom.getSelectedItem().toString();
                String section = cbsection.getSelectedItem().toString();
                String timing = cbtiming.getSelectedItem().toString();
                String credithour = cbcredithour.getSelectedItem().toString();
                String day = cbday.getSelectedItem().toString();
                if (coursename.equals("") || courseid.equals("") || coursetype.equals("") || teacher.equals("") || room.equals("") || section.equals("") || timing.equals("") || credithour.equals("") || day.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please fill all the fields");
                    return;
                }

                if (coursetype == "Lab" && day == "Monday"){
                    JOptionPane.showMessageDialog(null, "Lab Course cannot be added as Makeup Class");
                    return;
                }

                addMakeupClass(coursename, courseid, coursetype, teacher, room, section, timing, credithour, day);

            }

            private void addMakeupClass(String coursename, String courseid, String coursetype, String teacher, String room, String section, String timing, String credithour, String day) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/universitydata", "root", "Hamza123");
                    String query = "INSERT INTO makeupclass (coursename, courseid, coursetype, teacher, room, section, timing, credithour, day) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, coursename);
                    preparedStatement.setString(2, courseid);
                    preparedStatement.setString(3, coursetype);
                    preparedStatement.setString(4, teacher);
                    preparedStatement.setString(5, room);
                    preparedStatement.setString(6, section);
                    preparedStatement.setString(7, timing);
                    preparedStatement.setString(8, credithour);
                    preparedStatement.setString(9, day);
                    preparedStatement.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Makeup Class Added");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                }

                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/universitydata", "root", "Hamza123");
                    String query = "INSERT INTO course (coursename, courseid, coursetype, teacher, room, section, timing, credithour, day) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, tfcoursename.getText());
                    preparedStatement.setString(2, tfcourseid.getText());
                    preparedStatement.setString(3, cbcoursetype.getSelectedItem().toString());
                    preparedStatement.setString(4, tfteacher.getText());
                    preparedStatement.setString(5, cbroom.getSelectedItem().toString());
                    preparedStatement.setString(6, cbsection.getSelectedItem().toString());
                    preparedStatement.setString(7, cbtiming.getSelectedItem().toString());
                    preparedStatement.setString(8, cbcredithour.getSelectedItem().toString());
                    preparedStatement.setString(9, cbday.getSelectedItem().toString());
                    preparedStatement.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Makeup Class Added");
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "Error: " + e1.getMessage());
                }
            }

        });
        setVisible(true);
        btnadd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (cbsection.getSelectedItem().toString().equals("Select Section")) {
                    JOptionPane.showMessageDialog(null, "Please select a section");
                    return;
                }
                if (cbsection.getSelectedItem().toString().equals("BSE-2A")) {
                    try {
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/universitydata", "root", "Hamza123");
                        String query = "INSERT INTO bse2 (coursename, courseid, coursetype, teacher, room, section, timing, credithour, day) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                        PreparedStatement preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, tfcoursename.getText());
                        preparedStatement.setString(2, tfcourseid.getText());
                        preparedStatement.setString(3, cbcoursetype.getSelectedItem().toString());
                        preparedStatement.setString(4, tfteacher.getText());
                        preparedStatement.setString(5, cbroom.getSelectedItem().toString());
                        preparedStatement.setString(6, cbsection.getSelectedItem().toString());
                        preparedStatement.setString(7, cbtiming.getSelectedItem().toString());
                        preparedStatement.setString(8, cbcredithour.getSelectedItem().toString());
                        preparedStatement.setString(9, cbday.getSelectedItem().toString());
                        preparedStatement.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Makeup Class Added");
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(null, "Error: " + e1.getMessage());
                    }
                }

                if (cbsection.getSelectedItem().toString().equals("BSE-2B")) {
                    try {
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/universitydata", "root", "Hamza123");
                        String query = "INSERT INTO bse2b (coursename, courseid, coursetype, teacher, room, section, timing, credithour, day) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                        PreparedStatement preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, tfcoursename.getText());
                        preparedStatement.setString(2, tfcourseid.getText());
                        preparedStatement.setString(3, cbcoursetype.getSelectedItem().toString());
                        preparedStatement.setString(4, tfteacher.getText());
                        preparedStatement.setString(5, cbroom.getSelectedItem().toString());
                        preparedStatement.setString(6, cbsection.getSelectedItem().toString());
                        preparedStatement.setString(7, cbtiming.getSelectedItem().toString());
                        preparedStatement.setString(8, cbcredithour.getSelectedItem().toString());
                        preparedStatement.setString(9, cbday.getSelectedItem().toString());
                        preparedStatement.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Makeup Class Added");
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(null, "Error: " + e1.getMessage());
                    }
                }

                if (cbsection.getSelectedItem().toString().equals("BSE-4A")) {
                    try {
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/universitydata", "root", "Hamza123");
                        String query = "INSERT INTO bse4 (coursename, courseid, coursetype, teacher, room, section, timing, credithour, day) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                        PreparedStatement preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, tfcoursename.getText());
                        preparedStatement.setString(2, tfcourseid.getText());
                        preparedStatement.setString(3, cbcoursetype.getSelectedItem().toString());
                        preparedStatement.setString(4, tfteacher.getText());
                        preparedStatement.setString(5, cbroom.getSelectedItem().toString());
                        preparedStatement.setString(6, cbsection.getSelectedItem().toString());
                        preparedStatement.setString(7, cbtiming.getSelectedItem().toString());
                        preparedStatement.setString(8, cbcredithour.getSelectedItem().toString());
                        preparedStatement.setString(9, cbday.getSelectedItem().toString());
                        preparedStatement.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Makeup Class Added");
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(null, "Error: " + e1.getMessage());
                    }
                }
                if (cbsection.getSelectedItem().toString().equals("BSE-6A")) {
                    try {
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/universitydata", "root", "Hamza123");
                        String query = "INSERT INTO bse6 (coursename, courseid, coursetype, teacher, room, section, timing, credithour, day) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                        PreparedStatement preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, tfcoursename.getText());
                        preparedStatement.setString(2, tfcourseid.getText());
                        preparedStatement.setString(3, cbcoursetype.getSelectedItem().toString());
                        preparedStatement.setString(4, tfteacher.getText());
                        preparedStatement.setString(5, cbroom.getSelectedItem().toString());
                        preparedStatement.setString(6, cbsection.getSelectedItem().toString());
                        preparedStatement.setString(7, cbtiming.getSelectedItem().toString());
                        preparedStatement.setString(8, cbcredithour.getSelectedItem().toString());
                        preparedStatement.setString(9, cbday.getSelectedItem().toString());
                        preparedStatement.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Makeup Class Added");
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(null, "Error: " + e1.getMessage());
                    }
                }

                if (cbsection.getSelectedItem().toString().equals("BSE-8A")) {
                    try {
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/universitydata", "root", "Hamza123");
                        String query = "INSERT INTO bse8 (coursename, courseid, coursetype, teacher, room, section, timing, credithour, day) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                        PreparedStatement preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, tfcoursename.getText());
                        preparedStatement.setString(2, tfcourseid.getText());
                        preparedStatement.setString(3, cbcoursetype.getSelectedItem().toString());
                        preparedStatement.setString(4, tfteacher.getText());
                        preparedStatement.setString(5, cbroom.getSelectedItem().toString());
                        preparedStatement.setString(6, cbsection.getSelectedItem().toString());
                        preparedStatement.setString(7, cbtiming.getSelectedItem().toString());
                        preparedStatement.setString(8, cbcredithour.getSelectedItem().toString());
                        preparedStatement.setString(9, cbday.getSelectedItem().toString());
                        preparedStatement.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Makeup Class Added");
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(null, "Error: " + e1.getMessage());
                    }
                }

            }
        });
    }




    public static void main(String[] args) {
        JFrame frame = new JFrame("AddMakeupClass");
        frame.setContentPane(new AddMakeupClass(null).AddMakeupClass);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
    }
}
