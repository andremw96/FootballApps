package com.example.wijaya_pc.footballapps.presenter

import com.example.wijaya_pc.footballapps.api.ApiRepository
import com.example.wijaya_pc.footballapps.api.TheSportDBApi
import com.example.wijaya_pc.footballapps.coroutine.TestContextProvider
import com.example.wijaya_pc.footballapps.model.Player
import com.example.wijaya_pc.footballapps.model.PlayerDetailResponse
import com.example.wijaya_pc.footballapps.view.DetailPlayerView
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class DetailPlayerPresenterTest {

    @Mock
    private
    lateinit var view: DetailPlayerView

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    @Mock
    private
    lateinit var gson: Gson

    private lateinit var presenter: DetailPlayerPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailPlayerPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetPlayerDetails() {
        val players: List<Player> = listOf(
            Player(
                "34146370",
                "https://www.thesportsdb.com/images/media/player/cutout/17okck1516994722.png",
                "Lionel Messi",
                "Forward",
                "https://www.thesportsdb.com/images/media/player/fanart/vsywsp1421580471.jpg",
                "65.76",
                "1.68",
                "Lionel Andrés \"Leo\" Messi Cuccittini (Spanish pronunciation:  (About this sound listen); born 24 June 1987) is an Argentine professional footballer who plays as a forward for Spanish club Barcelona and the Argentina national team. Often considered the best player in the world and regarded by many as the greatest of all time, Messi has a record-tying five Ballon d'Or awards, four of which he won consecutively, and a record-tying four European Golden Shoes. He has spent his entire professional career with Barcelona, where he has won 29 trophies, including eight La Liga titles, four UEFA Champions League titles, and five Copas del Rey. Both a prolific goalscorer and a creative playmaker, Messi holds the records for most official goals scored in La Liga (365), a La Liga season (50), a club football season in Europe (73), a calendar year (91), El Clásico (26), as well as those for most assists made in La Liga (144) and the Copa América (11). He has scored over 600 senior career goals for club and country.\r\n\r\nBorn and raised in central Argentina, Messi was diagnosed with a growth hormone deficiency as a child. At age 13, he relocated to Spain to join Barcelona, who agreed to pay for his medical treatment. After a fast progression through Barcelona's youth academy, Messi made his competitive debut aged 17 in October 2004. Despite being injury-prone during his early career, he established himself as an integral player for the club within the next three years, finishing 2007 as a finalist for both the Ballon d'Or and FIFA World Player of the Year award, a feat he repeated the following year. His first uninterrupted campaign came in the 2008–09 season, during which he helped Barcelona achieve the first treble in Spanish football. At 22 years old, Messi won the Ballon d'Or and FIFA World Player of the Year award by record voting margins.\r\n\r\nThree successful seasons followed, with Messi winning three consecutive FIFA Ballon d'Ors, including an unprecedented fourth. His personal best campaign statistically to date was the 2011–12 season, in which he set the La Liga and European records for most goals scored in a single season, while establishing himself as Barcelona's all-time top scorer in official competitions in March 2012. The following two seasons, Messi finished twice second for the Ballon d'Or behind Cristiano Ronaldo, his perceived career rival. Messi regained his best form during the 2014–15 campaign, breaking the all-time goalscoring records in both La Liga and the Champions League in November 2014, and led Barcelona to a historic second treble.\r\n\r\nAn Argentine international, Messi is his country's all-time leading goalscorer. At youth level, he won the 2005 FIFA World Youth Championship, finishing the tournament with both the Golden Ball and Golden Shoe, and an Olympic gold medal at the 2008 Summer Olympics. His style of play as a diminutive, left-footed dribbler drew comparisons with compatriot Diego Maradona, who declared the teenager his successor. After making his senior debut in August 2005, Messi became the youngest Argentine to play and score in a FIFA World Cup during the 2006 edition, and reached the final of the 2007 Copa América, where he was named young player of the tournament. As the squad's captain from August 2011, he led Argentina to three consecutive finals: the 2014 World Cup, for which he won the Golden Ball, and the 2015 and 2016 Copas América. After announcing his international retirement in 2016, he reversed his decision and led his country to qualification for the 2018 World Cup."
            )
        )
        val response = PlayerDetailResponse(players)
        val playerId = "34146370"

        `when`(
            gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getPlayerDetails(playerId)),
                PlayerDetailResponse::class.java
            )
        ).thenReturn(response)

        presenter.getPlayerDetails(playerId)

        verify(view).showLoading()
        verify(view).showPlayerDetail(players[0])
        verify(view).hideLoading()
    }
}