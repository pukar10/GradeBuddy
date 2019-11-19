import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class Panel4 extends JPanel implements ActionListener {

    JLabel[] eachAssWorthLabels;

    public Panel4(){
        setLayout(null);
        setBounds(0, 0, 600, 800);

        eachAssWorthLabels = new JLabel[Course.numAssignmentTypes];

        JLabel congrats = new JLabel("WE DID IT! Holy guacamole");
        congrats.setBounds(290, 20, 200, 30);

        // Calculations
        // calcGrade sets currentGrade and percentOfClassCompletion
        double currGrade = Course.currentGrade;
        double perClassComp = Course.percentOfClassCompletion;

        DecimalFormat df = new DecimalFormat("0.00");
        JLabel currGradeLabel = new JLabel(String.valueOf(df.format(currGrade)));
        currGradeLabel.setBounds(300, 60, 120, 120);
        currGradeLabel.setFont(new Font(currGradeLabel.getFont().getName(), currGradeLabel.getFont().getStyle(), 26));

        JLabel percentClassCompleted = new JLabel(String.valueOf(perClassComp)+"% of course finished!");
        percentClassCompleted.setBounds(230, 220, 200, 50);

        //add(congrats);
        JComponent pb = new ProgressBar().makeUI((int)Course.percentOfClassCompletion);
        pb.setBounds(100,250,500,300);

        add(pb);
        add(currGradeLabel);
        add(percentClassCompleted);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
