/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.chromosome;

public class IntegerChromosome extends Chromosome implements IChromosome {

    protected int min = 0;
    protected int max = 0;

    public IntegerChromosome() {
        super();
    }

    public IntegerChromosome(IntegerChromosome c) {
        super(c);
        this.min = c.min;
        this.max = c.max;
    }

    public IntegerChromosome(int min, int max) {
        super();
        this.min = min;
        this.max = max;
    }

    @Override
    public void generate(int length) {
        int nextValue;

        while(chromosome.size() < length) {
            nextValue = (int)generator.getUniform(min, max+1);

            if(nextValue > max) {
            } else {
                chromosome.add(nextValue);
            }
        }
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    @Override
    public IChromosome cloneChromosome() {
        return (new IntegerChromosome(this));
    }
}
