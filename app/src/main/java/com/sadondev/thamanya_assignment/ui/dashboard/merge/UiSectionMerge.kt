package com.sadondev.thamanya_assignment.ui.dashboard.merge

import com.sadondev.thamanya_assignment.ui.models.UiCard
import com.sadondev.thamanya_assignment.ui.models.UiSection

//import com.sadondev.thamanya_assignment.ui.models.UiCard
//import com.sadondev.thamanya_assignment.ui.models.UiSection


data class MergeResult(
    val merged: List<UiSection>,
    val addedCount: Int
)

fun List<UiSection>.mergeWithCount(newPage: List<UiSection>): MergeResult {
    var added = 0
    val result = mutableListOf<UiSection>()
    val byKeyNew = newPage.associateBy { Triple(it::class.simpleName.orEmpty(), it.name, it.layout.name) }

    for (old in this) {
        val key = Triple(old::class.simpleName.orEmpty(), old.name, old.layout.name)
        val merged = when (old) {
            is UiSection.Square -> {
                val n = (byKeyNew[key] as? UiSection.Square)
                val (items, inc) = old.items.appendDistinctCount(n?.items)
                added += inc; old.copy(items = items)
            }
            is UiSection.Grid2Lines -> {
                val n = (byKeyNew[key] as? UiSection.Grid2Lines)
                val (items, inc) = old.items.appendDistinctCount(n?.items)
                added += inc; old.copy(items = items)
            }
            is UiSection.BigSquare -> {
                val n = (byKeyNew[key] as? UiSection.BigSquare)
                val (items, inc) = old.items.appendDistinctCount(n?.items)
                added += inc; old.copy(items = items)
            }
            is UiSection.Queue -> {
                val n = (byKeyNew[key] as? UiSection.Queue)
                val (items, inc) = old.items.appendDistinctCount(n?.items)
                added += inc; old.copy(items = items)
            }
            is UiSection.Unknown -> old
        }
        result += merged
    }

    // brand-new sections that didn't exist before
    val existingKeys = this.map { Triple(it::class.simpleName.orEmpty(), it.name, it.layout.name) }.toSet()
    newPage.filterNot { Triple(it::class.simpleName.orEmpty(), it.name, it.layout.name) in existingKeys }
        .forEach { sec ->
            added += sec.items.size
            result += sec
        }

    return MergeResult(result, added)
}

private fun List<UiCard>.appendDistinctCount(next: List<UiCard>?): Pair<List<UiCard>, Int> {
    if (next.isNullOrEmpty()) return this to 0
    val seen = this.asSequence().map { it.id }.toHashSet()
    val append = next.filter { seen.add(it.id) }
    return (this + append) to append.size
}





//
//fun List<UiSection>.mergeWith(newPage: List<UiSection>): List<UiSection> {
//    val result = mutableListOf<UiSection>()
//    val byKeyNew = newPage.associateBy { it.key() }
//    for (old in this) {
//        val key = old.key()
//        val merged = when (old) {
//            is UiSection.Square -> {
//                val n = (byKeyNew[key] as? UiSection.Square)
//                old.copy(items = old.items.appendDistinct(n?.items))
//            }
//            is UiSection.Grid2Lines -> {
//                val n = (byKeyNew[key] as? UiSection.Grid2Lines)
//                old.copy(items = old.items.appendDistinct(n?.items))
//            }
//            is UiSection.BigSquare -> {
//                val n = (byKeyNew[key] as? UiSection.BigSquare)
//                old.copy(items = old.items.appendDistinct(n?.items))
//            }
//            is UiSection.Queue -> {
//                val n = (byKeyNew[key] as? UiSection.Queue)
//                old.copy(items = old.items.appendDistinct(n?.items))
//            }
//            is UiSection.Unknown -> old
//        }
//        result += merged
//    }
//
//    val existingKeys = this.map { it.key() }.toSet()
//    result += newPage.filterNot { it.key() in existingKeys }
//
//    return result
//}
//
//private fun UiSection.key(): Triple<String, String, String> =
//    Triple(this::class.simpleName.orEmpty(), name, layout.name)
//
//private fun List<UiCard>.appendDistinct(next: List<UiCard>?): List<UiCard> {
//    if (next.isNullOrEmpty()) return this
//    val seen = this.asSequence().map { it.id }.toHashSet()
//    val append = next.filter { seen.add(it.id) }
//    return this + append
//}
