package com.example.toolbar_compose_ejemplo

sealed class Routes(val route: String) {
    object PantallaFavoritos: Routes("favoritos")
    object PantallaConfiguraciones: Routes("configuraciones")
}