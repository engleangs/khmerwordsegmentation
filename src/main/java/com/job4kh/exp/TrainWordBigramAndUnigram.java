package com.job4kh.exp;

import com.job4kh.corpus.TextTrainingBuilder;
import com.job4kh.crawler.SiteContent;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class TrainWordBigramAndUnigram {
    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(20);
        List<SiteContent> siteContents = SiteDaoHelper.getSiteContents();

        CountDownLatch countDownLatch = new CountDownLatch(siteContents.size());
        TextTrainingBuilder textTrainingBuilder = TextTrainingBuilder.getInstace();
        for (SiteContent siteContent : siteContents) {
            threadPoolExecutor.execute(() -> {

                System.out.println("train : " + siteContent.getUrl());
                textTrainingBuilder.train(siteContent.getContent());
                countDownLatch.countDown();
                System.out.println("remaining :" + countDownLatch.getCount());
            });
        }

    }
}
