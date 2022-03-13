package com.example.calculator11.presentation

import com.example.calculator11.domain.ParsingAndCalculating
import java.util.*

class Manager() {

    private val calc = ParsingAndCalculating()
    private val stopWord = "finish"
    private val err = "You have entered incorrect data!"
    var result: Number = 0

    private fun scanning() {
        println("Please, input an expression")
        val scan = Scanner(System.`in`)
        val expression = scan.nextLine()

        while (true) {
            if (expression == stopWord) {
                println("Your final result is: $result \n\tBye!")
                return
            }
            if (calc.checkString(expression)) {
                println(err)
                scanning()
            }
            result = calc.calculate(expression!!)
            println(result)
            break
        }

        println("If you want to continue input 1, else input 0")
        val tempInt = scan.nextInt()
        when (tempInt) {
            1 -> scanning()
            0 -> {
                println("Your final result is: $result \n\tBye!")
                return
            }
            else -> println("You have entered incorrect data!")
        }
    }
}