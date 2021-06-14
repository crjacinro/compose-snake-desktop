import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Suppress("FunctionName")
@Composable
fun SnakeGrid(gridType: GridType) {
    val boxModifier = Modifier
        .padding(0.dp)
        .border(getBorderStroke(gridType))
        .size(GRID_SIZE_DP)
        .background(getBackground(gridType))

    Box(modifier = boxModifier)
}

fun List<List<GridType>>.toLinearList(): List<GridType> {
    val mutableList = mutableListOf<GridType>()
    for (i in this.indices) {
        for (j in this[i].indices) {
            mutableList.add(this[i][j])
        }
    }
    return mutableList.toList()
}

private fun getBorderStroke(gridType: GridType) =
    when (gridType) {
        GridType.BACKGROUND -> BorderStroke(0.dp, BACKGROUND_COLOR)
        GridType.FOOD -> BorderStroke(0.dp, SNAKE_FOOD_COLOR)
        GridType.BODY -> BorderStroke(1.dp, Color.Gray)
        GridType.HEAD -> BorderStroke(0.dp, SNAKE_HEAD_COLOR)
    }

private fun getBackground(gridType: GridType) =
    when (gridType) {
        GridType.BACKGROUND -> BACKGROUND_COLOR
        GridType.FOOD -> SNAKE_FOOD_COLOR
        GridType.BODY -> SNAKE_BODY_COLOR
        GridType.HEAD -> SNAKE_HEAD_COLOR
    }

enum class GridType {
    HEAD,
    BODY,
    FOOD,
    BACKGROUND
}
