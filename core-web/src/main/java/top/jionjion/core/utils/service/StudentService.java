package top.jionjion.core.utils.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.jionjion.core.bean.Student;
import top.jionjion.core.mapper.StudentMapper;

/**
 * 学生类业务
 *
 * @author Jion
 */
@Service
public class StudentService {

    private final StudentMapper mapper;

    @Autowired
    public StudentService(StudentMapper mapper) {
        this.mapper = mapper;
    }


    /**
     * 通过ID查询
     *
     * @param id 主键
     * @return 结果
     */
    public Student findStudentById(Integer id) {
        return mapper.findStudentById(id);
    }
}
