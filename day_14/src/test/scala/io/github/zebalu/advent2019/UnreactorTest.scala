package io.github.zebalu.advent2019

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class UnreactorTest extends FunSuite {
  
  test("example 0") {
    val rg = new ReactionGraph()
    rg.read("example0.txt")
    val ur = new Unreactor("FUEL", 1, rg, "ORE")
    assert(165 == ur.getRootNeed())
  }
  
  test("example 1") {
    val rg = new ReactionGraph()
    rg.read("example1.txt")
    val ur = new Unreactor("FUEL", 1, rg, "ORE")
    assert(13312 == ur.getRootNeed())
  }
  
  test("example 2") {
    val rg = new ReactionGraph()
    rg.read("example2.txt")
    val ur = new Unreactor("FUEL", 1, rg, "ORE")
    assert(180697 == ur.getRootNeed())
  }
  
  test("example 3") {
    val rg = new ReactionGraph()
    rg.read("example3.txt")
    val ur = new Unreactor("FUEL", 1, rg, "ORE")
    assert(2210736 == ur.getRootNeed())
  }
  
  test("input") {
    val rg = new ReactionGraph()
    rg.read("puzzle_14.txt")
    val ur = new Unreactor("FUEL", 1, rg, "ORE")
    assert(1037742 == ur.getRootNeed())
  }
  
  test("example 0 -- 1") {
    val rg = new ReactionGraph()
    rg.read("example0.txt")
    val ur = new Unreactor("FUEL", 1, rg, "ORE")
    assert(1 == ur.getMaxNeedWithMaxRoot(322))
  }
  
  test("example 0 -- 2") {
    val rg = new ReactionGraph()
    rg.read("example0.txt")
    val ur = new Unreactor("FUEL", 1, rg, "ORE")
    assert(2 == ur.getMaxNeedWithMaxRoot(330))
  }
  
  test("example 0 -- 2 - 2") {
    val rg = new ReactionGraph()
    rg.read("example0.txt")
    val ur = new Unreactor("FUEL", 1, rg, "ORE")
    assert(2 == ur.getMaxNeedWithMaxRoot(331))
  }
  
  test("example 0 -- trillion") {
    val rg = new ReactionGraph()
    rg.read("example0.txt")
    val ur = new Unreactor("FUEL", 1, rg, "ORE")
    assert(6323777403L == ur.getMaxNeedWithMaxRoot(1000000000000L))
  }
  
  test("example 1 -- trillion") {
    val rg = new ReactionGraph()
    rg.read("example1.txt")
    val ur = new Unreactor("FUEL", 1, rg, "ORE")
    assert(82892753 == ur.getMaxNeedWithMaxRoot(1000000000000L))
  }
  
  test("example 2 -- trillion") {
    val rg = new ReactionGraph()
    rg.read("example2.txt")
    val ur = new Unreactor("FUEL", 1, rg, "ORE")
    assert(5586022  == ur.getMaxNeedWithMaxRoot(1000000000000L))
  }
  
  test("example 3 -- trillion") {
    val rg = new ReactionGraph()
    rg.read("example3.txt")
    val ur = new Unreactor("FUEL", 1, rg, "ORE")
    assert(460664  == ur.getMaxNeedWithMaxRoot(1000000000000L))
  }
  
  test("input -- trillion") {
    val rg = new ReactionGraph()
    rg.read("puzzle_14.txt")
    val ur = new Unreactor("FUEL", 1, rg, "ORE")
    assert(1572358 == ur.getMaxNeedWithMaxRoot(1000000000000L))
  }
}