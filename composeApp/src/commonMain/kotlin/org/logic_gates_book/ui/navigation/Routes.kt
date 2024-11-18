package org.logic_gates_book.ui.navigation

/**
 * Clase sellada que representa las rutas de navegación en la aplicación.
 * Cada objeto dentro de esta clase corresponde a una ruta específica en la navegación de la aplicación.
 *
 * Las rutas se definen como cadenas de texto y pueden ser usadas en el `NavHost` para definir las pantallas
 * y sus transiciones correspondientes.
 */
sealed class Routes(val route: String) {

    /**
     * Ruta para la pantalla de inicio.
     */
    data object Start : Routes("Start")

    /**
     * Ruta para la pantalla principal del libro.
     */
    data object Book : Routes("Book")

    /**
     * Ruta para la pantalla de créditos.
     */
    data object Credits : Routes("Credits")

    /**
     * Ruta para las páginas del libro. Acepta un número de página como parámetro.
     * Ejemplo de uso: "page/1", "page/2", etc.
     */
    data object Page : Routes("page/{pageNumber}")
}
