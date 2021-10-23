package ru.romaberendeev.factoriocalculator

object RecipeMapper {

  fun mapFactories(factories: List<FactoryDto>): Map<String, FactoryModel> {
    return factories.map {
      it.factoryId to mapFactory(it)
    }.toMap()
  }

  private fun mapFactory(factoryDto: FactoryDto): FactoryModel {
    return FactoryModel(
      factoryId = factoryDto.factoryId,
      factoryName = factoryDto.factoryName,
      productionFactor = factoryDto.productionFactor
    )
  }

  fun mapRecipes(recipes: List<RecipeDto>, factories: Map<String, FactoryModel>): Map<String, RecipeModel> {
    val mappedRecipes = mutableMapOf<String, RecipeModel>()
    val rawRecipes = recipes.map { it.recipeId to it }.toMap()
    recipes.forEach {
      mappedRecipes[it.recipeId] = mapRecipe(it, rawRecipes, mappedRecipes, factories)
    }
    return mappedRecipes
  }

  private fun mapRecipe(
    recipe: RecipeDto,
    raw: Map<String, RecipeDto>,
    mapped: Map<String, RecipeModel>,
    factories: Map<String, FactoryModel>
  ): RecipeModel {
    val resources: List<ResourceModelPack> = recipe.resources?.map {
      val recipe = if (mapped.containsKey(it.key)) {
        mapped[it.key]!!
      } else {
        mapRecipe(raw[it.key]!!, raw, mapped, factories)
      }
      ResourceModelPack(recipe, it.value)
    }?: emptyList()
    return RecipeModel(
      recipeId = recipe.recipeId,
      recipeName = recipe.recipeName,
      resources = resources,
      time = recipe.time,
      factories = listOf(factories[recipe.factoryId]!!),
      output = recipe.output,
    )
  }
}