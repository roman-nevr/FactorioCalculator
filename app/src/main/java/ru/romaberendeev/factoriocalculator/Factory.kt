package ru.romaberendeev.factoriocalculator

typealias KW = Int

enum class Factory(
  val factor: Double
) {
  BURNER_MINING_DRILL(0.25),

  STONE_FURNACE(1.0),

  ASSEMBLER_1(0.5)
}

/*
abstract class Factory(
  open val type: String,
  open val recipe: Recipe,
  open val outputPerSec: Double
)

abstract class BurningFactory(
  override val type: String,
  override val recipe: Recipe,
  override val outputPerSec: Double,
  val fuelConsumptionPerSec: KW,
) : Factory(type, recipe, outputPerSec)

abstract class ElectricFactory(
  override val type: String,
  override val recipe: Recipe,
  override val outputPerSec: Double,
  val energyConsumptionPerSec: KW,
) : Factory(type, recipe, outputPerSec)

object BURN_MINING_DRILL : BurningFactory("Charcoal mining drill", CHARCOAL, 0.25, 150)
object ELECTRIC_MINING_DRILL : ElectricFactory("Charcoal mining drill", CHARCOAL, 0.25, 90)
object STONE_FURNACE: BurningFactory("Stone furnace", STEEL_PLATE, 0.3125, 90)

 */