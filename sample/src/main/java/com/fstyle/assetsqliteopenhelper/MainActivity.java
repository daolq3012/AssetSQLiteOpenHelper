package com.fstyle.assetsqliteopenhelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.fstyle.assetsqliteopenhelper.database.entity.TestEntity;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Disposable mDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainApplication application = (MainApplication) getApplication();
        mDisposable = application.getDatabase()
                .testDao()
                .getAll()
                .subscribe(new Consumer<List<TestEntity>>() {
                    @Override
                    public void accept(List<TestEntity> testEntities) throws Exception {
                        Log.d(TAG, "size: " + testEntities.size());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, "accept: ", throwable);
                    }
                });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }
}
