/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.gui.panel.form;

import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.BinaryChromosome;
import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.ChromosomeType;
import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.DoubleChromosome;
import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.IChromosome;
import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.IntegerChromosome;
import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.PermutationChromosome;
import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.StringChromosome;
import cz.vutbr.fit.xkubis03.sfc.ga.gui.OffspringTableModel;
import cz.vutbr.fit.xkubis03.sfc.ga.gui.ToolBarActiveButton;
import cz.vutbr.fit.xkubis03.sfc.ga.mutation.ChromosomeMutation;
import cz.vutbr.fit.xkubis03.sfc.ga.util.Constants;
import cz.vutbr.fit.xkubis03.sfc.ga.util.Generator;
import cz.vutbr.fit.xkubis03.sfc.ga.util.Variables;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class MutateForm extends JPanel implements ActionListener {

    protected JSpinner thresholdSpinner;
    protected JSpinner indexSpinner;
    protected JSpinner index2Spinner;

    public MutateForm() {
        super();

        indexSpinner = null;
        index2Spinner = null;

        setLayout(new GridBagLayout());

        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));

        createGUI();
    }

    private void createGUI() {
        GridBagConstraints c = new GridBagConstraints();

        JLabel thresholdLabel = new JLabel("Pravděpodobnost mutace [%]:");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(2, 2, 2, 2);
        add(thresholdLabel, c);

        SpinnerModel thresholdSpinnerModel = new SpinnerNumberModel(Constants.DEFAULT_MUTATION_THRESHOLD, 0.0, 100.0, 0.1);
        thresholdSpinner = new JSpinner(thresholdSpinnerModel);
        c.gridx = 1;

        JComponent comp = ((JSpinner.DefaultEditor)thresholdSpinner.getEditor());
        Dimension prefSize = comp.getPreferredSize();
        prefSize = new Dimension(30, prefSize.height);
        comp.setPreferredSize(prefSize);

        add(thresholdSpinner, c);

        JLabel indexLabel = new JLabel("Index mutace (-1 = náhodně):");
        c.gridx = 0;
        c.gridy = 1;
        add(indexLabel, c);

        // Index spinner

        JLabel index2Label = new JLabel("Index mutace 2 (-1 = náhodně):");
        c.gridx = 0;
        c.gridy = 2;
        add(index2Label, c);

        // Index2 spinner

        JButton btn = new JButton("Proveď mutaci");
        btn.setActionCommand("mutate");
        btn.addActionListener(this);
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        add(btn, c);
    }

    public void setSpinners() {
        if(indexSpinner != null) {
            remove(indexSpinner);
        }

        if(index2Spinner != null) {
            remove(index2Spinner);
        }

        int cLength = ((IChromosome)Variables.getPopulation().getPopulation().get(0)).getChromosome().size();

        SpinnerModel indexSpinnerModel = new SpinnerNumberModel(cLength/2, -1, (cLength-1), 1);
        indexSpinner = new JSpinner(indexSpinnerModel);

        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(2, 2, 2, 2);

        add(indexSpinner, c);

        SpinnerModel index2SpinnerModel = new SpinnerNumberModel(cLength/2+1, -1, (cLength-1), 1);
        index2Spinner = new JSpinner(index2SpinnerModel);
        c.gridy = 2;
        add(index2Spinner, c);

        if(Variables.getPopulation().getChromosomeType() == ChromosomeType.PERMUTATION) {
            index2Spinner.setEnabled(true);
        } else {
            index2Spinner.setEnabled(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "mutate": {
                int index1 = (int)indexSpinner.getValue();
                int index2 = (int)index2Spinner.getValue();
                double mThreshold = (double)thresholdSpinner.getValue();
                mThreshold /= 100;
                int cLength = ((IChromosome)Variables.getPopulation().getPopulation().get(0)).getChromosome().size();

                ChromosomeMutation cm = new ChromosomeMutation(mThreshold);
                ArrayList mutatedOffspring = new ArrayList();

                Iterator it = Variables.getOffspring().iterator();

                switch(Variables.getPopulation().getChromosomeType()) {
                    case BINARY: {
                        while(it.hasNext()) {
                            if(index1 == -1) {
                                mutatedOffspring.add(cm.mutate((BinaryChromosome)it.next()));
                            } else {
                                mutatedOffspring.add(cm.mutate((BinaryChromosome)it.next(), index1));
                            }
                        }
                        break;
                    }
                    case INTEGER: {
                        while(it.hasNext()) {
                            if(index1 == -1) {
                                mutatedOffspring.add(cm.mutate((IntegerChromosome)it.next()));
                            } else {
                                mutatedOffspring.add(cm.mutate((IntegerChromosome)it.next(), index1));
                            }
                        }
                        break;
                    }
                    case DOUBLE: {
                        while(it.hasNext()) {
                            if(index1 == -1) {
                                mutatedOffspring.add(cm.mutate((DoubleChromosome)it.next()));
                            } else {
                                mutatedOffspring.add(cm.mutate((DoubleChromosome)it.next(), index1));
                            }
                        }
                        break;
                    }
                    case STRING: {
                        while(it.hasNext()) {
                            if(index1 == -1) {
                                mutatedOffspring.add(cm.mutate((StringChromosome)it.next()));
                            } else {
                                mutatedOffspring.add(cm.mutate((StringChromosome)it.next(), index1));
                            }
                        }
                        break;
                    }
                    case PERMUTATION: {
                        while(it.hasNext()) {
                            if(index1 == -1 && index2 == -1) {
                                mutatedOffspring.add(cm.mutate((PermutationChromosome)it.next()));
                            } else if(index1 == -1) {
                                index1 = (int)(new Generator()).getUniform(0, cLength);

                                mutatedOffspring.add(cm.mutate((PermutationChromosome)it.next(), index1, index2));
                            } else if(index2 == -1) {
                                index2 = (int)(new Generator()).getUniform(0, cLength);

                                mutatedOffspring.add(cm.mutate((PermutationChromosome)it.next(), index1, index2));
                            } else {
                                mutatedOffspring.add(cm.mutate((PermutationChromosome)it.next(), index1, index2));
                            }
                        }
                        break;
                    }
                }

                Variables.setMutatedOffspring(mutatedOffspring);
                Variables.computeMutatedOffspringFitness();

                JTable mOffspringTable = new JTable(new OffspringTableModel(Variables.getMutatedOffspring()));
                mOffspringTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                Variables.resizeTable(mOffspringTable);
                JScrollPane mOffspringTablePane = new JScrollPane(mOffspringTable);

                Variables.getWorkingPlace().getMPanel().setTablePane(mOffspringTablePane);
                Variables.getWorkingPlace().getNpPanel().setTablePane(null);

                Variables.getToolBar().activateButtons(ToolBarActiveButton.MUTATE);

                break;
            }
        }
    }

}
