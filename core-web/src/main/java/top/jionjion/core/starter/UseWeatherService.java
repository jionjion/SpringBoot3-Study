package top.jionjion.core.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.jionjion.util.weather.WeatherService;

/**
 * 使用自定义, 如果依赖没有事先编译成为 Jar 文件,可能会失效
 *
 * @author Jion
 */
@Component
public class UseWeatherService {

    final WeatherService weatherService;

    public UseWeatherService(@Autowired(required = false) WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    /**
     * 获得天气状态
     *
     * @return 状态
     */
    public String getType() {
        return weatherService.getType();
    }
}
