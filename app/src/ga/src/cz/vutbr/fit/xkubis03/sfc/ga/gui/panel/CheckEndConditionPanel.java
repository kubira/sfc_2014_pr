/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.gui.panel;

import cz.vutbr.fit.xkubis03.sfc.ga.gui.panel.form.CheckEndConditionForm;
import cz.vutbr.fit.xkubis03.sfc.ga.util.Variables;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class CheckEndConditionPanel extends JPanel {

    protected CheckEndConditionForm cecForm;
    protected JScrollPane tablePane;

    public CheckEndConditionPanel() {
        super();

        setLayout(new GridLayout(2, 1, 5, 5));

        cecForm = new CheckEndConditionForm();

        add(cecForm);
    }

    public void revalidateFitness() {
        cecForm.setRequiredFitness(Variables.getRequiredFitness());
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
