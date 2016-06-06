/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.gui.panel.form;

import cz.vutbr.fit.xkubis03.sfc.ga.gui.ChromosomePopulationTableModel;
import cz.vutbr.fit.xkubis03.sfc.ga.gui.NextPopulationTypeComboBoxModel;
import cz.vutbr.fit.xkubis03.sfc.ga.gui.NextPopulationTypeComboBoxObject;
import cz.vutbr.fit.xkubis03.sfc.ga.gui.ToolBarActiveButton;
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

public class NextPopulationForm extends JPanel implements ActionListener {

    protected JComboBox nextPopulationTypeComboBox;
    protected JSpinner percentOfReplace;

    public NextPopulationForm() {
        super();

        setLayout(new GridBagLayout());

        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));

        createGUI();
    }

    private void createGUI() {
        GridBagConstraints c = new GridBagConstraints();

        JLabel typeLabel = new JLabel("Způsob vytvoření nové populace:");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(2, 2, 2, 2);
        add(typeLabel, c);

        NextPopulationTypeComboBoxModel nextPopulationTypeComboBoxModel = new NextPopulationTypeComboBoxModel();
        nextPopulationTypeComboBox = new JComboBox(nextPopulationTypeComboBoxModel);
        nextPopulationTypeComboBox.setActionCommand("combobox");
        nextPopulationTypeComboBox.addActionListener(this);
        c.gridx = 1;
        add(nextPopulationTypeComboBox, c);

        JLabel percentOfReplaceLabel = new JLabel("Procento náhrady staré populace:");
        c.gridx = 0;
        c.gridy = 1;
        add(percentOfReplaceLabel, c);

        SpinnerModel percentOfReplaceModel = new SpinnerNumberModel(50, 0, 100, 1);
        percentOfReplace = new JSpinner(percentOfReplaceModel);
        c.gridx = 1;
        add(percentOfReplace, c);
        percentOfReplace.setEnabled(false);

        JButton generateBtn = new JButton("Vytvoř novou populaci");
        generateBtn.setActionCommand("newPopulation");
        generateBtn.addActionListener(this);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        add(generateBtn, c);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "combobox": {
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
            case "newPopulation": {
                Variables.setNewPopulation(Variables.getPopulation().clonePopulation());

                NextPopulationTypeComboBoxObject obj = (NextPopulationTypeComboBoxObject)nextPopulationTypeComboBox.getSelectedItem();

                switch(obj.getNpType()) {
                    case GENERATIONAL: {
                        Variables.getNewPopulation().nextGenerational(Variables.getMutatedOffspring());
                        break;
                    }
                    case INCREMENTAL: {
                        Variables.getNewPopulation().nextIncremental(Variables.getMutatedOffspring());
                        break;
                    }
                    case PURE_REINSERTION: {
                        Variables.getNewPopulation().nextPureReinsertion(Variables.getMutatedOffspring());
                        break;
                    }
                    case UNIFORM_REINSERTION: {
                        Variables.getNewPopulation().nextUniformReinsertion(Variables.getMutatedOffspring());
                        break;
                    }
                    case ELITIST_REINSERTION: {
                        Variables.getNewPopulation().nextElitistReinsertion(Variables.getMutatedOffspring());
                        break;
                    }
                    case TOURNAMENT_REINSERTION: {
                        int percent = (int)percentOfReplace.getValue();
                        Variables.getNewPopulation().nextTournamentReinsertion(Variables.getMutatedOffspring(), percent);
                        break;
                    }
                }

                JTable newPopulationTable = new JTable();
                newPopulationTable.setModel(new ChromosomePopulationTableModel(Variables.getNewPopulation()));
                newPopulationTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                Variables.resizeTable(newPopulationTable);
                JScrollPane newPopulationTablePane = new JScrollPane(newPopulationTable);

                Variables.getWorkingPlace().getNpPanel().setTablePane(newPopulationTablePane);
                Variables.getToolBar().activateButtons(ToolBarActiveButton.NEW);
                break;
            }
        }
    }
}
