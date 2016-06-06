/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.mutation;

import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.BinaryChromosome;
import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.DoubleChromosome;
import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.IntegerChromosome;
import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.PermutationChromosome;
import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.StringChromosome;
import cz.vutbr.fit.xkubis03.sfc.ga.util.Generator;

public class ChromosomeMutation {

    private Generator generator = null;
    private double threshold = 0.0;

    public ChromosomeMutation(double threshold) {
        generator = new Generator();
        this.threshold = threshold;
    }

    public BinaryChromosome mutate(BinaryChromosome c) {
        int mutationIndex = (int)generator.getUniform(0, c.getChromosome().size());

        return mutate(c, mutationIndex);
    }

    public BinaryChromosome mutate(BinaryChromosome c, int mutationIndex) {
        if(generator.getUniform() < threshold) {
            // Mutate input chromosome
            BinaryChromosome mutated = new BinaryChromosome(c);

            if((int)mutated.getChromosome().get(mutationIndex) == 0) {
                mutated.getChromosome().remove(mutationIndex);
                mutated.getChromosome().add(mutationIndex, 1);
            } else {
                mutated.getChromosome().remove(mutationIndex);
                mutated.getChromosome().add(mutationIndex, 0);
            }

            return mutated;
        }

        return c;
    }

    public IntegerChromosome mutate(IntegerChromosome c) {
        int mutationIndex = (int)generator.getUniform(0, c.getChromosome().size());

        return mutate(c, mutationIndex);
    }

    public IntegerChromosome mutate(IntegerChromosome c, int mutationIndex) {
        if(generator.getUniform() < threshold) {
            // Mutate input chromosome
            IntegerChromosome mutated = new IntegerChromosome(c);

            int newValue = (int)generator.getUniform(c.getMin(), c.getMax());

            mutated.getChromosome().remove(mutationIndex);
            mutated.getChromosome().add(mutationIndex, newValue);

            return mutated;
        }

        return c;
    }

    public DoubleChromosome mutate(DoubleChromosome c) {
        int mutationIndex = (int)generator.getUniform(0, c.getChromosome().size());

        return mutate(c, mutationIndex);
    }

    public DoubleChromosome mutate(DoubleChromosome c, int mutationIndex) {
        if(generator.getUniform() < threshold) {
            // Mutate input chromosome
            DoubleChromosome mutated = new DoubleChromosome(c);

            double oldValue = (double)mutated.getChromosome().get(mutationIndex);
            double newValue = oldValue;
            double sign = (generator.getUniform() < 0.5) ? -1.0 : 1.0;
            double change = generator.getUniform();

            while(change > 0.1) {
                change = generator.getUniform();
            }

            newValue += oldValue * sign * change;

            mutated.getChromosome().remove(mutationIndex);
            mutated.getChromosome().add(mutationIndex, newValue);

            return mutated;
        }

        return c;
    }

    public StringChromosome mutate(StringChromosome c) {
        int mutationIndex = (int)generator.getUniform(0, c.getChromosome().size());

        return mutate(c, mutationIndex);
    }

    public StringChromosome mutate(StringChromosome c, int mutationIndex) {
        if(generator.getUniform() < threshold) {
            // Mutate input chromosome
            StringChromosome mutated = new StringChromosome(c);

            int replacementIndex = (int)generator.getUniform(0, mutated.getSet().size());
            String newValue = (String)mutated.getSet().get(replacementIndex);

            mutated.getChromosome().remove(mutationIndex);
            mutated.getChromosome().add(mutationIndex, newValue);

            return mutated;
        }

        return c;
    }

    public PermutationChromosome mutate(PermutationChromosome c) {
        int index1 = (int)generator.getUniform(0, c.getChromosome().size());
        int index2 = (int)generator.getUniform(0, c.getChromosome().size());

        while(index1 == index2) {
            index2 = (int)generator.getUniform(0, c.getChromosome().size());
        }

        return mutate(c, index1, index2);
    }

    public PermutationChromosome mutate(PermutationChromosome c, int index1, int index2) {
        if(generator.getUniform() < threshold) {
            PermutationChromosome mutated = new PermutationChromosome(c);

            mutated.getChromosome().remove(index1);
            mutated.getChromosome().add(index1, c.getChromosome().get(index2));

            mutated.getChromosome().remove(index2);
            mutated.getChromosome().add(index2, c.getChromosome().get(index1));

            return mutated;
        }

        return c;
    }
}
