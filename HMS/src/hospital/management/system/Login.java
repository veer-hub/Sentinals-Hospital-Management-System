package hospital.management.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;

public class Login extends JFrame implements ActionListener{
    
    JTextField textField;
    JPasswordField jPasswordField;
    JButton b1,b2;
   
    Login(){

        JLabel namelaLabel= new JLabel( "Username");
        namelaLabel.setBounds(40, 20, 100, 30);
        namelaLabel.setFont(new Font("Tahoma",Font.BOLD,16));
        namelaLabel.setForeground(Color.black);
        add(namelaLabel);

        JLabel password= new JLabel( "Password");
        password.setBounds(40, 70, 100, 30);
        password.setFont(new Font("Tahoma",Font.BOLD,16));
        password.setForeground(Color.black);
        add(password);

        textField= new JTextField();
        textField.setBounds(150,20,150,30);
        textField.setFont(new Font("Tahoma",Font.PLAIN,15));
        textField.setBackground(new Color(207, 169, 33));
        add(textField);

        jPasswordField = new JPasswordField();
        jPasswordField.setBounds(150, 70, 150, 30);
        jPasswordField.setFont(new Font("Tahoma",Font.PLAIN,15));
        jPasswordField.setBackground(new Color(207, 169, 33));
        add(jPasswordField);


        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/login.png"));
        Image i1  = imageIcon.getImage().getScaledInstance(500,329,Image.SCALE_DEFAULT);
        ImageIcon imageIcon1  = new ImageIcon(i1);
        JLabel label = new JLabel(imageIcon1);
        label.setBounds(320, -30, 400, 300);
        add(label);

        b1= new JButton("Login");
        b1.setBounds(40, 140, 120, 30);
        b1.setFont(new Font("serif",Font.BOLD,15));
        b1.addActionListener(this);
        add(b1);



        b2 = new JButton("Clear");
        b2.setBounds(180, 140, 120, 30);
        b2.setFont(new Font("serif",Font.BOLD,15));
        b1.addActionListener(this);
        add(b2);



        getContentPane().setBackground(new Color(109,164,170));
        setSize(750,300);
        setLocation(400 , 270);
        setLayout(null);
        setVisible(true);


    }
    public static void main(String[] args) {
        new Login();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            try {
                conn c = new conn();  // Establish the connection
                String user = textField.getText();
                String Pass = new String(jPasswordField.getPassword()); // Use getPassword() to get the password
    
                // Secure query to avoid SQL injection
                String q = "SELECT * FROM login WHERE ID = ? AND PW = ?";
                PreparedStatement preparedStatement = c.connection.prepareStatement(q);
                preparedStatement.setString(1, user);
                preparedStatement.setString(2, Pass);
                ResultSet resultSet = preparedStatement.executeQuery();
    
                // Check if login is successful
                if (resultSet.next()) {
                    // Successful login, move to the Reception class
                    new Reception();
                    setVisible(false);  // Hide the Login window
                } else {
                    // Invalid credentials
                    JOptionPane.showMessageDialog(null, "Invalid username or password");
                }
    
            } catch (Exception E) {
                E.printStackTrace();
            }
        } else {
            // Close the application if the exit button is clicked
            System.exit(0);
        }
    }
}