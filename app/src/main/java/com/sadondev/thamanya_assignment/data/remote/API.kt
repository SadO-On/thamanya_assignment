package com.sadondev.thamanya_assignment.data.remote

import com.sadondev.thamanya_assignment.data.remote.model.MainContentRemote
import com.sadondev.thamanya_assignment.domain.models.MainContent
import kotlinx.coroutines.flow.Flow

interface ThamanyaAPI {
    fun getMainContent(): Flow<MainContentRemote>
}