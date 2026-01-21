package engineApi;

import boards.TicTacToeBoard;
import gamePlay.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.BiFunction;
import java.util.function.Function;

public class TicTacToeRuleEngine implements RuleEngine {

    @Override
    public boolean isValid(Board board, Player player, Move move) {
        TicTacToeBoard board1= (TicTacToeBoard)board;
        return (move.getRow()>=0 && move.getCol()>=0 && move.getRow()<3 && move.getCol()<3 && board1.getCellValue(move)== '-');
    }

    public GameResult fullTraversal(BiFunction<Integer, Integer, Character> getCellChar)  {
        GameResultBuilder gameResult= new GameResultBuilder();
        //row/col
        for(int i=0; i<3; ++i) {
            int finalI = i;
            Function<Integer, Character> traversal= j ->getCellChar.apply(finalI, j);
            if(innerTraversal(traversal).getGameOver()) {
                return gameResult.isGameOver(true).isDraw(false).build();
            }
        }
        return gameResult.build();
    }
    public GameResult innerTraversal(Function<Integer, Character> traversal) {
        GameResultBuilder gameResult= new GameResultBuilder();
        for(int j=0; j<3; ++j) {
            if(traversal.apply(j)== '-' || traversal.apply(0)!= traversal.apply(j)){
                return gameResult.build();
            }
        }
        return gameResult.isGameOver(true).build();
    }
    public GameResult checkDraw(TicTacToeBoard board) {
        GameResultBuilder gameResult= new GameResultBuilder();
        boolean hasEmptySpace= false;
        for(int i=0; i<3; ++i) {
            for(int j=0; j<3; ++j) {
                if(board.getCellValue(new Move(i, j))=='-') hasEmptySpace= true;
            }
        }
        if(!hasEmptySpace) {
            return gameResult.isDraw(true).isGameOver(true).build();
        }
        return gameResult.build();
    }
    public GameResult timeoutCheck(GamePlay gamePlay, Player player) {
        GameResultBuilder gameResult= new GameResultBuilder();
        if(gamePlay.getGameConfig().isTimed()) {
            if(player.getTimeLeft().getMilliseconds()<=0) {
                return gameResult.isGameOver(true).winBy("timeout").build();
            }
        }
        return gameResult.build();
    }

    @Override
    public GameResult updateGameResult(GamePlay gamePlay, Player player) {
        TicTacToeBoard board= (TicTacToeBoard) gamePlay.getBoard();
        GameResult gameResult= new GameResult();


        
        BiFunction<Integer, Integer, Character> getRowCells= (i, j)->board.getCellValue(new Move(i, j));
        BiFunction<Integer, Integer, Character> getColCells= (i, j)->board.getCellValue(new Move(j, i));
        Function<Integer, Character> getDiagCells= (j)-> board.getCellValue(new Move(j, j));
        Function<Integer, Character> getRevDiagCells= (j)-> board.getCellValue(new Move(j, 2-j));


        HashMap<String, ArrayList<Rule<Board, GameResult>>> boardRules= new HashMap<>();
        ArrayList<Rule<Board, GameResult>> rules= new ArrayList<>();
        rules.add(new Rule<>(board1-> timeoutCheck(gamePlay, player)));
        rules.add(new Rule<>(board1-> fullTraversal(getRowCells)));
        rules.add(new Rule<>(board1-> fullTraversal(getColCells)));
        rules.add(new Rule<>(board1-> innerTraversal(getDiagCells)));
        rules.add(new Rule<>(board1-> innerTraversal(getRevDiagCells)));
        rules.add(new Rule<>(board1-> checkDraw(board)));


        boardRules.put(board.getClass().getName(), rules);

        for(Rule<Board, GameResult> rule: boardRules.get(board.getClass().getName())) {
            gameResult= rule.getCondition().apply(board);
            if(gameResult.getGameOver()) {
                gameResult.setVictorious(player);
                return gameResult;
            }
        }


        return gameResult;

    }

}
