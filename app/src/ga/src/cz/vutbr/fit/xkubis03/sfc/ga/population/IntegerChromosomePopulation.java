/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.population;

import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.IntegerChromosome;
import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.ChromosomeType;

public class IntegerChromosomePopulation extends ChromosomePopulation {

    protected int min;
    protected int max;

    public IntegerChromosomePopulation(int size, int min, int max) {
        super(ChromosomeType.INTEGER, size);
        this.min = min;
        this.max = max;
    }

    public IntegerChromosomePopulation(ChromosomePopulation p) {
        super(p);
        this.min = ((IntegerChromosomePopulation)p).min;
        this.max = ((IntegerChromosomePopulation)p).max;
    }

    @Override
    public void generate(int length) {
        for(int rank = 0; rank < size; rank++) {
            IntegerChromosome i = new IntegerChromosome(min, max);
            i.generate(length);
            population.add(i);
        }
    }

    @Override
    public ChromosomePopulation clonePopulation() {
        return (new IntegerChromosomePopulation(this));
    }

}
