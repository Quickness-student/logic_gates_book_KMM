package org.logic_gates_book.ui.screens.book.pags

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import logic_gates_book_aidw.composeapp.generated.resources.Res
import logic_gates_book_aidw.composeapp.generated.resources.pagina3
import logic_gates_book_aidw.composeapp.generated.resources.pagina4
import logic_gates_book_aidw.composeapp.generated.resources.pagina5
import logic_gates_book_aidw.composeapp.generated.resources.pagina6
import logic_gates_book_aidw.composeapp.generated.resources.pagina7
import logic_gates_book_aidw.composeapp.generated.resources.pagina8
import logic_gates_book_aidw.composeapp.generated.resources.pagina9
import org.jetbrains.compose.resources.painterResource
import org.logic_gates_book.ui.components.Fire

/**
 * Pantalla de introducción del libro donde el texto se muestra de manera progresiva.
 *
 * Esta función crea una animación de texto, donde un fragmento de narrativa se muestra
 * letra por letra, simulando una escritura progresiva. El texto es una introducción a
 * la historia dentro del libro interactivo y proporciona un inicio inmersivo para el usuario.
 *
 * @Composable
 * Función que representa la pantalla inicial con la narrativa del libro.
 */
@Composable
fun Pag1() {
    // Texto completo que se quiere mostrar de manera progresiva
    val fullText = """
        Los rumores eran ciertos en el reino de Itsu. Una cueva más antigua que la tierra misma del hombre existe en las profundidades de la montaña llamada "Sistema", y guarda un tesoro más valioso que todo el oro de los reyes de la tierra. Este lugar es conocido como "Lógica".
        
        Sin embargo, abundan las leyendas sobre la cueva, pues se dice que en su interior reina una terrible oscuridad causada por una maldición inquebrantable. La luz solo puede ser invocada por los más sabios de los Nueve Reinos.
        
        A pesar de haber escuchado las horrendas historias de la cueva de Lógica, dos caballeros de Itsu, llenos de valentía, decidieron templar su espíritu y adentrarse en el gran peligro...
    """.trimIndent()

    // Estado que mantiene el texto actual a medida que se va mostrando
    var displayedText by remember { mutableStateOf("") }

    // Efecto de lanzamiento para mostrar el texto letra por letra
    LaunchedEffect(fullText) {
        fullText.forEachIndexed { index, _ ->
            displayedText = fullText.take(index + 1) // Muestra las primeras letras progresivamente
            delay(20) // Controla la velocidad entre cada letra (ajustable)
        }
    }

    // UI para mostrar el texto de manera progresiva en el centro de la pantalla
    Box(
        modifier = Modifier
            .fillMaxSize() // El contenedor ocupa todo el tamaño de la pantalla
            .padding(60.dp) // Espaciado de 60dp alrededor del texto
            .background(colorScheme.background), // Fondo con el color de fondo del esquema de colores
        contentAlignment = Alignment.Center, // Alineación centrada del contenido
    ) {
        Text(
            text = displayedText, // Texto que se va mostrando progresivamente
            fontWeight = FontWeight.Medium, // Peso de la fuente para un texto no tan grueso
            fontFamily = FontFamily.Serif, // Fuente con estilo serif
            color = colorScheme.tertiary, // Color terciario del esquema de colores
            fontSize = 16.sp, // Tamaño de fuente de 16sp
            textAlign = TextAlign.Left, // Alineación a la izquierda del texto
        )
    }
}

/**
 * Pantalla del libro que muestra la cueva Wire, una de las subcuevas malditas de la cueva Lógica.
 * El texto se revela progresivamente y una imagen de la cueva se anima según el estado `active`.
 *
 * @param active Estado que controla la animación de la imagen de la cueva Wire. Si es `true`, la imagen es completamente visible.
 *               Si es `false`, la imagen se muestra con un nivel de opacidad reducido.
 *
 * Esta pantalla combina texto narrativo con una animación visual que responde a la interacción del usuario,
 * creando una experiencia inmersiva en el contexto del libro.
 */
@Composable
fun Pag2(active: Boolean) {
    // Texto completo que se va a mostrar progresivamente
    val fullText = """
        La cueva lógica tenía muchas subcuevas malditas, 
        la cueva Wire era la primera de ellas... justo en la entrada.
        La cueva wire no permitía que la luz fuera hecha a 
        menos que la antorcha fuera tomada por el portador
    """.trimIndent()

    // Estado para el texto que se muestra progresivamente
    var displayedText = remember { mutableStateOf("") }

    // Efecto que muestra el texto letra por letra con un pequeño retraso
    LaunchedEffect(fullText) {
        fullText.forEachIndexed { index, _ ->
            displayedText.value = fullText.take(index + 1) // Muestra el texto de manera progresiva
            delay(20) // Controla el tiempo entre cada letra
        }
    }

    // Animación para el nivel de opacidad de la imagen
    val alpha = animateFloatAsState(
        targetValue = if (active) 1f else 0.5f, // Si 'active' es verdadero, la imagen es completamente visible
        animationSpec = tween(durationMillis = 500),
        label = "" // Duración de la animación de la opacidad
    )

    // Box que contiene la imagen de la cueva Wire
    Box(
        modifier = Modifier
            .fillMaxSize() // Ocupa todo el tamaño de la pantalla
            .background(colorScheme.background), // Fondo con el color de fondo del esquema de colores
        contentAlignment = Alignment.Center, // Alineación centrada
    ) {
        Image(
            painter = painterResource(Res.drawable.pagina3), // Imagen de la cueva Wire
            contentDescription = "Imagen de la cueva Wire",
            alpha = alpha.value, // Controla la opacidad con la animación
            contentScale = Crop, // Escala la imagen recortándola para que ocupe el área completa
        )
    }

    // Box para mostrar el texto narrativo
    Box(
        modifier = Modifier
            .fillMaxSize() // Ocupa todo el tamaño de la pantalla
            .padding(40.dp), // Espaciado de 40dp alrededor del texto
        contentAlignment = Alignment.TopStart, // Alineación del texto al principio de la pantalla
    ) {
        Text(
            text = displayedText.value, // Muestra el texto progresivo
            fontWeight = FontWeight.SemiBold, // Peso de la fuente semi-negrita
            fontFamily = FontFamily.Serif, // Fuente con estilo serif
            color = Color.White, // Color blanco para el texto
            fontSize = 16.sp, // Tamaño de la fuente en 16sp
            textAlign = TextAlign.Left, // Alineación a la izquierda del texto
        )
    }

    // Componente Fire que se posiciona de manera personalizada sobre la pantalla
    Fire(
        active = active, // Controla la activación del componente Fire
        modifier = Modifier.layout { measurable, constraints ->
            // Posicionamiento personalizado usando layout
            val placeable = measurable.measure(constraints)
            layout(constraints.maxWidth, constraints.maxHeight) {
                // Se coloca en una posición específica dentro de la pantalla
                placeable.placeRelative(
                    x = (constraints.maxWidth * 0.28f).toInt(),
                    y = (constraints.maxHeight * -0.2f).toInt() // Movimiento hacia arriba para colocar más cerca de la parte superior
                )
            }
        }
    )
}

/**
 * Pantalla del libro que muestra la cueva "not", una de las subcuevas malditas. El texto se revela progresivamente
 * y una imagen relacionada con la cueva cambia de opacidad en función del estado `active`.
 *
 * @param active Estado que controla la animación de la imagen de la cueva "not". Si es `true`, la imagen se vuelve
 *               completamente transparente, si es `false`, la imagen es completamente visible.
 *
 * El componente genera una transición suave para la imagen y el texto, creando una experiencia visual inmersiva.
 */
@Composable
fun Pag3(active: Boolean) {
    // Texto completo que se va a mostrar progresivamente
    val fullText = """
        La siguiente subcueva maldita era la cueva not, 
        donde el ojo led brillaba con una fuerza cegadora 
        y solo podia ser apagado si el portador de la antorcha sostenia con firmeza su antorcha
    """.trimIndent()

    // Estado que mantiene el texto actual a medida que se muestra
    var displayedText = remember { mutableStateOf("") }

    // Efecto para mostrar el texto de manera progresiva, letra por letra
    LaunchedEffect(fullText) {
        fullText.forEachIndexed { index, _ ->
            displayedText.value = fullText.take(index + 1) // Muestra el texto letra por letra
            delay(20) // Controla la velocidad del efecto de revelado
        }
    }

    // Animación que controla la opacidad de la imagen según el estado `active`
    val alpha = animateFloatAsState(
        targetValue = if (active) 0f else 1f, // Si 'active' es true, la imagen se vuelve completamente transparente
        animationSpec = tween(durationMillis = 500),
        label = "" // Duración de la animación en milisegundos
    )

    // Box que contiene la imagen de la cueva "not"
    Box(
        modifier = Modifier
            .fillMaxSize() // La caja ocupa todo el tamaño de la pantalla
            .background(colorScheme.background), // Fondo con el color de fondo del esquema de colores
        contentAlignment = Alignment.TopCenter, // Alineación de la imagen en la parte superior central
    ) {
        Image(
            painter = painterResource(Res.drawable.pagina4), // Imagen de la cueva "not"
            contentDescription = "Imagen de la cueva not", // Descripción de la imagen
            alpha = alpha.value, // Se aplica la animación de opacidad
            contentScale = Crop, // La imagen se recorta para llenar el área disponible
        )
    }

    // Box que contiene el texto
    Box(
        modifier = Modifier
            .fillMaxSize() // La caja ocupa todo el tamaño de la pantalla
            .padding(40.dp), // Se agrega un espaciado alrededor del texto
        contentAlignment = Alignment.TopStart, // El texto se alinea en la parte superior izquierda
    ) {
        Text(
            text = displayedText.value, // Muestra el texto progresivo
            fontWeight = FontWeight.SemiBold, // Estilo de fuente semi-negrita
            fontFamily = FontFamily.Serif, // Tipo de fuente serif
            color = Color.White, // Color del texto (blanco)
            fontSize = 16.sp, // Tamaño de la fuente
            textAlign = TextAlign.Left, // Alineación del texto a la izquierda
        )
    }
}

/**
 * Pantalla del libro que muestra la cueva "or", una de las subcuevas malditas. El texto se revela progresivamente
 * y una imagen relacionada con la cueva "or" cambia de opacidad dependiendo del estado `active`.
 * También se visualiza un efecto de fuego en la pantalla con posiciones dinámicas, que depende del parámetro `touch`.
 *
 * @param active Estado que controla la animación de la imagen de la cueva "or". Si es `true`, la imagen se vuelve completamente visible,
 *               si es `false`, la imagen tiene opacidad reducida.
 * @param touch Controla la aparición de efectos de fuego adicionales. Si es igual a 2, se muestra un segundo efecto de fuego.
 *
 * El componente genera una experiencia visual dinámica con texto progresivo, animación de opacidad y efectos de fuego.
 */
@Composable
fun Pag4(active: Boolean, touch: Int) {
    // Texto completo que se va a mostrar progresivamente
    val fullText = """
        La cueva or era simple. 
        Almenos uno o ambos valientes 
        debia sostener la antorcha 
        para que la luz fuera creada.
    """.trimIndent()

    // Estado que mantiene el texto actual a medida que se muestra
    var displayedText = remember { mutableStateOf("") }

    // Efecto para mostrar el texto de manera progresiva, letra por letra
    LaunchedEffect(fullText) {
        fullText.forEachIndexed { index, _ ->
            displayedText.value = fullText.take(index + 1) // Muestra el texto letra por letra
            delay(20) // Controla la velocidad del efecto de revelado
        }
    }

    // Animación que controla la opacidad de la imagen según el estado `active`
    val alpha = animateFloatAsState(
        targetValue = if (active) 1f else 0.5f, // Si 'active' es true, la imagen se vuelve completamente visible, de lo contrario, se reduce su opacidad
        animationSpec = tween(durationMillis = 500), // Duración de la animación de opacidad en milisegundos
        label = "" // Etiqueta para la animación
    )

    // Box que contiene la imagen de la cueva "or"
    Box(
        modifier = Modifier
            .fillMaxSize() // La caja ocupa todo el tamaño de la pantalla
            .background(colorScheme.background), // Fondo con el color de fondo del esquema de colores
        contentAlignment = Alignment.TopCenter, // Alineación de la imagen en la parte superior central
    ) {
        Image(
            painter = painterResource(Res.drawable.pagina5), // Imagen de la cueva "or"
            contentDescription = "Imagen de la cueva or", // Descripción de la imagen
            alpha = alpha.value, // Se aplica la animación de opacidad
            contentScale = Crop, // La imagen se recorta para llenar el área disponible
        )
    }

    // Box que contiene el texto
    Box(
        modifier = Modifier
            .fillMaxSize() // La caja ocupa todo el tamaño de la pantalla
            .padding(40.dp), // Se agrega un espaciado alrededor del texto
        contentAlignment = Alignment.BottomStart, // El texto se alinea en la parte inferior izquierda
    ) {
        Text(
            text = displayedText.value, // Muestra el texto progresivo
            fontWeight = FontWeight.SemiBold, // Estilo de fuente semi-negrita
            fontFamily = FontFamily.Serif, // Tipo de fuente serif
            color = Color.White, // Color del texto (blanco)
            fontSize = 16.sp, // Tamaño de la fuente
            textAlign = TextAlign.Left, // Alineación del texto a la izquierda
        )
    }

    // Efecto de fuego animado en una posición dinámica
    Fire(
        active = active,
        modifier = Modifier.layout { measurable, constraints ->
            val placeable = measurable.measure(constraints)
            layout(constraints.maxWidth, constraints.maxHeight) {
                placeable.placeRelative(
                    x = (constraints.maxWidth * -0.02f).toInt(), // Posiciona el fuego en un lugar específico
                    y = (constraints.maxHeight * -0.3f).toInt()
                )
            }
        }
    )

    // Si el parámetro 'touch' es igual a 2, se agrega un segundo efecto de fuego en una posición diferente
    if (touch == 2) {
        Fire(
            active = active,
            modifier = Modifier.layout { measurable, constraints ->
                val placeable = measurable.measure(constraints)
                layout(constraints.maxWidth, constraints.maxHeight) {
                    placeable.placeRelative(
                        x = (constraints.maxWidth * 0.25f).toInt(), // Posición diferente para el segundo efecto de fuego
                        y = (constraints.maxHeight * -0.3f).toInt()
                    )
                }
            }
        )
    }
}

/**
 * Pantalla que muestra la cueva "and", en la que dos valientes deben tomar sus antorchas
 * para romper la maldición. El texto se muestra de forma progresiva, con una animación de opacidad
 * para la imagen de la cueva, y efectos de fuego visuales en posiciones dinámicas.
 *
 * @param value Estado que controla la visibilidad de la imagen y los efectos visuales en la pantalla.
 *              Si es `true`, la imagen se muestra completamente visible y los efectos de fuego son activados.
 *              Si es `false`, la imagen tiene una opacidad reducida y los efectos de fuego tienen una visibilidad reducida.
 *
 * Esta pantalla genera una atmósfera de tensión con el texto de revelado progresivo y efectos visuales
 * que simulan el ambiente de la cueva "and".
 */
@Composable
fun Pag5(value: Boolean) {
    // Texto completo que se va a mostrar progresivamente
    val fullText = """
        La cueva and. 
        La maldicion de esta cueva 
        se rompia cuando 
        ambos valientes tomaban sus antorchas.
        Los dos caballeros 
        se acercaban cada vez mas a un destino fatal...
    """.trimIndent()

    // Estado que mantiene el texto actual a medida que se muestra
    var displayedText = remember { mutableStateOf("") }

    // Efecto para mostrar el texto progresivamente, letra por letra
    LaunchedEffect(fullText) {
        fullText.forEachIndexed { index, _ ->
            displayedText.value = fullText.take(index + 1) // Muestra el texto letra por letra
            delay(20) // Controla la velocidad del efecto de revelado
        }
    }

    // Animación que controla la opacidad de la imagen según el estado `value`
    val alpha = animateFloatAsState(
        targetValue = if (value) 1f else 0.5f, // Si 'value' es true, la imagen se vuelve completamente visible, de lo contrario, se reduce su opacidad
        animationSpec = tween(durationMillis = 500), // Duración de la animación de opacidad en milisegundos
        label = "" // Etiqueta para la animación
    )

    // Box que contiene la imagen de la cueva "and"
    Box(
        modifier = Modifier
            .fillMaxSize() // La caja ocupa todo el tamaño de la pantalla
            .background(colorScheme.background), // Fondo con el color de fondo del esquema de colores
        contentAlignment = Alignment.TopCenter, // Alineación de la imagen en la parte superior central
    ) {
        Image(
            painter = painterResource(Res.drawable.pagina6), // Imagen de la cueva "and"
            contentDescription = "Imagen de la cueva and", // Descripción de la imagen
            alpha = alpha.value, // Se aplica la animación de opacidad
            contentScale = Crop, // La imagen se recorta para llenar el área disponible
        )
    }

    // Box que contiene el texto
    Box(
        modifier = Modifier
            .fillMaxSize() // La caja ocupa todo el tamaño de la pantalla
            .padding(40.dp), // Se agrega un espaciado alrededor del texto
        contentAlignment = Alignment.BottomStart, // El texto se alinea en la parte inferior izquierda
    ) {
        Text(
            text = displayedText.value, // Muestra el texto progresivo
            fontWeight = FontWeight.SemiBold, // Estilo de fuente semi-negrita
            fontFamily = FontFamily.Serif, // Tipo de fuente serif
            color = Color.White, // Color del texto (blanco)
            fontSize = 16.sp, // Tamaño de la fuente
            textAlign = TextAlign.Left, // Alineación del texto a la izquierda
        )
    }

    // Efecto de fuego animado en la parte superior izquierda de la pantalla
    Fire(
        active = value,
        modifier = Modifier.layout { measurable, constraints ->
            val placeable = measurable.measure(constraints)
            layout(constraints.maxWidth, constraints.maxHeight) {
                placeable.placeRelative(
                    x = (constraints.maxWidth * .0001f).toInt(), // Posición exacta para el fuego
                    y = (constraints.maxHeight * -0.25f).toInt()
                )
            }
        }
    )

    // Efecto de fuego animado en la parte media derecha de la pantalla
    Fire(
        active = value,
        modifier = Modifier.layout { measurable, constraints ->
            val placeable = measurable.measure(constraints)
            layout(constraints.maxWidth, constraints.maxHeight) {
                placeable.placeRelative(
                    x = (constraints.maxWidth * 0.31f).toInt(), // Posición exacta para el segundo fuego
                    y = (constraints.maxHeight * -0.2f).toInt()
                )
            }
        }
    )
}

/**
 * Componente que representa la cueva "xor", una de las etapas en la que los dos valientes
 * se enfrentan a un desafío donde no pueden sostener la antorcha al mismo tiempo.
 * El texto se muestra de forma progresiva, y se utiliza una imagen de fondo que se desvanece
 * según el estado del parámetro `value`. Además, se muestra un efecto visual de fuego en la pantalla.
 *
 * @param value Controla el estado de visibilidad de la imagen y los efectos visuales en la pantalla.
 *              Si es `true`, la imagen se muestra con opacidad completa, y el efecto de fuego está activo.
 *              Si es `false`, la imagen tiene opacidad reducida, y el efecto de fuego es atenuado.
 */
@Composable
fun Pag6(value: Boolean) {
    // Texto completo que se va a mostrar progresivamente
    val fullText = """
        Cerca de su destino se encuentran la cueva xor.
        Los dos valientes 
        no podian sostener la antorcha al mismo tiempo
        para que la luz fuera creada
    """.trimIndent()

    // Estado que mantiene el texto actual a medida que se muestra
    var displayedText = remember { mutableStateOf("") }

    // Efecto para mostrar el texto progresivamente, letra por letra
    LaunchedEffect(fullText) {
        fullText.forEachIndexed { index, _ ->
            displayedText.value = fullText.take(index + 1) // Muestra el texto letra por letra
            delay(20) // Controla la velocidad del efecto de revelado
        }
    }

    // Animación que controla la opacidad de la imagen según el estado `value`
    val alpha = animateFloatAsState(
        targetValue = if (value) 1f else 0.5f, // Si 'value' es true, la imagen se vuelve completamente visible, de lo contrario, se reduce su opacidad
        animationSpec = tween(durationMillis = 500), // Duración de la animación de opacidad en milisegundos
        label = "" // Etiqueta para la animación
    )

    // Box que contiene la imagen de la cueva "xor"
    Box(
        modifier = Modifier
            .fillMaxSize() // La caja ocupa todo el tamaño de la pantalla
            .background(colorScheme.background), // Fondo con el color de fondo del esquema de colores
        contentAlignment = Alignment.TopCenter, // Alineación de la imagen en la parte superior central
    ) {
        Image(
            painter = painterResource(Res.drawable.pagina7), // Imagen de la cueva "xor"
            contentDescription = "Imagen de la cueva xor", // Descripción de la imagen
            alpha = alpha.value, // Se aplica la animación de opacidad
            contentScale = Crop, // La imagen se recorta para llenar el área disponible
        )
    }

    // Box que contiene el texto
    Box(
        modifier = Modifier
            .fillMaxSize() // La caja ocupa todo el tamaño de la pantalla
            .padding(40.dp), // Se agrega un espaciado alrededor del texto
        contentAlignment = Alignment.TopStart, // El texto se alinea en la parte superior izquierda
    ) {
        Text(
            text = displayedText.value, // Muestra el texto progresivo
            fontWeight = FontWeight.SemiBold, // Estilo de fuente semi-negrita
            fontFamily = FontFamily.Serif, // Tipo de fuente serif
            color = Color.White, // Color del texto (blanco)
            fontSize = 16.sp, // Tamaño de la fuente
            textAlign = TextAlign.Left, // Alineación del texto a la izquierda
        )
    }

    // Efecto de fuego animado en una posición relativa dentro de la pantalla
    Fire(
        active = value,
        modifier = Modifier.layout { measurable, constraints ->
            val placeable = measurable.measure(constraints)
            layout(constraints.maxWidth, constraints.maxHeight) {
                placeable.placeRelative(
                    x = (constraints.maxWidth * 0.27f).toInt(), // Posición del fuego
                    y = (constraints.maxHeight * -0.3f).toInt() // Posición del fuego
                )
            }
        }
    )
}

/**
 * Componente que representa la última cueva "latch", un escenario peligroso en el que los valientes
 * deben ser astutos con sus antorchas. El texto se muestra progresivamente, y la imagen de fondo se
 * ajusta en opacidad según el estado del parámetro `active`. Además, se coloca un efecto de fuego en la
 * pantalla en una posición definida.
 *
 * @param active Controla el estado de la imagen y los efectos visuales. Si es `true`, la imagen se
 *              muestra con opacidad completa, y el efecto de fuego se activa. Si es `false`, la imagen
 *              tiene opacidad reducida, y el fuego es atenuado.
 */
@Composable
fun Pag7(active: Boolean) {
    // Texto completo que se va a mostrar progresivamente
    val fullText = """
        La ultima cueva... la cueva latch. Una cueva peligrosa y tamprosa, 
        pues los valientes debian de ser astutos con sus antorchas. 
        Un valiente es el que debera sostener la antorcha 
        para que la luz sea creada pero el otro debera soltarla hasta el final de la cueva.
    """.trimIndent()

    // Estado que mantiene el texto actual a medida que se muestra
    var displayedText = remember { mutableStateOf("") }

    // Efecto para mostrar el texto progresivamente, letra por letra
    LaunchedEffect(fullText) {
        fullText.forEachIndexed { index, _ ->
            displayedText.value = fullText.take(index + 1) // Muestra el texto letra por letra
            delay(20) // Controla la velocidad del efecto de revelado
        }
    }

    // Animación que controla la opacidad de la imagen según el estado `active`
    val alpha = animateFloatAsState(
        targetValue = if (active) 1f else 0.3f, // Si 'active' es true, la imagen se vuelve completamente visible
        animationSpec = tween(durationMillis = 500), // Duración de la animación de opacidad en milisegundos
        label = "" // Etiqueta para la animación
    )

    // Box que contiene la imagen de la cueva "latch"
    Box(
        modifier = Modifier
            .fillMaxSize() // La caja ocupa todo el tamaño de la pantalla
            .background(colorScheme.background), // Fondo con el color de fondo del esquema de colores
        contentAlignment = Alignment.TopCenter, // Alineación de la imagen en la parte superior central
    ) {
        Image(
            painter = painterResource(Res.drawable.pagina8), // Imagen de la cueva "latch"
            contentDescription = "Imagen de la cueva latch", // Descripción de la imagen
            alpha = alpha.value, // Se aplica la animación de opacidad
            contentScale = Crop, // La imagen se recorta para llenar el área disponible
        )
    }

    // Box que contiene el texto
    Box(
        modifier = Modifier
            .fillMaxSize() // La caja ocupa todo el tamaño de la pantalla
            .padding(40.dp), // Se agrega un espaciado alrededor del texto
        contentAlignment = Alignment.BottomCenter, // El texto se alinea en la parte inferior central
    ) {
        Text(
            text = displayedText.value, // Muestra el texto progresivo
            fontWeight = FontWeight.SemiBold, // Estilo de fuente semi-negrita
            fontFamily = FontFamily.Serif, // Tipo de fuente serif
            color = Color.White, // Color del texto (blanco)
            fontSize = 16.sp, // Tamaño de la fuente
            textAlign = TextAlign.Left, // Alineación del texto a la izquierda
        )
    }

    // Efecto de fuego animado en una posición relativa dentro de la pantalla
    Fire(
        active = active,
        modifier = Modifier.layout { measurable, constraints ->
            // Define la posición exacta de la llama en función de la imagen
            val placeable = measurable.measure(constraints)
            layout(constraints.maxWidth, constraints.maxHeight) {
                placeable.placeRelative(
                    x = (constraints.maxWidth * -0.39f).toInt(), // Posición horizontal del fuego
                    y = (constraints.maxHeight * -0.17f).toInt() // Posición vertical del fuego
                )
            }
        }
    )
}

/**
 * Componente que representa una página donde los valientes llegan al tesoro más valioso tras
 * atravesar la peligrosa cueva "latch". El texto se muestra progresivamente, mientras una imagen
 * de fondo ilustra la escena. El texto se revela con un retraso entre cada letra, creando un
 * efecto de escritura en tiempo real.
 *
 * @param fullText El texto completo que se debe mostrar progresivamente en la página.
 */
@Composable
fun Pag8() {
    // Texto completo que se va a mostrar progresivamente
    val fullText = """
        Atravesando la cueva latch, llegaron al tesoro mas valioso que se ha visto nunca, 
        pero pronto se dieron cuenta el porque no debieron olvidar sus espadas en aquella taverna...
    """.trimIndent()

    // Estado que mantiene el texto actual a medida que se muestra
    var displayedText = remember { mutableStateOf("") }

    // Efecto de lanzamiento para mostrar el texto progresivamente
    LaunchedEffect(fullText) {
        fullText.forEachIndexed { index, _ ->
            displayedText.value = fullText.take(index + 1) // Toma las primeras letras
            delay(20) // Retardo entre cada letra (ajusta para más o menos velocidad)
        }
    }

    // Box que contiene la imagen de la cueva y el tesoro
    Box(
        modifier = Modifier
            .fillMaxSize() // La caja ocupa todo el tamaño de la pantalla
            .background(colorScheme.background), // Fondo con el color de fondo del esquema de colores
        contentAlignment = Alignment.TopCenter, // Alineación de la imagen en la parte superior central
    ) {
        Image(
            painter = painterResource(Res.drawable.pagina9), // Imagen del tesoro
            contentDescription = "Imagen del tesoro", // Descripción de la imagen
            contentScale = ContentScale.FillBounds, // La imagen llena el espacio disponible sin perder su proporción
        )
    }

    // Box que contiene el texto progresivo
    Box(
        modifier = Modifier
            .fillMaxSize() // La caja ocupa todo el tamaño de la pantalla
            .padding(40.dp), // Se agrega un espaciado alrededor del texto
        contentAlignment = Alignment.TopCenter, // El texto se alinea en la parte superior central
    ) {
        Text(
            text = displayedText.value, // Muestra el texto progresivo
            fontWeight = FontWeight.SemiBold, // Estilo de fuente semi-negrita
            fontFamily = FontFamily.Serif, // Tipo de fuente serif
            color = Color.White, // Color del texto (blanco)
            fontSize = 16.sp, // Tamaño de la fuente
            textAlign = TextAlign.Left, // Alineación del texto a la izquierda
        )
    }
}