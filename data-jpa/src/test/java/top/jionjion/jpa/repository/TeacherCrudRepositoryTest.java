package top.jionjion.jpa.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import top.jionjion.jpa.bean.Teacher;

import java.util.Date;
import java.util.Optional;

@Slf4j
@Rollback
@Transactional
@SpringBootTest
class TeacherCrudRepositoryTest {

    @Autowired
    TeacherCrudRepository teacherCrudRepository;


    /**
     * 查询当前总记录数
     */
    @Test
    void testCount() {
        long count = teacherCrudRepository.count();
        System.out.println("总记录数:" + count);
    }

    /**
     * 删除当前数据表
     */
    @Test
    void testDeleteAll() {
        teacherCrudRepository.deleteAll();
    }

    /**
     * 保存实体
     */
    @Test
    void testSaveS() {
        Teacher teacher = new Teacher();
        teacher.setId(7);
        teacher.setName("Per");
        teacher.setAge(20);
        teacher.setAddress("开封");
        teacher.setWorkday(new Date());
        teacherCrudRepository.save(teacher);
    }

    /**
     * 通过主键查询
     */
    @Test
    void testFindOneInteger() {
        Optional<Teacher> teacher = teacherCrudRepository.findById(1);
        System.out.println("查询第一个:" + teacher);
    }

    /**
     * 通过主键判断
     */
    @Test
    void testExistsInteger() {
        boolean exists = teacherCrudRepository.existsById(1);
        System.out.println("第一个是否存在:" + exists);
    }

    /**
     * 查询全部方法
     * 输出一个实现了Iterable<T>接口的实体对象,这里直接调用其迭代器进行迭代输出
     */
    @Test
    void testFindAll() {
        for (Teacher teacher : teacherCrudRepository.findAll()) {
            System.out.println("迭代下一个:" + teacher);
        }
    }


    /**
     * 根据主键删除
     */
    @Test
    void testDeleteInteger() {
        teacherCrudRepository.deleteById(7);
    }

    /**
     * 通过实体删除
     */
    @Test
    void testDeleteTeacher() {
        Optional<Teacher> teacher = teacherCrudRepository.findById(8);
        teacher.ifPresent(value -> teacherCrudRepository.delete(value));
    }

    /**
     * 通过迭代器删除全部
     */
    @Test
    void testDeleteIterableTeacher() {
        Iterable<Teacher> teachers = teacherCrudRepository.findAll();
        teacherCrudRepository.deleteAll(teachers);
    }

}
