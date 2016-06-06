/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.chromosome;

public class BinaryChromosome extends Chromosome implements IChromosome {

    public BinaryChromosome() {
        super();
    }

    public BinaryChromosome(BinaryChromosome c) {
        super(c);
    }

    @Override
    public void generate(int length) {
        for(int index = 0; index < length; index++) {
            if(generator.getUniform() < 0.5) {
                chromosome.add(0);
            } else {
                chromosome.add(1);
            }
        }
    }

    @Override
    public IChromosome cloneChromosome() {
        return (new BinaryChromosome(this));
    }
}
