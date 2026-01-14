package engineApi;

import gamePlay.Board;
import gamePlay.GameResult;
import gamePlay.Move;
import gamePlay.Player;

public class TicTacToeRuleEngine implements RuleEngine {

    @Override
    public boolean isValid(Board board, Player player, Move move) {
        return (move.getRow()>=0 && move.getCol()>=0 && move.getRow()<3 && move.getCol()<3 && board.getCellValue(move)== '-');
    }

    @Override
    public void updateGameResult(GameResult gameResult, Board board, Player player) {
        //row
        boolean rowComplete= false;
        for(int i=0; i<3; ++i) {
            char firstChar= board.getCellValue(new Move(i, 0));
            if(firstChar=='-') break;
            for(int j=0; j<3; ++j) {
                if(firstChar!= board.getCellValue(new Move(i, j))) {
                    rowComplete= false;
                    break;
                }
                rowComplete= true;
            }
        }
        if(rowComplete) {
            gameResult.setVictorious(player);
            gameResult.setGameOver(true);
            return;
        }
        //col
        boolean colComplete= false;
        for(int i=0; i<3; ++i) {
            char firstChar= board.getCellValue(new Move(0, i));
            if(firstChar=='-') break;
            for(int j=0; j<3; ++j) {
                if(firstChar!= board.getCellValue(new Move(j, i))) {
                    colComplete= false;
                    break;
                }
                colComplete= true;
            }
        }
        if(colComplete) {
            gameResult.setVictorious(player);
            gameResult.setGameOver(true);
            return;
        }
        //diag1
        boolean diag1Complete= false;
        char firstChar= board.getCellValue(new Move(0, 0));
        if(firstChar!='-') {
            for(int i=0, j=0; i<3 && j<3; ++i, ++j) {
                if(firstChar!= board.getCellValue(new Move(i, j))) {
                    diag1Complete= false;
                    break;
                }
                diag1Complete= true;
            }
        }
        if(diag1Complete) {
            gameResult.setVictorious(player);
            gameResult.setGameOver(true);
            return;
        }
        //diag2
        boolean diag2Complete= false;
        firstChar= board.getCellValue(new Move(0, 0));
        if(firstChar!='-') {
            for(int i=0, j=0; i<3 && j<3; ++i, ++j) {
                if(firstChar!= board.getCellValue(new Move(i, j))) {
                    diag2Complete= false;
                    break;
                }
                diag2Complete= true;
            }
        }
        if(diag2Complete) {
            gameResult.setVictorious(player);
            gameResult.setGameOver(true);
            return;
        }
        boolean hasEmptySpace= false;
        for(int i=0; i<3; ++i) {
            for(int j=0; j<3; ++j) {
                if(board.getCellValue(new Move(i, j))=='-') hasEmptySpace= true;
            }
        }
        if(!hasEmptySpace) {
            gameResult.setDraw(true);
            gameResult.setGameOver(true);
            return;
        }

    }

}
