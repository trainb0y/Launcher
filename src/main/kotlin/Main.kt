import androidx.compose.material.MaterialTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberDialogState
import androidx.compose.ui.window.rememberWindowState
import io.github.trainb0y.launcher.Auth




suspend fun main() {
	application {
		Window(
			onCloseRequest = ::exitApplication,
			title = "trainb0y screwing around with stuff",
			state = rememberWindowState(width = 300.dp, height = 300.dp)
		) {
			var isDialogOpen by remember { mutableStateOf(false) }
			MaterialTheme {
				Column(Modifier.fillMaxSize(), Arrangement.spacedBy(5.dp)) {
					Button(modifier = Modifier.align(Alignment.CenterHorizontally),
						onClick = {
							isDialogOpen = true
						}) {
						Text("Login with Microsoft")
					}
					Button(modifier = Modifier.align(Alignment.CenterHorizontally),
						onClick = {

						}) {
						Text("Launch")
					}
				}
			}
			if (isDialogOpen) {
				Dialog(
					onCloseRequest = { isDialogOpen = false },
					state = rememberDialogState(position = WindowPosition(Alignment.Center))
				) {
					Button(
						onClick = {
							Auth.getMicrosoftToken()
						}){
							Text("Open Login Page")
						}
					// Dialog's content
					/*TextField {
						Text("Enter Link")
					}*/
				}
			}
		}
	}
}
