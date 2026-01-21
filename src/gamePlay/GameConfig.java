package gamePlay;

public class GameConfig {
    boolean timed= false;
    Time timeControl;

    public boolean isTimed() {
        return timed;
    }

    public void setTimed(boolean timed) {
        this.timed = timed;
    }

    public Time getTimeControl() {
        return timeControl;
    }

    public void setTimeControl(Time timeControl) {
        this.timeControl = timeControl;
    }
}
