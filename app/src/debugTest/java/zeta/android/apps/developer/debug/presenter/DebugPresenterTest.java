package zeta.android.apps.developer.debug.presenter;

import com.github.pedrovgs.lynx.LynxConfig;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import okhttp3.logging.HttpLoggingInterceptor;
import zeta.android.apps.developer.debug.presentation.DebugPresentation;
import zeta.android.apps.rx.providers.UnitTestSchedulerProvider;
import zeta.android.apps.sharedPref.DebugSharedPreferences;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DebugPresenterTest {

    //Object under test.
    DebugPresenter mPresenter;

    @Mock
    DebugPresentation mPresentation;

    @Mock
    LynxConfig mMockLynxConfig;

    @Mock
    DebugSharedPreferences mMockDebugSharedPreferences;

    @Before
    public void setUp() throws Exception {
        //Unit test scheduler for testing purposes.
        final UnitTestSchedulerProvider unitTestSchedulerProvider = new UnitTestSchedulerProvider();
        mPresenter = new DebugPresenter(unitTestSchedulerProvider, mMockDebugSharedPreferences, mMockLynxConfig);
        mPresenter.onCreateView(mPresentation);
    }

    @After
    public void tearDown() throws Exception {
        verifyNoMoreInteractions(mPresentation);
    }

    @Test
    public void testOnCreateOptionsMenu() throws Exception {
        verifyCommonInteractions();
        verifyZeroInteractions(mPresentation);
    }

    @Test
    public void testOnCreateView() throws Exception {
        //on create view gets called in every test
        verifyCommonInteractions();
    }

    @Test
    public void testOnViewCreated() throws Exception {
        //Mock
        final HttpLoggingInterceptor.Level mockHttpLevel = HttpLoggingInterceptor.Level.BODY;
        when(mMockDebugSharedPreferences.getStethoEnabled()).thenReturn(false);
        when(mMockDebugSharedPreferences.getStrictModeEnabled()).thenReturn(false);
        when(mMockDebugSharedPreferences.getTinyDancerEnabled()).thenReturn(false);
        when(mMockDebugSharedPreferences.getLeakyCanaryEnabled()).thenReturn(false);
        when(mMockDebugSharedPreferences.getHttpLoggingLevel()).thenReturn(mockHttpLevel);

        //Unit test
        mPresenter.onViewCreated();

        verifyCommonInteractions();
        verify(mPresentation).setEnableStetho(eq(false));
        verify(mPresentation).setEnableStrictMode(eq(false));
        verify(mPresentation).setEnableTinyDancer(eq(false));
        verify(mPresentation).setEnableLeakyCanary(eq(false));
        verify(mPresentation).setEnableInspectionToolsApplyButton(eq(false));

        verify(mPresentation).setLoggerSpinnerSelected(eq(mockHttpLevel.ordinal()));
        verify(mPresentation).setEnableLoggingToolsApplyButton(eq(false));
    }

    @Test
    public void testOnDestroyView() throws Exception {
        mPresenter.onDestroyView();
        verifyCommonInteractions();
    }

    @Test
    public void testOnDestroy() throws Exception {
        mPresenter.onDestroy();
        verifyCommonInteractions();
    }

    private void verifyCommonInteractions() {
        //onCreateView() gets called in every test
        final String[] loggingSpinnerValues = getLoggingSpinnerAdapter();
        verify(mPresentation).initLoggerSpinnerAdapter(eq(loggingSpinnerValues));
    }

    private String[] getLoggingSpinnerAdapter() {
        int size = HttpLoggingInterceptor.Level.values().length;
        String[] spinnerItems = new String[size];
        for (int i = 0; i < size; i++) {
            spinnerItems[i] = HttpLoggingInterceptor.Level.values()[i].name();
        }
        return spinnerItems;
    }
}