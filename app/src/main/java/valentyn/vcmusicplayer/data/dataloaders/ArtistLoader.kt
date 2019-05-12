package valentyn.vcmusicplayer.data.dataloaders

import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import valentyn.vcmusicplayer.models.Artist
import java.util.ArrayList

object ArtistLoader {

    fun getArtist(cursor: Cursor?): Artist {
        var artist = Artist()
        cursor?.let {
            if (cursor.moveToFirst())
                artist = Artist(cursor.getLong(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3))

            cursor.close()
        }
        return artist
    }

    fun getArtistsForCursor(cursor: Cursor?): MutableList<Artist> {
        val arrayList = ArrayList<Artist>()
        cursor?.let {
            if (cursor.moveToFirst())
                do {
                    arrayList.add(Artist(cursor.getLong(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3)))
                } while (cursor.moveToNext())
            cursor.close()
        }
        return arrayList
    }

    fun getAllArtists(context: Context?) = getArtistsForCursor(makeArtistCursor(context, null, null))

    fun getArtist(context: Context, id: Long) = getArtist(makeArtistCursor(context, "_id=?", arrayOf(id.toString())))

    fun getArtists(context: Context, paramString: String, limit: Int): List<Artist> {
        val result = getArtistsForCursor(makeArtistCursor(context, "artist LIKE ?", arrayOf("$paramString%")))
        if (result.size < limit) {
            result.addAll(getArtistsForCursor(makeArtistCursor(context, "artist LIKE ?", arrayOf("%_$paramString%"))))
        }
        return if (result.size < limit) result else result.subList(0, limit)
    }

    fun makeArtistCursor(context: Context?, selection: String?, paramArrayOfString: Array<String>?) =
        context?.contentResolver?.query(
            MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI,
            arrayOf("_id", "artist", "number_of_albums", "number_of_tracks"),
            selection,
            paramArrayOfString,
            MediaStore.Audio.Artists.DEFAULT_SORT_ORDER
        )
}
