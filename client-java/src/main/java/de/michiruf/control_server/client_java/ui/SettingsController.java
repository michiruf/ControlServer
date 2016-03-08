package de.michiruf.control_server.client_java.ui;

import de.michiruf.control_server.client_java.config.JavaClientConfiguration;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Michael Ruf
 * @since 2016-03-01
 */
@Singleton
public class SettingsController {

    public static SettingsController INSTANCE;

    private JavaClientConfiguration configuration;

    public SettingsController() {
    }

    @Inject
    public SettingsController(JavaClientConfiguration configuration) {
        INSTANCE = this;
        this.configuration = configuration;

//        setSize(300, 500);
//        int x = Toolkit.getDefaultToolkit().getScreenSize().width / 2 - getSize().width / 2;
//        int y = Toolkit.getDefaultToolkit().getScreenSize().height / 2 - getSize().height / 2;
    }
}
