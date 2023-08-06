package kr.co.fastcampus.gateway.route

import kr.co.fastcampus.gateway.filter.ServicePrivateApiFilter
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RouteConfig(
    private val servicePrivateApiFilter: ServicePrivateApiFilter
) {
    @Bean
    fun gatewayRoutes(builder: RouteLocatorBuilder): RouteLocator {
        return builder.routes().route { spec ->
            spec.order(-1)  // 최우선
            spec.path(
                "/api/**"
            ).filters { filterSpec ->
                filterSpec.filter(servicePrivateApiFilter.apply(ServicePrivateApiFilter.Config()))
                filterSpec.rewritePath("/api(?<segment>/?.*)", "\${segment}")
            }.uri(
                "http://localhost:8088"
            )
        }
            .build()
    }
}