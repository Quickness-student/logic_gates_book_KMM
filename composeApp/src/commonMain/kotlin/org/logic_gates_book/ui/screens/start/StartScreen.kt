package org.logic_gates_book.ui.screens.start

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import logic_gates_book_aidw.composeapp.generated.resources.Res
import logic_gates_book_aidw.composeapp.generated.resources.baseline_device_hub_24
import logic_gates_book_aidw.composeapp.generated.resources.baseline_info_24
import logic_gates_book_aidw.composeapp.generated.resources.logo_swiftid_centrado
import logic_gates_book_aidw.composeapp.generated.resources.portada
import org.jetbrains.compose.resources.painterResource
import org.logic_gates_book.Uri
import org.logic_gates_book.ui.navigation.Routes
import org.logic_gates_book.ui.theme.MaterialThemeApp

/**
 * Pantalla de inicio de la aplicación.
 *
 * Esta pantalla contiene un fondo con gradiente, una imagen y la opción de navegar a otras
 * secciones de la aplicación, como los créditos y el repositorio de GitHub. La pantalla está
 * dividida en un encabezado y un pie de página con elementos interactivos.
 *
 * @param navController Controlador de navegación utilizado para navegar entre las pantallas de la aplicación.
 */
@Composable
fun StartScreen(navController: NavController, uri: Uri) =
    MaterialThemeApp { Screen(navController, uri) }

/**
 * Composable que organiza la estructura principal de la pantalla de inicio.
 *
 * Esta función combina el fondo y el contenido de la pantalla mediante los composables `Background`
 * y `Content`. Se asegura de que los elementos estén correctamente organizados en una columna.
 *
 * @param navController Controlador de navegación que se pasa a las pantallas secundarias.
 */
@Composable
private fun Screen(navController: NavController, uri: Uri) {
    Background(navController)
    Content(navController, uri)
}

/**
 * Composable que organiza el contenido principal de la pantalla en una columna.
 *
 * La columna contiene dos elementos: el encabezado y el pie de página. Estos están organizados de
 * forma que el encabezado se muestra en la parte superior y el pie de página en la parte inferior.
 *
 * @param navController Controlador de navegación para interactuar con las secciones de la aplicación.
 */
@Composable
private fun Content(navController: NavController, uri: Uri) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 10.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Header()
        Footer(navController = navController, uri = uri)
    }
}

/**
 * Composable para el encabezado de la pantalla de inicio.
 *
 * Muestra el texto "Powered By" seguido de un ícono de logo y el texto "Override". Estos elementos
 * están alineados horizontalmente en el centro de la pantalla.
 */
@Composable
private fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Power By",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = colorScheme.tertiary
        )
        Icon(
            painter = painterResource(Res.drawable.logo_swiftid_centrado),
            tint = colorScheme.tertiary,
            contentDescription = "",
            modifier = Modifier.size(100.dp)
        )
        Text(
            text = "Override",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = colorScheme.tertiary
        )
    }
}

/**
 * Composable para el pie de página de la pantalla.
 *
 * El pie de página contiene dos iconos de botones interactivos y un texto animado que cambia de
 * color entre blanco y gris de forma continua. Los botones permiten al usuario navegar a otras pantallas,
 * como los créditos o el repositorio en GitHub.
 *
 * @param navController Controlador de navegación para manejar las acciones de navegación del pie de página.
 */
@Composable
private fun Footer(navController: NavController, uri: Uri) {
    // Transición infinita para animar el color del texto
    val rememberInfiniteTransition = rememberInfiniteTransition("")

    // Animación de color entre blanco y gris
    val infiniteTransitionColor = rememberInfiniteTransition.animateColor(
        initialValue = colorScheme.tertiary,
        targetValue = colorScheme.primary,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.padding(20.dp))
        IconButton(
            onClick = {
                navController.popBackStack()
                navController.navigate(Routes.Credits.route)
            }
        ) {
            Icon(
                painter = painterResource(Res.drawable.baseline_info_24),
                contentDescription = "Mostrar créditos",
                tint = colorScheme.tertiary,
                modifier = Modifier.size(50.dp)
            )
        }
        Text(
            "Presione la pantalla",
            color = infiniteTransitionColor.value,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        IconButton(
            onClick = {
                uri.navigate()
            }
        ) {
            Icon(
                painter = painterResource(Res.drawable.baseline_device_hub_24),
                contentDescription = "Ir a GitHub",
                tint = colorScheme.tertiary,
                modifier = Modifier.size(50.dp)
            )
        }
        Spacer(modifier = Modifier.padding(20.dp))
    }
}

/**
 * Composable para el fondo de la pantalla. Aplica un gradiente vertical de colores y una imagen.
 *
 * El fondo también detecta clics y navega a la pantalla del libro cuando el usuario hace clic en cualquier
 * parte de la pantalla.
 *
 * @param navController Controlador de navegación que se usa para navegar a la pantalla del libro.
 */
@Composable
private fun Background(navController: NavController) {
    val brush = Brush.verticalGradient(
        colors = listOf(
            colorScheme.background,
            colorScheme.primary,
            colorScheme.background
        ),
        startY = -300f
    )

    Box(
        modifier = Modifier
            .background(brush)
            .fillMaxSize()
            .clickable {
                navController.navigate(Routes.Book.route)
            }
    ) {
        Image(
            painter = painterResource(Res.drawable.portada),
            contentDescription = "Portada del libro",
            contentScale = ContentScale.Crop
        )
    }
}
