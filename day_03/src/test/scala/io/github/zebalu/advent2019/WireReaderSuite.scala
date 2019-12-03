package io.github.zebalu.advent2019

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class WireReaderSuite extends FunSuite {
  
  test("read") {
    assert(2 == WireReader.wires.size)
  }
  
  test("wires closes cross to origo") {
    assert(3247 == WireReader.wires.head.distance(WireReader.wires.tail.head))
  }
  
  test("best steps are found") {
    assert(48054 == WireReader.wires.head.bestSteps(WireReader.wires.tail.head))
  }
  
}