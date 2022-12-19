package top.jionjion.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.jionjion.web.service.AsyncBusinessService;

import java.util.List;

/**
 * 异步处理线程任务
 *
 * @author Jion
 */
@RestController
public class AsyncBusinessController {

    final AsyncBusinessService asyncBusinessService;

    @Autowired
    public AsyncBusinessController(AsyncBusinessService asyncBusinessService) {
        this.asyncBusinessService = asyncBusinessService;
    }

    @GetMapping(value = "/async")
    public String asyncDoSomeThings(@RequestParam("things") List<String> things) {
        things.forEach(asyncBusinessService::doSomeThing);
        return "异步处理中..";
    }
}
