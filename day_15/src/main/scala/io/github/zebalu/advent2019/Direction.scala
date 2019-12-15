package io.github.zebalu.advent2019

object Direction {
  sealed abstract class Dir(val name: String, val difX: Int, val difY: Int, val intCode: Int) {
    def move(coords: (Long, Long)): (Long, Long) = {
      (coords._1 + difX, coords._2 + difY)
    }
    def turnLeft(): Dir = {
      this match {
        case UP => LEFT
        case LEFT => DOWN
        case DOWN => RIGHT
        case RIGHT => UP
      }
    }

    def turnRight(): Dir = {
      this match {
        case UP => RIGHT
        case LEFT => UP
        case DOWN => LEFT
        case RIGHT => DOWN
      }
    }
  }
  case object UP extends Dir("UP", 0, -1, 1)
  case object DOWN extends Dir("DOWN", 0, 1, 2)
  case object LEFT extends Dir("LEFT", -1, 0, 4)
  case object RIGHT extends Dir("RIGHT", 1, 0, 3)

  val directions: Set[Dir] = Set(UP, DOWN, LEFT, RIGHT)
  def getDirFromPos(from: (Long, Long), to: (Long, Long)): Dir = {
    val x = to._1 - from._1
    val y = to._2 - from._2
    if (x < 0) {
      LEFT
    } else if (x > 0) {
      RIGHT
    } else if (y < 0) {
      UP
    } else if (y > 0) {
      DOWN
    } else {
      throw new IllegalArgumentException(s"can not go from $from to $to")
    }
  }

}