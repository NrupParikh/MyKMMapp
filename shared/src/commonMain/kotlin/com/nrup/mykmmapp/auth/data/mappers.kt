import com.nrup.mykmmapp.auth.data.AuthResponseData
import com.nrup.mykmmapp.auth.domain.model.AuthResultData

internal fun AuthResponseData.toAuthResultData(): AuthResultData {
    return AuthResultData(id, fullName, email, authToken, createdAt)
}