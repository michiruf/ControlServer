package de.michiruf.control_server.client_java.ui;

import dagger.Module;

/**
 * @author Michael Ruf
 * @since 2016-03-01
 */
@Module(
        injects = {
                TrayMenu.class
        },
        library = true
)
public class UiModule {
}
