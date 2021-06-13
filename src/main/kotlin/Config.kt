import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// Window Configuration
const val GRID_SIZE = 15
const val WINDOW_SIZE = 600
const val NUMBER_OF_GRIDS_PER_SIDE = (WINDOW_SIZE.div(GRID_SIZE))
const val WINDOW_HEIGHT_OFFSET = 22
val GRID_SIZE_DP = GRID_SIZE.dp

// Game Configuration
const val SNAKE_INIT_SIZE = 10
const val SNAKE_INIT_POSITION = 0
const val FOOD_INIT_POSITION = 800 - NUMBER_OF_GRIDS_PER_SIDE + NUMBER_OF_GRIDS_PER_SIDE / 2

// UI Configuration
val SNAKE_BODY_COLOR = Color.White
val SNAKE_FOOD_COLOR = Color.Red
val BACKGROUND_COLOR = Color.Black