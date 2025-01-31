package com.survivalcoding.network_apps.feature_basic.data.datasource.local

import com.survivalcoding.network_apps.feature_basic.data.datasource.TodoDataSource
import com.survivalcoding.network_apps.feature_basic.domain.model.Todo
import kotlinx.coroutines.delay

class LocalTodoDataSource : TodoDataSource {
    override suspend fun getTodoById(id: Int): Todo {
        delay(1000)
        return Todo(
            id = 1,
            title = "test",
            completed = true,
            userId = 1
        )
    }
}