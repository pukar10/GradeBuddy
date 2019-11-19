import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Panel4 extends JPanel implements ActionListener {

    JLabel[] eachAssWorthLabels;

    public Panel4(){
        setLayout(null);
        setBounds(0, 0, 800, 600);

        eachAssWorthLabels = new JLabel[Course.numAssignmentTypes];
    
        // progress bar
        JComponent pb = new ProgressBar().makeUI((int)Course.percentOfClassCompletion);
        pb.setBounds(0,-5,800,80);

        // JLabel congrats = new JLabel("WE DID IT! Holy guacamole");
        // congrats.setBounds(350, 20, 200, 30);

        // Calculations
        // calcGrade sets currentGrade and percentOfClassCompletion
        double currGrade = Course.currentGrade;
        double perClassComp = Course.percentOfClassCompletion;

        JLabel currGradeLabel = new JLabel(String.valueOf(currGrade));
        currGradeLabel.setBounds(290, 100, 50, 50);

        JLabel percentClassCompleted = new JLabel(String.valueOf(perClassComp));
        percentClassCompleted.setBounds(290, 150, 50, 50);

        //add(congrats);
        add(pb);
        add(currGradeLabel);
        add(percentClassCompleted);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
