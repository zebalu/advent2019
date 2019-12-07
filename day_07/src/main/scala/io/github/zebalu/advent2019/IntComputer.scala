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

import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn

class IntComputer(code: Array[Int], phase: Int, defaultAnswer: Int = Integer.MIN_VALUE) {
  private var steps = -1;
  private var ip = 0
  private var finished = false

  private var readCount = 0;

  private var lastPrint = -1

  def getDiagnosticCode(): Int = lastPrint

  def execute(): Unit = {
    while (!finished) {
      steps = steps + 1
      val start = ip
      val opCode = getOpCode(code(start))
      if (opCode(0) == 99) {
        run(opCode, Array())
        ip += 1
      } else if (opCode(0) == 3 || opCode(0) == 4) {
        run(opCode, Array(code(start + 1)))
        ip += 2
      } else if (opCode(0) == 5 || opCode(0) == 6) {
        ip += 3
        run(opCode, Array(code(start + 1), code(start + 2)))
      } else {
        run(opCode, Array(code(start + 1), code(start + 2), code(start + 3)))
        ip += 4
      }
    }
    println("code(0)\t:=\t" + code(0))
  }

  def run(opCode: Array[Int], instruction: Array[Int]): Unit = {
    if (opCode(0) == 1) {
      val v1 = valueByOpcode(opCode(1), instruction(0))
      val v2 = valueByOpcode(opCode(2), instruction(1))
      code(instruction(2)) = v1 + v2
    } else if (opCode(0) == 2) {
      val v1 = valueByOpcode(opCode(1), instruction(0))
      val v2 = valueByOpcode(opCode(2), instruction(1))
      code(instruction(2)) = v1 * v2
    } else if (opCode(0) == 3) {
      val v = defaultAnswer match {
        case Integer.MIN_VALUE => StdIn.readInt()
        case _ => readCount match {
          case 0 => {
            readCount += 1
            phase
          }
          case _ => {
            readCount += 1
            defaultAnswer
          }
        }
      }
      code(instruction(0)) = v
    } else if (opCode(0) == 4) {
      lastPrint = valueByOpcode(opCode(1), instruction(0))
      println(lastPrint)
    } else if (opCode(0) == 5) {
      if (valueByOpcode(opCode(1), instruction(0)) != 0) {
        ip = valueByOpcode(opCode(2), instruction(1))
      }
    } else if (opCode(0) == 6) {
      if (valueByOpcode(opCode(1), instruction(0)) == 0) {
        ip = valueByOpcode(opCode(2), instruction(1))
      }
    } else if (opCode(0) == 7) {
      if (valueByOpcode(opCode(1), instruction(0)) < valueByOpcode(opCode(2), instruction(1))) {
        code(instruction(2)) = 1
      } else {
        code(instruction(2)) = 0
      }
    } else if (opCode(0) == 8) {
      if (valueByOpcode(opCode(1), instruction(0)) == valueByOpcode(opCode(2), instruction(1))) {
        code(instruction(2)) = 1
      } else {
        code(instruction(2)) = 0
      }
    } else if (opCode(0) == 99) {
      println("halt: " + code(0))
      finished = true
    }
  }

  private val getOpCode: Int => Array[Int] = (code: Int) => {
    var buffer = new ArrayBuffer[Int]
    buffer += code % 100
    var num = code / 100
    while (num > 0) {
      buffer += num % 10
      num = num / 10
    }
    while (buffer.size < 4) {
      buffer += 0
    }
    buffer.toArray
  }

  private val valueByOpcode: (Int, Int) => Int = (opCode: Int, value: Int) => {
    opCode match {
      case 1 => value
      case _ => code(value)
    }
  }
}