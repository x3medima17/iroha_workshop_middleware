package jp.co.soramitsu.iroha_workshop_middleware.controller.web

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
class IndexController {

    @RequestMapping("/", method = [(RequestMethod.GET)])
    fun showIndexPage(model: Model): String {
        return "index.html"
    }
}