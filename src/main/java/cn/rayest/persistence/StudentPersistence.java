package cn.rayest.persistence;

import cn.rayest.model.Student;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

/**
 * Created by Rayest on 2016/6/24 0024.
 */

public interface StudentPersistence {
    List<Student> searchStudents(Map<String, Object> map);

    List<Student> searchStudents2(Map<String, Object> map);

    List<Student> searchStudents3(Map<String, Object> map);

    List<Student> searchStudents5(Map<String, Object> map);

    int update(Student student);

    int create(Student student);

    Student findById(Integer id);

    List<Student> loadStudents(RowBounds rowBounds);

    List<Student> loadWithPage(Map<String, Object> map);


}
