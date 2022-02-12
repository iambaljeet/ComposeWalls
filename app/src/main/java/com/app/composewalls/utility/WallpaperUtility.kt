package com.app.composewalls.utility

import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import com.app.composewalls.model.WallpaperType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.URL

object WallpaperUtility {
    fun getBitmapFromUrl(url: String): Bitmap {
        val imageUrl = URL(url)
        return BitmapFactory.decodeStream(imageUrl.openConnection().getInputStream())
    }
}

suspend fun applyWallpaper(context: Context, wallpaperType: WallpaperType, url: String): Boolean =
    withContext(Dispatchers.IO) {
        if (!context.isWallpaperSupported()) {
            return@withContext false
        }
        return@withContext runCatching {
            context.applyWallpaper(wallpaperType, WallpaperUtility.getBitmapFromUrl(url))
        }.isSuccess
    }

fun Context.isWallpaperSupported() = WallpaperManager.getInstance(this).run {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        isWallpaperSupported && isSetWallpaperAllowed
    } else {
        isWallpaperSupported
    }
}

@Throws(IOException::class)
fun Context.applyWallpaper(wallpaperType: WallpaperType, wallpaperBitmap: Bitmap) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        val flags = when (wallpaperType) {
            WallpaperType.HOMESCREEN_LOCKSCREEN -> {
                WallpaperManager.FLAG_LOCK or WallpaperManager.FLAG_SYSTEM
            }
            WallpaperType.HOMESCREEN -> {
                WallpaperManager.FLAG_SYSTEM
            }
            else -> {
                WallpaperManager.FLAG_LOCK
            }
        }

        WallpaperManager.getInstance(this).setBitmap(
            wallpaperBitmap,
            null,
            true,
            flags
        )
    } else {
        WallpaperManager.getInstance(this).setBitmap(
            wallpaperBitmap
        )
    }
}