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