package jp.co.soramitsu.iroha_workshop_middleware

import jp.co.soramitsu.iroha_workshop_middleware.controller.User
import jp.co.soramitsu.iroha_workshop_middleware.model.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTests {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Test
    fun `Assert writing and reading user info`() {
        val testUser = UserData(0, "testuser")
        val response = restTemplate.postForEntity<InsertionResponse>("/users", testUser)
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response).isNotNull
        assertThat(response.body).isNotNull
        assertThat(response.body?.id).isNotNull()

        val id = response.body?.id
        val entity = restTemplate.getForEntity<UserData>("/users/$id")
        assertThat(entity.body?.id).isEqualTo(0)
        assertThat(entity.body?.name).isEqualTo("testuser")

        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body?.name).isEqualTo(testUser.name)
    }

    @Test
    fun userBalanceTx() {
        val testUser = UserData(0, "testuser")
        val response = restTemplate.postForEntity<InsertionResponse>("/users", testUser)

        val resp = restTemplate.getForEntity<UserBalanceData>("/users/testuser/balance")

        //Add few more transactions and check all users balance
    }

    fun waitForIrohaTransactionCommit() {
        Thread.sleep(1)
    }

}