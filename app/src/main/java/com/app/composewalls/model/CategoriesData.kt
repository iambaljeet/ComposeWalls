package com.app.composewalls.model

import com.google.firebase.firestore.DocumentSnapshot

data class CategoriesData(
    val categoriesItemList: MutableList<CategoriesItem>?,
    val lastDoc: DocumentSnapshot
)