package cg.group4.server.database.query;

import cg.group4.data_structures.collection.collectibles.Collectible;
import cg.group4.server.database.DatabaseConnection;

import java.io.Serializable;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Adds a new collectible to the server.
 */
public class AddCollectible extends Query {

    /**
     * Id of the group the collectible belongs to.
     */
    protected final String cGroupId;
    /**
     * The collectible to add.
     */
    protected Collectible cCollectible;

    /**
     * Inserts a new Collectible in the database.
     *
     * @param collectible The collectible to update.
     * @param groupId     Id of the group the collectible belongs to.
     */
    protected AddCollectible(Collectible collectible, String groupId) {
        cCollectible = collectible;
        cGroupId = groupId;
    }

    @Override
    public Serializable query(DatabaseConnection databaseConnection) throws SQLException {
        Statement statement = databaseConnection.query();
        statement.executeUpdate("INSERT INTO Collectible (OwnerId, Type, Hue, Amount, Date, GroupId) VALUES ('"
                + cCollectible.getOwnerId() + "', '" + cCollectible.getClass().getSimpleName() + "', "
                + cCollectible.getHue() + ", " + cCollectible.getAmount() + ", '" + cCollectible.getDateAsString()
                + "', '" + cGroupId + "')");

        databaseConnection.commit();
        statement.close();
        return null;
    }
}