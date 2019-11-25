import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Panel3 extends JPanel implements ActionListener {

    JTextField grades[][];
    JButton next;
    
    
    public Panel3(){
        setLayout(null);
        setBounds(0, 0, 800, 600);

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
        if(Course.currentGrade > 90.0){
            JLabel labelGrade = new JLabel("A");
            labelGrade.setPreferredSize(new Dimension(100, 100));
            labelGrade.setBounds(330, 130, 50, 50);
            labelGrade.setFont(new Font(labelGrade.getFont().getName(), labelGrade.getFont().getStyle(), 20));
            panel4.add(labelGrade);

            JLabel labelFeedback = new JLabel("Perfect! Keep it up!");
            labelFeedback.setBounds(300, 500, 100, 30);
            panel4.add(labelFeedback);
        }else if(Course.currentGrade > 80.0){
            JLabel labelGrade = new JLabel("B");
            labelGrade.setPreferredSize(new Dimension(100, 100));
            labelGrade.setBounds(330, 500, 100, 100);
            panel4.add(labelGrade);

            JLabel labelFeedback = new JLabel("Almost there!");
            labelFeedback.setBounds(300, 500, 100, 30);
            panel4.add(labelFeedback);
        }else if(Course.currentGrade > 70.0){
            JLabel labelGrade = new JLabel("C");
            labelGrade.setPreferredSize(new Dimension(100, 100));
            labelGrade.setBounds(330, 500, 100, 100);
            panel4.add(labelGrade);

            JLabel labelFeedback = new JLabel("C's get Degree's");
            labelFeedback.setBounds(300, 500, 100, 30);
            panel4.add(labelFeedback);
        }else if(Course.currentGrade > 60){
            JLabel labelGrade = new JLabel("D");
            labelGrade.setPreferredSize(new Dimension(100, 100));
            labelGrade.setBounds(330, 500, 100, 100);
            panel4.add(labelGrade);

            JLabel labelFeedback = new JLabel("ouch");
            labelFeedback.setBounds(300, 500, 100, 30);
            panel4.add(labelFeedback);
        }else{
            JLabel labelGrade = new JLabel("F");
            labelGrade.setPreferredSize(new Dimension(100, 100));
            labelGrade.setBounds(330, 130, 100, 100);
            panel4.add(labelGrade);

            JLabel labelFeedback = new JLabel("Get some help");
            labelFeedback.setBounds(300, 500, 100, 30);
            panel4.add(labelFeedback);
        }
        panel4.setVisible(true);

        panel4.revalidate();
        panel4.repaint();
        
        setVisible(false);
        panel4.setVisible(true);
        
        
        

        // DEBUG
        // check if Course.grades array is properly filled in
        ///*

        for(int i = 0; i < Course.grades.length; i++){
            for(int j = 0; j < Course.grades[i].length; j++){
                double grade = Course.grades[i][j];
                JOptionPane.showMessageDialog(null, "DEBUG: "+grade);
            }
        }

         //*/

    }
}
