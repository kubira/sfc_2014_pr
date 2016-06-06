/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.gui;

import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.ChromosomeType;

public class ChromosomeTypeComboBoxObject {

    protected String label;
    protected ChromosomeType cType;

    public ChromosomeTypeComboBoxObject(String label, ChromosomeType cType) {
        this.label = label;
        this.cType = cType;
    }

    public String getLabel() {
        return label;
    }

    public ChromosomeType getcType() {
        return cType;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setcType(ChromosomeType cType) {
        this.cType = cType;
    }

    @Override
    public String toString() {
        return label;
    }
}
