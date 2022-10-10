package org.sopt.sample.ui.main.home

interface InstagramData{
    val isTitle : Boolean
}

data class InstagramTitle(
    override val isTitle: Boolean = true,
) : InstagramData

data class InstagramContent(
    override val isTitle: Boolean = false,
    val name : String,
    val id : String
) : InstagramData