package boards;

import gamePlay.Board;
import gamePlay.Move;
import gamePlay.Player;


public class TicTacToeBoard implements Board {


    char[][] ticTacToeBoard= new char[3][3];
    public TicTacToeBoard() {
        for(int i=0; i<3; ++i) {
            for(int j=0; j<3; ++j) {
                ticTacToeBoard[i][j]='-';
            }
        }
    }

    public char[][] getTicTacToeBoard() {
        return ticTacToeBoard;
    }

    public void setTicTacToeBoard(char[][] ticTacToeBoard) {
        this.ticTacToeBoard = ticTacToeBoard;
    }

    public char getCellValue(Move move) {
        return ticTacToeBoard[move.getRow()][move.getCol()];
    }

    public void setCellValue(Player player, Move move) {
        ticTacToeBoard[move.getRow()][move.getCol()]= player.getSymbol();
    }
    @Override
    public String toString() {
        StringBuilder sb= new StringBuilder();
        for(int i=0; i<3; ++i) {
            for(int j=0; j<3; ++j) {
                sb.append(getCellValue(new Move(i, j)));
            }
            sb.append(System.lineSeparator());;
        }
        return sb.toString();
    }
}
