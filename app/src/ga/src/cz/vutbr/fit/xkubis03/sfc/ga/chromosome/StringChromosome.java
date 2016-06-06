/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.chromosome;

import java.util.ArrayList;
import java.util.Iterator;

public class StringChromosome extends Chromosome implements IChromosome {

    protected ArrayList set = null;

    public StringChromosome(ArrayList set) {
        super();
        this.set = set;
    }

    public StringChromosome(StringChromosome c) {
        super(c);
        set = new ArrayList();

        Iterator it = c.set.iterator();

        while(it.hasNext()) {
            set.add(it.next());
        }
    }

    @Override
    public void generate(int length) {
        int nextIndex;

        while(chromosome.size() < length) {
            nextIndex = (int)generator.getUniform(0, set.size());

            chromosome.add(set.get(nextIndex));
        }
    }

    @Override
    public void generate() {
        generate(set.size());
    }

    public ArrayList getSet() {
        return set;
    }

    @Override
    public IChromosome cloneChromosome() {
        return (new StringChromosome(this));
    }
}
