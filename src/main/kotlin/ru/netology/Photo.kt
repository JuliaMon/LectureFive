package ru.netology

class Photo(
    val id: Int,
    val albumId: Int,
    val ownerId: Int,
    val userId: Int,
    val text: String,
    val date: Int,
    val sizes: Int,
    val width: Int,
    val height: Int

) {
    override fun toString(): String {
        return "id: $id,\n albumId: $albumId\n ownerId: $ownerId,\n userId: $userId,\n text: $text,\n date: $date,\n size: $sizes,\n width: $width,\n height: $height "
    }
}