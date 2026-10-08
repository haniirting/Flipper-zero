package com.example.flipperclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(colorScheme = darkColorScheme()) { FlipperApp() }
        }
    }
}

@Composable
fun FlipperApp() {
    val nav = rememberNavController()
    NavHost(navController = nav, startDestination = "menu") {
        composable("menu") { MainMenuScreen { route -> nav.navigate(route) } }
        composable("subghz") { CategoryScreen("Sub-GHz", SubGhzApps) }
        composable("nfc")    { CategoryScreen("NFC", NfcApps) }
        composable("rfid")   { CategoryScreen("RFID", RfidApps) }
        composable("ir")     { CategoryScreen("Infrared", IrApps) }
        composable("games")  { CategoryScreen("Games", GamesApps) }
    }
}

data class AppEntry(val name: String, val desc: String)

val SubGhzApps = listOf(
    AppEntry("Sub-GHz Bruteforcer", "v4.0"),
    AppEntry("Sub-GHz Playlist", "v1.0.1"),
    AppEntry("Frequency Analyzer", "v1.2"),
    AppEntry("Sub-GHz WarDriving", "v0.1"),
)
val NfcApps = listOf(
    AppEntry("NFC Magic", "v2.2"),
    AppEntry("NFC Maker", "v2.1"),
    AppEntry("NFC Seader", "v4.2"),
    AppEntry("Metroflip", "v2.0.3"),
)
val RfidApps = listOf(
    AppEntry("RFID Fuzzer", "v1.8"),
    AppEntry("T5577 Multiwriter", "v0.2"),
    AppEntry("K2 RFID", "v1.0"),
)
val IrApps = listOf(
    AppEntry("IR Blaster", "v1.1"),
    AppEntry("IR Scope", "v1.4"),
    AppEntry("IR Xbox Controller", "v1.5"),
)
val GamesApps = listOf(
    AppEntry("Doom", "v1.6"),
    AppEntry("Tetris Modern", "v1.5"),
    AppEntry("Snake 2.0", "v2.4"),
    AppEntry("Maze 3D", "v7.2"),
    AppEntry("2048", "v1.6"),
)

@Composable
fun MainMenuScreen(onNavigate: (String) -> Unit) {
    val items = listOf(
        "Sub-GHz" to "subghz", "NFC" to "nfc", "RFID" to "rfid",
        "Infrared" to "ir", "Games" to "games"
    )
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Flipper Zero (Clone)", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))
        items.forEach { (label, route) ->
            Button(
                onClick = { onNavigate(route) },
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
            ) { Text(label) }
        }
    }
}

@Composable
fun CategoryScreen(title: String, apps: List<AppEntry>) {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text(title, style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(12.dp))
        LazyColumn {
            items(apps) { app ->
                Card(Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                    Column(Modifier.padding(12.dp)) {
                        Text(app.name, style = MaterialTheme.typography.titleMedium)
                        Text(app.desc, style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}
