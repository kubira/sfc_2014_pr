/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.population;

import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.PermutationChromosome;
import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.ChromosomeType;
import java.util.ArrayList;

public class PermutationChromosomePopulation extends ChromosomePopulation {

    protected ArrayList set;

    public PermutationChromosomePopulation(int size, ArrayList set) {
        super(ChromosomeType.PERMUTATION, size);
        this.set = set;
    }

    public PermutationChromosomePopulation(ChromosomePopulation p) {
        super(p);
        this.set = (ArrayList)((PermutationChromosomePopulation)p).set.clone();
    }

    @Override
    public void generate() {
        generate(set.size());
    }

    @Override
    public void generate(int length) {
        for(int rank = 0; rank < size; rank++) {
            PermutationChromosome p = new PermutationChromosome(set);
            p.generate(length);
            population.add(p);
        }
    }

    @Override
    public ChromosomePopulation clonePopulation() {
        return (new PermutationChromosomePopulation(this));
    }

}
