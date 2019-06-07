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

class AllMediaCollectionsAdapter(val context: Context, var list: List<Int>) :
    RecyclerView.Adapter<AllMediaCollectionsAdapter.ItemHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {

        val viewItemMedia = LayoutInflater.from(parent.context).inflate(R.layout.item_all_media, parent, false)

        // use a linear layout manager
        val layoutMan = LinearLayoutManager(context)
        layoutMan.orientation = LinearLayoutManager.HORIZONTAL

        viewItemMedia.allMedia_recyclerViewHorizontal.apply {
            setHasFixedSize(false)
            isHorizontalScrollBarEnabled = true
            layoutManager = layoutMan
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
                    val songsAdapter = SongsAdapter(SongLoader.getAllSongs(context))
                    view.allMedia_recyclerViewHorizontal.adapter = songsAdapter
                    view.collection_media_title.text = context.getString(R.string.songs_count, songsAdapter.list.size)
                }
                R.id.albums_tab -> {
                    val albumsAdapter = AlbumAdapter(AlbumLoader.getAllAlbums(context))
                    view.allMedia_recyclerViewHorizontal.adapter = albumsAdapter
                    view.collection_media_title.text = context.getString(R.string.albums_count, albumsAdapter.list.size)
                }
                R.id.artists_tab -> {
                    val artistAdapter = ArtistAdapter(ArtistLoader.getAllArtists(context))
                    view.allMedia_recyclerViewHorizontal.adapter = artistAdapter
                    view.collection_media_title.text =
                        context.getString(R.string.artists_count, artistAdapter.list.size)
                }
            }
        }
    }
}