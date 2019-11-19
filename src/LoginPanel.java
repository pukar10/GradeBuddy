import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginPanel extends JPanel implements ActionListener {

    Panel1 panel1;
    JButton login, register;
    JTextField user, pass;
    JLabel username, password;

    public LoginPanel() throws IOException {

        panel1 = new Panel1();

        setLayout(null);
        setBounds(0, 0, 800, 600);

        username = new JLabel("Username");
        username.setBounds(250, 250, 75, 20);
        password = new JLabel("Password");
        password.setBounds(250, 300, 75, 20);

        user = new JTextField();
        user.setBounds(350, 250, 75, 20);
        pass = new JTextField();
        pass.setBounds(350, 300, 75, 20);

        login = new JButton("Login");
        login.setBounds(250, 350, 75, 20);
        register = new JButton("Register");
        register.setBounds(350, 350, 85, 20);

        add(login);
        add(register);
        add(user);
        add(pass);
        add(username);
        add(password);

        login.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // set username and password
        if(e.getSource() == login){
            if(user.getText().equals("") && pass.getText().equals("")){
                // JOptionPane.showMessageDialog(null, "DEBUG login");
                this.setVisible(false);
                Login frame = (Login) SwingUtilities.getWindowAncestor(this);
                frame.panel1.setVisible(true);
            }
        }

    }
}
