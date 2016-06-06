/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.chromosome;

import java.util.ArrayList;
import java.util.Iterator;

public class PermutationChromosome extends Chromosome implements IChromosome {

    protected ArrayList set = null;

    public PermutationChromosome(ArrayList set) {
        super();
        this.set = set;
    }

    public PermutationChromosome(PermutationChromosome c) {
        super(c);
        set = new ArrayList();

        Iterator it = c.set.iterator();

        while(it.hasNext()) {
            set.add(it.next());
        }
    }

    @Override
    public void generate() {
        generate(set.size());
    }

    @Override
    public void generate(int length) {
        chromosome.clear();

        if(length > set.size()) {
            System.out.format("ERROR: Required length (%d) is greater than size of set (%d).\n", length, set.size());
        } else {
            ArrayList lst = new ArrayList();
            int nextIndex;

            while(lst.size() < length) {
                nextIndex = (int)generator.getUniform(0, length);

                if(nextIndex > length) {
                } else if(lst.indexOf(set.get(nextIndex)) < 0) {
                    lst.add(set.get(nextIndex));
                    chromosome.add(set.get(nextIndex));
                }
            }
        }
    }

    public ArrayList getSet() {
        return set;
    }

    @Override
    public IChromosome cloneChromosome() {
        return (new PermutationChromosome(this));
    }
}
