/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.gui.panel.form;

import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.IChromosome;
import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.PermutationChromosome;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class PermutationEvaluatePopulationForm extends EvaluatePopulationForm implements ActionListener {

    protected JTextField textField;
    protected ButtonGroup btnGroup;

    public PermutationEvaluatePopulationForm() {
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
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        add(method, c);

        JRadioButton concrete = new JRadioButton("Konkrétní řetězec");
        concrete.setActionCommand("concrete");
        concrete.setSelected(true);
        buttons.add(concrete);

        btnGroup = new ButtonGroup();
        btnGroup.add(concrete);

        c.gridy = 1;
        add(buttons, c);

        concrete.addActionListener(this);

        textField = new JTextField();
        c.gridy = 2;
        add(textField, c);

        JButton evaluateBtn = new JButton("Ohodnoť populaci");
        evaluateBtn.setActionCommand("evaluate");
        evaluateBtn.addActionListener(this);
        c.gridy = 3;
        add(evaluateBtn, c);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "concrete": {
                textField.setEnabled(true);
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
            case "concrete": {
                // Check input field
                int cLength = ((PermutationChromosome)Variables.getPopulation().getPopulation().get(0)).getChromosome().size();
                ArrayList set = ((PermutationChromosome)Variables.getPopulation().getPopulation().get(0)).getSet();
                String str = textField.getText();
                String[] items = str.split("");
                ArrayList input = new ArrayList();
                if(items.length != cLength) {
                    JOptionPane.showMessageDialog(null, "Délka řetězce neodpovídá délce chromozomu.", "Chyba", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                for(int i = 0; i < items.length; i++) {
                    if(input.indexOf(items[i]) != -1) {
                        JOptionPane.showMessageDialog(null, "Řetězec nemá unikátní položky.", "Chyba", JOptionPane.ERROR_MESSAGE);
                        return;
                    } else {
                        input.add(items[i]);
                    }
                }
                for(int i = 0; i < input.size(); i++) {
                    if(set.indexOf(input.get(i)) == -1) {
                        JOptionPane.showMessageDialog(null, "Řetězec obsahuje položky, které nejsou v sadě chromozomu.", "Chyba", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                concrete(str);
                Variables.setFitnessMethodType(FitnessMethodType.CONCRETE);
                Variables.setConcrete(str);
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

    public void concrete(String s) {
        ArrayList population = Variables.getPopulation().getPopulation();

        Iterator populationIt = population.iterator();

        while(populationIt.hasNext()) {
            PermutationChromosome sc = (PermutationChromosome)populationIt.next();

            sc.setFitness(Variables.getConcreteFitness(sc, s));
        }

        Variables.setRequiredFitness(((IChromosome)population.get(0)).getChromosome().size());
    }
}
