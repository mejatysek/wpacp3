/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wpa.wpa.scheduling;

import com.wpa.wpa.dto.ArticleDto;
import com.wpa.wpa.service.ArticleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 *
 * @author Skalda
 */
@Component
public class MyTask {
    @Autowired
    private ArticleService articleService;
    
    @Async
    public void execute() {
        int state1 = articleService.getStatesArticles(1l).size();
        int state2 = articleService.getStatesArticles(2l).size();
        int state3 = articleService.getStatesArticles(3l).size();
        System.out.println("V systemu je celkem " + (state1 + state2 + state3) + " článků z toho " + state3 + " již prošlo korekturou, u " + state2 + " korektura probíhá a " + state1 + " čeká na přiřazení ke korektorovi");
        
    }
    
}
