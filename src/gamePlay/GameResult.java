package gamePlay;

public class GameResult {
    Player victorious;
    Boolean isGameOver;
    Boolean isDraw;
    Boolean hasFork;
    int numberOfMoves;
    String winBy="";

    public GameResult(Player victorious, Boolean isGameOver, Boolean isDraw, Boolean hasFork, int numberOfMoves, String winBy) {
        this.victorious = victorious;
        this.isGameOver = isGameOver;
        this.isDraw = isDraw;
        this.hasFork = hasFork;
        this.numberOfMoves = numberOfMoves;
        this.winBy= winBy;
    }

    public GameResult() {
        this.isGameOver= false;
        this.isDraw= false;
    }
    public String getWinBy() {
        return winBy;
    }

    public void setWinBy(String winBy) {
        this.winBy = winBy;
    }

    public int getNumberOfMoves() {
        return numberOfMoves;
    }

    public void setNumberOfMoves(int numberOfMoves) {
        this.numberOfMoves = numberOfMoves;
    }

    public Boolean getHasFork() {
        return hasFork;
    }

    public void setHasFork(Boolean hasFork) {
        this.hasFork = hasFork;
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
