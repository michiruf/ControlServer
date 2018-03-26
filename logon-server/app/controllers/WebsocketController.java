package controllers;

import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.stream.Materializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import play.Logger;
import play.libs.streams.ActorFlow;
import play.mvc.Controller;
import play.mvc.WebSocket;

import javax.inject.Inject;

/**
 * @author Michael Ruf
 * @since 2018-03-21
 */
public class WebsocketController extends Controller {

    private final ActorSystem actorSystem;
    private final Materializer materializer;

    @Inject
    public WebsocketController(ActorSystem actorSystem, Materializer materializer) {
        this.actorSystem = actorSystem;
        this.materializer = materializer;
    }

    public WebSocket socket() {
        Logger.info("Websocket connection request");
        // Note this must be text because of the own type switching implementation
        return WebSocket.Text.accept(request ->
                ActorFlow.actorRef(
                        actorRef -> Props.create(WebsocketActor.class, actorRef, new ObjectMapper()),
                        actorSystem,
                        materializer));
    }
}
