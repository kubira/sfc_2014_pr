/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.gui;

import cz.vutbr.fit.xkubis03.sfc.ga.population.ChromosomeNextPopulationType;

public class NextPopulationTypeComboBoxObject {

    protected String label;
    protected ChromosomeNextPopulationType npType;

    public NextPopulationTypeComboBoxObject(String label, ChromosomeNextPopulationType npType) {
        this.label = label;
        this.npType = npType;
    }

    public String getLabel() {
        return label;
    }

    public ChromosomeNextPopulationType getNpType() {
        return npType;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setNpType(ChromosomeNextPopulationType npType) {
        this.npType = npType;
    }

    @Override
    public String toString() {
        return label;
    }
}
