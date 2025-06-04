package com.example.training.ui.theme.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.training.Data.model.School
import com.example.training.Data.repositary.SchoolRepository
import kotlinx.coroutines.launch

class SchoolViewModel : ViewModel() {
    private val repository = SchoolRepository()

    private val _schools = MutableLiveData<List<School>>()
    val schools: LiveData<List<School>> = _schools

    private var currentOffset = 0          // Track how many items we've loaded so far
    private val limit = 20                 // Number of items to load per page
    private var isLoading = false          // Flag to prevent multiple parallel loads
    private var allDataLoaded = false      // Flag to stop loading if no more data

    // Public getter to check current offset if needed
    fun getCurrentOffset(): Int = currentOffset

    init {
        loadMoreSchools()                  // Load first page when ViewModel is created
    }

    fun loadMoreSchools() {
        if (isLoading || allDataLoaded) return

        isLoading = true

        viewModelScope.launch {
            val newSchools = repository.fetchSchools(limit, currentOffset)

            if (newSchools.isEmpty()) {
                allDataLoaded = true
            } else {
                val updatedList = (_schools.value ?: emptyList()) + newSchools
                _schools.postValue(updatedList)
                currentOffset += limit
            }

            isLoading = false
        }
    }

    fun resetSchools() {
        currentOffset = 0
        allDataLoaded = false
        _schools.value = emptyList()
        loadMoreSchools()
    }
}







