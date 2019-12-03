package io.github.zebalu.advent2019

import org.scalatestplus.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.FunSuite

@RunWith(classOf[JUnitRunner])
class WireSuite extends FunSuite {

  test("example 0") {
    val first: Wire = new Wire(Array("R8", "U5", "L5", "D3"))
    val second: Wire = new Wire(Array("U7", "R6", "D4", "L4"))
    assert(6 == first.distance(second))
  }

  test("example 1") {
    val first: Wire = new Wire(Array("R75", "D30", "R83", "U83", "L12", "D49", "R71", "U7", "L72"))
    val second: Wire = new Wire(Array("U62", "R66", "U55", "R34", "D71", "R55", "D58", "R83"))
    assert(159 == first.distance(second))
  }

  test("example 2") {
    val first: Wire = new Wire(Array("R98", "U47", "R26", "D63", "R33", "U87", "L62", "D20", "R33", "U53", "R51"))
    val second: Wire = new Wire(Array("U98", "R91", "D20", "R16", "D67", "R40", "U7", "R15", "U6", "R7"))
    assert(135 == first.distance(second))
  }
  
  test("best steps example 0") {
    val first: Wire = new Wire(Array("R8", "U5", "L5", "D3"))
    val second: Wire = new Wire(Array("U7", "R6", "D4", "L4"))
    assert(30 == first.bestSteps(second))
  }
  
  test("best steps example 1") {
    val first: Wire = new Wire(Array("R75", "D30", "R83", "U83", "L12", "D49", "R71", "U7", "L72"))
    val second: Wire = new Wire(Array("U62", "R66", "U55", "R34", "D71", "R55", "D58", "R83"))
    assert(610 == first.bestSteps(second))
  }
  
  test("best steps example 2") {
    val first: Wire = new Wire(Array("R98", "U47", "R26", "D63", "R33", "U87", "L62", "D20", "R33", "U53", "R51"))
    val second: Wire = new Wire(Array("U98", "R91", "D20", "R16", "D67", "R40", "U7", "R15", "U6", "R7"))
    assert(410 == first.bestSteps(second))
  }

}