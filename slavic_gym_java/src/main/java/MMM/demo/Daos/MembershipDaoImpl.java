package MMM.demo.Daos;

import MMM.demo.Entities.Membership;
import MMM.demo.Entities.TransactionsMembership;
import MMM.demo.Repositories.MembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MembershipDaoImpl implements MembershipRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Membership> findAll() {
        String sql = "SELECT * FROM memberships";
        return jdbcTemplate.query(sql, new MembershipRowMapper());
    }

    public List<Membership> findByMemberId(Integer id) {
        String sql = "SELECT m.* FROM memberships m " +
                "JOIN client_membership cm ON m.id_membership = cm.id_membership " +
                "WHERE cm.id_member = ?";
        return jdbcTemplate.query(sql, new MembershipRowMapper(), id);
    }

    public boolean buyMembership(TransactionsMembership transaction) {
        String sql = "INSERT INTO transactions_memberships (id_transaction, id_membership, id_member, order_time) VALUES (?, ?, ?, CURRENT_TIMESTAMP)";

        int rowsAffected = jdbcTemplate.update(sql, transaction.getId_transaction(), transaction.getId_membership(), transaction.getId_member());
        return rowsAffected > 0;
    }

    private static class MembershipRowMapper implements RowMapper<Membership> {
        @Override
        public Membership mapRow(ResultSet rs, int rowNum) throws SQLException {
            Membership result = new Membership();
            result.setName(rs.getString("name"));
            result.setId_membership(rs.getInt("id_membership"));
            result.setIs_active(rs.getBoolean("is_active"));
            result.setPrice(rs.getBigDecimal("price"));
            result.setDuration(rs.getInt("duration"));
            return result;
        }
    }
}
