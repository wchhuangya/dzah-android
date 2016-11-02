package com.ch.wchhuangya.dzah.android.activity.retrofit.github;

import com.ch.wchhuangya.dzah.android.model.Contributor;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * 用于请求 GitHub 网站的数据
 * Created by wchya on 16/11/1.
 */

public interface GitHubClient {

    /**
     * 获取某个仓库的贡献者列表
     * @param owner 仓库所有人
     * @param repo 仓库
     * @return
     */
    @GET("repos/{owner}/{repo}/controbutors")
    Observable<List<Contributor>> contributors(
            @Path("owner") String owner,
            @Path("repo") String repo
    );
}
