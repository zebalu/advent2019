package io.github.zebalu.advent2019

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PasswordRangeSuite extends FunSuite {
  
  test("input has right size") {
    val pr = new PasswordRange(264793, 803935)
    assert(966 == pr.size)
  }
  
  test("input has right extended size") {
    val pr = new PasswordRange(264793, 803935)
    assert(628 == pr.extraRuleSize)
  }
  
}