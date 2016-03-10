package job;

import models.ControlEventStream;
import play.jobs.Every;
import play.jobs.Job;

/**
 * @author Michael Ruf
 * @since 2016-03-10
 */
@SuppressWarnings("unused")
@Every("1h")
public class ControlEventStreamCleanupJob extends Job {

    public static final int TIMEOUT = 100000; // TODO

    @Override
    public void doJob() throws Exception {
        super.doJob();
        ControlEventStream.cleanup(TIMEOUT);
    }
}
