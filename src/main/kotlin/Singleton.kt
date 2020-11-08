import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

object Singleton {

    init {
        println("init= $this")
    }

}

class SingletonTest {

    @Test
    fun test() {
        assertEquals(Singleton, Singleton)
    }

}