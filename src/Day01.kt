fun main() {
    fun part1(input: List<String>): Int = input.sumOf { line ->
        "${line.first { it.isDigit() }}${line.last { it.isDigit() }}".toInt()
    }

    fun part2(input: List<String>): Int {
        val digitWords = mapOf(
            "one" to "1",
            "two" to "2",
            "three" to "3",
            "four" to "4",
            "five" to "5",
            "six" to "6",
            "seven" to "7",
            "eight" to "8",
            "nine" to "9"
        )
        return input.sumOf { line ->
            val firstDigitOrWord = line.findAnyOf(digitWords.keys + digitWords.values)!!
                .let { (_, digitOrWord) -> digitWords.getOrDefault(digitOrWord, digitOrWord) }
            val lastDigitOrWord = line.findLastAnyOf(digitWords.keys + digitWords.values)!!
                .let { (_, digitOrWord) -> digitWords.getOrDefault(digitOrWord, digitOrWord) }
            "$firstDigitOrWord$lastDigitOrWord".toInt()
        }
    }

    val testInput = readInput("input_test")
    check(part1(testInput) == 142)
    check(part2(testInput) == 281)

    val input = readInput("input")
    part1(input).println()
    part2(input).println()
}
