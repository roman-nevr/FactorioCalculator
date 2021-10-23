package ru.romaberendeev.factoriocalculator

import com.google.gson.annotations.SerializedName

class FactoryDto(
  @SerializedName("id")
  val factoryId: String,

  @SerializedName("name")
  val factoryName: String,

  @SerializedName("production_factor")
  val productionFactor: Double,
)
