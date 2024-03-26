import com.nrup.mykmmapp.auth.data.AuthResponseData
import com.nrup.mykmmapp.auth.data.PostResponseData
import com.nrup.mykmmapp.auth.domain.model.AuthResultData
import com.nrup.mykmmapp.auth.domain.model.PostResultData

internal fun AuthResponseData.toAuthResultData(): AuthResultData {
    return AuthResultData(id, fullName, email, authToken, createdAt)
}

internal fun PostResponseData.toPostResultData(): PostResultData {
    return PostResultData(id, title, imageUrl, createdAt)
}