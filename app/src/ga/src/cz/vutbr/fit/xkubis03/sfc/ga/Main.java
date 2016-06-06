/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga;

import cz.vutbr.fit.xkubis03.sfc.ga.gui.MenuBar;
import cz.vutbr.fit.xkubis03.sfc.ga.gui.StatusBar;
import cz.vutbr.fit.xkubis03.sfc.ga.gui.ToolBar;
import cz.vutbr.fit.xkubis03.sfc.ga.gui.WorkingPlace;
import cz.vutbr.fit.xkubis03.sfc.ga.util.Variables;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import javax.swing.JFrame;

public class Main {

    protected static ToolBar tb;
    protected static WorkingPlace wp;
    protected static StatusBar statusBar;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Demonstrace jednotlivých kroků GA");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        frame.setMaximizedBounds(env.getMaximumWindowBounds());
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);

        frame.setJMenuBar(new MenuBar());

        tb = new ToolBar();
        Variables.setToolBar(tb);
        frame.add(tb, BorderLayout.WEST);
        tb.setPreferredSize(new Dimension(150, 100));

        wp = new WorkingPlace(tb);
        Variables.setWorkingPlace(wp);

        frame.add(wp);

        statusBar = new StatusBar(frame.getWidth());
        frame.add(statusBar, BorderLayout.SOUTH);

        frame.pack();

        frame.setVisible(true);
    }
}
