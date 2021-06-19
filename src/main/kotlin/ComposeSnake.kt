import androidx.compose.desktop.LocalAppWindow
import androidx.compose.desktop.Window
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import kotlinx.coroutines.delay

val snakeState = State()

@OptIn(ExperimentalComposeUiApi::class)
fun main() = application {
    Window(
        title = "Snake using Jetpack Compose",
        resizable = false,
        size = IntSize(WINDOW_SIZE, WINDOW_SIZE + WINDOW_HEIGHT_OFFSET),
        centered = true
    ) {
        val grid = remember { mutableStateOf(snakeState.drawSnakeDataGrid()) }

        LocalAppWindow.current.keyboard.onKeyEvent = {
            var handled = false
            if (it.type == KeyEventType.KeyDown) {
                when (it.key) {
                    Key.DirectionUp -> {
                        handled = true
                        snakeState.moveSnakeUp()
                    }
                    Key.DirectionDown -> {
                        handled = true
                        snakeState.moveSnakeDown()
                    }
                    Key.DirectionRight -> {
                        handled = true
                        snakeState.moveSnakeRight()
                    }
                    Key.DirectionLeft -> {
                        handled = true
                        snakeState.moveSnakeLeft()
                    }
                }

                grid.value = snakeState.drawSnakeDataGrid()

            }
            handled
        }
        SnakeApp(grid.value)

        LaunchedEffect(Unit) {
            while (true) {
                delay(GAME_SPEED.toLong())
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