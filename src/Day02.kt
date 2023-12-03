fun main() {
    fun part1(input: List<String>): Int {
        var sumOfGameIds = 0
        input.forEach { line ->
            val gameId = line.substringBefore(':').filter { it.isDigit() }.toInt()
            val cubes = line.substringAfter(':').replace(" ", "").split(',', ';')
            val redCubesNotExceeded =
                cubes.filter { it.contains("red") }.all { redCubes -> redCubes.filter { it.isDigit() }.toInt() <= 12 }
            val greenCubesNotExceeded =
                cubes.filter { it.contains("green") }.all { redCubes -> redCubes.filter { it.isDigit() }.toInt() <= 13 }
            val blueCubesNotExceeded =
                cubes.filter { it.contains("blue") }.all { redCubes -> redCubes.filter { it.isDigit() }.toInt() <= 14 }
            if (redCubesNotExceeded && greenCubesNotExceeded && blueCubesNotExceeded)
                sumOfGameIds += gameId
        }
        return sumOfGameIds
    }

    fun part2(input: List<String>): Int {
        var sumOfGamePowers = 0
        input.forEach { line ->
            val cubes = line.substringAfter(':').replace(" ", "").split(',', ';')
            val redCubesMax =
                cubes.filter { it.contains("red") }.maxOf { redCubes -> redCubes.filter { it.isDigit() }.toInt() }
            val greenCubesMax =
                cubes.filter { it.contains("green") }.maxOf { redCubes -> redCubes.filter { it.isDigit() }.toInt() }
            val blueCubesMax =
                cubes.filter { it.contains("blue") }.maxOf { redCubes -> redCubes.filter { it.isDigit() }.toInt() }
            sumOfGamePowers += redCubesMax * greenCubesMax * blueCubesMax
        }
        return sumOfGamePowers
    }

    val testInput = readInput("input_test")
    check(part1(testInput) == 8)
    check(part2(testInput) == 2286)

    val input = readInput("input")
    part1(input).println()
    part2(input).println()
}
