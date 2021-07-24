package com.fara.morty.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.fara.morty.data.model.RickMorty
import com.fara.morty.repo.RemoteRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val remoteRepo: RemoteRepo
) : ViewModel() {

    suspend fun getCharacters() = remoteRepo.getCharacters().cachedIn(viewModelScope)
}