import java.util.*;

public class WinCondition {
    
    public static void main(String[] args) {
        char[][] gameBoard;
        int boardSize = 0;
        int condition = 3;
        boolean win = false;
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the size of game board: ");
        boardSize = sc.nextInt();
        gameBoard = new char[boardSize][boardSize];

        if(boardSize == 3) {
            gameBoard = game3(boardSize).clone();
        }
        if(boardSize == 5) {
            gameBoard = game5(boardSize).clone();
        }
        win = checkWin(gameBoard, boardSize, condition);
        System.out.println("Status: " + win);

        
    }

    private static char[][] game3(int size) {
        char[][] gameBoardTemp = new char[3][3];
        gameBoardTemp[0][0]='x';  gameBoardTemp[0][1]='o';  gameBoardTemp[0][2]='x';
        gameBoardTemp[1][0]='o';  gameBoardTemp[1][1]='x';  gameBoardTemp[1][2]='o';
        gameBoardTemp[2][0]='o';  gameBoardTemp[2][1]='x';  gameBoardTemp[2][2]='x';
        return gameBoardTemp;
    }

    private static char[][] game5(int size) {
        char[][] gameBoardTemp = new char[5][5];
        gameBoardTemp[0][0]='x'; gameBoardTemp[0][1]='x'; gameBoardTemp[0][2]='x'; gameBoardTemp[0][3]='x'; gameBoardTemp[0][4]='o'; 
        gameBoardTemp[1][0]='x'; gameBoardTemp[1][1]='o'; gameBoardTemp[1][2]='o'; gameBoardTemp[1][3]='a'; gameBoardTemp[1][4]='x'; 
        gameBoardTemp[2][0]='x'; gameBoardTemp[2][1]='o'; gameBoardTemp[2][2]='o'; gameBoardTemp[2][3]='o'; gameBoardTemp[2][4]='x'; 
        gameBoardTemp[3][0]='x'; gameBoardTemp[3][1]='a'; gameBoardTemp[3][2]='x'; gameBoardTemp[3][3]='x'; gameBoardTemp[3][4]='x';
        gameBoardTemp[4][0]='x'; gameBoardTemp[4][1]='o'; gameBoardTemp[4][2]='x'; gameBoardTemp[4][3]='x'; gameBoardTemp[4][4]='x'; 
        return gameBoardTemp;
    }

    private static boolean checkWin(char[][] board, int size, int condition) {
        boolean win = false;
        if(checkWinHrzn(board, size, condition) || checkWinVrt(board, size, condition) || checkWinDgn(board, size, condition)) {
            win = true;
        }
        
        return win;
    }

    private static boolean checkWinHrzn(char[][] board, int size, int condition) {
        boolean win = false;
        int count = 0;
        char currChar;;
        for(int i = 0; i < size; i++) {
            currChar = board[i][0];
            for(int j = 0; j < size; j++) {
                if(board[i][j] == currChar) {
                    count++;
                    if(count == condition) {
                        win = true;
                    }
                } else {
                    currChar = board[i][j];
                    count = 0;
                }
            }
            count = 0;
        }
        return win;
    }
    private static boolean checkWinVrt(char[][] board, int size, int condition) {
        boolean win = false;
        int count = 0;
        char currChar;;
        for(int i = 0; i < size; i++) {
            currChar = board[i][0];
            for(int j = 0; j < size; j++) {
                if(board[j][i] == currChar) {
                    count++;
                    if(count == condition) {
                        win = true;
                    }
                } else {
                    currChar = board[j][i];
                    count = 0;
                }
            }
            count = 0;
        }
        return win;
    }

    private static boolean checkWinDgn(char[][] board, int size, int condition) {
        boolean win1 = false;
        boolean win2 = false;
        boolean win = false;
        char currChar;;
        for(int a = 1; a < size; a++) {
            if(!win1){
                if(checkWinDgnRec1(board, size, condition, a, 1, 1) || checkWinDgnRec1(board, size, condition, 1, a, 1)) {
                    win1 = true;
                }
            }
        }
        System.out.println("Next Diag");
        for(int b = size-2; b >= 0; b--) {
            if(!win2){
                if(checkWinDgnRec2(board, size, condition, 1, b, 1) || checkWinDgnRec2(board, size, condition, size-1-b, size-2, 1)) {
                    win2 = true;
                }
            }
        }
        if(win1 || win2){
            win = true;
        }
        return win;
    }

    private static boolean checkWinDgnRec1(char[][] board, int size, int condition, int i, int j, int count) {
        boolean win = false;
        char prevChar = board[i-1][j-1];
        if(board[i][j] == prevChar) {
            count++;
            System.out.println("prev: " + prevChar + " curr: " + board[i][j] + " at: " + i + j + " Count: " + count);
        }else {
            count = 1;
            System.out.println("Failed at " + i + j);
        }
        if(count == condition) {
            win = true;
        }
        if(i+1<size && j+1<size && win==false) {
            win = checkWinDgnRec1(board, size, condition, i+1, j+1, count);
        }
        return win;
    }

    private static boolean checkWinDgnRec2(char[][] board, int size, int condition, int i, int j, int count) {
        boolean win = false;
        char prevChar = board[i-1][j+1];
        if(board[i][j] == prevChar) {
            count++;
            System.out.println("prev: " + prevChar + " curr: " + board[i][j] + " at: " + i + j + " Count: " + count);
        }else {
            count = 1;
            System.out.println("Failed at " + i + j);
            //currChar = board[i][j];
        }
        if(count == condition) {
            win = true;
        }
        if(i+1<size && j-1>=0 && win==false) {
            win = checkWinDgnRec2(board, size, condition, i+1, j-1, count);
        }
        return win;
    }
}