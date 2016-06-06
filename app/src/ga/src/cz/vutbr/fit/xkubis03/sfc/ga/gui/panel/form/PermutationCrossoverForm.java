/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.gui.panel.form;

import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.PermutationChromosome;
import cz.vutbr.fit.xkubis03.sfc.ga.crossover.ChromosomeCrossover;
import cz.vutbr.fit.xkubis03.sfc.ga.gui.CrossoverTypeComboBoxModel;
import cz.vutbr.fit.xkubis03.sfc.ga.gui.CrossoverTypeComboBoxObject;
import cz.vutbr.fit.xkubis03.sfc.ga.gui.OffspringTableModel;
import cz.vutbr.fit.xkubis03.sfc.ga.gui.ToolBarActiveButton;
import cz.vutbr.fit.xkubis03.sfc.ga.util.Variables;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PermutationCrossoverForm extends CrossoverForm implements ActionListener {

    public PermutationCrossoverForm() {
        super();

        setLayout(new GridBagLayout());

        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));

        createGUI();
    }

    private void createGUI() {
        GridBagConstraints c = new GridBagConstraints();

        JLabel typeLabel = new JLabel("Typ křížení:");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(2, 2, 2, 2);
        add(typeLabel, c);

        CrossoverTypeComboBoxModel crossoverTypeComboBoxmodel = new CrossoverTypeComboBoxModel();
        this.crossoverTypeComboBox = new JComboBox(crossoverTypeComboBoxmodel);
        crossoverTypeComboBox.setActionCommand("combobox");
        crossoverTypeComboBox.addActionListener(this);
        c.gridx = 1;
        add(crossoverTypeComboBox, c);

        JButton btn = new JButton("Proveď křížení");
        btn.setActionCommand("cross");
        btn.addActionListener(this);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        add(btn, c);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "combobox": {
                break;
            }
            case "cross": {
                ChromosomeCrossover cCrossover = new ChromosomeCrossover();
                ArrayList offspring = new ArrayList();

                CrossoverTypeComboBoxObject item = (CrossoverTypeComboBoxObject)crossoverTypeComboBox.getSelectedItem();
                switch(item.getCoType()) {
                    case INDEX_TABLE: {
                        Iterator it = Variables.getParentSelection().iterator();

                        while(it.hasNext()) {
                            PermutationChromosome i1 = (PermutationChromosome)it.next();
                            PermutationChromosome i2 = (PermutationChromosome)it.next();

                            offspring.addAll(cCrossover.indexTableCrossover(i1, i2));
                        }
                        break;
                    }
                    case PMX: {
                        Iterator it = Variables.getParentSelection().iterator();

                        while(it.hasNext()) {
                            PermutationChromosome i1 = (PermutationChromosome)it.next();
                            PermutationChromosome i2 = (PermutationChromosome)it.next();

                            offspring.addAll(cCrossover.pmxCrossover(i1, i2));
                        }
                        break;
                    }
                }

                Variables.setOffspring(offspring);

                JTable offspringTable = new JTable(new OffspringTableModel(Variables.getOffspring()));
                offspringTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                Variables.resizeTable(offspringTable);
                JScrollPane chromosomeTablePane = new JScrollPane(offspringTable);

                Variables.getWorkingPlace().getCoPanel().setTablePane(chromosomeTablePane);
                Variables.getWorkingPlace().getMPanel().setTablePane(null);

                Variables.getToolBar().activateButtons(ToolBarActiveButton.CROSSOVER);
                break;
            }
        }
    }
}
