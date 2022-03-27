package com.matiaziCelso.superhero.data.mock


import com.matiaziCelso.superhero.data.models.CharacterItem

class CharactersMock(
    private val comicsRepository: ComicsMock = ComicsMock.instance
){



    fun ironMan(): CharacterItem {

        return CharacterItem(
                "Iron Man",
                description = "Tony Stark teve uma relação difícil com seu pai, sendo enviado aos seis anos de idade para um internato, onde ele iria em breve começar a experimentar a convivência com mais pessoas. Mesmo sendo uma criança no ensino médio, ele era considerado por muitos como um prodígio e gênio. Mais tarde, é revelado que Howard Stark, mesmo sendo uma boa pessoa, quando é vítima da fraqueza dos Starks (o álcool), se tornava um alcoólatra desprezível tanto verbalmente quanto abusivo com sua esposa e filho. Foi Howard quem forçou Tony a beber sua primeira bebida alcoólica. Howard tentou ensinar à Tony muitas lições, tais como os custos de fazer a guerra e que ele deve sempre limpar suas próprias mãos.",
                image = "https://www.tenhomaisdiscosqueamigos.com/wp-content/uploads/2019/04/Iron-Man-da-Marvel-696x391.jpg",
//                comics = comicsRepository.ironMan()
        )
    }


    fun huck(): CharacterItem {

        return CharacterItem(
            "Huck",
            description = "Robert Bruce Banner era o filho do Dr. Brian Banner, um cientista atômico, e sua esposa Rebecca. Embora Rebecca amasse profundamente Bruce, que retribuiu o carinho, Brian odiava o filho. Alcoólatra, Brian Banner foi levado por um ciúme insano por Bruce, por ele ser objeto do amor de Rebecca. Além disso, Brian acreditava que seu trabalho com radiação tinha alterado o seu DNA e lhe deu um filho mutante. Ele finalmente assassinou Rebecca e foi colocado em um hospital psiquiátrico. Bruce, um grande e jovem intelectual, foi criado por sua tia, a senhora Drake, e internalizou sua grande dor e raiva sobre os sofrimentos de sua infância. Bruce mais tarde frequentou a faculdade onde conheceu Tony Stark, com quem iniciou uma grande amizade e também uma rivalidade.",
            image = "https://observatoriodocinema.uol.com.br/wp-content/uploads/2020/02/hulk-1280x720-1.jpg",
//            comics = comicsRepository.huck()
        )
    }


    fun thor(): CharacterItem {

        return CharacterItem(
            "Thor",
            description = "Filho de Odin, o deus supremo de Asgard (lar dos deuses nórdicos) e de Jord, a deusa da Terra (também chamada de Midgard ou Gaia), Thor Odinson é o príncipe de um outro mundo existente numa dimensão acima de Midgard, a Terra. Nesse mundo existem outros diversos reinos, como por exemplo, a terra dos gigantes de gelo (Jotunheim e Valhala, o lugar para onde vão os espíritos dos guerreiros que morrem em combate). Trata-se justamente de uma adaptação da mitologia nórdica, traduzida no Universo Marvel como apenas mais uma dimensão paralela.",
            image = "https://kanto.legiaodosherois.com.br/w760-h398-gnw-cfill-q80/wp-content/uploads/2019/07/legiao_JdgLpF_Ho4VwPQY1zKfjCiWZc798y2ST36Nktvxeua.png.jpeg",
//            comics = comicsRepository.thor()
        )
    }

    fun captainAmerica(): CharacterItem {

        return CharacterItem(
            "Capitão America",
            description = "Nascido em 4 de Julho de 1922, filho de um casal pobre de imigrantes irlandeses, Sarah e Joseph Rogers (falecidos em sua adolescência), Steve Rogers era um rapaz com problemas de saúde que desejava, de qualquer forma, participar dos esforços estadunidenses para vencer a Segunda Guerra Mundial. Ao ter seu alistamento recusado por sua saúde debilitada, ele deixa claro estar disposto a fazer qualquer coisa para ajudar na guerra. Esse \"qualquer coisa\" é tão literal que ele se torna parte de um experimento para a criação de soldados superficiais a alguém: o \"projeto supersoldado\", que consistia em um soro especial e criado pelo Dr. Joseph Reinstein, anos depois, um retcon diria que esse foi um codinome usado pelo cientista Abraham Erskine.[15] O nome \"Erskine\" foi usado pela primeira vez em um romance The Great Gold Steal por Ted Branco publicado pela Bantam Books em 1968.",
            image = "https://thumbs.web.sapo.io/?W=800&H=0&delay_optim=1&epic=ZjI455nvSE2/Nd1AQaWNzvbYsvhUq7kguwClKeI1Pah+G5gWfofHPDPKcH/NNEP41rlZ9ZbfxoisyufoH5BPhfXmGbf9gxef2KuuhRlPESrMgNo=",
//            comics = comicsRepository.captainAmerica()
        )
    }

    companion object{
        val instance by lazy { CharactersMock() }
    }

}