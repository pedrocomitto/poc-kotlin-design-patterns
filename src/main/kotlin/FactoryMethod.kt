import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

interface Person {
    val name: String
    val gender: String
}

class Man(override val name: String) : Person {
    override val gender = "M"
}

class Woman(override val name: String) : Person {
    override val gender = "F"
}

object PersonFactory {

    fun of(name: String, gender: String): Person =
        when (gender) {
            "M" -> Man(name)
            "F" -> Woman(name)
            else -> throw IllegalArgumentException()
        }

}

class FactoryMethodTest {

    @Test
    fun test() {
        val pedro = PersonFactory.of("Pedro", "M")
        val ana = PersonFactory.of("Ana", "F")

        assertTrue(pedro is Man)
        assertTrue(ana is Woman)
    }
}