package engineApi;

import gamePlay.Board;
import gamePlay.GameResult;
import gamePlay.Move;
import gamePlay.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.BiFunction;
import java.util.function.Function;

public class TicTacToeRuleEngine implements RuleEngine {

    @Override
    public boolean isValid(Board board, Player player, Move move) {
        return (move.getRow()>=0 && move.getCol()>=0 && move.getRow()<3 && move.getCol()<3 && board.getCellValue(move)== '-');
    }

    public GameResult fullTraversal(BiFunction<Integer, Integer, Character> getCellChar)  {
        boolean streakComplete= false;
        GameResult gameResult= new GameResult();
        //row/col
        for(int i=0; i<3; ++i) {
            int finalI = i;
            Function<Integer, Character> traversal= j ->getCellChar.apply(finalI, j);
            gameResult= innerTraversal(traversal);
            if(gameResult.getGameOver()) {
                gameResult.setGameOver(true);
                return gameResult;
            }
        }
        return gameResult;
    }
    public GameResult innerTraversal(Function<Integer, Character> traversal) {
        GameResult gameResult= new GameResult();
        for(int j=0; j<3; ++j) {
            if(traversal.apply(j)== '-' || traversal.apply(0)!= traversal.apply(j)){
                return gameResult;
            }
        }
        gameResult.setGameOver(true);
        return gameResult;
    }
    public GameResult checkDraw(Board board) {
        GameResult gameResult= new GameResult();
        boolean hasEmptySpace= false;
        for(int i=0; i<3; ++i) {
            for(int j=0; j<3; ++j) {
                if(board.getCellValue(new Move(i, j))=='-') hasEmptySpace= true;
            }
        }
        if(!hasEmptySpace) {
            gameResult.setDraw(true);
            gameResult.setGameOver(true);
            return gameResult;
        }
        return gameResult;
    }

    @Override
    public GameResult updateGameResult(Board board, Player player) {
        GameResult gameResult= new GameResult();
        
        BiFunction<Integer, Integer, Character> getRowCells= (i, j)->board.getCellValue(new Move(i, j));
        BiFunction<Integer, Integer, Character> getColCells= (i, j)->board.getCellValue(new Move(j, i));
        Function<Integer, Character> getDiagCells= (j)-> board.getCellValue(new Move(j, j));
        Function<Integer, Character> getRevDiagCells= (j)-> board.getCellValue(new Move(j, 2-j));

        HashMap<String, ArrayList<Rule>> boardRules= new HashMap<>();
        ArrayList<Rule> rules= new ArrayList<>();

        rules.add(new Rule(board1-> fullTraversal(getRowCells)));
        rules.add(new Rule(board1-> fullTraversal(getColCells)));
        rules.add(new Rule(board1-> innerTraversal(getDiagCells)));
        rules.add(new Rule(board1-> innerTraversal(getRevDiagCells)));
        rules.add(new Rule(board1-> checkDraw(board)));


        boardRules.put(board.getClass().getName(), rules);

        for(Rule rule: boardRules.get(board.getClass().getName())) {
            gameResult= rule.getCondition().apply(board);
            if(gameResult.getGameOver()) {
                gameResult.setVictorious(player);
                return gameResult;
            }
        }


        return gameResult;

    }

}
