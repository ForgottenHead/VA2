package cz.mendelu.pef.compose.todo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TasksDatabase : RoomDatabase() {

    abstract fun tasksDao(): TasksDao

    companion object {

        private var INSTANCE: TasksDatabase? = null

        fun getDatabase(context: Context): TasksDatabase {
            if (INSTANCE == null) {
                synchronized(TasksDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            TasksDatabase::class.java, "tasks_database"
                        ).build()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}
