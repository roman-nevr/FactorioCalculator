package ru.romaberendeev.factoriocalculator

class ResourcePack(
  val resource: Recipe,
  val amount: Int
)

class ResourceModelPack(
  val resource: RecipeModel,
  val amount: Int
)

class ProductionPack(
  val resource: Recipe,
  val amountPerSec: Double
) {
  fun multiply(factor: Double): ProductionPack {
    return ProductionPack(resource, amountPerSec * factor)
  }
}