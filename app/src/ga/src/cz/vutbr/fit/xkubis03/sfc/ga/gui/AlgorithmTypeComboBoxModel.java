/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.gui;

import cz.vutbr.fit.xkubis03.sfc.ga.selection.ParentSelectionType;
import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

public class AlgorithmTypeComboBoxModel extends AbstractListModel implements ComboBoxModel {

    protected ArrayList options;
    protected AlgorithmTypeComboBoxObject selection;

    public AlgorithmTypeComboBoxModel() {
        options = new ArrayList();
        options.add(new AlgorithmTypeComboBoxObject("Ruleta", ParentSelectionType.ROULETTE));
        options.add(new AlgorithmTypeComboBoxObject("Stochastický", ParentSelectionType.STOCHASTIC));
        options.add(new AlgorithmTypeComboBoxObject("Elitismus", ParentSelectionType.ELITE));
        options.add(new AlgorithmTypeComboBoxObject("Turnaj", ParentSelectionType.TOURNAMENT));

        selection = (AlgorithmTypeComboBoxObject)options.get(0);
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
        selection = (AlgorithmTypeComboBoxObject)anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selection;
    }
}
