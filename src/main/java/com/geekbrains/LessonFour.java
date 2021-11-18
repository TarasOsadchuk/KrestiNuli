package com.geekbrains;

import java.util.Random;
import java.util.Scanner;

public class LessonFour {

    public static int SIZE = 5;
    public static final char DOT_EMPTY = '•';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static char[][] MAP;
    public static Scanner SCANNER = new Scanner(System.in);
    public static Random RANDOM = new Random();
    /*
        Переписал игру используя методичку и ваш урок.
    Сделал ее 5х5. Переработал checkWin(); чтобы не было if.
    Хотя насчет 'диагональ' все-таки оставил if, думаю так удобнее
    потому что по моему мнению код был бы больше, хотя не исключаю
    что лучший вариант есть 100%. И немного изменил карту, чтобы
    сначала не было цифры 0.
     */

    public static void main(String[] args) {
        System.out.println();
        System.out.println("GAME X and O");
        initMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if (checkWin(DOT_X)) {
                System.out.println("Победил человек");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
            aiTurn();
            printMap();
            if (checkWin(DOT_O)) {
                System.out.println("Победил ИИ");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
        }
        System.out.println("Игра закончена");
        SCANNER.close();
    }

    public static boolean checkWin(char symbol) {
        int a = 0;
        int b = 0;
        while (a < SIZE) {
            int c = 0;
            if (MAP[a][c] == symbol && MAP[a][c + 1] == symbol && MAP[a][c + 2] == symbol && MAP[a][c + 3] == symbol && MAP[a][c + 4] == symbol)
                return true;
            a++;
        }
        while (b < SIZE) {
            int c = 0;
            if (MAP[c][b] == symbol && MAP[c + 1][b] == symbol && MAP[c + 2][b] == symbol && MAP[c + 3][b] == symbol && MAP[c + 4][b] == symbol)
                return true;
            b++;
        }
        if (MAP[0][0] == symbol && MAP[1][1] == symbol && MAP[2][2] == symbol && MAP[3][3] == symbol && MAP[4][4] == symbol)
            return true;
        return MAP[4][0] == symbol && MAP[3][1] == symbol && MAP[2][2] == symbol && MAP[1][3] == symbol && MAP[0][4] == symbol;
    }

    public static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (MAP[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }

    public static void aiTurn() {
        int x, y;
        do {
            x = RANDOM.nextInt(SIZE);
            y = RANDOM.nextInt(SIZE);
        } while (!isCellValid(x, y));
        System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y + 1));
        MAP[y][x] = DOT_O;
    }

    public static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты в формате X Y");
            x = SCANNER.nextInt() - 1;
            y = SCANNER.nextInt() - 1;
        } while (!isCellValid(x, y));
        MAP[y][x] = DOT_X;
    }

    public static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
        return MAP[y][x] == DOT_EMPTY;
    }

    public static void initMap() {
        MAP = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                MAP[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            if (i == 0) {
                System.out.print("  ");
                continue;
            }
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(MAP[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}