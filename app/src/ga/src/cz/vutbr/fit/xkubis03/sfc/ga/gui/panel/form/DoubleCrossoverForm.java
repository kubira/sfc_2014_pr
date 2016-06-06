/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.gui.panel.form;

import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.DoubleChromosome;
import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.IChromosome;
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
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class DoubleCrossoverForm extends CrossoverForm implements ActionListener {

    protected JSpinner positionSpinner;
    protected JSpinner numberOfPointsSpinner;
    protected JSpinner minSpinner;
    protected JSpinner maxSpinner;

    public DoubleCrossoverForm() {
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

        JLabel positionLabel = new JLabel("Pozice (0 = náhodně):");
        c.gridx = 0;
        c.gridy = 1;
        add(positionLabel, c);

        // Position spinner

        JLabel noPointLabel = new JLabel("Počet bodů (0 = náhodně):");
        c.gridx = 0;
        c.gridy = 2;
        add(noPointLabel, c);

        // Number of points spinner

        JLabel minLabel = new JLabel("Minimum koeficientu:");
        c.gridx = 0;
        c.gridy = 3;
        add(minLabel, c);

        SpinnerModel minModel = new SpinnerNumberModel(1.0, -10.0, 10.0, 0.1);
        minSpinner = new JSpinner(minModel);
        c.gridx = 1;
        add(minSpinner, c);
        minSpinner.setEnabled(false);

        JLabel maxLabel = new JLabel("Maximum koeficientu:");
        c.gridx = 0;
        c.gridy = 4;
        add(maxLabel, c);

        SpinnerModel maxModel = new SpinnerNumberModel(1.0, -10.0, 10.0, 0.1);
        maxSpinner = new JSpinner(maxModel);
        c.gridx = 1;
        add(maxSpinner, c);
        maxSpinner.setEnabled(false);

        JLabel info = new JLabel("Pokud min=0 a max=0 - náhodný koeficient.");
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 2;
        add(info, c);

        JButton btn = new JButton("Proveď křížení");
        btn.setActionCommand("cross");
        btn.addActionListener(this);
        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 2;
        add(btn, c);
    }

    public void setSpinners() {
        if(positionSpinner != null) {
            remove(positionSpinner);
        }

        if(numberOfPointsSpinner != null) {
            remove(numberOfPointsSpinner);
        }

        int cLenght = ((IChromosome)Variables.getPopulation().getPopulation().get(0)).getChromosome().size();
        int defValue = cLenght/2;

        if(defValue % 2 == 1) {
            defValue++;

        }

        SpinnerModel numberOfParentsSpinnerModel = new SpinnerNumberModel(defValue, 0, (cLenght-1), 1);
        positionSpinner = new JSpinner(numberOfParentsSpinnerModel);

        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(2, 2, 2, 2);

        add(positionSpinner, c);

        SpinnerModel numberOfSamplesSpinnerModel = new SpinnerNumberModel(2, 0, defValue, 1);
        numberOfPointsSpinner = new JSpinner(numberOfSamplesSpinnerModel);

        c.gridy = 2;
        add(numberOfPointsSpinner, c);

        positionSpinner.setEnabled(true);
        numberOfPointsSpinner.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "combobox": {
                CrossoverTypeComboBoxObject item = (CrossoverTypeComboBoxObject)crossoverTypeComboBox.getSelectedItem();
                switch(item.getCoType()) {
                    case SINGLE_POINT: {
                        positionSpinner.setEnabled(true);
                        numberOfPointsSpinner.setEnabled(false);
                        minSpinner.setEnabled(false);
                        maxSpinner.setEnabled(false);
                        break;
                    }
                    case MULTI_POINT: {
                        positionSpinner.setEnabled(false);
                        numberOfPointsSpinner.setEnabled(true);
                        minSpinner.setEnabled(false);
                        maxSpinner.setEnabled(false);
                        break;
                    }
                    case UNIFORM: {
                        positionSpinner.setEnabled(false);
                        numberOfPointsSpinner.setEnabled(false);
                        minSpinner.setEnabled(false);
                        maxSpinner.setEnabled(false);
                        break;
                    }
                    case ARITHMETIC: {
                        positionSpinner.setEnabled(false);
                        numberOfPointsSpinner.setEnabled(false);
                        minSpinner.setEnabled(true);
                        maxSpinner.setEnabled(true);
                        break;
                    }
                    case HEURISTIC: {
                        positionSpinner.setEnabled(false);
                        numberOfPointsSpinner.setEnabled(false);
                        minSpinner.setEnabled(false);
                        maxSpinner.setEnabled(false);
                        break;
                    }
                }
                break;
            }
            case "cross": {
                ChromosomeCrossover cCrossover = new ChromosomeCrossover();
                ArrayList offspring = new ArrayList();
                int position = (int)positionSpinner.getValue();
                int noPoints = (int)numberOfPointsSpinner.getValue();

                CrossoverTypeComboBoxObject item = (CrossoverTypeComboBoxObject)crossoverTypeComboBox.getSelectedItem();
                switch(item.getCoType()) {
                    case SINGLE_POINT: {
                        Iterator it = Variables.getParentSelection().iterator();

                        while(it.hasNext()) {
                            IChromosome i1 = (IChromosome)it.next();
                            IChromosome i2 = (IChromosome)it.next();

                            if(position == 0) {
                                offspring.addAll(cCrossover.singlePointCrossover(i1, i2));
                            } else {
                                offspring.addAll(cCrossover.singlePointCrossover(i1, i2, position));
                            }
                        }
                        break;
                    }
                    case MULTI_POINT: {
                        Iterator it = Variables.getParentSelection().iterator();

                        while(it.hasNext()) {
                            IChromosome i1 = (IChromosome)it.next();
                            IChromosome i2 = (IChromosome)it.next();

                            if(noPoints == 0) {
                                offspring.addAll(cCrossover.multiPointCrossover(i1, i2));
                            } else {
                                offspring.addAll(cCrossover.multiPointCrossover(i1, i2, noPoints));
                            }
                        }
                        break;
                    }
                    case UNIFORM: {
                        Iterator it = Variables.getParentSelection().iterator();

                        while(it.hasNext()) {
                            IChromosome i1 = (IChromosome)it.next();
                            IChromosome i2 = (IChromosome)it.next();

                            offspring.addAll(cCrossover.uniformCrossover(i1, i2));
                        }
                        break;
                    }
                    case ARITHMETIC: {
                        Iterator it = Variables.getParentSelection().iterator();

                        double min = (Double)minSpinner.getValue();
                        double max = (Double)maxSpinner.getValue();
                        if(min == 0.0 && max == 0.0) {
                            while(it.hasNext()) {
                                DoubleChromosome i1 = (DoubleChromosome)it.next();
                                DoubleChromosome i2 = (DoubleChromosome)it.next();

                                offspring.addAll(cCrossover.arithmeticCrossover(i1, i2));
                            }
                        } else if(min >= max) {
                            JOptionPane.showMessageDialog(null, "Miminální hodnota musí být menší než maximální hodnota.", "Chyba", JOptionPane.ERROR_MESSAGE);
                            return;
                        } else {
                            while(it.hasNext()) {
                                DoubleChromosome i1 = (DoubleChromosome)it.next();
                                DoubleChromosome i2 = (DoubleChromosome)it.next();

                                offspring.addAll(cCrossover.arithmeticCrossover(i1, i2, min, max));
                            }
                        }
                        break;
                    }
                    case HEURISTIC: {
                        Iterator it = Variables.getParentSelection().iterator();

                        while(it.hasNext()) {
                            DoubleChromosome i1 = (DoubleChromosome)it.next();
                            DoubleChromosome i2 = (DoubleChromosome)it.next();

                            offspring.addAll(cCrossover.heuristicCrossover(i1, i2));
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
