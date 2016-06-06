/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.gui.panel;

import cz.vutbr.fit.xkubis03.sfc.ga.gui.ChromosomePopulationTableModel;
import cz.vutbr.fit.xkubis03.sfc.ga.gui.ToolBarActiveButton;
import cz.vutbr.fit.xkubis03.sfc.ga.gui.panel.form.InitialPopulationForm;
import cz.vutbr.fit.xkubis03.sfc.ga.population.ChromosomePopulation;
import cz.vutbr.fit.xkubis03.sfc.ga.util.Variables;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class InitialPopulationPanel extends JPanel {

    protected JTable chromosomeTable;
    protected JScrollPane chromosomeTablePane;

    public InitialPopulationPanel() {
        super();

        chromosomeTablePane = null;

        setLayout(new GridLayout(2, 1, 5, 5));

        add(new InitialPopulationForm());
    }

    public void generateTable(ChromosomePopulation cp) {
        if(chromosomeTablePane != null) {
            remove(chromosomeTablePane);
            chromosomeTablePane = null;
        }

        if(cp != null) {
            chromosomeTable = new JTable();
            Variables.setPopulation(cp);
            chromosomeTable.setModel(new ChromosomePopulationTableModel(Variables.getPopulation()));
            chromosomeTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            Variables.resizeTable(chromosomeTable);
            chromosomeTablePane = new JScrollPane(chromosomeTable);
            add(chromosomeTablePane);
            Variables.getToolBar().activateButtons(ToolBarActiveButton.GENERATE);
            Variables.getWorkingPlace().getEpPanel().setForm();
            Variables.getWorkingPlace().getCoPanel().setForm();
        }
    }
}
