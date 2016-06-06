/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.gui;

import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public class StatusBar extends JPanel {

    protected JLabel status;

    public StatusBar(int width) {
        super();
        setBorder(new BevelBorder(BevelBorder.LOWERED));
        setPreferredSize(new Dimension(width, 16));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        status = new JLabel("StatusBar");
        status.setHorizontalAlignment(SwingConstants.LEFT);
        add(status);
    }

    public void setStatus(String status) {
        this.status.setText(status);
    }
}
