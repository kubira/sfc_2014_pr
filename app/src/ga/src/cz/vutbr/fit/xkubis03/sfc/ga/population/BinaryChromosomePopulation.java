/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.population;

import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.BinaryChromosome;
import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.ChromosomeType;

public class BinaryChromosomePopulation extends ChromosomePopulation {

    public BinaryChromosomePopulation(int size) {
        super(ChromosomeType.BINARY, size);
    }

    public BinaryChromosomePopulation(ChromosomePopulation p) {
        super(p);
    }

    @Override
    public void generate(int length) {
        for(int rank = 0; rank < size; rank++) {
            BinaryChromosome b = new BinaryChromosome();
            b.generate(length);
            population.add(b);
        }
    }

    @Override
    public ChromosomePopulation clonePopulation() {
        return (new BinaryChromosomePopulation(this));
    }

}
