package com.ajbell.technicaltest.detail

import androidx.lifecycle.Observer
import com.ajbell.technicaltest.testutil.InstantTaskExecutorExtension
import com.ajbell.technicaltest.data.GetMarketsResponse
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(
    InstantTaskExecutorExtension::class,
)
class MarketDetailsViewModelTest {

    @RelaxedMockK
    private lateinit var viewActionObserver: Observer<MarketDetailsViewModel.ViewAction>

    private val sut = MarketDetailsViewModel()

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
        sut.viewAction.observeForever(viewActionObserver)
    }

    @Test
    fun `when updateView is called, then viewState is updated with expected company name`() {
        val expected = "FTSE 350"
        val marketDetails = mockk<GetMarketsResponse.Market>(relaxed = true).apply {
            every { this@apply.companyName } returns COMPANY_NAME
        }
        sut.updateView(marketDetails)

        assertEquals(
            expected,
            sut.viewState.value.companyName
        )
    }

    @Test
    fun `when updateView is called, then viewState is updated with expected epic`() {
        val expected = "NMX"
        val marketDetails = mockk<GetMarketsResponse.Market>(relaxed = true).apply {
            every { this@apply.epic } returns EPIC
        }
        sut.updateView(marketDetails)

        assertEquals(
            expected,
            sut.viewState.value.epic
        )
    }

    @Test
    fun `when updateView is called, then viewState is updated with expected current price`() {
        val expected = "$5.0"
        val marketDetails = mockk<GetMarketsResponse.Market>(relaxed = true).apply {
            every { this@apply.currentPrice } returns CURRENT_PRICE
        }
        sut.updateView(marketDetails)

        assertEquals(
            expected,
            sut.viewState.value.currentPrice
        )
    }

    @Test
    fun `when updateView is called, then viewState is updated with expected current change`() {
        val expected = "$10.0"
        val marketDetails = mockk<GetMarketsResponse.Market>(relaxed = true).apply {
            every { this@apply.currentChange } returns CURRENT_CHANGE
        }
        sut.updateView(marketDetails)

        assertEquals(
            expected,
            sut.viewState.value.currentChange
        )
    }

    @Test
    fun `when updateView is called, then viewState is updated with expected current percentage`() {
        val expected = "%0.4"
        val marketDetails = mockk<GetMarketsResponse.Market>(relaxed = true).apply {
            every { this@apply.currentChangePercentage } returns CURRENT_PERCENTAGE
        }
        sut.updateView(marketDetails)

        assertEquals(
            expected,
            sut.viewState.value.currentChangePercentage
        )
    }

    @Test
    fun `when onBackPressed is called, then view action is updated with NavigateToHome`() {
        sut.onBackPressed()

        verify(exactly = 1) {
            viewActionObserver.onChanged(MarketDetailsViewModel.ViewAction.NavigateToHome)
        }
    }
}

private const val COMPANY_NAME = "FTSE 350"
private const val EPIC = "NMX"
private const val CURRENT_CHANGE = 10.0
private const val CURRENT_PRICE = 5.0
private const val CURRENT_PERCENTAGE = 0.4
