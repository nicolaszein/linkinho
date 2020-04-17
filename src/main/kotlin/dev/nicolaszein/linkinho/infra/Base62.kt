package dev.nicolaszein.linkinho.infra

import org.springframework.stereotype.Service
import java.math.BigInteger

@Service
class Base62() {
    private val charSet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"

    fun encode(value: BigInteger): String {
        if (value == 0.toBigInteger()) {
            return charSet[0].toString()
        }

        var result = ""
        var divisionRemainder: Int
        var divisionResult = value
        val length = 62.toBigInteger()

        while (divisionResult > 0.toBigInteger()) {
            divisionRemainder = divisionResult.rem(length).toInt()
            divisionResult /= length

            result = result.plus(charSet[divisionRemainder])
        }

        return result.reversed()
    }
}
