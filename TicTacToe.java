package com.company;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    final char SIGN_X = 'X';
    final char SIGN_O = '0';
    final char SIGN_EMPTY = '.';
    char[][] field;
    Random random;
    Scanner scanner;

    public static void main(String[] args) {
        new TicTacToe().game();
    }

    TicTacToe() {
        random = new Random();
        scanner = new Scanner(System.in);
        field = new char[3][3];
    }

    void game() {
        initTable();
        while (true) {
            turnHuman();
            if (checkWin(SIGN_X)) {
                System.out.println("Вы победили.Поздравляю!");
                break;
            }
            if (isTableFull()) {
                System.out.println("Простите, но вы проиграли!");
                break;
            }
            turnAI();
            printTable();
            if (checkWin(SIGN_O)) {
                System.out.println("Компьютер победил!");
                break;
            }
            if (isTableFull()) {
                System.out.println("НИЧЬЯ!");
                break;
            }
        }
        System.out.println("ИГРА ОКОНЧЕНА");
        printTable();
    }
    void initTable() {
        for (int row = 0; row < 3; row++)
            for (int colum = 0; colum < 3; colum++)
                field[row][colum] = SIGN_EMPTY;
    }
    void printTable() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++)
                System.out.print(field[row][col] + " ");
            System.out.println();
        }
    }
    void turnHuman() {
        int x, y;
        do {
            System.out.println("Enter X and Y (1..3):");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(x, y));
        field[y][x] = SIGN_X;
    }
    boolean isCellValid(int x, int y) {
        if (x < 0 || y < 0 || x >= 3|| y >= 3)
            return false;
        return field[y][x] == SIGN_EMPTY;
    }
    void turnAI() {
        int x, y;
        do {
            x = random.nextInt(3);
            y = random.nextInt(3);
        } while (!isCellValid(x, y));
        field[y][x] = SIGN_O;
    }
    boolean checkWin(char dot) {
        for (int i = 0; i < 3; i++)
            if ((field[i][0] == dot && field[i][1] == dot &&
                    field[i][2] == dot) ||
                    (field[0][i] == dot && field[1][i] == dot &&
                            field[2][i] == dot))
                return true;
        if ((field[0][0] == dot && field[1][1] == dot &&
                field[2][2] == dot) ||
                (field[2][0] == dot && field[1][1] == dot &&
                        field[0][2] == dot))
            return true;
        return false;
    }
    boolean isTableFull() {
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
                if (field[row][col] == SIGN_EMPTY)
                    return false;
        return true;
    }
}
