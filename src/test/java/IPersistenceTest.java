import com.lagou.dao.IUserDao;
import com.lagou.io.Resources;
import com.lagou.pojo.User;
import com.lagou.sqlSession.SessionFactoryBuilder;
import com.lagou.sqlSession.SqlSessionFactory;
import com.lagou.sqlSession.Sqlsession;
import org.dom4j.DocumentException;
import org.junit.Test;

import java.beans.IntrospectionException;
import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author lijian
 * @create 2021-03-07 15:25
 */
public class IPersistenceTest {

    /**
     * @description: 根据statementId操作数据库
     * @params: []
     * @return: void
     * @author: lijian
     * @dateTime: 2021/3/16 14:40
     */
    @Test
    public void testStatementId() throws Exception {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SessionFactoryBuilder().build(resourceAsStream);
        Sqlsession sqlsession = sqlSessionFactory.openSession();

        User user = new User();
        user.setId(2);
        user.setUsername("tom");
        User user2 = sqlsession.selectOne("com.lagou.dao.IUserDao.findByCondition", user);
        System.out.println("----findByCondition----:"+user2.toString());
        System.out.println("----findAll----:");
        List<User> users = sqlsession.selectList("com.lagou.dao.IUserDao.findAll", new User());
        for (User user3 : users) {
            System.out.println(user3.toString());
        }
    }

    /**
     * @description: 通过代理对象操作数据库
     * @params: []
     * @return: void
     * @author: lijian
     * @dateTime: 2021/3/16 14:41
     */
    @Test
    public void testProxy() throws Exception {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SessionFactoryBuilder().build(resourceAsStream);
        Sqlsession sqlsession = sqlSessionFactory.openSession();

        IUserDao userDao = sqlsession.getMapper(IUserDao.class);

       /* User user = new User();
        user.setId(2);
        user.setUsername("tom");

        User resultUser = userDao.findByCondition(user);
        System.out.println("----findByCondition----:"+resultUser.toString());
        System.out.println("----findAll----:");
        List<User> resultUsers = userDao.findAll();
        for (User resultUser2 : resultUsers) {
            System.out.println(resultUser2.toString());
        }*/

        //新增
        User user2 = new User();
        user2.setId(99);
        user2.setUsername("六六");
        user2.setPassword("11111");
        user2.setBirthday("2021-03-16");

        int ret = userDao.insertUser(user2);
    }
}
