import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Panel4 extends JPanel implements ActionListener {

    JLabel[] eachAssWorthLabels;

    public Panel4(){
        setLayout(null);
        setBounds(0, 0, 600, 800);

        eachAssWorthLabels = new JLabel[Course.numAssignmentTypes];

        JLabel congrats = new JLabel("WE DID IT! Holy guacamole");
        congrats.setBounds(350, 20, 200, 30);

        JButton showAssignmentWorth = new JButton("Assignment Worth");
        showAssignmentWorth.setBounds(20, 50, 150, 35);
        showAssignmentWorth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ypos = 50;
                for(int i = 0 ; i < Course.numAssignmentTypes; i++){
                    JLabel temp = new JLabel("Each "+Course.assignmentTypeNames[i]+" worth: "+Course.eachAssWorth[i]);
                    temp.setBounds(400, ypos, 100, 35);
                    eachAssWorthLabels[i] = temp;
                    add(temp);
                    ypos += 40;
                }
                showAssignmentWorth.setVisible(false);
                revalidate();
            }
        });

        add(congrats);
        add(showAssignmentWorth);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
