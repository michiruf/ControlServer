package controllers;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import de.michiruf.control_server.common.event.Event;
import de.michiruf.control_server.common.user.DeviceRequest;
import de.michiruf.control_server.common.user.LoginRequest;
import de.michiruf.control_server.common.user.LoginResult;
import de.michiruf.control_server.common.user.UnauthenticatedError;
import models.User;
import play.Logger;

/**
 * @author Michael Ruf
 * @since 2018-03-21
 */
public class WebsocketActor extends AbstractActor {

    private final ActorRef out;

    private boolean authenticated = false;

    public WebsocketActor(ActorRef out) {
        this.out = out;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(LoginRequest.class, this::onLogin)
                .match(DeviceRequest.class, this::onDeviceRequest)
                .match(Event.class, this::onEvent)
                .match(String.class, this::onUnknownMessage)
                .build();
    }

    // Killing a connection: self().tell(PoisonPill.getInstance(), self());

    @Override
    public void postStop() throws Exception {
        authenticated = false;
        super.postStop();
        Logger.error("Websocket closed");
    }

    private void checkAuthentication() {
        if (!authenticated) {
            out.tell(new UnauthenticatedError(), self());
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
        out.tell(new LoginResult(authenticated), self());
    }

    private void onDeviceRequest(DeviceRequest deviceRequest) {
        checkAuthentication();

        // TODO
    }

    private void onEvent(Event event) {
    }

    private void onUnknownMessage(String message) {
        out.tell(String.format("Could not interpret the message: %s", message), self());
    }
}
