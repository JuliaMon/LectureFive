package ru.netology

import org.junit.Test

import org.junit.Assert.*

class TaskOneTest {
    @Test
    fun shouldReturnNewPostWithUniqueId() {
        val service = WallService()
        val defaultPost = Post(
            copyHistory = emptyArray(),
            comments = null,
            copyright = null,
            likes = null,
            reposts = null,
            geo = null,
            donut = null,
            attachments = emptyArray()
        )

        var myFirstPost = service.add(defaultPost.copy(text = "Hello from my first post", date = 2022))

        assertNotEquals(0, myFirstPost.id)
    }

    @Test
    fun shouldReturnSuccessUpdatedPost() {
        val service = WallService()
        val defaultPost = Post(
            copyHistory = emptyArray(),
            comments = null,
            copyright = null,
            likes = null,
            reposts = null,
            geo = null,
            donut = null,
            attachments = emptyArray()
        )

        var mySecondPost = service.add(defaultPost.copy(text = "Hello from my second post", date = 2021))
        var result = service.update(mySecondPost.copy(text = "New text in second post"))

        assertTrue(result)
    }

    @Test
    fun shouldReturnFailedUpdatedPost() {
        val service = WallService()
        val defaultPost = Post(
            copyHistory = emptyArray(),
            comments = null,
            copyright = null,
            likes = null,
            reposts = null,
            geo = null,
            donut = null,
            attachments = emptyArray()
        )

        var myPost = service.add(defaultPost.copy(text = "Hello from my first post", date = 2022))
        var result = service.update(myPost.copy(id = 11, text = "New text in second post"))

        assertFalse(result)
    }

    @Test
    fun shouldCreateCommentPost() {
        val service = WallService()
        val defaultPost = Post(
            copyHistory = emptyArray(),
            comments = null,
            copyright = null,
            likes = null,
            reposts = null,
            geo = null,
            donut = null,
            attachments = emptyArray()
        )
        var myPost = service.add(defaultPost.copy(text = "Hello from my post", date = 2022))

        val myComment = CommentsPost(
            id = 1,
            postId = 1,
            date = 2022,
            text = "Пост"
        )

        service.createComment(myComment)
        assertEquals(1, service.getComments().size)
    }


    @Test(expected = PostNotFoundException::class)
    fun shouldCreateExceptionCommentPost() {
        val service = WallService()
        val defaultPost = Post(
            copyHistory = emptyArray(),
            comments = null,
            copyright = null,
            likes = null,
            reposts = null,
            geo = null,
            donut = null,
            attachments = emptyArray()
        )
        var myPost = service.add(defaultPost.copy(text = "Hello from my post", date = 2022))

        val myComment = CommentsPost(
            id = 1,
            postId = 7,
            date = 2022,
            text = "Пост"
        )
        service.createComment(myComment)
    }
}










