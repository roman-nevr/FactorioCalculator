package ru.romaberendeev.factoriocalculator

import android.os.Build
import android.os.Build.VERSION_CODES
import androidx.test.platform.app.InstrumentationRegistry
import com.google.gson.GsonBuilder
import org.junit.Test

import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import ru.romaberendeev.factoriocalculator.ReadUtils.listToken
import ru.romaberendeev.factoriocalculator.Recipe.*

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
    Calculator().calculate(listOf(
      IRON_GEAR.asProduct(1.0),
      IRON_PLATE.asProduct(1.0)
    ))
  }

  @Test
  fun readTest() {
    val gson = GsonBuilder().create()
    val recipesFile = ReadUtils.readFile(RuntimeEnvironment.getApplication().classLoader, "testraw/recepies.json")
    val factoriesFile = ReadUtils.readFile(RuntimeEnvironment.getApplication().classLoader, "testraw/factories.json")
    val recipeDtoList: List<RecipeDto> = gson.fromJson(recipesFile, RecipeDto::class.java.listToken())
    val factoryDtoList: List<FactoryDto> = gson.fromJson(factoriesFile, FactoryDto::class.java.listToken())
    val factories = RecipeMapper.mapFactories(factoryDtoList)
    val recipes = RecipeMapper.mapRecipes(recipeDtoList, factories)
    println(recipes)
  }
}