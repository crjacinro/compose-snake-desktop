import androidx.compose.runtime.MutableState

fun MutableState<List<GridType>>.moveSnakeToTheRight() {
    val initialList = this.value

    initialList.forEach {
        if (it == GridType.BODY) {

        }
    }
}