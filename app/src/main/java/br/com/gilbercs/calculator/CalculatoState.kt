package br.com.gilbercs.calculator

data class CalculatoState(
    val number1: String = "",
    val number2: String = "",
    val operation: CalculatorOperation? = null
)
