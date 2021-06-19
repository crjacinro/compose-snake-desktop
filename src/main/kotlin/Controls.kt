

fun refreshedBackground(): MutableList<MutableList<GridType>> {
    val mutableGrid = MutableList(NUMBER_OF_GRIDS_PER_SIDE) {
        MutableList(NUMBER_OF_GRIDS_PER_SIDE) { GridType.BACKGROUND }
    }

    for (i in 0 until WINDOW_WIDTH) {
        for (j in 0 until WINDOW_HEIGHT) {
            mutableGrid[i][j] = GridType.BACKGROUND
        }
    }

    return mutableGrid
}

private fun newXPosition(x: Int, deltaX: Int): Int {
    val newX = x + deltaX
    return if (newX >= NUMBER_OF_GRIDS_PER_SIDE) 0 else newX
}

private fun newYPosition(y: Int, deltaY: Int): Int {
    val newY = y + deltaY
    return if (newY >= NUMBER_OF_GRIDS_PER_SIDE) 0 else newY
}