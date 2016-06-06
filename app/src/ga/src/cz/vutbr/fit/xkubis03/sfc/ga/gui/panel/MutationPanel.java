/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.gui.panel;

import cz.vutbr.fit.xkubis03.sfc.ga.gui.panel.form.MutateForm;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MutationPanel extends JPanel {

    protected JPanel mutatePanel;
    protected JScrollPane tablePane;

    public MutationPanel() {
        super();

        setLayout(new GridLayout(2, 1, 5, 5));

        mutatePanel = new MutateForm();
        tablePane = null;

        add(mutatePanel);
    }

    public void setSpinners() {
        ((MutateForm)mutatePanel).setSpinners();
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
