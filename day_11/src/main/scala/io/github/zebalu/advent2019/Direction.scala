package io.github.zebalu.advent2019

object Direction {
  sealed abstract class Dir(val name: String, val difX:Int, val difY:Int) {
    def move(coords: (Int, Int)): (Int, Int) = {
      (coords._1+difX, coords._2+difY)
    }
    def turnLeft():Dir = {
      this match {
        case UP => LEFT
        case LEFT => DOWN
        case DOWN => RIGHT
        case RIGHT => UP
      }
    }
    
    def turnRight():Dir = {
      this match {
        case UP => RIGHT
        case LEFT => UP
        case DOWN => LEFT
        case RIGHT => DOWN
      }
    }
  }
  case object UP extends Dir("UP", 0, -1)
  case object DOWN extends Dir("DOWN", 0, 1)
  case object LEFT extends Dir("LEFT", -1, 0)
  case object RIGHT extends Dir("RIGHT", 1, 0)
  
  val directions: Set[Dir] = Set(UP, DOWN, LEFT, RIGHT)
  
}