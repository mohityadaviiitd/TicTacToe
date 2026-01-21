package engineApi;

import gamePlay.*;

public interface RuleEngine {

    boolean isValid(Board board, Player player, Move move);

    GameResult updateGameResult(GamePlay gamePlay, Player player);
}
