import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SchoolViewModel : ViewModel() {
    private val repository = SchoolRepository()

    private val _schools = MutableLiveData<List<School>>()
    val schools: LiveData<List<School>> = _schools

    fun fetchSchools() {
        viewModelScope.launch {
            val response = repository.getSchools()
            if (response.isSuccessful) {
                _schools.postValue(response.body())
            }
        }
    }
}
