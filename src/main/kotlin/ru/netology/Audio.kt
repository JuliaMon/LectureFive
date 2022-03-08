package ru.netology

class Audio(
    val id: Int,
    val ownerId: Int,
    val artist: String,
    val title: String,
    val duration: Int,
    val url: String,
    val lyricsId: Int,
    val albumId: Int,
    val genreId: Int,
    val date: Int,
    val noSearch: Boolean,
    val isHq: Boolean

) {
    override fun toString(): String {
        return "id: $id,\n ownerId: $ownerId,\n artist: $artist,\n title: $title,\n duration: $duration,\n " +
                "url: $url,\n lyricsId: $lyricsId,\n albumId: $albumId,\n genreId: $genreId,\n date: $date,\n noSearch: $noSearch,\n isHq: $isHq"
    }
}