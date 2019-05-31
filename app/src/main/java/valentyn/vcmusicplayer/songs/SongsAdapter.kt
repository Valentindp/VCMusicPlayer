package valentyn.vcmusicplayer.songs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_song_list.view.*
import valentyn.vcmusicplayer.R
import valentyn.vcmusicplayer.models.Song
import valentyn.vcmusicplayer.utils.PlayerUtils

class SongsAdapter(var list: List<Song>) : RecyclerView.Adapter<SongsAdapter.ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SongsAdapter.ItemHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_song_list, parent, false)
    )

    override fun getItemCount() = list.size

    fun updateDataSet(arraylist: List<Song>) {
        list = arraylist
    }

    override fun onBindViewHolder(holder: SongsAdapter.ItemHolder, position: Int) {
        holder.bindData(list[position])
    }


    class ItemHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bindData(item: Song) {

            Picasso.get()
                .load(PlayerUtils.getAlbumArtUri(item.id))
                .fit()
                .error(R.drawable.ic_music_note_blue_24dp)
                .into(view.song_card_image)

            view.apply {
                song_title.text = item.title
                song_artist.text = item.artistName
            }

        }
    }

}