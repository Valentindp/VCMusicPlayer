package valentyn.vcmusicplayer.mainview

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import valentyn.vcmusicplayer.albums.AlbumsFragment

class MediaPager internal constructor(
    fm: FragmentManager
) : androidx.fragment.app.FragmentPagerAdapter(fm) {

    private val count = 1

    override fun getItem(position: Int): Fragment? {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = AlbumsFragment()
        }
        return fragment
    }

    override fun getCount(): Int {
        return count
    }
}