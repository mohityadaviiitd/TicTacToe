package gamePlay;

public class GameResult {
    Player victorious;
    Boolean isGameOver;
    Boolean isDraw;

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
