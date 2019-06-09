package valentyn.vcmusicplayer.albums

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_album_list.view.*
import valentyn.vcmusicplayer.R
import valentyn.vcmusicplayer.models.Album
import valentyn.vcmusicplayer.utils.PlayerUtils

class AlbumAdapter(var list: List<Album>, val horizontal: Boolean = false) :
    RecyclerView.Adapter<AlbumAdapter.ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemHolder(
        LayoutInflater.from(parent.context)
            .inflate(if (horizontal) R.layout.item_album_list_horizontal else R.layout.item_album_list, parent, false)
    )

    override fun getItemCount() = list.size

    fun updateDataSet(arraylist: List<Album>) {
        list = arraylist
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bindData(list[position])
    }

    class ItemHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bindData(item: Album) {

            Picasso.get()
                .load(PlayerUtils.getAlbumArtUri(item.id))
                .fit()
                .error(R.drawable.ic_album_blue_24dp)
                .into(view.album_card_image)

            view.apply {
                album_card_title.text = item.title
                album_artist_name.text = item.artistName
            }

        }
    }

}