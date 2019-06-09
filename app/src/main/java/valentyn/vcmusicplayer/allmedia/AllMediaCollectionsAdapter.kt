package valentyn.vcmusicplayer.allmedia

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import valentyn.vcmusicplayer.R
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.item_all_media.view.*
import valentyn.vcmusicplayer.albums.AlbumAdapter
import valentyn.vcmusicplayer.artists.ArtistAdapter
import valentyn.vcmusicplayer.data.dataloaders.AlbumLoader
import valentyn.vcmusicplayer.data.dataloaders.ArtistLoader
import valentyn.vcmusicplayer.data.dataloaders.SongLoader

import valentyn.vcmusicplayer.songs.SongsAdapter

class AllMediaCollectionsAdapter(val context: Context) :
    RecyclerView.Adapter<AllMediaCollectionsAdapter.ItemHolder>() {

    private val list = listOf(R.id.songs_tab,  R.id.albums_tab, R.id.artists_tab)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {

        val viewItemMedia = LayoutInflater.from(parent.context).inflate(R.layout.item_all_media, parent, false)

        viewItemMedia.allMedia_recyclerViewHorizontal.apply {
            setHasFixedSize(false)
            isHorizontalScrollBarEnabled = true
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        return ItemHolder(context, viewItemMedia)
    }


    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bindData(list[position])
    }

    class ItemHolder(val context: Context, val view: View) : RecyclerView.ViewHolder(view) {

        fun bindData(item: Int) {
            when (item) {
                R.id.songs_tab -> {
                    view.allMedia_recyclerViewHorizontal.adapter = SongsAdapter(SongLoader.getAllSongs(context), true)
                    view.collection_media_title.text = context.getString(R.string.tab_song_title)
                }
                R.id.albums_tab -> {
                    view.allMedia_recyclerViewHorizontal.adapter = AlbumAdapter(AlbumLoader.getAllAlbums(context), true)
                    view.collection_media_title.text = context.getString(R.string.tab_album_title)
                }
                R.id.artists_tab -> {
                    view.allMedia_recyclerViewHorizontal.adapter = ArtistAdapter(ArtistLoader.getAllArtists(context), true)
                    view.collection_media_title.text = context.getString(R.string.tab_artist_title)
                }
            }
        }
    }
}