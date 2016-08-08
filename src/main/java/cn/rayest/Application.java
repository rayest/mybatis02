package cn.rayest;

import cn.rayest.persistence.StudentPersistence;
import cn.rayest.model.Student;
import cn.rayest.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

/**
 * Created by Rayest on 2016/6/24 0024.
 */
public class Application {

    private static Logger logger = Logger.getLogger(Application.class);

    public static void main(String[] args) {
        SqlSession sqlSession = SqlSessionFactoryUtil.openSession();
        StudentPersistence studentPersistence = sqlSession.getMapper(StudentPersistence.class);
        Student student = new Student("Candy", 21);
    }
}
