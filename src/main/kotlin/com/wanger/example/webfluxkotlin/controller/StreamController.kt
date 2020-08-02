package com.wanger.example.webfluxkotlin.controller

import kotlinx.coroutines.*
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
    fun multipleElement(content:String): Flow<String>{
        val scope = CoroutineScope(Dispatchers.IO)
        val start = System.currentTimeMillis()
        val jobList = ArrayList<Deferred<String>>(8)
        val result = flow{
            for(i in 1..3){
                val temp = scope.async {
                    delay(1000L + 2000L*i)
                    "Received content: $content $i \n"
                }
                jobList.add(temp)
            }
            for(job in jobList){
                emit(job.await())
                val end = System.currentTimeMillis()
                println("Spend ${start - end} ms")
            }
        }
        return result
    }
}
