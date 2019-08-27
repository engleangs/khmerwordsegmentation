package com.job4kh.exp;
import com.job4kh.tokenizer.task.SegmentTask;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class SegmentWebCollection
{

    private static ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);

    public SegmentWebCollection()
    {

    }

    public void execute(SegmentTask segmentTask)
    {
        threadPoolExecutor.execute(segmentTask);
    }

    public void execute(List<SegmentTask> tasks) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(tasks.size());
        for(SegmentTask task:tasks)
        {
            task.setEndController( countDownLatch);
            this.execute( task);
        }
        countDownLatch.await();
    }

    public void stop()
    {
        threadPoolExecutor.shutdown();
    }


}
