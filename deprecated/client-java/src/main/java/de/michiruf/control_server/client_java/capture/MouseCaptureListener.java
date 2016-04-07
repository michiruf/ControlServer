package de.michiruf.control_server.client_java.capture;

import de.michiruf.control_server.client.dispatch.EventDispatcher;
import de.michiruf.control_server.common.Direction;
import de.michiruf.control_server.common.Event;
import de.michiruf.control_server.common.data.MouseData;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.Date;

/**
 * @author Michael Ruf
 * @since 2016-03-01
 */
@Singleton
public class MouseCaptureListener extends MouseAdapter {

    private final EventDispatcher dispatcher;

    @Inject
    public MouseCaptureListener(EventDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    // This gets triggered after pressed
    //@Override
    //public void mouseClicked(MouseEvent e) {
    //    System.out.println("mouseClicked");
    //
    //    dispatcher.dispatch(new Event(
    //            Direction.DOWN,
    //            new MouseData(e.getX(), e.getY(), e.getButton(), MouseData.CoordinateType.ABSOLUTE),
    //            new Date(System.currentTimeMillis())));
    //}

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("mousePressed");

        dispatcher.dispatch(new Event(
                Direction.DOWN,
                new MouseData(e.getX(), e.getY(), e.getButton(), MouseData.CoordinateType.ABSOLUTE),
                new Date(System.currentTimeMillis())));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("mouseReleased");

        dispatcher.dispatch(new Event(
                Direction.UP,
                new MouseData(e.getX(), e.getY(), e.getButton(), MouseData.CoordinateType.ABSOLUTE),
                new Date(System.currentTimeMillis())));
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        System.out.println("mouseMoved");

        dispatcher.dispatch(new Event(
                Direction.UP,
                new MouseData(e.getX(), e.getY(), e.getButton(), MouseData.CoordinateType.ABSOLUTE),
                new Date(System.currentTimeMillis())));
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        System.out.println("mouseDragged");

        dispatcher.dispatch(new Event(
                Direction.DOWN,
                new MouseData(e.getX(), e.getY(), e.getButton(), MouseData.CoordinateType.ABSOLUTE),
                new Date(System.currentTimeMillis())));
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        System.out.println("mouseWheelMoved");

        dispatcher.dispatch(new Event(
                Direction.DOWN,
                new MouseData(e.getX(), e.getY(), e.getButton(), MouseData.CoordinateType.ABSOLUTE),
                new Date(System.currentTimeMillis())));
    }
}
