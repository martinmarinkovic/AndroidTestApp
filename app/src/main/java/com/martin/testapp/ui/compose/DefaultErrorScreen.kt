package com.martin.testapp.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.martin.testapp.R
import com.martin.testapp.ui.compose.theme.TestAppTheme

@Composable
fun DefaultErrorScreen(
    errorMessage: String,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.onPrimary),
    ) {
        Text(
            modifier = Modifier.padding(20.dp),
            text = errorMessage,
        )
    }
}

@Preview
@Composable
fun DefaultErrorComposablePreview() {
    TestAppTheme {
        DefaultErrorScreen(
            errorMessage = stringResource(id = R.string.error_message),
        )
    }
}