/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.gui;

import cz.vutbr.fit.xkubis03.sfc.ga.selection.ParentSelectionType;

public class AlgorithmTypeComboBoxObject {

    protected String label;
    protected ParentSelectionType psType;

    public AlgorithmTypeComboBoxObject(String label, ParentSelectionType psType) {
        this.label = label;
        this.psType = psType;
    }

    public String getLabel() {
        return label;
    }

    public ParentSelectionType getpsType() {
        return psType;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setpsType(ParentSelectionType psType) {
        this.psType = psType;
    }

    @Override
    public String toString() {
        return label;
    }
}
