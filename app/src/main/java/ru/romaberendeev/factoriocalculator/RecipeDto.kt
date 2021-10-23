package ru.romaberendeev.factoriocalculator

import com.google.gson.annotations.SerializedName

class RecipeDto(
  @SerializedName("id")
  val recipeId: String,

  @SerializedName("name")
  val recipeName: String,

  @SerializedName("time")
  val time: Seconds,

  @SerializedName("output")
  val output: Int,

  @SerializedName("resources")
  val resources: Map<String, Int>?,

  @SerializedName("factory_id")
  val factoryId: String
)
