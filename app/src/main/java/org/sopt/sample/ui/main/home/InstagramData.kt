package org.sopt.sample.ui.main.home

abstract class InstagramData {
    abstract val viewType: Int

    companion object {
        const val TITLE = 0
        const val CONTENT = 1
    }
}

data class InstagramTitle(
    override val viewType: Int = TITLE,
    val title: String
) : InstagramData()

data class InstagramContent(
    override val viewType: Int = CONTENT,
    val name: String,
    val id: String
) : InstagramData()

