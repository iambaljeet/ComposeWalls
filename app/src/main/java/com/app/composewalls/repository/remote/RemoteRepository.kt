package com.app.composewalls.repository.remote

import com.app.composewalls.constants.NetworkConstants
import com.app.composewalls.model.*
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

object RemoteRepository {
    init {
        val firebaseSetting = FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true)
            .build()
        Firebase.firestore.firestoreSettings = firebaseSetting
    }

    var fireStoreBase = Firebase.firestore.collection(NetworkConstants.WALLPAPER_COLLECTION)
        .document(NetworkConstants.WALLPAPER_DOC)


    fun getCategories(lastDocument: DocumentSnapshot? = null): Flow<ResultData<CategoriesData>> =
        callbackFlow {
            send(ResultData.Loading())

            fireStoreBase.collection(NetworkConstants.CATEGORIES)
                .orderBy(NetworkConstants.CATEGORY_NAME, Query.Direction.DESCENDING).limit(10)
                .startAfter(lastDocument)
                .get()
                .addOnCompleteListener { task ->
                    if (task.isComplete && task.exception == null) {
                        val querySnapshot = task.result

                        val categoriesItemList: MutableList<CategoriesItem> =
                            querySnapshot.toObjects(
                                CategoriesItem::class.java
                            )

                        if (categoriesItemList.isNotEmpty()) {
                            val lastDoc = querySnapshot.documents.last()
                            val categoriesData = CategoriesData(categoriesItemList, lastDoc)
                            trySend(ResultData.Success(categoriesData))
                        } else {
                            trySend(ResultData.NoData())
                        }
                        close()
                    } else {
                        trySend(ResultData.Failed(task.exception?.message, task.exception))
                        close()
                    }
                }
        }

    fun getTopWallpapers(lastDocument: DocumentSnapshot? = null): Flow<ResultData<WallpaperData>> =
        callbackFlow {
            send(ResultData.Loading())

            fireStoreBase.collection(NetworkConstants.WALLPAPERS)
                .orderBy(NetworkConstants.WALLPAPER_TIMESTAMP, Query.Direction.DESCENDING).limit(10)
                .startAfter(lastDocument)
                .get()
                .addOnCompleteListener { task ->
                    if (task.isComplete && task.exception == null) {
                        val querySnapshot = task.result

                        val wallpapersItemList: MutableList<WallpapersItem> =
                            querySnapshot.toObjects(
                                WallpapersItem::class.java
                            )

                        if (wallpapersItemList.isNotEmpty()) {
                            val lastDoc = querySnapshot.documents.last()
                            val wallpaperData = WallpaperData(wallpapersItemList, lastDoc)
                            trySend(ResultData.Success(wallpaperData))
                        } else {
                            trySend(ResultData.NoData())
                        }
                        close()
                    } else {
                        trySend(ResultData.Failed(task.exception?.message, task.exception))
                        close()
                    }
                }
        }

    fun getWallpapersByCategory(
        categoryId: String,
        lastDocument: DocumentSnapshot? = null
    ): Flow<ResultData<WallpaperData>> =
        callbackFlow {
            send(ResultData.Loading())

            fireStoreBase.collection(NetworkConstants.WALLPAPERS)
                .whereEqualTo(NetworkConstants.CATEGORY_ID, categoryId).limit(10)
                .startAfter(lastDocument)
                .get()
                .addOnCompleteListener { task ->
                    if (task.isComplete && task.exception == null) {
                        val querySnapshot = task.result

                        val wallpapersItemList: MutableList<WallpapersItem> =
                            querySnapshot.toObjects(
                                WallpapersItem::class.java
                            )

                        if (wallpapersItemList.isNotEmpty()) {
                            val lastDoc = querySnapshot.documents.last()
                            val wallpaperData = WallpaperData(wallpapersItemList, lastDoc)
                            trySend(ResultData.Success(wallpaperData))
                        } else {
                            trySend(ResultData.NoData())
                        }
                        close()
                    } else {
                        trySend(ResultData.Failed(task.exception?.message, task.exception))
                        close()
                    }
                }
        }

    fun getAllWallpapers(lastDocument: DocumentSnapshot? = null): Flow<ResultData<WallpaperData>> =
        callbackFlow {
            send(ResultData.Loading())

            fireStoreBase.collection(NetworkConstants.WALLPAPERS).limit(10)
                .startAfter(lastDocument)
                .get()
                .addOnCompleteListener { task ->
                    if (task.isComplete && task.exception == null) {
                        val querySnapshot = task.result

                        val wallpapersItemList: MutableList<WallpapersItem> =
                            querySnapshot.toObjects(
                                WallpapersItem::class.java
                            )

                        if (wallpapersItemList.isNotEmpty()) {
                            val lastDoc = querySnapshot.documents.last()
                            val wallpaperData = WallpaperData(wallpapersItemList, lastDoc)
                            trySend(ResultData.Success(wallpaperData))
                        } else {
                            trySend(ResultData.NoData())
                        }
                        close()
                    } else {
                        trySend(ResultData.Failed(task.exception?.message, task.exception))
                        close()
                    }
                }
        }

    fun getCategoryByName(
        categoryName: String,
        lastDocument: DocumentSnapshot? = null
    ): Flow<ResultData<CategoriesData>> = callbackFlow {
        send(ResultData.Loading())

        fireStoreBase.collection(NetworkConstants.CATEGORIES)
            .whereLessThanOrEqualTo(NetworkConstants.CATEGORY_NAME, categoryName).limit(10)
            .startAfter(lastDocument)
            .get()
            .addOnCompleteListener { task ->
                if (task.isComplete && task.exception == null) {
                    val querySnapshot = task.result
                    val categoriesItemList: MutableList<CategoriesItem> = querySnapshot.toObjects(
                        CategoriesItem::class.java
                    )

                    if (categoriesItemList.isNotEmpty()) {
                        val lastDoc = querySnapshot.documents.last()
                        val categoriesData = CategoriesData(categoriesItemList, lastDoc)
                        trySend(ResultData.Success(categoriesData))
                    } else {
                        trySend(ResultData.NoData())
                    }
                    close()
                } else {
                    trySend(ResultData.Failed(task.exception?.message, task.exception))
                    close()
                }
            }
    }
}