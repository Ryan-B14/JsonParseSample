package com.ryanb.jsonpractice.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ryanb.jsonpractice.model.data.ContactInfo
import com.ryanb.jsonpractice.model.repository.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ContactViewModel : ViewModel() {
    private val _contactInfo = MutableStateFlow<ContactInfo?>(null)
    val contactInfo: StateFlow<ContactInfo?> = _contactInfo

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _idInput = MutableStateFlow(1)
    val idInput: StateFlow<Int> = _idInput

    fun fetchUser() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val contactData = RetrofitInstance.api.getContactInfo()
                _contactInfo.value = contactData
            } catch (e: Exception) {
                _error.value = e.message ?: "Unknown error"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun fetchUserById(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val contactData = RetrofitInstance.api.getContactInfoById(id)
                _contactInfo.value = contactData
            } catch (e: Exception) {
                _error.value = e.message ?: "Unknown error"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun increaseIdInput() {
        val currentId = _idInput.value
        if (currentId < 10) {
            _idInput.value = currentId + 1
        }
    }

    fun decreaseIdInput() {
        val currentId = _idInput.value
        if (currentId > 1) {
            _idInput.value = currentId - 1
        }
    }
}
