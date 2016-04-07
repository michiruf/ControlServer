package de.michiruf.control_server.client_java;

import dagger.ObjectGraph;
import de.michiruf.control_server.client_java.ui.TrayControl;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Michael Ruf
 * @since 2016-03-08
 */
public class JavaControlClientFX extends Application {

    public static void main(String args[]) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ObjectGraph appGraph = ObjectGraph.create(new JavaControlClientFXModule(primaryStage));
        TrayControl trayControl = appGraph.get(TrayControl.class);
        trayControl.show();
    }
}
