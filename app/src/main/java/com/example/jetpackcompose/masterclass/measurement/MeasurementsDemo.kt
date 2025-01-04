package com.example.jetpackcompose.masterclass.measurement

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme


private val boundedConstraints = Constraints(
    minWidth = 2000,
    maxWidth = 2000,
    minHeight = 0,
    maxHeight = 3000
)

private val unboundedConstraints = Constraints()

private val exactConstraints = Constraints(
    minWidth = 50,
    maxWidth = 50,
    minHeight = 100,
    maxHeight = 100
)

private val combinedConstraints = Constraints(
    minWidth = 50,
    maxWidth = 50,
    minHeight = 50,
    maxHeight = Constraints.Infinity
)

@Composable
fun MeasurementsDemo(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .background(Color.Red)
            .padding(16.dp)
    ) {
        Text(
            text = "This is a text",
            modifier = Modifier
                .background(Color.Yellow)
        )
        Text(
            text = "This is another text",
            modifier = Modifier
                .background(Color.Green)
        )
    }
}

@Preview
@Composable
private fun MeasurementsDemoPreview() {
    JetpackComposeTheme {
        MeasurementsDemo()
    }
}