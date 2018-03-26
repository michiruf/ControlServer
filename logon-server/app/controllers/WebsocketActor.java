package controllers;

import akka.actor.ActorRef;
import com.fasterxml.jackson.databind.ObjectMapper;
import config.actor.MultiTypeAbstractActor;
import de.michiruf.control_server.common.event.Event;
import de.michiruf.control_server.common.user.DeviceRequest;
import de.michiruf.control_server.common.user.LoginRequest;
import de.michiruf.control_server.common.user.LoginResult;
import de.michiruf.control_server.common.user.UnauthenticatedError;
import models.User;
import play.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author Michael Ruf
 * @since 2018-03-21
 */
public class WebsocketActor extends MultiTypeAbstractActor {

    private boolean authenticated = false;

    public WebsocketActor(ActorRef out, ObjectMapper objectMapper) {
        super(out, objectMapper);
    }

    @Override
    public Map<Class<?>, Consumer<Object>> onReceiveTypeDelegation() {
        Map<Class<?>, Consumer<Object>> doByClass = new HashMap<>();
        doByClass.put(LoginRequest.class, o -> onLogin((LoginRequest) o));
        doByClass.put(DeviceRequest.class, o -> onDeviceRequest((DeviceRequest) o));
        doByClass.put(Event.class, o -> onEvent((Event) o));
        doByClass.put(String.class, o -> onUnknownMessage((String) o));
        return doByClass;
    }

    // Killing a connection: self().tell(PoisonPill.getInstance(), self());

    @Override
    public void postStop() throws Exception {
        authenticated = false;
        super.postStop();
        Logger.info("Websocket closed");
    }

    private void checkAuthentication() {
        if (!authenticated) {
            tell(new UnauthenticatedError());
            throw new IllegalStateException("Not authenticated!");
            // TODO Do this better?
        }
    }

    private void onLogin(LoginRequest loginRequest) {
        authenticated = User.finder.query()
                .where()
                .eq("username", loginRequest.getUsername())
                .and()
                .eq("password", loginRequest.getEncryptedPassword())
                .findCount() > 0;
        tell(new LoginResult(authenticated));
    }

    private void onDeviceRequest(DeviceRequest deviceRequest) {
        checkAuthentication();

        // TODO
    }

    private void onEvent(Event event) {
    }

    private void onUnknownMessage(String message) {
        tell(String.format("Could not interpret the message: %s", message));
    }
}
