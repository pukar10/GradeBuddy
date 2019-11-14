import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Login extends JFrame {

    LoginPanel loginPanel;
    Panel1 panel1;
    Panel2 panel2;

    public Login() throws IOException {

        // set size of frame and layout of frame
        setSize(800, 600);
        setLayout(null);

        // initialize all panels we use in the frame
        loginPanel = new LoginPanel();
        panel1 = new Panel1();
        panel2 = new Panel2();

        //add all panels we use TO the frame
        this.add(loginPanel);
        this.add(panel1);
        this.add(panel2);

        loginPanel.setVisible(true);
        panel1.setVisible(false);
        panel2.setVisible(false);

        // Frame stuff
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }
}
