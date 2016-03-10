package job;

import models.Device;
import play.jobs.Every;
import play.jobs.Job;

import java.util.Date;

/**
 * @author Michael Ruf
 * @since 2016-03-10
 */
@SuppressWarnings("unused")
@Every("1h")
public class DeviceCleanupJob extends Job {

    public static final int TIMEOUT = 1209600000; // 14d

    @Override
    public void doJob() throws Exception {
        super.doJob();
        Device.findAll().stream().map(jpaBase -> (Device) jpaBase).forEach(device -> {
            if (device.getLastActive().before(new Date(System.currentTimeMillis() - TIMEOUT))) {
                device.delete();
            }
        });
    }
}
