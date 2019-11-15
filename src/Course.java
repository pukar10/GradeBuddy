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
