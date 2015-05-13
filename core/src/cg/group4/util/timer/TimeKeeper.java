package cg.group4.util.timer;

import com.badlogic.gdx.Gdx;

import java.util.LinkedHashSet;

/**
 * Singleton timekeeper which keeps track of every individual timer.
 * @author Jurgen van Schagen
 * @author Benjamin Los
 */
public final class TimeKeeper {
	
	/**
	 * Tag used for debugging.
	 */
    public static final String TAG = TimeKeeper.class.getSimpleName();
    
    /**
     * Creates a single instance of the timekeeper. This is a singleton, because of the fact
     * that we want all timers to be tracked by one 'person'.
     */
    private static final TimeKeeper INSTANCE = new TimeKeeper();
    
    /**
     * Method that returns the singleton timekeeper.
     * @return Singleton TimeKeeper
     */
    public static TimeKeeper getInstance() {
        return INSTANCE;
    }


    LinkedHashSet<Timer> c_timers;
    private long c_previousTick;

    private TimeKeeper(){
        c_timers = new LinkedHashSet<Timer>();
        c_previousTick = System.currentTimeMillis();
        Gdx.app.debug(TimeKeeper.TAG, "Created a new TimeKeeper!");
    }

    public void init(){
        for (Timer.Global timer : Timer.Global.values()) {
            new Timer(timer.name(), timer.getDuration(), true);
        }
    }

    public void update(){
        long timeStamp = System.currentTimeMillis();
        if (timeStamp - c_previousTick > 1000) {
            for (Timer timer : c_timers) {
                timer.tick(timeStamp);
            }
            c_previousTick = timeStamp;
        }
    }

    
    protected boolean containsTimer(String name){
        for(Timer timer : c_timers){
            if(timer.getName().equals(name)){
                return true;
            }
        }
        return false;
    }
    
    void addTimer(Timer timer){
        if(c_timers.add(timer)){
            Gdx.app.debug(TAG, "Added Timer '" + timer.getName() + "'.");
        }
    }

    public Timer getTimer(String name){
        for(Timer timer : c_timers){
            if(timer.getName().equals(name)){
                return timer;
            }
        }
        return null;
    }
}
