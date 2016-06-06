/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class MenuBar extends JMenuBar implements ActionListener {

    public MenuBar() {
        super();
        JMenu menu = new JMenu("Nápověda");

        add(menu);

        JMenuItem about = new JMenuItem("O aplikaci");
        about.setActionCommand("about");
        about.addActionListener(this);

        menu.add(about);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "about": {
                JOptionPane.showMessageDialog(null,
                        "<html><center>"
                        + "Projekt do předmětu SFC"
                        + "<br>"
                        + "Demonstrace jednotlivých kroků GA"
                        + "<br>"
                        + "Radim Kubiš, xkubis03 &copy; 2014"
                        + "<center></html>",
                        "O aplikaci",
                        JOptionPane.INFORMATION_MESSAGE
                );
                break;
            }
        }
    }

}
