/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.gui;

import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.IChromosome;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class OffspringTableModel extends AbstractTableModel {

    protected String[] columnNames = {"ID", "Fitness", "Chromozom"};
    protected ArrayList parents;

    public OffspringTableModel(ArrayList parents) {
        super();
        this.parents = parents;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public int getRowCount() {
        return parents.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        IChromosome c = (IChromosome)parents.get(rowIndex);

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
