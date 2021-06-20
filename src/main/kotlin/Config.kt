import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// Window Configuration
const val GRID_SIZE = 15
const val WINDOW_SIZE = 600
const val NUMBER_OF_GRIDS_PER_SIDE = WINDOW_SIZE.div(GRID_SIZE)
const val WINDOW_WIDTH = NUMBER_OF_GRIDS_PER_SIDE
const val WINDOW_HEIGHT = NUMBER_OF_GRIDS_PER_SIDE
const val WINDOW_HEIGHT_OFFSET = 22
val GRID_SIZE_DP = GRID_SIZE.dp

// Game Configuration
const val GAME_SPEED = 80
const val SNAKE_INIT_SIZE = 10
val SNAKE_INIT_POSITION = Position(0, 0)
val FOOD_INIT_POSITION = Position(19, 19)

// UI Configuration
val SNAKE_BODY_COLOR = Color.White
val SNAKE_HEAD_COLOR = Color.DarkGray
val SNAKE_FOOD_COLOR = Color.Red
val BACKGROUND_COLOR = Color.Black
