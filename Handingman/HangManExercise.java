/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hangman;

import java.util.Scanner;

/**
 *
 * @author aliemre
 */
public class HangManExercise {

    public static String answer = "";

    static char empthy[];
    static int lifeamount = 3;
    static int amountOfEstimating = 2;
    static int bigCheck = 0;
    static int bigcheck = 0;
    static int point = 0;
    static Scanner input = new Scanner(System.in);
          
    // it is showed on console for helping user
    public static void fillEmptyarray(char empt[]) {
        for (int i = 0; i < empt.length; i++) {
            empt[i] = '_';

        }

    }
 // when guess will be true ,it chance it's of look
    public static void printVaryArray(char various[]) {

        for (int i = 0; i < various.length; i++) {
            System.out.printf("%2c", various[i]);

        }
        System.out.println("");

    }

    public static void input() {

        //printVaryArray(empthy);
        while (lifeamount != 0 & amountOfEstimating != 0 & point < 5 & bigcheck == 0) {

            printVaryArray(empthy); // show finding elemnts on array
            System.out.println("your life is " + lifeamount + "\nand your amaount of estimating is " + amountOfEstimating);
            System.out.println("please a choose one of under things \n"
                    + "0.exit\n1.Locate letter\n2.Estimating time");
            int choice = input.nextInt(); // menu choice

            switch (choice) {
                case 1:
                    locateletters(empthy, answer);
                    break;

                case 2:
                    System.out.println("please a word");
                    String guess = input.next();

                    estimatingProcess(guess, answer, 0, 1);
                    break;

                case 0:
                    bigcheck = 1;
                    bigCheck = 1;
                    break;
                default:
                    System.out.println("please enter a 1 or 2 ");
                    input();
                    break;

            }

        }
        //System.out.println("game over");
    }
 // you enter a letter and funchion will return whether it exists
    public static void locateletters(char target[], String dest) {
        int check = 0;
        int amount = 0;
        System.out.println("hello ,please enter a letter");
        char letter = Character.toLowerCase(input.next().charAt(0));// check sensitive case

        //System.out.println(""+letter);
        for (int i = 0; i < dest.length(); i++) {
            if (letter == dest.charAt(i)) {
                target[i] = letter;
                check = 1;
                amount++;

            }

        }
        if (check != 1) {
            System.out.println("your word wasnt found ");
            lifeamount--;
        } else {
            System.out.println("your word was found " + amount + " times");
        }

        // input();
    }
// you try to estimate word,and funhction evalute and origin check function  
    public static void estimatingProcess(String target, String dest, int line, int check) {

        if (line < target.length()) {
            if (Character.toLowerCase(target.charAt(line)) == Character.toLowerCase(dest.charAt(line))) {
                estimatingProcess(target, dest, line + 1, check);
            } else {
                check = 0;
                estimatingProcess(target, dest, line + 1, check);
            }

        } else {

            checkOf(check);
        }
    }
// check whether guess is true or false 
    public static void checkOf(int check) {
        if (check == 0) {
            amountOfEstimating--;
            System.out.println("sorryyy your answer is wrong ,try again");
            //input();

        } else {
            System.out.println("congratssss ");
            System.out.println("our answer " + answer + " \nyour points " + ++point + "\n" + "next question");
            bigcheck = 1;

        }

    }

    public static void main(String[] args) {

        Hangman[] wordarray = new Hangman[5];// categorizing on diiferent 5 part
        wordarray[0] = new Cityname();
        wordarray[1] = new Fruitname();
        wordarray[2] = new ForeignCityName();
        wordarray[3] = new Animalname();
        wordarray[4] = new CountryName();

        System.out.println("hello welcome to our game,your purpose is passing 5 points.\nThis game is handsman which is estimating words of game\nbest regards \n\n");

        while (lifeamount != 0 & amountOfEstimating != 0 & point < 5 & bigCheck == 0) {
            int random = (int) (Math.random() * wordarray.length); // random category and random word
            wordarray[random].kelimeatma();

            empthy = new char[answer.length()]; // array of answer 
            fillEmptyarray(empthy);
            bigcheck = 0; 

            input();

        }

        if (point < 5) {

            System.out.println("YOU LOST,YOU DID NOT REACH 5 POİNTS ");

        } else {
            System.out.println("CONGRATS YOU WIN,YOU REACHED 5 POİNTS");
        }

    }
}
