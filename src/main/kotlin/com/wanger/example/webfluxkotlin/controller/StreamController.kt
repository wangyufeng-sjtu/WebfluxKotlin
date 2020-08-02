package com.wanger.example.webfluxkotlin.controller

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.reactor.mono
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/stream")
class StreamController{
    @RequestMapping("/printContent",
            method = [RequestMethod.GET, RequestMethod.GET])
    fun oneElement(content:String) = mono{
        delay(1000L)
        "Received content: $content"
    }

    @RequestMapping("/printMultiContent",
            method = [RequestMethod.GET, RequestMethod.GET])
    fun multipleElement(content:String): Flow<String> {
        val result = flow{
            for(i in 1..3){
                delay(1000L*i)
                emit( "Received content: $content $i \n")
            }
        }
        return result
    }
}
