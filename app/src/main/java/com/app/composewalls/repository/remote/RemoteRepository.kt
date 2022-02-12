package com.app.composewalls.repository.remote

import com.app.composewalls.model.CategoriesData
import com.app.composewalls.model.ResultData
import com.app.composewalls.model.WallpaperData
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.coroutines.flow.Flow

interface RemoteRepository {
    fun getCategories(lastDocument: DocumentSnapshot? = null): Flow<ResultData<CategoriesData>>
    fun getTopWallpapers(lastDocument: DocumentSnapshot? = null): Flow<ResultData<WallpaperData>>
    fun getWallpapersByCategory(
        categoryId: String,
        lastDocument: DocumentSnapshot? = null
    ): Flow<ResultData<WallpaperData>>

    fun getAllWallpapers(lastDocument: DocumentSnapshot? = null): Flow<ResultData<WallpaperData>>
    fun getCategoryByName(
        categoryName: String,
        lastDocument: DocumentSnapshot? = null
    ): Flow<ResultData<CategoriesData>>
}