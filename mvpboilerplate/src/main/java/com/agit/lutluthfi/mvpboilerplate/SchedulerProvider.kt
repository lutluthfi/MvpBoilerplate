package com.agit.lutluthfi.mvpboilerplate

import io.reactivex.CompletableTransformer
import io.reactivex.FlowableTransformer
import io.reactivex.MaybeTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object SchedulerProvider {

    fun <T> ioToMainObservableScheduler(): ObservableTransformer<T, T> =
            ObservableTransformer { upstream ->
                upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            }

    fun <T> ioToMainSingleScheduler(): SingleTransformer<T, T> =
            SingleTransformer { upstream ->
                upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            }


    fun ioToMainCompletableScheduler(): CompletableTransformer =
            CompletableTransformer { upstream ->
                upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            }


    fun <T> ioToMainFlowableScheduler(): FlowableTransformer<T, T> =
            FlowableTransformer { upstream ->
                upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            }


    fun <T> ioToMainMaybeScheduler(): MaybeTransformer<T, T> =
            MaybeTransformer { upstream ->
                upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            }
}
