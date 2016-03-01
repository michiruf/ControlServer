package de.michiruf.control_server.client_java.ui;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Michael Ruf
 * @since 2016-03-01
 */
@Singleton
public class TrayMenu {

    @Inject
    public TrayMenu() {
    }

    public void show() {
        System.out.println("Yolo");
    }
}
