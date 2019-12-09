package io.github.zebalu.advent2019

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.Map

class FeedableIntComputer(codeInput: Array[Long]) {
  private val code = {
    var buffer = Map.empty[Long, Long]
    for(i <- 0.to(codeInput.size-1)) {
      buffer+=(i.toLong->codeInput(i))
    }
    buffer
  }
  private var steps = -1;
  private var ip = 0L
  private var relativisticBase = 0L
  private var finished = false
  private var shouldWait = false

  private var readCount = 0;

  private var outputLog: ArrayBuffer[Long] = ArrayBuffer.empty
  private var lastPrint = -1L

  private var defaultAnswer: Unit => Int = null

  private var maxRead = 1

  def setDefaultAnser(provider: Unit => Int): Unit = defaultAnswer = provider

  def getDiagnosticCode(): Long = lastPrint
  def getOutput(idx: Int): Long = outputLog(idx)
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
        } else if (opCode(0) == 3 || opCode(0) == 4 || opCode(0) == 9) {
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

  def run(opCode: Array[Int], instruction: Array[Long]): Unit = {
    if (opCode(0) == 1) {
      val v1 = valueByOpcode(opCode(1), instruction(0))
      val v2 = valueByOpcode(opCode(2), instruction(1))
      storeByOpcode(opCode(3), instruction(2), v1+v2)
    } else if (opCode(0) == 2) {
      val v1 = valueByOpcode(opCode(1), instruction(0))
      val v2 = valueByOpcode(opCode(2), instruction(1))
      storeByOpcode(opCode(3), instruction(2), v1*v2)
    } else if (opCode(0) == 3) {
      readCount += 1
      val v = defaultAnswer.apply(())
      storeByOpcode(opCode(1), instruction(0), v)
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
        storeByOpcode(opCode(3), instruction(2), 1)
      } else {
        storeByOpcode(opCode(3), instruction(2), 0)
      }
    } else if (opCode(0) == 8) {
      if (valueByOpcode(opCode(1), instruction(0)) == valueByOpcode(opCode(2), instruction(1))) {
        storeByOpcode(opCode(3), instruction(2), 1)
      } else {
        storeByOpcode(opCode(3), instruction(2), 0)
      }
    } else if(opCode(0) == 9) {
      relativisticBase += valueByOpcode(opCode(1), instruction(0))
    } else if (opCode(0) == 99) {
      finished = true
    }
  }

  private val getOpCode: Long => Array[Int] = (code: Long) => {
    var buffer = new ArrayBuffer[Int]
    buffer += (code % 100).toInt
    var num = code / 100
    while (num > 0) {
      buffer += (num % 10).toInt
      num = num / 10
    }
    while (buffer.size < 4) {
      buffer += 0
    }
    buffer.toArray
  }

  private def valueByOpcode(opCode: Int, value: Long) :Long = {
    opCode match {
      case 1 => value
      case 0 => code.getOrElse(value, 0L)
      case _ => code.getOrElse(relativisticBase+value, 0L)
    }
  }
  
  private def storeByOpcode(opCode: Int, key: Long, value: Long) :Unit = {
    opCode match {
      case 2 => code.put(key+relativisticBase, value)
      case _ => code.put(key, value)
    }
  }
  
}