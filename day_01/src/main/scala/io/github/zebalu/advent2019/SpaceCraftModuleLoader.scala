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

import scala.io.Source
import scala.util.Try
import scala.util.Success
import scala.util.Failure
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.stream.Collectors
import scala.collection.JavaConverters._

object SpaceCraftModuleLoader {
  val modules = {
    Try(new BufferedReader(new InputStreamReader(SpaceCraftModuleLoader.getClass().getResourceAsStream("/puzzle1_input.txt")))) match {
      case Success(br) => {
        val lines = br.lines().collect(Collectors.toList()).asScala
        br.close()
        lines.map(line => Integer.parseInt(line)).toArray
      }
      case Failure(e) => {
        throw e
      }
    }
  }
}