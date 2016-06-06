/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.gui;

import cz.vutbr.fit.xkubis03.sfc.ga.crossover.ChromosomeCrossoverType;

public class CrossoverTypeComboBoxObject {

    protected String label;
    protected ChromosomeCrossoverType coType;

    public CrossoverTypeComboBoxObject(String label, ChromosomeCrossoverType coType) {
        this.label = label;
        this.coType = coType;
    }

    public String getLabel() {
        return label;
    }

    public ChromosomeCrossoverType getCoType() {
        return coType;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setcType(ChromosomeCrossoverType coType) {
        this.coType = coType;
    }

    @Override
    public String toString() {
        return label;
    }
}
