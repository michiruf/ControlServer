package controllers;

import akka.actor.ActorRef;
import com.fasterxml.jackson.databind.ObjectMapper;
import config.actor.MultiTypeAbstractActor;
import de.michiruf.control_server.common.control.StartControlRequest;
import de.michiruf.control_server.common.event.Event;
import de.michiruf.control_server.common.user.DeviceRequest;
import de.michiruf.control_server.common.user.DeviceResult;
import de.michiruf.control_server.common.user.LoginRequest;
import de.michiruf.control_server.common.user.LoginResult;
import de.michiruf.control_server.common.user.UnauthenticatedError;
import models.Device;
import models.User;
import play.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author Michael Ruf
 * @since 2018-03-21
 */
public class WebsocketActor extends MultiTypeAbstractActor {

    private static Map<Device, WebsocketActor> actors = new HashMap<>();

    private User user;

    public WebsocketActor(ActorRef out, ObjectMapper objectMapper) {
        super(out, objectMapper);
    }

    @Override
    public Map<Class<?>, Consumer<Object>> onReceiveTypeDelegation() {
        Map<Class<?>, Consumer<Object>> delegateMap = new HashMap<>();
        delegateMap.put(LoginRequest.class, o -> onLogin((LoginRequest) o));
        delegateMap.put(DeviceRequest.class, o -> onDeviceRequest((DeviceRequest) o));
        delegateMap.put(Event.class, o -> onEvent((Event) o));
        delegateMap.put(String.class, o -> onUnknownMessage((String) o));
        return delegateMap;
    }

    // Killing a connection: self().tell(PoisonPill.getInstance(), self());

    @Override
    public void postStop() throws Exception {
        super.postStop();
        user = null;
        Logger.info("Websocket closed");
    }

    private void checkAuthentication() {
        if (user == null) {
            tell(new UnauthenticatedError());
            throw new IllegalStateException("Not authenticated!");
            // TODO Do this better?
        }
    }

    private void onLogin(LoginRequest loginRequest) {
        user = User.finder.query()
                .where()
                .eq("username", loginRequest.getUsername())
                .and()
                .eq("password", loginRequest.getEncryptedPassword())
                .findOne();
        tell(new LoginResult(user != null));
    }

    private void onDeviceRequest(DeviceRequest deviceRequest) {
        checkAuthentication();

        Device device = Device.fromCommon(deviceRequest.getOwnDevice());
        if (device == null) {
            tell(new DeviceResult());
        }

        actors.put(device, this);

        tell(new DeviceResult(user.devices.stream()
                .map(Device::toCommon)
                .collect(Collectors.toList())));
    }

    private void onStartControlRequest(StartControlRequest startControlRequest) {
//        startControlRequest.getDevice();
    }

    private void onEvent(Event event) {
    }

    private void onUnknownMessage(String message) {
        tell(String.format("Could not interpret the message: %s", message));
    }
}
