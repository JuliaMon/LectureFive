package ru.netology

class AudioAttachment(
    override val type: String = "audio",
    val audio: Audio
) : Attachment {
    override fun toString(): String {
        return "type: $type:\n $audio"
    }
}