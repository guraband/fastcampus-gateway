package kr.co.fastcampus.gateway.filter

import kr.co.fastcampus.gateway.common.Log
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.stereotype.Component

@Component
class ServicePublicApiFilter : AbstractGatewayFilterFactory<ServicePublicApiFilter.Config>(Config::class.java) {
    companion object : Log

    class Config

    override fun apply(config: Config): GatewayFilter {
        return GatewayFilter { exchange, chain ->
            val uri = exchange.request.uri
            log.info("service api public filter route uri : {}", uri)

            val mono = chain.filter(exchange)
            mono
        }
    }
}