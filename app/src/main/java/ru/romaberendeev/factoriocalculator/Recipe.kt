package ru.romaberendeev.factoriocalculator

import ru.romaberendeev.factoriocalculator.Factory.ASSEMBLER_1
import ru.romaberendeev.factoriocalculator.Factory.BURNER_MINING_DRILL
import ru.romaberendeev.factoriocalculator.Factory.STONE_FURNACE


enum class Recipe(
  val type: String,
  val resources: List<ResourcePack>,
  val time: Seconds,
  val factories: List<Factory>,
  val output: Int
) {
  IRON_ORE("Iron Ore", emptyList(), 1.0, listOf(BURNER_MINING_DRILL), 1),
  COPPER_ORE("Cooper Ore", emptyList(), 1.0, listOf(BURNER_MINING_DRILL), 1),
  IRON_PLATE("Iron plate", pack(1 of IRON_ORE), 3.2, listOf(STONE_FURNACE), 1),
  COPPER_PLATE("Cooper plate", pack(1 of COPPER_ORE), 3.2, listOf(STONE_FURNACE), 1),
  IRON_GEAR("Iron gear", pack(2 of IRON_PLATE), 0.5, listOf(ASSEMBLER_1), 1),

  COPPER_CABLE("Copper cable", pack(1 of COPPER_PLATE), 0.5, listOf(ASSEMBLER_1), 2),
  GREEN_CHIP("Green chip", pack(3 of COPPER_CABLE, 1 of IRON_PLATE), 0.5, listOf(ASSEMBLER_1), 1),
  YELLOW_TRANSPORTER("Yellow transporter", pack(2 of IRON_PLATE), 0.5, listOf(ASSEMBLER_1), 1),
  YELLOW_INSERTER("Yellow inserter", pack(1 of IRON_PLATE, 1 of GREEN_CHIP, 1 of IRON_PLATE), 0.5, listOf(ASSEMBLER_1), 1),
  GREEN_BOTTLE("Green bottle", pack(2 of IRON_PLATE), 0.5, listOf(ASSEMBLER_1), 1),
  ;

  val factory: Factory = factories.first()
}


