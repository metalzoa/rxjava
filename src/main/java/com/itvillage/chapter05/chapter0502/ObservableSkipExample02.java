package com.itvillage.chapter05.chapter0502;

import com.itvillage.utils.Logger;
import com.itvillage.utils.TimeUtil;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class ObservableSkipExample02 {
    public static void main(String[] args) {
        Observable.interval(300L, TimeUnit.MILLISECONDS)
                .skip(1000L, TimeUnit.MILLISECONDS)
                .subscribe(Logger::on);

        TimeUtil.sleep(3000L);
    }
}
