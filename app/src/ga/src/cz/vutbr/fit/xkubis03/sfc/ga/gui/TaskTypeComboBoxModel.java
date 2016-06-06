/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.gui;

import cz.vutbr.fit.xkubis03.sfc.ga.task.TaskType;
import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

public class TaskTypeComboBoxModel extends AbstractListModel implements ComboBoxModel {

    protected ArrayList options;
    protected TaskTypeComboBoxObject selection;

    public TaskTypeComboBoxModel() {
        options = new ArrayList();
        options.add(new TaskTypeComboBoxObject("OneMax", TaskType.ONE_MAX));

        selection = (TaskTypeComboBoxObject)options.get(0);
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
        selection = (TaskTypeComboBoxObject)anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selection;
    }
}
