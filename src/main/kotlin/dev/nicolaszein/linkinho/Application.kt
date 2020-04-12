package dev.nicolaszein.linkinho

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LinkinhoApplication

fun main(args: Array<String>) {
    runApplication<LinkinhoApplication>(*args)
}
