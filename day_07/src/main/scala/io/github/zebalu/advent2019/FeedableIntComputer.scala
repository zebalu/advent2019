package io.github.zebalu.advent2019

import scala.collection.mutable.ArrayBuffer

class FeedableIntComputer(code: Array[Int], phase: Int) {
  private var steps = -1;
  private var ip = 0
  private var finished = false
  private var shouldWait = false

  private var readCount = 0;

  private var outputLog: ArrayBuffer[Int] = ArrayBuffer.empty
  private var lastPrint = -1

  private var defaultAnswer: Unit => Int = null

  private var maxRead = 1

  def setDefaultAnser(provider: Unit => Int): Unit = defaultAnswer = provider

  def getDiagnosticCode(): Int = lastPrint
  def getOutput(idx: Int): Int = outputLog(idx)
  def isFinished(): Boolean = finished

  def execute(): Unit = {
    while (!finished && !shouldWait) {
      steps = steps + 1
      val start = ip
      val opCode = getOpCode(code(start))
      if (opCode(0) == 3 && maxRead < readCount) {
        shouldWait = true
      } else {
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
    }
  }

  def increaseMaxRead(): Unit = {
    maxRead += 1
    shouldWait = false
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
      val v = readCount match {
        case 0 => {
          readCount += 1
          phase
        }
        case _ => {
          readCount += 1
          defaultAnswer.apply(())
        }
      }
      code(instruction(0)) = v
    } else if (opCode(0) == 4) {
      lastPrint = valueByOpcode(opCode(1), instruction(0))
      outputLog += lastPrint
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