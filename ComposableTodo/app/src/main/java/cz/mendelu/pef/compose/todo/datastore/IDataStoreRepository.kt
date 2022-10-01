package cz.mendelu.pef.compose.todo.datastore

interface IDataStoreRepository {
    suspend fun setFirstRun()
    suspend fun getFirstRun(): Boolean
}