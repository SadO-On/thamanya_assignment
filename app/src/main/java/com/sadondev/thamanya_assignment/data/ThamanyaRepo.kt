package com.sadondev.thamanya_assignment.data

import com.sadondev.thamanya_assignment.domain.models.MainContent
import kotlinx.coroutines.flow.Flow

interface ThamanyaRepository {
    fun mainContent(): Flow<MainContent>
}