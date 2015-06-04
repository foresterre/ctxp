package cg.group4.client.connection;

import cg.group4.client.query.CollectionWrapper;
import cg.group4.client.query.Update;
import cg.group4.client.query.UserData;
import cg.group4.client.query.Reply;
import cg.group4.client.query.Request;
import cg.group4.rewards.Collection;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;
import com.badlogic.gdx.utils.GdxRuntimeException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class Connected extends Thread implements Connection {
    protected Socket cConnection;

    protected ObjectInputStream inputStream;

    protected ObjectOutputStream outputStream;

    public Connected(String ip, int port) throws GdxRuntimeException {
        SocketHints hints = new SocketHints();
        hints.connectTimeout = 7000;
        cConnection = Gdx.net.newClientSocket(Net.Protocol.TCP, ip, port, hints);
        try {
            outputStream = new ObjectOutputStream(cConnection.getOutputStream());
            inputStream = new ObjectInputStream(cConnection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.start();
    }

    @Override
    public Connection connect(String ip, int port) {
        return this;
    }

    @Override
    public Connection disconnect() {
        try {
            outputStream.close();
            inputStream.close();
            cConnection.dispose();
        } catch (IOException e){
            e.printStackTrace();
        }
        return new Unconnected();
    }

    @Override
    public UserData requestUserData(String id) {
        try {
            outputStream.writeObject(new Request(new UserData(id)));
            outputStream.flush();
            Reply reply = (Reply) inputStream.readObject();
            return (UserData) reply.getcData();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateCollection(Collection collection, UserData userData) {
        try {
            outputStream.writeObject(new Update(new CollectionWrapper(collection, userData)));
            outputStream.flush();
            Reply reply = (Reply) inputStream.readObject();
            return reply.iscSuccess();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean updateUserData(UserData data) {
        boolean success = false;
        try {
            outputStream.writeObject(new Update(data));
            outputStream.flush();
            Reply reply = (Reply) inputStream.readObject();
            success = reply.iscSuccess();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public boolean isConnected() {
        return true;
    }
}