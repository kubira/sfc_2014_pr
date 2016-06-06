/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.gui.panel.form;

import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.IChromosome;
import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.IntegerChromosome;
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
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;

public class IntegerEvaluatePopulationForm extends EvaluatePopulationForm implements ActionListener {

    protected JSpinner numberSpinner;
    protected ButtonGroup btnGroup;

    public IntegerEvaluatePopulationForm() {
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

        c.gridy = 1;
        add(buttons, c);

        JRadioButton nonUp = new JRadioButton("Nestoupající posloupnost");
        nonUp.setActionCommand(">=");
        nonUp.setSelected(false);
        buttons.add(nonUp);

        JRadioButton allSame = new JRadioButton("Všechna čísla stejná");
        allSame.setActionCommand("allSame");
        allSame.setSelected(true);
        buttons.add(allSame);

        btnGroup = new ButtonGroup();
        btnGroup.add(allSame);
        btnGroup.add(nonDown);
        btnGroup.add(nonUp);

        allSame.addActionListener(this);
        nonDown.addActionListener(this);
        nonUp.addActionListener(this);

        int min = ((IntegerChromosome)Variables.getPopulation().getPopulation().get(0)).getMin();
        int max = ((IntegerChromosome)Variables.getPopulation().getPopulation().get(0)).getMax();

        numberSpinner = new JSpinner();
        SpinnerNumberModel binNumModel = new SpinnerNumberModel(min, min, max, 1);
        numberSpinner.setModel(binNumModel);
        c.gridy = 2;
        add(numberSpinner, c);

        JButton evaluateBtn = new JButton("Ohodnoť populaci");
        evaluateBtn.setActionCommand("evaluate");
        evaluateBtn.addActionListener(this);
        c.gridy = 3;
        add(evaluateBtn, c);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "allSame": {
                numberSpinner.setEnabled(true);
                break;
            }
            case "<=": {
                numberSpinner.setEnabled(false);
                break;
            }
            case ">=": {
                numberSpinner.setEnabled(false);
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
                int number = (int)numberSpinner.getValue();
                allSame(number);
                Variables.setFitnessMethodType(FitnessMethodType.ALL_SAME_INT);
                Variables.setAllSameInt(number);
                break;
            }
            case "<=": {
                nonDown();
                Variables.setFitnessMethodType(FitnessMethodType.NON_DOWN_INT);
                break;
            }
            case ">=": {
                nonUp();
                Variables.setFitnessMethodType(FitnessMethodType.NON_UP_INT);
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

    public void allSame(int num) {
        ArrayList population = Variables.getPopulation().getPopulation();

        Iterator populationIt = population.iterator();

        while(populationIt.hasNext()) {
            IntegerChromosome bc = (IntegerChromosome)populationIt.next();

            bc.setFitness(Variables.getAllSameFitness(bc, num));
        }

        Variables.setRequiredFitness(((IChromosome)population.get(0)).getChromosome().size());
    }

    public void nonDown() {
        ArrayList population = Variables.getPopulation().getPopulation();

        Iterator populationIt = population.iterator();

        while(populationIt.hasNext()) {
            IntegerChromosome bc = (IntegerChromosome)populationIt.next();

            bc.setFitness(Variables.getNonDownFitness(bc));
        }

        Variables.setRequiredFitness(((IChromosome)population.get(0)).getChromosome().size() - 1);
    }

    public void nonUp() {
        ArrayList population = Variables.getPopulation().getPopulation();

        Iterator populationIt = population.iterator();

        while(populationIt.hasNext()) {
            IntegerChromosome bc = (IntegerChromosome)populationIt.next();

            bc.setFitness(Variables.getNonUpFitness(bc));
        }

        Variables.setRequiredFitness(((IChromosome)population.get(0)).getChromosome().size() - 1);
    }
}
