package valentyn.vcmusicplayer.artists

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_artist_list.view.*
import valentyn.vcmusicplayer.R
import valentyn.vcmusicplayer.models.Artist
import valentyn.vcmusicplayer.utils.PlayerUtils

class ArtistAdapter(var list: List<Artist>) : RecyclerView.Adapter<ArtistAdapter.ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_artist_list, parent, false)
    )

    override fun getItemCount() = list.size

    fun updateDataSet(arraylist: List<Artist>) {
        list = arraylist
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bindData(list[position])
    }

    class ItemHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bindData(item: Artist) {

            Picasso.get()
                .load(PlayerUtils.getAlbumArtUri(item.id))
                .fit()
                .error(R.drawable.ic_artist_blue_24dp)
                .into(view.artist_card_image)

            view.apply {
                artist_card_title.text = item.name
                artist_songs_count.text = context.getString(R.string.artist_songs_count, item.albumCount, item.songCount)
            }

        }
    }
}