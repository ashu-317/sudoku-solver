import java.util.*;

public class sodukusolver {
    public static boolean issafe(int[][] s, int row, int col, int digit) {
        int n = s.length;
        // col
        for (int i = 0; i < n; i++) {
            if (s[i][col] == digit) {
                return false;
            }
        }
        // row
        for (int i = 0; i < n; i++) {
            if (s[row][i] == digit) {
                return false;
            }
        }
        // grid
        int sr = (row / 3) * 3;// start row of 3*3 mat
        int sc = (col / 3) * 3;
        for (int i = sr; i < sr + 3; i++) {
            for (int j = sc; j < sc + 3; j++) {
                if (s[i][j] == digit) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean sodukusolve(int[][] s, int row, int col) {
        if (row == 9 && col == 0) {// next row mai col 0 hoga
            return true;
        }
        int nextrow = row, nextcol = col + 1;
        if (col + 1 == 9) {
            nextrow = row + 1;
            nextcol = 0;
        }
        if (s[row][col] != 0) {
            return sodukusolve(s, nextrow, nextcol);
        }
        for (int i = 0; i <= 9; i++) {
            if (issafe(s, row, col, i)) {
                s[row][col] = i;
                if (sodukusolve(s, nextrow, nextcol)) {
                    return true;
                }
                s[row][col] = 0;
            }
        }
        return false;
    }

    public static void printsoduku(int[][] s) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(s[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] soduku = {
                { 5, 3, 0, 0, 7, 0, 0, 0, 0 },
                { 6, 0, 0, 1, 9, 5, 0, 0, 0 },
                { 0, 9, 8, 0, 0, 0, 0, 6, 0 },
                { 8, 0, 0, 0, 6, 0, 0, 0, 3 },
                { 4, 0, 0, 8, 0, 3, 0, 0, 1 },
                { 7, 0, 0, 0, 2, 0, 0, 0, 6 },
                { 0, 6, 0, 0, 0, 0, 2, 8, 0 },
                { 0, 0, 0, 4, 1, 9, 0, 0, 5 },
                { 0, 0, 0, 0, 8, 0, 0, 7, 9 }
        };
        if (sodukusolve(soduku, 0, 0)) {
            printsoduku(soduku);
        } else {
            System.out.println("solution doesn't exist");
        }
    }
}
