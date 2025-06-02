import retrofit2.Response
import retrofit2.http.GET

interface SchoolApiService {
    @GET("resource/s3k6-pzi2.json")
    suspend fun getSchools(): Response<List<School>>
}
