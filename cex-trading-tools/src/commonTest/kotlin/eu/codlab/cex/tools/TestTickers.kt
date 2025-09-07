package eu.codlab.cex.tools

import eu.codlab.cex.tools.extrapolate.Bar5m
import eu.codlab.cex.tools.extrapolate.Predict
import org.junit.Test

class TestTickers {
    val values = listOf(
        Bar5m(
            lastPrice = 1.159,
            openPrice = 1.159,
            highPrice = 1.16,
            lowPrice = 1.158,
            closePrice = 1.159,
            volume = 0.0,
            timestamp = 1757179200000
        ),
        Bar5m(
            lastPrice = 1.155,
            openPrice = 1.159,
            highPrice = 1.159,
            lowPrice = 1.155,
            closePrice = 1.155,
            volume = 0.0,
            timestamp = 1757179500000
        ),
        Bar5m(
            lastPrice = 1.156,
            openPrice = 1.155,
            highPrice = 1.156,
            lowPrice = 1.154,
            closePrice = 1.156,
            volume = 0.0,
            timestamp = 1757179800000
        ),
        Bar5m(
            lastPrice = 1.155,
            openPrice = 1.156,
            highPrice = 1.159,
            lowPrice = 1.155,
            closePrice = 1.155,
            volume = 0.0,
            timestamp = 1757180100000
        ),
        Bar5m(
            lastPrice = 1.155,
            openPrice = 1.155,
            highPrice = 1.155,
            lowPrice = 1.153,
            closePrice = 1.155,
            volume = 0.0,
            timestamp = 1757180400000
        ),
        Bar5m(
            lastPrice = 1.155,
            openPrice = 1.155,
            highPrice = 1.156,
            lowPrice = 1.153,
            closePrice = 1.155,
            volume = 0.0,
            timestamp = 1757180700000
        ),
        Bar5m(
            lastPrice = 1.155,
            openPrice = 1.155,
            highPrice = 1.156,
            lowPrice = 1.154,
            closePrice = 1.155,
            volume = 0.0,
            timestamp = 1757181000000
        ),
        Bar5m(
            lastPrice = 1.158,
            openPrice = 1.155,
            highPrice = 1.158,
            lowPrice = 1.154,
            closePrice = 1.158,
            volume = 0.0,
            timestamp = 1757181300000
        ),
        Bar5m(
            lastPrice = 1.156,
            openPrice = 1.158,
            highPrice = 1.158,
            lowPrice = 1.155,
            closePrice = 1.156,
            volume = 0.0,
            timestamp = 1757181600000
        ),
        Bar5m(
            lastPrice = 1.157,
            openPrice = 1.156,
            highPrice = 1.157,
            lowPrice = 1.155,
            closePrice = 1.157,
            volume = 0.0,
            timestamp = 1757181900000
        ),
        Bar5m(
            lastPrice = 1.159,
            openPrice = 1.157,
            highPrice = 1.16,
            lowPrice = 1.156,
            closePrice = 1.159,
            volume = 0.0,
            timestamp = 1757182200000
        ),
        Bar5m(
            lastPrice = 1.161,
            openPrice = 1.159,
            highPrice = 1.162,
            lowPrice = 1.158,
            closePrice = 1.161,
            volume = 0.0,
            timestamp = 1757182500000
        ),
        Bar5m(
            lastPrice = 1.161,
            openPrice = 1.161,
            highPrice = 1.162,
            lowPrice = 1.16,
            closePrice = 1.161,
            volume = 0.0,
            timestamp = 1757182800000
        ),
        Bar5m(
            lastPrice = 1.161,
            openPrice = 1.161,
            highPrice = 1.162,
            lowPrice = 1.16,
            closePrice = 1.161,
            volume = 0.0,
            timestamp = 1757183100000
        ),
        Bar5m(
            lastPrice = 1.159,
            openPrice = 1.161,
            highPrice = 1.161,
            lowPrice = 1.158,
            closePrice = 1.159,
            volume = 0.0,
            timestamp = 1757183400000
        ),
        Bar5m(
            lastPrice = 1.158,
            openPrice = 1.159,
            highPrice = 1.159,
            lowPrice = 1.157,
            closePrice = 1.158,
            volume = 0.0,
            timestamp = 1757183700000
        ),
        Bar5m(
            lastPrice = 1.155,
            openPrice = 1.158,
            highPrice = 1.159,
            lowPrice = 1.155,
            closePrice = 1.155,
            volume = 0.0,
            timestamp = 1757184000000
        ),
        Bar5m(
            lastPrice = 1.159,
            openPrice = 1.155,
            highPrice = 1.159,
            lowPrice = 1.155,
            closePrice = 1.159,
            volume = 0.0,
            timestamp = 1757184300000
        ),
        Bar5m(
            lastPrice = 1.159,
            openPrice = 1.159,
            highPrice = 1.16,
            lowPrice = 1.158,
            closePrice = 1.159,
            volume = 0.0,
            timestamp = 1757184600000
        ),
        Bar5m(
            lastPrice = 1.158,
            openPrice = 1.159,
            highPrice = 1.16,
            lowPrice = 1.158,
            closePrice = 1.158,
            volume = 0.0,
            timestamp = 1757184900000
        ),
        Bar5m(
            lastPrice = 1.158,
            openPrice = 1.158,
            highPrice = 1.161,
            lowPrice = 1.156,
            closePrice = 1.158,
            volume = 0.0,
            timestamp = 1757185200000
        ),
        Bar5m(
            lastPrice = 1.157,
            openPrice = 1.158,
            highPrice = 1.158,
            lowPrice = 1.156,
            closePrice = 1.157,
            volume = 0.0,
            timestamp = 1757185500000
        ),
        Bar5m(
            lastPrice = 1.155,
            openPrice = 1.157,
            highPrice = 1.158,
            lowPrice = 1.155,
            closePrice = 1.155,
            volume = 0.0,
            timestamp = 1757185800000
        ),
        Bar5m(
            lastPrice = 1.153,
            openPrice = 1.155,
            highPrice = 1.155,
            lowPrice = 1.152,
            closePrice = 1.153,
            volume = 0.0,
            timestamp = 1757186100000
        ),
        Bar5m(
            lastPrice = 1.155,
            openPrice = 1.153,
            highPrice = 1.155,
            lowPrice = 1.153,
            closePrice = 1.155,
            volume = 0.0,
            timestamp = 1757186400000
        ),
        Bar5m(
            lastPrice = 1.152,
            openPrice = 1.155,
            highPrice = 1.155,
            lowPrice = 1.152,
            closePrice = 1.152,
            volume = 0.0,
            timestamp = 1757186700000
        ),
        Bar5m(
            lastPrice = 1.152,
            openPrice = 1.152,
            highPrice = 1.153,
            lowPrice = 1.151,
            closePrice = 1.152,
            volume = 0.0,
            timestamp = 1757187000000
        ),
        Bar5m(
            lastPrice = 1.151,
            openPrice = 1.152,
            highPrice = 1.152,
            lowPrice = 1.151,
            closePrice = 1.151,
            volume = 0.0,
            timestamp = 1757187300000
        ),
        Bar5m(
            lastPrice = 1.149,
            openPrice = 1.151,
            highPrice = 1.151,
            lowPrice = 1.149,
            closePrice = 1.149,
            volume = 0.0,
            timestamp = 1757187600000
        ),
        Bar5m(
            lastPrice = 1.151,
            openPrice = 1.149,
            highPrice = 1.152,
            lowPrice = 1.148,
            closePrice = 1.151,
            volume = 0.0,
            timestamp = 1757187900000
        ),
        Bar5m(
            lastPrice = 1.15,
            openPrice = 1.151,
            highPrice = 1.151,
            lowPrice = 1.149,
            closePrice = 1.15,
            volume = 0.0,
            timestamp = 1757188200000
        ),
        Bar5m(
            lastPrice = 1.153,
            openPrice = 1.15,
            highPrice = 1.153,
            lowPrice = 1.15,
            closePrice = 1.153,
            volume = 0.0,
            timestamp = 1757188500000
        ),
        Bar5m(
            lastPrice = 1.155,
            openPrice = 1.153,
            highPrice = 1.155,
            lowPrice = 1.152,
            closePrice = 1.155,
            volume = 0.0,
            timestamp = 1757188800000
        ),
        Bar5m(
            lastPrice = 1.153,
            openPrice = 1.155,
            highPrice = 1.155,
            lowPrice = 1.153,
            closePrice = 1.153,
            volume = 0.0,
            timestamp = 1757189100000
        ),
        Bar5m(
            lastPrice = 1.155,
            openPrice = 1.153,
            highPrice = 1.156,
            lowPrice = 1.153,
            closePrice = 1.155,
            volume = 0.0,
            timestamp = 1757189400000
        ),
        Bar5m(
            lastPrice = 1.158,
            openPrice = 1.155,
            highPrice = 1.158,
            lowPrice = 1.155,
            closePrice = 1.158,
            volume = 0.0,
            timestamp = 1757189700000
        ),
        Bar5m(
            lastPrice = 1.154,
            openPrice = 1.158,
            highPrice = 1.158,
            lowPrice = 1.154,
            closePrice = 1.154,
            volume = 0.0,
            timestamp = 1757190000000
        ),
        Bar5m(
            lastPrice = 1.156,
            openPrice = 1.154,
            highPrice = 1.156,
            lowPrice = 1.154,
            closePrice = 1.156,
            volume = 0.0,
            timestamp = 1757190300000
        ),
        Bar5m(
            lastPrice = 1.156,
            openPrice = 1.156,
            highPrice = 1.156,
            lowPrice = 1.155,
            closePrice = 1.156,
            volume = 0.0,
            timestamp = 1757190600000
        ),
        Bar5m(
            lastPrice = 1.156,
            openPrice = 1.156,
            highPrice = 1.156,
            lowPrice = 1.155,
            closePrice = 1.156,
            volume = 0.0,
            timestamp = 1757190900000
        ),
        Bar5m(
            lastPrice = 1.16,
            openPrice = 1.156,
            highPrice = 1.162,
            lowPrice = 1.156,
            closePrice = 1.16,
            volume = 0.0,
            timestamp = 1757191200000
        ),
        Bar5m(
            lastPrice = 1.162,
            openPrice = 1.16,
            highPrice = 1.162,
            lowPrice = 1.16,
            closePrice = 1.162,
            volume = 0.0,
            timestamp = 1757191500000
        ),
        Bar5m(
            lastPrice = 1.164,
            openPrice = 1.162,
            highPrice = 1.166,
            lowPrice = 1.162,
            closePrice = 1.164,
            volume = 0.0,
            timestamp = 1757191800000
        ),
        Bar5m(
            lastPrice = 1.165,
            openPrice = 1.164,
            highPrice = 1.165,
            lowPrice = 1.163,
            closePrice = 1.165,
            volume = 0.0,
            timestamp = 1757192100000
        ),
        Bar5m(
            lastPrice = 1.164,
            openPrice = 1.165,
            highPrice = 1.165,
            lowPrice = 1.163,
            closePrice = 1.164,
            volume = 0.0,
            timestamp = 1757192400000
        ),
        Bar5m(
            lastPrice = 1.164,
            openPrice = 1.164,
            highPrice = 1.165,
            lowPrice = 1.163,
            closePrice = 1.164,
            volume = 0.0,
            timestamp = 1757192700000
        ),
        Bar5m(
            lastPrice = 1.162,
            openPrice = 1.164,
            highPrice = 1.165,
            lowPrice = 1.162,
            closePrice = 1.162,
            volume = 0.0,
            timestamp = 1757193000000
        ),
        Bar5m(
            lastPrice = 1.164,
            openPrice = 1.162,
            highPrice = 1.164,
            lowPrice = 1.16,
            closePrice = 1.164,
            volume = 0.0,
            timestamp = 1757193300000
        ),
        Bar5m(
            lastPrice = 1.163,
            openPrice = 1.164,
            highPrice = 1.165,
            lowPrice = 1.163,
            closePrice = 1.163,
            volume = 0.0,
            timestamp = 1757193600000
        ),
        Bar5m(
            lastPrice = 1.164,
            openPrice = 1.163,
            highPrice = 1.165,
            lowPrice = 1.163,
            closePrice = 1.164,
            volume = 0.0,
            timestamp = 1757193900000
        ),
        Bar5m(
            lastPrice = 1.164,
            openPrice = 1.164,
            highPrice = 1.164,
            lowPrice = 1.163,
            closePrice = 1.164,
            volume = 0.0,
            timestamp = 1757194200000
        ),
        Bar5m(
            lastPrice = 1.165,
            openPrice = 1.164,
            highPrice = 1.165,
            lowPrice = 1.163,
            closePrice = 1.165,
            volume = 0.0,
            timestamp = 1757194500000
        ),
        Bar5m(
            lastPrice = 1.165,
            openPrice = 1.165,
            highPrice = 1.166,
            lowPrice = 1.164,
            closePrice = 1.165,
            volume = 0.0,
            timestamp = 1757194800000
        ),
        Bar5m(
            lastPrice = 1.166,
            openPrice = 1.165,
            highPrice = 1.166,
            lowPrice = 1.164,
            closePrice = 1.166,
            volume = 0.0,
            timestamp = 1757195100000
        ),
        Bar5m(
            lastPrice = 1.166,
            openPrice = 1.166,
            highPrice = 1.167,
            lowPrice = 1.165,
            closePrice = 1.166,
            volume = 0.0,
            timestamp = 1757195400000
        ),
        Bar5m(
            lastPrice = 1.172,
            openPrice = 1.166,
            highPrice = 1.172,
            lowPrice = 1.165,
            closePrice = 1.172,
            volume = 0.0,
            timestamp = 1757195700000
        ),
        Bar5m(
            lastPrice = 1.169,
            openPrice = 1.172,
            highPrice = 1.175,
            lowPrice = 1.169,
            closePrice = 1.169,
            volume = 0.0,
            timestamp = 1757196000000
        ),
        Bar5m(
            lastPrice = 1.169,
            openPrice = 1.169,
            highPrice = 1.17,
            lowPrice = 1.167,
            closePrice = 1.169,
            volume = 0.0,
            timestamp = 1757196300000
        ),
        Bar5m(
            lastPrice = 1.168,
            openPrice = 1.169,
            highPrice = 1.169,
            lowPrice = 1.167,
            closePrice = 1.168,
            volume = 0.0,
            timestamp = 1757196600000
        ),
        Bar5m(
            lastPrice = 1.168,
            openPrice = 1.168,
            highPrice = 1.169,
            lowPrice = 1.166,
            closePrice = 1.168,
            volume = 0.0,
            timestamp = 1757196900000
        ),
        Bar5m(
            lastPrice = 1.165,
            openPrice = 1.168,
            highPrice = 1.168,
            lowPrice = 1.164,
            closePrice = 1.165,
            volume = 0.0,
            timestamp = 1757197200000
        ),
        Bar5m(
            lastPrice = 1.166,
            openPrice = 1.165,
            highPrice = 1.167,
            lowPrice = 1.164,
            closePrice = 1.166,
            volume = 0.0,
            timestamp = 1757197500000
        ),
        Bar5m(
            lastPrice = 1.164,
            openPrice = 1.166,
            highPrice = 1.167,
            lowPrice = 1.164,
            closePrice = 1.164,
            volume = 0.0,
            timestamp = 1757197800000
        ),
        Bar5m(
            lastPrice = 1.164,
            openPrice = 1.164,
            highPrice = 1.165,
            lowPrice = 1.163,
            closePrice = 1.164,
            volume = 0.0,
            timestamp = 1757198100000
        ),
        Bar5m(
            lastPrice = 1.164,
            openPrice = 1.164,
            highPrice = 1.166,
            lowPrice = 1.163,
            closePrice = 1.164,
            volume = 0.0,
            timestamp = 1757198400000
        ),
        Bar5m(
            lastPrice = 1.165,
            openPrice = 1.164,
            highPrice = 1.165,
            lowPrice = 1.163,
            closePrice = 1.165,
            volume = 0.0,
            timestamp = 1757198700000
        ),
        Bar5m(
            lastPrice = 1.164,
            openPrice = 1.165,
            highPrice = 1.166,
            lowPrice = 1.163,
            closePrice = 1.164,
            volume = 0.0,
            timestamp = 1757199000000
        ),
        Bar5m(
            lastPrice = 1.165,
            openPrice = 1.164,
            highPrice = 1.166,
            lowPrice = 1.164,
            closePrice = 1.165,
            volume = 0.0,
            timestamp = 1757199300000
        ),
        Bar5m(
            lastPrice = 1.162,
            openPrice = 1.165,
            highPrice = 1.165,
            lowPrice = 1.162,
            closePrice = 1.162,
            volume = 0.0,
            timestamp = 1757199600000
        ),
        Bar5m(
            lastPrice = 1.163,
            openPrice = 1.162,
            highPrice = 1.163,
            lowPrice = 1.161,
            closePrice = 1.163,
            volume = 0.0,
            timestamp = 1757199900000
        ),
        Bar5m(
            lastPrice = 1.163,
            openPrice = 1.163,
            highPrice = 1.164,
            lowPrice = 1.162,
            closePrice = 1.163,
            volume = 0.0,
            timestamp = 1757200200000
        ),
        Bar5m(
            lastPrice = 1.163,
            openPrice = 1.163,
            highPrice = 1.164,
            lowPrice = 1.162,
            closePrice = 1.163,
            volume = 0.0,
            timestamp = 1757200500000
        ),
        Bar5m(
            lastPrice = 1.164,
            openPrice = 1.163,
            highPrice = 1.166,
            lowPrice = 1.163,
            closePrice = 1.164,
            volume = 0.0,
            timestamp = 1757200800000
        ),
        Bar5m(
            lastPrice = 1.165,
            openPrice = 1.164,
            highPrice = 1.166,
            lowPrice = 1.163,
            closePrice = 1.165,
            volume = 0.0,
            timestamp = 1757201100000
        ),
        Bar5m(
            lastPrice = 1.162,
            openPrice = 1.165,
            highPrice = 1.166,
            lowPrice = 1.162,
            closePrice = 1.162,
            volume = 0.0,
            timestamp = 1757201400000
        ),
        Bar5m(
            lastPrice = 1.164,
            openPrice = 1.162,
            highPrice = 1.165,
            lowPrice = 1.162,
            closePrice = 1.164,
            volume = 0.0,
            timestamp = 1757201700000
        ),
        Bar5m(
            lastPrice = 1.163,
            openPrice = 1.164,
            highPrice = 1.165,
            lowPrice = 1.163,
            closePrice = 1.163,
            volume = 0.0,
            timestamp = 1757202000000
        ),
        Bar5m(
            lastPrice = 1.163,
            openPrice = 1.163,
            highPrice = 1.164,
            lowPrice = 1.162,
            closePrice = 1.163,
            volume = 0.0,
            timestamp = 1757202300000
        ),
        Bar5m(
            lastPrice = 1.161,
            openPrice = 1.163,
            highPrice = 1.163,
            lowPrice = 1.161,
            closePrice = 1.161,
            volume = 0.0,
            timestamp = 1757202600000
        ),
        Bar5m(
            lastPrice = 1.16,
            openPrice = 1.161,
            highPrice = 1.161,
            lowPrice = 1.16,
            closePrice = 1.16,
            volume = 0.0,
            timestamp = 1757202900000
        ),
        Bar5m(
            lastPrice = 1.162,
            openPrice = 1.16,
            highPrice = 1.163,
            lowPrice = 1.16,
            closePrice = 1.162,
            volume = 0.0,
            timestamp = 1757203200000
        ),
        Bar5m(
            lastPrice = 1.163,
            openPrice = 1.162,
            highPrice = 1.164,
            lowPrice = 1.161,
            closePrice = 1.163,
            volume = 0.0,
            timestamp = 1757203500000
        ),
        Bar5m(
            lastPrice = 1.162,
            openPrice = 1.163,
            highPrice = 1.165,
            lowPrice = 1.162,
            closePrice = 1.162,
            volume = 0.0,
            timestamp = 1757203800000
        ),
        Bar5m(
            lastPrice = 1.165,
            openPrice = 1.162,
            highPrice = 1.165,
            lowPrice = 1.161,
            closePrice = 1.165,
            volume = 0.0,
            timestamp = 1757204100000
        ),
        Bar5m(
            lastPrice = 1.162,
            openPrice = 1.165,
            highPrice = 1.165,
            lowPrice = 1.162,
            closePrice = 1.162,
            volume = 0.0,
            timestamp = 1757204400000
        ),
        Bar5m(
            lastPrice = 1.162,
            openPrice = 1.162,
            highPrice = 1.164,
            lowPrice = 1.16,
            closePrice = 1.162,
            volume = 0.0,
            timestamp = 1757204700000
        ),
        Bar5m(
            lastPrice = 1.162,
            openPrice = 1.162,
            highPrice = 1.164,
            lowPrice = 1.16,
            closePrice = 1.162,
            volume = 0.0,
            timestamp = 1757205000000
        ),
        Bar5m(
            lastPrice = 1.165,
            openPrice = 1.162,
            highPrice = 1.165,
            lowPrice = 1.161,
            closePrice = 1.165,
            volume = 0.0,
            timestamp = 1757205300000
        ),
        Bar5m(
            lastPrice = 1.164,
            openPrice = 1.165,
            highPrice = 1.165,
            lowPrice = 1.163,
            closePrice = 1.164,
            volume = 0.0,
            timestamp = 1757205600000
        ),
        Bar5m(
            lastPrice = 1.168,
            openPrice = 1.164,
            highPrice = 1.168,
            lowPrice = 1.164,
            closePrice = 1.168,
            volume = 0.0,
            timestamp = 1757205900000
        ),
        Bar5m(
            lastPrice = 1.164,
            openPrice = 1.168,
            highPrice = 1.169,
            lowPrice = 1.164,
            closePrice = 1.164,
            volume = 0.0,
            timestamp = 1757206200000
        ),
        Bar5m(
            lastPrice = 1.164,
            openPrice = 1.164,
            highPrice = 1.165,
            lowPrice = 1.163,
            closePrice = 1.164,
            volume = 0.0,
            timestamp = 1757206500000
        ),
        Bar5m(
            lastPrice = 1.163,
            openPrice = 1.164,
            highPrice = 1.165,
            lowPrice = 1.163,
            closePrice = 1.163,
            volume = 0.0,
            timestamp = 1757206800000
        ),
        Bar5m(
            lastPrice = 1.167,
            openPrice = 1.163,
            highPrice = 1.167,
            lowPrice = 1.163,
            closePrice = 1.167,
            volume = 0.0,
            timestamp = 1757207100000
        ),
        Bar5m(
            lastPrice = 1.164,
            openPrice = 1.167,
            highPrice = 1.167,
            lowPrice = 1.164,
            closePrice = 1.164,
            volume = 0.0,
            timestamp = 1757207400000
        ),
        Bar5m(
            lastPrice = 1.164,
            openPrice = 1.164,
            highPrice = 1.167,
            lowPrice = 1.164,
            closePrice = 1.164,
            volume = 0.0,
            timestamp = 1757207700000
        ),
        Bar5m(
            lastPrice = 1.163,
            openPrice = 1.164,
            highPrice = 1.165,
            lowPrice = 1.163,
            closePrice = 1.163,
            volume = 0.0,
            timestamp = 1757208000000
        ),
        Bar5m(
            lastPrice = 1.165,
            openPrice = 1.163,
            highPrice = 1.166,
            lowPrice = 1.163,
            closePrice = 1.165,
            volume = 0.0,
            timestamp = 1757208300000
        ),
        Bar5m(
            lastPrice = 1.166,
            openPrice = 1.165,
            highPrice = 1.166,
            lowPrice = 1.164,
            closePrice = 1.166,
            volume = 0.0,
            timestamp = 1757208600000
        ),
        Bar5m(
            lastPrice = 1.167,
            openPrice = 1.166,
            highPrice = 1.167,
            lowPrice = 1.164,
            closePrice = 1.167,
            volume = 0.0,
            timestamp = 1757208900000
        ),
        Bar5m(
            lastPrice = 1.167,
            openPrice = 1.167,
            highPrice = 1.168,
            lowPrice = 1.166,
            closePrice = 1.167,
            volume = 0.0,
            timestamp = 1757209200000
        ),
        Bar5m(
            lastPrice = 1.173,
            openPrice = 1.167,
            highPrice = 1.174,
            lowPrice = 1.166,
            closePrice = 1.173,
            volume = 0.0,
            timestamp = 1757209500000
        ),
        Bar5m(
            lastPrice = 1.17,
            openPrice = 1.173,
            highPrice = 1.173,
            lowPrice = 1.17,
            closePrice = 1.17,
            volume = 0.0,
            timestamp = 1757209800000
        ),
        Bar5m(
            lastPrice = 1.167,
            openPrice = 1.17,
            highPrice = 1.17,
            lowPrice = 1.166,
            closePrice = 1.167,
            volume = 0.0,
            timestamp = 1757210100000
        ),
        Bar5m(
            lastPrice = 1.164,
            openPrice = 1.167,
            highPrice = 1.167,
            lowPrice = 1.164,
            closePrice = 1.164,
            volume = 0.0,
            timestamp = 1757210400000
        ),
        Bar5m(
            lastPrice = 1.165,
            openPrice = 1.164,
            highPrice = 1.166,
            lowPrice = 1.163,
            closePrice = 1.165,
            volume = 0.0,
            timestamp = 1757210700000
        ),
        Bar5m(
            lastPrice = 1.165,
            openPrice = 1.165,
            highPrice = 1.166,
            lowPrice = 1.164,
            closePrice = 1.165,
            volume = 0.0,
            timestamp = 1757211000000
        ),
        Bar5m(
            lastPrice = 1.167,
            openPrice = 1.165,
            highPrice = 1.167,
            lowPrice = 1.165,
            closePrice = 1.167,
            volume = 0.0,
            timestamp = 1757211300000
        ),
        Bar5m(
            lastPrice = 1.166,
            openPrice = 1.167,
            highPrice = 1.168,
            lowPrice = 1.166,
            closePrice = 1.166,
            volume = 0.0,
            timestamp = 1757211600000
        ),
        Bar5m(
            lastPrice = 1.167,
            openPrice = 1.166,
            highPrice = 1.167,
            lowPrice = 1.164,
            closePrice = 1.167,
            volume = 0.0,
            timestamp = 1757211900000
        ),
        Bar5m(
            lastPrice = 1.166,
            openPrice = 1.167,
            highPrice = 1.167,
            lowPrice = 1.165,
            closePrice = 1.166,
            volume = 0.0,
            timestamp = 1757212200000
        ),
        Bar5m(
            lastPrice = 1.169,
            openPrice = 1.166,
            highPrice = 1.17,
            lowPrice = 1.166,
            closePrice = 1.169,
            volume = 0.0,
            timestamp = 1757212500000
        ),
        Bar5m(
            lastPrice = 1.167,
            openPrice = 1.169,
            highPrice = 1.171,
            lowPrice = 1.166,
            closePrice = 1.167,
            volume = 0.0,
            timestamp = 1757212800000
        ),
        Bar5m(
            lastPrice = 1.168,
            openPrice = 1.167,
            highPrice = 1.169,
            lowPrice = 1.166,
            closePrice = 1.168,
            volume = 0.0,
            timestamp = 1757213100000
        ),
        Bar5m(
            lastPrice = 1.168,
            openPrice = 1.168,
            highPrice = 1.169,
            lowPrice = 1.166,
            closePrice = 1.168,
            volume = 0.0,
            timestamp = 1757213400000
        ),
        Bar5m(
            lastPrice = 1.167,
            openPrice = 1.168,
            highPrice = 1.169,
            lowPrice = 1.166,
            closePrice = 1.167,
            volume = 0.0,
            timestamp = 1757213700000
        ),
        Bar5m(
            lastPrice = 1.166,
            openPrice = 1.167,
            highPrice = 1.169,
            lowPrice = 1.166,
            closePrice = 1.166,
            volume = 0.0,
            timestamp = 1757214000000
        ),
        Bar5m(
            lastPrice = 1.166,
            openPrice = 1.166,
            highPrice = 1.167,
            lowPrice = 1.164,
            closePrice = 1.166,
            volume = 0.0,
            timestamp = 1757214300000
        ),
        Bar5m(
            lastPrice = 1.167,
            openPrice = 1.166,
            highPrice = 1.168,
            lowPrice = 1.166,
            closePrice = 1.167,
            volume = 0.0,
            timestamp = 1757214600000
        ),
        Bar5m(
            lastPrice = 1.171,
            openPrice = 1.167,
            highPrice = 1.171,
            lowPrice = 1.166,
            closePrice = 1.171,
            volume = 0.0,
            timestamp = 1757214900000
        ),
        Bar5m(
            lastPrice = 1.17,
            openPrice = 1.171,
            highPrice = 1.172,
            lowPrice = 1.17,
            closePrice = 1.17,
            volume = 0.0,
            timestamp = 1757215200000
        ),
        Bar5m(
            lastPrice = 1.17,
            openPrice = 1.17,
            highPrice = 1.171,
            lowPrice = 1.167,
            closePrice = 1.17,
            volume = 0.0,
            timestamp = 1757215500000
        ),
        Bar5m(
            lastPrice = 1.167,
            openPrice = 1.17,
            highPrice = 1.171,
            lowPrice = 1.167,
            closePrice = 1.167,
            volume = 0.0,
            timestamp = 1757215800000
        ),
        Bar5m(
            lastPrice = 1.166,
            openPrice = 1.167,
            highPrice = 1.167,
            lowPrice = 1.164,
            closePrice = 1.166,
            volume = 0.0,
            timestamp = 1757216100000
        ),
        Bar5m(
            lastPrice = 1.164,
            openPrice = 1.166,
            highPrice = 1.166,
            lowPrice = 1.164,
            closePrice = 1.164,
            volume = 0.0,
            timestamp = 1757216400000
        ),
        Bar5m(
            lastPrice = 1.165,
            openPrice = 1.164,
            highPrice = 1.167,
            lowPrice = 1.164,
            closePrice = 1.165,
            volume = 0.0,
            timestamp = 1757216700000
        ),
        Bar5m(
            lastPrice = 1.165,
            openPrice = 1.165,
            highPrice = 1.166,
            lowPrice = 1.164,
            closePrice = 1.165,
            volume = 0.0,
            timestamp = 1757217000000
        ),
        Bar5m(
            lastPrice = 1.165,
            openPrice = 1.165,
            highPrice = 1.167,
            lowPrice = 1.164,
            closePrice = 1.165,
            volume = 0.0,
            timestamp = 1757217300000
        ),
        Bar5m(
            lastPrice = 1.164,
            openPrice = 1.165,
            highPrice = 1.166,
            lowPrice = 1.164,
            closePrice = 1.164,
            volume = 0.0,
            timestamp = 1757217600000
        ),
        Bar5m(
            lastPrice = 1.166,
            openPrice = 1.164,
            highPrice = 1.167,
            lowPrice = 1.164,
            closePrice = 1.166,
            volume = 0.0,
            timestamp = 1757217900000
        ),
        Bar5m(
            lastPrice = 1.166,
            openPrice = 1.166,
            highPrice = 1.169,
            lowPrice = 1.166,
            closePrice = 1.166,
            volume = 0.0,
            timestamp = 1757218200000
        ),
        Bar5m(
            lastPrice = 1.168,
            openPrice = 1.166,
            highPrice = 1.171,
            lowPrice = 1.166,
            closePrice = 1.168,
            volume = 103.74,
            timestamp = 1757218500000
        ),
        Bar5m(
            lastPrice = 1.166,
            openPrice = 1.168,
            highPrice = 1.169,
            lowPrice = 1.166,
            closePrice = 1.166,
            volume = 0.0,
            timestamp = 1757218800000
        ),
        Bar5m(
            lastPrice = 1.166,
            openPrice = 1.166,
            highPrice = 1.168,
            lowPrice = 1.165,
            closePrice = 1.166,
            volume = 0.0,
            timestamp = 1757219100000
        ),
        Bar5m(
            lastPrice = 1.166,
            openPrice = 1.166,
            highPrice = 1.167,
            lowPrice = 1.164,
            closePrice = 1.166,
            volume = 0.0,
            timestamp = 1757219400000
        ),
        Bar5m(
            lastPrice = 1.166,
            openPrice = 1.166,
            highPrice = 1.168,
            lowPrice = 1.164,
            closePrice = 1.166,
            volume = 0.0,
            timestamp = 1757219700000
        ),
        Bar5m(
            lastPrice = 1.166,
            openPrice = 1.166,
            highPrice = 1.166,
            lowPrice = 1.165,
            closePrice = 1.166,
            volume = 0.0,
            timestamp = 1757220000000
        ),
        Bar5m(
            lastPrice = 1.169,
            openPrice = 1.166,
            highPrice = 1.169,
            lowPrice = 1.166,
            closePrice = 1.169,
            volume = 0.0,
            timestamp = 1757220300000
        ),
        Bar5m(
            lastPrice = 1.17,
            openPrice = 1.169,
            highPrice = 1.17,
            lowPrice = 1.169,
            closePrice = 1.17,
            volume = 0.0,
            timestamp = 1757220600000
        ),
        Bar5m(
            lastPrice = 1.169,
            openPrice = 1.17,
            highPrice = 1.171,
            lowPrice = 1.169,
            closePrice = 1.169,
            volume = 0.0,
            timestamp = 1757220900000
        ),
        Bar5m(
            lastPrice = 1.17,
            openPrice = 1.169,
            highPrice = 1.17,
            lowPrice = 1.167,
            closePrice = 1.17,
            volume = 0.0,
            timestamp = 1757221200000
        ),
        Bar5m(
            lastPrice = 1.168,
            openPrice = 1.17,
            highPrice = 1.17,
            lowPrice = 1.168,
            closePrice = 1.168,
            volume = 0.0,
            timestamp = 1757221500000
        ),
        Bar5m(
            lastPrice = 1.169,
            openPrice = 1.168,
            highPrice = 1.169,
            lowPrice = 1.167,
            closePrice = 1.169,
            volume = 0.0,
            timestamp = 1757221800000
        ),
        Bar5m(
            lastPrice = 1.168,
            openPrice = 1.169,
            highPrice = 1.169,
            lowPrice = 1.168,
            closePrice = 1.168,
            volume = 0.0,
            timestamp = 1757222100000
        ),
        Bar5m(
            lastPrice = 1.17,
            openPrice = 1.168,
            highPrice = 1.17,
            lowPrice = 1.168,
            closePrice = 1.17,
            volume = 0.0,
            timestamp = 1757222400000
        ),
        Bar5m(
            lastPrice = 1.171,
            openPrice = 1.17,
            highPrice = 1.171,
            lowPrice = 1.169,
            closePrice = 1.171,
            volume = 0.0,
            timestamp = 1757222700000
        ),
        Bar5m(
            lastPrice = 1.172,
            openPrice = 1.171,
            highPrice = 1.172,
            lowPrice = 1.17,
            closePrice = 1.172,
            volume = 0.0,
            timestamp = 1757223000000
        ),
        Bar5m(
            lastPrice = 1.171,
            openPrice = 1.172,
            highPrice = 1.173,
            lowPrice = 1.171,
            closePrice = 1.171,
            volume = 0.0,
            timestamp = 1757223300000
        ),
        Bar5m(
            lastPrice = 1.172,
            openPrice = 1.171,
            highPrice = 1.173,
            lowPrice = 1.171,
            closePrice = 1.172,
            volume = 0.0,
            timestamp = 1757223600000
        ),
        Bar5m(
            lastPrice = 1.175,
            openPrice = 1.172,
            highPrice = 1.175,
            lowPrice = 1.172,
            closePrice = 1.175,
            volume = 0.0,
            timestamp = 1757223900000
        ),
        Bar5m(
            lastPrice = 1.175,
            openPrice = 1.175,
            highPrice = 1.177,
            lowPrice = 1.175,
            closePrice = 1.175,
            volume = 0.0,
            timestamp = 1757224200000
        ),
        Bar5m(
            lastPrice = 1.175,
            openPrice = 1.175,
            highPrice = 1.176,
            lowPrice = 1.174,
            closePrice = 1.175,
            volume = 0.0,
            timestamp = 1757224500000
        ),
        Bar5m(
            lastPrice = 1.179,
            openPrice = 1.175,
            highPrice = 1.18,
            lowPrice = 1.175,
            closePrice = 1.179,
            volume = 0.0,
            timestamp = 1757224800000
        ),
        Bar5m(
            lastPrice = 1.178,
            openPrice = 1.179,
            highPrice = 1.18,
            lowPrice = 1.176,
            closePrice = 1.178,
            volume = 0.0,
            timestamp = 1757225100000
        ),
        Bar5m(
            lastPrice = 1.171,
            openPrice = 1.178,
            highPrice = 1.179,
            lowPrice = 1.171,
            closePrice = 1.171,
            volume = 0.0,
            timestamp = 1757225400000
        ),
        Bar5m(
            lastPrice = 1.172,
            openPrice = 1.171,
            highPrice = 1.172,
            lowPrice = 1.17,
            closePrice = 1.172,
            volume = 0.0,
            timestamp = 1757225700000
        ),
        Bar5m(
            lastPrice = 1.171,
            openPrice = 1.172,
            highPrice = 1.173,
            lowPrice = 1.17,
            closePrice = 1.171,
            volume = 0.0,
            timestamp = 1757226000000
        ),
        Bar5m(
            lastPrice = 1.17,
            openPrice = 1.171,
            highPrice = 1.172,
            lowPrice = 1.17,
            closePrice = 1.17,
            volume = 0.0,
            timestamp = 1757226300000
        ),
        Bar5m(
            lastPrice = 1.17,
            openPrice = 1.17,
            highPrice = 1.173,
            lowPrice = 1.17,
            closePrice = 1.17,
            volume = 0.0,
            timestamp = 1757226600000
        ),
        Bar5m(
            lastPrice = 1.17,
            openPrice = 1.17,
            highPrice = 1.172,
            lowPrice = 1.169,
            closePrice = 1.17,
            volume = 0.0,
            timestamp = 1757226900000
        ),
        Bar5m(
            lastPrice = 1.171,
            openPrice = 1.17,
            highPrice = 1.172,
            lowPrice = 1.168,
            closePrice = 1.171,
            volume = 0.0,
            timestamp = 1757227200000
        ),
        Bar5m(
            lastPrice = 1.17,
            openPrice = 1.171,
            highPrice = 1.173,
            lowPrice = 1.169,
            closePrice = 1.17,
            volume = 0.0,
            timestamp = 1757227500000
        ),
        Bar5m(
            lastPrice = 1.17,
            openPrice = 1.17,
            highPrice = 1.172,
            lowPrice = 1.169,
            closePrice = 1.17,
            volume = 0.0,
            timestamp = 1757227800000
        ),
        Bar5m(
            lastPrice = 1.169,
            openPrice = 1.17,
            highPrice = 1.17,
            lowPrice = 1.168,
            closePrice = 1.169,
            volume = 0.0,
            timestamp = 1757228100000
        ),
        Bar5m(
            lastPrice = 1.168,
            openPrice = 1.169,
            highPrice = 1.171,
            lowPrice = 1.168,
            closePrice = 1.168,
            volume = 0.0,
            timestamp = 1757228400000
        ),
        Bar5m(
            lastPrice = 1.169,
            openPrice = 1.168,
            highPrice = 1.17,
            lowPrice = 1.167,
            closePrice = 1.169,
            volume = 0.0,
            timestamp = 1757228700000
        ),
        Bar5m(
            lastPrice = 1.168,
            openPrice = 1.169,
            highPrice = 1.17,
            lowPrice = 1.167,
            closePrice = 1.168,
            volume = 0.0,
            timestamp = 1757229000000
        ),
        Bar5m(
            lastPrice = 1.167,
            openPrice = 1.168,
            highPrice = 1.171,
            lowPrice = 1.167,
            closePrice = 1.167,
            volume = 0.0,
            timestamp = 1757229300000
        ),
        Bar5m(
            lastPrice = 1.17,
            openPrice = 1.167,
            highPrice = 1.17,
            lowPrice = 1.167,
            closePrice = 1.17,
            volume = 0.0,
            timestamp = 1757229600000
        ),
        Bar5m(
            lastPrice = 1.174,
            openPrice = 1.17,
            highPrice = 1.175,
            lowPrice = 1.169,
            closePrice = 1.174,
            volume = 0.0,
            timestamp = 1757229900000
        ),
        Bar5m(
            lastPrice = 1.174,
            openPrice = 1.174,
            highPrice = 1.178,
            lowPrice = 1.174,
            closePrice = 1.174,
            volume = 0.0,
            timestamp = 1757230200000
        ),
        Bar5m(
            lastPrice = 1.176,
            openPrice = 1.174,
            highPrice = 1.177,
            lowPrice = 1.173,
            closePrice = 1.176,
            volume = 0.0,
            timestamp = 1757230500000
        ),
        Bar5m(
            lastPrice = 1.177,
            openPrice = 1.176,
            highPrice = 1.178,
            lowPrice = 1.175,
            closePrice = 1.177,
            volume = 0.0,
            timestamp = 1757230800000
        ),
        Bar5m(
            lastPrice = 1.18,
            openPrice = 1.177,
            highPrice = 1.183,
            lowPrice = 1.177,
            closePrice = 1.18,
            volume = 0.0,
            timestamp = 1757231100000
        ),
        Bar5m(
            lastPrice = 1.178,
            openPrice = 1.18,
            highPrice = 1.182,
            lowPrice = 1.178,
            closePrice = 1.178,
            volume = 0.0,
            timestamp = 1757231400000
        ),
        Bar5m(
            lastPrice = 1.178,
            openPrice = 1.178,
            highPrice = 1.178,
            lowPrice = 1.177,
            closePrice = 1.178,
            volume = 0.0,
            timestamp = 1757231700000
        ),
        Bar5m(
            lastPrice = 1.177,
            openPrice = 1.178,
            highPrice = 1.179,
            lowPrice = 1.174,
            closePrice = 1.177,
            volume = 0.0,
            timestamp = 1757232000000
        ),
        Bar5m(
            lastPrice = 1.177,
            openPrice = 1.177,
            highPrice = 1.178,
            lowPrice = 1.176,
            closePrice = 1.177,
            volume = 0.0,
            timestamp = 1757232300000
        ),
        Bar5m(
            lastPrice = 1.174,
            openPrice = 1.177,
            highPrice = 1.178,
            lowPrice = 1.174,
            closePrice = 1.174,
            volume = 0.0,
            timestamp = 1757232600000
        ),
        Bar5m(
            lastPrice = 1.175,
            openPrice = 1.174,
            highPrice = 1.175,
            lowPrice = 1.172,
            closePrice = 1.175,
            volume = 0.0,
            timestamp = 1757232900000
        ),
        Bar5m(
            lastPrice = 1.18,
            openPrice = 1.175,
            highPrice = 1.18,
            lowPrice = 1.174,
            closePrice = 1.18,
            volume = 0.0,
            timestamp = 1757233200000
        ),
        Bar5m(
            lastPrice = 1.178,
            openPrice = 1.18,
            highPrice = 1.183,
            lowPrice = 1.177,
            closePrice = 1.178,
            volume = 0.0,
            timestamp = 1757233500000
        ),
        Bar5m(
            lastPrice = 1.177,
            openPrice = 1.178,
            highPrice = 1.181,
            lowPrice = 1.177,
            closePrice = 1.177,
            volume = 0.0,
            timestamp = 1757233800000
        ),
        Bar5m(
            lastPrice = 1.178,
            openPrice = 1.177,
            highPrice = 1.178,
            lowPrice = 1.177,
            closePrice = 1.178,
            volume = 0.0,
            timestamp = 1757234100000
        ),
        Bar5m(
            lastPrice = 1.178,
            openPrice = 1.178,
            highPrice = 1.181,
            lowPrice = 1.177,
            closePrice = 1.178,
            volume = 0.0,
            timestamp = 1757234400000
        ),
        Bar5m(
            lastPrice = 1.177,
            openPrice = 1.178,
            highPrice = 1.178,
            lowPrice = 1.177,
            closePrice = 1.177,
            volume = 0.0,
            timestamp = 1757234700000
        ),
        Bar5m(
            lastPrice = 1.181,
            openPrice = 1.177,
            highPrice = 1.182,
            lowPrice = 1.177,
            closePrice = 1.181,
            volume = 0.0,
            timestamp = 1757235000000
        ),
        Bar5m(
            lastPrice = 1.18,
            openPrice = 1.181,
            highPrice = 1.181,
            lowPrice = 1.18,
            closePrice = 1.18,
            volume = 0.0,
            timestamp = 1757235300000
        ),
        Bar5m(
            lastPrice = 1.177,
            openPrice = 1.18,
            highPrice = 1.18,
            lowPrice = 1.177,
            closePrice = 1.177,
            volume = 0.0,
            timestamp = 1757235600000
        ),
        Bar5m(
            lastPrice = 1.178,
            openPrice = 1.177,
            highPrice = 1.181,
            lowPrice = 1.177,
            closePrice = 1.178,
            volume = 0.0,
            timestamp = 1757235900000
        ),
        Bar5m(
            lastPrice = 1.176,
            openPrice = 1.178,
            highPrice = 1.18,
            lowPrice = 1.175,
            closePrice = 1.176,
            volume = 0.0,
            timestamp = 1757236200000
        ),
        Bar5m(
            lastPrice = 1.177,
            openPrice = 1.176,
            highPrice = 1.178,
            lowPrice = 1.174,
            closePrice = 1.177,
            volume = 0.0,
            timestamp = 1757236500000
        ),
        Bar5m(
            lastPrice = 1.179,
            openPrice = 1.177,
            highPrice = 1.18,
            lowPrice = 1.177,
            closePrice = 1.179,
            volume = 0.0,
            timestamp = 1757236800000
        ),
        Bar5m(
            lastPrice = 1.175,
            openPrice = 1.179,
            highPrice = 1.179,
            lowPrice = 1.174,
            closePrice = 1.175,
            volume = 0.0,
            timestamp = 1757237100000
        ),
        Bar5m(
            lastPrice = 1.172,
            openPrice = 1.175,
            highPrice = 1.176,
            lowPrice = 1.171,
            closePrice = 1.172,
            volume = 0.0,
            timestamp = 1757237400000
        ),
        Bar5m(
            lastPrice = 1.168,
            openPrice = 1.17,
            highPrice = 1.171,
            lowPrice = 1.168,
            closePrice = 1.168,
            volume = 0.0,
            timestamp = 1757238000000
        ),
        Bar5m(
            lastPrice = 1.17,
            openPrice = 1.168,
            highPrice = 1.171,
            lowPrice = 1.167,
            closePrice = 1.17,
            volume = 0.0,
            timestamp = 1757238300000
        ),
        Bar5m(
            lastPrice = 1.172,
            openPrice = 1.17,
            highPrice = 1.174,
            lowPrice = 1.17,
            closePrice = 1.172,
            volume = 0.0,
            timestamp = 1757238600000
        ),
        Bar5m(
            lastPrice = 1.175,
            openPrice = 1.172,
            highPrice = 1.175,
            lowPrice = 1.172,
            closePrice = 1.175,
            volume = 0.0,
            timestamp = 1757238900000
        ),
        Bar5m(
            lastPrice = 1.177,
            openPrice = 1.175,
            highPrice = 1.179,
            lowPrice = 1.175,
            closePrice = 1.177,
            volume = 0.0,
            timestamp = 1757239200000
        ),
        Bar5m(
            lastPrice = 1.176,
            openPrice = 1.177,
            highPrice = 1.177,
            lowPrice = 1.175,
            closePrice = 1.176,
            volume = 0.0,
            timestamp = 1757239500000
        ),
        Bar5m(
            lastPrice = 1.177,
            openPrice = 1.176,
            highPrice = 1.179,
            lowPrice = 1.176,
            closePrice = 1.177,
            volume = 0.0,
            timestamp = 1757239800000
        ),
        Bar5m(
            lastPrice = 1.176,
            openPrice = 1.177,
            highPrice = 1.178,
            lowPrice = 1.175,
            closePrice = 1.176,
            volume = 0.0,
            timestamp = 1757240100000
        ),
        Bar5m(
            lastPrice = 1.176,
            openPrice = 1.176,
            highPrice = 1.177,
            lowPrice = 1.175,
            closePrice = 1.176,
            volume = 0.0,
            timestamp = 1757240400000
        ),
        Bar5m(
            lastPrice = 1.176,
            openPrice = 1.176,
            highPrice = 1.177,
            lowPrice = 1.174,
            closePrice = 1.176,
            volume = 0.0,
            timestamp = 1757240700000
        ),
        Bar5m(
            lastPrice = 1.176,
            openPrice = 1.176,
            highPrice = 1.177,
            lowPrice = 1.175,
            closePrice = 1.176,
            volume = 0.0,
            timestamp = 1757241000000
        ),
        Bar5m(
            lastPrice = 1.177,
            openPrice = 1.176,
            highPrice = 1.178,
            lowPrice = 1.176,
            closePrice = 1.177,
            volume = 0.0,
            timestamp = 1757241300000
        ),
        Bar5m(
            lastPrice = 1.175,
            openPrice = 1.177,
            highPrice = 1.178,
            lowPrice = 1.174,
            closePrice = 1.175,
            volume = 0.0,
            timestamp = 1757241600000
        ),
        Bar5m(
            lastPrice = 1.174,
            openPrice = 1.175,
            highPrice = 1.175,
            lowPrice = 1.173,
            closePrice = 1.174,
            volume = 0.0,
            timestamp = 1757241900000
        ),
        Bar5m(
            lastPrice = 1.174,
            openPrice = 1.174,
            highPrice = 1.177,
            lowPrice = 1.174,
            closePrice = 1.174,
            volume = 0.0,
            timestamp = 1757242200000
        ),
        Bar5m(
            lastPrice = 1.175,
            openPrice = 1.174,
            highPrice = 1.176,
            lowPrice = 1.174,
            closePrice = 1.175,
            volume = 0.0,
            timestamp = 1757242500000
        ),
        Bar5m(
            lastPrice = 1.174,
            openPrice = 1.175,
            highPrice = 1.176,
            lowPrice = 1.173,
            closePrice = 1.174,
            volume = 0.0,
            timestamp = 1757242800000
        ),
        Bar5m(
            lastPrice = 1.174,
            openPrice = 1.174,
            highPrice = 1.175,
            lowPrice = 1.173,
            closePrice = 1.174,
            volume = 0.0,
            timestamp = 1757243100000
        ),
        Bar5m(
            lastPrice = 1.173,
            openPrice = 1.174,
            highPrice = 1.175,
            lowPrice = 1.173,
            closePrice = 1.173,
            volume = 0.0,
            timestamp = 1757243400000
        ),
        Bar5m(
            lastPrice = 1.173,
            openPrice = 1.173,
            highPrice = 1.174,
            lowPrice = 1.173,
            closePrice = 1.173,
            volume = 0.0,
            timestamp = 1757243700000
        ),
        Bar5m(
            lastPrice = 1.173,
            openPrice = 1.173,
            highPrice = 1.175,
            lowPrice = 1.173,
            closePrice = 1.173,
            volume = 0.0,
            timestamp = 1757244000000
        ),
        Bar5m(
            lastPrice = 1.174,
            openPrice = 1.173,
            highPrice = 1.176,
            lowPrice = 1.173,
            closePrice = 1.174,
            volume = 0.0,
            timestamp = 1757244300000
        ),
        Bar5m(
            lastPrice = 1.174,
            openPrice = 1.174,
            highPrice = 1.176,
            lowPrice = 1.173,
            closePrice = 1.174,
            volume = 0.0,
            timestamp = 1757244600000
        ),
        Bar5m(
            lastPrice = 1.174,
            openPrice = 1.174,
            highPrice = 1.176,
            lowPrice = 1.173,
            closePrice = 1.174,
            volume = 0.0,
            timestamp = 1757244900000
        ),
        Bar5m(
            lastPrice = 1.175,
            openPrice = 1.174,
            highPrice = 1.177,
            lowPrice = 1.174,
            closePrice = 1.175,
            volume = 0.0,
            timestamp = 1757245200000
        ),
        Bar5m(
            lastPrice = 1.175,
            openPrice = 1.175,
            highPrice = 1.175,
            lowPrice = 1.173,
            closePrice = 1.175,
            volume = 0.0,
            timestamp = 1757245500000
        ),
        Bar5m(
            lastPrice = 1.176,
            openPrice = 1.175,
            highPrice = 1.177,
            lowPrice = 1.174,
            closePrice = 1.176,
            volume = 0.0,
            timestamp = 1757245800000
        ),
        Bar5m(
            lastPrice = 1.176,
            openPrice = 1.176,
            highPrice = 1.177,
            lowPrice = 1.175,
            closePrice = 1.176,
            volume = 0.0,
            timestamp = 1757246100000
        ),
        Bar5m(
            lastPrice = 1.174,
            openPrice = 1.176,
            highPrice = 1.177,
            lowPrice = 1.174,
            closePrice = 1.174,
            volume = 0.0,
            timestamp = 1757246400000
        ),
        Bar5m(
            lastPrice = 1.172,
            openPrice = 1.174,
            highPrice = 1.174,
            lowPrice = 1.171,
            closePrice = 1.172,
            volume = 0.0,
            timestamp = 1757246700000
        ),
        Bar5m(
            lastPrice = 1.17,
            openPrice = 1.172,
            highPrice = 1.173,
            lowPrice = 1.169,
            closePrice = 1.17,
            volume = 0.0,
            timestamp = 1757247000000
        ),
        Bar5m(
            lastPrice = 1.169,
            openPrice = 1.17,
            highPrice = 1.171,
            lowPrice = 1.169,
            closePrice = 1.169,
            volume = 0.0,
            timestamp = 1757247300000
        ),
        Bar5m(
            lastPrice = 1.172,
            openPrice = 1.169,
            highPrice = 1.173,
            lowPrice = 1.169,
            closePrice = 1.172,
            volume = 0.0,
            timestamp = 1757247600000
        ),
        Bar5m(
            lastPrice = 1.173,
            openPrice = 1.172,
            highPrice = 1.174,
            lowPrice = 1.171,
            closePrice = 1.173,
            volume = 0.0,
            timestamp = 1757247900000
        ),
        Bar5m(
            lastPrice = 1.171,
            openPrice = 1.173,
            highPrice = 1.174,
            lowPrice = 1.17,
            closePrice = 1.171,
            volume = 0.0,
            timestamp = 1757248200000
        ),
        Bar5m(
            lastPrice = 1.174,
            openPrice = 1.171,
            highPrice = 1.174,
            lowPrice = 1.171,
            closePrice = 1.174,
            volume = 0.0,
            timestamp = 1757248500000
        ),
        Bar5m(
            lastPrice = 1.172,
            openPrice = 1.174,
            highPrice = 1.175,
            lowPrice = 1.171,
            closePrice = 1.172,
            volume = 0.0,
            timestamp = 1757248800000
        ),
        Bar5m(
            lastPrice = 1.172,
            openPrice = 1.172,
            highPrice = 1.173,
            lowPrice = 1.171,
            closePrice = 1.172,
            volume = 0.0,
            timestamp = 1757249100000
        ),
        Bar5m(
            lastPrice = 1.174,
            openPrice = 1.172,
            highPrice = 1.175,
            lowPrice = 1.172,
            closePrice = 1.174,
            volume = 0.0,
            timestamp = 1757249400000
        ),
        Bar5m(
            lastPrice = 1.175,
            openPrice = 1.174,
            highPrice = 1.176,
            lowPrice = 1.174,
            closePrice = 1.175,
            volume = 0.0,
            timestamp = 1757249700000
        ),
        Bar5m(
            lastPrice = 1.176,
            openPrice = 1.175,
            highPrice = 1.176,
            lowPrice = 1.174,
            closePrice = 1.176,
            volume = 0.0,
            timestamp = 1757250000000
        ),
        Bar5m(
            lastPrice = 1.176,
            openPrice = 1.176,
            highPrice = 1.176,
            lowPrice = 1.175,
            closePrice = 1.176,
            volume = 0.0,
            timestamp = 1757250300000
        ),
        Bar5m(
            lastPrice = 1.176,
            openPrice = 1.176,
            highPrice = 1.177,
            lowPrice = 1.176,
            closePrice = 1.176,
            volume = 0.0,
            timestamp = 1757250600000
        ),
        Bar5m(
            lastPrice = 1.174,
            openPrice = 1.176,
            highPrice = 1.176,
            lowPrice = 1.173,
            closePrice = 1.174,
            volume = 0.0,
            timestamp = 1757250900000
        ),
        Bar5m(
            lastPrice = 1.175,
            openPrice = 1.174,
            highPrice = 1.175,
            lowPrice = 1.173,
            closePrice = 1.175,
            volume = 0.0,
            timestamp = 1757251200000
        ),
        Bar5m(
            lastPrice = 1.176,
            openPrice = 1.175,
            highPrice = 1.177,
            lowPrice = 1.175,
            closePrice = 1.176,
            volume = 0.0,
            timestamp = 1757251500000
        ),
        Bar5m(
            lastPrice = 1.179,
            openPrice = 1.176,
            highPrice = 1.18,
            lowPrice = 1.175,
            closePrice = 1.179,
            volume = 0.0,
            timestamp = 1757251800000
        ),
        Bar5m(
            lastPrice = 1.179,
            openPrice = 1.179,
            highPrice = 1.181,
            lowPrice = 1.178,
            closePrice = 1.179,
            volume = 0.0,
            timestamp = 1757252100000
        ),
        Bar5m(
            lastPrice = 1.177,
            openPrice = 1.179,
            highPrice = 1.18,
            lowPrice = 1.176,
            closePrice = 1.177,
            volume = 0.0,
            timestamp = 1757252400000
        ),
        Bar5m(
            lastPrice = 1.18,
            openPrice = 1.177,
            highPrice = 1.18,
            lowPrice = 1.177,
            closePrice = 1.18,
            volume = 0.0,
            timestamp = 1757252700000
        ),
        Bar5m(
            lastPrice = 1.176,
            openPrice = 1.18,
            highPrice = 1.181,
            lowPrice = 1.175,
            closePrice = 1.176,
            volume = 0.0,
            timestamp = 1757253000000
        ),
        Bar5m(
            lastPrice = 1.177,
            openPrice = 1.176,
            highPrice = 1.177,
            lowPrice = 1.175,
            closePrice = 1.177,
            volume = 0.0,
            timestamp = 1757253300000
        ),
        Bar5m(
            lastPrice = 1.175,
            openPrice = 1.177,
            highPrice = 1.177,
            lowPrice = 1.174,
            closePrice = 1.175,
            volume = 0.0,
            timestamp = 1757253600000
        ),
        Bar5m(
            lastPrice = 1.177,
            openPrice = 1.175,
            highPrice = 1.178,
            lowPrice = 1.173,
            closePrice = 1.177,
            volume = 0.0,
            timestamp = 1757253900000
        ),
        Bar5m(
            lastPrice = 1.173,
            openPrice = 1.177,
            highPrice = 1.177,
            lowPrice = 1.171,
            closePrice = 1.173,
            volume = 0.0,
            timestamp = 1757254200000
        ),
        Bar5m(
            lastPrice = 1.172,
            openPrice = 1.173,
            highPrice = 1.176,
            lowPrice = 1.172,
            closePrice = 1.172,
            volume = 0.0,
            timestamp = 1757254500000
        ),
        Bar5m(
            lastPrice = 1.175,
            openPrice = 1.172,
            highPrice = 1.176,
            lowPrice = 1.172,
            closePrice = 1.175,
            volume = 0.0,
            timestamp = 1757254800000
        ),
        Bar5m(
            lastPrice = 1.172,
            openPrice = 1.175,
            highPrice = 1.175,
            lowPrice = 1.172,
            closePrice = 1.172,
            volume = 0.0,
            timestamp = 1757255100000
        ),
    )

    @Test
    fun `default tickers`() {
        val predict = Predict()

        println(predict.predictWithTa4j(values))
    }
}