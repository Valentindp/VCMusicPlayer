package valentyn.vcmusicplayer.mainview

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import valentyn.vcmusicplayer.albums.AlbumsFragment
import valentyn.vcmusicplayer.allmedia.AllMediaFragment
import valentyn.vcmusicplayer.artists.ArtistsFragment
import valentyn.vcmusicplayer.songs.SongsFragment

class MediaPager internal constructor(
    fm: FragmentManager
) : androidx.fragment.app.FragmentPagerAdapter(fm) {

    private val count = 4

    override fun getItem(position: Int): Fragment? {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = AllMediaFragment()
            1 -> fragment = SongsFragment()
            2 -> fragment = AlbumsFragment()
            3 -> fragment = ArtistsFragment()
        }
        return fragment
    }

    override fun getCount(): Int {
        return count
    }
}