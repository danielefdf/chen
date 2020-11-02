package application.app;

import view.game.GameStage;

/**
 * Per costanti utilizzate sia in {@link MatchTui} che in {@link GameStage}
 */
public class MainUtils {

    /* time managing */

    public static final int MIN_TIME_MILLIS = 1000;

    public static final int TIMER_PERIOD_MILLIS = 200;
    public static final double PV_TIMELINE_PERIOD_MILLIS = 300;
    public static final double MOVE_TIMELINE_PERIOD_MILLIS = 1000;

    /* stop/start sleep time */

    public static final long THREAD_GAME_PAUSE_MILLIS = 3000;
    public static final long THREAD_MOVE_PAUSE_MILLIS = 500;

    /* other */
    public static final int ENGINE_TIME_MILLIS = 50;

}
