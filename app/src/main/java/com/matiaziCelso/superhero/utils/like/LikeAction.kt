package com.matiaziCelso.superhero.utils.like

import android.os.Handler
import android.view.View

class LikeAction(
    likeButton: View,
    doubleClickLike: Boolean,
    isLiked: Boolean
){
    var doubleClickLike: Boolean = doubleClickLike
    var isLiked: Boolean = isLiked
    var likeButton: View = likeButton

    fun doubleClick(
        likeAction: (button: View) -> Unit,
        unlikeAction: (button: View) -> Unit,
    ) {
        this.likeButton.setOnClickListener{
            if (this.doubleClickLike!!) {
                if(this.isLiked!!){
                    likeAction(this.likeButton)
                    this.isLiked = false
                } else {
                    unlikeAction(this.likeButton)
                    this.isLiked = true
                }
            }
            this.doubleClickLike = true
            Handler().postDelayed({
                doubleClickLike = false
            },1000)
        }
    }
}
