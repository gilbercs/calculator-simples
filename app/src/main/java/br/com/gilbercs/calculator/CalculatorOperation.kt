package br.com.gilbercs.calculator

sealed class CalculatorOperation(val symbol: String){
    object Add: CalculatorOperation(symbol = "+")
    object Subtract: CalculatorOperation(symbol = "-")
    object Multiply: CalculatorOperation(symbol = "*")
    object Divider: CalculatorOperation(symbol = "/")
    object Igual: CalculatorOperation(symbol = "=")

}
