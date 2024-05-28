import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Teacherwise extends JDialog{
    private JPanel Teacherwise;
    private JTextField tfTeachername;
    private JButton btnSearch;
    private JTable table1;
    private JScrollPane tableScroll;

    public Teacherwise(JFrame parent) {
        super(parent, "Teacherwise", true);
        setSize(900, 550);
        setLocationRelativeTo(null);
        setContentPane(Teacherwise);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModal(true);
        setLocationRelativeTo(parent);


        table1.setModel(new DefaultTableModel(new String[]{
                "CourseName",  "CourseID", "CourseType", "Teacher", "Room", "Section", "Timing", "CreditHour", "Day",}, 0));


        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                SearchTeacherwise();

            }

            private void SearchTeacherwise() {
                DefaultTableModel model = (DefaultTableModel) table1.getModel();
                model.setRowCount(0);
                String teachername = tfTeachername.getText();
                if (teachername.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter a teacher name");
                    return;
                }
                try {
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/universitydata", "root", "Hamza123");
                    Statement stmt = con.createStatement();
                    String query = "SELECT * FROM course WHERE Teacher = '" + teachername + "'";
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        model.addRow(new Object[]{rs.getString("CourseName"), rs.getString("CourseID"), rs.getString("CourseType"), rs.getString("Teacher"), rs.getString("Room"), rs.getString("Section"), rs.getString("Timing"), rs.getString("CreditHour"), rs.getString("Day")});
                    }
                    con.close();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Teacherwise");
        frame.setContentPane(new Teacherwise(frame).Teacherwise);
       // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
       // frame.setVisible(true);
    }

}
