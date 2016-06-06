/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.gui;

import cz.vutbr.fit.xkubis03.sfc.ga.population.FitnessConversionType;
import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

public class FitnessConversionTypeComboBoxModel extends AbstractListModel implements ComboBoxModel {

    protected ArrayList options;
    protected FitnessConversionTypeComboBoxObject selection;

    public FitnessConversionTypeComboBoxModel() {
        options = new ArrayList();
        options.add(new FitnessConversionTypeComboBoxObject("Žádný", FitnessConversionType.NONE));
        options.add(new FitnessConversionTypeComboBoxObject("Proporční", FitnessConversionType.PROPORTIONAL));
        options.add(new FitnessConversionTypeComboBoxObject("Podle pořadí", FitnessConversionType.RANK_BASED));

        selection = (FitnessConversionTypeComboBoxObject)options.get(0);
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
        selection = (FitnessConversionTypeComboBoxObject)anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selection;
    }
}
