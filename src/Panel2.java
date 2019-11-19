import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Panel2 extends JPanel implements ActionListener {

    JTextField[] assignmentNames;
    JTextField[] assignmentQuantities;
    JTextField[] assignmentWeights;
    JButton next;

    public Panel2(){
        setLayout(null);
        setBounds(0, 0, 800, 600);

        JLabel type, quantity, weight;

        type = new JLabel("Type");
        type.setBounds(50, 20, 100, 35);
        quantity = new JLabel("Quantity");
        quantity.setBounds(300, 20, 100, 35);
        weight = new JLabel("Weight");
        weight.setBounds(500, 20, 100, 35);

        next = new JButton("Next");
        next.addActionListener(this);

        add(next);
        add(type);
        add(quantity);
        add(weight);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Login frame = (Login) SwingUtilities.getWindowAncestor(this);

        // put AssignmentTypeNames, numberOfAssignments, AssignmentTypeWeight in Course Object.
        // create jaggged array in Course and panel3.
        for(int i = 0; i < Course.numAssignmentTypes; i++) {
            String assignmentName = assignmentNames[i].getText();
            String weight = assignmentWeights[i].getText();
            int quantity = Integer.parseInt(assignmentQuantities[i].getText());

            Course.quantity[i] = quantity;
            Course.assignmentTypeNames[i] = assignmentName;
            Course.weights[i] = Integer.parseInt(weight);
            Course.setNumGrades(i, quantity);
            frame.panel3.grades[i] = new JTextField[quantity];
        }

        // create labels and TextFields for panel3
        int ypos = 100;
        for(int i = 0; i < Course.numAssignmentTypes; i++){
            JLabel assignmentName = new JLabel(Course.assignmentTypeNames[i]);
            assignmentName.setBounds(50, ypos, 100, 35);

            int xpos = 100;
            for(int j = 0; j < Course.grades[i].length; j++){
                JTextField assignmentTextField = new JTextField();
                assignmentTextField.setBounds(xpos, ypos, 50, 30);
                frame.panel3.add(assignmentTextField);
                frame.panel3.grades[i][j] = assignmentTextField;
                xpos += 60;
            }

            frame.panel3.add(assignmentName);
            ypos+=100;
        }

        frame.panel3.next.setBounds(350, 450, 75, 30);

        frame.panel3.revalidate();
        frame.panel3.repaint();

        setVisible(false);
        frame.panel3.setVisible(true);

        // DEBUG

        /*

         //check jagged array
        for(int i = 0; i < Course.numAssignmentTypes; i++){
            JOptionPane.showMessageDialog(null, "DEBUG: "+Course.grades[i].length);
        }
         //check course object
        JOptionPane.showMessageDialog(null, "DEBUG: Panel2 Next Click");
        JOptionPane.showMessageDialog(null, "DEBUG: course.numAssignmentTypes\n"+Course.numAssignmentTypes);
        JOptionPane.showMessageDialog(null, "DEBUG: course.courseName\n"+Course.courseName);
        JOptionPane.showMessageDialog(null, "DEBUG: course.goalGrade\n"+Course.goalGrade);
        JOptionPane.showMessageDialog(null, "DEBUG: course.professor\n"+Course.professor);
        JOptionPane.showMessageDialog(null, "DEBUG: course.yourName\n"+Course.yourName);

        */
    }
}
