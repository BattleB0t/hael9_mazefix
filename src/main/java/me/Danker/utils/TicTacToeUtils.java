package me.Danker.utils;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class TicTacToeUtils {
    public static int getBestMove(char[][] board) {
        HashMap<Integer, Integer> moves = new HashMap<>();
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < (board[row]).length; col++) {
                if (board[row][col] == '\000') {
                    board[row][col] = 'O';
                    int score = minimax(board, false, 0);
                    board[row][col] = Character.MIN_VALUE;
                    moves.put(Integer.valueOf(row * 3 + col + 1), Integer.valueOf(score));
                }
            }
        }
        return ((Integer)((Map.Entry)Collections.<Map.Entry>max((Collection)moves.entrySet(), (Comparator)Map.Entry.comparingByValue())).getKey()).intValue();
    }

    public static boolean hasMovesLeft(char[][] board) {
        for (char[] rows : board) {
            for (char col : rows) {
                if (col == '\000')
                    return true;
            }
        }
        return false;
    }

    public static int getBoardRanking(char[][] board) {
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == board[row][1] && board[row][0] == board[row][2]) {
                if (board[row][0] == 'X')
                    return -10;
                if (board[row][0] == 'O')
                    return 10;
            }
        }
        for (int col = 0; col < 3; col++) {
            if (board[0][col] == board[1][col] && board[0][col] == board[2][col]) {
                if (board[0][col] == 'X')
                    return -10;
                if (board[0][col] == 'O')
                    return 10;
            }
        }
        if (board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
            if (board[0][0] == 'X')
                return -10;
            if (board[0][0] == 'O')
                return 10;
        } else if (board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
            if (board[0][2] == 'X')
                return -10;
            if (board[0][2] == 'O')
                return 10;
        }
        return 0;
    }

    public static int minimax(char[][] board, boolean max, int depth) {
        int score = getBoardRanking(board);
        if (score == 10 || score == -10)
            return score;
        if (!hasMovesLeft(board))
            return 0;
        if (max) {
            int i = -1000;
            for (int j = 0; j < 3; j++) {
                for (int col = 0; col < 3; col++) {
                    if (board[j][col] == '\000') {
                        board[j][col] = 'O';
                        i = Math.max(i, minimax(board, false, depth + 1));
                        board[j][col] = Character.MIN_VALUE;
                    }
                }
            }
            return i - depth;
        }
        int bestScore = 1000;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == '\000') {
                    board[row][col] = 'X';
                    bestScore = Math.min(bestScore, minimax(board, true, depth + 1));
                    board[row][col] = Character.MIN_VALUE;
                }
            }
        }
        return bestScore + depth;
    }
}
