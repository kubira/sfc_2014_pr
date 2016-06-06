/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.gui.panel;

import cz.vutbr.fit.xkubis03.sfc.ga.gui.panel.form.NextPopulationForm;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class NextPopulationPanel extends JPanel {

    protected JScrollPane tablePane;

    public NextPopulationPanel() {
        super();

        setLayout(new GridLayout(2, 1, 5, 5));

        tablePane = null;

        add(new NextPopulationForm());
    }

    public void setTablePane(JScrollPane tablePane) {
        if(this.tablePane != null) {
            remove(this.tablePane);
        }

        this.tablePane = tablePane;

        if(this.tablePane != null) {
            add(this.tablePane);
            revalidate();
            repaint();
        }
    }
}
