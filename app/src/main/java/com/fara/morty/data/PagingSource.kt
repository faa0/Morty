package com.fara.morty.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fara.morty.data.dao.ApiService
import com.fara.morty.data.model.RickMorty
import retrofit2.HttpException

class PagingSource(
    private val apiService: ApiService,
) : PagingSource<Int, RickMorty>() {

    override fun getRefreshKey(state: PagingState<Int, RickMorty>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RickMorty> {
        val currentPage = params.key ?: 1
        val pageSize = params.loadSize

        val response = apiService.getCharacters(currentPage)
        return if (response.isSuccessful) {
            val data = checkNotNull(response.body()?.results)
            val prevKey = null
            val nextKey = if (data.size < pageSize) null else currentPage + 1
            LoadResult.Page(data, prevKey, nextKey)
        } else {
            LoadResult.Error(HttpException(response))
        }
    }
}