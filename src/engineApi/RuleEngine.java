package engineApi;

import gamePlay.Board;
import gamePlay.GameResult;
import gamePlay.Move;
import gamePlay.Player;

public interface RuleEngine {

    boolean isValid(Board board, Player player, Move move);

    GameResult updateGameResult(Board board, Player player);
}
