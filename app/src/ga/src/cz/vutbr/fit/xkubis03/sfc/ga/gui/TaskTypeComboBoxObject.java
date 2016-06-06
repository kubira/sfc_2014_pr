/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.gui;

import cz.vutbr.fit.xkubis03.sfc.ga.task.TaskType;

public class TaskTypeComboBoxObject {

    protected String label;
    protected TaskType taskType;

    public TaskTypeComboBoxObject(String label, TaskType taskType) {
        this.label = label;
        this.taskType = taskType;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    @Override
    public String toString() {
        return label;
    }
}
