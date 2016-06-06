/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.gui.panel.form;

import cz.vutbr.fit.xkubis03.sfc.ga.gui.CrossoverTypeComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class CrossoverForm extends JPanel {

    protected JComboBox crossoverTypeComboBox;

    public CrossoverForm() {
        super();
    }

    public void setOptions() {
        ((CrossoverTypeComboBoxModel)crossoverTypeComboBox.getModel()).setOptions();
    }
}
