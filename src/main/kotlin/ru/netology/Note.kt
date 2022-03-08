package ru.netology

class Note(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val text: String,
    val date: Int,
    val comments: Int,
    val readComment: Int,
    val viewUrl: String
) {
    override fun toString(): String {
        return "id: $id,\n ownerId: $ownerId,\n title: $title,\n test: $text,\n date: $date,\n comments: $comments,\n readComment: $readComment,\n viewUrl: $viewUrl "
    }
}