
package MMM.demo.Daos;

import MMM.demo.Entities.Inventory;
import MMM.demo.Repositories.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class InventoryDaoImpl implements InventoryRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Inventory> findAll() {
        String sql = "SELECT * FROM inventory";
        return jdbcTemplate.query(sql, new InventoryRowMapper());
    }

    private static class InventoryRowMapper implements RowMapper<Inventory> {
        @Override
        public Inventory mapRow(ResultSet rs, int rowNum) throws SQLException {
            Inventory result = new Inventory();
            result.setId_item(rs.getInt("id_item"));
            result.setItem_name(rs.getString("item_name"));
            result.setQuantity(rs.getInt("quantity"));
            result.setId_gym(rs.getInt("id_gym"));
            result.setId_category(rs.getInt("id_category"));
            return result;
        }
    }
}