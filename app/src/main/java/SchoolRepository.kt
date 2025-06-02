class SchoolRepository {
    suspend fun getSchools() = RetrofitInstance.api.getSchools()
}
