package arhive

import kotlin.random.Random
import kotlin.random.nextInt

class SavedMethods {


    fun generateString(length: Int): String {
        val allowedChars = ('A'..'Z') + ('a'..'z')  // Allowed characters for the random string
        return (1..length)
            .map { allowedChars.random() }  // Randomly select characters from allowedChars
            .joinToString("")  // Convert the list of characters to a string
    }
    fun generateRandomNumberInRange(min: Int, max: Int, count:Int): String {
        require(min < max) { "min must be less than max" }
        require(count >= 0) { "count must be non-negative" }

        val randomNumbers = List(count) { Random.nextInt(min..max) }
        return randomNumbers.joinToString("")
    }


}