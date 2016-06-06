/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.population;

import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.DoubleChromosome;
import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.ChromosomeType;

public class DoubleChromosomePopulation extends ChromosomePopulation {

    protected double min;
    protected double max;

    public DoubleChromosomePopulation(int size, double min, double max) {
        super(ChromosomeType.DOUBLE, size);
        this.min = min;
        this.max = max;
    }

    public DoubleChromosomePopulation(ChromosomePopulation p) {
        super(p);
        this.min = ((DoubleChromosomePopulation)p).min;
        this.max = ((DoubleChromosomePopulation)p).max;
    }

    @Override
    public void generate(int length) {
        for(int rank = 0; rank < size; rank++) {
            DoubleChromosome d = new DoubleChromosome(min, max);
            d.generate(length);
            population.add(d);
        }
    }

    @Override
    public ChromosomePopulation clonePopulation() {
        return (new DoubleChromosomePopulation(this));
    }

}
