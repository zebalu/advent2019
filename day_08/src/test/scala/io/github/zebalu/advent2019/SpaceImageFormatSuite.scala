package io.github.zebalu.advent2019

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class SpaceImageFormatSuite extends FunSuite {
  
  test("example image") {
    val sif = new SpaceImageFormat(3,2, List(1,2,3,4,5,6,7,8,9,0,1,2))
    assert(1==sif.hashOfMin(0, 1, 2))
  }
  
  test("example collasped") {
    val sif = new SpaceImageFormat(2,2, List(0,2,2,2,1,1,2,2,2,2,1,2,0,0,0,0))
    assert("01\n10"==sif.collaspedString())
  }
  
  test("task01") {
    val digits = SpaceImageFormatReader.digitFromResource("/day8_puzzle.txt")
    val sif = new SpaceImageFormat(25,6,digits)
    println (sif.collapseForConsole())
    assert(2193 == sif.hashOfMin(0, 1, 2))
  }
}