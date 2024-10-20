package net.iessochoa.joseantoniolopez.t08navegacion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import net.iessochoa.joseantoniolopez.t08navegacion.ui.navigation.AppNavigation

import net.iessochoa.joseantoniolopez.t08navegacion.ui.theme.T08NavegacionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            T08NavegacionTheme {
                AppNavigation()
            }
        }
    }
}

