import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Genarte extends JDialog{
    private JComboBox <String> cbgenarte;
    private JButton btnGenarate;
    private JPanel Genarte;
    private JTable table1;
    private JScrollPane tablescroll;




    public Genarte(JFrame parent) {
        super(parent, "Genarte", true);
        setSize(900, 550);
        setLocationRelativeTo(null);
        setContentPane(Genarte);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModal(true);
        setLocationRelativeTo(parent);



        //ComboBox Code Genarte
        cbgenarte.addItem("Select Genarte");
        cbgenarte.addItem("Genarte BSE-2A");
        cbgenarte.addItem("Genarte BSE-2B");
        cbgenarte.addItem("Genarte BSE-4A");
        cbgenarte.addItem("Genarte BSE-6A");
        cbgenarte.addItem("Genarte BSE-8A");


        table1.setModel(new DefaultTableModel(new String[]{
                "CourseName",  "CourseID", "CourseType", "Teacher", "Room", "Section", "Timing", "CreditHour", "Day",}, 0));


        btnGenarate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Genarte();

            }

            private void Genarte() {
                DefaultTableModel model = (DefaultTableModel) table1.getModel();
                model.setRowCount(0);
                String genarte = cbgenarte.getSelectedItem().toString();
                if (genarte.equals("Select Genarte")) {
                    JOptionPane.showMessageDialog(null, "Please select a Genarte");
                    return;
                }
                try {
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/universitydata", "root", "Hamza123");
                    Statement stmt = con.createStatement();
                    String query = "SELECT * FROM course WHERE Section = '" + genarte + "'";
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
        JFrame frame = new JFrame("Genarte");
        frame.setContentPane(new Genarte(frame).Genarte);
       // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
       // frame.setVisible(true);
    }
}
