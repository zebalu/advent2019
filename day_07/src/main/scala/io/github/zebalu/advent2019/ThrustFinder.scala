package io.github.zebalu.advent2019

class ThrustFinder(code: Array[Int]) {

  val execute: (Int, Int, Int, Int, Int) => Int = (phaseA: Int, phaseB: Int, phaseC: Int, phaseD: Int, phaseE: Int) => {
    val thrusterA = new IntComputer(code.clone(), phaseA, 0)
    thrusterA.execute()
    val thrusterB = new IntComputer(code.clone(), phaseB, thrusterA.getDiagnosticCode())
    thrusterB.execute()
    val thrusterC = new IntComputer(code.clone(), phaseC, thrusterB.getDiagnosticCode())
    thrusterC.execute()
    val thrusterD = new IntComputer(code.clone(), phaseD, thrusterC.getDiagnosticCode())
    thrusterD.execute()
    val thrusterE = new IntComputer(code.clone(), phaseE, thrusterD.getDiagnosticCode())
    thrusterE.execute()
    thrusterE.getDiagnosticCode()
  }
  
  val highestCombination = {
    List(0,1,2,3,4).permutations.map(l => execute(l(0), l(1), l(2), l(3), l(4))).max
  }

}