package ru.romaberendeev.factoriocalculator

class Calculator {

  fun calculate(production: List<ProductionPack>) {
    val c = production.flatMap { pack ->
      pack.resource.resources.map {
        val factory = it.resource.factory
        ProductionFactory(
          factoriesAmount = it.amount * pack.amountPerSec / factory.factor,
          factory = factory,
          product = it.resource
        )
//        ProductionPack(it.resource, it.amount * pack.amountPerSec)
      }
    }.compressFactories()
    println(c)
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
  val product: Recipe
) {
  val productPerSec = factoriesAmount * factory.factor / product.time
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