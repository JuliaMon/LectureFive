package ru.netology

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

    val myComment = CommentsPost(1,4,2022,"Первый комментарий")
    try {
        wallService.createComment(myComment)
    } catch (exception: PostNotFoundException) {
        println(exception.message)
    }

}