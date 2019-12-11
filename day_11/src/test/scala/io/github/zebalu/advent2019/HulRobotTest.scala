package io.github.zebalu.advent2019

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class HulRobotTest extends FunSuite {
  test("input expectations") {
    val computer: FeedableIntComputer = new FeedableIntComputer(ProgramReader.read("puzzle_11.txt"))
    val robot = new HulRobot(computer)
    assert(1863 == robot.paint())
  }
  
  test("fixed start") {
    val computer: FeedableIntComputer = new FeedableIntComputer(ProgramReader.read("puzzle_11.txt"))
    val robot = new HulRobot(computer)
    robot.paintWhiteStartPosition()
    val count = robot.paint()
    robot.printOut()
    assert(249 == count)
  }
}