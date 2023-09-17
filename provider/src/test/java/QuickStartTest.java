import org.bamboo.ProviderAPP;
import org.bamboo.mapper.StuMapper;
import org.bamboo.pojo.Stu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

@SpringBootTest(classes = QuickStartTest.class)
@ContextConfiguration( classes = ProviderAPP.class)
public class QuickStartTest {
    @Autowired
    private StuMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<Stu> userList = userMapper.queryAll();
        Assertions.assertEquals(2, userList.size());
        userList.forEach(System.out::println);
    }
}