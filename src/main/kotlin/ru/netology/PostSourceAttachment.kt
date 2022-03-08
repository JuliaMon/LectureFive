package ru.netology

class PostSourceAttachment(
    override val type: String = "post_source",
    val postSource: PostSource
) : Attachment {
    override fun toString(): String {
        return "type: $type:\n $postSource"
    }
}