package com.app.composewalls.model

import com.google.firebase.firestore.DocumentSnapshot

data class WallpaperData(
    val wallpapersItemList: MutableList<WallpapersItem>?,
    val lastDocument: DocumentSnapshot
)