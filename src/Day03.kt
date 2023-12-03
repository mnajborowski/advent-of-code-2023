fun main() {
    fun Char.isSpecialCharacter(): Boolean = !equals('.') && !isDigit()

    fun List<String>.isSpecialCharacterAround(x: Int, y: Int): Boolean {
        if (getOrNull(x - 1)?.getOrNull(y - 1)?.isSpecialCharacter() == true)
            return true
        if (getOrNull(x - 1)?.getOrNull(y)?.isSpecialCharacter() == true)
            return true
        if (getOrNull(x - 1)?.getOrNull(y + 1)?.isSpecialCharacter() == true)
            return true
        if (getOrNull(x)?.getOrNull(y - 1)?.isSpecialCharacter() == true)
            return true
        if (getOrNull(x)?.getOrNull(y + 1)?.isSpecialCharacter() == true)
            return true
        if (getOrNull(x + 1)?.getOrNull(y - 1)?.isSpecialCharacter() == true)
            return true
        if (getOrNull(x + 1)?.getOrNull(y)?.isSpecialCharacter() == true)
            return true
        if (getOrNull(x + 1)?.getOrNull(y + 1)?.isSpecialCharacter() == true)
            return true
        return false
    }

    fun List<String>.getGearAdjacentNumbers(x: Int, y: Int): Set<Int> {
        val gearAdjacentNumbers = mutableListOf<Int?>()
        if (getOrNull(x - 1)?.getOrNull(y - 1)?.isDigit() == true)
            gearAdjacentNumbers.add((get(x - 1).substring(0, y).reversed().takeWhile { it.isDigit() }.reversed() + get(x - 1).substring(y).takeWhile { it.isDigit() }).toIntOrNull())
        if (getOrNull(x - 1)?.getOrNull(y)?.isDigit() == true)
            gearAdjacentNumbers.add((get(x - 1).substring(0, y).reversed().takeWhile { it.isDigit() }.reversed() + get(x - 1).substring(y).takeWhile { it.isDigit() }).toIntOrNull())
        if (getOrNull(x - 1)?.getOrNull(y + 1)?.isDigit() == true)
            gearAdjacentNumbers.add((get(x - 1).substring(0, y + 1).reversed().takeWhile { it.isDigit() }.reversed() + get(x - 1).substring(y + 1).takeWhile { it.isDigit() }).toIntOrNull())
        if (getOrNull(x)?.getOrNull(y - 1)?.isDigit() == true)
            gearAdjacentNumbers.add(get(x).substring(0, y).reversed().takeWhile { it.isDigit() }.reversed().toIntOrNull())
        if (getOrNull(x)?.getOrNull(y + 1)?.isDigit() == true)
            gearAdjacentNumbers.add(get(x).substring(y + 1).takeWhile { it.isDigit() }.toIntOrNull())
        if (getOrNull(x + 1)?.getOrNull(y - 1)?.isDigit() == true)
            gearAdjacentNumbers.add((get(x + 1).substring(0, y).reversed().takeWhile { it.isDigit() }.reversed() + get(x + 1).substring(y).takeWhile { it.isDigit() }).toIntOrNull())
        if (getOrNull(x + 1)?.getOrNull(y)?.isDigit() == true)
            gearAdjacentNumbers.add((get(x + 1).substring(0, y).reversed().takeWhile { it.isDigit() }.reversed() + get(x + 1).substring(y).takeWhile { it.isDigit() }).toIntOrNull())
        if (getOrNull(x + 1)?.getOrNull(y + 1)?.isDigit() == true)
            gearAdjacentNumbers.add((get(x + 1).substring(0, y + 1).reversed().takeWhile { it.isDigit() }.reversed() + get(x + 1).substring(y + 1).takeWhile { it.isDigit() }).toIntOrNull())
        return gearAdjacentNumbers.filterNotNull().toSet()
    }

    fun part1(input: List<String>): Int {
        val numbersToCount = mutableListOf<Int>()
        input.forEachIndexed { x, line ->
            var firstDigitIndex: Int = -1
            var shouldTakeNumber = false
            line.forEachIndexed { y, char ->
                if (firstDigitIndex == -1 && char.isDigit())
                    firstDigitIndex = y
                if (input.isSpecialCharacterAround(x, y) && char.isDigit())
                    shouldTakeNumber = true
                if (char.isDigit() && line.getOrNull(y + 1)?.isDigit() != true) {
                    if (shouldTakeNumber)
                        numbersToCount.add(line.substring(firstDigitIndex, y + 1).toInt())
                    firstDigitIndex = -1
                    shouldTakeNumber = false
                }
            }
        }
        return numbersToCount.sum()
    }

    fun part2(input: List<String>): Int {
        val gearRatiosToCount = mutableListOf<Int>()
        input.forEachIndexed { x, line ->
            line.forEachIndexed { y, char ->
                if (char == '*') {
                    val gearAdjacentNumbers = input.getGearAdjacentNumbers(x, y)
                    if (gearAdjacentNumbers.size == 2)
                        gearRatiosToCount.add(gearAdjacentNumbers.first() * gearAdjacentNumbers.last())
                }
            }
        }
        return gearRatiosToCount.sum()
    }

    val testInput = readInput("input_test")
    check(part1(testInput) == 4361)
    check(part2(testInput) == 467835)

    val input = readInput("input")
    part1(input).println()
    part2(input).println()
}
