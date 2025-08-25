package com.sadondev.thamanya_assignment.data.remote

import com.sadondev.thamanya_assignment.data.remote.model.ContentSearchRemote
import com.sadondev.thamanya_assignment.data.remote.model.MainContentRemote
import kotlinx.coroutines.flow.Flow

interface ThamanyaAPI {
    fun getMainContent(pagePath: String? = null): Flow<MainContentRemote>
    fun searchContent(text: String? = null): Flow<ContentSearchRemote>
}