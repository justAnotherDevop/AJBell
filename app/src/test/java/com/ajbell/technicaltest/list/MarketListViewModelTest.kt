package com.ajbell.technicaltest.list

import androidx.lifecycle.Observer
import com.ajbell.technicaltest.data.GetMarketsResponse
import com.ajbell.technicaltest.data.MarketRepository
import com.ajbell.technicaltest.testutil.InstantTaskExecutorExtension
import com.ajbell.technicaltest.testutil.MainCoroutineExtension
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(
    InstantTaskExecutorExtension::class,
    MainCoroutineExtension::class,
)
class MarketListViewModelTest {

    @RelaxedMockK
    private lateinit var marketObserver: Observer<List<GetMarketsResponse.Market>>

    @RelaxedMockK
    private lateinit var marketRepository: MarketRepository

    @RelaxedMockK
    private lateinit var marketListEventObserver: Observer<MarketListEvent>

    private lateinit var sut: MarketListViewModel

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
        sut = MarketListViewModel(marketRepository)
        sut.markets.observeForever(marketObserver)
        sut.event.observeForever(marketListEventObserver)
    }

    @Test
    fun `when onStart is called, then market repository is invoked to get market data`() {
        sut.onStart()

        verify {
            marketRepository.getMarkets()
        }
    }

    @Test
    fun `given market data is returned successfully, when onStart is called, then markets observer is updated`() {
        val markets = listOf(mockk<GetMarketsResponse.Market>())
        val marketResponse = mockk<GetMarketsResponse>().apply {
            every { this@apply.data } returns markets
        }
        every { marketRepository.getMarkets() } returns marketResponse

        sut.onStart()

        verify(exactly = 1) {
            marketObserver.onChanged(markets)
        }
    }

    @Test
    fun `when onItemClick is called, then market event observer is updated`() {
        val market = mockk<GetMarketsResponse.Market>()

        sut.onItemClick(market)

        verify(exactly = 1) {
            marketListEventObserver.onChanged(any())
        }
    }
}