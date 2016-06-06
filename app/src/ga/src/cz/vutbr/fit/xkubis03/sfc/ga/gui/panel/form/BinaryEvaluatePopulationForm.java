/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.gui.panel.form;

import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.BinaryChromosome;
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
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;

public class BinaryEvaluatePopulationForm extends EvaluatePopulationForm implements ActionListener {

    protected JSpinner numberSpinner;
    protected ButtonGroup btnGroup;

    public BinaryEvaluatePopulationForm() {
        super();

        setLayout(new GridBagLayout());

        //setBackground(Color.blue);

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

        JRadioButton oneMax = new JRadioButton("Samé jedničky");
        oneMax.setActionCommand("onemax");
        oneMax.setSelected(true);
        buttons.add(oneMax);

        JRadioButton zeroMax = new JRadioButton("Samé nuly");
        zeroMax.setActionCommand("zeromax");
        zeroMax.setSelected(false);
        buttons.add(zeroMax);

        c.gridy = 1;
        add(buttons, c);

        JRadioButton binNum = new JRadioButton("Binární číslo");
        binNum.setActionCommand("binnum");
        binNum.setSelected(false);
        buttons.add(binNum);

        btnGroup = new ButtonGroup();
        btnGroup.add(oneMax);
        btnGroup.add(zeroMax);
        btnGroup.add(binNum);

        oneMax.addActionListener(this);
        zeroMax.addActionListener(this);
        binNum.addActionListener(this);

        int chromosomeLength = ((IChromosome)Variables.getPopulation().getPopulation().get(0)).getChromosome().size();
        int max = (int)Math.pow(2, chromosomeLength);
        max -= 1;

        numberSpinner = new JSpinner();
        SpinnerNumberModel binNumModel = new SpinnerNumberModel(0, 0, max, 1);
        numberSpinner.setModel(binNumModel);
        c.gridy = 2;
        add(numberSpinner, c);
        numberSpinner.setEnabled(false);

        JButton evaluateBtn = new JButton("Ohodnoť populaci");
        evaluateBtn.setActionCommand("evaluate");
        evaluateBtn.addActionListener(this);
        c.gridy = 3;
        add(evaluateBtn, c);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "onemax": {
                numberSpinner.setEnabled(false);
                break;
            }
            case "zeromax": {
                numberSpinner.setEnabled(false);
                break;
            }
            case "binnum": {
                numberSpinner.setEnabled(true);
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
            case "onemax": {
                oneMax();
                Variables.setFitnessMethodType(FitnessMethodType.ONE_MAX);
                break;
            }
            case "zeromax": {
                zeroMax();
                Variables.setFitnessMethodType(FitnessMethodType.ZERO_MAX);
                break;
            }
            case "binnum": {
                int bn = (int)numberSpinner.getValue();
                binNum(bn);
                Variables.setFitnessMethodType(FitnessMethodType.BIN_NUM);
                Variables.setBinNum(bn);
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

    public void oneMax() {
        ArrayList population = Variables.getPopulation().getPopulation();

        Iterator populationIt = population.iterator();

        while(populationIt.hasNext()) {
            BinaryChromosome bc = (BinaryChromosome)populationIt.next();

            bc.setFitness(Variables.getOneMaxFitness(bc));
        }

        Variables.setRequiredFitness(((IChromosome)population.get(0)).getChromosome().size());
    }

    public void zeroMax() {
        ArrayList population = Variables.getPopulation().getPopulation();

        Iterator populationIt = population.iterator();

        while(populationIt.hasNext()) {
            BinaryChromosome bc = (BinaryChromosome)populationIt.next();

            bc.setFitness(Variables.getZeroMaxFitness(bc));
        }

        Variables.setRequiredFitness(((IChromosome)population.get(0)).getChromosome().size());
    }

    public void binNum(int num) {
        ArrayList population = Variables.getPopulation().getPopulation();

        Iterator populationIt = population.iterator();

        while(populationIt.hasNext()) {
            BinaryChromosome bc = (BinaryChromosome)populationIt.next();

            bc.setFitness(Variables.getBinNumFitness(bc, num));
        }

        Variables.setRequiredFitness(((IChromosome)population.get(0)).getChromosome().size());
    }
}
