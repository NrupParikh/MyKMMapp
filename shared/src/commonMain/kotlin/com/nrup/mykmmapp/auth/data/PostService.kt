import com.nrup.mykmmapp.auth.data.PostListResponse
import com.nrup.mykmmapp.common.data.KtorApi
import io.ktor.client.call.body
import io.ktor.client.request.get

internal class PostService : KtorApi() {
    // http://10.37.54.125:8080/post/getAllPost
    suspend fun getAllPosts(): PostListResponse = client.get {
        endPoint(path = "/post/getAllPost")
    }.body()
}