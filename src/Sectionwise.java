import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Sectionwise extends JDialog{
    private JButton searchButton;
    private JComboBox <String> cbSearch;
    private JPanel Sectionwise;
    private JTable table1;
    private JScrollPane Scrolltable;

    public Sectionwise(JFrame parent) {
        super(parent, "Sectionwise", true);
        setSize(900, 550);
        setLocationRelativeTo(null);
        setContentPane(Sectionwise);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModal(true);
        setLocationRelativeTo(parent);


        //ComboBox Code sectionwise
        cbSearch.addItem("Select Section");
        cbSearch.addItem("BSE-2A");
        cbSearch.addItem("BSE-2B");
        cbSearch.addItem("BSE-4A");
        cbSearch.addItem("BSE-6A");
        cbSearch.addItem("BSE-8A");


        table1.setModel(new DefaultTableModel(new String[]{
                "CourseName",  "CourseID", "CourseType", "Teacher", "Room", "Section", "Timing", "CreditHour", "Day",}, 0));



        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchsectionwise();

            }

            private void searchsectionwise() {
                DefaultTableModel model = (DefaultTableModel) table1.getModel();
                model.setRowCount(0);
                String section = cbSearch.getSelectedItem().toString();
                if (section.equals("Select Section")) {
                    JOptionPane.showMessageDialog(null, "Please select a section");
                    return;
                }
                try {
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/universitydata", "root", "Hamza123");
                    Statement stmt = con.createStatement();
                    String query = "SELECT * FROM course WHERE Section = '" + section + "'";
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        String CourseName = rs.getString("CourseName");
                        String CourseID = rs.getString("CourseID");
                        String CourseType = rs.getString("CourseType");
                        String Teacher = rs.getString("Teacher");
                        String Room = rs.getString("Room");
                        String Section = rs.getString("Section");
                        String Timing = rs.getString("Timing");
                        String CreditHour = rs.getString("CreditHour");
                        String Day = rs.getString("Day");
                        model.addRow(new Object[]{CourseName, CourseID, CourseType, Teacher, Room, Section, Timing, CreditHour, Day});
                    }
                    con.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        });
        setVisible(true);

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sectionwise");
        frame.setContentPane(new Sectionwise(null).Sectionwise);
       // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        //frame.setVisible(true);
    }
}