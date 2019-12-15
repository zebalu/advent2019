package io.github.zebalu.advent2019

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner
import java.time.Instant
import java.time.Duration

@RunWith(classOf[JUnitRunner])
class OxygenSystemFinderTest extends FunSuite {
  test("input") {
    val comp = new FeedableIntComputer(DroidCodeReader.read("puzzle_15.txt"))
    val osf = new OxygenSystemFinder(comp)
    osf.shouldPrint=false
    osf.draw()
    assert(230 == osf.shortestPath())
  }

  test("input --2") {
    val comp = new FeedableIntComputer(DroidCodeReader.read("puzzle_15.txt"))
    val osf = new OxygenSystemFinder(comp)
    osf.shouldPrint=false
    osf.draw()
    assert(288 == osf.fillTime())
  }

}