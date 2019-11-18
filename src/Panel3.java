import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Panel3 extends JPanel implements ActionListener {

    JTextField grades[][];
    JButton next;
    
    
    public Panel3(){
        setLayout(null);
        setBounds(0, 0, 600, 800);

        next = new JButton("Next");
        next.addActionListener(this);

        add(next);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    
        Login frame = (Login) SwingUtilities.getWindowAncestor(this);

        // send grades to Course.grades[][]
        for(int i = 0; i < grades.length; i++){
            for(int j = 0; j < grades[i].length; j++){
                if(grades[i][j].getText().isEmpty()){
                    Course.grades[i][j] = -1;
                    continue;
                }
                double grade = Double.parseDouble(grades[i][j].getText().trim());
                Course.grades[i][j] = grade;
            }
        }

        Course.calcGrade();
        Panel4 panel4 = new Panel4();
        frame.add(panel4);
        panel4.setVisible(true);

        panel4.revalidate();
        panel4.repaint();
        
        setVisible(false);
        panel4.setVisible(true);
        
        
        

        // DEBUG
        // check if Course.grades array is properly filled in
        ///*

        // for(int i = 0; i < Course.grades.length; i++){
        //     for(int j = 0; j < Course.grades[i].length; j++){
        //         double grade = Course.grades[i][j];
        //         JOptionPane.showMessageDialog(null, "DEBUG: "+grade);
        //     }
        // }

         //*/

    }
}
