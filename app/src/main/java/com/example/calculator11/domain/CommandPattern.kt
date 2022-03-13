package com.example.calculator11.domain

class CommandPattern : Multiply, Divide, Plus, Minus {
    override fun multiply(num1: Double, num2: Double): Double {
        return num1 * num2
    }

    override fun divide(num1: Double, num2: Double): Double {
        return num1 / num2
    }

    override fun plus(num1: Double, num2: Double): Double {
        return num1 + num2
    }

    override fun minus(num1: Double, num2: Double): Double {
        return num1 - num2
    }
}

interface Multiply { fun multiply(num1: Double, num2: Double): Double }

interface Divide { fun divide(num1: Double, num2: Double): Double }

interface Plus { fun plus(num1: Double, num2: Double): Double }

interface Minus { fun minus(num1: Double, num2: Double): Double }