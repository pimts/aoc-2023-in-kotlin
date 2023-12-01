private fun String.removeNonDigits() = replace("""[^0-9]""".toRegex(), "")

private fun String.markSpelledOutDigits(): String {
    val result = toCharArray()
    for ((index, c) in withIndex()) {
        when (c) {
            'o' -> if (substring(index).take(3) == "one") result[index] = '1'
            't' -> {
                when {
                    substring(index).take(3) == "two" -> result[index] = '2'
                    substring(index).take(5) == "three" -> result[index] = '3'
                }
            }
            'f' -> {
                when {
                    substring(index).take(4) == "four" -> result[index] = '4'
                    substring(index).take(4) == "five" -> result[index] = '5'
                }
            }
            's' -> {
                when {
                    substring(index).take(3) == "six" -> result[index] = '6'
                    substring(index).take(5) == "seven" -> result[index] = '7'
                }
            }
            'e' -> if (substring(index).take(5) == "eight") result[index] = '8'
            'n' -> if (substring(index).take(4) == "nine") result[index] = '9'
        }
    }
    return result.joinToString(separator = "")
}

fun main() {
    fun part1(input: List<String>) =
        input.map { it.removeNonDigits() }
            .sumOf {
                "${it.first()}${it.last()}".toInt()
            }

    fun part2(input: List<String>) =
        input.map {
            it.markSpelledOutDigits().removeNonDigits()
        }.sumOf {
            "${it.first()}${it.last()}".toInt()
        }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    part2(testInput).println()
    check(part2(testInput) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
