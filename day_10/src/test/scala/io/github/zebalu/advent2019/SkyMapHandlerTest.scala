package io.github.zebalu.advent2019

import org.scalatest.TestSuite
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner
import org.scalatest.FunSuite
import scala.io.Source

@RunWith(classOf[JUnitRunner])
class SkyMapHandlerTest extends FunSuite {
  test("example") {
    val smh = new SkyMapHandler("/day_10_example_1.txt")
    assert(8 == smh.maxVisible)
  }
  test("max") {
    val smh = new SkyMapHandler("/puzzle_10.txt")
    assert(326 == smh.maxVisible)
  }
  test("destruction order") {
    val smh = new SkyMapHandler("/puzzle_10.txt")
    val l = smh.destructionOrder
    val twoHundreadthAsteroideToDestroy = smh.destructionOrder(199)
    assert(1623 == twoHundreadthAsteroideToDestroy.x*100+twoHundreadthAsteroideToDestroy.y)
  }
}