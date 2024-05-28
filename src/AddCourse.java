import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class AddCourse extends JDialog {
    private JPanel AddCourse;
    private JTextField tfcourseID;
    private JComboBox<String> cbCoursetype;
    private JTextField tfteacher;
    private JComboBox<String> cbsection;
    private JComboBox<String> cbtiming;
    private JComboBox<String> cbcredithour;
    private JComboBox<String> cbday;
    private JButton btnadd;
    private JTextField tfname;
    private JComboBox<String> cbroom;
    private String coursename;
    private String courseID;
    private String coursetype;
    private String teacher;
    private String room;
    private String section;
    private String timing;
    private String credithour;
    private String day;

    public AddCourse(JFrame parent) {
        super(parent, "Add Course", true);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setContentPane(AddCourse);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModal(true);
        setLocationRelativeTo(parent);


        //ComboBox Code course type
        cbCoursetype.addItem("Select Course Type");
        cbCoursetype.addItem("Theory");
        cbCoursetype.addItem("Lab");

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


        //ComboBox Code room
        cbroom.addItem("Select Room");
        cbroom.addItem("4-01");
        cbroom.addItem("4-02");
        cbroom.addItem("4-19");
        cbroom.addItem("4-18");
        cbroom.addItem("4-17");

        btnadd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String courseID = tfcourseID.getText();
                String coursename = tfname.getText();
                String coursetype = cbCoursetype.getSelectedItem().toString();
                String teacher = tfteacher.getText();
                String room = cbroom.getSelectedItem().toString();
                String section = cbsection.getSelectedItem().toString();
                String timing = cbtiming.getSelectedItem().toString();
                String credithour = cbcredithour.getSelectedItem().toString();
                String day = cbday.getSelectedItem().toString();

                if (courseID.isEmpty() || coursename.isEmpty() || coursetype.isEmpty() || teacher.isEmpty() || room.isEmpty() || section.isEmpty() || timing.isEmpty() || credithour.isEmpty() || day.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all fields");
                    return;
                }

                if (coursetype == "Lab" && day == "Monday"){
                    JOptionPane.showMessageDialog(null, "Lab Course cannot be added as Makeup Class");
                    return;
                }

                addCourseToDatabase(courseID, coursename, coursetype, teacher, room, section, timing, credithour, day);
            }

            private void addCourseToDatabase(String courseID, String coursename, String coursetype, String teacher, String room, String section, String timing, String credithour, String day) {
                try {
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/universitydata", "root", "Hamza123");
                    String query = "INSERT INTO course (courseID, coursename, coursetype, teacher, room, section, timing, credithour, day) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement preparedStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                    preparedStatement.setString(1, courseID);
                    preparedStatement.setString(2, coursename);
                    preparedStatement.setString(3, coursetype);
                    preparedStatement.setString(4, teacher);
                    preparedStatement.setString(5, room);
                    preparedStatement.setString(6, section);
                    preparedStatement.setString(7, timing);
                    preparedStatement.setString(8, credithour);
                    preparedStatement.setString(9, day);

                    preparedStatement.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Course added successfully");
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                setVisible(true);
            }
        });


        btnadd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String section = cbsection.getSelectedItem().toString();
                if (section.equals("BSE-2A")) {

                    String courseID = tfcourseID.getText();
                    String coursename = tfname.getText();
                    String coursetype = cbCoursetype.getSelectedItem().toString();
                    String teacher = tfteacher.getText();
                    String room = cbroom.getSelectedItem().toString();
                    String timing = cbtiming.getSelectedItem().toString();
                    String credithour = cbcredithour.getSelectedItem().toString();
                    String day = cbday.getSelectedItem().toString();




                    try {
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/universitydata", "root", "Hamza123");
                        String query = "INSERT INTO bse2 (courseid, coursename, coursetype, teacher, room, section, timing, credithour, day) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                        PreparedStatement preparedStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                        preparedStatement.setString(1, courseID);
                        preparedStatement.setString(2, coursename);
                        preparedStatement.setString(3, coursetype);
                        preparedStatement.setString(4, teacher);
                        preparedStatement.setString(5, room);
                        preparedStatement.setString(6, section);
                        preparedStatement.setString(7, timing);
                        preparedStatement.setString(8, credithour);
                        preparedStatement.setString(9, day);

                        preparedStatement.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Course added successfully");
                        con.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }


                    if (courseID.isEmpty() && coursename.isEmpty() && coursetype.isEmpty() && teacher.isEmpty() && room.isEmpty() && section.isEmpty() && timing.isEmpty() && credithour.isEmpty() && day.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please fill all fields");
                        return;
                    }


                } else if (section.equals("BSE-2B")) {


                    String courseID = tfcourseID.getText();
                    String coursename = tfname.getText();
                    String coursetype = cbCoursetype.getSelectedItem().toString();
                    String teacher = tfteacher.getText();
                    String room = cbroom.getSelectedItem().toString();
                    String timing = cbtiming.getSelectedItem().toString();
                    String credithour = cbcredithour.getSelectedItem().toString();
                    String day = cbday.getSelectedItem().toString();




                    try {
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/universitydata", "root", "Hamza123");
                        String query = "INSERT INTO bse2b (courseid, coursename, coursetype, teacher, room, section, timing, credithour, day) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                        PreparedStatement preparedStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                        preparedStatement.setString(1, courseID);
                        preparedStatement.setString(2, coursename);
                        preparedStatement.setString(3, coursetype);
                        preparedStatement.setString(4, teacher);
                        preparedStatement.setString(5, room);
                        preparedStatement.setString(6, section);
                        preparedStatement.setString(7, timing);
                        preparedStatement.setString(8, credithour);
                        preparedStatement.setString(9, day);

                        preparedStatement.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Course added successfully");
                        con.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }


                    if (courseID.isEmpty() && coursename.isEmpty() && coursetype.isEmpty() && teacher.isEmpty() && room.isEmpty() && section.isEmpty() && timing.isEmpty() && credithour.isEmpty() && day.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please fill all fields");
                        return;
                    }



                } else if (section.equals("BSE-4A")) {


                    String courseID = tfcourseID.getText();
                    String coursename = tfname.getText();
                    String coursetype = cbCoursetype.getSelectedItem().toString();
                    String teacher = tfteacher.getText();
                    String room = cbroom.getSelectedItem().toString();
                    String timing = cbtiming.getSelectedItem().toString();
                    String credithour = cbcredithour.getSelectedItem().toString();
                    String day = cbday.getSelectedItem().toString();




                    try {
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/universitydata", "root", "Hamza123");
                        String query = "INSERT INTO bse4 (courseid, coursename, coursetype, teacher, room, section, timing, credithour, day) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                        PreparedStatement preparedStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                        preparedStatement.setString(1, courseID);
                        preparedStatement.setString(2, coursename);
                        preparedStatement.setString(3, coursetype);
                        preparedStatement.setString(4, teacher);
                        preparedStatement.setString(5, room);
                        preparedStatement.setString(6, section);
                        preparedStatement.setString(7, timing);
                        preparedStatement.setString(8, credithour);
                        preparedStatement.setString(9, day);

                        preparedStatement.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Course added successfully");
                        con.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }




                    //tfteacher.setText("Mr. Usman");
                } else if (section.equals("BSE-6A")) {



                    String courseID = tfcourseID.getText();
                    String coursename = tfname.getText();
                    String coursetype = cbCoursetype.getSelectedItem().toString();
                    String teacher = tfteacher.getText();
                    String room = cbroom.getSelectedItem().toString();
                    String timing = cbtiming.getSelectedItem().toString();
                    String credithour = cbcredithour.getSelectedItem().toString();
                    String day = cbday.getSelectedItem().toString();




                    try {
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/universitydata", "root", "Hamza123");
                        String query = "INSERT INTO bse6 (courseid, coursename, coursetype, teacher, room, section, timing, credithour, day) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                        PreparedStatement preparedStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                        preparedStatement.setString(1, courseID);
                        preparedStatement.setString(2, coursename);
                        preparedStatement.setString(3, coursetype);
                        preparedStatement.setString(4, teacher);
                        preparedStatement.setString(5, room);
                        preparedStatement.setString(6, section);
                        preparedStatement.setString(7, timing);
                        preparedStatement.setString(8, credithour);
                        preparedStatement.setString(9, day);

                        preparedStatement.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Course added successfully");
                        con.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }



                    //tfteacher.setText("Mr. Saad");
                } else if (section.equals("BSE-8A")) {


                    String courseID = tfcourseID.getText();
                    String coursename = tfname.getText();
                    String coursetype = cbCoursetype.getSelectedItem().toString();
                    String teacher = tfteacher.getText();
                    String room = cbroom.getSelectedItem().toString();
                    String timing = cbtiming.getSelectedItem().toString();
                    String credithour = cbcredithour.getSelectedItem().toString();
                    String day = cbday.getSelectedItem().toString();




                    try {
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/universitydata", "root", "Hamza123");
                        String query = "INSERT INTO bse8 (courseid, coursename, coursetype, teacher, room, section, timing, credithour, day) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                        PreparedStatement preparedStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                        preparedStatement.setString(1, courseID);
                        preparedStatement.setString(2, coursename);
                        preparedStatement.setString(3, coursetype);
                        preparedStatement.setString(4, teacher);
                        preparedStatement.setString(5, room);
                        preparedStatement.setString(6, section);
                        preparedStatement.setString(7, timing);
                        preparedStatement.setString(8, credithour);
                        preparedStatement.setString(9, day);

                        preparedStatement.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Course added successfully");
                        con.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }


                    //tfteacher.setText("Mr. Ahmed");
                }

            }
        });
    }

    public static void main(String[] args) {
        AddCourse dialog = new AddCourse(null);
        dialog.setVisible(true);
        //System.exit(0);
    }
}
