package br.com.gilbercs.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.gilbercs.calculator.ui.theme.CalculatorTheme
import br.com.gilbercs.calculator.ui.theme.MediumGray
import br.com.gilbercs.calculator.ui.theme.Orange

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorTheme {
                val viewModel = viewModel<CalculatorViewModel>()
                val state = viewModel.state
                val buttonSpacin = 8.dp
                val brackgroundGradient: List<Color> = listOf(Color(0xFF4B0082,), Color(0xFFF5FFFA))
                Calculator(
                    state = state,
                    onAction = viewModel::onAction,
                    btnSpacing = buttonSpacin,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(brush = Brush.verticalGradient(colors = brackgroundGradient))
                        .padding(16.dp)
                )
            }
        }
    }
}
