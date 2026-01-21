package gamePlay;

public class Player {
    char symbol;
    Time timeLeft;
    public Player(char symbol) {
        this.symbol= symbol;
    }
    public Player(char symbol, Time timeLeft) {
        this.symbol= symbol;
        this.timeLeft= timeLeft;
    }

    public Time getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(Time time) {
        this.timeLeft = time;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }
}
