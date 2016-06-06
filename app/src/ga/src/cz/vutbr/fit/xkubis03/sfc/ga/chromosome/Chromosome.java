/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.chromosome;

import cz.vutbr.fit.xkubis03.sfc.ga.util.Constants;
import cz.vutbr.fit.xkubis03.sfc.ga.util.Generator;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class Chromosome implements IChromosome {

    protected ArrayList chromosome = null;
    protected Generator generator = null;
    protected double fitness = 0.0;
    protected double compareFitness = 0.0;

    public Chromosome() {
        generator = new Generator();
        chromosome = new ArrayList();
    };

    public Chromosome(IChromosome c) {
        generator = new Generator();
        chromosome = new ArrayList();
        fitness = c.getFitness();
        compareFitness = c.getCompareFitness();

        Iterator it = c.getChromosome().iterator();

        while(it.hasNext()) {
            chromosome.add(it.next());
        }
    }

    @Override
    public ArrayList getChromosome() {
        return chromosome;
    }

    @Override
    public String toString() {
        Iterator it = chromosome.iterator();

        String str = "[";

        while(it.hasNext()) {
            str += it.next().toString();
            if(it.hasNext()) {
                str += " ";
            }
        }

        str += "]";

        return str;
    }

    @Override
    public double getFitness() {
        return fitness;
    }

    @Override
    public double getCompareFitness() {
        return compareFitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    @Override
    public void setCompareFitness(double compareFitness) {
        this.compareFitness = compareFitness;
    }

    @Override
    public void generate() {
        generate(Constants.DEFAULT_LENGTH);
    }
}
