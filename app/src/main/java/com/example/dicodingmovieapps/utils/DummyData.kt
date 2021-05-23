package com.example.dicodingmovieapps.utils

import com.example.dicodingmovieapps.data.CastMoviesEntity
import com.example.dicodingmovieapps.data.MoviesEntity

object DummyData {

    fun generateDataMovies(): List<MoviesEntity> {

        val movies = ArrayList<MoviesEntity>()

        movies.add(
            MoviesEntity(
                1,
                "Tom Clancy's Without Remorse",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/rEm96ib0sPiZBADNKBHKBv5bve9.jpg",
                "https://www.themoviedb.org/t/p/w1920_and_h800_multi_faces/fPGeS6jgdLovQAKunNHX8l0avCy.jpg",
                "2021",
                " Action, Adventure, Thriller, War",
                73,
                "1h 49m",
                "An elite Navy SEAL uncovers an international conspiracy while seeking justice for the murder of his pregnant wife."

            )
        )
        movies.add(
            MoviesEntity(
                2,
                "Mortal Kombat",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg",
                "https://www.themoviedb.org/t/p/w1920_and_h800_multi_faces/6ELCZlTA5lGUops70hKdB83WJxH.jpg",
                "2021",
                " Action, Fantasy, Adventure ",
                77,
                "1h 50m",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe."
            )
        )
        movies.add(
            MoviesEntity(
                3,
                "Godzilla vs. Kong",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                "https://www.themoviedb.org/t/p/w1920_and_h800_multi_faces/inJjDhCjfhh3RtrJWBmmDqeuSYC.jpg",
                "2021",
                "Science Fiction, Action, Drama ",
                81,
                "1h 53m",
                "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages."
            )
        )
        movies.add(
            MoviesEntity(
                4,
                "22 vs. Earth ",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/32vLDKSzcCMh55zqqaSqqUA8naz.jpg",
                "https://www.themoviedb.org/t/p/w1920_and_h800_multi_faces/n2y7T8wJVjJ8yLhQXQgNCpsC3ga.jpg",
                "2021",
                " Family, Animation, Comedy ",
                72,
                "9m",
                "Set before the events of ‘Soul’, 22 refuses to go to Earth, enlisting a gang of 5 new souls in attempt of rebellion. However, 22’s subversive plot leads to a surprising revelation about the meaning of life."
            )
        )
        movies.add(
            MoviesEntity(
                5,
                "Demon Slayer -Kimetsu no Yaiba- The Movie: Mugen Train ",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/h8Rb9gBr48ODIwYUttZNYeMWeUU.jpg",
                "https://www.themoviedb.org/t/p/w1920_and_h800_multi_faces/xPpXYnCWfjkt3zzE0dpCNME1pXF.jpg",
                "2020",
                " Animation, Action, Adventure, Fantasy, Drama ",
                84,
                "1h 57m",
                "Tanjirō Kamado, joined with Inosuke Hashibira, a boy raised by boars who wears a boar's head, and Zenitsu Agatsuma, a scared boy who reveals his true power when he sleeps, boards the Infinity Train on a new mission with the Fire Hashira, Kyōjurō Rengoku, to defeat a demon who has been tormenting the people and killing the demon slayers who oppose it!"
            )
        )
        movies.add(
            MoviesEntity(
                6,
                "Raya and the Last Dragon ",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lPsD10PP4rgUGiGR4CCXA6iY0QQ.jpg",
                "https://www.themoviedb.org/t/p/w1920_and_h800_multi_faces/7prYzufdIOy1KCTZKVWpjBFqqNr.jpg",
                "2021",
                " Animation, Adventure, Fantasy, Family, Action ",
                82,
                "1h 47m",
                "Long ago, in the fantasy world of Kumandra, humans and dragons lived together in harmony. But when an evil force threatened the land, the dragons sacrificed themselves to save humanity. Now, 500 years later, that same evil has returned and it’s up to a lone warrior, Raya, to track down the legendary last dragon to restore the fractured land and its divided people."
            )
        )
        movies.add(
            MoviesEntity(
                7,
                "Nobody",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/oBgWY00bEFeZ9N25wWVyuQddbAo.jpg",
                "https://www.themoviedb.org/t/p/w1920_and_h800_multi_faces/6zbKgwgaaCyyBXE4Sun4oWQfQmi.jpg",
                "2021",
                " Action, Thriller, Crime, Comedy ",
                84,
                "1h 32m",
                "Hutch Mansell, a suburban dad, overlooked husband, nothing neighbor — a \"nobody.\" When two thieves break into his home one night, Hutch's unknown long-simmering rage is ignited and propels him on a brutal path that will uncover dark secrets he fought to leave behind."
            )
        )
        movies.add(
            MoviesEntity(
                8,
                "Zack Snyder's Justice League ",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg",
                "https://www.themoviedb.org/t/p/w1920_and_h800_multi_faces/pcDc2WJAYGJTTvRSEIpRZwM3Ola.jpg",
                "2021",
                " Action, Adventure, Fantasy, Science Fiction ",
                85,
                "4h 2m",
                "Determined to ensure Superman's ultimate sacrifice was not in vain, Bruce Wayne aligns forces with Diana Prince with plans to recruit a team of metahumans to protect the world from an approaching threat of catastrophic proportions."
            )
        )
        movies.add(
            MoviesEntity(
                9,
                "Tom & Jerry",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/8XZI9QZ7Pm3fVkigWJPbrXCMzjq.jpg",
                "https://www.themoviedb.org/t/p/w1920_and_h800_multi_faces/9ns9463dwOeo1CK1JU2wirL5Yi1.jpg",
                "2021",
                " Comedy, Family, Animation ",
                73,
                "1h 41m",
                "Tom the cat and Jerry the mouse get kicked out of their home and relocate to a fancy New York hotel, where a scrappy employee named Kayla will lose her job if she can’t evict Jerry before a high-class wedding at the hotel. Her solution? Hiring Tom to get rid of the pesky mouse."
            )
        )
        movies.add(
            MoviesEntity(
                10,
                "Chaos Walking ",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/9kg73Mg8WJKlB9Y2SAJzeDKAnuB.jpg",
                "https://www.themoviedb.org/t/p/w1920_and_h800_multi_faces/ovggmAOu1IbPGTQE8lg4lBasNC7.jpg",
                "2021",
                " Science Fiction, Action, Adventure, Thriller ",
                71,
                "1h 49m",
                "Two unlikely companions embark on a perilous adventure through the badlands of an unexplored planet as they try to escape a dangerous and disorienting reality, where all inner thoughts are seen and heard by everyone."
            )
        )

        return movies
    }

    fun generateDataTvSeries(): List<MoviesEntity> {

        val tvSeries = ArrayList<MoviesEntity>()

        tvSeries.add(
            MoviesEntity(
                11,
                "The Flash",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                "https://www.themoviedb.org/t/p/w1920_and_h800_multi_faces/jeruqNWhqRqOR1QyqdQdHunrvU5.jpg",
                "2014",
                " Drama, Sci-Fi & Fantasy ",
                77,
                "7 Season",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash."
            )
        )
        tvSeries.add(
            MoviesEntity(
                12,
                "Grey's Anatomy",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
                "https://www.themoviedb.org/t/p/w1920_and_h800_multi_faces/edmk8xjGBsYVIf4QtLY9WMaMcXZ.jpg",
                "2005",
                " Drama ",
                82,
                "17 Season",
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital."
            )
        )
        tvSeries.add(
            MoviesEntity(
                13,
                "Riverdale",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/wRbjVBdDo5qHAEOVYoMWpM58FSA.jpg",
                "https://www.themoviedb.org/t/p/w1920_and_h800_multi_faces/qZtAf4Z1lazGQoYVXiHOrvLr5lI.jpg",
                "2017",
                " Mystery, Drama, Crime ",
                86,
                "5 Season",
                "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade."
            )
        )
        tvSeries.add(
            MoviesEntity(
                14,
                "The Bad Batch",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/WjQmEWFrOf98nT5aEfUfVYz9N2.jpg",
                "https://www.themoviedb.org/t/p/w1920_and_h800_multi_faces/sjxtIUCWR74yPPcZFfTsToepfWm.jpg",
                "2021",
                " Sci-Fi & Fantasy, Action & Adventure, Animation ",
                90,
                "1 Season",
                "Follow the elite and experimental Clones of the Bad Batch as they find their way in a rapidly changing galaxy in the aftermath of the Clone Wars."
            )
        )
        tvSeries.add(
            MoviesEntity(
                15,
                "Lucifer",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg",
                "https://www.themoviedb.org/t/p/w1920_and_h800_multi_faces/ta5oblpMlEcIPIS2YGcq9XEkWK2.jpg",
                "2016",
                " Crime, Sci-Fi & Fantasy ",
                85,
                "5 Season",
                "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape."
            )
        )
        tvSeries.add(
            MoviesEntity(
                16,
                "The Handmaid's Tale ",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/oIkxqt6ug5zT5ZSUUyc1Iqopf02.jpg",
                "https://www.themoviedb.org/t/p/w1920_and_h800_multi_faces/hNiGqLsiD30C194lci7VYDmciHD.jpg",
                "2017",
                " Sci-Fi & Fantasy, Drama ",
                82,
                "5 Season",
                "Set in a dystopian future, a woman is forced to live as a concubine under a fundamentalist theocratic dictatorship. A TV adaptation of Margaret Atwood's novel."
            )
        )
        tvSeries.add(
            MoviesEntity(
                17,
                "Game of Thrones",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",
                "https://www.themoviedb.org/t/p/w1920_and_h800_multi_faces/suopoADq0k8YZr4dQXcU6pToj6s.jpg",
                "2011",
                " Sci-Fi & Fantasy, Drama, Action & Adventure ",
                84,
                "8 Season",
                "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond."
            )
        )
        tvSeries.add(
            MoviesEntity(
                18,
                "Legacies",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/qTZIgXrBKURBK1KrsT7fe3qwtl9.jpg",
                "https://www.themoviedb.org/t/p/w1920_and_h800_multi_faces/fRYwdeNjMqC30EhofPx5PlDpdun.jpg",
                "2018",
                " Sci-Fi & Fantasy, Drama ",
                86,
                "3 Season",
                "In a place where young witches, vampires, and werewolves are nurtured to be their best selves in spite of their worst impulses, Klaus Mikaelson’s daughter, 17-year-old Hope Mikaelson, Alaric Saltzman’s twins, Lizzie and Josie Saltzman, among others, come of age into heroes and villains at The Salvatore School for the Young and Gifted."
            )
        )
        tvSeries.add(
            MoviesEntity(
                19,
                "The Walking Dead",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/rqeYMLryjcawh2JeRpCVUDXYM5b.jpg",
                "https://www.themoviedb.org/t/p/w1920_and_h800_multi_faces/uro2Khv7JxlzXtLb8tCIbRhkb9E.jpg",
                "2010",
                " Action & Adventure, Drama, Sci-Fi & Fantasy ",
                81,
                "10 Season",
                "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way."
            )
        )
        tvSeries.add(
            MoviesEntity(
                20,
                "The Good Doctor",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                "https://www.themoviedb.org/t/p/w1920_and_h800_multi_faces/mZjZgY6ObiKtVuKVDrnS9VnuNlE.jpg",
                "2017",
                " Drama ",
                86,
                "4 Season",
                "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives"
            )
        )

        return tvSeries
    }

    fun generateMovieCredit(): List<CastMoviesEntity> {

        val movieCredit = ArrayList<CastMoviesEntity>()

        movieCredit.add(
            CastMoviesEntity(
                "https://www.themoviedb.org/t/p/w138_and_h175_face/eLArQ1HF3UgpCA6uRF1TQBFsMQS.jpg",
                "Danielle Rose Russell",
                "Hope Mikaelson",
                "https://www.themoviedb.org/t/p/w138_and_h175_face/mbBqJRFABxqR9OmfeYVo5xvwh4C.jpg",
                "Aria Shahghasemi",
                "Landon Kirby ",
                "https://www.themoviedb.org/t/p/w138_and_h175_face/7VKR2vroCbzVeklTzYSjnVVOwkl.jpg",
                "Matthew Davis",
                "Alaric Saltzman "
            )
        )

        return movieCredit
    }

}