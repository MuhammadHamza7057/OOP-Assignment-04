import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentLogin extends JDialog{
    private JPanel StudentLogin;
    private JTextField tfID;
    private JPasswordField pfpassword;
    private JComboBox <String> cbsection;
    private JButton btnback;
    private JButton btnOK;
    private JPanel StudentDashboard;

    public StudentLogin(JFrame parent) {
        super(parent, "Student Login", true);
        setSize(450, 500);
        setLocationRelativeTo(null);
        setContentPane(StudentLogin);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModal(true);
        setLocationRelativeTo(parent);


        //Combo box for section
        cbsection.addItem("Select Section");
        cbsection.addItem("BSE-2A");
        cbsection.addItem("BSE-2B");
        cbsection.addItem("BSE-4A");
        cbsection.addItem("BSE-6A");
        cbsection.addItem("BSE-8A");


        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = tfID.getText();
                String password = new String(pfpassword.getPassword());
                String section = cbsection.getSelectedItem().toString();

                if (section.equals("Select Section")) {
                    JOptionPane.showMessageDialog(null, "Please select a section");
                    return;
                }

                if (id.isEmpty() || password.isEmpty() || section.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all fields");
                    return;
                }


                loginedStudent(id, password, section);


            }

            private void loginedStudent(String id, String password, String section) {
                String DB_url = "jdbc:mysql://localhost:3306/universitydata";
                String user = "root";
                String pass = "Hamza123";
                try {
                    Connection con = DriverManager.getConnection(DB_url, user, pass);
                    String query = "SELECT * FROM studentdata WHERE ID = ? AND Password = ? AND Section = ?";
                    PreparedStatement ps = con.prepareStatement(query);
                    ps.setString(1, id);
                    ps.setString(2, password);
                    ps.setString(3, section);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "Login Successful");
                        StudentDashboard studentDashboard = new StudentDashboard(null);
                        studentDashboard.setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid ID, Password or Section");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        setVisible(true);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("StudentLogin");
        frame.setContentPane(new StudentLogin(null).StudentLogin);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        //frame.setVisible(true);
    }
}
