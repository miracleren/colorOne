package com.colorone.common.frame.asyncTask;

import lombok.extern.slf4j.Slf4j;

import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/11/28
 * @备注： 异步任务管理器
 */

@Slf4j
public class AsyncTaskManager {
    private static final AsyncTaskManager asyncTaskManager = new AsyncTaskManager();

    // 操作延迟10毫秒
    private final int START_DELAY_TIME = 10;

    private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(START_DELAY_TIME);


    /**
     * 获取异步任务单例对象
     * 用于执行异步任务
     *
     * @return
     */
    public static AsyncTaskManager getInstance() {
        return asyncTaskManager;
    }

    /**
     * 执行异步任务
     */
    public void execute(TimerTask task) {
        executor.schedule(task, START_DELAY_TIME, TimeUnit.MILLISECONDS);
    }

    /**
     * 停止任务线程池
     */
    public void shutdown() {
        if (!executor.isShutdown()) {
            executor.shutdown();
            try {
                if (!executor.awaitTermination(120, TimeUnit.SECONDS)) {
                    executor.shutdownNow();
                    if (!executor.awaitTermination(120, TimeUnit.SECONDS)) {
                        log.info("Pool did not terminate");
                    }
                }
            } catch (InterruptedException ie) {
                executor.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }

}