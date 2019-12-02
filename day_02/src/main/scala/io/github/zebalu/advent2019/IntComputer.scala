/*
   Copyright 2019 Balazs Zaicsek

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */


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