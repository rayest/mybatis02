package cn.rayest.persistence;

import cn.rayest.model.Student;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

/**
 * Created by Rayest on 2016/6/24 0024.
 */

//ӳ�����ӿ�
public interface StudentPersistence {
     List<Student> searchStudents(Map<String, Object> map);
     List<Student> searchStudents2(Map<String, Object> map);
     List<Student> searchStudents3(Map<String, Object> map);
     List<Student> searchStudents5(Map<String, Object> map);
     int updateStudent(Student student);
     int insertStudent(Student student);
     Student getStudentById(Integer id);

     // �߼���ҳ
     List<Student> findStudents(RowBounds rowBounds);

     // �����ҳ
     List<Student> findStudents2(Map<String, Object> map);


}
