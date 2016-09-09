package zeta.android.apps.fragments.home.presenter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import zeta.android.apps.fragments.home.presentation.HomePresentation;
import zeta.android.apps.rx.UnitTestSchedulerProvider;
import zeta.apps.flickr.managers.params.flickr.FlickrImageManager;
import zeta.apps.flickr.models.common.OneOf;
import zeta.apps.flickr.models.flickr.FlickrImageModel;
import zeta.apps.flickr.models.flickr.FlickrImages;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HomePresenterTest {

    //Object under test.
    HomePresenter mPresenter;

    @Mock
    HomePresentation mPresentation;

    @Mock
    HomePresenterParam mMockHomePresenterParam;

    @Mock
    FlickrImageManager mMockFlickrImageManager;

    @Before
    public void setUp() throws Exception {
        //Unit test scheduler for testing purposes.
        final UnitTestSchedulerProvider unitTestSchedulerProvider = new UnitTestSchedulerProvider();
        mPresenter = new HomePresenter(unitTestSchedulerProvider, mMockFlickrImageManager);
        mPresenter.onCreate(mMockHomePresenterParam);
        mPresenter.onCreateView(mPresentation);
    }

    @After
    public void tearDown() throws Exception {
        verifyNoMoreInteractions(mPresentation);
    }

    @Test
    public void testOnCreateOptionsMenu() throws Exception {
        verifyZeroInteractions(mPresentation);
    }

    @Test
    public void testOnCreate() throws Exception {
        verifyZeroInteractions(mPresentation);
    }

    @Test
    public void testOnCreateWithSavedInstance() throws Exception {
        List<FlickrImages> mockFlickrImages = getMockFlickrImages();
        mockSavedInstanceInParams(mockFlickrImages);
        mPresenter.onCreate(mMockHomePresenterParam);
        verifyZeroInteractions(mPresentation);
    }

    @Test
    public void testOnCreateView() throws Exception {
        verifyZeroInteractions(mPresentation);
    }

    @Test
    public void testOnViewCreated() throws Exception {
        List<FlickrImages> mockFlickrImages = getMockFlickrImages();
        FlickrImageModel mockImageModel = getMockFlickrImageModel(mockFlickrImages);
        when(mMockFlickrImageManager.getFlickrImages()).thenReturn(Observable.just(OneOf.fromSuccess(mockImageModel)));

        //Unit test
        mPresenter.onViewCreated();

        verify(mPresentation).showProgress(eq(true));
        verify(mPresentation).updateImageAdapters(eq(mockFlickrImages), eq(0));
        verify(mPresentation).showProgress(eq(false));
        verify(mPresentation).showListView(eq(true));
        verify(mPresentation).showListViewFooter(eq(false));
    }

    @Test
    public void testOnViewCreatedWithSavedInstance() throws Exception {
        List<FlickrImages> mockFlickrImages = getMockFlickrImages();
        mockSavedInstanceInParams(mockFlickrImages);
        mPresenter.onCreate(mMockHomePresenterParam);

        //Unit test
        mPresenter.onViewCreated();

        verify(mPresentation).updateImageAdapters(eq(mockFlickrImages), eq(0));
        verify(mPresentation).showProgress(eq(false));
        verify(mPresentation).showListView(eq(true));
        verify(mPresentation).showListViewFooter(eq(false));
    }

    @Test
    public void testOnDestroyView() throws Exception {
        verifyZeroInteractions(mPresentation);
    }

    @Test
    public void testOnDestroy() throws Exception {
        verifyZeroInteractions(mPresentation);
    }

    @Test
    public void testOnPageScrolled() throws Exception {
        verifyZeroInteractions(mPresentation);
    }

    @Test
    public void testOnFooterRetryClicked() throws Exception {
        verifyZeroInteractions(mPresentation);
    }

    private void mockSavedInstanceInParams(List<FlickrImages> mockFlickrImages) {
        HomePresenter.SavedState mockedSavedInstance = mock(HomePresenter.SavedState.class);
        when(mockedSavedInstance.getFlickrImages()).thenReturn(mockFlickrImages);
        when(mockedSavedInstance.getPreviousPageSize()).thenReturn(0);
        when(mMockHomePresenterParam.getSavedState()).thenReturn(mockedSavedInstance);
    }

    private FlickrImageModel getMockFlickrImageModel(List<FlickrImages> mockFlickrImages) {
        String mockDescription = "top test description";
        String mockTitle = "top test title";
        FlickrImageModel mockFlickrImageModel = mock(FlickrImageModel.class);

        when(mockFlickrImageModel.getFlickrImages()).thenReturn(mockFlickrImages);
        when(mockFlickrImageModel.getDescription()).thenReturn(mockDescription);
        when(mockFlickrImageModel.getTitle()).thenReturn(mockTitle);
        return mockFlickrImageModel;
    }

    private List<FlickrImages> getMockFlickrImages() {
        String mockImageUrl = "http://testimageurl.jpeg";
        String mockDescription = "test description";
        String mockTitle = "test title";

        FlickrImages mockFlickerImage = mock(FlickrImages.class);

        when(mockFlickerImage.getTitle()).thenReturn(mockTitle);
        when(mockFlickerImage.getDescription()).thenReturn(mockDescription);
        when(mockFlickerImage.getImageUrl()).thenReturn(mockImageUrl);

        List<FlickrImages> mockFlickerImages = new ArrayList<>();
        mockFlickerImages.add(mockFlickerImage);
        mockFlickerImages.add(mockFlickerImage);
        mockFlickerImages.add(mockFlickerImage);
        return mockFlickerImages;
    }
}