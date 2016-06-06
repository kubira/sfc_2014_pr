/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.gui.panel;

import cz.vutbr.fit.xkubis03.sfc.ga.gui.panel.form.ExamplesForm;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ExamplesPanel extends JPanel {

    protected JScrollPane tablePane;

    public ExamplesPanel() {
        super();

        setLayout(new GridLayout(2, 1, 5, 5));

        add(new ExamplesForm());
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
