import androidx.compose.desktop.Window
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.unit.IntSize

fun main() = Window(
    title = "Snake using Jetpack Compose",
    resizable = false,
    size = IntSize(WINDOW_SIZE, WINDOW_SIZE + WINDOW_HEIGHT_OFFSET),
    centered = true
) {
    MaterialTheme {
        SnakeApp()
    }
}

@Suppress("FunctionName")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SnakeApp() {
    var gridData = remember { mutableStateOf(getInitSnakeGridData()) }

    val eventModifier = Modifier.onPreviewKeyEvent {
        when (it.key) {
            Key.DirectionUp -> {
                true
            }
            Key.DirectionDown -> {
                true
            }
            Key.DirectionRight -> {
                true
            }
            Key.DirectionLeft -> {
                true
            }
            else -> false
        }
    }

    SnakeWindow(eventModifier, gridData.value.toLinearList())
}

@Suppress("FunctionName")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SnakeWindow(modifier: Modifier, gridData: List<GridType>) {
    LazyVerticalGrid(modifier = modifier, cells = GridCells.Fixed(NUMBER_OF_GRIDS_PER_SIDE)) {
        items(gridData) {
            Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
                SnakeGrid(modifier = modifier, it)
            }
        }
    }
}