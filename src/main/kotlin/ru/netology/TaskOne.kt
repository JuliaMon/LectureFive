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
    val comments: Comments?, // Информация о комментариях к записи, объект с полями:
    val copyright: Copyright?, // Источник материала, объект с полями:
    val likes: Likes?, // Информация о лайках к записи, объект с полями:
    val reposts: Reposts?, // Информация о репостах записи («Рассказать друзьям»), объект с полями:
    val views: Int = 0, // Информация о просмотрах записи. Объект с единственным полем:
    val postType: String = "", // Тип записи, может принимать следующие значения: post, copy, reply, postpone, suggest.
    val postSource: Int = 0,
    val geo: Geo?,
    val attachments: Array<Attachment>,
    val signerId: Int = 0, // Идентификатор автора, если запись была опубликована от имени сообщества и подписана пользователем;
    val copyHistory: Array<Reposts>,
    val canPin: Boolean = false, // Информация о том, может ли текущий пользователь закрепить запись (1 — может, 0 — не может).
    val canDelete: Boolean = false, // Информация о том, может ли текущий пользователь удалить запись (1 — может, 0 — не может).
    val canEdit: Boolean = false, // Информация о том, может ли текущий пользователь редактировать запись (1 — может, 0 — не может).
    val isPinned: Int = 0, // Информация о том, что запись закреплена.
    val markedAsAds: Boolean = false,// Информация о том, содержит ли запись отметку «реклама» (1 — да, 0 — нет).
    val isFavorite: Boolean = false, // true, если объект добавлен в закладки у текущего пользователя.
    val donut: Donut?,
    val postponedId: Int = 0 // Идентификатор отложенной записи. Это поле возвращается тогда, когда запись стояла на таймере.
)

fun main() {

    val defaultPost = Post(
        copyHistory = emptyArray(),
        comments = null,
        copyright = null,
        likes = Likes(),
        reposts = null,
        geo = null,
        donut = null,
        attachments = emptyArray()
    )

    val wallService = WallService()

    var myFirstPost = wallService.add(defaultPost.copy(text = "Hello from my first post", date = 2022))
    var mySecondPost = wallService.add(defaultPost.copy(text = "Hello from my second post", date = 2021))

    println(myFirstPost)
    println(mySecondPost)
    println("-------------------------------------")

    var isUpdated = wallService.update(mySecondPost.copy(text = "New text in second post"))
    println(isUpdated)
    println("-------------------------------------")

    println(defaultPost.likes)
    var qwe = defaultPost.likes
    qwe?.count = 1
    println(defaultPost.likes)

    val photoAtt = PhotoAttachment(photo = Photo(1, 1, 1, 1, "", 1, 1, 1, 1))
    val audioAtt = AudioAttachment(audio = Audio(1, 1, "", "", 1, "", 1, 1, 1, 1, false, false))
    val noteAtt = NoteAttachment(note = Note(1, 1, "", "", 1, 1, 1, ""))
    val linkAtt = LinkAttachment(link = Link("", "", "", "", 1, 1, 1, "", ""))
    val postSource = PostSourceAttachment(postSource = PostSource("", "", "2022", ""))

    var myArray = emptyArray<Attachment>()
    myArray += photoAtt
    myArray += audioAtt
    myArray += noteAtt
    myArray += linkAtt
    myArray += postSource

    for ((index, item) in myArray.withIndex()) {

        if (item is PhotoAttachment) {
            println(item.photo.id)
        }

        if (item is AudioAttachment) {
            println(item.audio.id)
        }

        if (item is NoteAttachment) {
            println(item.note.readComment)
        }

        if (item is LinkAttachment) {
            println(item.link.product)
        }

        if (item is PostSourceAttachment) {
            println(item.postSource.data)
        }

    }

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

interface Attachment {
    val type: String
}

class PhotoAttachment(
    override val type: String = "photo",
    val photo: Photo
) : Attachment {
    override fun toString(): String {
        return "type: $type:\n $photo"
    }
}

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

class AudioAttachment(
    override val type: String = "audio",
    val audio: Audio
) : Attachment {
    override fun toString(): String {
        return "type: $type:\n $audio"
    }
}

class Audio(
    val id: Int,
    val ownerId: Int,
    val artist: String,
    val title: String,
    val duration: Int,
    val url: String,
    val lyricsId: Int,
    val albumId: Int,
    val genreId: Int,
    val date: Int,
    val noSearch: Boolean,
    val isHq: Boolean

) {
    override fun toString(): String {
        return "id: $id,\n ownerId: $ownerId,\n artist: $artist,\n title: $title,\n duration: $duration,\n " +
                "url: $url,\n lyricsId: $lyricsId,\n albumId: $albumId,\n genreId: $genreId,\n date: $date,\n noSearch: $noSearch,\n isHq: $isHq"
    }
}

class NoteAttachment(
    override val type: String = "note",
    val note: Note

) : Attachment {
    override fun toString(): String {
        return "type: $type:\n $note"
    }
}

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

class LinkAttachment(
    override val type: String = "link",
    val link: Link
) : Attachment {
    override fun toString(): String {
        return "type: $type:\n $link"
    }
}

class Link(
    val url: String,
    val title: String,
    val caption: String,
    val description: String,
    val photo: Int,
    val product: Int,
    val button: Int,
    val previewPage: String,
    val previewUrl: String
) {
    override fun toString(): String {
        return "url: $url,\n title: $title,\n caption: $caption,\n description: $description,\n photo: $photo," +
                "product: $product,\n button: $button,\n previewPage: $previewPage,\n previewUrl: $previewUrl"
    }
}

class PostSourceAttachment(
    override val type: String = "post_source",
    val postSource: PostSource
) : Attachment {
    override fun toString(): String {
        return "type: $type:\n $postSource"
    }
}

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

class Comments(
    val count: Int = 0, // количество комментариев;
    val canPost: Boolean = false,  // информация о том, может ли текущий пользователь комментировать запись (1 — может, 0 — не может);
    val groupsCanPost: Boolean = false, // информация о том, могут ли сообщества комментировать запись;
    val canClose: Boolean = false,  //  может ли текущий пользователь закрыть комментарии к записи;
    val canOpen: Boolean = false // может ли текущий пользователь открыть комментарии к записи.
)

class Copyright {
    val id: Int = 0
    val link: String = ""
    val name: String = ""
    val type: String = ""
}

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

class Reposts {
    val count: Int = 0 // число пользователей, скопировавших запись;
    val userReposted: Boolean = false// наличие репоста от текущего пользователя (1 — есть, 0 — нет).
}

class Views {
    val count: Int = 0// число просмотров записи.
}

class Geo {
    val type: String = ""
    val coordinates: String = ""
    val place: Place = TODO()
}

class Place {
    val id: Int = 0
    val title: String = ""
    val latitude: Int = 0
    val longitude: Int = 0
    val created: Int = 0
    val icon: String = ""
    val checkins: Int = 0
    val updated: Int = 0
    val type: Int = 0
    val country: Int = 0
    val city: Int = 0
    val address: String = ""
}

class Donut {
    val isDonut: Boolean = false // запись доступна только платным подписчикам VK Donut;
    val paidDuration: Int =
        0 // время, в течение которого запись будет доступна только платным подписчикам VK Donut;
    val placeholder: Placeholder =
        Placeholder()  // заглушка для пользователей, которые не оформили подписку VK Donut. Отображается вместо содержимого записи.


    val canPublishFreeCopy: Boolean =
        false // можно ли открыть запись для всех пользователей, а не только подписчиков VK Donut;
    val editMode: EditMode =
        EditMode() // информация о том, какие значения VK Donut можно изменить в записи. Возможные значения:
}

class Placeholder { // заглушка для пользователей, которые не оформили подписку VK Donut. Отображается вместо содержимого записи.
    val textPlaceholder: String = "Заглушка"

}

class EditMode { // информация о том, какие значения VK Donut можно изменить в записи. Возможные значения:
    val all: String = "" // всю информацию о VK Donut.
    val duration: Int = 0 // время, в течение которого запись будет доступна только платным подписчикам VK Donut.
}