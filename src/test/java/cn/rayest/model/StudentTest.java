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


    // ���Է���ǰ����
    @Before
    public void setUp() {
        sqlSession = SqlSessionFactoryUtil.openSession();
        studentPersistence = sqlSession.getMapper(StudentPersistence.class);
    }

    // ���Է��������
    @After
    public void tearDown() {
        sqlSession.close();
    }

    @Test
    public void testSearchStudents() {
        logger.info("��ѯѧ��(������)");
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
        logger.info("��ѯѧ��(������)");
        Map<String, Object> map = new HashMap<String, Object>();
        // ͨ�� gradeId ���в��ң��� name �� age ����
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
        logger.info("��ѯѧ��(������)");
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
        logger.info("���ѧ��(������)");
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
        logger.info("����ѧ��(������)");
        Student student=new Student();
        student.setId(6);
        student.setName("Paris Sweety");
        student.setAge(21);
        studentPersistence.updateStudent(student);
        sqlSession.commit();
    }

    /* �����ļ����� */
    @Test
    public void testInsertStudent(){
        logger.info("���ѧ��");
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

    // ��ȡ�ļ�����
    @Test
    public void testGetStudentById(){
        logger.info("ͨ��ID����ѧ��");
        Student student=studentPersistence.getStudentById(1);
        System.out.println(student);
        // �����ݿ��л�ȡͼƬ�ļ�������ŵ� f://zxy5.jpg
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

    /* �߼���ҳ��ԭ�����Ȳ�����е���Ϣ�����ڴ��У��ٸ���������ʾ����Ҫ����Ϣ�����
       ���������ҳ��
    */
    @Test
    public void testFindStudent(){
        logger.info("��ѯѧ��");

        // ���㿪ʼ����ʾ������ѯ���
        int offset = 0, limit = 5;
        RowBounds rowBounds = new RowBounds(offset, limit);
        List<Student> studentList = studentPersistence.findStudents(rowBounds);
        for(Student student:studentList){
            System.out.println(student);
        }
    }

    // �����ҳ
    @Test
    public void testFindStudent2(){
        logger.info("��ѯѧ��");
        Map<String,Object> map = new HashMap<String,Object>();
        // start: �Ǵ��±�Ϊ 3 ��ʼ
        map.put("start", 3);
        // ���Ҫ��ѯ n ����¼���� size ����Ϊ n
        map.put("size", 3);
        List<Student> studentList=studentPersistence.findStudents2(map);
        for(Student student:studentList){
            System.out.println(student);
        }
    }


}
