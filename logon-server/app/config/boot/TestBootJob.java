package config.boot;

import akka.actor.ActorSystem;
import models.Device;
import models.User;
import play.Application;
import play.Logger;
import scala.concurrent.ExecutionContext;
import scala.concurrent.duration.Duration;

import javax.inject.Inject;

/**
 * @author Michael Ruf
 * @since 2018-03-06
 */
public class TestBootJob {

    private final ActorSystem actorSystem;
    private final ExecutionContext executionContext;

    @Inject
    public TestBootJob(Application application, ActorSystem actorSystem, ExecutionContext executionContext) {
        this.actorSystem = actorSystem;
        this.executionContext = executionContext;

        if (application.isTest()) {
            initialize();
        }
    }

    private void initialize() {
        actorSystem.scheduler().scheduleOnce(
                Duration.fromNanos(0),
                () -> {
                    Logger.info("TestBootJob starting");
                    mayCreateUsers();
                    mayCreateDevices();
                    Logger.info("TestBootJob done");
                },
                executionContext
        );
    }

    private void mayCreateUsers() {
        if (User.finder.query().findCount() > 0) {
            return;
        }

        new User("testuser", "testuser@example.com", "testpassword").save();
    }

    @SuppressWarnings("ConstantConditions")
    private void mayCreateDevices() {
        if (Device.finder.query().findCount() > 0) {
            return;
        }

        User.finder.query().where().eq("username", "testuser").findOneOrEmpty().ifPresent(user -> {
            Device d1 = new Device(user, "testuser-1-device-1");
            d1.save();
            user.devices.add(d1);
            user.save();
        });
    }
}
