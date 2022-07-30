package com.example.latihan15.ui.dicoding

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.latihan15.core.data.remote.api.ApiServiceStory
import com.example.latihan15.core.data.remote.response.dicoding.getStory.Story

class PagingStorySource(private val apiServiceStory: ApiServiceStory) :
    PagingSource<Int, Story>() {

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }

    override fun getRefreshKey(state: PagingState<Int, Story>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Story> {
        return try {
            val page = params.key ?: INITIAL_PAGE_INDEX
            val responseData = apiServiceStory.getStory(
                page,
                params.loadSize,
                "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiJ1c2VyLWRIVHp2UUtDWTVuYXotejkiLCJpYXQiOjE2NTg4OTk0MDN9.0OOS-9zckZBV2VA55i8MW7a3DUKaC_8BBwsAGIBNzUY"
            )
            val story = responseData.listStory

            LoadResult.Page(
                data = story,
                prevKey = if (page == INITIAL_PAGE_INDEX) null else page - 1,
                nextKey = if (story.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}