package ru.netology

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