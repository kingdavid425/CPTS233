import java.io.*;
import java.util.*;

public class MasterGradebook {   
   public static void main(String args[]) throws IOException, FileNotFoundException {
      List<Integer> assignmentValues = new ArrayList<Integer>(); //total value of each assignment
      List<String> categories = new ArrayList<String>();         //all the categories of the files inputted
      List<Integer> categoryValues = new ArrayList<Integer>();   //each categories total value   
      List<String> headers = new ArrayList<String>();            //all headers of every file category
      
      //Key: studentID and name
      //Value: List of student assignment scores 
      Hashtable<String, List<Integer>> assignmentScores = new Hashtable<String, List<Integer>>();
      
      //Key: studentID and name
      //Value: List of student category scores
      Hashtable<String, List<Integer>> categoryScores = new Hashtable<String, List<Integer>>();

      Scanner fileScanner;      
      
      //Start of program
      //creates a list of files based on user input
      List<String> fileList = inputFiles();
      
      //try: iterate through fileList and gather data from the files
      //catch: invalid or non-existent files
      try {
         //iterates through the list of files
         for(String fileName : fileList) {            
            fileScanner = new Scanner(new File(fileName));
                  
            getHeaders(fileScanner, assignmentValues, categories, categoryValues, headers, fileName);        
            getData(fileScanner, assignmentScores, categoryScores);
         }
         
         createDetails(headers, assignmentValues, assignmentScores);
         createSummary(headers, categories, categoryValues, categoryScores);
         
      } catch(StringIndexOutOfBoundsException | FileNotFoundException e) {
         System.out.println("One of the files you entered could not be found. Please try again.");
         System.out.println("Make sure the files you enter are in the format <Category>_<Number>.csv");
      }
      
      //output that lets the user know the program has finished running
      System.out.println("The program has finished running. The ouput"); 
      System.out.println("has been saved as 'output.csv' and 'details.csv'");
   }
   
   // pre: this method is the start of the program. This method will prompt the user to input files
   //      for the program to evaluate 
   // post: returns an alphabetically sorted list of the user inputted file names
   public static List<String> inputFiles() {      
      Scanner user = new Scanner(System.in);           //user input
      String fileName;                                 //stores the file name the user inputs
      List<String> fileList = new ArrayList<String>(); //list of the user inputted file names 
      
      System.out.println("     Input files one at a time by typing the name of a file");
      System.out.println("            followed by .csv, and then press Enter.");
      System.out.println("When you are finished inputting files, type 'START' and press Enter");
      System.out.println("-------------------------------------------------------------------");
      
      fileName = user.next();
      
      //adds the user inputted files to the fileList, exits when the user inputs "Start",
      //will only add files that have not yet been added
      
      //exit word is "Start"
      while(fileName.compareToIgnoreCase("start") != 0) {
         if(!fileList.contains(fileName)) { //if the file has not yet been added
            fileList.add(fileName);
            fileName = user.next();
         } else {
            System.out.println("You have already entered that file");
            fileName = user.next();
         }  
      }
         
      Collections.sort(fileList); //sort the list of file names alphabetically
                                  //this helps keep the file data in the correct order when
                                  //printing to the output file
      return fileList; 
   }
   
   //pre: is passed a valid fileScanner object and all other parameters have been initialized
   //post: 
   //    - creates a list of all file categories eg.(Exams, Homework, Quizzes)
   //       - stored in: List<String> categories
   //    - creates a list of headers eg.(HW1, HW2 etc)
   //       - stored in: List<String> headers
   //    - creates a list of each categories total point value eg.(Exams are worth 400 points)
   //       - stored in: List<Integer> categoryValues
   //    - creates a list of each assignments total point values eg.(Exam1 was worth 100 points)
   //       - stored in: List<Integer> assignmentValues
   public static void getHeaders (Scanner fileScanner, List<Integer> assignmentValues, 
                                  List<String> categories, List<Integer> categoryValues, 
                                  List<String> headers, String fileName) {
      
      //since file names are assumed to be of the format <category>_<number>.csv, this string will
      //store <category>
      String fileCategory = fileName.substring(0, fileName.indexOf('_'));
      
      //initializes lineScanner for the first line of the file
      Scanner lineScanner = new Scanner(fileScanner.nextLine());
      lineScanner.useDelimiter(","); 
      
      //if a file of the same category is passed, this shoudln't run because we already know
      //the files category, headers, and total value
      if(!categories.contains(fileCategory)) {
         String lineSegment;
      
         categories.add(fileCategory);
                   
         int column = 0; //keeps track of what cell the loop is on/how many iterations the loop has run
            
         //gather the names of each assignment in the file (HW1, HW2 etc) 
         while(lineScanner.hasNext()) {
            lineSegment = lineScanner.next();
            if(column > 2) {
               headers.add(lineSegment);
            } else column++;
         }
         
         //initializes lineScanner for the second line of the file
         lineScanner = new Scanner(fileScanner.nextLine());
         lineScanner.useDelimiter(",");
          
         column = 0;
         
         //skip one column
         lineScanner.next();
         
         // - gather the total value of the category
         // - gather the value of each assignment
         while(lineScanner.hasNext()) {
            lineSegment = lineScanner.next();
            if(column == 0) {
               categoryValues.add(Integer.parseInt(lineSegment)); //category value 
            } else if(column > 0) {
               assignmentValues.add(Integer.parseInt(lineSegment)); //assignment value
            }
            column++;
         }
      }
   }
   
   //pre: is passed a valid fileScanner, and assignmentScores as well as categoryScores have been
   //     initialized 
   //post:
   //    - creates a table of student assignment scores
   //    - creates a table of student category scores
   public static void getData (Scanner fileScanner, 
                               Hashtable<String, List<Integer>> assignmentScores,
                               Hashtable<String, List<Integer>> categoryScores) 
                               throws FileNotFoundException {
                               
      Scanner lineScanner = new Scanner(fileScanner.nextLine());
      lineScanner.useDelimiter(",");
                                        
      while(fileScanner.hasNextLine()) {
         String key;  //key for assignment and category scores
         String line; //temp value that holds the the next line of the scanner
         
         //temporary list that will contain the scores of the student whose line the scanner is on,
         //this is then stored as the value in the appropriate map
         List<Integer> studentScores;
                      
         line = fileScanner.nextLine(); //get third line of document
               
         //scan the third line of the document
         lineScanner = new Scanner(line);
         lineScanner.useDelimiter(",");
         
         //key = studentID + name      
         key = lineScanner.next() + ", " + lineScanner.next() + "; " + lineScanner.next() + ",";
              
         if(categoryScores.containsKey(key)) {
            //if category scores already has the student in the system, add new
            //value to the end of the list already in the map
            studentScores = categoryScores.get(key);      
            studentScores.add(Integer.parseInt(lineScanner.next()));
            categoryScores.replace(key, studentScores);                
         } else {
            //if category scores doesnt have the student in the system, it creates
            //a new spot in the hashtable
            studentScores = new ArrayList<Integer>();
            studentScores.add(Integer.parseInt(lineScanner.next()));
            categoryScores.put(key, studentScores);
         }         
               
         //we have now stored category scores. now we need to get each individual assignment scores       
         //if the student is already in the system, we just add to the list already in the table
         if(assignmentScores.containsKey(key)) {
            studentScores = assignmentScores.get(key);                 
            while(lineScanner.hasNext()) {
               studentScores.add(Integer.parseInt(lineScanner.next()));
            }                  
            assignmentScores.replace(key, studentScores);
         //if the student is not in the system, we create a new hashValue and add that to the table
         } else {
            studentScores = new ArrayList<Integer>();                  
            while(lineScanner.hasNext()) {
               studentScores.add(Integer.parseInt(lineScanner.next()));
            }                  
            assignmentScores.put(key, studentScores); 
         }     
      }            
   }
   
   //pre: all parameters have been properly intialized and contain correct values
   //post: creates the details file that contains an aggregate of all student's records
   public static void createDetails(List<String> headers, List<Integer> assignmentValues, 
                                    Hashtable<String, List<Integer>> assignmentScores) 
                                    throws IOException {
                                    
      String scores; //added for readability
      
      //create new file                              
      File file = new File("details.csv");
      file.createNewFile();
      FileWriter fw = new FileWriter(file);
      BufferedWriter bw = new BufferedWriter(fw);
      
      String topLine = removeBrackets(headers.toString());
      String secondLine = removeBrackets(assignmentValues.toString());
      
      bw.write("#ID,Name," + "," + topLine + "\n");
      bw.write(", Overall," +  "," + secondLine + "\n");
      
      Set<String> studentIDSet = assignmentScores.keySet(); //used to print each key in the table

      for(String studentID : studentIDSet) {
         scores = removeBrackets(assignmentScores.get(studentID).toString());
         
         bw.write(studentID + "," + scores + "\n");   
      }
      
      bw.flush();
      bw.close();
   }
   
   //pre: all parameters have been properly intialized and contain correct values
   //post: creates the summary file that contains all students, an overall class grade (in percent), and a points
   //      breakdown for each grading category
   public static void createSummary(List<String> headers, List<String> categories, 
                                    List<Integer> categoryValues,
                                    Hashtable<String, List<Integer>> categoryScores) 
                                    throws IOException {
      //added for readability                              
      String scores; //will contain the string representation of the student scores
      double grade;  //will contain students overall class grade as a percent
      
      //create new file                              
      File file = new File("summary.csv");
      file.createNewFile();
      FileWriter fw = new FileWriter(file);
      BufferedWriter bw = new BufferedWriter(fw);
      
      String topLine = removeBrackets(categories.toString());
      String secondLine = removeBrackets(categoryValues.toString());
      
      bw.write("#ID, Name, , Final Grade," + topLine + "\n");
      bw.write(", Overall, , ," + secondLine + "\n");
      
      Set<String> studentIDSet = categoryScores.keySet(); //used to print each key in the table
      
      for(String studentID : studentIDSet) {
         scores = removeBrackets(categoryScores.get(studentID).toString());     
         grade = (double) Math.round(finalGrade(studentID, categoryValues, categoryScores) * 100) / 100;
        
         bw.write(studentID + "," + grade + "," + scores + "\n");
      }
      
      bw.flush();
      bw.close(); 
   }
   
   //pre: all parameters have been properly initialized and contain correct data
   //post: this method returns the given students final grade as a percent 
   public static double finalGrade(String studentID, List<Integer> categoryValues, 
                                   Hashtable<String, List<Integer>> categoryScores) {
                                   
      double pointsGained = 0;
      double pointsPossible = 0;
      
      //this executes the formula: finalScore = sum(categoryScores) / sum(categoryValues)
      for(int i = 0; i < categoryValues.size(); i++) {
         if(categoryScores.containsKey(studentID)) {
            pointsGained += categoryScores.get(studentID).get(i);
            pointsPossible += categoryValues.get(i);
         }  
      }
      double finalScore = (pointsGained / pointsPossible);
      
      return (finalScore * 100);            
   }
   
   //this is a helper method used to remove the brackets from the string representation
   //of the List data structures
   public static String removeBrackets(String input) {
      input = input.replace('[', ' ');
      input = input.replace(']', ' ');
      return input;
   } 
}