/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.gui;

import cz.vutbr.fit.xkubis03.sfc.ga.population.FitnessConversionType;

public class FitnessConversionTypeComboBoxObject {

    protected String label;
    protected FitnessConversionType fcType;

    public FitnessConversionTypeComboBoxObject(String label, FitnessConversionType fcType) {
        this.label = label;
        this.fcType = fcType;
    }

    public String getLabel() {
        return label;
    }

    public FitnessConversionType getFcType() {
        return fcType;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setFcType(FitnessConversionType fcType) {
        this.fcType = fcType;
    }

    @Override
    public String toString() {
        return label;
    }
}
