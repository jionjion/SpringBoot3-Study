package top.jionjion.jpa.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import top.jionjion.jpa.bean.Teacher;

@Slf4j
@Rollback
@Transactional
@SpringBootTest
class TeacherPagingAndSortingRepositoryTest {

    @Autowired
    TeacherPagingAndSortingRepository teacherPagingAndSortingRepository;


    /**
     * 测试分页逻辑
     */
    @Test
    void testFindAllPageable() {
        //分页查询,第一页,显示三个
        Pageable pageable = PageRequest.of(0, 3);
        Page<Teacher> page = teacherPagingAndSortingRepository.findAll(pageable);
        System.out.println("总页数:" + page.getTotalPages());
        System.out.println("总记录数:" + page.getTotalElements());
        System.out.println("当前页数:" + page.getNumber());                    //从0开始
        System.out.println("当前页面记录数:" + page.getNumberOfElements());
        System.out.println("当前页面的集合:" + page.getContent());
    }


    /**
     * 测试排序逻辑
     */
    @Test
    void testFindAllSort() {

        //匿名内部类,完成根据id降序排列
        Sort.Order orders = new Sort.Order(Sort.Direction.DESC, "id");
        Sort sort = Sort.by(orders);

        //分页查询,第一页,显示三个,并传入排序规则
        Pageable pageable = PageRequest.of(0, 3, sort);
        Page<Teacher> page = teacherPagingAndSortingRepository.findAll(pageable);
        System.out.println("总页数:" + page.getTotalPages());
        System.out.println("总记录数:" + page.getTotalElements());
        System.out.println("当前页数:" + page.getNumber());                    //从0开始
        System.out.println("当前页面记录数:" + page.getNumberOfElements());
        System.out.println("当前页面的集合:" + page.getContent());
    }

}
