package org.logic_gates_book.ui.screens.book.pags

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import logic_gates_book_aidw.composeapp.generated.resources.Res
import logic_gates_book_aidw.composeapp.generated.resources.pagina3
import org.jetbrains.compose.resources.painterResource
import org.logic_gates_book.ui.components.Tutorial

/**
 * Componente que representa una pantalla de tutorial con una imagen de fondo y un componente
 * de tutorial superpuesto. La imagen de fondo llena toda la pantalla y la imagen se ajusta
 * con el escalado `ContentScale.Crop`, mientras que el componente `Tutorial` se muestra encima.
 *
 * @see Tutorial El componente que muestra el contenido de tutorial en la pantalla.
 */
@Composable
fun TutorialScreen(screenWidth: Float) {
    // Caja que contiene la imagen de fondo y el componente de tutorial
    Box(
        modifier = Modifier
            .fillMaxSize() // La caja ocupa todo el tamaño de la pantalla
            .background(colorScheme.background) // Fondo con el color del esquema de colores
    ) {
        // Imagen que cubre toda la pantalla y se ajusta con ContentScale.Crop
        Image(
            painter = painterResource(Res.drawable.pagina3), // Imagen de fondo (tutorial)
            contentDescription = "Tutorial", // Descripción de la imagen
            modifier = Modifier.fillMaxSize(), // Imagen ocupa todo el tamaño de la caja
            contentScale = ContentScale.Crop // Ajusta la imagen para llenar la caja
        )
    }

    // El componente Tutorial se coloca encima de la imagen de fondo
    Tutorial(screenWidth)
}