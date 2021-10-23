package ru.romaberendeev.factoriocalculator

data class Recipe(
  val recipeId: String,
  val recipeName: String,
  val resources: List<ResourcePack>,
  val time: Seconds,
  val factories: List<Factory>,
  val output: Int
) {

  val factory = factories.first()

  override fun toString(): String {
    if (resources.isEmpty()) {
      return recipeName
    }
    return "$recipeName from ${resources.map { "${it.amount} ${it.resource.recipeName}" }.joinToString(separator = ",")}"
  }
}