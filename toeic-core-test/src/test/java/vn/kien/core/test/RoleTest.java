package vn.kien.core.test;

import org.junit.Assert;
import org.testng.annotations.Test;
import vn.kien.core.dao.RoleDao;
import vn.kien.core.daoimpl.RoleDaoImpl;
import vn.kien.core.persistence.entity.Role;
import vn.kien.core.persistence.entity.Users;

import java.util.ArrayList;
import java.util.List;

public class RoleTest {
    /*@Test
    public void checkFindAll() {
        RoleDao roleDao = new RoleDaoImpl();
        List<Role> list = roleDao.findAll();
    }*/

   /* @Test
    public void checkUpdateRole() {
        RoleDao roleDao = new RoleDaoImpl();
        Role entity = new Role();
        entity.setRoleId(1);
        entity.setName("ADMIN");
        roleDao.update(entity);
    }*/

    @Test
    public void checkSaveRole() {
        RoleDao roleDao = new RoleDaoImpl();
        Users users = new Users();
        users.setName("Nick4");
        users.setRole_id(4);
        roleDao.save(users);
    }

   /* @Test
    public void checkFindById() {
        RoleDao roleDao = new RoleDaoImpl();
        Role entity = roleDao.findById(2);
        System.out.println(entity.getRoleId() + " " + entity.getName());
    }*/

    @Test
    public void checkFindByProperty() {
        RoleDao roleDao = new RoleDaoImpl();
        Object[] res = roleDao.findByProperty("role_id", 1, null, null);
        List<Role> list = (List<Role>) res[0];
        System.out.println();
    }

    @Test
    public void checkDelete() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        RoleDao roleDao = new RoleDaoImpl();
        Assert.assertEquals(list.size(), (int) roleDao.delete(list));
    }

}
