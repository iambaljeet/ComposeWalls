package com.app.composewalls.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WallpapersItem(
	val wallpaper_name: String? = null,
	val category_id: String? = null,
	val wallpaper_author: String? = null,
	val wallpaper_thumb: String? = null,
	val wallpaper_timestamp: Long? = null,
	val wallpaper_url: String? = null,
	val wallpaper_provider: String? = null
): Parcelable