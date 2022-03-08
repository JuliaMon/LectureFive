package ru.netology

class NoteAttachment(
    override val type: String = "note",
    val note: Note

) : Attachment {
    override fun toString(): String {
        return "type: $type:\n $note"
    }
}