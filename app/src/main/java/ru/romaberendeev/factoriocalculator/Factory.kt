package ru.romaberendeev.factoriocalculator



enum class Factory(
  val factor: Double
) {
  BURNER_MINING_DRILL(0.25),

  STONE_FURNACE(1.0),

  ASSEMBLER_1(0.5)
}
