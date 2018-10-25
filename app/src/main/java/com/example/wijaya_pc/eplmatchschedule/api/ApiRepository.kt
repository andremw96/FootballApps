package com.example.wijaya_pc.eplmatchschedule.api

import java.net.URL

class ApiRepository {

    // function untuk membaca URL
    fun doRequest(url: String): String {
        return URL(url).readText()
    }
}