package ru.netology

class LinkAttachment(
    override val type: String = "link",
    val link: Link
) : Attachment {
    override fun toString(): String {
        return "type: $type:\n $link"
    }
}