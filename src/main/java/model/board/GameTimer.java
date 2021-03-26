package main.java.model.board;

import javax.swing.*;
import java.text.DecimalFormat;

public class GameTimer {
    private int second = 0, minute = 0;
    private String ddSecond, ddMinute;

    public GameTimer() {
        timerIncrement();
    }

    private final DecimalFormat dFormat = new DecimalFormat("00");

    public void timerIncrement(){
        second++;

        if(second == 60){
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
