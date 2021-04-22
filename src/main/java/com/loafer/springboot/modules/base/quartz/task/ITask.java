package com.loafer.springboot.modules.base.quartz.task;

/**
 * 定时任务接口，所有定时任务都要实现该接口
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
public interface ITask {

    /**
     * 执行定时任务接口
     *
     * @param params   参数，多参数使用JSON数据
     */
    void run(String params);
}
