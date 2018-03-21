package controllers;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import de.michiruf.control_server.common.auth.Authentication;
import play.Logger;

/**
 * @author Michael Ruf
 * @since 2018-03-21
 */
public class WebsocketActor extends AbstractActor {

    private final ActorRef out;

    public WebsocketActor(ActorRef out) {
        this.out = out;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Authentication.class, auth ->
                        out.tell("Password you sent was " + auth.getEncryptedPassword(), self())
                )
                .match(String.class, message ->
                        out.tell("I received your message: " + message, self())
                )
                .build();
    }

    // Killing a connection: self().tell(PoisonPill.getInstance(), self());

    @Override
    public void postStop() throws Exception {
        super.postStop();
        Logger.error("Websocket closed");
    }
}
