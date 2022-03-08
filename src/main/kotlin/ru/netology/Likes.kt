package ru.netology

class Likes(
    var count: Int = 0,// число пользователей, которым понравилась запись;
    val userLikes: Boolean = false,// наличие отметки «Мне нравится» от текущего пользователя (1 — есть, 0 — нет);
    val canLike: Boolean = false,// информация о том, может ли текущий пользователь поставить отметку «Мне нравится» (1 — может, 0 — не может);
    val canPublish: Boolean = false// информация о том, может ли текущий пользователь сделать репост записи (1 — может, 0 — не может).
) {
    override fun toString(): String {
        return "Likes count: $count"
    }
}