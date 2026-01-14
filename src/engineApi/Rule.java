package engineApi;

import gamePlay.Board;
import gamePlay.GameResult;

import java.util.function.Function;

class Rule {
    Function<Board, GameResult> condition;
    public Rule(Function<Board ,GameResult> condition) {
        this.condition= condition;
    }

    public Function<Board, GameResult> getCondition() {
        return condition;
    }

    public void setCondition(Function<Board, GameResult> condition) {
        this.condition = condition;
    }
}
