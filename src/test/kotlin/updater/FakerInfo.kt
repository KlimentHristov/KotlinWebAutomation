package updater

import com.github.javafaker.Faker

object FakerInfo {
    lateinit var fake: Faker

    init {
        fake = Faker()
    }

    fun randomFullName(): String{
        return fake.name().fullName()
    }
    fun randomEGN(): String{
        return fake.number().digits(10)
    }
    fun randomPostCode(): String{
        return fake.number().digits(4)
    }
    fun randomADDRESS(): String{
        return fake.address().streetAddress()
    }
    fun randomCITY(): String{
        return fake.address().city()
    }
    fun randomPhoneNumber(): String{
        val areaCodes = listOf("089", "087", "088", "098")
        val randomAreaCode = areaCodes.random()
        val phoneNumber = fake.number().digits(7)
        return "$randomAreaCode$phoneNumber"
    }
    fun randomIBAN():String{
        return fake.finance().iban()
    }



}