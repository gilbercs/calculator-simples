package br.com.gilbercs.calculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CalculatorViewModel: ViewModel() {
    var state by mutableStateOf(CalculatoState())
    private set

    fun onAction(action: CalculatorActions){
        when(action){
            is CalculatorActions.Number -> enterNumber(action.number)
            is CalculatorActions.Operation -> enterOperation(action.operation)
            CalculatorActions.Calculate -> calculate()
            CalculatorActions.Clear -> state = CalculatoState()
            CalculatorActions.Decimal -> enterDecimal()
            CalculatorActions.Delete -> delete()
            null -> return
        }
    }
    private fun enterOperation(operation: CalculatorOperation){
        if (state.number1.isNotBlank()){
            state = state.copy(operation = operation)
        }
    }
    private fun calculate(){
        val number1 = state.number1.toDoubleOrNull()
        val number2 = state.number2.toDoubleOrNull()
        if (number1 !=null && number2 !=null){
            val result = when(state.operation){
                is CalculatorOperation.Add -> number1 + number2
                is CalculatorOperation.Subtract -> number1 - number2
                is CalculatorOperation.Divider -> number1 / number2
                is CalculatorOperation.Multiply -> number1 * number2
                null -> return
                else -> {
                    return
                }
            }
            state = state.copy(
                number1 = result.toString().take(15),
                number2 = "",
                operation = null

            )
        }
    }
    private fun delete(){
        when{
            state.number2.isNotBlank() -> state = state.copy(
                number2 = state.number2.dropLast(1)
            )
            state.operation != null -> state = state.copy(
                operation = null
            )
            state.number1.isNotBlank() -> state = state.copy(
                number1 = state.number1.dropLast(1)
            )
        }
    }
    private fun enterDecimal(){
        if (state.operation == null && !state.number1.contains(".") && state.number1.isNotBlank()){
            state = state.copy(
                number1 = state.number1 + "."
            )
            return
        }else
            if (!state.number2.contains(".") && state.number2.isNotBlank()){
                state = state.copy(
                    number2 = state.number2 + "."
                )
            }

    }
    private fun enterNumber(number: Int){
        if (state.operation == null){
            if (state.number1.length >= MAX_NUM_LENGTH){
                return
            }
            state = state.copy(
                number1 = state.number1 + number
            )
            return
        }
        if (state.number2.length >= MAX_NUM_LENGTH){
            return
        }
        state = state.copy(
            number2 = state.number2 + number
        )
    }
    companion object{
        private const val MAX_NUM_LENGTH = 8
    }
}