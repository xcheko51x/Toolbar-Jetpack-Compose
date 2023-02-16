package com.example.toolbar_compose_ejemplo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.toolbar_compose_ejemplo.ui.theme.Toolbar_Compose_EjemploTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Toolbar_Compose_EjemploTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ToolbarScreen()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ToolbarScreen() {

    val navigationController = rememberNavController()

    val accionesItems: List<ActionItems> = listOf(
        ActionItems("favoritos", R.drawable.icon_favorite, "ir pantalla favoritos"),
        ActionItems("configuraciones", R.drawable.icon_settings, "ir pantalla configuraciones"),
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("ToolbarApp") },
                elevation = 8.dp,
                actions = {
                    accionesItems.forEach { accion ->
                        IconButton(
                            onClick = {
                                onIconClick(idPantalla = accion.nombre, navigationController)
                            } ) {
                            Icon(painterResource(id = accion.icon),
                                contentDescription = accion.contentDescription)
                        }
                    }
                }
            )
        }
    ) {
        NavHost(
            navController = navigationController,
            startDestination = Routes.PantallaFavoritos.route) {
            composable(Routes.PantallaFavoritos.route) { PantallaFavoritos() }
            composable(Routes.PantallaConfiguraciones.route) { PantallaConfiguraciones() }
        }
    }
}

fun onIconClick(idPantalla: String, navController: NavController) {
    when(idPantalla) {
        "favoritos" -> { navController.navigate(Routes.PantallaFavoritos.route) }
        "configuraciones" -> { navController.navigate(Routes.PantallaConfiguraciones.route) }
    }
}
