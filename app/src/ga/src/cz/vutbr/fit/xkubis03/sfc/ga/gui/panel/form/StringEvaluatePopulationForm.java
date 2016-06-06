/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.gui.panel.form;

import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.IChromosome;
import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.StringChromosome;
import cz.vutbr.fit.xkubis03.sfc.ga.gui.ChromosomePopulationTableModel;
import cz.vutbr.fit.xkubis03.sfc.ga.gui.StringSetComboBoxModel;
import cz.vutbr.fit.xkubis03.sfc.ga.gui.ToolBarActiveButton;
import cz.vutbr.fit.xkubis03.sfc.ga.gui.panel.EvaluatePopulationPanel;
import cz.vutbr.fit.xkubis03.sfc.ga.util.FitnessMethodType;
import cz.vutbr.fit.xkubis03.sfc.ga.util.Variables;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class StringEvaluatePopulationForm extends EvaluatePopulationForm implements ActionListener {

    protected JComboBox stringComboBox;
    protected ButtonGroup btnGroup;

    public StringEvaluatePopulationForm() {
        super();

        setLayout(new GridBagLayout());

        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));

        createGUI();
    }

    private void createGUI() {
        GridBagConstraints c = new GridBagConstraints();

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(1, 1));

        JLabel method = new JLabel("Hodnotící metoda");
        c.gridx = 0;
        c.gridy = 0;
        add(method, c);

        JRadioButton allSame = new JRadioButton("Všechny řetězce stejné");
        allSame.setActionCommand("allSame");
        allSame.setSelected(true);
        buttons.add(allSame);

        btnGroup = new ButtonGroup();
        btnGroup.add(allSame);

        c.gridy = 1;
        add(buttons, c);

        allSame.addActionListener(this);

        // String combobox

        JButton evaluateBtn = new JButton("Ohodnoť populaci");
        evaluateBtn.setActionCommand("evaluate");
        evaluateBtn.addActionListener(this);
        c.gridy = 3;
        add(evaluateBtn, c);

        setComboBox();
    }

    public void setComboBox() {
        if(stringComboBox != null) {
            remove(stringComboBox);
        }

        GridBagConstraints c = new GridBagConstraints();

        StringSetComboBoxModel stringSetComboBoxModel = new StringSetComboBoxModel();
        stringSetComboBoxModel.setOptions();
        stringComboBox = new JComboBox(stringSetComboBoxModel);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        add(stringComboBox, c);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "allSame": {
                stringComboBox.setEnabled(true);
                break;
            }
            case "evaluate": {
                evaluate();
                break;
            }
        }
    }

    public void evaluate() {
        switch(btnGroup.getSelection().getActionCommand()) {
            case "allSame": {
                String str = stringComboBox.getSelectedItem().toString();
                allSame(str);
                Variables.setFitnessMethodType(FitnessMethodType.ALL_SAME_STR);
                Variables.setAllSameString(str);
                break;
            }
        }

        JTable chromosomeTable = new JTable();
        chromosomeTable.setModel(new ChromosomePopulationTableModel(Variables.getPopulation()));
        chromosomeTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        Variables.resizeTable(chromosomeTable);
        JScrollPane chromosomeTablePane = new JScrollPane(chromosomeTable);

        // Show table
        ((EvaluatePopulationPanel)Variables.getActualScreen()).setTablePane(chromosomeTablePane);

        Variables.getToolBar().activateButtons(ToolBarActiveButton.EVALUATE);
        Variables.getWorkingPlace().getCecPanel().setTablePane(null);
        Variables.getWorkingPlace().getPsPanel().setTablePane(null);
        Variables.getWorkingPlace().getCoPanel().setTablePane(null);
    }

    public void allSame(String s) {
        ArrayList population = Variables.getPopulation().getPopulation();

        Iterator populationIt = population.iterator();

        while(populationIt.hasNext()) {
            StringChromosome sc = (StringChromosome)populationIt.next();

            sc.setFitness(Variables.getAllSameFitness(sc, s));
        }

        Variables.setRequiredFitness(((IChromosome)population.get(0)).getChromosome().size());
    }
}
