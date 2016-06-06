/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.gui;

import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.ChromosomeType;
import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

public class ChromosomeTypeComboBoxModel extends AbstractListModel implements ComboBoxModel {

    protected ArrayList options;
    protected ChromosomeTypeComboBoxObject selection;

    public ChromosomeTypeComboBoxModel() {
        options = new ArrayList();
        options.add(new ChromosomeTypeComboBoxObject("Binární", ChromosomeType.BINARY));
        options.add(new ChromosomeTypeComboBoxObject("Celá čísla", ChromosomeType.INTEGER));
        options.add(new ChromosomeTypeComboBoxObject("Reálná čísla", ChromosomeType.DOUBLE));
        options.add(new ChromosomeTypeComboBoxObject("Řetězcový", ChromosomeType.STRING));
        options.add(new ChromosomeTypeComboBoxObject("Permutační", ChromosomeType.PERMUTATION));

        selection = (ChromosomeTypeComboBoxObject)options.get(0);
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
        selection = (ChromosomeTypeComboBoxObject)anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selection;
    }
}
