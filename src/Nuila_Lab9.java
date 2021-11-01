package src;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.*;


public class Nuila_Lab9{
    private static int random ;
    private static Scanner sc;
    private static Cord cordOne;
    private static Cord cordTwo;
    private static ArrayList<Cord> listOfCordsFound = new ArrayList<>();



    private static void setBoard(int[][] gameBoard){
        for(int rowNum = 0; rowNum < gameBoard.length; rowNum++){
            for (int columnNum = 0; columnNum < gameBoard[rowNum].length; columnNum++){
                // Will genarate a random num from 1 to 8
                genarateNum(gameBoard);
                gameBoard[rowNum][columnNum] = random;
            }
        }

    }

    private static void prettyPrint(int[][] gameBoard){
        for (int r = 0; r < gameBoard.length; r++) {
            for (int c = 0; c < gameBoard[r].length; c++) {
                if(c+1 < gameBoard[r].length){
                    System.out.print(gameBoard[r][c]+" ");
                }else{
                    System.out.println(gameBoard[r][c]);
                }

            }
        }
    }

    private static void genarateNum(int[][] gameBoard){
        random = 1 + (int)(Math.random()*8);
        if(!checkNumberOfApperanses(gameBoard,random)){
            //If false
            genarateNum(gameBoard);
        }

    }

    private static boolean checkNumberOfApperanses(int[][] gameBoard,int randomNum){
        int randomNumApperanceCount = 0;
        for (int r = 0; r < gameBoard.length; r++){
            for(int c = 0; c < gameBoard[r].length; c++){
                if(gameBoard[r][c] == randomNum){
                    randomNumApperanceCount++;
                }
            }
        }
        if(randomNumApperanceCount >= 2){
            return false;
        }
        return true;
    }

    private static void userInteraction(int[][] gameBoard){
        askForCoordinateOne();
        askForCoordinateTwo();
      
        if(areUserSelectedCordAreAPair(gameBoard)){
            if(checkIfPairAlreadyFound()){
                System.out.println("The Pair selected was already found try again");
                userInteraction(gameBoard);
            }else{
                listOfCordsFound.add(cordOne);
                listOfCordsFound.add(cordTwo);
                System.out.println("Good job pair found.");
                if(listOfCordsFound.size() == 4*4){
                    System.out.println("All Pairs found Good Job\nGame Over");
                    System.exit(0);
                }
                userInteraction(gameBoard);
            }
           
        }else {
            System.out.println("Selected coordinates are not a pair. Try again");
            userInteraction(gameBoard);
        }
    }


    private static void askForCoordinateOne(){
        sc = new Scanner(System.in);
        System.out.println("Enter your first coordinate (<row>,<column>)");
        String input = sc.nextLine();
        if(Pattern.matches("[1-8],[1-8]", input)){
            cordOne = new Cord(input);
        }else{
            System.out.println("Incorrect format or row\\column number is invalid try again.");
            askForCoordinateOne();
        }
    }

    private static void askForCoordinateTwo(){
        sc = new Scanner(System.in);
        System.out.println("Enter your Second coordinate (<row>,<column>)");
        String input = sc.nextLine();
        if(Pattern.matches("[1-8],[1-8]", input)){
            cordTwo = new Cord(input);
       
            if(areUserSelectedCordsTheSame()){
                System.out.println("Your First Coordinate and your Second Coordinate are the same. Try again");
                askForCoordinateTwo();
            }
        }else{
            System.out.println("Incorrect format or row\\column number is invalid try again.");
            askForCoordinateTwo();
        }
        
    }

    private static boolean areUserSelectedCordAreAPair(int[][] gameBoard){
        if(gameBoard[cordOne.getRowNum()-1][cordOne.getColumnNum()-1]==gameBoard[cordTwo.getRowNum()-1][cordTwo.getColumnNum()-1]){
            System.out.println(gameBoard[cordOne.getRowNum()-1][cordOne.getColumnNum()-1]+" == "+gameBoard[cordTwo.getRowNum()-1][cordTwo.getColumnNum()-1]);
            return true;     
        }
        System.out.println(gameBoard[cordOne.getRowNum()-1][cordOne.getColumnNum()-1]+" != "+gameBoard[cordTwo.getRowNum()-1][cordTwo.getColumnNum()-1]);
        return false;
    }


    private static boolean areUserSelectedCordsTheSame(){
        if((cordOne.getRowNum() ==  cordTwo.getRowNum()) && (cordOne.getColumnNum() == cordTwo.getColumnNum())){
            return true;
        }
        return false;
    }

    private static boolean checkIfPairAlreadyFound(){
        boolean hasCordOne = false;
        boolean hasCordTwo = false;
        for ( Cord x : listOfCordsFound) {
            if((cordOne.getRowNum()== x.getRowNum())&&(cordOne.getColumnNum()==x.getColumnNum())){
                hasCordOne = true;
            }
            if((cordTwo.getRowNum()== x.getRowNum())&&(cordTwo.getColumnNum()==x.getColumnNum())){
                hasCordTwo = true;
            }
            if((hasCordOne==true)&&(hasCordTwo==true)){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        int[][] gameBoard = new int[4][4];
        setBoard(gameBoard);
        prettyPrint(gameBoard);
        userInteraction(gameBoard);

    }

}

