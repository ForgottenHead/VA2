package cz.mendelu.pef.compose.todo.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "tasks")
data class Task(
    @ColumnInfo(name = "text") var text: String) : Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null

    @ColumnInfo(name = "date")
    var date: Long? = null

    @ColumnInfo(name = "task_state")
    var taskState: Boolean = false

    @ColumnInfo(name = "latitude")
    var latitude: Double? = null

    @ColumnInfo(name = "longitude")
    var longitude: Double? = null

    fun hasLocation(): Boolean = latitude != null && longitude != null

}


