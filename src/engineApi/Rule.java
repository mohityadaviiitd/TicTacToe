package engineApi;

import gamePlay.Board;
import gamePlay.GameResult;

import java.util.function.BiFunction;
import java.util.function.Function;

class Rule<T, R> {
    Function<T, R> condition;
    public Rule(Function<T ,R> condition) {
        this.condition= condition;
    }
    public R apply(T t) {
        return condition.apply(t);
    }

    public Function<T, R> getCondition() {
        return condition;
    }

    public void setCondition(Function<T, R> condition) {
        this.condition = condition;
    }
}
