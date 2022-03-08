package ru.netology

class PhotoAttachment(
    override val type: String = "photo",
    val photo: Photo
) : Attachment {
    override fun toString(): String {
        return "type: $type:\n $photo"
    }
}