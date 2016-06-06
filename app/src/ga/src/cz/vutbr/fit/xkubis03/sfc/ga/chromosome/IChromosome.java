/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.chromosome;

import java.util.ArrayList;

public interface IChromosome {

    public void generate();

    public void generate(int length);

    public ArrayList getChromosome();

    public double getFitness();

    public void setCompareFitness(double compareFitness);

    public double getCompareFitness();

    public IChromosome cloneChromosome();

}
