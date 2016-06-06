/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.chromosome;

import java.util.Iterator;

public class DoubleChromosome extends Chromosome implements IChromosome {

    protected double min = 0.0;
    protected double max = 0.0;

    public DoubleChromosome(double min, double max) {
        super();
        this.min = min;
        this.max = max;
    }

    public DoubleChromosome(DoubleChromosome c) {
        super(c);
        this.min = c.min;
        this.max = c.max;
    }

    @Override
    public String toString() {
        Iterator it = chromosome.iterator();

        String str = "[";

        while(it.hasNext()) {
            str += String.format("%.5f", (double)it.next());
            if(it.hasNext()) {
                str += " ";
            }
        }

        str += "]";

        return str;
    }

    @Override
    public void generate(int length) {
        double nextValue;

        while(chromosome.size() < length) {
            nextValue = generator.getUniform(min, max);

            chromosome.add(nextValue);
        }
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    @Override
    public IChromosome cloneChromosome() {
        return (new DoubleChromosome(this));
    }
}
