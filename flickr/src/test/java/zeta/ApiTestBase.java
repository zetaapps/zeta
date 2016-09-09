package zeta;

import android.support.annotation.CallSuper;

import com.google.gson.Gson;

import org.junit.Before;

import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.mock.Calls;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;
import zeta.utils.TestUtils;

public class ApiTestBase {

    protected MockRetrofit mMockRetrofit;
    private Gson mGson = new Gson();

    @CallSuper
    @Before
    public void setUpMockRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://test.com")
                .validateEagerly(true)
                .addConverterFactory(GsonConverterFactory.create(mGson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();


        // by default, 3% of the network requests would fail at random and we don't want them to fail.
        NetworkBehavior networkBehavior = NetworkBehavior.create();
        networkBehavior.setFailurePercent(0);
        // no delay
        networkBehavior.setDelay(0, TimeUnit.MILLISECONDS);

        // create a mock retrofit object
        mMockRetrofit = new MockRetrofit.Builder(retrofit)
                .networkBehavior(networkBehavior)
                .build();
    }

    public <T> Call<T> buildResponse(String fileName, Class<T> clazz) {
        return Calls.response(TestUtils.deserialize(fileName, clazz));
    }

    public <T> Call<List<T>> buildListResponse(String fileName, Class<T[]> clazz) {
        return Calls.response(TestUtils.deserializeList(fileName, clazz));
    }
}
