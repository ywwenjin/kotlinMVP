package cn.nekocode.kotgo.sample.data.service.Api

import cn.nekocode.kotgo.sample.data.DO.Meizi
import cn.nekocode.kotgo.sample.data.service.GankService
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author nekocode (nekocode.cn@gmail.com)
 */
internal interface Gank {
    companion object {
        val API: Gank = GankService.REST_ADAPTER.create(Gank::class.java)
    }

    @GET("{count}/{pageNum}")
    fun getMeizi(@Path("count") count: Int, @Path("pageNum") pageNum: Int)
            : Observable<GankService.ResponseWrapper<Meizi>>
}