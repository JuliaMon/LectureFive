package ru.netology

data class Post(
    val id: Int = 0, // Идентификатор записи.
    val ownerId: Int = 0, //Идентификатор владельца стены, на которой размещена запись. В версиях API ниже 5.7 это поле называется to_id.
    val fromId: Int = 0, // Идентификатор автора записи (от чьего имени опубликована запись).
    val createdBy: Int = 0, // Идентификатор администратора, который опубликовал запись (возвращается только для сообществ при запросе с ключом доступа администратора). Возвращается в записях, опубликованных менее 24 часов назад.
    val date: Int = 0, // Время публикации записи в формате unixtime.
    val text: String = "", // Текст записи.
    val replyOwnerId: Int = 0, // Идентификатор владельца записи, в ответ на которую была оставлена текущая.
    val replyPostId: Int = 0, // Идентификатор записи, в ответ на которую была оставлена текущая.
    val friendsOnly: Boolean = false, // 1, если запись была создана с опцией «Только для друзей».
    val comments: Int = 0, // Информация о комментариях к записи, объект с полями:
    val copyright: Boolean = false, // Источник материала, объект с полями:
    val likes: Int = 0, // Информация о лайках к записи, объект с полями:
    val reposts: Int = 0, // Информация о репостах записи («Рассказать друзьям»), объект с полями:
    val views: Int = 0, // Информация о просмотрах записи. Объект с единственным полем:
    val postType: String = "", // Тип записи, может принимать следующие значения: post, copy, reply, postpone, suggest.
    val signerId: Int = 0, // Идентификатор автора, если запись была опубликована от имени сообщества и подписана пользователем;
    val canPin: Boolean = false, // Информация о том, может ли текущий пользователь закрепить запись (1 — может, 0 — не может).
    val canDelete: Boolean = false, // Информация о том, может ли текущий пользователь удалить запись (1 — может, 0 — не может).
    val canEdit: Boolean = false, // Информация о том, может ли текущий пользователь редактировать запись (1 — может, 0 — не может).
    val isPinned: Int = 0, // Информация о том, что запись закреплена.
    val markedAsAds: Boolean = false,// Информация о том, содержит ли запись отметку «реклама» (1 — да, 0 — нет).
    val isFavorite: Boolean = false, // true, если объект добавлен в закладки у текущего пользователя.
    val donut: Int = 0,
    val postponedId: Int = 0 // Идентификатор отложенной записи. Это поле возвращается тогда, когда запись стояла на таймере.
)

fun main() {

    val defaultPost = Post()
    val wallService = WallService()

    var myFirstPost = wallService.add(defaultPost.copy(text = "Hello from my first post", date = 2022, likes = 0))
    var mySecondPost = wallService.add(defaultPost.copy(text = "Hello from my second post", date = 2021, likes = 0))

    println(myFirstPost)
    println(mySecondPost)
    println("-------------------------------------")

    var isUpdated = wallService.update(mySecondPost.copy(text = "New text in second post", likes = 1))
    println(isUpdated)
    println("-------------------------------------")

}

class WallService {
    private var postsArray = emptyArray<Post>()
    private var uniqueId = 1

    fun add(post: Post): Post {
        val postInProgress = post.copy(id = uniqueId)
        uniqueId++
        postsArray += postInProgress
        return postsArray.last()
    }

    fun update(post: Post): Boolean {
        for ((index, postInArray) in postsArray.withIndex()) {
            if (post.id == postInArray.id) {
                postsArray[index] = post
                return true
            }
        }
        return false
    }
}