package cg.group4.server.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

/**
 * In this state a database connection is successfully created.
 * Throws an exception if Connection could not be created in the constructor.
 *
 * @author Jurgen van Schagen
 */
public final class Connected extends ConnectionWrapper {
    /**
     * Default Java logging tool. Used for logging inside the class.
     */
    protected static final Logger LOGGER = Logger.getLogger(Connected.class.getName());

    /**
     * The Connection with the database.
     */
    protected Connection cConnection;

    /**
     * Attempts to establish a connection with the SQLite database.
     * @param isRemote Determines if to use remote db or local db.
     * @throws ClassNotFoundException Thrown if sqlite.JDBC dependencies are missing.
     * @throws SQLException           Thrown if connection could not be established.
     */
    public Connected(boolean isRemote) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        if(isRemote) {
            cConnection = DriverManager.getConnection("jdbc:sqlite:remote.sqlite");
        } else {
            cConnection = DriverManager.getConnection("jdbc:sqlite:local.sqlite");
        }
        cConnection.setAutoCommit(false);
        LOGGER.info("Successfully connected to the database.");
    }

    @Override
    public void commit() {
        try {
            cConnection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a Statement on which queries can be executed.
     *
     * @return Statement for queries.
     */
    public Statement query() {
        try {
            return cConnection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public ConnectionWrapper openConnection(boolean isRemote) {
        return this;
    }

    @Override
    public ConnectionWrapper closeConnection() {
        try {
            cConnection.close();
            LOGGER.info("Successfully disconnected from the database.");
        } catch (SQLException e) {
            LOGGER.severe("Something went wrong while trying to disconnect! " + e.getMessage());
        }
        return new NoConnection();
    }
}