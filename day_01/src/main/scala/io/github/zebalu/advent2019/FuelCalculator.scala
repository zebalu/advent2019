/*
   Copyright 2019 Balazs Zaicsek

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package io.github.zebalu.advent2019

object FuelCalculator {
  val forMass = (mass: Int) => mass / 3 - 2
  val forModules = (modules: Array[Int]) => {
    var sum: Int = 0
    for (mass <- modules) {
      sum += forMass(mass)
    }
    sum
  }
  val forMassWithFuel: Int => Int = (mass: Int) => {
    val base = forMass(mass)
    if (base > 0) {
      base + forMassWithFuel(base)
    } else {
      0
    }
  }
  val forModulesWithFuel = (modules: Array[Int]) => {
    var sum: Int = 0
    for (mass <- modules) {
      sum += forMassWithFuel(mass)
    }
    sum
  }
  
}