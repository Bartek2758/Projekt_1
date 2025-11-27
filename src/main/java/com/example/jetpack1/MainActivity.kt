package com.example.jetpack1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpack1.ekrany.Ekran1
import com.example.jetpack1.ekrany.Ekran2
import com.example.jetpack1.ekrany.Ekran3
import com.example.jetpack1.ui.theme.Jetpack1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent { //interf
            Jetpack1Theme {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding -> //buduje.ui - kontener
                    NavHost( //trasy.ekran
                        navController = navController, //miedzy.ekr
                        startDestination = "ekran1",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("ekran1") {
                            Ekran1(onNextClick = {
                                navController.navigate("ekran2")
                            })
                        }

                        composable("ekran2") { //id ekranow
                            Ekran2(
                                onBackClick = { navController.popBackStack() },
                                onNextClick = { navController.navigate("ekran3") }
                            )
                        }

                        composable("ekran3") {
                            Ekran3(onBackClick = { navController.popBackStack() })
                        }
                    }
                }
            }
        }
    }
}
