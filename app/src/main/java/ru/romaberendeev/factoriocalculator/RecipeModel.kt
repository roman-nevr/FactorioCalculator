package ru.romaberendeev.factoriocalculator

class RecipeModel(
  val recipeId: String,
  val recipeName: String,
  val resources: List<ResourceModelPack>,
  val time: Seconds,
  val factories: List<FactoryModel>,
  val output: Int
)