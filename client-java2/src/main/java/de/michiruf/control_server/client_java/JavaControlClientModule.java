package de.michiruf.control_server.client_java;

import dagger.Module;
import dagger.Provides;
import de.michiruf.control_server.client.ControlClientModule;
import de.michiruf.control_server.client_java.capture.CaptureModule;
import de.michiruf.control_server.client_java.config.JavaClientConfigurationModule;
import de.michiruf.control_server.client_java.ui.UiModule;

import javax.inject.Named;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * @author Michael Ruf
 * @since 2016-03-01
 */
@Module(
        includes = {
                ControlClientModule.class,
                CaptureModule.class,
                JavaClientConfigurationModule.class,
                UiModule.class
        },
        // TODO why is this necessary?
        complete = false,
        library = true // TODO remove (due to computer id)
)
public class JavaControlClientModule {

    @SuppressWarnings("unused")
    @Provides
    @Named("deviceId")
    public String provideDeviceId() {
        try {
            NetworkInterface networkInterface = NetworkInterface.getNetworkInterfaces().nextElement();
            return Arrays.toString(networkInterface.getHardwareAddress());
        } catch (SocketException | NoSuchElementException e) {
            e.printStackTrace(); // TODO Error
        }

        return null;
    }
}
