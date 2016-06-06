/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.gui;

import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.ChromosomeType;
import cz.vutbr.fit.xkubis03.sfc.ga.crossover.ChromosomeCrossoverType;
import cz.vutbr.fit.xkubis03.sfc.ga.util.Variables;
import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

public class CrossoverTypeComboBoxModel extends AbstractListModel implements ComboBoxModel {

    protected ArrayList options;
    protected CrossoverTypeComboBoxObject selection;

    public CrossoverTypeComboBoxModel() {
        options = new ArrayList();
    }

    public void setOptions() {
        setOptions(Variables.getPopulation().getChromosomeType());
    }

    public void setOptions(ChromosomeType cType) {
        options.clear();

        switch(cType) {
            case BINARY: {
                options.add(new CrossoverTypeComboBoxObject("Jednobodové", ChromosomeCrossoverType.SINGLE_POINT));
                options.add(new CrossoverTypeComboBoxObject("Vícebodové", ChromosomeCrossoverType.MULTI_POINT));
                options.add(new CrossoverTypeComboBoxObject("Uniformní", ChromosomeCrossoverType.UNIFORM));
                break;
            }
            case INTEGER: {
                options.add(new CrossoverTypeComboBoxObject("Jednobodové", ChromosomeCrossoverType.SINGLE_POINT));
                options.add(new CrossoverTypeComboBoxObject("Vícebodové", ChromosomeCrossoverType.MULTI_POINT));
                options.add(new CrossoverTypeComboBoxObject("Uniformní", ChromosomeCrossoverType.UNIFORM));
                break;
            }
            case DOUBLE: {
                options.add(new CrossoverTypeComboBoxObject("Jednobodové", ChromosomeCrossoverType.SINGLE_POINT));
                options.add(new CrossoverTypeComboBoxObject("Vícebodové", ChromosomeCrossoverType.MULTI_POINT));
                options.add(new CrossoverTypeComboBoxObject("Uniformní", ChromosomeCrossoverType.UNIFORM));
                options.add(new CrossoverTypeComboBoxObject("Aritmetické", ChromosomeCrossoverType.ARITHMETIC));
                options.add(new CrossoverTypeComboBoxObject("Heuristické", ChromosomeCrossoverType.HEURISTIC));
                break;
            }
            case STRING: {
                options.add(new CrossoverTypeComboBoxObject("Jednobodové", ChromosomeCrossoverType.SINGLE_POINT));
                options.add(new CrossoverTypeComboBoxObject("Vícebodové", ChromosomeCrossoverType.MULTI_POINT));
                options.add(new CrossoverTypeComboBoxObject("Uniformní", ChromosomeCrossoverType.UNIFORM));
                break;
            }
            case PERMUTATION: {
                options.add(new CrossoverTypeComboBoxObject("Indexová tabulka", ChromosomeCrossoverType.INDEX_TABLE));
                options.add(new CrossoverTypeComboBoxObject("PMX", ChromosomeCrossoverType.PMX));
                break;
            }
        }

        selection = (CrossoverTypeComboBoxObject)options.get(0);
    }

    @Override
    public int getSize() {
        return options.size();
    }

    @Override
    public Object getElementAt(int index) {
        return options.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selection = (CrossoverTypeComboBoxObject)anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selection;
    }
}
