package com

import com.graph._
import com.expTree._
import swing._

/*object App extends SimpleSwingApplication{

    //val mutator    = new GaussMutate(0.025f)
    //val crosser    = new ArithmeticCrossover(1.0f)
    //val problems   = List(Sphere,Schwefel,Rosenbrock,Rastrigin,Ackley,Griewangk)
    val problems   = List(Rosenbrock,Rastrigin,Schwefel)
    val optimizers = List(new GA(popSize = 500, genMax = 4000, tournamentSize=1))

    val runs = for(p <- problems; optimizer <- optimizers) yield {
        val scale   = (p.max - p.min)/200.0
        val problem = p(new SelectiveMutate(0.01f, scale.toFloat),
                        new TwoPointCrossover())
        val (averages, best) = optimizer(problem)
        new ArrayDataSource(optimizer+": "+problem+" Average", averages)
    }

    class ArrayDataSource(name: String, data:Seq[Double]) extends DataSource{
        val getName = name
        def get(X: Double): Double = {
            val index :Int = (X*data.size.toDouble).toInt
            data(index).toDouble
        }
    }
    def top = new MainFrame{
        title = "Optimizer"
        val aL = new java.util.ArrayList[DataSource]()
        for(ds <- runs) aL.add(ds)
        contents = Component.wrap(new Graph(aL, true))
    }
}
*/

object App {
    def main(args: Array[String]) {
        val testDS = new DataSet{
            val range = 10.0
            val vectorLen = 3
            val data: Seq[(Seq[Double],Double)] = List(
                (Array(0.0,1.0,1.0),0.0),
                (Array(1.0,1.0,1.0),2.0),
                (Array(2.0,1.0,1.0),4.0),
                (Array(3.0,1.0,1.0),6.0),
                (Array(4.0,1.0,1.0),8.0)
            )
        }
        val problem  = RegressionTree(testDS, 3)
        val solver   = new GA(popSize = 50, genMax = 200, tournamentSize=3)
        val solution = solver(problem)

        println(solution)

    }
}

