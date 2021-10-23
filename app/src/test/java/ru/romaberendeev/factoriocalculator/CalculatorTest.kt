package ru.romaberendeev.factoriocalculator

import android.os.Build.VERSION_CODES
import com.google.gson.GsonBuilder
import org.junit.Test

import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import ru.romaberendeev.factoriocalculator.ReadUtils.listToken

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@Config(sdk = [VERSION_CODES.M])
@RunWith(RobolectricTestRunner::class)
class CalculatorTest {
  @Test
  fun test() {
    val recipes = getRecipes()
    val calculate = Calculator.calculate(
      listOf(
        recipes["green-chip"]!!.asProduct(1.0),
        recipes["green-chip"]!!.asProduct(1.0),
//        recipes["iron-plate"]!!.asProduct(1.0)
      )
    )
    print(calculate)
  }

  @Test
  fun testCopperCables() {
    val recipes = getRecipes()
    val calculate = Calculator.calculate(
      listOf(
        recipes["copper-cable"]!!.asProduct(3.0),
//        recipes["iron-plate"]!!.asProduct(1.0)
      )
    )
    print(calculate)
  }

  @Test
  fun readTest() {
    getRecipes()
    println(getRecipes())
  }

  private fun getRecipes(): Map<String, Recipe> {
    val gson = GsonBuilder().create()
    val recipesFile = ReadUtils.readFile(RuntimeEnvironment.getApplication().classLoader, "testraw/recepies.json")
    val factoriesFile = ReadUtils.readFile(RuntimeEnvironment.getApplication().classLoader, "testraw/factories.json")
    val recipeDtoList: List<RecipeDto> = gson.fromJson(recipesFile, RecipeDto::class.java.listToken())
    val factoryDtoList: List<FactoryDto> = gson.fromJson(factoriesFile, FactoryDto::class.java.listToken())
    val factories = RecipeMapper.mapFactories(factoryDtoList)
    return RecipeMapper.mapRecipes(recipeDtoList, factories)
  }
}