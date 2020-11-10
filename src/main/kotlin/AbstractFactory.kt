import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

interface Athlete {
    val score: Int
    val matches: Int
}

data class SoccerAthlete(
    override val score: Int,
    override val matches: Int
) : Athlete

data class BasketballAthlete(
    override val score: Int,
    override val matches: Int

) : Athlete

abstract class AthleteFactory {

    abstract fun createAthlete(score: Int, playedMinutes: Int): Athlete

    companion object {
        inline fun <reified T : Athlete> createFactory() : AthleteFactory =
            when (T::class) {
                SoccerAthlete::class -> SoccerAthleteFactory()
                BasketballAthlete::class -> BasketballAthleteFactory()
                else -> throw IllegalArgumentException()
            }
    }

}

class SoccerAthleteFactory : AthleteFactory() {

    override fun createAthlete(score: Int, playedMinutes: Int): Athlete {
        val matches = playedMinutes / 90

        return SoccerAthlete(score, matches)
    }

}

class BasketballAthleteFactory : AthleteFactory() {

    override fun createAthlete(score: Int, playedMinutes: Int): Athlete {
        val matches = playedMinutes / 48

        return BasketballAthlete(score, matches)
    }

}

class AbstractFactoryTest {

    @Test
    fun test() {
        val soccerAthleteFactory = AthleteFactory.createFactory<SoccerAthlete>()
        val soccerAthlete = soccerAthleteFactory.createAthlete(12, 900)

        val basketballAthleteFactory = AthleteFactory.createFactory<BasketballAthlete>()
        val basketballAthlete = basketballAthleteFactory.createAthlete(300, 528)

        assertTrue(soccerAthlete is SoccerAthlete)
        assertTrue(basketballAthlete is BasketballAthlete)
        assertEquals(10, soccerAthlete.matches)
        assertEquals(11, basketballAthlete.matches)
    }
}