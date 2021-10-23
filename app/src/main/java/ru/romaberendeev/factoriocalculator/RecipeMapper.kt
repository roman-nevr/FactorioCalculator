package ru.romaberendeev.factoriocalculator

object RecipeMapper {

  fun mapFactories(factories: List<FactoryDto>): Map<String, Factory> {
    return factories.map {
      it.factoryId to mapFactory(it)
    }.toMap()
  }

  private fun mapFactory(factoryDto: FactoryDto): Factory {
    return Factory(
      factoryId = factoryDto.factoryId,
      factoryName = factoryDto.factoryName,
      productionFactor = factoryDto.productionFactor
    )
  }

  fun mapRecipes(recipes: List<RecipeDto>, factories: Map<String, Factory>): Map<String, Recipe> {
    val mappedRecipes = mutableMapOf<String, Recipe>()
    val rawRecipes = recipes.map { it.recipeId to it }.toMap()
    recipes.forEach {
      mappedRecipes[it.recipeId] = mapRecipe(it, rawRecipes, mappedRecipes, factories)
    }
    return mappedRecipes
  }

  private fun mapRecipe(
    recipe: RecipeDto,
    raw: Map<String, RecipeDto>,
    mapped: Map<String, Recipe>,
    factories: Map<String, Factory>
  ): Recipe {
    if (!raw.containsKey(recipe.recipeId)) {
      throw IllegalStateException("wrong recipe id: ${recipe.recipeId}")
    }
    val resources: List<ResourcePack> = recipe.resources?.map {
      val recipeModel = if (mapped.containsKey(it.key)) {
        mapped[it.key]!!
      } else {
        if (raw[it.key] == null) {
          throw IllegalStateException("wrong resource id: ${it.key}")
        }
        mapRecipe(raw[it.key]!!, raw, mapped, factories)
      }
      ResourcePack(recipeModel, it.value)
    }?: emptyList()
    return Recipe(
      recipeId = recipe.recipeId,
      recipeName = recipe.recipeName,
      resources = resources,
      time = recipe.time,
      factories = listOf(factories[recipe.factoryId]!!),
      output = recipe.output,
    )
  }
}