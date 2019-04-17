package com.example.valentin.valentinmusicplayer.data.dataloaders

import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import com.example.valentin.valentinmusicplayer.models.Album
import java.util.ArrayList

object AlbumLoader {

    fun getAlbum(cursor: Cursor?): Album {
        var album = Album()
        if (cursor != null) {
            if (cursor.moveToFirst())
                album = Album(
                    cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getLong(3),
                    cursor.getInt(4),
                    cursor.getInt(5)
                )
        }
        cursor?.close()
        return album
    }

    fun getAlbumsForCursor(cursor: Cursor?): MutableList<Album> {
        val arrayList = ArrayList<Album>()
        if (cursor != null && cursor.moveToFirst())
            do {
                arrayList.add(
                    Album(
                        cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getLong(3),
                        cursor.getInt(4),
                        cursor.getInt(5)
                    )
                )
            } while (cursor.moveToNext())
        cursor?.close()
        return arrayList
    }

    fun getAllAlbums(context: Context?) = getAlbumsForCursor(makeAlbumCursor(context, null, null))

    fun getAlbum(context: Context, id: Long) = getAlbum(makeAlbumCursor(context, "_id=?", arrayOf(id.toString())))

    fun getAlbums(context: Context, paramString: String) =
        getAlbumsForCursor(makeAlbumCursor(context, "album LIKE ?", arrayOf("$paramString%")))

    fun makeAlbumCursor(context: Context?, selection: String?, paramArrayOfString: Array<String>?) =
        context?.contentResolver?.query(
            MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
            arrayOf("_id", "album", "artist", "artist_id", "numsongs", "minyear"),
            selection,
            paramArrayOfString,
            MediaStore.Audio.Albums.DEFAULT_SORT_ORDER
        )
}
