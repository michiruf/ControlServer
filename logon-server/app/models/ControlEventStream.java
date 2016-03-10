package models;

import helper.MyArchivedEventStream;
import play.libs.F;

import java.util.HashMap;

/**
 * @author Michael Ruf
 * @since 2016-03-10
 */
public class ControlEventStream {

    private static HashMap<Device, ControlEventStream> instances = new HashMap<>();

    public static ControlEventStream forDevice(Device device) {
        if (!instances.containsKey(device)) {
            instances.put(device, new ControlEventStream());
        }
        return instances.get(device);
    }

    public static void cleanup(int timeout) {
        instances.forEach((device, controlEventStream) -> {
            long lastPublish = controlEventStream.stream.getLastPublishTimestamp();
            if (lastPublish != 0 && lastPublish + timeout < System.currentTimeMillis()) {
                instances.remove(device);
            }
        });
    }


    private MyArchivedEventStream<String> stream = new MyArchivedEventStream<>(100);

    private ControlEventStream() {
    }

    public F.Promise<String> nextEvent() {
        return stream.eventStream().nextEvent();
    }

    public void forward(String message) {
        stream.publish(message);
    }
}
