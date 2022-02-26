package ru.netology

import org.junit.Test

import org.junit.Assert.*

class TaskOneTest {
    @Test
     fun shouldReturnNewPostWithUniqueId() {
        val service = WallService()
        val defaultPost = Post()

        var myFirstPost = service.add(defaultPost.copy(text = "Hello from my first post", date = 2022, likes = 0))

        assertNotEquals(0, myFirstPost.id)
    }

    @Test
    fun shouldReturnSuccessUpdatedPost() {
        val service = WallService()
        val defaultPost = Post()

        var mySecondPost = service.add(defaultPost.copy(text = "Hello from my second post", date = 2021, likes = 0))
        var result = service.update(mySecondPost.copy(text = "New text in second post", likes = 1))

        assertTrue(result)
    }

    @Test
    fun shouldReturnFailedUpdatedPost() {
        val service = WallService()
        val defaultPost = Post()

        var myPost = service.add(defaultPost.copy(text = "Hello from my first post", date = 2022, likes = 0))
        var result = service.update(myPost.copy(id = 11, text = "New text in second post", likes = 1))

        assertFalse(result)
    }


}


