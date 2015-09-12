package com

import scala.util.Random

class Annealing(
    trials: Int,
    Tmax: Float)
  extends Optimizer{
    val rand = new Random()

    def apply[T <: Solution[T]] (gen: ()=>T): (Seq[Double], T) = {
        def bypass(d: Double, i: Int): Boolean = {
            val temp = Tmax * (trials - i).toFloat / trials.toFloat
            val prob = Math.exp(-d/temp)
            val rnd = rand.nextFloat()
            return rand.nextFloat() < prob;
        }
        val scores = Array.ofDim[Double](trials)
        var sol = gen()
        for(i <- 0 until trials) {
            val next = sol.mutate()
            val diff = next.fitness - sol.fitness
            if(diff < 0 || bypass(diff, i))
                sol = next
            scores(i) = sol.fitness
        }
        (scores, sol)
    }
}
