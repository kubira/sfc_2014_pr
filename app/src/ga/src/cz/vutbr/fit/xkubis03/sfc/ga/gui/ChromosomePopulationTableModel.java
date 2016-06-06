/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.gui;

import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.IChromosome;
import cz.vutbr.fit.xkubis03.sfc.ga.population.ChromosomePopulation;
import javax.swing.table.AbstractTableModel;

public class ChromosomePopulationTableModel extends AbstractTableModel {

    protected String[] columnNames = {"ID", "Fitness", "Chromozom"};
    protected ChromosomePopulation cp;

    public ChromosomePopulationTableModel(ChromosomePopulation cp) {
        super();
        this.cp = cp;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public int getRowCount() {
        return cp.getPopulation().size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        IChromosome c = (IChromosome)cp.getPopulation().get(rowIndex);

        switch(columnIndex) {
            case 0: {
                return rowIndex;
            }
            case 1: {
                return String.format("%.5f", c.getFitness());
            }
            default:
            case 2: {
                return c.toString();
            }
        }
    }

}
