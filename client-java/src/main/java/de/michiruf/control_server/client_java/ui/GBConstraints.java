package de.michiruf.control_server.client_java.ui;

import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * @author Michael Ruf
 * @since 2016-03-08
 */
public class GBConstraints extends GridBagConstraints {

    public void gridX(int gridX) {
        this.gridx = gridX;
    }

    public void gridY(int gridY) {
        this.gridy = gridY;
    }

    public void gridWidth(int gridWidth) {
        this.gridwidth = gridWidth;
    }

    public void gridHeight(int gridHeight) {
        this.gridheight = gridHeight;
    }

    public void weightX(double weightX) {
        this.weightx = weightX;
    }

    public void weightY(double weightY) {
        this.weighty = weightY;
    }

    public void anchor(int anchor) {
        this.anchor = anchor;
    }

    public void fill(int fill) {
        this.fill = fill;
    }

    public void insets(Insets insets) {
        this.insets = insets;
    }

    public void iPadX(int ipadx) {
        this.ipadx = ipadx;
    }

    public void iPadY(int ipady) {
        this.ipady = ipady;
    }
}
