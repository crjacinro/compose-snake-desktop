import androidx.compose.desktop.Window
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import kotlin.math.pow


private const val GRID_SIZE = 5
private const val WINDOW_SIZE = 600
private val GRID_SIZE_DP = GRID_SIZE.dp

fun main() = Window(
    resizable = true,
    size = IntSize(WINDOW_SIZE, WINDOW_SIZE + 22),
    centered = true
) {
    MaterialTheme {
        SnakeApp()
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SnakeApp() {
    val numberOfGridsPerSide = (WINDOW_SIZE.div(GRID_SIZE))
    val totalNumberOfGrids = numberOfGridsPerSide.toDouble().pow(2).toInt()
    val numbers = (0 until totalNumberOfGrids).toList()

    LazyVerticalGrid(
        cells = GridCells.Fixed(numberOfGridsPerSide)
    ) {
        var count = 0
        var isForeground = true
        items(numbers) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                if (count == numberOfGridsPerSide) {
                    count = 0
                } else {
                    isForeground = !isForeground
                }
                SnakeGrid(isForeground)
                count++
            }
        }
    }
}

@Composable
fun SnakeGrid(isForeground: Boolean) {
    Box(
        modifier = Modifier
            .padding(0.dp)
            .size(GRID_SIZE_DP)
            .background(if (isForeground) Color.White else Color.Black)
    )
}