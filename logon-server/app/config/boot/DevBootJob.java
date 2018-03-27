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
public class DevBootJob {

    private final ActorSystem actorSystem;
    private final ExecutionContext executionContext;

    @Inject
    public DevBootJob(Application application, ActorSystem actorSystem, ExecutionContext executionContext) {
        this.actorSystem = actorSystem;
        this.executionContext = executionContext;

        if (application.isDev()) {
            initialize();
        }
    }

    private void initialize() {
        actorSystem.scheduler().scheduleOnce(
                Duration.fromNanos(0),
                () -> {
                    Logger.info("DevBootJob starting");
                    mayCreateUsers();
                    mayCreateDevices();
                    Logger.info("DevBootJob done");
                },
                executionContext
        );
    }

    private void mayCreateUsers() {
        if (User.finder.query().findCount() > 0) {
            return;
        }

        new User("bobbi", "cs@michiruf.de", "123456").save();
    }

    @SuppressWarnings("ConstantConditions")
    private void mayCreateDevices() {
        if (Device.finder.query().findCount() > 0) {
            return;
        }

        User.finder.query().where().eq("username", "bobbi").findOneOrEmpty().ifPresent(user -> {
            Device d1 = new Device(user, "bobbi-PC");
            d1.save();
            user.devices.add(d1);
            user.save();
        });
    }
}
