package com.matiaziCelso.superhero.utils.like

import android.view.View
import android.widget.Button
import android.widget.ImageButton


class Like() {

    companion object Factory {
        fun createAction(likeButton: View): LikeAction {
            if(likeButton is ImageButton || likeButton is Button){
                return LikeAction(
                    likeButton = likeButton,
                    doubleClickLike = false,
                    isLiked = false
                )
            } else {
                throw LikeError(message = "Invalid Button type!!")
            }
        }
    }
}