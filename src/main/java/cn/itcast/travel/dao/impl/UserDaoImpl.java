package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao {

    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public User findByUsername(String username) {
        User user = null;
        try {
            String sql = "select * from tab_user where username = ?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        } catch (DataAccessException e) {

        }
        return user;
    }

    @Override
    public Boolean registerUser(User user) {
        String sql = "insert into tab_user values(null,?,?,?,?,?,?,?,?,?)";
        int update = template.update(sql, user.getUsername(), user.getPassword(), user.getName(),
                user.getBirthday(), user.getSex(), user.getTelephone(), user.getEmail(),user.getStatus(), user.getCode());
        if(update>0) {
            return true;
        }
        return false;
    }

    @Override
    public User findUserByCode(String code) {
        User user = null;
        try {
            String sql = "select * from tab_user where code = ?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), code);
            return user;
        } catch (DataAccessException e) {

        }
        return user;
    }

    @Override
    public Integer activateUser(User u) {

        String sql = "update tab_user set status = 'Y'where uid = ?";
        int update = template.update(sql, u.getUid());
        return update;
    }

    @Override
    public User findUserByUsernameAndPassword(User loginUser) {
        User user = null;
        try {
            String sql = "select * from tab_user where username = ? and password = ?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), loginUser.getUsername(),loginUser.getPassword());
        } catch (DataAccessException e) {

        }
        return user;

    }
}
