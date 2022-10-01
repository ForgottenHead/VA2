package cz.mendelu.pef.compose.todo.extensions

fun Double.round(): String {
    return String.format("%.2f", this)
}
