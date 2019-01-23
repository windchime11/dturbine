package com.airahm.dturbineapplist

import android.arch.lifecycle.Observer
import com.airahm.dturbineapplist.db.model.AppListData
import com.airahm.dturbineapplist.viewmodel.AppListViewModel
import com.facebook.soloader.SoLoader
import org.junit.*
import org.junit.runner.RunWith
import org.koin.standalone.StandAloneContext
import org.koin.standalone.inject
import org.koin.test.KoinTest
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28], manifest = "src/main/AndroidManifest.xml")
class AppListViewModelTest : KoinTest {

    val viewModel: AppListViewModel by inject()

    companion object {
        @BeforeClass
        @JvmStatic
        fun beforeClass() {
            SoLoader.setInTestMode()
        }
    }

    @Mock
    lateinit var appListData: Observer<List<AppListData>>

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
    }

    @After
    fun after() {
        StandAloneContext.stopKoin()
    }

    @Test
    fun testAppList() {
        val list = listOf(AppListData("json", 1L, "{}"))
        viewModel.appList?.observeForever(appListData)
        appListData.onChanged(list)
        Assert.assertNotNull(viewModel.appList)
        Assert.assertNotNull(viewModel.r)
        Mockito.verify(appListData).onChanged(list)
    }
}