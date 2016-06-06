/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.gui;

import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.StringChromosome;
import cz.vutbr.fit.xkubis03.sfc.ga.util.Variables;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

public class StringSetComboBoxModel extends AbstractListModel implements ComboBoxModel {

    protected ArrayList options;
    protected StringSetComboBoxObject selection;

    public StringSetComboBoxModel() {
        options = new ArrayList();
    }

    public void setOptions() {
        ArrayList set = ((StringChromosome)Variables.getPopulation().getPopulation().get(0)).getSet();

        Iterator it = set.iterator();
        while(it.hasNext()) {
            options.add(new StringSetComboBoxObject((String)it.next()));
        }

        selection = (StringSetComboBoxObject)options.get(0);
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
        selection = (StringSetComboBoxObject)anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selection;
    }
}
