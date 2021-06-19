import androidx.compose.desktop.LocalAppWindow
import androidx.compose.desktop.Window
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.type
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.window.application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

val snakeState = State()
val scope = CoroutineScope(Dispatchers.Main)


@OptIn(ExperimentalComposeUiApi::class)
fun main() = application {
    Window(
        title = "Snake using Jetpack Compose",
        resizable = false,
        size = IntSize(WINDOW_SIZE, WINDOW_SIZE + WINDOW_HEIGHT_OFFSET),
        centered = true
    ) {
        val grid = remember { mutableStateOf(snakeState.drawInitGrid()) }

        LocalAppWindow.current.keyboard.onKeyEvent = {
            var handled = false
            if (it.type == KeyEventType.KeyDown) {
                when (it.key) {
                    Key.DirectionUp -> {
                        handled = true
                        scope.launch { snakeState.moveSnakeUp() }
                    }
                    Key.DirectionDown -> {
                        handled = true
                        scope.launch { snakeState.moveSnakeDown() }
                    }
                    Key.DirectionRight -> {
                        handled = true
                        scope.launch { snakeState.moveSnakeRight() }
                    }
                    Key.DirectionLeft -> {
                        handled = true
                        scope.launch { snakeState.moveSnakeLeft() }
                    }
                }
                scope.launch {
                    grid.value = snakeState.drawSnakeDataGrid()
                }
            }
            handled
        }
        SnakeApp(grid.value)
        scope.launch {
            while (true) {
                delay(1000)
                snakeState.moveSnake()
                grid.value = snakeState.drawSnakeDataGrid()
            }
        }
    }
}

@Suppress("FunctionName")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SnakeApp(grid: List<List<GridType>>) {
    SnakeWindow(grid.toLinearList())
}

@Suppress("FunctionName")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SnakeWindow(gridData: List<GridType>) {
    LazyVerticalGrid(cells = GridCells.Fixed(NUMBER_OF_GRIDS_PER_SIDE)) {
        items(gridData) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                SnakeGrid(it)
            }
        }
    }
}