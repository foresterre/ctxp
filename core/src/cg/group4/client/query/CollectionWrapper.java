package cg.group4.client.query;

import cg.group4.rewards.Collection;

/**
 * Wraps the collection and makes it transmittable to the server.
 */
public final class CollectionWrapper extends Data {

    /**
     * The collection to send.
     */
    protected Collection cCollection;

    /**
     * The user whom this collection belongs to.
     */
    protected UserData cUserData;

    /**
     * Constructs a new collection wrapper.
     * @param collection The collection that needs to be send.
     * @param userData The users data.
     */
    public CollectionWrapper(final Collection collection, final UserData userData) {
        cCollection = collection;
        cUserData = userData;
    }

    /**
     * Returns the collection.
     * @return the collection.
     */
    public Collection getcCollection() {
        return cCollection;
    }

    /**
     * Sets the collection.
     * @param collection The new collection.
     */
    public void setcCollection(final Collection collection) {
        cCollection = collection;
    }

    /**
     * Returns the userdata.
     * @return the userdata.
     */
    public UserData getcUserData() {
        return cUserData;
    }

    /**
     * Sets the userdata.
     * @param userData the new userdata.
     */
    public void setcUserData(final UserData userData) {
        cUserData = userData;
    }
}
