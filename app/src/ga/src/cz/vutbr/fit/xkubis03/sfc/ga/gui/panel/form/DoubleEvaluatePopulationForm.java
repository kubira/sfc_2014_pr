/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.gui.panel.form;

import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.DoubleChromosome;
import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.IChromosome;
import cz.vutbr.fit.xkubis03.sfc.ga.gui.ChromosomePopulationTableModel;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class DoubleEvaluatePopulationForm extends EvaluatePopulationForm implements ActionListener {

    protected ButtonGroup btnGroup;

    public DoubleEvaluatePopulationForm() {
        super();

        setLayout(new GridBagLayout());

        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));

        createGUI();
    }

    private void createGUI() {
        GridBagConstraints c = new GridBagConstraints();

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(3, 1));

        JLabel method = new JLabel("Hodnotící metoda");
        c.gridx = 0;
        c.gridy = 0;
        add(method, c);

        JRadioButton nonDown = new JRadioButton("Neklesající posloupnost");
        nonDown.setActionCommand("<=");
        nonDown.setSelected(false);
        buttons.add(nonDown);
        nonDown.setSelected(true);

        c.gridy = 1;
        add(buttons, c);

        JRadioButton nonUp = new JRadioButton("Nestoupající posloupnost");
        nonUp.setActionCommand(">=");
        nonUp.setSelected(false);
        buttons.add(nonUp);

        btnGroup = new ButtonGroup();
        btnGroup.add(nonDown);
        btnGroup.add(nonUp);

        nonDown.addActionListener(this);
        nonUp.addActionListener(this);

        JButton evaluateBtn = new JButton("Ohodnoť populaci");
        evaluateBtn.setActionCommand("evaluate");
        evaluateBtn.addActionListener(this);
        c.gridy = 2;
        add(evaluateBtn, c);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "evaluate": {
                evaluate();
                break;
            }
        }
    }

    public void evaluate() {
        switch(btnGroup.getSelection().getActionCommand()) {
            case "<=": {
                nonDown();
                Variables.setFitnessMethodType(FitnessMethodType.NON_DOWN_DOUBLE);
                break;
            }
            case ">=": {
                nonUp();
                Variables.setFitnessMethodType(FitnessMethodType.NON_UP_DOUBLE);
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

    public void nonDown() {
        ArrayList population = Variables.getPopulation().getPopulation();

        Iterator populationIt = population.iterator();

        while(populationIt.hasNext()) {
            DoubleChromosome dc = (DoubleChromosome)populationIt.next();

            dc.setFitness(Variables.getNonDownFitness(dc));
        }

        Variables.setRequiredFitness(((IChromosome)population.get(0)).getChromosome().size() - 1);
    }

    public void nonUp() {
        ArrayList population = Variables.getPopulation().getPopulation();

        Iterator populationIt = population.iterator();

        while(populationIt.hasNext()) {
            DoubleChromosome dc = (DoubleChromosome)populationIt.next();

            dc.setFitness(Variables.getNonUpFitness(dc));
        }

        Variables.setRequiredFitness(((IChromosome)population.get(0)).getChromosome().size() - 1);
    }
}
