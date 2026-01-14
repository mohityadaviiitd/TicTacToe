package engineApi;

import gamePlay.Board;
import gamePlay.GameResult;
import gamePlay.Move;
import gamePlay.Player;

import java.util.function.BiFunction;
import java.util.function.Function;

public class TicTacToeRuleEngine implements RuleEngine {

    @Override
    public boolean isValid(Board board, Player player, Move move) {
        return (move.getRow()>=0 && move.getCol()>=0 && move.getRow()<3 && move.getCol()<3 && board.getCellValue(move)== '-');
    }

    public boolean fullTraversal(BiFunction<Integer, Integer, Character> getCellChar)  {
        boolean streakComplete= false;
        //row/col
        for(int i=0; i<3; ++i) {
            int finalI = i;
            Function<Integer, Character> traversal= j ->getCellChar.apply(finalI, j);
            streakComplete= innerTraversal(traversal);
            if(streakComplete) return streakComplete;
        }
        return streakComplete;
    }
    public boolean innerTraversal(Function<Integer, Character> traversal) {
        for(int j=0; j<3; ++j) {
            if(traversal.apply(j)== '-' || traversal.apply(0)!= traversal.apply(j)){
                return false;
            }
        }
        return true;
    }

    @Override
    public void updateGameResult(GameResult gameResult, Board board, Player player) {

        BiFunction<Integer, Integer, Character> getRowChar= (i, j)->board.getCellValue(new Move(i, j));
        BiFunction<Integer, Integer, Character> getColChar= (i, j)->board.getCellValue(new Move(j, i));
        Function<Integer, Character> getDiag1Char= (j)-> board.getCellValue(new Move(j, j));
        Function<Integer, Character> getDiag2Char= (j)-> board.getCellValue(new Move(j, 2-j));

        boolean streakComplete= fullTraversal(getRowChar) || fullTraversal(getColChar) || innerTraversal(getDiag1Char) || innerTraversal(getDiag2Char);
        if(streakComplete) {
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
