package org.logic_gates_book.ui.screens.book

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import logic_gates_book_aidw.composeapp.generated.resources.Res
import logic_gates_book_aidw.composeapp.generated.resources.and
import logic_gates_book_aidw.composeapp.generated.resources.baseline_account_tree_24
import logic_gates_book_aidw.composeapp.generated.resources.baseline_info_24
import logic_gates_book_aidw.composeapp.generated.resources.latch
import logic_gates_book_aidw.composeapp.generated.resources.not
import logic_gates_book_aidw.composeapp.generated.resources.or
import logic_gates_book_aidw.composeapp.generated.resources.wire
import logic_gates_book_aidw.composeapp.generated.resources.xor
import org.jetbrains.compose.resources.painterResource
import org.logic_gates_book.HapticFeedback
import org.logic_gates_book.ui.navigation.NavControllerBook
import org.logic_gates_book.ui.screens.book.pags.CreditsScreen
import org.logic_gates_book.ui.screens.book.pags.Pag1
import org.logic_gates_book.ui.screens.book.pags.Pag2
import org.logic_gates_book.ui.screens.book.pags.Pag3
import org.logic_gates_book.ui.screens.book.pags.Pag4
import org.logic_gates_book.ui.screens.book.pags.Pag5
import org.logic_gates_book.ui.screens.book.pags.Pag6
import org.logic_gates_book.ui.screens.book.pags.Pag7
import org.logic_gates_book.ui.screens.book.pags.Pag8
import org.logic_gates_book.ui.screens.book.pags.TutorialScreen
import org.logic_gates_book.ui.theme.MaterialThemeApp

/**
 * Composable principal para la pantalla del libro que maneja la navegación y la visualización de las páginas.
 *
 * Esta pantalla se encarga de gestionar el contenido de un libro interactivo, donde el usuario puede navegar
 * a través de diferentes páginas con contenido relacionado con compuertas lógicas. Las páginas incluyen
 * información interactiva sobre compuertas como AND, OR, NOT, XOR, entre otras.
 *
 * @param navController Controlador de navegación utilizado para cambiar entre las pantallas del libro.
 */
@Composable
fun BookScreen(
    navController: NavHostController,
    hapticFeedback: HapticFeedback,
    screenWidth: Float
) = MaterialThemeApp { Screen(navController, hapticFeedback, screenWidth) }

/**
 * Composable que organiza la navegación entre las pantallas dentro del libro.
 * @param navControllerHome Controlador de navegación principal de la aplicación.
 */
@Composable
fun Screen(
    navControllerHome: NavHostController,
    hapticFeedback: HapticFeedback,
    screenWidth: Float
) {
    val navController = rememberNavController()
    NavControllerBook(
        navController = navController,
        navControllerHome = navControllerHome,
        hapticFeedback = hapticFeedback,
        screenWidth = screenWidth
    )
}

/**
 * Composable que maneja la lógica de presentación de una página del libro.
 * Controla la interacción con las compuertas lógicas y permite al usuario avanzar o retroceder entre las páginas.
 *
 * @param pageNumber El número de la página que se está mostrando.
 * @param navController Controlador de navegación utilizado para mover entre páginas.
 * @param onNextPage Función que se llama cuando el usuario avanza a la siguiente página.
 * @param onPreviousPage Función que se llama cuando el usuario retrocede a la página anterior.
 */
@Composable
fun PageScreen(
    pageNumber: Int,
    navController: NavController,
    hapticFeedback: HapticFeedback,
    screenWidth: Float,
    onNextPage: () -> Unit,
    onPreviousPage: () -> Unit
) {
    val active = remember { mutableStateOf(false) }
    val activeOr = remember { mutableStateOf(false) }
    val activeDouble = remember { mutableStateOf(false) }
    val activeXor = remember { mutableStateOf(false) }
    val activeLatch = remember { mutableStateOf(false) }
    val complete = remember { mutableStateOf(false) }
    val touch = remember { mutableIntStateOf(0) }
    val tutorial = remember { mutableStateOf(false) }
    val gate = remember { mutableStateOf(false) }

    Pages(
        pageNumber = pageNumber,
        active = active,
        touch = touch,
        activeDouble = activeDouble,
        activeXor = activeXor,
        activeLatch = activeLatch,
        navController = navController,
        screenWidth = screenWidth
    )

    if (pageNumber < 10)
        Controller(
            active = active,
            activeOr = activeOr,
            activeDouble = activeDouble,
            activeXor = activeXor,
            activeLatch = activeLatch,
            complete = complete,
            hapticFeedback = hapticFeedback,
            pageNumber = pageNumber,
            touch = touch,
            onNextPage = onNextPage,
            onPreviousPage = onPreviousPage,
            gate = gate,
            screenWidth = screenWidth
        )

    Tutorial(
        pageNumber = pageNumber,
        tutorial = tutorial,
        screenWidth = screenWidth
    )

    Gate(
        pageNumber = pageNumber,
        gate = gate
    )
}

/**
 * Composable que muestra la imagen de una compuerta lógica según la página activa.
 * Las imágenes se muestran con una animación que puede ser activada por el usuario.
 *
 * @param pageNumber El número de la página activa que determina qué imagen de compuerta se debe mostrar.
 * @param gate Estado mutable que determina si la imagen de la compuerta debe ser visible o no.
 */
@Composable
private fun Gate(pageNumber: Int, gate: MutableState<Boolean>) {
    val gates = remember {
        mutableListOf(
            Res.drawable.wire,
            Res.drawable.not,
            Res.drawable.or,
            Res.drawable.and,
            Res.drawable.xor,
            Res.drawable.latch
        )
    }

    // Condición para mostrar la imagen de compuerta
    if (pageNumber in 3..8) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            IconButton(onClick = { gate.value = !gate.value }) {
                Icon(
                    painter = painterResource(Res.drawable.baseline_account_tree_24),
                    contentDescription = "Mostrar tutorial",
                    tint = colorScheme.tertiary
                )
            }
        }
    }

    AnimatedVisibility(
        visible = gate.value,
        enter = fadeIn(animationSpec = tween(1000)),
        exit = fadeOut(animationSpec = tween(1000))
    ) {
        Box(
            modifier = Modifier
                .size(400.dp)
                .clickable { gate.value = false },
            contentAlignment = Alignment.Center,
        ) {
            when (pageNumber) {
                3 -> Image(
                    painter = painterResource(gates[0]),
                    contentDescription = "Wire",
                    modifier = Modifier
                        .size(400.dp)
                        .clip(RoundedCornerShape(30.dp))
                )

                4 -> Image(
                    painter = painterResource(gates[1]),
                    contentDescription = "Not",
                    modifier = Modifier
                        .size(400.dp)
                        .clip(RoundedCornerShape(30.dp))
                )

                5 -> Image(
                    painter = painterResource(gates[2]),
                    contentDescription = "Or",
                    modifier = Modifier
                        .size(400.dp)
                        .clip(RoundedCornerShape(30.dp))
                )

                6 -> Image(
                    painter = painterResource(gates[3]),
                    contentDescription = "And",
                    modifier = Modifier
                        .size(400.dp)
                        .clip(RoundedCornerShape(30.dp))
                )

                7 -> Image(
                    painter = painterResource(gates[4]),
                    contentDescription = "Xor",
                    modifier = Modifier
                        .size(400.dp)
                        .clip(RoundedCornerShape(30.dp))
                )
            }
        }
    }
}

/**
 * Composable que maneja la lógica de interacción táctil en la pantalla de la página del libro.
 * Detecta toques simples y dobles y controla el avance/retroceso de la navegación.
 *
 * @param active Estado que indica si una compuerta lógica está activa.
 * @param activeOr Estado que indica si la compuerta OR está activa.
 * @param activeDouble Estado que indica si hay un toque doble en la pantalla.
 * @param activeXor Estado que indica si la compuerta XOR está activa.
 * @param activeLatch Estado que indica si la compuerta LATCH está activa.
 * @param complete Estado que indica si la página está completa y lista para avanzar.
 * @param pageNumber El número de la página que se está mostrando.
 * @param gate Estado mutable que determina si la imagen de la compuerta debe ser visible o no.
 * @param touch Contador que lleva el seguimiento del número de toques simultáneos.
 * @param onNextPage Función para ir a la siguiente página.
 * @param onPreviousPage Función para ir a la página anterior.
 */
@Composable
private fun Controller(
    active: MutableState<Boolean>,
    activeOr: MutableState<Boolean>,
    activeDouble: MutableState<Boolean>,
    activeXor: MutableState<Boolean>,
    activeLatch: MutableState<Boolean>,
    complete: MutableState<Boolean>,
    pageNumber: Int,
    hapticFeedback: HapticFeedback,
    gate: MutableState<Boolean>,
    touch: MutableIntState,
    screenWidth: Float,
    onNextPage: () -> Unit,
    onPreviousPage: () -> Unit,
) {
    val color = animateColorAsState(
        targetValue = if (!gate.value) Color.Transparent else Color.Black.copy(alpha = 0.9f),
        animationSpec = tween(durationMillis = 500), label = ""
    )

    val centerZoneStart = 0.3f * screenWidth
    val centerZoneEnd = 0.7f * screenWidth
    val leftZoneEnd = 0.3f * screenWidth
    val rightZoneStart = 0.7f * screenWidth

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color.value)
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = { offset ->
                        if (active.value || activeDouble.value || activeXor.value || activeLatch.value || pageNumber == 1 || pageNumber == 2)
                            complete.value = true

                        if (offset.x in centerZoneStart..centerZoneEnd) {
                            active.value = true
                            activeLatch.value = true

                            hapticFeedback.vibrate(50)

                            tryAwaitRelease()
                            active.value = false
                        } else {
                            if (offset.x < leftZoneEnd && pageNumber > 2) {
                                onPreviousPage()
                            } else if (offset.x > rightZoneStart && pageNumber < 10) {
                                if (complete.value || pageNumber == 9) {
                                    onNextPage()
                                    complete.value = false
                                }
                            }
                        }
                    },
                    onDoubleTap = {
                        activeLatch.value = false
                    }
                )
            }
            .pointerInput(Unit) {
                awaitPointerEventScope {
                    while (true) {
                        val event = awaitPointerEvent()
                        val pointers = event.changes

                        when (pointers.size) {
                            1 -> {
                                val singleTouchInCenter = pointers.all { change ->
                                    change.position.x in centerZoneStart..centerZoneEnd && change.pressed
                                }
                                touch.intValue = 1
                                activeOr.value = true
                                activeDouble.value = false
                                activeXor.value = singleTouchInCenter
                            }

                            2 -> {
                                val bothTouchesInCenter = pointers.all { change ->
                                    change.position.x in centerZoneStart..centerZoneEnd && change.pressed
                                }
                                touch.intValue = 2
                                activeOr.value = true
                                activeDouble.value = bothTouchesInCenter
                                activeXor.value = false
                            }

                            else -> {
                                touch.intValue = 0
                                activeOr.value = false
                                activeDouble.value = false
                                activeXor.value = false
                            }
                        }
                    }
                }
            }
    )
}

/**
 * Composable que muestra el contenido de la página basado en el número de página.
 * Cada página contiene diferentes tipos de información interactiva sobre compuertas lógicas.
 *
 * @param pageNumber El número de la página actual.
 * @param active Estado mutable que indica si una compuerta está activa.
 * @param touch Estado mutable que indica el número de toques detectados en la pantalla.
 * @param activeDouble Estado mutable que indica si hay un toque doble en la pantalla.
 * @param activeXor Estado mutable que indica si la compuerta XOR está activa.
 * @param activeLatch Estado mutable que indica si la compuerta LATCH está activa.
 * @param navController Controlador de navegación para cambiar entre las páginas.
 */
@Composable
private fun Pages(
    pageNumber: Int,
    active: MutableState<Boolean>,
    touch: MutableIntState,
    activeDouble: MutableState<Boolean>,
    activeXor: MutableState<Boolean>,
    activeLatch: MutableState<Boolean>,
    screenWidth: Float,
    navController: NavController
) {
    when (pageNumber) {
        1 -> TutorialScreen(screenWidth) //Tutorial
        2 -> Pag1() //Introducción
        3 -> Pag2(active.value) //Wire
        4 -> Pag3(active.value) //Not
        5 -> Pag4(active.value, touch.intValue) //Or
        6 -> Pag5(activeDouble.value) //And
        7 -> Pag6(activeXor.value) //Xor
        8 -> Pag7(activeLatch.value) //Latch
        9 -> Pag8() //Final
        10 -> CreditsScreen(navController = navController) //Créditos
    }
}

/**
 * Composable que maneja la visualización de un tutorial interactivo en las páginas del libro.
 * El tutorial proporciona instrucciones o información adicional sobre las compuertas lógicas.
 *
 * @param pageNumber El número de la página actual.
 * @param tutorial Estado mutable que indica si el tutorial está visible o no.
 */
@Composable
fun Tutorial(pageNumber: Int, tutorial: MutableState<Boolean>, screenWidth: Float) {
    if (pageNumber >= 3 && pageNumber <= 8) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopEnd
        ) {
            IconButton(onClick = { tutorial.value = !tutorial.value }) {
                Icon(
                    painter = painterResource(Res.drawable.baseline_info_24),
                    contentDescription = "Mostrar tutorial",
                    tint = colorScheme.tertiary
                )
            }
        }
    }
    // Animación de visibilidad del tutorial
    AnimatedVisibility(
        tutorial.value,
        enter = fadeIn(animationSpec = tween(1000)),
        exit = fadeOut(animationSpec = tween(1000))
    ) {
        Box(
            modifier = Modifier
                .clickable { tutorial.value = false }, // Hacer clic fuera cierra el tutorial
            contentAlignment = Alignment.Center
        ) {
            org.logic_gates_book.ui.components.Tutorial(screenWidth )
        }
    }
}