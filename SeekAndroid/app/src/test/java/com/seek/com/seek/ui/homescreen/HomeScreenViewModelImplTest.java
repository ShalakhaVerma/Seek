package com.seek.com.seek.ui.homescreen;

import com.seek.app.sample.model.SearchJobResult;
import com.seek.app.sample.model.SearchJobResultResponse;
import com.seek.app.sample.repository.SearchJobsRepository;
import com.seek.app.sample.ui.homescreen.HomeScreenViewModelImpl;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import androidx.annotation.NonNull;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;


public class HomeScreenViewModelImplTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @BeforeClass
    public static void setupClass() {
        RxJavaPlugins.setIoSchedulerHandler(new Function<Scheduler, Scheduler>() {
            @Override
            public Scheduler apply(Scheduler scheduler) throws Exception {
                return Schedulers.trampoline();
            }
        });

        RxAndroidPlugins.setInitMainThreadSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(Callable<Scheduler> schedulerCallable) throws Exception {
                return Schedulers.trampoline();
            }
        });
    }


    @Test
    public void test_seekJobs_success() {
        TestViewModel model = new TestViewModel();
        //mock
        mockResponse(model.searchJobsRepository, mockResult());
        model.seekJobs("Android", "Melbourne");
        //verify
        assertNotNull(model.getSearchResultObservable().getValue());
        assertEquals(mockResult().jobItems(), model.getSearchResultObservable().getValue());
    }


    @Test
    public void test_seekJobs_failure() {
        TestViewModel model = new TestViewModel();
        //mock
        mockResponse(model.searchJobsRepository, mock(SearchJobResult.class));
        model.seekJobs("Android", "Melbourne");
        //verify
        assertNotNull(model.getSearchResultObservable().getValue());
        assert (model.getSearchResultObservable().getValue()).isEmpty();
    }


    private static SearchJobResult mockResult() {

        SearchJobResultResponse.Advertiser advertiser = new SearchJobResultResponse.Advertiser();
        advertiser.advertiserId = "1";
        advertiser.description = "ANZ";

        SearchJobResultResponse.Job jobItem1 = mock(SearchJobResultResponse.Job.class);
        jobItem1.jobId = "1";
        jobItem1.title = "AAndroid Developer Jobs in All Melbourne VIC";
        jobItem1.teaser = "Dummy Data 1";
        jobItem1.listingDate = "26/03/2019";
        jobItem1.advertiser = advertiser;


        SearchJobResultResponse.Job jobItem2 = mock(SearchJobResultResponse.Job.class);
        jobItem2.jobId = "2";
        jobItem2.title = "Android Developer";
        jobItem2.teaser = "Dummy Data 2";
        jobItem2.listingDate = "27/03/2019";
        jobItem2.advertiser = advertiser;

        ArrayList<SearchJobResultResponse.Job> jobs = new ArrayList<>();
        jobs.add(jobItem1);
        jobs.add(jobItem2);


        SearchJobResultResponse resultResponse = mock(SearchJobResultResponse.class);
        resultResponse.title = "Android Developer Jobs in All Melbourne VIC";
        resultResponse.jobs = jobs;

        return SearchJobResult.create(resultResponse);
    }

    private void mockResponse(@NonNull SearchJobsRepository searchJobsRepository, @NonNull SearchJobResult result) {
        Mockito.doReturn(Single.just(result)).when(searchJobsRepository).getSearchJobResults("Android", "Melbourne", 1, 20);
    }

    private static final class TestViewModel extends HomeScreenViewModelImpl {

        private SearchJobsRepository searchJobsRepository;

        TestViewModel() {
            this(mock(SearchJobsRepository.class));
        }

        private TestViewModel(@NonNull SearchJobsRepository searchJobsRepository) {
            super(searchJobsRepository);
            this.searchJobsRepository = searchJobsRepository;
        }
    }


}
