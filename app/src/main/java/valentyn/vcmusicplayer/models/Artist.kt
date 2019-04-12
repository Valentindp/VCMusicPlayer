package valentyn.vcmusicplayer.models

data class Artist(
    val id: Long = -1,
    val name: String = "",
    val songCount: Int = -1,
    val albumCount: Int = -1
) {}