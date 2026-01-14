package engineApi;

import gamePlay.Board;
import gamePlay.GameResult;
import gamePlay.Move;
import gamePlay.Player;

public interface RuleEngine {

    boolean isValid(Board board, Player player, Move move);

    void updateGameResult(GameResult gameResult, Board board, Player player);
}
