package de.michiruf.control_server.robot.controls;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael Ruf
 * @since 2015-09-28
 */
@Module(
        injects = {
                KeyControlExecutor.class,
                MouseControlExecutor.class
        },
        library = true,
        complete = false
)
public class ExecutorModule {

    @SuppressWarnings("unused")
    @Provides
    @Singleton
    public List<ControlExecutor> provideControlExecutors(KeyControlExecutor keyControlExecutor,
                                                         MouseControlExecutor mouseControlExecutor) {
        List<ControlExecutor> all = new ArrayList<>();
        all.add(keyControlExecutor);
        all.add(mouseControlExecutor);
        return all;
    }
}
