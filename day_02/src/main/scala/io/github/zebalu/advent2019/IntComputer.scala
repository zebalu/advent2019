package io.github.zebalu.advent2019

class IntComputer(code: Array[Int]) {
  private var steps = -1;
  private var ip = 0
  private var finished = false
  
  def execute(): Unit = {
    while(!finished) {
      steps = steps + 1
      val start = ip
      if(code(start) == 99) {
        run(Array(code(start)))
        ip += 1
      } else {
        run(Array(code(start), code(start+1), code(start+2), code(start+3)))
        ip += 4
      }
    }
    println("code(0)\t:=\t"+code(0))
  }
  
  def run(instruction: Array[Int]) : Unit = {
    if(instruction(0)==1) {
      code(instruction(3)) = code(instruction(1))+code(instruction(2))
    } else if(instruction(0)==2) {
      code(instruction(3)) = code(instruction(1))*code(instruction(2))
    } else if(instruction(0)==99) {
      println ("halt: "+code(0))
      finished = true
    }
  }
}