package com.matiaziCelso.superhero.data.mock

import com.matiaziCelso.superhero.data.models.ComicItem

class ComicsMoreMock {

    private val description: String = "He's a hero. An Avenger. A living legend. The sentinel of liberty. Mankind's greatest threat? It's the story that shocked the world - and you won't believe where it's going next. Marvel's next big event begins right here, and Steve Rogers is in the dead center of the storm."

    fun comicsMore() : List<ComicItem> {
        return mutableListOf(

            ComicItem(
                title = "Origins of Marvel Comics",
                description = description,
                value = 19.99,
                image = "https://m.media-amazon.com/images/I/61KFLylOgPL.jpg",
                isFavorite = false,
                more = mutableListOf(),
                characters = mutableListOf()

            ),
            ComicItem(
                title = "Spider Man",
                description = description,
                value = 5.99,
                image = "https://lumiere-a.akamaihd.net/v1/images/maractsminf001_cov_2a89b17b.jpeg?region=0%2C0%2C1844%2C2800",
                isFavorite = false,
                more = mutableListOf(),
                characters = mutableListOf()

            ),
            ComicItem(
                title = "Capitain Marvel",
                description = description,
                value = 6.69,
                image = "https://752617.smushcdn.com/1328696/wp-content/uploads/2019/11/b4cc2826-untitled.jpg?lossy=1&strip=1&webp=1",
                isFavorite = false,
                more = mutableListOf(),
                characters = mutableListOf()

            ),

            ComicItem(
                title = "Marvel Comics #1",
                description = description,
                value = 7.79,
                image = "https://images-na.ssl-images-amazon.com/images/I/81GhmQvIJ8L.jpg",
                isFavorite = false,
                more = mutableListOf(),
                characters = mutableListOf()

            ),
            ComicItem(
                title = "Ultraman",
                description = description,
                value = 3.99,
                image = "https://i.annihil.us/u/prod/marvel/i/mg/9/80/5f594342f1afc/clean.jpg",
                isFavorite = false,
                more = mutableListOf(),
                characters = mutableListOf()

            ),
            ComicItem(
                title = "Capitain Marvel",
                description = description,
                value = 7.99,
                image = "https://i.annihil.us/u/prod/marvel/i/mg/a/10/5ff8d9a2932e4/clean.jpg",
                isFavorite = false,
                more = mutableListOf(),
                characters = mutableListOf()

            ),

            ComicItem(
                title = "Iron Man",
                description = description,
                value = 9.00,
                image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTI5qXZ0a-cJExgbqgiJM8S2JZSPeZwLi8dA9Aa5_hc4-TPWEEb6vOASoV2uxz6fQepCzE&usqp=CAU",
                isFavorite = false,
                more = mutableListOf(),
                characters = mutableListOf()

            ),
            ComicItem(
                title = "Iron Man",
                description = description,
                value = 11.54,
                image = "https://i.annihil.us/u/prod/marvel/i/mg/b/c0/56a7bde31f20a/portrait_uncanny.jpg",
                isFavorite = false,
                more = mutableListOf(),
                characters = mutableListOf()

            ),
            ComicItem(
                title = "Spider Man",
                description = description,
                value = 43.99,
                image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQGKWIPdIWGwjgOnjQg2JCF03FW9spQgXl4Ww&usqp=CAU",
                isFavorite = false,
                more = mutableListOf(),
                characters = mutableListOf()

            ),
        ).shuffled()
    }


    fun avengers() : List<ComicItem> {
        return mutableListOf(

            ComicItem(
                title = "Avengers",
                description = description,
                value = 54.59,
                image = "https://m.media-amazon.com/images/I/61uoAZqd3NL.jpg",
                isFavorite = false,
                more = mutableListOf(),
                characters = mutableListOf()

            ),
            ComicItem(
                title = "Avengers",
                description = description,
                value = 24.99,
                image = "https://i.annihil.us/u/prod/marvel/i/mg/1/20/5af0aacb41ae3/clean.jpg",
                isFavorite = false,
                more = mutableListOf(),
                characters = mutableListOf()

            ),
            ComicItem(
                title = "Avengers",
                description = description,
                value = 54.99,
                image = "https://m.media-amazon.com/images/I/81YAcg9D9wL._SL1398_.jpg",
                isFavorite = false,
                more = mutableListOf(),
                characters = mutableListOf()

            ),

            ComicItem(
                title = "Avengers",
                description = description,
                value = 12.99,
                image = "https://i.pinimg.com/564x/62/b3/bf/62b3bf35b5446cea059c258ccb603deb.jpg",
                isFavorite = false,
                more = mutableListOf(),
                characters = mutableListOf()

            ),
            ComicItem(
                title = "Avengers",
                description = description,
                value = 32.99,
                image = "https://i.imgur.com/grXZGIe.jpg",
                isFavorite = false,
                more = mutableListOf(),
                characters = mutableListOf()

            ),
            ComicItem(
                title = "Avengers",
                description = description,
                value = 34.99,
                image = "https://m.media-amazon.com/images/I/81QY5c8H+mL._AC_SL1500_.jpg",
                isFavorite = false,
                more = mutableListOf(),
                characters = mutableListOf()

            ),

            ComicItem(
                title = "Avengers",
                description = description,
                value = 9.99,
                image = "https://d29xot63vimef3.cloudfront.net/image/avengers/150-1.jpg",
                isFavorite = false,
                more = mutableListOf(),
                characters = mutableListOf()

            ),
            ComicItem(
                title = "Avengers",
                description = description,
                value = 3.99,
                image = "https://i.pinimg.com/originals/f7/13/1e/f7131e169e91657f6998875d59bf37cc.jpg",
                isFavorite = false,
                more = mutableListOf(),
                characters = mutableListOf()

            ),
            ComicItem(
                title = "Avengers",
                description = description,
                value = 7.99,
                image = "https://d29xot63vimef3.cloudfront.net/image/avengers/151-3.jpg",
                isFavorite = false,
                more = mutableListOf(),
                characters = mutableListOf()

            ),
        ).shuffled()
    }


    fun ironMan() : List<ComicItem> {
        return mutableListOf(

            ComicItem(
                title = "Iron Man",
                description = description,
                value = 54.99,
                image = "https://images-na.ssl-images-amazon.com/images/I/41eXG7xY09L._SX331_BO1,204,203,200_.jpg",
                isFavorite = false,
                more = mutableListOf(),
                characters = mutableListOf()

            ),
            ComicItem(
                title = "Iron Man",
                description = description,
                value = 1.99,
                image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRbyTw5rRYDsfR0q03utdddg1NHcPiNpA6EnXSpN8kdkYi_v8BNiI3uYSzJS9Twcha39bk&usqp=CAU",
                isFavorite = false,
                more = mutableListOf(),
                characters = mutableListOf()

            ),
            ComicItem(
                title = "Iron Man",
                description = description,
                value = 6.99,
                image = "https://www.previewsworld.com/SiteImage/MainImage/STL170875.jpg",
                isFavorite = false,
                more = mutableListOf(),
                characters = mutableListOf()

            ),

            ComicItem(
                title = "Iron Man",
                description = description,
                value = 4.99,
                image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQoA5nbix3RETyIECSOWMS0gCbLAYidD3BL_x3z3t-0bUdhVpcfp6oz5yNCOG4BfAWFWFk&usqp=CAU",
                isFavorite = false,
                more = mutableListOf(),
                characters = mutableListOf()

            ),
            ComicItem(
                title = "Iron Man",
                description = description,
                value = 6.99,
                image = "https://i.imgur.com/grXZGIe.jpg",
                isFavorite = false,
                more = mutableListOf(),
                characters = mutableListOf()

            ),
            ComicItem(
                title = "Iron Man",
                description = description,
                value = 54.99,
                image = "https://i5.walmartimages.com/asr/96882727-47c2-4a85-b328-154fe85eabe4_1.95570a294fd40051dbf835b3cc7f4003.jpeg",
                isFavorite = false,
                more = mutableListOf(),
                characters = mutableListOf()

            ),

            ComicItem(
                title = "Iron Man",
                description = description,
                value = 3.99,
                image = "https://www.previewsworld.com/SiteImage/MainImage/STL148704.jpg",
                isFavorite = false,
                more = mutableListOf(),
                characters = mutableListOf()

            ),
            ComicItem(
                title = "Iron Man",
                description = description,
                value = 7.99,
                image = "https://i0.wp.com/ultimatecomics.com/wp-content/uploads/2021/02/Scan-238-2.jpg?fit=1935%2C2990&ssl=1",
                isFavorite = false,
                more = mutableListOf(),
                characters = mutableListOf()

            ),
            ComicItem(
                title = "Iron Man",
                description = description,
                value = 8.99,
                image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRbWeOey1sv9VIxCSYYaPOEDmhsBOn3lvlTL3HcgmsC8P_RGyXrc1R-53tcPhbe1YXkcZI&usqp=CAU",
                isFavorite = false,
                more = mutableListOf(),
                characters = mutableListOf()

            ),
        ).shuffled()
    }


    fun huck() : List<ComicItem> {
        return mutableListOf(

            ComicItem(
                title = "Huck",
                description = description,
                value = 119.99,
                image = "https://images-na.ssl-images-amazon.com/images/I/51Ka3kfNYeL._SX342_SY445_QL70_ML2_.jpg",
                isFavorite = false,
                more = mutableListOf(),
                characters = mutableListOf()

            ),
            ComicItem(
                title = "Huck",
                description = description,
                value = 96.99,
                image = "https://i.pinimg.com/originals/40/36/b6/4036b616698fa1b0aa3d18d7e4108a29.jpg",
                isFavorite = false,
                more = mutableListOf(),
                characters = mutableListOf()

            ),
            ComicItem(
                title = "Huck",
                description = description,
                value = 123.99,
                image = "https://static.wikia.nocookie.net/marveldatabase/images/d/d0/Incredible_Hulk_Special_Vol_1_1.jpg/revision/latest/scale-to-width-down/250?cb=20200107031923",
                isFavorite = false,
                more = mutableListOf(),
                characters = mutableListOf()

            ),

            ComicItem(
                title = "Huck",
                description = description,
                value = 94.99,
                image = "https://www.sellmycomicbooks.com/images/incredible-hulk-102.png",
                isFavorite = false,
                more = mutableListOf(),
                characters = mutableListOf()

            ),
            ComicItem(
                title = "Huck",
                description = description,
                value = 65.99,
                image = "https://d1466nnw0ex81e.cloudfront.net/n_iv/600/729683.jpg",
                isFavorite = false,
                more = mutableListOf(),
                characters = mutableListOf()

            ),
            ComicItem(
                title = "Huck",
                description = description,
                value = 65.99,
                image = "https://cdn11.bigcommerce.com/s-5yvq3t3oaz/images/stencil/1280x1280/products/3122/3000/incredible_hulk_epic_coll_leadr_lives__15315.1604518812.jpg?c=2",
                isFavorite = false,
                more = mutableListOf(),
                characters = mutableListOf()

            ),

            ComicItem(
                title = "Huck",
                description = description,
                value = 76.99,
                image = "https://cdnb.artstation.com/p/assets/covers/images/015/628/925/large/simon-williams-img-20190201-121536.jpg?1549023918",
                isFavorite = false,
                more = mutableListOf(),
                characters = mutableListOf()

            ),
            ComicItem(
                title = "Huck",
                description = description,
                value = 95.99,
                image = "https://cdna.artstation.com/p/assets/images/images/020/036/726/large/simon-williams-immortal-hulk.jpg?1566085562",
                isFavorite = false,
                more = mutableListOf(),
                characters = mutableListOf()

            ),
            ComicItem(
                title = "Huck",
                description = description,
                value = 45.99,
                image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSvZOUVzXoOhqobFBBb4cF5xBn5qKSRQwzrj1u5b0533fqZ9YyUBFXQvBAJT3-MkADeC6o&usqp=CAU",
                isFavorite = false,
                more = mutableListOf(),
                characters = mutableListOf()

            ),
        ).shuffled()
    }


    fun thor() : List<ComicItem> {
        return mutableListOf(

            ComicItem(
                title = "Thor",
                description = description,
                value = 65.99,
                image = "https://i.annihil.us/u/prod/marvel/i/mg/8/b0/5e00da382a27d/clean.jpg",
                isFavorite = false,
                more = mutableListOf(),
                characters = mutableListOf()

            ),
            ComicItem(
                title = "Thor",
                description = description,
                value = 34.99,
                image = "https://m.media-amazon.com/images/I/51lxe-L820L.jpg",
                isFavorite = false,
                more = mutableListOf(),
                characters = mutableListOf()

            ),
            ComicItem(
                title = "Thor",
                description = description,
                value = 23.99,
                image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ-W7KNu0lGpZ-o8tMBFFG5tGJi9UssvjIArpHYnZxDFGEkKO8ZBj11aG_Lrg5THF656f0&usqp=CAU",
                isFavorite = false,
                more = mutableListOf(),
                characters = mutableListOf()

            ),

            ComicItem(
                title = "Thor",
                description = description,
                value = 43.99,
                image = "https://m.media-amazon.com/images/I/51hJbVhssFL.jpg",
                isFavorite = false,
                more = mutableListOf(),
                characters = mutableListOf()

            ),
            ComicItem(
                title = "Thor",
                description = description,
                value = 23.99,
                image = "https://m.media-amazon.com/images/I/51+cSFhY4TL.jpg",
                isFavorite = false,
                more = mutableListOf(),
                characters = mutableListOf()

            ),
            ComicItem(
                title = "Thor",
                description = description,
                value = 5.99,
                image = "https://i.annihil.us/u/prod/marvel/i/mg/9/a0/5fa23e3229e9e/clean.jpg",
                isFavorite = false,
                more = mutableListOf(),
                characters = mutableListOf()

            ),

            ComicItem(
                title = "Thor",
                description = description,
                value = 54.99,
                image = "https://mlpnk72yciwc.i.optimole.com/cqhiHLc.WqA8~2eefa/w:auto/h:auto/q:75/https://bleedingcool.com/wp-content/uploads/2020/09/THOR2020010_KNULLIFIED_VAR.jpg",
                isFavorite = false,
                more = mutableListOf(),
                characters = mutableListOf()

            ),
            ComicItem(
                title = "Thor",
                description = description,
                value = 23.99,
                image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSk-sn2H-iNVq8kStaXaMAJh7zYY4SWG3XUgdPnfjNItDUXsjf4UGdsXsBTpg3KLDNqhPI&usqp=CAU",
                isFavorite = false,
                more = mutableListOf(),
                characters = mutableListOf()

            ),
            ComicItem(
                title = "Thor",
                description = "description",
                value = 54.99,
                image = "https://i.annihil.us/u/prod/marvel/i/mg/b/a0/5fbd401b0df25/clean.jpg",
                isFavorite = false,
                more = mutableListOf(),
                characters = mutableListOf()

            ),
        ).shuffled()
    }

    fun captainAmerica(): List<ComicItem> {

        return listOf(

            ComicItem(
                title = "Hail Hydra",
                description = "He's back! The original Sentinel of Liberty returns, with a new shield, a new team, and a new mission! And he's not the only one who's back! Like the saying goes-- cut off one head, two more will take its place! HAIL HYDRA!",
                value = 12.22,
                image = "https://images-na.ssl-images-amazon.com/images/I/91Q5FQH5oIL.jpg",
                isFavorite = false,
                more = mutableListOf(),
                characters = mutableListOf()
            ),

            ComicItem(
                title = "Empire Building ",
                description = "He's a hero. An Avenger. A living legend. The sentinel of liberty. Mankind's greatest threat? It's the story that shocked the world - and you won't believe where it's going next. Marvel's next big event begins right here, and Steve Rogers is in the dead center of the storm.",
                value = 2.22,
                image = "https://images-na.ssl-images-amazon.com/images/I/51CbU+FbtQL._SX323_BO1,204,203,200_.jpg",
                isFavorite = false,
                more = mutableListOf(),
                characters = mutableListOf()
            ),

            ComicItem(
                title = "The Legend of Steve",
                description = "He's a hero. An Avenger. A living legend. The sentinel of liberty. Mankind's greatest threat? It's the story that shocked the world - and you won't believe where it's going next. Marvel's next big event begins right here, and Steve Rogers is in the dead center of the storm.",
                value = 156.22,
                image = "https://images-na.ssl-images-amazon.com/images/I/51-mXWgX9+L._SX323_BO1,204,203,200_.jpg",
                isFavorite = false,
                more = mutableListOf(),
                characters = mutableListOf()
            ),


            ComicItem(
                title = "Ta-Nehisi Coates Vol. 4",
                description = "He's a hero. An Avenger. A living legend. The sentinel of liberty. Mankind's greatest threat? It's the story that shocked the world - and you won't believe where it's going next. Marvel's next big event begins right here, and Steve Rogers is in the dead center of the storm.",
                value = 43.22,
                image = "https://images-na.ssl-images-amazon.com/images/I/51w2v4zXp-L._SX323_BO1,204,203,200_.jpg",
                isFavorite = false,
                more = mutableListOf(),
                characters = mutableListOf()
            ),

            ComicItem(
                title = "Captain of Nothing",
                description = "He's a hero. An Avenger. A living legend. The sentinel of liberty. Mankind's greatest threat? It's the story that shocked the world - and you won't believe where it's going next. Marvel's next big event begins right here, and Steve Rogers is in the dead center of the storm.",
                value = 2.22,
                image = "https://images-na.ssl-images-amazon.com/images/I/41RxTewpQQL._SX318_BO1,204,203,200_.jpg",
                isFavorite = false,
                more = mutableListOf(),
                characters = mutableListOf()
            ),

            ComicItem(
                title = "Winter in America",
                description = "He's a hero. An Avenger. A living legend. The sentinel of liberty. Mankind's greatest threat? It's the story that shocked the world - and you won't believe where it's going next. Marvel's next big event begins right here, and Steve Rogers is in the dead center of the storm.",
                value = 12.22,
                image = "https://images-na.ssl-images-amazon.com/images/I/51Zq6oJqBaL._SX322_BO1,204,203,200_.jpg",
                isFavorite = false,
                more = mutableListOf(),
                characters = mutableListOf()
            ),

            ).shuffled()

    }

    companion object{
        val instance by lazy { ComicsMoreMock() }
    }
}