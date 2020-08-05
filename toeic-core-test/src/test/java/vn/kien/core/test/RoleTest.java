package vn.kien.core.test;

import org.testng.annotations.Test;
import vn.kien.core.dao.RoleDao;
import vn.kien.core.daoimpl.RoleDaoImpl;

import javax.management.relation.Role;
import java.util.List;

public class RoleTest {
    @Test
    public void checkFindAll() {
        RoleDao roleDao = new RoleDaoImpl();
        List<Role> list = roleDao.findAll();
    }
}
