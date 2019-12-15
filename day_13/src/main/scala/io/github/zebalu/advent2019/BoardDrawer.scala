package io.github.zebalu.advent2019

import scala.collection.mutable.Map
import scala.collection.mutable.HashMap
import scala.util.Try
import scala.io.Source
import scala.util.Success
import org.scalactic.Fail
import scala.util.Failure

class BoardDrawer(computer: FeedableIntComputer) {

  var board: Map[(Long, Long), Long] = HashMap.empty[(Long, Long), Long]
  var segment: Long = 0L
  var shouldPrint = true
  var ballPosition = (Long.MinValue, Long.MinValue)
  var paddPosition = (Long.MaxValue, Long.MaxValue)
  
  def draw(): Unit = {
    computer.execute()
    var i = 0
    while (i < computer.getOutputSize()) {
      val x = computer.getOutput(i)
      val y = computer.getOutput(i + 1)
      val v = computer.getOutput(i + 2)
      if (x == -1L && y == 0L) {
        segment = v
      } else {
        board((x, y)) = v
        if(v==4) {
          ballPosition=(x,y)
        } else if(v==3) {
          paddPosition=(x,y)
        }
      }
      i += 3
    }
    if(shouldPrint) {
    for (y <- board.keys.map(_._2).min.to(board.keys.map(_._2).max)) {
      for (x <- board.keys.map(_._1).min.to(board.keys.map(_._1).max)) {
        val v = board((x, y))
        v match {
          case 0 => print(" ")
          case 1 => print("#")
          case 2 => print("X")
          case 3 => print("_")
          case 4 => print("o")
        }
      }
      println()
    }
    println("\n\n @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ \n\n")
    0.to(5).foreach(_=>println("\n"))
    Thread.sleep(100)
    }
  }

  def calculate(block: Long): Int = board.values.filter(_ == block).size

}

object BoardCodeReader {
  def read(name: String): Array[Long] = {
    Try(Source.fromResource(name)) match {
      case Success(src) => src.getLines().map(_.split(",")).flatten.map(_.toLong).toArray
      case Failure(e) => throw new IllegalArgumentException("Could not work with " + name, e)
    }
  }

  def computerFrom(name: String): FeedableIntComputer = {
    var fc = new FeedableIntComputer(read(name))
    fc.setDefaultAnser(_ => {
      throw new IllegalAccessException("I am not ready for inputs")
    })
    fc
  }
}