/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.population;

import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.StringChromosome;
import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.ChromosomeType;
import java.util.ArrayList;

public class StringChromosomePopulation extends ChromosomePopulation {

    protected ArrayList set;

    public StringChromosomePopulation(int size, ArrayList set) {
        super(ChromosomeType.STRING, size);
        this.set = set;
    }

    public StringChromosomePopulation(ChromosomePopulation p) {
        super(p);
        this.set = (ArrayList)((StringChromosomePopulation)p).set.clone();
    }

    @Override
    public void generate(int length) {
        for(int rank = 0; rank < size; rank++) {
            StringChromosome s = new StringChromosome(set);
            s.generate(length);
            population.add(s);
        }
    }

    @Override
    public ChromosomePopulation clonePopulation() {
        return (new StringChromosomePopulation(this));
    }

}
