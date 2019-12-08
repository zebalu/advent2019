package io.github.zebalu.advent2019

import scala.collection.mutable.ListBuffer

class SpaceImageFormat(width: Int, height: Int, digits: List[Int]) {

  private val layerSize = width * height

  private val layers: List[Layer] = {
    val count = digits.size / layerSize
    var buffer = new ListBuffer[Layer]
    for (i <- 0.to(count - 1)) {
      buffer += new Layer(digits.slice(i * layerSize, (i + 1) * layerSize))
    }
    buffer.toList
  }

  def hashOfMin(minDigit: Int, count1: Int, count2: Int): Int = {
    val layer = layerWithFewestOf(minDigit)
    return layer.count(count1) * layer.count(count2)
  }

  def collaspedString(): String = {
    var buffer = new StringBuffer()
    for (i <- 0.to(height - 1)) {
      for (j <- 0.to(width - 1)) {
        buffer.append(pixelAt(i, j))
      }
      buffer.append("\n")
    }
    buffer.substring(0, buffer.length()-1)
  }

  def collapseForConsole(): String = {
    var buffer = new StringBuffer()
    for (i <- 0.to(height - 1)) {
      for (j <- 0.to(width - 1)) {
        buffer.append(pixelAt(i, j) match {
          case 0 => " "
          case 1 => "#"
          case _ => throw new IllegalStateException("a pixel has invalid color");
        })
      }
      buffer.append("\n")
    }
    buffer.toString()
  }

  private def pixelAt(i: Int, j: Int): Int = {
    pixelAt(i, j, 0)
  }

  private def pixelAt(i: Int, j: Int, idx: Int): Int = {
    layers(idx).pixelAt(i, j) match {
      case 2 => pixelAt(i, j, idx + 1)
      case p => p
    }
  }

  private def layerWithFewestOf(digit: Int): Layer = {
    layers.min((left: Layer, right: Layer) => left.count(digit) - right.count(digit))
  }

  private class Layer(lDigits: List[Int]) {
    val data: List[List[Int]] = {
      var lbuffer = new ListBuffer[List[Int]]
      for (i <- 0.to(height - 1)) {
        lbuffer += lDigits.slice(i * width, (i + 1) * width)
      }
      lbuffer.toList
    }

    private val count0: Int = {
      var countBuff = 0
      for (i <- lDigits) {
        if (i == 0) {
          countBuff += 1
        }
      }
      countBuff
    }

    val count: Int => Int = (digit: Int) => {
      var res = -1
      if (digit == 0) {
        count0
      } else {
        var countBuff = 0
        for (i <- lDigits) {
          if (i == digit) {
            countBuff += 1
          }
        }
        countBuff
      }
    }

    val pixelAt: (Int, Int) => Int = (i: Int, j: Int) => {
      data(i)(j)
    }
  }
}