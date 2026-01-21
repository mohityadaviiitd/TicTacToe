package gamePlay;

import engineApi.RuleEngine;
import engineApi.TicTacToeRuleEngine;

public class GamePlay {
    public Board board;
    public GameConfig gameConfig;

    public GamePlay(Board board, GameConfig gameConfig) {
        this.board = board;
        this.gameConfig = gameConfig;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public GameConfig getGameConfig() {
        return gameConfig;
    }

    public void setGameConfig(GameConfig gameConfig) {
        this.gameConfig = gameConfig;
    }

    public void makeMove(Player player, Move move) {
        RuleEngine ruleEngine= new TicTacToeRuleEngine();
        boolean isValidMove= ruleEngine.isValid(board, player, move);
        if(isValidMove) {
            board.setCellValue(player, move);
        }
    }
}
