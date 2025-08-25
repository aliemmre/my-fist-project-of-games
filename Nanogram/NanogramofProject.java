/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.nanogramofproject;

import java.util.Scanner;

/**
 *
 * @author aliemre
 */
public class NanogramofProject {

     static Scanner input = new Scanner(System.in);
    static int height = (int) (Math.random() * 10+0.3);
    static char nanogram[][] = new char[height][height];
    static int row[] = new int[height];
    static int col[] = new int[height];
    static int trials = 0;
    static boolean check = true;

    public static void main(String[] args) {
        randomFill(nanogram, 0); // Fill nanogram randomly
    }

    // Fill the nanogram with random '#' and then 'X'
    public static void randomFill(char nanog[][], int line) {
        if (line < nanog.length) {
            for (int j = 0; j < nanog.length; j++) {
                int random = (int) (Math.random() * nanog.length);
                int random1 = (int) (Math.random() * nanog.length);
                nanog[random][random1] = '#';
            }
            randomFill(nanog, line + 1);
        } else {
            for (int i = 0; i < nanog.length; i++) {
                for (int j = 0; j < nanog.length; j++) {
                    if (nanog[i][j] != '#') {
                        nanog[i][j] = 'X';
                    }
                }
            }
            evaluteRowandColumn(row, col, 0); // Calculate row and column clues
        }
    }

    // Calculate the number of '#' in each row and column
    public static void evaluteRowandColumn(int row[], int col[], int line) {
        if (line < row.length) {
            int tempR = 0;
            int tempC = 0;
            for (int j = 0; j < row.length; j++) {
                if (nanogram[line][j] == '#') tempR++;
                if (nanogram[j][line] == '#') tempC++;
            }
            row[line] = tempR;
            col[line] = tempC;
            evaluteRowandColumn(row, col, line + 1);
        } else {
            userPart(row, col, -1); // Show clues to the user
        }
    }

    // Print the nanogram and clues (for debugging or end)
    public static void printnano(int row[], int col[]) {
        for (int i = -1; i < row.length; i++) {
            if (i >= 0) System.out.printf("%d", row[i]);
            for (int j = 0; j < row.length; j++) {
                if (i >= 0) System.out.printf("%2c", nanogram[i][j]);
                if (i == -1 && j == 0) {
                    for (int k = 0; k < col.length; k++) {
                        System.out.printf(k == 0 ? "%3d" : "%2d", col[k]);
                    }
                }
            }
            System.out.println("");
        }
    }

    // Display row and column clues to the user
    public static void userPart(int row[], int col[], int line) {
        if (line < row.length) {
            if (line == -1) {
                for (int j = 0; j < col.length; j++)
                    System.out.printf(j == 0 ? "%3d" : "%2d", col[j]);
                System.out.println("");
            } else if (line >= 0) {
                System.out.println(row[line]);
            }
            userPart(row, col, line + 1);
        } else {
            System.out.println("Please fill the table using '#' and 'X', no spaces.\n");
            takeInput(0); // Start taking user input
        }
    }

    // Take user input row by row and check correctness
    public static void takeInput(int line) {
        check = true;
        if (line < nanogram.length) {
            System.out.println("Enter row " + (line + 1) + " (errors: " + trials + "):");
            String str = input.next();
            for (int j = 0; j < nanogram.length; j++) {
                if (str.charAt(j) != nanogram[line][j]) {
                    System.out.println("Wrong, try again.\n");
                    trials++;
                    check = false;
                    break;
                }
            }
            if (check) {
                System.out.println("Correct, continue.\n");
                takeInput(line + 1);
            } else {
                takeInput(line);
            }
        } else {
            System.out.println("Finished with " + trials + " errors. Correct table:");
            printnano(row, col);
        }
    }
}
