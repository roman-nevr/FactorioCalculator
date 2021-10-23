package ru.romaberendeev.factoriocalculator

object Calculator {

  fun calculate(production: List<ProductionPack>): List<ProductionFactory> {

    val result = production.compressPacks().map { productionPack ->
      val recipe = productionPack.resource
      val downLevel = calculate(recipe.resources.map { resourcePack ->
        ProductionPack(
          resourcePack.resource,
          resourcePack.amount * productionPack.amountPerSec / productionPack.resource.output
        )
      })
      ProductionFactory(
        factoriesAmount = productionPack.amountPerSec * recipe.time / recipe.factory.productionFactor / recipe.output,
        factory = recipe.factory,
        product = recipe,
        downLevel = downLevel
      )
    }
    return result
  }

//  private fun calculate(product: Recipe): Map<Int, Recipe> {
//
//  }
}

class ProductionLevel(
  val factories: List<ProductionFactory>,
  downLevel: ProductionLevel? = null
)

class ProductionFactory(
  val factoriesAmount: Double,
  val factory: Factory,
  val product: Recipe,
  val downLevel: List<ProductionFactory> = emptyList()
) {
  val productPerSec = factoriesAmount * factory.productionFactor / product.time * product.output

  override fun toString(): String {
    return "$factoriesAmount ${factory.factoryName} of ${product.recipeName}"
  }
}

fun List<ProductionPack>.compressPacks(): List<ProductionPack> {
  val map = hashMapOf<Recipe, Double>()
  this.forEach { pack ->
    map.addDouble(pack.resource, pack.amountPerSec)
  }
  return map.map { ProductionPack(it.key, it.value) }
}

fun List<ProductionFactory>.compressFactories(): List<ProductionFactory> {
  val map = hashMapOf<Recipe, Double>()
  this.forEach { factory ->
    map.addDouble(factory.product, factory.factoriesAmount)
  }
  return map.map { ProductionFactory(it.value, it.key.factory, it.key) }
}

fun <K> MutableMap<K, Double>.addDouble(key: K, number: Double) {
  val stored: Double = get(key) ?: 0.0
  this[key] = stored + number
}