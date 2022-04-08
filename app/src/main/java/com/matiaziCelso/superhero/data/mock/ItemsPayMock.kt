package com.matiaziCelso.superhero.data.mock

import com.matiaziCelso.superhero.data.models.BoughtItem

class ItemsPayMock {

    fun boughtItems() : MutableList<BoughtItem>{
        return mutableListOf(
            BoughtItem(
                "Huck",
                "12",
                "Dez",
                0,
                9.50
            ),
            BoughtItem(
                "Thor",
                "19",
                "Dez",
                0,
                9.00
            ),
            BoughtItem(
                "Iron Man",
                "25",
                "Dez",
                -1,
                6.99
            ),
            BoughtItem(
                "Avengers",
                "01",
                "Jan",
                1,
                19.99
            ),
            BoughtItem(
                "Capitain Marvel",
                "12",
                "Fev",
                1,
                5.99
            ),
            BoughtItem(
                "Huck",
                "12",
                "Dez",
                0,
                9.99
            ),
            BoughtItem(
                "Huck",
                "12",
                "Dez",
                -1,
                9.99
            )
        )
    }
}