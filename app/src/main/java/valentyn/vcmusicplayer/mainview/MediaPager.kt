package valentyn.vcmusicplayer.mainview

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import valentyn.vcmusicplayer.albums.AlbumsFragment
import valentyn.vcmusicplayer.artists.ArtistsFragment

class MediaPager internal constructor(
    fm: FragmentManager
) : androidx.fragment.app.FragmentPagerAdapter(fm) {

    private val count = 2

    override fun getItem(position: Int): Fragment? {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = AlbumsFragment()
            1 -> fragment = ArtistsFragment()
        }
        return fragment
    }

    override fun getCount(): Int {
        return count
    }
}