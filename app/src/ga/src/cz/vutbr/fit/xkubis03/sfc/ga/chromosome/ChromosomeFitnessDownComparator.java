/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.chromosome;

import java.util.Comparator;

public class ChromosomeFitnessDownComparator implements Comparator<IChromosome>{

    @Override
    public int compare(IChromosome o1, IChromosome o2) {
        return o1.getFitness() < o2.getFitness() ? 1 : o1.getFitness() == o2.getFitness() ? 0 : -1;
    }

}
