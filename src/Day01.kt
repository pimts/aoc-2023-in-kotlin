private val digitsMap = mapOf(
    "one" to 1, "1" to 1,
    "two" to 2, "2" to 2,
    "three" to 3, "3" to 3,
    "four" to 4, "4" to 4,
    "five" to 5, "5" to 5,
    "six" to 6, "6" to 6,
    "seven" to 7, "7" to 7,
    "eight" to 8, "8" to 8,
    "nine" to 9, "9" to 9
)
private val nonDigitRegex = """[^0-9]""".toRegex()
private val digitsRegex = """(?=(\d|one|two|three|four|five|six|seven|eight|nine))""".toRegex()

private fun String.removeNonDigits() = replace(nonDigitRegex, "")

fun main() {
    fun part1(input: List<String>) =
        input.map { it.removeNonDigits() }
            .sumOf {
                "${it.first()}${it.last()}".toInt()
            }

    fun part2(input: List<String>): Int {
        return input.map { line ->
            digitsRegex.findAll(line)
                .flatMap { matches ->
                    matches.groupValues.filter { string ->
                        string.isNotEmpty()
                    }
                }.map { digitsMap[it]!! }
                .toList()
        }.sumOf {
            "${it.first()}${it.last()}".toInt()
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part2(testInput) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
