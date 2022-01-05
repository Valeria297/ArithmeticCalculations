package com.example.calculator11

abstract class Command() {
    abstract fun execute(toDouble: Double, toDouble1: Double): Double?
}

class AddCommand() : Command() {
    override fun execute(num1: Double, num2: Double): Double {
        return num1 + num2
    }
}

class SubCommand() : Command() {
    override fun execute(num1: Double, num2: Double): Double {
        return  num1 - num2
    }
}

class DevCommand() : Command() {
    override fun execute(num1: Double, num2: Double): Double {
        return num1 / num2
    }
}

class MulCommand() : Command() {
    override fun execute(num1: Double, num2: Double): Double {
        return num1 * num2
    }
}