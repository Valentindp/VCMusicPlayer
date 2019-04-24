package valentyn.vcmusicplayer.utils

import android.content.ContentUris
import android.net.Uri

object PlayerUtils {

    fun getAlbumArtUri(albumId: Long) =
        ContentUris.withAppendedId(Uri.parse("content://media/external/audio/albumart"), albumId)


}