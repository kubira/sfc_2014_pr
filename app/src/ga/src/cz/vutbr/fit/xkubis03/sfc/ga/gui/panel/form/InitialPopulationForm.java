/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.gui.panel.form;

import cz.vutbr.fit.xkubis03.sfc.ga.gui.ChromosomeTypeComboBoxModel;
import cz.vutbr.fit.xkubis03.sfc.ga.gui.ChromosomeTypeComboBoxObject;
import cz.vutbr.fit.xkubis03.sfc.ga.population.BinaryChromosomePopulation;
import cz.vutbr.fit.xkubis03.sfc.ga.population.ChromosomePopulation;
import cz.vutbr.fit.xkubis03.sfc.ga.population.DoubleChromosomePopulation;
import cz.vutbr.fit.xkubis03.sfc.ga.population.IntegerChromosomePopulation;
import cz.vutbr.fit.xkubis03.sfc.ga.population.PermutationChromosomePopulation;
import cz.vutbr.fit.xkubis03.sfc.ga.population.StringChromosomePopulation;
import cz.vutbr.fit.xkubis03.sfc.ga.util.Variables;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
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
import javax.swing.text.DefaultCaret;

public class InitialPopulationForm extends JPanel implements ActionListener {

    protected JComboBox chromosomeTypeComboBox;
    protected JSpinner lengthOfChromosomeSpinner;
    protected JSpinner numberOfChromosomesSpinner;
    protected JSpinner minimumSpinner;
    protected JSpinner maximumSpinner;
    protected JTextArea setArea;

    public InitialPopulationForm() {
        super();

        setLayout(new GridBagLayout());

        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));

        createGUI();
    }

    private void createGUI() {
        GridBagConstraints c = new GridBagConstraints();

        JLabel typeLabel = new JLabel("Typ chromozomu:");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(2, 2, 2, 2);
        add(typeLabel, c);

        ChromosomeTypeComboBoxModel chromosomeTypeComboBoxmodel = new ChromosomeTypeComboBoxModel();
        chromosomeTypeComboBox = new JComboBox(chromosomeTypeComboBoxmodel);
        chromosomeTypeComboBox.setActionCommand("combobox");
        chromosomeTypeComboBox.addActionListener(this);
        c.gridx = 1;
        add(chromosomeTypeComboBox, c);

        JLabel lengthLabel = new JLabel("Délka chromozomu:");
        c.gridx = 0;
        c.gridy = 1;
        add(lengthLabel, c);

        SpinnerModel lengthOfChromosome = new SpinnerNumberModel(10, 4, 25, 1);
        lengthOfChromosomeSpinner = new JSpinner(lengthOfChromosome);
        c.gridx = 1;
        add(lengthOfChromosomeSpinner, c);

        JLabel numberOfLabel = new JLabel("Počet chromozomů v populaci:");
        c.gridx = 0;
        c.gridy = 2;
        add(numberOfLabel, c);

        SpinnerModel numberOfChromosomesSpinnerModel = new SpinnerNumberModel(10, 10, 100, 1);
        numberOfChromosomesSpinner = new JSpinner(numberOfChromosomesSpinnerModel);
        c.gridx = 1;
        add(numberOfChromosomesSpinner, c);

        JLabel minimumSpinnerLabel = new JLabel("Minimum:");
        c.gridx = 0;
        c.gridy = 3;
        add(minimumSpinnerLabel, c);

        minimumSpinner = new JSpinner();
        c.gridx = 1;
        add(minimumSpinner, c);
        minimumSpinner.setEnabled(false);

        JLabel maximumSpinnerLabel = new JLabel("Maximum:");
        c.gridx = 0;
        c.gridy = 4;
        add(maximumSpinnerLabel, c);

        maximumSpinner = new JSpinner();
        c.gridx = 1;
        add(maximumSpinner, c);
        maximumSpinner.setEnabled(false);

        JLabel setAreaLabel = new JLabel("Položky chromozomu (oddělené novým řádkem):");
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 2;
        add(setAreaLabel, c);

        setArea = new JTextArea("A\nC\nG\nT", 6, 10);
        setArea.setEnabled(false);

        DefaultCaret caret = (DefaultCaret)setArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(setArea);

        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 2;
        c.gridheight = 4;
        add(scrollPane, c);

        JButton generateBtn = new JButton("Vygeneruj počáteční populaci");
        generateBtn.setActionCommand("generatePopulation");
        generateBtn.addActionListener(this);
        c.gridx = 0;
        c.gridwidth = 2;
        c.gridy = 10;
        add(generateBtn, c);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();

        switch(action) {
            case "combobox": {
                JComboBox cb = (JComboBox)e.getSource();
                ChromosomeTypeComboBoxObject item = (ChromosomeTypeComboBoxObject)cb.getSelectedItem();
                switch(item.getcType()) {
                    case BINARY: {
                        lengthOfChromosomeSpinner.setEnabled(true);
                        minimumSpinner.setEnabled(false);
                        maximumSpinner.setEnabled(false);
                        setArea.setEnabled(false);
                        break;
                    }
                    case INTEGER: {
                        lengthOfChromosomeSpinner.setEnabled(true);
                        minimumSpinner.setModel(new SpinnerNumberModel(1.0, -100.0, 100.0, 1.0));
                        maximumSpinner.setModel(new SpinnerNumberModel(10.0, -100.0, 100.0, 1.0));
                        minimumSpinner.setEnabled(true);
                        maximumSpinner.setEnabled(true);
                        setArea.setEnabled(false);
                        break;
                    }
                    case DOUBLE: {
                        lengthOfChromosomeSpinner.setEnabled(true);
                        minimumSpinner.setModel(new SpinnerNumberModel(1.0, -100.0, 100.0, 0.1));
                        maximumSpinner.setModel(new SpinnerNumberModel(10.0, -100.0, 100.0, 0.1));
                        minimumSpinner.setEnabled(true);
                        maximumSpinner.setEnabled(true);
                        setArea.setEnabled(false);
                        break;
                    }
                    case STRING: {
                        lengthOfChromosomeSpinner.setEnabled(true);
                        minimumSpinner.setEnabled(false);
                        maximumSpinner.setEnabled(false);
                        setArea.setEnabled(true);
                        break;
                    }
                    case PERMUTATION: {
                        lengthOfChromosomeSpinner.setEnabled(false);
                        minimumSpinner.setEnabled(false);
                        maximumSpinner.setEnabled(false);
                        setArea.setEnabled(true);
                        break;
                    }
                }
                break;
            }
            case "generatePopulation": {
                int populationSize = (int)numberOfChromosomesSpinner.getValue();
                int chromosomeLength = (int)lengthOfChromosomeSpinner.getValue();
                ChromosomeTypeComboBoxObject item = (ChromosomeTypeComboBoxObject)chromosomeTypeComboBox.getSelectedItem();
                ChromosomePopulation cp;

                switch(item.getcType()) {
                    default:
                    case BINARY: {
                        cp = new BinaryChromosomePopulation(populationSize);
                        cp.generate(chromosomeLength);
                        break;
                    }
                    case INTEGER: {
                        int min = (int) Math.round((Double)minimumSpinner.getValue());
                        int max = (int) Math.round((Double)maximumSpinner.getValue());
                        if(min >= max) {
                            JOptionPane.showMessageDialog(null, "Miminální hodnota musí být menší než maximální hodnota.", "Chyba", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        cp = new IntegerChromosomePopulation(populationSize, min, max);
                        cp.generate(chromosomeLength);
                        break;
                    }
                    case DOUBLE: {
                        double min = (double)minimumSpinner.getValue();
                        double max = (double)maximumSpinner.getValue();
                        if(min >= max) {
                            JOptionPane.showMessageDialog(null, "Miminální hodnota musí být menší než maximální hodnota.", "Chyba", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        cp = new DoubleChromosomePopulation(populationSize, min, max);
                        cp.generate(chromosomeLength);
                        break;
                    }
                    case STRING: {
                        ArrayList set = new ArrayList();
                        String[] items = setArea.getText().split("\n");
                        set.addAll(Arrays.asList(items));
                        if (set.size() < 2) {
                            System.out.println("ERROR: Set of items is short.");
                            JOptionPane.showMessageDialog(null, "Položky řetězcového chromozomu musí být alespoň dvě.", "Chyba", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        cp = new StringChromosomePopulation(populationSize, set);
                        cp.generate(chromosomeLength);
                        break;
                    }
                    case PERMUTATION: {
                        ArrayList set = new ArrayList();
                        String[] items = setArea.getText().split("\n");
                        for (String inputItem : items) {
                            if (set.indexOf(inputItem) != -1) {
                                System.out.println("ERROR: Set of items is not unique.");
                                JOptionPane.showMessageDialog(null, "Položky permutačního chromozomu musí být unikátní.", "Chyba", JOptionPane.ERROR_MESSAGE);
                                return;
                            } else {
                                set.add(inputItem);
                            }
                        }
                        if (set.size() < 2) {
                            System.out.println("ERROR: Set of items is short.");
                            JOptionPane.showMessageDialog(null, "Položky permutačního chromozomu musí být alespoň dvě.", "Chyba", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        cp = new PermutationChromosomePopulation(populationSize, set);
                        cp.generate();
                        break;
                    }
                }

                Variables.getWorkingPlace().getIpPanel().generateTable(cp);

                revalidate();
                repaint();
                break;
            }
        }
    }
}
