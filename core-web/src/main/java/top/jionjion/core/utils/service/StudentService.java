package top.jionjion.core.utils.service;

import org.springframework.stereotype.Service;
import top.jionjion.core.bean.Student;

/**
 * @author Jion
 */
@Service
public class StudentService {

//    private final StudentMapper mapper;
//
//    public StudentService(StudentMapper mapper) {
//        this.mapper = mapper;
//    }


    /**
     *  通过ID查询
     * @param id 主键
     * @return 结果
     */
    public Student findStudentById(Integer id){
        return  new Student(); // mapper.findStudentById(id);
    }
}
