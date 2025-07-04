package game2048logic;

import game2048rendering.Side;
import static game2048logic.MatrixUtils.rotateLeft;
import static game2048logic.MatrixUtils.rotateRight;

/**
 * @author  Josh Hug
 */
public class GameLogic {
    /** Moves the given tile up as far as possible, subject to the minR constraint.
     *
     * @param board the current state of the board
     * @param r     the row number of the tile to move up
     * @param c -   the column number of the tile to move up
     * @param minR  the minimum row number that the tile can land in, e.g.
     *              if minR is 2, the moving tile should move no higher than row 2.
     * @return      if there is a merge, returns the 1 + the row number where the merge occurred.
     *              if no merge occurs, then return 0.
     */
    public static int moveTileUpAsFarAsPossible(int[][] board, int r, int c, int minR) {
        // TODO: Fill this in in tasks 2, 3, 4
        int i = r - 1;//当前位置的前一个位置 注意：i可能小于0
        while (i > 0 && board[i][c] == 0) {//找到第一个非0或者到达边界
            i--;
        }
        int temp = board[r][c];//记录当前位置元素
        if (i < minR) {//保证不超过最小r
            board[r][c] = 0;
            board[minR][c] = temp;
            return 0;
        }
        if (i == 0 && board[0][c] == 0) {//swap r,c i,c
            board[r][c] = 0;
            board[i][c] = temp;
        }  else if (i >= 0) {//swap r,c i+1,c 包括 i>0 和 i==0 && boardboard[i][c] != 0 俩情况,即有阻挡
            if (board[r][c] == board[i][c]) {//有相同时合并
                board[r][c] = 0;
                board[i][c] *= 2;
                return i + 1;
            }
            board[r][c] = 0;
            board[i + 1][c] = temp;
        }
        return 0;
    }

    /**
     * Modifies the board to simulate the process of tilting column c
     * upwards.
     *
     * @param board     the current state of the board
     * @param c         the column to tilt up.
     */
    public static void tiltColumn(int[][] board, int c) {
        // TODO: fill this in in task 5
        return;
    }

    /**
     * Modifies the board to simulate tilting all columns upwards.
     *
     * @param board     the current state of the board.
     */
    public static void tiltUp(int[][] board) {
        // TODO: fill this in in task 6
        return;
    }

    /**
     * Modifies the board to simulate tilting the entire board to
     * the given side.
     *
     * @param board the current state of the board
     * @param side  the direction to tilt
     */
    public static void tilt(int[][] board, Side side) {
        // TODO: fill this in in task 7
        if (side == Side.EAST) {
            return;
        } else if (side == Side.WEST) {
            return;
        } else if (side == Side.SOUTH) {
            return;
        } else {
            return;
        }
    }
}
