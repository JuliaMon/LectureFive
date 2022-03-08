package ru.netology

class WallService {
    private var postsArray = emptyArray<Post>()
    private var uniqueId = 1
    private var commentsArray = emptyArray<CommentsPost>()

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

    fun getComments() : Array<CommentsPost> {
        return commentsArray
    }

    fun createComment(myComment: CommentsPost) {
        for ((index, postInArray) in postsArray.withIndex()) {
            if (myComment.postId == postInArray.id) {
                commentsArray += myComment
                return
            }
        }
        throw PostNotFoundException("Post not found")
    }
}