import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentRegistration extends JDialog{
    private JPanel StudentRegistration;
    private JTextField tfID;
    private JPasswordField pfpassword;
    private JComboBox<String> cbSection;
    private JButton btnLogin;
    private JButton btnOK;
    private String section;
    private String id;
    private String password;
    private JPanel StudentLogin;

    public StudentRegistration(JFrame parent) {
        super(parent, "Student Registration", true);
        setSize(450, 500);
        setLocationRelativeTo(null);
        setContentPane(StudentRegistration);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModal(true);
        setLocationRelativeTo(parent);

        // Combo box for section
        cbSection.addItem("Select Section");
        cbSection.addItem("BSE-2A");
        cbSection.addItem("BSE-2B");
        cbSection.addItem("BSE-4A");
        cbSection.addItem("BSE-6A");
        cbSection.addItem("BSE-8A");

        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = tfID.getText();
                String password = new String(pfpassword.getPassword());
                String section = cbSection.getSelectedItem().toString();

                if (section.equals("Select Section")) {
                    JOptionPane.showMessageDialog(null, "Please select a section");
                    return;
                }

                if (id.isEmpty() || password.isEmpty() || section.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all fields");
                    return;
                }

                boolean isAdded = addStudentToDatabase(id, password, section);
                if (isAdded) {
                    JOptionPane.showMessageDialog(null, "Student added successfully");
                    StudentLogin studentLogin = new StudentLogin(null);
                    studentLogin.setVisible(true);
                    dispose(); // Dispose after showing the message and opening the login page
                } else {
                    JOptionPane.showMessageDialog(null, "Student not added");
                }
            }
        });

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StudentLogin studentLogin = new StudentLogin(null);
                studentLogin.setVisible(true);
            }
        });

        setVisible(true);
    }

    private boolean addStudentToDatabase(String id, String password, String section) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/universitydata", "root", "Hamza123");
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO studentdata (id, password, section) VALUES (?, ?, ?)");
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, section);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Student Registration");
        frame.setContentPane(new StudentRegistration(null).StudentRegistration);
        frame.pack();
    }
}
