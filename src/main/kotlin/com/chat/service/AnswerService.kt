package com.chat.service

import org.jsoup.Jsoup
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.net.URLEncoder

const val HREF_A = "a[href]"
const val ABS_HREF = "abs:href"
const val GOOGLE_DOT = "google."
const val ZERO = "0"

@Component
class AnswerService {

    private val log = LoggerFactory.getLogger(AnswerService::class.java)

    fun getAnswer(query: String): String {
        return scrapeGoogle(query, ZERO).first()
    }

    private fun scrapeGoogle(query: String, start: String): List<String> {
        val encodedQuery = URLEncoder.encode(query, "UTF-8")
        val url = "https://www.google.com/search?start=$start&q=$encodedQuery"

        log.info("Query to $url")
        val doc = Jsoup.parse(getSource(url))

        return doc.select(HREF_A)
            .map { it.attr(ABS_HREF) }
            .filter { !it.contains(GOOGLE_DOT) }
            .filter { it.isNotBlank() }
    }

    private fun getSource(url: String): String {
        try {
            val doc = Jsoup.connect(url).get()
            return doc.outerHtml()
        } catch (e: Exception) {
            log.error("ERROR ", e)
            e.printStackTrace()
        }

        log.error("Return empty answer")
        return ""
    }

}
