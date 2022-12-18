package scripts

import top.jionjion.groovy.service.SpringContextUtil
import top.jionjion.groovy.service.GroovyLogicService

/**
 * groovy 类
 *
 * @author Jion
 */


/**
 * 调用SpringBoot的业务方法
 */
def doSomething() {
    GroovyLogicService groovyLogicService = SpringContextUtil.getBean(GroovyLogicService.class);
    return groovyLogicService.doSomething();
}