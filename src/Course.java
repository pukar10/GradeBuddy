import javax.swing.*;

public class Course {

    static String studentName;
    static String professor;
    static String courseName;

    static int numAssignmentTypes;
    static double goalGrade;

    /***  The order in here is the order in which all the other data structures are based on. ***/
    static String[] assignmentTypeNames;
    static int[] weights;
    static int[] quantity;

    /** 2-D array that stores the scores earned for each assignment.
     Each row is a different assignment type, and the columns are
     the grades received for that assignment type. **/
    static double[][] grades;

    /** calculation variables **/
    static double[] eachAssWorth;
    static double currentGrade;
    static double percentOfClassCompletion;
    static double totalScores;


    public Course(){
        studentName = "";
        professor = "";
        courseName = "";
        numAssignmentTypes = 0;
        goalGrade = 0.0;
        percentOfClassCompletion = 0.0;
        totalScores = 0;
        currentGrade = 0.0;
    }

    /**
     * Calculates the student's current grade in the course.
     * 1. Determine the weight percentage for each assignment in every assignment type by:
     *          - taking the total weight for that assignment, and dividing it by the quantity
     *          of that assignment type.
     *          Ex:      TYPE = HW           QUANTITY = 4        WEIGHT = 20
     *          singleWeight = totalWeight/quantity (5% each)
     *
     * 2. Calculate the score earned in terms of the percentage for each assignment by:
     *  - divide the score by 100, then multiply it by the percentage of weight
     *  Ex: HW1   singleWeight = 5%            SCORE = 67/100 (67%)
     *  scorePercent = (score/100) * singleWeight  --- (would equal 3.35/5%)
     *
     *  3. Add all the total scores for each assignment type to get the percentage of the course
     *      completed thus far.
     *
     *  4. Add all the score percentages earned for each assignment type, then divide by the percentage
     *          of the course completed thus far to get the student's current grade.
     *          Ex = 70% of course completed, totalScores = 62.1%
     *          currentGrade = totalScores / courseCompletion (would equal 88.71%, a B)
     *
     *
     * @return the student's current grade
     */
    public static void calcGrade()
    {
        /**
        // for the first assignment type (i.e. Homework)
        int type1SingleWeight = ( getTotalWeightFor(0) ) / ( getTotalQuantityFor(0) );

        // for the second assignment type (i.e Quizzes)
        int type2SingleWeight = ( getTotalWeightFor(1) ) / ( getTotalQuantityFor(1) );

        // for the third assignment type (i.e Exams)
        int type3SingleWeight = ( getTotalWeightFor(2) ) / ( getTotalQuantityFor(2) );


        double scoreForType1 = getTotalScoreFor(0, type1SingleWeight);
        double scoreForType2 = getTotalScoreFor(1, type2SingleWeight);
        double scoreForType3 = getTotalScoreFor(2, type3SingleWeight);


        percentOfClassCompletion = getTotalPercent();

        totalScores = scoreForType1 + scoreForType2 + scoreForType3;
        currentGrade = totalScores/percentOfClassCompletion;
         **/

        double[] weight = new double[Course.numAssignmentTypes];
        double[] totalScores = new double[Course.numAssignmentTypes];

        double totalScore = 0;
        double totalPoints = 0;
        double score = 0;


        for(int i = 0; i < Course.numAssignmentTypes; i++){
            weight[i] = (double)weights[i]/quantity[i];

            int numGrades = 0;
            for(int j = 0; j < grades[i].length; j++){
                if(grades[i][j] == -1){
                    continue;
                }
                score = (grades[i][j] / 100) * weight[i];
                totalScore += score;
                totalPoints += weight[i];
                numGrades++;
            }
            totalScores[i] = totalScore;
            percentOfClassCompletion += numGrades * weight[i];
            //JOptionPane.showMessageDialog(null, "DEBUG: weight["+i+"] "+weight[i]);
        }
        JOptionPane.showMessageDialog(null, "DEBUG: totalScore: " + totalScore);
        JOptionPane.showMessageDialog(null, "DEBUG: totalPoints: " + totalPoints);

        currentGrade = (totalScore/totalPoints) * 100;
        JOptionPane.showMessageDialog(null, "DEBUG: currGrade " + currentGrade);
        JOptionPane.showMessageDialog(null, "DEBUG: perCompleted " + percentOfClassCompletion);
    }




    /**
     * Calculates and returns the cumulative percent of the course that has been completed so far.
     * @return the percent of completion in the course.

    public static double getTotalPercent()
    {
        double percent = 0.0;
        double sumOfPercent = 0.0;
        int weight = 0;
        int count = 0;

        // there can only be 3 assignment types for the rows
        for(int i = 0; i < 3; i++)
        {
            // calculate the weight for individual assignments in the the current assignment
            weight = getTotalWeightFor(i) / getTotalQuantityFor(i);

            // loop through the scores of the current assignment
            for(int j = 0; j < grades[i].length; j++)
            {
                // check to see if the user entered a score
                if(grades[i][j] != -1)
                {
                    // if a score was entered, increment the count
                    count++;
                }
            }

            // stores the total possible points that could have been earned for the current
            // assignment based on how many assignment have been completed in the course so far
            percent = count * weight;
            sumOfPercent += percent;   // sum the total percents for each assignment
        }

        return sumOfPercent;
    }
     **/

    /**
     *  Returns the cumulative scores for an assignment that have been completed so far in the course.
     * @return the student's cumulative score
     * @param row the assignment type
     * @param singleWeight the weight for each assignment
     */
    public static double getTotalScoreFor(int row, int singleWeight)
    {
        double score = 0.0;
        double sum = 0.0;

        // loop through the columns of the specified row (assignment type scores)
        // and calculate the cumulative score for that assignment type
        for(int j = 0; j < grades[row].length; j++)
        {
            // check to make sure the user entered a value
            if(grades[row][j] != -1)
            {
                score = grades[row][j];
                score = (score / 100) * singleWeight;
                sum += score;
            }
        }

        return sum;
    }


    /**
     * Returns the total quantity for the specified assignment type
     * @param assignmentNum the specified type
     * @return the total quantity for the assignment
     */
    public static int getTotalQuantityFor(int assignmentNum)
    {
        return quantity[assignmentNum];
    }

    /**
     * Returns the total weight for the specified assignment type
     * @param assignmentNum the specified assignment
     * @return the total weight for the assignment
     */
    public static int getTotalWeightFor(int assignmentNum)
    {
        return weights[assignmentNum];
    }

    /**
     * Initializes the elements of the array that stores the weights
     * of the assignments types based on the specified size.
     * @param i the specified size
     */
    public static void initializeWeightsArray(int i){
        weights = new int[i];
    }

    public static void initializeAssignmentTypeNames(int i){
        assignmentTypeNames = new String[i];
    }

    /**
     * initialize num columns in the grades array (assignmentType = rows, numGrades = col)
     * @param assignmentType ROWS
     * @param numGrades COLUMNS
     */
    public static void setNumGrades(int assignmentType, int numGrades){
        grades[assignmentType] = new double[numGrades];
    }


    /**
     * Initializes the elements of the array that stores the student's
     * grades for each assignment based on the specified size.
     * @param numAssignmentTypes the specified size
     */
    public static void initializeGrades(int numAssignmentTypes){
        grades = new double[numAssignmentTypes][];
    }


    /**
     * Initializes the elements of the array that stores the names
     * of the assignments types based on the specified size.
     * @param numAssignmentTypes the specified size
     */
    public static void initializeCourseNames(int numAssignmentTypes){
        assignmentTypeNames = new String[numAssignmentTypes];
    }
    
}
