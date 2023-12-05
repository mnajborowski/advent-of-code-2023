import kotlin.math.pow

fun main() {
    fun String.getYourWinningNumbers(): List<Int> =
        substringAfter(": ")
            .split("| ")
            .map {
                it.trimEnd().split(' ').mapNotNull { it.toIntOrNull() }
            }.let { numbers -> numbers.last().filter { it in numbers.first() } }

    fun String.getCardNumber(): Int = substringBefore(':').filter { it.isDigit() }.toInt()

    fun part1(input: List<String>): Int {
        return input.fold(0) { acc, line ->
            line.getYourWinningNumbers().let {
                if (it.isEmpty())
                    acc
                else acc + 2.0.pow(it.size - 1).toInt()
            }
        }
    }

    fun part2(input: List<String>): Int {
        val cards = input.toMutableList()
        var step = 0
        var size = cards.size
        while (step != size - 1) {
            cards.addAll(input.subList(cards[step].getCardNumber(), cards[step].getCardNumber() + cards[step].getYourWinningNumbers().size))
            size = cards.size
            step++
        }
        return cards.size
    }

    val testInput = readInput("input_test")
    check(part1(testInput) == 13)
    check(part2(testInput) == 30)

    val input = readInput("input")
    part1(input).println()
    part2(input).println()
}
