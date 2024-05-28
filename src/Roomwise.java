import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Roomwise extends JDialog{
    private JPanel Roomwise;
    private JComboBox <String> cbsection;
    private JButton btnSearch;
    private JTable table1;
    private JScrollPane TableScroll;


    public Roomwise(JFrame parent) {
        super(parent, "Roomwise", true);
        setSize(950, 500);
        setLocationRelativeTo(null);
        setContentPane(Roomwise);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModal(true);
        setLocationRelativeTo(parent);




        //ComboBox Code Roomwise
        cbsection.addItem("Select Room");
        cbsection.addItem("4-01");
        cbsection.addItem("4-02");
        cbsection.addItem("4-17");
        cbsection.addItem("4-18");
        cbsection.addItem("4-19");



        table1.setModel(new DefaultTableModel(new String[]{
                "CourseName",  "CourseID", "CourseType", "Teacher", "Room", "Section", "Timing", "CreditHour", "Day",}, 0));


        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchRoomwise();

            }

            private void SearchRoomwise() {
                DefaultTableModel model = (DefaultTableModel) table1.getModel();
                model.setRowCount(0);
                String section = cbsection.getSelectedItem().toString();
                if (section.equals("Select Section")) {
                    JOptionPane.showMessageDialog(null, "Please select a section");
                    return;
                }
                try {
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/universitydata", "root", "Hamza123");
                    Statement stmt = con.createStatement();
                    String query = "SELECT * FROM course WHERE Room = '" + section + "'";
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        model.addRow(new Object[]{rs.getString("CourseName"), rs.getString("CourseID"), rs.getString("CourseType"), rs.getString("Teacher"), rs.getString("Room"), rs.getString("Section"), rs.getString("Timing"), rs.getString("CreditHour"), rs.getString("Day")});
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }


            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Roomwise");
        frame.setContentPane(new Roomwise(null).Roomwise);
       // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
       // frame.setVisible(true);
    }
}
