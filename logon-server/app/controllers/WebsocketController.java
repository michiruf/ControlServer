package controllers;

import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.stream.Materializer;
import play.Logger;
import play.libs.F;
import play.libs.streams.ActorFlow;
import play.mvc.Controller;
import play.mvc.WebSocket;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;

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
        Logger.error("Websocket connection request");
        return WebSocket.Text.acceptOrResult(request -> {
            // TODO Add auth
//            if (session().get("user") == null) {
//                Logger.error("Websocket connection rejected: unauthorized");
//                return CompletableFuture.completedFuture(F.Either.Left(forbidden()));
//            }

            // Create the websocket actor for this connection request
            Logger.error("Websocket connection accepted");
            return CompletableFuture.completedFuture(F.Either.Right(
                    ActorFlow.actorRef(
                            actorRef -> Props.create(WebsocketActor.class, actorRef),
                            actorSystem,
                            materializer)));
        });
    }
}
