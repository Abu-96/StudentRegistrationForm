import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class StudentForm extends JFrame implements ActionListener {
    JTextField nameField, rollField, emailField;
    JButton submitButton, clearButton;

    public StudentForm() {
        setTitle("Student Registration Form");
        setSize(400, 300);
        setLayout(new GridLayout(5, 2, 10, 10));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();

        JLabel rollLabel = new JLabel("Roll Number:");
        rollField = new JTextField();

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();

        submitButton = new JButton("Submit");
        clearButton = new JButton("Clear");

        add(nameLabel); add(nameField);
        add(rollLabel); add(rollField);
        add(emailLabel); add(emailField);
        add(submitButton); add(clearButton);

        submitButton.addActionListener(this);
        clearButton.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String name = nameField.getText();
            String roll = rollField.getText();
            String email = emailField.getText();

            if (name.isEmpty() || roll.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!");
            } else {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("students.txt", true))) {
                    writer.write(name + "," + roll + "," + email);
                    writer.newLine();
                    JOptionPane.showMessageDialog(this, "Data saved successfully!");
                    nameField.setText("");
                    rollField.setText("");
                    emailField.setText("");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error saving data!");
                }
            }
        } else if (e.getSource() == clearButton) {
            nameField.setText("");
            rollField.setText("");
            emailField.setText("");
        }
    }

    public static void main(String[] args) {
        new StudentForm();
    }
}
