/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.gui.panel.form;

import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.ChromosomeType;
import cz.vutbr.fit.xkubis03.sfc.ga.crossover.ChromosomeCrossoverType;
import cz.vutbr.fit.xkubis03.sfc.ga.gui.AlgorithmTypeComboBoxModel;
import cz.vutbr.fit.xkubis03.sfc.ga.gui.AlgorithmTypeComboBoxObject;
import cz.vutbr.fit.xkubis03.sfc.ga.gui.CrossoverTypeComboBoxModel;
import cz.vutbr.fit.xkubis03.sfc.ga.gui.CrossoverTypeComboBoxObject;
import cz.vutbr.fit.xkubis03.sfc.ga.gui.FitnessConversionTypeComboBoxModel;
import cz.vutbr.fit.xkubis03.sfc.ga.gui.FitnessConversionTypeComboBoxObject;
import cz.vutbr.fit.xkubis03.sfc.ga.gui.NextPopulationTypeComboBoxModel;
import cz.vutbr.fit.xkubis03.sfc.ga.gui.NextPopulationTypeComboBoxObject;
import cz.vutbr.fit.xkubis03.sfc.ga.gui.TaskTypeComboBoxModel;
import cz.vutbr.fit.xkubis03.sfc.ga.selection.ParentSelectionType;
import cz.vutbr.fit.xkubis03.sfc.ga.task.OneMaxTask;
import cz.vutbr.fit.xkubis03.sfc.ga.task.Task;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class ExamplesForm extends JPanel implements ActionListener {

    protected JComboBox taskType;
    protected JSpinner numberOfIteratiosSpinner;
    protected JSpinner lengthOfChromosomeSpinner;
    protected JSpinner numberOfChromosomesSpinner;
    protected JComboBox algorithmTypeComboBox;
    protected JSpinner numberOfParentsSpinner;
    protected JSpinner percentOfParentsSpinner;
    protected JSpinner numberOfSamplesSpinner;
    protected JComboBox fitnessConversionTypeComboBox;
    protected JSpinner selectivePressureSpinner;
    protected JComboBox crossoverTypeComboBox;
    protected JSpinner positionSpinner;
    protected JSpinner numberOfPointsSpinner;
    protected JSpinner thresholdSpinner;
    protected JSpinner indexSpinner;
    protected JComboBox nextPopulationTypeComboBox;
    protected JSpinner percentOfReplace;

    public ExamplesForm() {
        super();

        setLayout(new GridBagLayout());

        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));

        createGUI();
    }

    private void createGUI() {
        GridBagConstraints c = new GridBagConstraints();

        JLabel typeLabel = new JLabel("Příklad:");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(2, 2, 2, 2);
        add(typeLabel, c);

        TaskTypeComboBoxModel taskTypeComboBoxModel = new TaskTypeComboBoxModel();
        taskType = new JComboBox(taskTypeComboBoxModel);
        c.gridx = 1;
        add(taskType, c);
        taskType.setEnabled(false);

        JLabel numberOfIterationsLabel = new JLabel("Počet iterací:");
        c.gridx = 2;
        c.gridy = 0;
        add(numberOfIterationsLabel, c);

        SpinnerModel numberOfIterations = new SpinnerNumberModel(100, 20, 100000, 20);
        numberOfIteratiosSpinner = new JSpinner(numberOfIterations);
        c.gridx = 3;
        add(numberOfIteratiosSpinner, c);

        JLabel lengthLabel = new JLabel("Délka chromozomu:");
        c.gridx = 0;
        c.gridy = 1;
        add(lengthLabel, c);

        SpinnerModel lengthOfChromosome = new SpinnerNumberModel(10, 4, 100, 1);
        lengthOfChromosomeSpinner = new JSpinner(lengthOfChromosome);
        c.gridx = 1;
        add(lengthOfChromosomeSpinner, c);

        JLabel numberOfLabel = new JLabel("Počet chromozomů v populaci:");
        c.gridx = 0;
        c.gridy = 2;
        add(numberOfLabel, c);

        SpinnerModel numberOfChromosomesSpinnerModel = new SpinnerNumberModel(20, 10, 1000, 2);
        numberOfChromosomesSpinner = new JSpinner(numberOfChromosomesSpinnerModel);
        c.gridx = 1;
        add(numberOfChromosomesSpinner, c);

        JLabel algTypeLabel = new JLabel("Výběr rodičů:");
        c.gridx = 0;
        c.gridy = 3;
        add(algTypeLabel, c);

        AlgorithmTypeComboBoxModel algorithmTypeComboBoxModel = new AlgorithmTypeComboBoxModel();
        algorithmTypeComboBox = new JComboBox(algorithmTypeComboBoxModel);
        algorithmTypeComboBox.setActionCommand("algorithmCombobox");
        algorithmTypeComboBox.addActionListener(this);
        c.gridx = 1;
        add(algorithmTypeComboBox, c);

        JLabel numberOfParents = new JLabel("Počet rodičů:");
        c.gridx = 0;
        c.gridy = 4;
        add(numberOfParents, c);

        SpinnerModel numberOfParentsSpinnerModel = new SpinnerNumberModel(10, 2, 1000, 2);
        numberOfParentsSpinner = new JSpinner(numberOfParentsSpinnerModel);
        c.gridx = 1;
        add(numberOfParentsSpinner, c);

        JLabel percentOfParents = new JLabel("Procento rodičů:");
        c.gridx = 0;
        c.gridy = 5;
        add(percentOfParents, c);

        SpinnerModel percentOfParentsSpinnerModel = new SpinnerNumberModel(10, 1, 100, 1);
        percentOfParentsSpinner = new JSpinner(percentOfParentsSpinnerModel);
        c.gridx = 1;
        add(percentOfParentsSpinner, c);

        JLabel numberOfSamples = new JLabel("Počet vzorků:");
        c.gridx = 0;
        c.gridy = 6;
        add(numberOfSamples, c);

        SpinnerModel numberOfSamplesSpinnerModel = new SpinnerNumberModel(10, 2, 1000, 1);
        numberOfSamplesSpinner = new JSpinner(numberOfSamplesSpinnerModel);
        c.gridx = 1;
        add(numberOfSamplesSpinner, c);

        JLabel convLabel = new JLabel("Konverze fitness:");
        c.gridx = 0;
        c.gridy = 7;
        add(convLabel, c);

        FitnessConversionTypeComboBoxModel fitnessConversionTypeComboBoxModel = new FitnessConversionTypeComboBoxModel();
        fitnessConversionTypeComboBox = new JComboBox(fitnessConversionTypeComboBoxModel);
        fitnessConversionTypeComboBox.setActionCommand("conversionCombobox");
        fitnessConversionTypeComboBox.addActionListener(this);
        c.gridx = 1;
        add(fitnessConversionTypeComboBox, c);

        JLabel selectivePressLabel = new JLabel("Selektivní tlak:");
        c.gridx = 0;
        c.gridy = 8;
        add(selectivePressLabel, c);

        SpinnerModel selectivePressureSpinnerModel = new SpinnerNumberModel(2, 1, 100, 1);
        selectivePressureSpinner = new JSpinner(selectivePressureSpinnerModel);
        c.gridx = 1;
        add(selectivePressureSpinner, c);

        JLabel crossTypeLabel = new JLabel("Typ křížení:");
        c.gridx = 2;
        c.gridy = 1;
        add(crossTypeLabel, c);

        CrossoverTypeComboBoxModel crossoverTypeComboBoxmodel = new CrossoverTypeComboBoxModel();
        crossoverTypeComboBoxmodel.setOptions(ChromosomeType.BINARY);
        crossoverTypeComboBox = new JComboBox(crossoverTypeComboBoxmodel);
        crossoverTypeComboBox.setActionCommand("crossCombobox");
        crossoverTypeComboBox.addActionListener(this);
        c.gridx = 3;
        add(crossoverTypeComboBox, c);

        JLabel positionLabel = new JLabel("Pozice (0 = náhodně):");
        c.gridx = 2;
        c.gridy = 2;
        add(positionLabel, c);

        SpinnerModel positionSpinnerModel = new SpinnerNumberModel(5, 0, 100, 1);
        positionSpinner = new JSpinner(positionSpinnerModel);
        c.gridx = 3;
        add(positionSpinner, c);

        JLabel noPointLabel = new JLabel("Počet bodů (0 = náhodně):");
        c.gridx = 2;
        c.gridy = 3;
        add(noPointLabel, c);

        SpinnerModel numberOfPointsSpinnerModel = new SpinnerNumberModel(2, 0, 50, 1);
        numberOfPointsSpinner = new JSpinner(numberOfPointsSpinnerModel);
        c.gridx = 3;
        add(numberOfPointsSpinner, c);

        JLabel thresholdLabel = new JLabel("Práh mutace [%]:");
        c.gridx = 2;
        c.gridy = 4;
        add(thresholdLabel, c);

        SpinnerModel thresholdSpinnerModel = new SpinnerNumberModel(Constants.DEFAULT_MUTATION_THRESHOLD, 0.0, 100.0, 0.1);
        thresholdSpinner = new JSpinner(thresholdSpinnerModel);
        c.gridx = 3;
        add(thresholdSpinner, c);

        JLabel indexLabel = new JLabel("Index mutace (-1 = náhodně):");
        c.gridx = 2;
        c.gridy = 5;
        add(indexLabel, c);

        SpinnerModel indexSpinnerModel = new SpinnerNumberModel(5, -1, 100, 1);
        indexSpinner = new JSpinner(indexSpinnerModel);
        c.gridx = 3;
        c.gridy = 5;
        add(indexSpinner, c);

        JLabel newTypeLabel = new JLabel("Nová populace:");
        c.gridx = 2;
        c.gridy = 6;
        add(newTypeLabel, c);

        NextPopulationTypeComboBoxModel nextPopulationTypeComboBoxModel = new NextPopulationTypeComboBoxModel();
        nextPopulationTypeComboBox = new JComboBox(nextPopulationTypeComboBoxModel);
        nextPopulationTypeComboBox.setActionCommand("nextCombobox");
        nextPopulationTypeComboBox.addActionListener(this);
        c.gridx = 3;
        add(nextPopulationTypeComboBox, c);

        JLabel percentOfReplaceLabel = new JLabel("Procento náhrady:");
        c.gridx = 2;
        c.gridy = 7;
        add(percentOfReplaceLabel, c);

        SpinnerModel percentOfReplaceModel = new SpinnerNumberModel(50, 0, 100, 1);
        percentOfReplace = new JSpinner(percentOfReplaceModel);
        c.gridx = 3;
        add(percentOfReplace, c);

        JButton runExample = new JButton("Spusť GA");
        runExample.setActionCommand("run");
        runExample.addActionListener(this);
        c.gridx = 2;
        c.gridy = 8;
        c.gridwidth = 2;
        add(runExample, c);

        percentOfParentsSpinner.setEnabled(false);
        numberOfSamplesSpinner.setEnabled(false);

        selectivePressureSpinner.setEnabled(false);

        numberOfPointsSpinner.setEnabled(false);

        percentOfReplace.setEnabled(false);
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
            case "crossCombobox": {
                CrossoverTypeComboBoxObject item = (CrossoverTypeComboBoxObject)crossoverTypeComboBox.getSelectedItem();
                switch(item.getCoType()) {
                    case SINGLE_POINT: {
                        positionSpinner.setEnabled(true);
                        numberOfPointsSpinner.setEnabled(false);
                        break;
                    }
                    case MULTI_POINT: {
                        positionSpinner.setEnabled(false);
                        numberOfPointsSpinner.setEnabled(true);
                        break;
                    }
                    case UNIFORM: {
                        positionSpinner.setEnabled(false);
                        numberOfPointsSpinner.setEnabled(false);
                        break;
                    }
                }
                break;
            }
            case "nextCombobox": {
                switch(((NextPopulationTypeComboBoxObject)nextPopulationTypeComboBox.getSelectedItem()).getNpType()) {
                    case TOURNAMENT_REINSERTION: {
                        percentOfReplace.setEnabled(true);
                        break;
                    }
                    default: {
                        percentOfReplace.setEnabled(false);
                        break;
                    }
                }
                break;
            }
            case "run": {
                int populationSize = (int)numberOfChromosomesSpinner.getValue();
                int numberOfParents = (int)numberOfParentsSpinner.getValue();
                if(((AlgorithmTypeComboBoxObject)algorithmTypeComboBox.getSelectedItem()).getpsType() != ParentSelectionType.ELITE && numberOfParents > populationSize) {
                    JOptionPane.showMessageDialog(null, "Počet rodičů je větší než velikost populace.", "Chyba", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int numberOfSamples = (int)numberOfSamplesSpinner.getValue();
                if(((AlgorithmTypeComboBoxObject)algorithmTypeComboBox.getSelectedItem()).getpsType() == ParentSelectionType.TOURNAMENT && numberOfSamples > populationSize) {
                    JOptionPane.showMessageDialog(null, "Počet vzorků je větší než velikost populace.", "Chyba", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int chromosomeLength = (int)lengthOfChromosomeSpinner.getValue();
                int crossPosition = (int)positionSpinner.getValue();
                if(((CrossoverTypeComboBoxObject)crossoverTypeComboBox.getSelectedItem()).getCoType() == ChromosomeCrossoverType.SINGLE_POINT && crossPosition >= chromosomeLength) {
                    JOptionPane.showMessageDialog(null, "Pozice křížení musí být menší než délka chromozomu.", "Chyba", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int numberOfPoints = (int)numberOfPointsSpinner.getValue();
                if(((CrossoverTypeComboBoxObject)crossoverTypeComboBox.getSelectedItem()).getCoType() == ChromosomeCrossoverType.MULTI_POINT && numberOfPoints >= chromosomeLength) {
                    JOptionPane.showMessageDialog(null, "Počet pozic křížení musí být menší než délka chromozomu.", "Chyba", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int mutationIndex = (int)indexSpinner.getValue();
                if(mutationIndex >= chromosomeLength) {
                    JOptionPane.showMessageDialog(null, "Pozice mutace musí být menší než délka chromozomu.", "Chyba", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int numberOfIterations = (int)numberOfIteratiosSpinner.getValue();
                double mutationThreshold = (double)thresholdSpinner.getValue();
                Task oneMaxTask = new OneMaxTask(populationSize, chromosomeLength, numberOfIterations, mutationThreshold);
                oneMaxTask.setChType(ChromosomeType.BINARY);
                oneMaxTask.setPsType(((AlgorithmTypeComboBoxObject)algorithmTypeComboBox.getSelectedItem()).getpsType());
                oneMaxTask.setNumberOfParents(numberOfParents);
                oneMaxTask.setPercentOfParents((int)percentOfParentsSpinner.getValue());
                oneMaxTask.setNumberOfSamples(numberOfSamples);
                oneMaxTask.setFcType(((FitnessConversionTypeComboBoxObject)fitnessConversionTypeComboBox.getSelectedItem()).getFcType());
                oneMaxTask.setSelectivePressure((int)selectivePressureSpinner.getValue());
                oneMaxTask.setCoType(((CrossoverTypeComboBoxObject)crossoverTypeComboBox.getSelectedItem()).getCoType());
                oneMaxTask.setChromosomeCrossoverIndex(crossPosition);
                oneMaxTask.setNumberOfPoints(numberOfPoints);
                oneMaxTask.setMutationIndex(mutationIndex);
                oneMaxTask.setCnpType(((NextPopulationTypeComboBoxObject)nextPopulationTypeComboBox.getSelectedItem()).getNpType());
                oneMaxTask.setPercentOfReplace((int)percentOfReplace.getValue());
                oneMaxTask.run();

                JTextArea ta = new JTextArea(oneMaxTask.getResult());
                JScrollPane pane = new JScrollPane(ta);

                Variables.getWorkingPlace().getExPanel().setTablePane(pane);
            }
        }
    }
}
