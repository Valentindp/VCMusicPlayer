package valentyn.vcmusicplayer.data.dataloaders

import android.content.Context
import android.database.Cursor
import android.media.MediaMetadataRetriever
import android.provider.BaseColumns
import android.provider.MediaStore
import android.text.TextUtils
import valentyn.vcmusicplayer.models.Song
import java.util.ArrayList

object SongLoader {

    private val sEmptyList = LongArray(0)

    fun getSongsForCursor(cursor: Cursor?): ArrayList<Song> {
        val arrayList = ArrayList<Song>()
        cursor?.let {
            if (cursor.moveToFirst())
                do {
                    val id = cursor.getLong(0)
                    val title = cursor.getString(1)
                    val artist = cursor.getString(2)
                    val album = cursor.getString(3)
                    val duration = cursor.getInt(4)
                    val trackNumber = cursor.getInt(5)
                    val artistId = cursor.getInt(6).toLong()
                    val albumId = cursor.getLong(7)

                    arrayList.add(Song(id, albumId, artistId, title, artist, album, duration, trackNumber))
                } while (cursor.moveToNext())
            cursor.close()
        }
        return arrayList
    }

    fun getSongForCursor(cursor: Cursor?): Song {
        var song = Song()
        cursor?.let {
            if (cursor.moveToFirst()) {
                val id = cursor.getLong(0)
                val title = cursor.getString(1)
                val artist = cursor.getString(2)
                val album = cursor.getString(3)
                val duration = cursor.getInt(4)
                val trackNumber = cursor.getInt(5)
                val artistId = cursor.getInt(6).toLong()
                val albumId = cursor.getLong(7)

                song = Song(id, albumId, artistId, title, artist, album, duration, trackNumber)
            }
            cursor.close()
        }
        return song
    }

    fun getSongListForCursor(cursor: Cursor?): LongArray {
        if (cursor == null) return sEmptyList
        val len = cursor.count
        val list = LongArray(len)
        cursor.moveToFirst()
        val columnIndex = try {
            cursor.getColumnIndexOrThrow(MediaStore.Audio.Playlists.Members.AUDIO_ID)
        } catch (notaplaylist: IllegalArgumentException) {
            cursor.getColumnIndexOrThrow(BaseColumns._ID)
        }

        for (i in 0 until len) {
            list[i] = cursor.getLong(columnIndex)
            cursor.moveToNext()
        }
        cursor.close()
        return list
    }

    fun getSongFromPath(songPath: String, context: Context): Song {
        val cr = context.contentResolver

        val uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val selection = MediaStore.Audio.Media.DATA
        val selectionArgs = arrayOf(songPath)
        val projection = arrayOf("_id", "title", "artist", "album", "duration", "track", "artist_id", "album_id")
        val sortOrder = MediaStore.Audio.Media.TITLE + " ASC"

        val cursor = cr.query(uri, projection, "$selection=?", selectionArgs, sortOrder)
        var song = Song()
        cursor?.let {
            if (cursor.count > 0) {
                song = getSongForCursor(cursor)
                cursor.close()
            }
        }
        return song
    }

    fun getAllSongs(context: Context?) = getSongsForCursor(makeSongCursor(context, null, null))

    fun getSongListInFolder(context: Context, path: String) =
        getSongListForCursor(makeSongCursor(context, MediaStore.Audio.Media.DATA + " LIKE ?", arrayOf("$path%"), null))

    fun getSongForID(context: Context, id: Long) = getSongForCursor(makeSongCursor(context, "_id=$id", null))

    fun searchSongs(context: Context, searchString: String, limit: Int): List<Song> {
        val result = getSongsForCursor(makeSongCursor(context, "title LIKE ?", arrayOf("$searchString%")))
        if (result.size < limit) {
            result.addAll(getSongsForCursor(makeSongCursor(context, "title LIKE ?", arrayOf("%_$searchString%"))))
        }
        return if (result.size < limit) result else result.subList(0, limit)
    }

    fun makeSongCursor(context: Context?, selection: String?, paramArrayOfString: Array<String>?) =
        makeSongCursor(context, selection, paramArrayOfString, MediaStore.Audio.Media.DEFAULT_SORT_ORDER)

    private fun makeSongCursor(
        context: Context?,
        selection: String?,
        paramArrayOfString: Array<String>?,
        sortOrder: String?
    ): Cursor? {

        var selectionStatement = "is_music=1 AND title != ''"

        if (!TextUtils.isEmpty(selection)) {
            selectionStatement = "$selectionStatement AND $selection"
        }
        return context?.contentResolver?.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            arrayOf("_id", "title", "artist", "album", "duration", "track", "artist_id", "album_id"),
            selectionStatement,
            paramArrayOfString,
            sortOrder
        )
    }

    fun songFromFile(filePath: String): Song {
        val mmr = MediaMetadataRetriever()
        mmr.setDataSource(filePath)
        return Song(
            -1,
            -1,
            -1,
            mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE),
            mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST),
            mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM),
            Integer.parseInt(mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)),
            0
        )
    }
}