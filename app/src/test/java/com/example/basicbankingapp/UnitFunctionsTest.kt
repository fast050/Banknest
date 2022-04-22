package com.example.basicbankingapp

import com.example.basicbankingapp.ui.toCurrency
import com.example.basicbankingapp.ui.trimLastNumber
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class UnitFunctionsTest {


    @Test
    fun toCurrency_20_20WithProperFormat()
    {
        val number  = 20
        val result = number.toCurrency()

        val actualValue="$${number}.00"  // $20.00

        assertEquals(result,actualValue)
    }

    @Test
    fun trimLastNumber_100string_result10string()
    {
        val numberInStringFrom ="100"
        val result = "10"
        val actual = numberInStringFrom.trimLastNumber()

        assertEquals(result , actual)
    }

    @Test
    fun trimNumber_CurrencyFormatNumberLengthLessThan3_empty()
    {

    }

    @Test
    fun trimNumber_CurrencyFormatNumber100LengthInRange3and8_10()
    {

    }

    @Test
    fun trimNumber_CurrencyFormatNumber1000LengthEqual9_100()
    {

    }

    @Test
    fun trimNumber_CurrencyFormatNumber100000LengthEqual10_10000()
    {

    }

    @Test
    fun trimNumber_CurrencyFormatNumber1000000LengthMoreThan10_0()
    {

    }



}