package gamePlay;

public class GameResultBuilder {
    Player victorious;
    Boolean isGameOver= false;
    Boolean isDraw= false;
    Boolean hasFork= false;
    int numberOfMoves;
    String winBy= "";

    public GameResultBuilder victorious(Player victorious) {
        this.victorious= victorious;
        return this;
    }
    public GameResultBuilder isGameOver(boolean isGameOver) {
        this.isGameOver= isGameOver;
        return this;
    }
    public GameResultBuilder isDraw(boolean isDraw) {
        this.isDraw= isDraw;
        return this;
    }
    public GameResultBuilder hasFork(boolean hasFork) {
        this.hasFork= hasFork;
        return this;
    }
    public GameResultBuilder numberOfMoves(int numberOfMoves) {
        this.numberOfMoves= numberOfMoves;
        return this;
    }
    public GameResultBuilder winBy(String winBy) {
        this.winBy= winBy;
        return this;
    }
    public GameResult build() {
        return new GameResult(victorious, isGameOver, isDraw, hasFork, numberOfMoves, winBy);
    }
}
