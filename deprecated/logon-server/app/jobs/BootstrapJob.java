package jobs;

import models.User;
import play.Logger;
import play.Play;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;

/**
 * @author Michael Ruf
 * @since 2016-03-10
 */
@SuppressWarnings("unused")
@OnApplicationStart
public class BootstrapJob extends Job {

    @Override
    public void doJob() throws Exception {
        super.doJob();
        if (Play.mode == Play.Mode.DEV) {
            Logger.info("Bootstrap job executing");
            Fixtures.deleteDatabase();
            createUsers();
            Logger.info("Bootstrap job executed");
        }
    }

    private void createUsers() {
        new User("bob", "bob").save();
        new User("tom", "tom").save();
    }
}
