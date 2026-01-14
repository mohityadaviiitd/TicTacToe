package gamePlay;

import engineApi.RuleEngine;
import engineApi.TicTacToeRuleEngine;

public class GamePlay {
    public void makeMove(Board board, Player player, Move move) {
        RuleEngine ruleEngine= new TicTacToeRuleEngine();
        boolean isValidMove= ruleEngine.isValid(board, player, move);
        if(isValidMove) {
            board.setCellValue(player, move);
        }
    }
}
