/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.util;

import java.util.Random;

public class Generator {

    private Random generator = null;

    public Generator() {
        generator = new Random();
    }

    public Generator(long seed) {
        generator = new Random(seed);
    }

    public double getUniform() {
        return generator.nextDouble();
    }

    public double getUniform(double min, double max) {
        return ((max - min) * generator.nextDouble()) + min;
    }
}
