
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.beans.*;
import javax.swing.*;
import javax.swing.plaf.basic.*;
import javax.swing.event.*;

// got this code from https://stackoverflow.com/questions/12524121/jprogressbar-how-to-change-colour-based-on-progress

public class ProgressBar {
	public JComponent makeUI(int finalGrade) {
		final JProgressBar progressBar = new JProgressBar();
		progressBar.setOpaque(false);
		progressBar.setUI(new GradientPalletProgressBarUI());
		progressBar.setStringPainted(true); // displays percentage inside progress bar
		
		JPanel p = new JPanel();
		p.add(progressBar);
		p.add(new JButton(new AbstractAction("Show") {
			@Override public void actionPerformed(ActionEvent e) {
				SwingWorker<Void,Void> worker = new SwingWorker<Void,Void>() {
					@Override public Void doInBackground() {
						int current = 0, lengthOfTask = 50; // time it takes to complete loading progress bar
						while(current<=lengthOfTask && !isCancelled()) {
							try { // dummy task
								Thread.sleep(50);
							} catch(InterruptedException ie) {
								return null;
							}
							setProgress(finalGrade * current / lengthOfTask); // how much it loads
							current++;
						}
						return null;
					}
				};
				worker.addPropertyChangeListener(new ProgressListener(progressBar));
				worker.execute();
			}
		}));
		
		progressBar.setPreferredSize(new Dimension(800, 30));
		//progressBar.setLayout(new GridLayout(1, 2));
		
		return p;
	}
	// public static void main(String[] args) {
	// 	EventQueue.invokeLater(new Runnable() {
	// 		@Override public void run() {
	// 			createAndShowGUI();
	// 		}
	// 	});
	// }
	// public static void createAndShowGUI() {
	// 	JFrame frame = new JFrame();
	// 	frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	//     frame.getContentPane().add(new ProgressBar().makeUI(100));
	// 	frame.setSize(800, 600);
	// 	frame.setLocationRelativeTo(null);
	// 	frame.setVisible(true);
	// }
}
class ProgressListener implements PropertyChangeListener {
	private final JProgressBar progressBar;
	ProgressListener(JProgressBar progressBar) {
		this.progressBar = progressBar;
		this.progressBar.setValue(0);
	}
	@Override public void propertyChange(PropertyChangeEvent evt) {
		String strPropertyName = evt.getPropertyName();
		if("progress".equals(strPropertyName)) {
			progressBar.setIndeterminate(false);
			int progress = (Integer)evt.getNewValue();
			progressBar.setValue(progress);
		}
	}
}
class GradientPalletProgressBarUI extends BasicProgressBarUI {
	private final int[] pallet;
	public GradientPalletProgressBarUI() {
		super();
		this.pallet = makeGradientPallet();
	}
	private static int[] makeGradientPallet() {
		BufferedImage image = new BufferedImage(100, 1, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2  = image.createGraphics();
		Point2D start  = new Point2D.Float(0f, 0f);
		Point2D end    = new Point2D.Float(99f, 0f);
		float[] dist   = {0.0f, 0.5f, 1.0f};
		Color[] colors = { Color.RED, Color.YELLOW, Color.GREEN };
		g2.setPaint(new LinearGradientPaint(start, end, dist, colors));
		g2.fillRect(0, 0, 100, 1);
		g2.dispose();

		int width  = image.getWidth(null);
		int[] pallet = new int[width];
		PixelGrabber pg = new PixelGrabber(image, 0, 0, width, 1, pallet, 0, width);
		try {
			pg.grabPixels();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return pallet;
	}
	private static Color getColorFromPallet(int[] pallet, float x) {
		if(x < 0.0 || x > 1.0) {
			throw new IllegalArgumentException("Parameter outside of expected range");
		}
		int i = (int)(pallet.length * x);
		int max = pallet.length-1;
		int index = i<0?0:i>max?max:i;
		int pix = pallet[index] & 0x00ffffff | (0x64 << 24);
		return new Color(pix, true);
	}
	@Override public void paintDeterminate(Graphics g, JComponent c) {
		if (!(g instanceof Graphics2D)) {
			return;
		}
		Insets b = progressBar.getInsets(); // area for border
		int barRectWidth  = progressBar.getWidth()  - (b.right + b.left);
		int barRectHeight = progressBar.getHeight() - (b.top + b.bottom);
		if (barRectWidth <= 0 || barRectHeight <= 0) {
			return;
		}
		int cellLength = getCellLength();
		int cellSpacing = getCellSpacing();
		// amount of progress to draw
		int amountFull = getAmountFull(b, barRectWidth, barRectHeight);

		if(progressBar.getOrientation() == JProgressBar.HORIZONTAL) {
			// draw the cells
			float x = amountFull / (float)barRectWidth;
			g.setColor(getColorFromPallet(pallet, x));
			g.fillRect(b.left, b.top, amountFull, barRectHeight);

		} else { // VERTICAL
			//...
		}
		// Deal with possible text painting
		if(progressBar.isStringPainted()) {
			paintString(g, b.left, b.top, barRectWidth, barRectHeight, amountFull, b);
		}
	}
}
	
	//
	//
	// // this method will set generalFeedback variable based on overall course grade
	// public static void generateGeneralFeedback(){
	// 	if(currentGrade==100){
	// 		generalFeedback = "Wow a perfect score! Keep up the great work!";
	// 	}else if(currentGrade<100&&currentGrade>=90){
	// 		generalFeedback = "You have an A in the course! You're clearly doing something right!";
	// 	}else if(currentGrade<90&&currentGrade>=80){
	// 		generalFeedback = "You have a B in the course! You're doing a decent job, but you can do better!";
	// 	}else if(currentGrade<80&&currentGrade>=70){
	// 		generalFeedback = "You have a C in the course! You're cutting it pretty close! You can do better!";
	// 	}else if(currentGrade<70&&currentGrade>=60){
	// 		generalFeedback = "You have a D in the course! It's not too late to bring your grade up!";
	// 	}else if(currentGrade<60){
	// 		generalFeedback = "You have an F in the course! You need to make some major changes!";
	// 	}
	// }
	//
	// // this method will calculate the best and worst assignment type scores
	// public static void calculateBestAndWorstAssType(){
	// 	bestAssignmentTypeScore = scoreEachAssignmentType[0];
	// 	worstAssignmentTypeScore = scoreEachAssignmentType[0];
	// 	for(int i = 0; i < scoreEachAssignmentType.length; i++){
	// 		if(bestAssignmentTypeScore<scoreEachAssignmentType[i]){
	// 			bestAssignmentTypeScore=scoreEachAssignmentType[i];
	// 			bestAssignmentLocation = i;
	// 		}
	// 	}
	// 	for(int j = 0; j < scoreEachAssignmentType.length; j++){
	// 		if(worstAssignmentTypeScore>scoreEachAssignmentType[j]){
	// 			worstAssignmentTypeScore=scoreEachAssignmentType[j];
	// 			worstAssignmentLocation = j;
	// 		}
	// 	}
	// }
	//
	// // this method creates specific feedback using the variables above
	// public static void generateSpecificFeedback(){
	// 	specificFeedback = "Hello " + yourName + ", As of right now your current grade in " +
	// 			courseName + ", with professor " + professor + ", is: " + currentGrade + ". " +
	// 			generalFeedback + " You are doing very well on " + assignmentTypeNames[bestAssignmentLocation] +
	// 			" with a score of: " + bestAssignmentTypeScore + ". However, your weakest assignment type is " +
	// 			assignmentTypeNames[worstAssignmentLocation] + "with a score of: " + worstAssignmentTypeScore +
	// 			".";
	// }
