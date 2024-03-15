import java.util.ArrayList;

public class SudokuSolver {

    static class Cell {
        int row;
        int col;

        Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static boolean solveSudoku(int[][] board) {
        int N = board.length;
        Cell emptyCell = findEmptyCell(board);

        if (emptyCell == null) {
            return true;
        }

        int row = emptyCell.row;
        int col = emptyCell.col;

        for (int num = 1; num <= 9; num++) {
            if (isSafe(board, row, col, num)) {
                board[row][col] = num;
                if (solveSudoku(board)) {
                    return true;
                }
                board[row][col] = 0;
            }
        }
        return false;
    }

    private static boolean isSafe(int[][] board, int row, int col, int num) {
        return !usedInRow(board, row, num) && !usedInColumn(board, col, num) && !usedInSubgrid(board, row - row % 3, col - col % 3, num);
    }

    private static boolean usedInRow(int[][] board, int row, int num) {
        for (int col = 0; col < board.length; col++) {
            if (board[row][col] == num) {
                return true;
            }
        }
        return false;
    }

    private static boolean usedInColumn(int[][] board, int col, int num) {
        for (int row = 0; row < board.length; row++) {
            if (board[row][col] == num) {
                return true;
            }
        }
        return false;
    }

    private static boolean usedInSubgrid(int[][] board, int startRow, int startCol, int num) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row + startRow][col + startCol] == num) {
                    return true;
                }
            }
        }
        return false;
    }

    private static Cell findEmptyCell(int[][] board) {
        ArrayList<Cell> emptyCells = new ArrayList<>();
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                if (board[row][col] == 0) {
                    emptyCells.add(new Cell(row, col));
                }
            }
        }
        return emptyCells.isEmpty() ? null : emptyCells.get(0);
    }

    private static void printBoard(int[][] board) {
        for (int[] row : board) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] sudokuBoard = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        System.out.println("Sudoku Puzzle:");
        printBoard(sudokuBoard);
        System.out.println("Solved Sudoku:");
        if (solveSudoku(sudokuBoard)) {
            printBoard(sudokuBoard);
        } else {
            System.out.println("No solution exists.");
        }
    }
}
