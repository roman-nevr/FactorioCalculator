package ru.romaberendeev.factoriocalculator

import org.junit.Test

import org.junit.Assert.*
import ru.romaberendeev.factoriocalculator.Recipe.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class CalculatorTest {
  @Test
  fun test() {
    Calculator().calculate(listOf(
      IRON_GEAR.asProduct(1.0),
      IRON_PLATE.asProduct(1.0)
    ))
  }
}