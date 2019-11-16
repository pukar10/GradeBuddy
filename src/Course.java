public class Course {

    static String yourName;
    static String professor;
    static String courseName;

    static int numAssignmentTypes;
    static double goalGrade;

    static String[] assignmentTypeNames;
    static int[] weights;
    static int[] quantity;

    static double[][] grades;

    // calculation variables
    static double[] eachAssWorth;
    static double[] eachAssignmentTypeWorth;
    static double[] scoreEachAssignmentType;
    static double currentGrade;
    static double bestAssignmentTypeScore;
    static double worstAssignmentTypeScore;
    static int bestAssignmentLocation;
    static int worstAssignmentLocation;

    // feedback variables
    static String generalFeedback;
    static String specificFeedback;

    public Course(){
        yourName = "";
        professor = "";
        courseName = "";
        numAssignmentTypes = 0;
        goalGrade = 0.0;
    }

    public static void eachAssignmentTypeScore(){
        double total = 0;

        for(int i = 0; i < eachAssWorth.length; i++){
            double worth = eachAssWorth[i];

            for(int j = 0; j < grades.length; j++){
                double score = 0;
                for(int x = 0; x < grades[j].length; x++){
                    score += grades[j][x] * worth;
                }
                scoreEachAssignmentType[j] = score;
                score = 0;
            }
        }
    }

    public static void courseCompeleted(){
        double total = 0.0;

        for(int i = 0; i < eachAssWorth.length; i++){
            double worth = eachAssWorth[i];
            for(int j = 0; j < grades.length; j++){
                for(int x = 0; x < grades[j].length; x++){
                    double grade = grades[j][x];
                    if(grade != -1.0){
                        total += worth;
                    }
                }
            }
        }

    }

    // array stores how much each assignment in AssignmentType is worth
    public static void eachAssWoth(){
        for(int i = 0; i < weights.length; i++){
            eachAssWorth[i] = quantity[i]/weights[i];
        }
    }

    // this method will calculate overall grade using assignment type scores and weights
    public static void calculateGrade(){
        for(int i = 0; i < scoreEachAssignmentType.length; i++){
            currentGrade += scoreEachAssignmentType[i]/weights[i];
        }
    }

    // this method will set generalFeedback variable based on overall course grade
    public static void generateGeneralFeedback(){
        if(currentGrade==100){
            generalFeedback = "Wow a perfect score! Keep up the great work!";
        }else if(currentGrade<100&&currentGrade>=90){
            generalFeedback = "You have an A in the course! You're clearly doing something right!";
        }else if(currentGrade<90&&currentGrade>=80){
            generalFeedback = "You have a B in the course! You're doing a decent job, but you can do better!";
        }else if(currentGrade<80&&currentGrade>=70){
            generalFeedback = "You have a C in the course! You're cutting it pretty close! You can do better!";
        }else if(currentGrade<70&&currentGrade>=60){
            generalFeedback = "You have a D in the course! It's not too late to bring your grade up!";
        }else if(currentGrade<60){
            generalFeedback = "You have an F in the course! You need to make some major changes!";
        }
    }

    // this method will calculate the best and worst assignment type scores
    public static void calculateBestAndWorstAssType(){
        bestAssignmentTypeScore = scoreEachAssignmentType[0];
        worstAssignmentTypeScore = scoreEachAssignmentType[0];
        for(int i = 0; i < scoreEachAssignmentType.length; i++){
            if(bestAssignmentTypeScore<scoreEachAssignmentType[i]){
                bestAssignmentTypeScore=scoreEachAssignmentType[i];
                bestAssignmentLocation = i;
            }
        }
        for(int j = 0; j < scoreEachAssignmentType.length; j++){
            if(worstAssignmentTypeScore>scoreEachAssignmentType[j]){
                worstAssignmentTypeScore=scoreEachAssignmentType[j];
                worstAssignmentLocation = j;
            }
        }
    }

    // this method creates specific feedback using the variables above
    public static void generateSpecificFeedback(){
        specificFeedback = "Hello " + yourName + ", As of right now your current grade in " +
                courseName + ", with professor " + professor + ", is: " + currentGrade + ". " +
                generalFeedback + " You are doing very well on " + assignmentTypeNames[bestAssignmentLocation] +
                " with a score of: " + bestAssignmentTypeScore + ". However, your weakest assignment type is " +
                assignmentTypeNames[worstAssignmentLocation] + "with a score of: " + worstAssignmentTypeScore +
                ".";
    }

    public static void initializeWeightsArray(int i){
        weights = new int[i];
    }

    public static void initializeAssignmentTypeNames(int i){
        assignmentTypeNames = new String[i];
    }

    public static void setNumGrades(int assignmentType, int numGrades){
        grades[assignmentType] = new double[numGrades];
    }

    public static void initializeGrades(int numAssignmentTypes){
        grades = new double[numAssignmentTypes][];
    }
    
    public static void initializeCourseNames(int numAssignmentTypes){
        assignmentTypeNames = new String[numAssignmentTypes];
    }
    
}
