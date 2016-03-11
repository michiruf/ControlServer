package helpers;

import play.libs.F;

/**
 * @author Michael Ruf
 * @since 2016-03-10
 */
public class MyArchivedEventStream<T> extends F.ArchivedEventStream<T> {

    private long lastPublishTimestamp;

    public MyArchivedEventStream(int archiveSize) {
        super(archiveSize);
    }

    @Override
    public synchronized void publish(T event) {
        super.publish(event);
        lastPublishTimestamp = System.currentTimeMillis();
    }

    public long getLastPublishTimestamp() {
        return lastPublishTimestamp;
    }
}
