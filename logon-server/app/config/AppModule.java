package config;

import com.google.inject.AbstractModule;
import config.boot.DevBootJob;
import config.boot.TestBootJob;

/**
 * @author Michael Ruf
 * @since 2018-03-19
 */
public class AppModule extends AbstractModule {

    @Override
    protected void configure() {
        // @see https://www.playframework.com/documentation/2.6.x/ScheduledTasks#starting-tasks-when-your-app-starts
        // @see https://stackoverflow.com/questions/46062618/playframework-2-6-x-execute-application-startup-code?rq=1
        bind(TestBootJob.class).asEagerSingleton();
        bind(DevBootJob.class).asEagerSingleton();
    }
}
