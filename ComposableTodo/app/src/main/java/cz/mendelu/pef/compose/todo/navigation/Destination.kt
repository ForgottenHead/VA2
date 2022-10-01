package cz.mendelu.pef.compose.todo.navigation

/**
 * Třída reprezentující desitnace při navidaci. V podstatě všechny místa, do kterých
 * půjdete.
 */
sealed class Destination(
    val route: String
){
    object TaskListScreen : Destination(route = "task_list")
    object AddEditTaskScreen : Destination(route = "add_edit_task")
    object MapScreen : Destination(route = "map")
}
