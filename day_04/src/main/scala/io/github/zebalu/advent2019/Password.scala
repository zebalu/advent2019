package io.github.zebalu.advent2019

import scala.collection.mutable.ArrayBuffer

class Password(value: Int) {

  private val digits = {
    var buffer = new ArrayBuffer[Int]
    var afterDiv = value
    while (afterDiv > 0) {
      buffer += afterDiv % 10
      afterDiv = afterDiv / 10
    }
    buffer.reverse.toArray
  }

  private val sixDigitLong = digits.length == 6
  private val neverDecrease = {
    var stillOk = true
    for (i <- 0.to(digits.size - 2)) {
      stillOk &= digits(i) <= digits(i + 1)
    }
    stillOk
  }
  private val hasDoubleDigits = {
    var stillOk = false
    for (i <- 0.to(digits.size - 2)) {
      stillOk |= digits(i) == digits(i + 1)
    }
    stillOk
  }

  val isValid = sixDigitLong && neverDecrease && hasDoubleDigits

  private val hasOnlyTwinRepeatingNumbers = {
    if (!isValid) {
      false
    } else {
      /*
      var largestRepeatingNum = -1
      var stillOk = true
      var repeateCount = -1
      for(i <- 1.to(digits.size-1).reverse) {
        if(digits(i) == digits(i-1)) {
          if(largestRepeatingNum<digits(i)) {
            largestRepeatingNum = digits(i)
            repeateCount=2
            stillOk &= true
          } else if(largestRepeatingNum == digits(i)) {
            repeateCount+=1
            stillOk &= repeateCount<3
          }
        }
      }
      if(!stillOk) {
        println(largestRepeatingNum+" "+repeateCount+" "+this)
      }
      stillOk

      */
      var currentDigit = digits(0)
      var appearedCount = 1
      var hasTwinOnly = false
      for (i <- 1.to(digits.size - 1)) {
        if (digits(i) == currentDigit) {
          appearedCount += 1
        } else {
          if (appearedCount == 2) {
            hasTwinOnly = true
          }
          currentDigit = digits(i)
          appearedCount = 1
        }
      }
      if (appearedCount == 2) {
        hasTwinOnly = true
      }
      hasTwinOnly
    }
  }

  val isExtendedValid = isValid && hasOnlyTwinRepeatingNumbers

  def next(): Password = new Password(value + 1)
  val lessOrEqual = (max: Int) => value <= max

  override def toString(): String = "p(" + value + ")"
}