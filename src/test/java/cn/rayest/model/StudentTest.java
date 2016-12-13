package cn.rayest.model;

import cn.rayest.persistence.StudentPersistence;
import cn.rayest.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Rayest on 2016/6/24 0024.
 */
public class StudentTest {
    private SqlSession sqlSession = null;
    private StudentPersistence studentPersistence = null;

    @Before
    public void setUp() {
        sqlSession = SqlSessionFactoryUtil.openSession();
        studentPersistence = sqlSession.getMapper(StudentPersistence.class);
    }

    @After
    public void tearDown() {
        sqlSession.close();
    }

    @Test
    public void testSearchStudents() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("gradeId", 1);
        map.put("name", "%ay%");
        map.put("age", 22);
        List<Student> studentList = studentPersistence.searchStudents(map);
        for (Student student : studentList) {
            System.out.println(student);
        }
        sqlSession.commit();
    }

    @Test
    public void testSearchStudents2() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("searchBy", "gradeId");
        map.put("gradeId", 1);
        map.put("name", "%Candy%");
        map.put("age", 22);
        List<Student> studentList = studentPersistence.searchStudents2(map);
        for (Student student : studentList) {
            System.out.println(student);
        }
        sqlSession.commit();
    }

    @Test
    public void testSearchStudents3() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("gradeId", 4);
        List<Student> studentList = studentPersistence.searchStudents3(map);
        for (Student student : studentList) {
            System.out.println(student);
        }
        sqlSession.commit();
    }

    @Test
    public void testSearchStudents5() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Integer> gradeIds = new ArrayList<Integer>();
        gradeIds.add(1);
        gradeIds.add(4);
        map.put("gradeIds", gradeIds);
        List<Student> studentList = studentPersistence.searchStudents5(map);
        for (Student student : studentList) {
            System.out.println(student);
        }
    }

    @Test
    public void testUpdateStudent() {
        Student student = new Student();
        student.setId(6);
        student.setName("Paris Sweety");
        student.setAge(21);
        studentPersistence.update(student);
        sqlSession.commit();
    }


    @Test
    public void testInsertStudent() {
        Student student = new Student();
        student.setName("LEE");
        student.setAge(14);
        student.setRemark("Long long text...");
        byte[] pic = null;
        try {
            File file = new File("F://zxy5.jpg");
            InputStream inputStream = new FileInputStream(file);
            pic = new byte[inputStream.available()];
            inputStream.read(pic);
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        student.setPic(pic);
        studentPersistence.create(student);
        sqlSession.commit();
    }


    @Test
    public void testGetStudentById() {
        Student student = studentPersistence.findById(1);
        System.out.println(student);
        byte[] pic = student.getPic();
        try {
            File file = new File("f://zxy5.jpg");
            OutputStream outputStream = new FileOutputStream(file);
            outputStream.write(pic);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindStudent() {
        int offset = 0, limit = 5;
        RowBounds rowBounds = new RowBounds(offset, limit);
        List<Student> studentList = studentPersistence.loadStudents(rowBounds);
        for (Student student : studentList) {
            System.out.println(student);
        }
    }

    @Test
    public void testFindStudent2() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", 3);
        map.put("size", 3);
        List<Student> studentList = studentPersistence.loadWithPage(map);
        for (Student student : studentList) {
            System.out.println(student);
        }
    }
}
