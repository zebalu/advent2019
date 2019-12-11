package io.github.zebalu.advent2019

import scala.collection.mutable.HashMap
import scala.collection.mutable.Map

class HulRobot(computer: FeedableIntComputer) {

  val panels: Map[(Int, Int), Int] = HashMap.empty[(Int, Int), Int].withDefaultValue(0)
  var currentPosition: (Int, Int) = (0, 0)
  var dir: Direction.Dir = Direction.UP 
  var reads = 0

  computer.setDefaultAnser(_ => {
    reads +=1
    panels(currentPosition)
  })
  
  def countPaintedPanels(): Int = panels.size
  def paintWhiteStartPosition(): Unit = panels((0,0))=1
  
  def paint():Int = {
    
    while(!computer.isFinished()) {
      computer.execute()
      val color = computer.getOutput(computer.getOutputSize()-2).toInt
      panels(currentPosition)=color
      val turnIndicator = computer.getOutput(computer.getOutputSize()-1) 
      dir = turnIndicator match {
        case 0 => dir.turnLeft()
        case 1 => dir.turnRight()
      }
      currentPosition = dir.move(currentPosition)
      computer.increaseMaxRead()
    }
    countPaintedPanels()
  }
  
  def printOut(): Unit = {
    val minX = panels.keys.map(coord => coord._1).min
    val maxX = panels.keys.map(coord => coord._1).max
    val minY = panels.keys.map(coord => coord._2).min
    val maxY = panels.keys.map(coord => coord._2).max
    for(y<-minY.to(maxY)) {
      for(x<-minX.to(maxX)) {
        panels((x,y)) match {
          case 0 => print(" ")
          case 1 => print("#")
        }
      }
      println()
    }
  }

}