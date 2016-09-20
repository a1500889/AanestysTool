package fi.softala.jee.aanestys.dao;

import java.sql.JDBCType;
import java.sql.ResultSet;
import java.util.List;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import fi.softala.jee.aanestys.bean.Vaihtoehto;
import fi.softala.jee.aanestys.bean.VaihtoehtoImpl;
@Repository
public class VaihtoehtoDAOImpl implements VaihtoehtoDAO {
	
	@Inject
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void insert(Vaihtoehto vEhto) {
		if(vEhto.getVaihtoehtoID() > 0) {
			
			String sql = "INSERT INTO Vaihtoehto (AanestysID, VaihtoehtoNimi)" + " VALUES (?, ?)";
			jdbcTemplate.update(sql, vEhto.getAanestysID(), vEhto.getVaihtoehtoNimi());
			
			
		}
		
	}

	public void delete(int VaihtoehtoID) {
		String sql = "DELETE FROM Vaihtoehto WHERE VaihtoehtoID = ?";
		jdbcTemplate.update(sql, VaihtoehtoID);
		
	}

	public Vaihtoehto get(int Vaihtoehto) {
		String sql = "SELECT * FROM Vaihtoehto WHERE VaihtoehtoID =" + Vaihtoehto+";";
		return jdbcTemplate.query(sql, new ResultSetExtractor<Vaihtoehto>() {
			
			public Vaihtoehto extractData(ResultSet rs) throws SQLException, DataAccessException {
				if(rs.next()) {
					Vaihtoehto vEhto = new VaihtoehtoImpl();
					vEhto.setVaihtoehtoID(rs.getInt("vaihtoehtoID"));
					vEhto.setAanestysID(rs.getInt("aanestysID"));
					vEhto.setVaihtoehtoNimi(rs.getString("vaihtoehtoNimi"));
					return vEhto;
				}
				return null;
			}
		});
			
	}

	public List<Vaihtoehto> lista() {
		String sql = "SELECT * FROM Vaihtoehto";
		List<Vaihtoehto> listaaVaihtoehdot = jdbcTemplate.query(sql, new RowMapper<Vaihtoehto>() {
			
			
			public Vaihtoehto mapRow(ResultSet rs, int rowNum) throws SQLException {
				Vaihtoehto vEhto = new VaihtoehtoImpl();
				
				vEhto.setVaihtoehtoID(rs.getInt("vaihtoehtoID"));
				vEhto.setAanestysID(rs.getInt("aanestysID"));
				vEhto.setVaihtoehtoNimi(rs.getString("vaihtoehtoNimi"));
				
				return vEhto;
		
			}
		});
		
		return listaaVaihtoehdot;
}
}
