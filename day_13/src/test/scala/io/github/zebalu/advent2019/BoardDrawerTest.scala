package io.github.zebalu.advent2019

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class BoardDrawerTest extends FunSuite {

  test("input block count") {
    val bd = new BoardDrawer(BoardCodeReader.computerFrom("puzzle_13.txt"))
    bd.shouldPrint = false
    bd.draw()
    assert(200 == bd.calculate(2))
  }

  test("hack in two quarters") {
    val input = BoardCodeReader.read("puzzle_13.txt")
    input(0) = 2L
    val computer = new FeedableIntComputer(input)
    val bd = new BoardDrawer(computer)
    bd.shouldPrint = false
    computer.setDefaultAnser(_ => math.signum(-1 * (bd.paddPosition._1 - bd.ballPosition._1)).toInt)
    while (!computer.isFinished()) {
      bd.draw()
      computer.increaseMaxRead()
    }
    assert(9803 == bd.segment)
  }
}
