package io.github.zebalu.advent2019

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class OrbitGraphSuite extends FunSuite {
  
  test("example") {
    val orbits = new OrbitResourceProvider("/example.txt").orbits
    val rootArray = orbits(0).split("\\)")
    val graph: OrbitGraph = new OrbitGraph();
    for(o <- orbits) {
      val oo = o.split("\\)")
      graph.append(oo(0), oo(1))
    }
    assert(42 == graph.getOrbitCount())
  }
  
  test("input") {
    val orbits = new OrbitResourceProvider("/puzzle_day06.txt").orbits
    val rootArray = orbits(0).split("\\)")
    val graph: OrbitGraph = new OrbitGraph();
    for(o <- orbits) {
      val oo = o.split("\\)")
      graph.append(oo(0), oo(1))
    }
    assert(140608 == graph.getOrbitCount())
  }
  
  test("example2 min route") {
    val orbits = new OrbitResourceProvider("/example2.txt").orbits
    val rootArray = orbits(0).split("\\)")
    val graph: OrbitGraph = new OrbitGraph();
    for(o <- orbits) {
      val oo = o.split("\\)")
      graph.append(oo(0), oo(1))
    }
    assert(4 == graph.minRoute("YOU", "SAN"))
  }
  
  test("input2 min route") {
    val orbits = new OrbitResourceProvider("/puzzle_day06_2.txt").orbits
    val rootArray = orbits(0).split("\\)")
    val graph: OrbitGraph = new OrbitGraph();
    for(o <- orbits) {
      val oo = o.split("\\)")
      graph.append(oo(0), oo(1))
    }
    assert(337 == graph.minRoute("YOU", "SAN"))
  }
  
}