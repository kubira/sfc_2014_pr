/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.gui.panel.form;

import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.IChromosome;
import cz.vutbr.fit.xkubis03.sfc.ga.gui.ChromosomePopulationTableModel;
import cz.vutbr.fit.xkubis03.sfc.ga.gui.ToolBarActiveButton;
import cz.vutbr.fit.xkubis03.sfc.ga.gui.panel.CheckEndConditionPanel;
import cz.vutbr.fit.xkubis03.sfc.ga.population.ChromosomePopulation;
import cz.vutbr.fit.xkubis03.sfc.ga.util.Variables;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class CheckEndConditionForm extends JPanel implements ActionListener {

    protected JLabel requiredFitnessLabel;
    protected double requiredFitness;

    public CheckEndConditionForm() {
        super();

        setLayout(new GridBagLayout());

        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));

        createGUI();
    }

    private void createGUI() {
        GridBagConstraints c = new GridBagConstraints();

        JLabel label = new JLabel("Požadovaná fitness: ");
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        add(label, c);

        requiredFitnessLabel = new JLabel();
        c.gridx = 1;
        c.gridy = 0;
        add(requiredFitnessLabel, c);

        JButton btn = new JButton("Zkontroluj populaci");
        btn.setActionCommand("check");
        btn.addActionListener(this);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        add(btn, c);
    }

    public void setRequiredFitness(double fitness) {
        requiredFitnessLabel.setText(Double.toString(fitness));
        requiredFitness = fitness;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "check": {
                int numberOfSolutions = 0;
                final ArrayList indexOfSolution = new ArrayList();

                ChromosomePopulation cPopulation = Variables.getPopulation();
                Iterator it = cPopulation.getPopulation().iterator();

                while(it.hasNext()) {
                    IChromosome c = (IChromosome)it.next();
                    if(c.getFitness() == requiredFitness) {
                        numberOfSolutions++;
                        indexOfSolution.add(cPopulation.getPopulation().indexOf(c));
                    }
                }

                JTable chromosomeTable = new JTable(new ChromosomePopulationTableModel(cPopulation)) {
                    @Override
                    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                        Component c = super.prepareRenderer(renderer, row, column);

                        if(indexOfSolution.indexOf(row) != -1) {
                            c.setBackground(Color.GREEN);
                        } else {
                            c.setBackground(getBackground());
                        }

                        return c;
                    }
                };
                chromosomeTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                Variables.resizeTable(chromosomeTable);
                JScrollPane chromosomeTablePane = new JScrollPane(chromosomeTable);

                // Show table
                ((CheckEndConditionPanel)Variables.getActualScreen()).setTablePane(chromosomeTablePane);

                if(numberOfSolutions == 0) {
                    Variables.getToolBar().activateButtons(ToolBarActiveButton.UNSOLVED);
                } else {
                    Variables.getToolBar().activateButtons(ToolBarActiveButton.SOLVED);
                }

                Variables.getWorkingPlace().getPsPanel().setNumberOfSpinners();

                JOptionPane.showMessageDialog(null, "Počet řešení v populaci: " + numberOfSolutions, "Informace", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
        }
    }
}
