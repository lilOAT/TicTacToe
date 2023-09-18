package com.example.tictactoe;

public class WinChecker {
    public static boolean checkWin(char[][] board, int size, int condition) {
        boolean win = false;
        if(checkWinHrzn(board, size, condition) || checkWinVrt(board, size, condition) || checkWinDgn(board, size, condition)) {
            win = true;
        }

        return win;
    }

    private static boolean checkWinHrzn(char[][] board, int size, int condition) {
        boolean win = false;
        int count = 0;
        char currChar;
        System.out.println(board[size-1][0]);
        for(int i = 0; i < size; i++) {
            currChar = board[i][0];
            for(int j = 0; j < size; j++) {
                if(board[i][j] == currChar && board[i][j]!=' ') {
                    count++;
                    if(count == condition) {
                        win = true;
                    }
                } else {
                    currChar = board[i][j];
                    count = 1;
                }
            }
            count = 0;
        }
        return win;
    }
    private static boolean checkWinVrt(char[][] board, int size, int condition) {
        boolean win = false;
        int count = 0;
        char currChar;
        for(int i = 0; i < size; i++) {
            currChar = board[0][i];
            for(int j = 0; j < size; j++) {
                if(board[j][i] == currChar && board[j][i]!=' ') {
                    count++;
                    if(count == condition) {
                        win = true;
                    }
                } else {
                    currChar = board[j][i];
                    count = 1;
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

        //Check diagonal win heading south-east
        for(int a = 1; a < size; a++) {
            if(!win1){
                if(checkWinDgnRec1(board, size, condition, a, 1, 1) || checkWinDgnRec1(board, size, condition, 1, a, 1)) {
                    win1 = true;
                }
            }
        }
        //Check diagonal win heading north-west
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

    // Diagonal South-East
    private static boolean checkWinDgnRec1(char[][] board, int size, int condition, int i, int j, int count) {
        boolean win = false;
        char prevChar = board[i-1][j-1];
        if(board[i][j] == prevChar && board[i][j]!=' ') {
            count++;
        }else {
            count = 1;
        }
        if(count == condition) {
            win = true;
        }
        if(i+1<size && j+1<size && win==false) {
            win = checkWinDgnRec1(board, size, condition, i+1, j+1, count);
        }
        return win;
    }

    // Diagonal North-West
    private static boolean checkWinDgnRec2(char[][] board, int size, int condition, int i, int j, int count) {
        boolean win = false;
        char prevChar = board[i-1][j+1];
        if(board[i][j] == prevChar  && board[i][j]!=' ') {
            count++;
        }else {
            count = 1;
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
