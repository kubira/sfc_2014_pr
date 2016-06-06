/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.gui.panel.form;

import cz.vutbr.fit.xkubis03.sfc.ga.gui.AlgorithmTypeComboBoxModel;
import cz.vutbr.fit.xkubis03.sfc.ga.gui.AlgorithmTypeComboBoxObject;
import cz.vutbr.fit.xkubis03.sfc.ga.gui.FitnessConversionTypeComboBoxModel;
import cz.vutbr.fit.xkubis03.sfc.ga.gui.FitnessConversionTypeComboBoxObject;
import cz.vutbr.fit.xkubis03.sfc.ga.gui.ParentSelectionTableModel;
import cz.vutbr.fit.xkubis03.sfc.ga.gui.ToolBarActiveButton;
import cz.vutbr.fit.xkubis03.sfc.ga.gui.panel.ParentSelectionPanel;
import cz.vutbr.fit.xkubis03.sfc.ga.population.FitnessConversionType;
import cz.vutbr.fit.xkubis03.sfc.ga.selection.ParentSelection;
import cz.vutbr.fit.xkubis03.sfc.ga.util.Constants;
import cz.vutbr.fit.xkubis03.sfc.ga.util.Variables;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class ParentSelectionForm extends JPanel implements ActionListener {

    protected JComboBox algorithmTypeComboBox;
    protected JComboBox fitnessConversionTypeComboBox;
    protected JSpinner numberOfParentsSpinner;
    protected JSpinner percentOfParentsSpinner;
    protected JSpinner numberOfSamplesSpinner;
    protected JSpinner selectivePressureSpinner;

    protected ParentSelectionPanel psPanel;

    public ParentSelectionForm(ParentSelectionPanel psPanel) {
        super();

        numberOfParentsSpinner = null;
        numberOfSamplesSpinner = null;

        this.psPanel = psPanel;

        setLayout(new GridBagLayout());

        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));

        createGUI();
    }

    private void createGUI() {
        GridBagConstraints c = new GridBagConstraints();

        JLabel typeLabel = new JLabel("Typ algoritmu:");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(2, 2, 2, 2);
        add(typeLabel, c);

        AlgorithmTypeComboBoxModel algorithmTypeComboBoxModel = new AlgorithmTypeComboBoxModel();
        algorithmTypeComboBox = new JComboBox(algorithmTypeComboBoxModel);
        algorithmTypeComboBox.setActionCommand("algorithmCombobox");
        algorithmTypeComboBox.addActionListener(this);
        c.gridx = 1;
        add(algorithmTypeComboBox, c);

        JLabel numberOfParents = new JLabel("Počet rodičů:");
        c.gridx = 0;
        c.gridy = 1;
        add(numberOfParents, c);

        // Number of parents spinner

        JLabel percentOfParents = new JLabel("Procento rodičů:");
        c.gridx = 0;
        c.gridy = 2;
        add(percentOfParents, c);

        SpinnerModel percentOfParentsSpinnerModel = new SpinnerNumberModel(10, 5, 100, 5);
        percentOfParentsSpinner = new JSpinner(percentOfParentsSpinnerModel);
        c.gridx = 1;
        add(percentOfParentsSpinner, c);

        JLabel numberOfSamples = new JLabel("Počet vzorků:");
        c.gridx = 0;
        c.gridy = 3;
        add(numberOfSamples, c);

        // Number of samples spinner

        JLabel convLabel = new JLabel("Konverze fitness:");
        c.gridx = 0;
        c.gridy = 4;
        add(convLabel, c);

        FitnessConversionTypeComboBoxModel fitnessConversionTypeComboBoxModel = new FitnessConversionTypeComboBoxModel();
        fitnessConversionTypeComboBox = new JComboBox(fitnessConversionTypeComboBoxModel);
        fitnessConversionTypeComboBox.setActionCommand("conversionCombobox");
        fitnessConversionTypeComboBox.addActionListener(this);
        c.gridx = 1;
        add(fitnessConversionTypeComboBox, c);

        JLabel selectivePressLabel = new JLabel("Selektivní tlak:");
        c.gridx = 0;
        c.gridy = 5;
        add(selectivePressLabel, c);

        SpinnerModel selectivePressureSpinnerModel = new SpinnerNumberModel(2, 1, 10, 1);
        selectivePressureSpinner = new JSpinner(selectivePressureSpinnerModel);
        c.gridx = 1;
        add(selectivePressureSpinner, c);

        JButton btn = new JButton("Vyber rodiče");
        btn.setActionCommand("select");
        btn.addActionListener(this);
        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 2;
        add(btn, c);

        percentOfParentsSpinner.setEnabled(false);
        selectivePressureSpinner.setEnabled(false);
    }

    public void setNumberOfSpinners() {
        if(numberOfParentsSpinner != null) {
            remove(numberOfParentsSpinner);
        }

        if(numberOfSamplesSpinner != null) {
            remove(numberOfSamplesSpinner);
        }

        int populationSize = Variables.getPopulation().getPopulation().size();
        int defValue = populationSize/2;

        if(defValue % 2 == 1) {
            defValue++;

        }

        SpinnerModel numberOfParentsSpinnerModel = new SpinnerNumberModel(defValue, Constants.MINIMAL_NUMBER_OF_PARENTS, populationSize, 2);
        numberOfParentsSpinner = new JSpinner(numberOfParentsSpinnerModel);

        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(2, 2, 2, 2);

        add(numberOfParentsSpinner, c);

        SpinnerModel numberOfSamplesSpinnerModel = new SpinnerNumberModel(defValue, Constants.MINIMAL_NUMBER_OF_PARENTS, populationSize, 1);
        numberOfSamplesSpinner = new JSpinner(numberOfSamplesSpinnerModel);

        c.gridy = 3;
        add(numberOfSamplesSpinner, c);

        numberOfParentsSpinner.setEnabled(true);
        numberOfSamplesSpinner.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "algorithmCombobox": {
                JComboBox cb = (JComboBox)e.getSource();
                AlgorithmTypeComboBoxObject item = (AlgorithmTypeComboBoxObject)cb.getSelectedItem();
                switch(item.getpsType()) {
                    case ROULETTE: {
                        numberOfParentsSpinner.setEnabled(true);
                        percentOfParentsSpinner.setEnabled(false);
                        numberOfSamplesSpinner.setEnabled(false);
                        break;
                    }
                    case STOCHASTIC: {
                        numberOfParentsSpinner.setEnabled(true);
                        percentOfParentsSpinner.setEnabled(false);
                        numberOfSamplesSpinner.setEnabled(false);
                        break;
                    }
                    case ELITE: {
                        numberOfParentsSpinner.setEnabled(false);
                        percentOfParentsSpinner.setEnabled(true);
                        numberOfSamplesSpinner.setEnabled(false);
                        break;
                    }
                    case TOURNAMENT: {
                        numberOfParentsSpinner.setEnabled(true);
                        percentOfParentsSpinner.setEnabled(false);
                        numberOfSamplesSpinner.setEnabled(true);
                        break;
                    }
                }
                break;
            }
            case "conversionCombobox": {
                JComboBox cb = (JComboBox)e.getSource();
                FitnessConversionTypeComboBoxObject item = (FitnessConversionTypeComboBoxObject)cb.getSelectedItem();
                switch(item.getFcType()) {
                    case NONE: {
                        selectivePressureSpinner.setEnabled(false);
                        break;
                    }
                    case PROPORTIONAL: {
                        selectivePressureSpinner.setEnabled(false);
                        break;
                    }
                    case RANK_BASED: {
                        selectivePressureSpinner.setEnabled(true);
                        break;
                    }
                }
                break;
            }
            case "select": {
                // Select parents
                ParentSelection pSelection = new ParentSelection();

                int sPressure = (int)selectivePressureSpinner.getValue();
                int noParents = (int)numberOfParentsSpinner.getValue();
                FitnessConversionType fcType = ((FitnessConversionTypeComboBoxObject)fitnessConversionTypeComboBox.getSelectedItem()).getFcType();
                int poParents = (int)percentOfParentsSpinner.getValue();
                int noSamples = (int)numberOfSamplesSpinner.getValue();

                AlgorithmTypeComboBoxObject item = (AlgorithmTypeComboBoxObject)algorithmTypeComboBox.getSelectedItem();
                switch(item.getpsType()) {
                    case ROULETTE: {
                        Variables.setParentSelection(pSelection.roulette(Variables.getPopulation(), sPressure, noParents, fcType));
                        break;
                    }
                    case STOCHASTIC: {
                        Variables.setParentSelection(pSelection.stochastic(Variables.getPopulation(), sPressure, noParents, fcType));
                        break;
                    }
                    case ELITE: {
                        Variables.setParentSelection(pSelection.elite(Variables.getPopulation(), sPressure, poParents, fcType));
                        break;
                    }
                    case TOURNAMENT: {
                        Variables.setParentSelection(pSelection.tournament(Variables.getPopulation(), sPressure, noSamples, noParents, fcType));
                        break;
                    }
                }

                JTable chromosomeTable = new JTable(new ParentSelectionTableModel(Variables.getParentSelection()));
                chromosomeTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                Variables.resizeTable(chromosomeTable);
                JScrollPane chromosomeTablePane = new JScrollPane(chromosomeTable);

                // Show table
                ((ParentSelectionPanel)Variables.getActualScreen()).setTablePane(chromosomeTablePane);

                Variables.getToolBar().activateButtons(ToolBarActiveButton.PARENTS);
                Variables.getWorkingPlace().getCoPanel().setTablePane(null);
                Variables.getWorkingPlace().getCoPanel().setOptions();
                Variables.getWorkingPlace().getMPanel().setSpinners();

                break;
            }
        }
    }

}
