package com.example.calculator11

class ParsingAndCalculating {
    private val addCommand = AddCommand()
    private val subCommand = SubCommand()
    private val devCommand = DevCommand()
    private val mulCommand = MulCommand()

    fun parser(operation: String): List<String> {
        val cleanOperation = operation.filter {
            !it.isWhitespace()
        }
            .replace("*", " * ")
            .replace("/", " / ")
            .replace("+", " + ")
            .replace("-", " - ")
            .replace("(", " ( ")
            .replace(")", " ) ")
            .replace("  ", " ")
            .trim()

        return cleanOperation.split(" ");
    }

    fun calculate(operation: String): Double {
        val operators = parser(operation)
        val operationQueue = mutableListOf<Any>()

        val nestedOperation = mutableListOf<Any>()
        var openNestedOperations = 0

        val zeroPriorityIterator = operators.iterator()
        for ((index) in zeroPriorityIterator.withIndex()) {

            if(operators[index] == '('.toString() || nestedOperation.count() > 0){

                if(operators[index] == '('.toString()) openNestedOperations++;
                if(operators[index] == ')'.toString()) openNestedOperations--;
                nestedOperation.add(operators[index])

                if (operators[index] == ')'.toString() && openNestedOperations == 0) {

                    nestedOperation.removeFirst()
                    nestedOperation.removeLast()

                    val nestedStringOperation = nestedOperation.joinToString(separator = " ")
                    val result:Any = calculate(nestedStringOperation) as Any
                    operationQueue.add(result)
                    nestedOperation.clear()
                }
            } else {
                operationQueue.add(operators[index]);
            }
        }

        var processedOperators = 0
        val tempList = mutableListOf<Any>()

        val firstPriorityIterator = operationQueue.iterator()
        for ((index) in firstPriorityIterator.withIndex()) {

            if (processedOperators > 0) {
                processedOperators--
                continue
            }

            val next = index + 1

            if (operationQueue[index] == '*'.toString()) {
                val value = tempList.removeLastOrNull()
                val result =
                    mulCommand.execute(value.toString().toDouble(),operationQueue[next].toString()
                        .toDouble())
                tempList.add(result)
                processedOperators++
            } else if (operationQueue[index] == '/'.toString()) {
                if (operationQueue[next].toString().toDouble() == 0.0)
                    return 0.0

                val value = tempList.removeLastOrNull()
                val result =
                    devCommand.execute(value.toString().toDouble(), operationQueue[next].toString()
                        .toDouble())
                tempList.add(result)
                processedOperators++
            } else {
                tempList.add(operationQueue[index]);
            }
        }

        operationQueue.clear()
        operationQueue.addAll(tempList)

        var result:Double = operationQueue.removeFirst().toString().toDouble()

        val secondPriorityIterator = operationQueue.iterator()
        for ((index) in secondPriorityIterator.withIndex()) {
            if((index + 1) % 2 > 0 && operationQueue.count() > index+1){
                val nextOperator = index + 1
                if (operationQueue[index] == '+'.toString()) {
                    result = addCommand.execute(result, operationQueue[nextOperator].toString()
                        .toDouble())
                } else if (operationQueue[index] == '-'.toString()) {
                    result = subCommand.execute(result, operationQueue[nextOperator].toString()
                        .toDouble())
                }
            }
        }

        return result
    }

    fun checkString (str: String?) : Boolean {
        if (str!!.contains("[0-9]".toRegex()) && str.contains("[+./()-,*]".toRegex())) {
            return true
        }
        return false
    }

}