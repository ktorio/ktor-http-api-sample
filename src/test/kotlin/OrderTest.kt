import com.jetbrains.handson.httpapi.module
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.Test
import kotlin.test.assertEquals

class OrderRouteTests {

    @Test
    fun testGetOrder() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Get, "/order/2020-04-06-01").apply {
                assertEquals(
                    """{"number":"2020-04-06-01","contents":[{"item":"Ham Sandwich","amount":2,"price":5.5},{"item":"Water","amount":1,"price":1.5},{"item":"Beer","amount":3,"price":2.3},{"item":"Cheesecake","amount":1,"price":3.75}]}""",
                    response.content
                )
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }

    @Test
    fun testGetOrders() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Get, "/order").apply {
                assertEquals(
                    """[{"number":"2020-04-06-01","contents":[{"item":"Ham Sandwich","amount":2,"price":5.5},{"item":"Water","amount":1,"price":1.5},{"item":"Beer","amount":3,"price":2.3},{"item":"Cheesecake","amount":1,"price":3.75}]},{"number":"2020-04-03-01","contents":[{"item":"Cheeseburger","amount":1,"price":8.5},{"item":"Water","amount":2,"price":1.5},{"item":"Coke","amount":2,"price":1.76},{"item":"Ice Cream","amount":1,"price":2.35}]}]""",
                    response.content
                )
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }

    @Test
    fun testOrderTotal() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Get, "/order/2020-04-06-01/total").apply {
                assertEquals(
                    "23.15",
                    response.content
                )

            }
        }
    }

}