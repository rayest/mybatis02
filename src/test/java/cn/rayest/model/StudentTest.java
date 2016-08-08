package cn.rayest.model;

import cn.rayest.persistence.StudentPersistence;
import cn.rayest.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
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
    private static Logger logger = Logger.getLogger(StudentTest.class);
    private SqlSession sqlSession = null;
    private StudentPersistence studentPersistence = null;


    // 测试方法前调用
    @Before
    public void setUp() {
        sqlSession = SqlSessionFactoryUtil.openSession();
        studentPersistence = sqlSession.getMapper(StudentPersistence.class);
    }

    // 测试方法后调用
    @After
    public void tearDown() {
        sqlSession.close();
    }

    @Test
    public void testSearchStudents() {
        logger.info("查询学生(带条件)");
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
        logger.info("查询学生(带条件)");
        Map<String, Object> map = new HashMap<String, Object>();
        // 通过 gradeId 进行查找，而 name 和 age 忽略
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
        logger.info("查询学生(带条件)");
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
        logger.info("添加学生(带条件)");
        Map<String,Object> map=new HashMap<String,Object>();
        List<Integer> gradeIds=new ArrayList<Integer>();
        gradeIds.add(1);
        gradeIds.add(4);
        map.put("gradeIds", gradeIds);
        List<Student> studentList=studentPersistence.searchStudents5(map);
        for(Student student:studentList){
            System.out.println(student);
        }
    }

    @Test
    public void testUpdateStudent(){
        logger.info("更新学生(带条件)");
        Student student=new Student();
        student.setId(6);
        student.setName("Paris Sweety");
        student.setAge(21);
        studentPersistence.updateStudent(student);
        sqlSession.commit();
    }

    /* 插入文件操作 */
    @Test
    public void testInsertStudent(){
        logger.info("添加学生");
        Student student = new Student();
        student.setName("LEE");
        student.setAge(14);
        student.setRemark("Long long text...");
        byte[] pic = null;
        try{
            File file = new File("F://zxy5.jpg");
            InputStream inputStream=new FileInputStream(file);
            pic = new byte[inputStream.available()];
            inputStream.read(pic);
            inputStream.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        student.setPic(pic);
        studentPersistence.insertStudent(student);
        sqlSession.commit();
    }

    // 读取文件操作
    @Test
    public void testGetStudentById(){
        logger.info("通过ID查找学生");
        Student student=studentPersistence.getStudentById(1);
        System.out.println(student);
        // 从数据库中获取图片文件，并存放到 f://zxy5.jpg
        byte[] pic=student.getPic();
        try{
            File file=new File("f://zxy5.jpg");
            OutputStream outputStream = new FileOutputStream(file);
            outputStream.write(pic);
            outputStream.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /* 逻辑分页：原理是先查出所有的信息放在内存中，再根据条件显示所需要的信息结果数
       不如物理分页好
    */
    @Test
    public void testFindStudent(){
        logger.info("查询学生");

        // 从零开始，显示三条查询结果
        int offset = 0, limit = 5;
        RowBounds rowBounds = new RowBounds(offset, limit);
        List<Student> studentList = studentPersistence.findStudents(rowBounds);
        for(Student student:studentList){
            System.out.println(student);
        }
    }

    // 物理分页
    @Test
    public void testFindStudent2(){
        logger.info("查询学生");
        Map<String,Object> map = new HashMap<String,Object>();
        // start: 是从下标为 3 开始
        map.put("start", 3);
        // 如果要查询 n 条记录，则将 size 设置为 n
        map.put("size", 3);
        List<Student> studentList=studentPersistence.findStudents2(map);
        for(Student student:studentList){
            System.out.println(student);
        }
    }


}
