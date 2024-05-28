import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Daywise extends JDialog{
    private JComboBox <String> cbDaywise;
    private JPanel Daywise;
    private JButton btnSearch;
    private JTable table1;
    private JScrollPane TableScroll;

    public Daywise(JFrame parent) {
        super(parent, "Daywise", true);
        setSize(900, 550);
        setLocationRelativeTo(null);
        setContentPane(Daywise);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModal(true);
        setLocationRelativeTo(parent);


        //ComboBox Code Daywise
        cbDaywise.addItem("Select Day");
        cbDaywise.addItem("Monday");
        cbDaywise.addItem("Tuesday");
        cbDaywise.addItem("Wednesday");
        cbDaywise.addItem("Thursday");
        cbDaywise.addItem("Friday");


        table1.setModel(new DefaultTableModel(new String[]{
                "CourseName",  "CourseID", "CourseType", "Teacher", "Room", "Section", "Timing", "CreditHour", "Day",}, 0));



        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchDaywise();

            }

           private void SearchDaywise() {
                DefaultTableModel model = (DefaultTableModel) table1.getModel();
                model.setRowCount(0);
                String day = cbDaywise.getSelectedItem().toString();
                if (day.equals("Select Day")) {
                    JOptionPane.showMessageDialog(null, "Please select a day");
                    return;
                }
                try {
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/universitydata", "root", "Hamza123");
                    Statement stmt = con.createStatement();
                    String query = "SELECT * FROM course WHERE Day = '" + day + "'";
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        model.addRow(new Object[]{rs.getString("CourseName"), rs.getString("CourseID"), rs.getString("CourseType"), rs.getString("Teacher"), rs.getString("Room"), rs.getString("Section"), rs.getString("Timing"), rs.getString("CreditHour"), rs.getString("Day")});
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
        JFrame frame = new JFrame("Daywise");
        frame.setContentPane(new Daywise(null).Daywise);
       // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
       // frame.setVisible(true);
    }
}
