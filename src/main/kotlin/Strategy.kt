import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// example 1
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

// example 2
class Printer(private val stringFormatterStrategy: (String) -> String) {

    fun print(string: String) =
        println(stringFormatterStrategy(string))

}

val lowerCaseFormatter: (String) -> String = { it.toLowerCase() }
val upperCaseFormatter = { it: String -> it.toUpperCase() }

// example 3
interface Calculator {

    fun calculate(firstNumber: Int, secondNumber: Int): Int

    companion object {
        fun of(operation: Operation) =
            when (operation) {
                Operation.ADDITION -> Addition
                Operation.SUBTRACTION -> Subtraction
            }
    }
}

object Addition : Calculator {

    override fun calculate(firstNumber: Int, secondNumber: Int) =
        firstNumber + secondNumber

}

object Subtraction : Calculator {

    override fun calculate(firstNumber: Int, secondNumber: Int) =
        firstNumber - secondNumber

}

data class AnyObject(
    val firstNumber: Int,
    val secondNumber: Int,
    val operation: Operation
)

enum class Operation {
    ADDITION,
    SUBTRACTION
}

class StrategyTest {

    @Test
    fun example1Test() {
        val turtle = Animal(LowJumpStrategy()).jump()
        val rabbit = Animal(HighJumpStrategy()).jump()

        assertEquals("Jumping low", turtle)
        assertEquals("Jumping high", rabbit)
    }

    @Test
    fun example2Test() {
        val inputString = "Testing OUR singleton PRINTER"

        val lowerCasePrinter = Printer(lowerCaseFormatter)
        lowerCasePrinter.print(inputString)

        val upperCasePrinter = Printer(upperCaseFormatter)
        upperCasePrinter.print(inputString)

        val prefixPrinter = Printer { "Prefix: $it" }
        prefixPrinter.print(inputString)
    }

    @Test
    fun example3Test() {
        val additionObject = AnyObject(5, 2, Operation.ADDITION)
        val subtractionObject = AnyObject(5, 2, Operation.SUBTRACTION)

        val additionResult = Calculator.of(additionObject.operation)
            .calculate(additionObject.firstNumber, additionObject.secondNumber)

        val subtractionResult = Calculator.of(subtractionObject.operation)
            .calculate(subtractionObject.firstNumber, subtractionObject.secondNumber)

        assertEquals(7, additionResult)
        assertEquals(3, subtractionResult)
    }
}
