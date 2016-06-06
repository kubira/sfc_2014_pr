/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.gui.panel;

import cz.vutbr.fit.xkubis03.sfc.ga.gui.panel.form.BinaryEvaluatePopulationForm;
import cz.vutbr.fit.xkubis03.sfc.ga.gui.panel.form.DoubleEvaluatePopulationForm;
import cz.vutbr.fit.xkubis03.sfc.ga.gui.panel.form.IntegerEvaluatePopulationForm;
import cz.vutbr.fit.xkubis03.sfc.ga.gui.panel.form.PermutationEvaluatePopulationForm;
import cz.vutbr.fit.xkubis03.sfc.ga.gui.panel.form.StringEvaluatePopulationForm;
import cz.vutbr.fit.xkubis03.sfc.ga.util.Variables;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class EvaluatePopulationPanel extends JPanel {

    protected JPanel actualForm;
    protected JScrollPane tablePane;

    public EvaluatePopulationPanel() {
        super();

        setLayout(new GridLayout(2, 1, 5, 5));

        actualForm = null;
    }

    public void setForm() {
        if(actualForm != null) {
            remove(actualForm);
            actualForm = null;
        }

        if(tablePane != null) {
            remove(tablePane);
            tablePane = null;
        }

        switch(Variables.getPopulation().getChromosomeType()) {
            case BINARY: {
                actualForm = new BinaryEvaluatePopulationForm();
                break;
            }
            case INTEGER: {
                actualForm = new IntegerEvaluatePopulationForm();
                break;
            }
            case DOUBLE: {
                actualForm = new DoubleEvaluatePopulationForm();
                break;
            }
            case STRING: {
                actualForm = new StringEvaluatePopulationForm();
                break;
            }
            case PERMUTATION: {
                actualForm = new PermutationEvaluatePopulationForm();
                break;
            }
        }

        if(actualForm != null) {
            add(actualForm);
        }
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
