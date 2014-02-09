/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wpa.wpa.scheduling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author Skalda
 */

@Component
public class MyScheduler {
    @Autowired
    private MyTask task;

    @Scheduled(fixedDelay = 10000)
    public void process() {
        task.execute();
    }
    
    /**
     * @return the task
     */
    public MyTask getTask() {
        return task;
    }

    /**
     * @param task the task to set
     */
    public void setTask(MyTask task) {
        this.task = task;
    }
    
    
}
