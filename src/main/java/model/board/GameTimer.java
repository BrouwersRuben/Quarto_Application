package main.java.model.board;

import java.text.DecimalFormat;

/**
 * Class for dealing with the built-in game timer.
 * @author Rodžers Ušackis
 * @author Ruben Brouwers
 * @version 1.0
 */

public class GameTimer {
    private final DecimalFormat dFormat = new DecimalFormat("00");
    private int second = 0, minute = 0;
    private String ddSecond, ddMinute;

    public GameTimer() {
        timerIncrement();
    }

    /**
     * Method which displays the timer in minutes and seconds.
     */
    public void timerIncrement() {
        second++;

        if (second == 60) {
            second = 0;
            minute++;
        }

        ddSecond = dFormat.format(second);
        ddMinute = dFormat.format(minute);
    }

    public String getDdSecond() {
        return ddSecond;
    }

    public String getDdMinute() {
        return ddMinute;
    }
}
