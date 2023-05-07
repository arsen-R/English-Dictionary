package com.example.english_dictionary.presentation.component

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.english_dictionary.presentation.navigation.Screen
import com.example.english_dictionary.ui.theme.EnglishDictionaryTheme
import com.example.english_dictionary.ui.theme.Purple200
import com.example.english_dictionary.ui.theme.Purple500
import kotlinx.coroutines.launch

@Composable
fun NavigationDrawer(
    modifier: Modifier = Modifier,
    navController: NavController,
    currentRoute: String?,
    onCloseDrawer: () -> Unit
) {
    val items = listOf(
        Screen.Search,
        Screen.Bookmarks,
        Screen.History,
        Screen.Settings
    )
    Column(
        modifier = Modifier.padding(top = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items.forEach { drawer ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                    .clickable {
                        onCloseDrawer()
                        navController.navigate(drawer.route) {
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                elevation = 0.dp,
                backgroundColor = if (currentRoute == drawer.route) Color.Gray.copy(
                    0.2f
                ) else MaterialTheme.colors.surface,
                shape = RoundedCornerShape(5.dp)
            ) {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = drawer.icon!!),
                        contentDescription = null
                    )

                    Text(
                        text = stringResource(id = drawer.title),
                        modifier = modifier.padding(start = 15.dp),
                    )
                }
            }
        }
    }

}

@Preview(name = "Light Mode")
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun NavigationDrawerPreview() {
    EnglishDictionaryTheme {
        NavigationDrawer(
            navController = rememberNavController(),
            currentRoute = Screen.Search.route
        ) {

        }
    }
}