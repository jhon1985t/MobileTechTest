package com.jhonjto.mobiletechtest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.jhonjto.domain.CommentsItem
import com.jhonjto.mobiletechtest.ui.main.MainViewModel
import com.jhonjto.usecases.DeleteAllComments
import com.jhonjto.usecases.GetPopularComments
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    lateinit var getPopularComments: GetPopularComments

    @Mock
    lateinit var deleteAllComments: DeleteAllComments

    @Mock
    lateinit var observer: Observer<MainViewModel.UiModel>

    private lateinit var vm: MainViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        vm = MainViewModel(getPopularComments, deleteAllComments)
    }

    @Test
    fun `observing LiveData launches requestComments request`() {

        vm.model.observeForever(observer)

        verify(observer).onChanged(MainViewModel.UiModel.RequestComments)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `after start application, loading is shown`() = runTest {

        val movies = listOf(mockedComments.copy(id = 1))

        whenever(getPopularComments.invoke()).thenReturn(movies)

        vm.model.observeForever(observer)

        vm.onCallComments()

        (observer).onChanged(MainViewModel.UiModel.Loading)

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `after loading, deleteAllComments is called`() = runTest {

        whenever(deleteAllComments.invoke()).thenReturn(Unit)

        vm.model.observeForever(observer)

        vm.onDeleteAllClicked()

        MainViewModel.UiDelete(Unit)

    }
}

val mockedComments = CommentsItem(
    "this is tests",
    1,
    "test",
    1,
    false
)
