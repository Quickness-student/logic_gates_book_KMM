package org.logic_gates_book

import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.logic_gates_book.ui.Main
import org.logic_gates_book.ui.theme.MaterialThemeApp

@Composable
@Preview
fun App(hapticFeedback: HapticFeedback, uri: Uri) = MaterialThemeApp { Main(hapticFeedback, uri) }