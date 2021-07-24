package com.fara.morty.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.fara.morty.data.PagingSource
import com.fara.morty.data.dao.ApiService
import com.fara.morty.data.model.ResponseApi
import com.fara.morty.data.model.RickMorty
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

abstract class RemoteRepo {

    abstract suspend fun getCharacters(): Flow<PagingData<RickMorty>>
}

class RemoteRepoImpl @Inject constructor(
    private val apiService: ApiService
) : RemoteRepo() {

    override suspend fun getCharacters() = Pager(PagingConfig(pageSize = 1)) {
        PagingSource(apiService)
    }.flow
}