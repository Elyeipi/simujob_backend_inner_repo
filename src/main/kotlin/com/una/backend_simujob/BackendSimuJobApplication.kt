package com.una.backend_simujob

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication

@SpringBootApplication
@EntityScan("com.una.backend_simujob.data.entity")
class BackendSimuJobApplicationApplication

fun main(args: Array<String>) {
	runApplication<BackendSimuJobApplicationApplication>(*args)
}
