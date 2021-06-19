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

@OptIn(ExperimentalComposeUiApi::class)
fun main() = application {
    Window(
        title = "Snake using Jetpack Compose",
        resizable = false,
        size = IntSize(WINDOW_SIZE, WINDOW_SIZE + WINDOW_HEIGHT_OFFSET),
        centered = true
    ) {
        val initGridState = drawSnakeData(initialBodyPosition, FOOD_INIT_POSITION)
        val gridData = remember { mutableStateOf(initGridState) }

        var xDirection = 0
        var yDirection = 0

        LocalAppWindow.current.keyboard.onKeyEvent = {
            var handled = false
            if (it.type == KeyEventType.KeyDown) {
                when (it.key) {
                    Key.DirectionUp -> {
                        println("Up pressed")
                        xDirection = 0
                        yDirection++
                        handled = true
//                        gridData.value = gridData.value.moveSnake(xDirection, yDirection)
                    }
                    Key.DirectionDown -> {
                        println("Down pressed")
                        xDirection = 0
                        yDirection--
                        handled = true
//                        gridData.value = gridData.moveSnake(xDirection, yDirection)
                    }
                    Key.DirectionRight -> {
                        println("Right pressed")
                        xDirection++
                        yDirection = 0
                        handled = true
                        gridData.value = gridData.value.moveSnake(xDirection, yDirection)
                    }
                    Key.DirectionLeft -> {
                        println("Left pressed")
                        xDirection--
                        yDirection = 0
                        handled = true
//                        gridData = gridData.moveSnake(xDirection, yDirection)
                    }
                }
            }
            handled
        }
        SnakeApp(gridData.value)
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