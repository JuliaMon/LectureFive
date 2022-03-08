package ru.netology

class PostSource(
    val type: String,
    val plarform: String,
    val data: String,
    val url: String
) {
    override fun toString(): String {
        return "type: $type,\n platform: $plarform,\n data: $data,\n url: $url"
    }
}