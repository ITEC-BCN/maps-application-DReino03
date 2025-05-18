package com.example.mapapp.Model

import java.util.UUID

object MediaStoreImageSaver {
    fun saveBitmapToMediaStore(context: android.content.Context, bitmap: android.graphics.Bitmap): String? {
        val uri = android.provider.MediaStore.Images.Media.insertImage(
            context.contentResolver, bitmap, UUID.randomUUID().toString(), null
        )
        return uri
    }
}
