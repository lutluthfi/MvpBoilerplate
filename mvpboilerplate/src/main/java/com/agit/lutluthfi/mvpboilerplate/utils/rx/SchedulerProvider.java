package com.agit.lutluthfi.mvpboilerplate.utils.rx;

import io.reactivex.CompletableTransformer;
import io.reactivex.FlowableTransformer;
import io.reactivex.MaybeTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.SingleTransformer;

public interface SchedulerProvider {

    <T> ObservableTransformer<T, T> ioToMainObservableScheduler();

    <T> FlowableTransformer<T, T> ioToMainFlowableScheduler();

    <T> SingleTransformer<T, T> ioToMainSingleScheduler();

    <T> MaybeTransformer<T, T> ioToMainMaybeScheduler();

    CompletableTransformer ioToMainCompletableScheduler();
}
