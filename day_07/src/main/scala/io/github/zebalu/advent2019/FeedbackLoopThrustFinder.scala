package io.github.zebalu.advent2019

class FeedbackLoopThrustFinder(code: Array[Int]) {

  val execute: (Int, Int, Int, Int, Int) => Int = (phaseA: Int, phaseB: Int, phaseC: Int, phaseD: Int, phaseE: Int) => {
    val steps = Array(0, 0, 0, 0, 0)
    val thrusters = Array(
      new FeedableIntComputer(code.clone(), phaseA),
      new FeedableIntComputer(code.clone(), phaseB),
      new FeedableIntComputer(code.clone(), phaseC),
      new FeedableIntComputer(code.clone(), phaseD),
      new FeedableIntComputer(code.clone(), phaseE))
    thrusters(0).setDefaultAnser(_ => {
      steps(0) match {
        case 0 => {
          steps(0) += 1
          0
        }
        case _ => {
          steps(0) += 1
          thrusters(4).getOutput(steps(0) - 2)
        }
      }
    })
    for (i <- 1.to(4)) {
      thrusters(i).setDefaultAnser(_ => {
        steps(i) += 1
        thrusters(i - 1).getOutput(steps(i) - 1)
      })
    }
    while (!thrusters(0).isFinished()) {
      for (i <- 0.to(4)) {
        thrusters(i).execute()
      }
      for (i <- 0.to(4)) {
        thrusters(i).increaseMaxRead()
      }
    }
    thrusters(4).getDiagnosticCode()
  }

  val highestCombination = {
    List(5, 6, 7, 8, 9).permutations.map(l => execute(l(0), l(1), l(2), l(3), l(4))).max
  }
}