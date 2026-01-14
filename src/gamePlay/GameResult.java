package gamePlay;

public class GameResult {
    Player victorious;
    Boolean isGameOver;
    Boolean isDraw;
    Boolean hasFork;
    int numberOfMoves;

    public GameResult(Player victorious, Boolean isGameOver, Boolean isDraw, Boolean hasFork, int numberOfMoves) {
        this.victorious = victorious;
        this.isGameOver = isGameOver;
        this.isDraw = isDraw;
        this.hasFork = hasFork;
        this.numberOfMoves = numberOfMoves;
    }

    public GameResult() {
        this.isGameOver= false;
        this.isDraw= false;
    }

    public Player getVictorious() {
        return victorious;
    }

    public void setVictorious(Player victorious) {
        this.victorious = victorious;
    }

    public Boolean getGameOver() {
        return isGameOver;
    }

    public void setGameOver(Boolean gameOver) {
        isGameOver = gameOver;
    }

    public Boolean getDraw() {
        return isDraw;
    }

    public void setDraw(Boolean draw) {
        isDraw = draw;
    }
}
