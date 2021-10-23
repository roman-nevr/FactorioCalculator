package ru.romaberendeev.factoriocalculator

fun pack(vararg pairs: Pair<Int, Recipe>): List<ResourcePack> {
  return pairs.map { pair -> ResourcePack(pair.second, pair.first) }
}

fun Recipe.toPack(amount: Int): ResourcePack {
  return ResourcePack(this, amount)
}

fun Recipe.asProduct(amountPerSec: Double): ProductionPack {
  return ProductionPack(this, amountPerSec)
}

infix fun <A, B> A.of(that: B): Pair<A, B> = Pair(this, that)