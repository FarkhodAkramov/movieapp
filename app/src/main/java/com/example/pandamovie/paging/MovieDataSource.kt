package com.example.pandamovie.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pandamovie.models.Result
import com.example.pandamovie.retrofit.ApiService
import java.lang.Exception

class   MovieDataSource(val apiService: ApiService) : PagingSource<Int, Result>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        try {

            val nextPageNumber = params.key ?: 1
            val movies = apiService.getMovie(
                "6b78176c0604017425ee4ed96d07fbba",
                "en-US",
                nextPageNumber
            )
            if (nextPageNumber > 1) {
                return LoadResult.Page(movies.results, nextPageNumber - 1, nextPageNumber + 1)
            } else {
                return LoadResult.Page(movies.results, null, nextPageNumber + 1)

            }

        } catch (e: Exception) {
       return LoadResult.Error(e)
        }


    }
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {

        return state.anchorPosition
    }
}