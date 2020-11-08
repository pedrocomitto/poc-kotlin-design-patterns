import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Animal(
    private val jumpStrategy: JumpStrategy
) {

    fun jump() =
        jumpStrategy.jump()

}

interface JumpStrategy {
    fun jump(): String
}

class HighJumpStrategy : JumpStrategy {

    override fun jump() =
        "Jumping high"


}

class LowJumpStrategy : JumpStrategy {

    override fun jump() =
        "Jumping low"

}


class Printer(private val stringFormatterStrategy: (String) -> String) {

    fun print(string: String) =
        println(stringFormatterStrategy(string))

}

val lowerCaseFormatter: (String) -> String = { it.toLowerCase() }
val upperCaseFormatter = { it: String -> it.toUpperCase() }


class StrategyTest {

    @Test
    fun animalTest() {
        val turtle = Animal(LowJumpStrategy()).jump()
        val rabbit = Animal(HighJumpStrategy()).jump()

        assertEquals(turtle, "Jumping low")
        assertEquals(rabbit, "Jumping high")
    }

    @Test
    fun printerTest() {
        val inputString = "Testing OUR singleton PRINTER"

        val lowerCasePrinter = Printer(lowerCaseFormatter)
        lowerCasePrinter.print(inputString)

        val upperCasePrinter = Printer(upperCaseFormatter)
        upperCasePrinter.print(inputString)

        val prefixPrinter = Printer { "Prefix: $it" }
        prefixPrinter.print(inputString)
    }
}
