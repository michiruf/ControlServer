package de.michiruf.control_server.client_java.ui.pages;

import dagger.Module;

/**
 * @author Michael Ruf
 * @since 2016-03-12
 */
@Module(
        staticInjections = {
                DevicesController.class,
                DirectConnectionServerController.class,
                LoginController.class,
                OptionsController.class
        },
        library = true,
        complete = false
)
public class PagesModule {
}
