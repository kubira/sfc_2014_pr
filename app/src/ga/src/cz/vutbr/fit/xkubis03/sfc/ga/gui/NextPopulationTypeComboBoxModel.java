/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.gui;

import cz.vutbr.fit.xkubis03.sfc.ga.population.ChromosomeNextPopulationType;
import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

public class NextPopulationTypeComboBoxModel extends AbstractListModel implements ComboBoxModel {

    protected ArrayList options;
    protected NextPopulationTypeComboBoxObject selection;

    public NextPopulationTypeComboBoxModel() {
        options = new ArrayList();
        options.add(new NextPopulationTypeComboBoxObject("Generační", ChromosomeNextPopulationType.GENERATIONAL));
        options.add(new NextPopulationTypeComboBoxObject("Inkrementální", ChromosomeNextPopulationType.INCREMENTAL));
        options.add(new NextPopulationTypeComboBoxObject("Úplná náhrada", ChromosomeNextPopulationType.PURE_REINSERTION));
        options.add(new NextPopulationTypeComboBoxObject("Uniformní náhrada", ChromosomeNextPopulationType.UNIFORM_REINSERTION));
        options.add(new NextPopulationTypeComboBoxObject("Elitní náhrada", ChromosomeNextPopulationType.ELITIST_REINSERTION));
        options.add(new NextPopulationTypeComboBoxObject("Turnajová náhrada", ChromosomeNextPopulationType.TOURNAMENT_REINSERTION));

        selection = (NextPopulationTypeComboBoxObject)options.get(0);
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
        selection = (NextPopulationTypeComboBoxObject)anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selection;
    }
}
