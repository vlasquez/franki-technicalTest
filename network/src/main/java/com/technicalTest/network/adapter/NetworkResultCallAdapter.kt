package com.technicalTest.network.adapter

import com.technicalTest.network.adapter.NetworkResultCall
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

internal class NetworkResultCallAdapter(
    private val resultType: Type
) : CallAdapter<Type, Call<Result<Type>>> {

    override fun responseType(): Type = resultType

    override fun adapt(call: Call<Type>): Call<Result<Type>> {
        return NetworkResultCall(call)
    }
}