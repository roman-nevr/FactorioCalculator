package ru.romaberendeev.factoriocalculator

class Factory(
  val factoryId: String,
  val factoryName: String,
  val productionFactor: Double
) {

  override fun toString(): String {
    return factoryName
  }
}
