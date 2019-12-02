package io.github.zebalu.advent2019

class IntComputerDebugger(initialCode: Array[Int], expected: Int) {
  
  private var noun = 0
  private var verb = 0
  private var found = false
  
  def find(): Int = {
    while(!found && noun < 100) {
      verb = 0
      while(!found && verb < 100) {
        val code = initialCode.clone
        code(1)=noun
        code(2)=verb
        new IntComputer(code).execute
        found = code(0) == expected
        if(!found) {
          verb += 1
        }
      }
      if(!found) {
        noun += 1
      }
    }
    100*noun+verb
  }
  
}