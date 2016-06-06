/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.gui.panel;

import cz.vutbr.fit.xkubis03.sfc.ga.gui.panel.form.BinaryCrossoverForm;
import cz.vutbr.fit.xkubis03.sfc.ga.gui.panel.form.CrossoverForm;
import cz.vutbr.fit.xkubis03.sfc.ga.gui.panel.form.DoubleCrossoverForm;
import cz.vutbr.fit.xkubis03.sfc.ga.gui.panel.form.IntegerCrossoverForm;
import cz.vutbr.fit.xkubis03.sfc.ga.gui.panel.form.PermutationCrossoverForm;
import cz.vutbr.fit.xkubis03.sfc.ga.gui.panel.form.StringCrossoverForm;
import cz.vutbr.fit.xkubis03.sfc.ga.util.Variables;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class CrossoverPanel extends JPanel {

    protected CrossoverForm actualForm;
    protected JScrollPane tablePane;

    public CrossoverPanel() {
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
                actualForm = new BinaryCrossoverForm();
                ((BinaryCrossoverForm)actualForm).setSpinners();
                break;
            }
            case INTEGER: {
                actualForm = new IntegerCrossoverForm();
                ((IntegerCrossoverForm)actualForm).setSpinners();
                break;
            }
            case DOUBLE: {
                actualForm = new DoubleCrossoverForm();
                ((DoubleCrossoverForm)actualForm).setSpinners();
                break;
            }
            case STRING: {
                actualForm = new StringCrossoverForm();
                ((StringCrossoverForm)actualForm).setSpinners();
                break;
            }
            case PERMUTATION: {
                actualForm = new PermutationCrossoverForm();
                break;
            }
        }

        if(actualForm != null) {
            add(actualForm);
        }
    }

    public void setOptions() {
        if(actualForm != null) {
            actualForm.setOptions();
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
