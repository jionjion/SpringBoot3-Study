package top.jionjion.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.logging.Logger;

/**
 * 自定义日期格式转换器
 *
 * @author Jion
 */
@RestController
@RequestMapping("/date")
public class DateConverterController {

    final Logger logger = Logger.getLogger(DateConverterController.class.getName());


    /**
     * 返回指定日期的时间戳
     */
    @GetMapping
    public Long getDateTimestamp(@RequestParam("date") Date date) {
        // 通过自定义格式转换器转换
        logger.info(() -> "userList: " + date);
        return date.getTime();
    }
}