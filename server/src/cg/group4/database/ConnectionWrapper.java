package cg.group4.database;

import java.sql.Statement;

/**
 * Wrapper used for the 'Connected' states.
 *
 * @author Jurgen van Schagen
 */
public abstract class ConnectionWrapper {

    /**
     * Attempts to return a Connected Wrapper.
     *
     * @return A Connected State.
     */
    public abstract ConnectionWrapper openConnection();

    /**
     * Attempts to return a NoConnection Wrapper.
     *
     * @return A Connected State.
     */
    public abstract ConnectionWrapper closeConnection();

    public abstract void commit();

    public abstract Statement query();
}