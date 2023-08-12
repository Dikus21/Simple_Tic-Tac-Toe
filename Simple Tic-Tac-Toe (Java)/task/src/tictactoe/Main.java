package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // write your code here
        int size = 3;
        char[][] board = new char[size+2][size+2];
        boardMaker(board, size);
        updateBoard(board, size, 'X');
    }

    public static void updateBoard(char[][] matrix, int size, char printSwitch) {
        Scanner sc = new Scanner(System.in);
        int row;
        int col;
        if(sc.hasNextInt()){
            row = sc.nextInt();
        } else {
            System.out.println("You should enter numbers!");
            updateBoard(matrix, size, printSwitch);
            return;
        }

        if(sc.hasNextInt()){
            col = sc.nextInt();
        } else {
            System.out.println("You should enter numbers!");
            updateBoard(matrix, size, printSwitch);
            return;
        }

        if(row > size || row < 1 || col > size || col < 1){
            System.out.println("Coordinates should be from 1 to 3!");
            updateBoard(matrix,size,printSwitch);
        }else if(matrix[row][col] != ' '){
            System.out.println("This cell is occupied! Choose another one!");
            updateBoard(matrix,size,printSwitch);
        } else {
            matrix[row][col] = printSwitch;
            printBoard(matrix);
        }
    }

    public static void printBoard(char[][] matrix) {
        for (char[] boardRow : matrix) {
            for (char boardCol : boardRow) {
                if(boardCol == '-') {
                    System.out.print(boardCol + "-");
                } else {
                    System.out.print(boardCol + " ");
                }
            }
            System.out.println();
        }
        gameState(matrix, 3);
    }

    public static void gameState(char[][] matrix, int size) {
        int empty = 0;
        int wins = 0;
        char winSide = ' ';
        for (int i = 1; i <= size; i++) {
            int countH = 0;
            int countV = 0;
            int countCL = 0;
            int countCR = 0;
            if(matrix[i][size] == ' ')empty++;
            for (int j = 1; j < size; j++) {
                if(matrix[i][j] == ' ')empty++;
                if (matrix[i][j] == matrix[i][j + 1] && matrix[i][j] != ' ') countH++;
                if (matrix[j][i] == matrix[j + 1][i] && matrix[j][i] != ' ') countV++;
                if (i == 1 && matrix[j][j] == matrix[j + 1][j + 1] && matrix[j][j] != ' ') countCL++;
                if (i == 1 && matrix[j][size - j + 1] == matrix[j + 1][size - j] && matrix[j][size - j + 1] != ' ') countCR++;
            }
            if (countH == 2) {
                winSide = matrix[i][1];
                wins++;
            }
            if (countV == 2) {
                winSide = matrix[1][i];
                wins++;
            }
            if (countCL == 2) {
                winSide = matrix[1][1];
                wins++;
            }
            if (countCR == 2) {
                winSide = matrix[1][size];
                wins++;
            }
        }
        System.out.println(empty);
        if (wins == 1) {
            System.out.printf("%c wins", winSide);
        } else if (empty == 0){
            System.out.println("Draw");
        } else if (empty % 2 == 0) {
            updateBoard(matrix, size, 'O');
        } else  {
            updateBoard(matrix, size, 'X');
        }
    }

    public static void boardMaker(char[][]  matrix, int size) {
        int boardSize = size + 2;
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if(i == 0 || i == boardSize - 1) {
                    System.out.print("--");
                    matrix[i][j] = '-';
                } else if(j == 0 || j == boardSize - 1){
                    System.out.print("| ");
                    matrix[i][j] = '|';
                } else {
                    System.out.print("  ");
                    matrix[i][j] = ' ';
                }
            }
            System.out.println();
        }
        /*if(countX - countO >= 2 || countX - countO <= -2){
            System.out.println("Impossible");
        } else {
            gameState(matrix, size);
        }*/
    }
}

