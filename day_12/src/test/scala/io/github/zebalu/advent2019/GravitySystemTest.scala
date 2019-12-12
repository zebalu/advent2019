package io.github.zebalu.advent2019

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class GravitySystemTest extends FunSuite {
  
  test("1000 steps with input") {
    val moons = MoonReader.readMoons("day_12_puzzle.txt")
    val gs = new GravitySystem (moons)
    1.to(1000).foreach(_ => gs.timeStep())
    assert(12490 == gs.totalEnery())
  }
  
  test("find first repeat") {
    val moons = MoonReader.readMoons("day_12_puzzle.txt")
    val gs = new GravitySystem (moons)
    println(s"this was too low: 9 820 085 536 800")
    assert(392733896255168L == gs.findFirstRepeat())
  }
}