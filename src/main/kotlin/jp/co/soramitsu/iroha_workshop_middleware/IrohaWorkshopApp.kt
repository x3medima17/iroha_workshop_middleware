package jp.co.soramitsu.iroha_workshop_middleware

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class IrohaWorkshopApp

fun main(args: Array<String>) {
    runApplication<IrohaWorkshopApp>(*args) {
//        setBannerMode(Banner.Mode.OFF)
    }
}
