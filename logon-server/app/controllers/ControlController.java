package controllers;

import models.ControlEventStream;
import models.Device;
import play.libs.F.Either;
import play.libs.F.Promise;
import play.mvc.Http.WebSocketClose;
import play.mvc.Http.WebSocketEvent;
import play.mvc.WebSocketController;

import static play.libs.F.Matcher.ClassOf;
import static play.mvc.Http.WebSocketEvent.SocketClosed;
import static play.mvc.Http.WebSocketEvent.TextFrame;

/**
 * @author Michael Ruf
 * @since 2016-03-09
 */
@SuppressWarnings("unused")
public class ControlController extends WebSocketController {

    public static void webservice(String deviceId, String accessToken) {
        Device device = Device.find("byDeviceId", deviceId).first();
        if (device == null || !device.proveAccessToken(accessToken)) {
            if (inbound.isOpen()) {
                disconnect();
            }
            return;
        }

        ControlEventStream stream = ControlEventStream.forDevice(device);

        while (inbound.isOpen()) {
            Either<WebSocketEvent, String> e = await(Promise.waitEither(
                    inbound.nextEvent(),
                    stream.nextEvent()));

            // Case: TextEvent received on the socket
            for (String message : TextFrame.match(e._1)) {
                stream.forward(message);
            }

            // Case: Event received in stream
            for (String message : ClassOf(String.class).match(e._2)) {
                outbound.send(message);
            }

            // Case: The socket has been closed
            for (WebSocketClose closed : SocketClosed.match(e._1)) {
                disconnect();
            }
        }
    }
}
