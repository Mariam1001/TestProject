package am.md.keepwith.shared.di.module

import com.gsolutions.testproject.views.viewmodels.PhotosViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

    viewModel { PhotosViewModel(get()) }

}
