package fi.softala.jee.aanestys.dao;

import java.sql.ResultSet;
import java.util.List;
import java.sql.SQLException;

import javax.inject.Inject;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import fi.softala.jee.aanestys.bean.Aanestys;
import fi.softala.jee.aanestys.bean.AanestysImpl;

@Repository
public class AanestysDAOImpl implements AanestysDAO{
	
	@Inject
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public void saveOrUpdate(Aanestys aanestys) {
	    if (aanestys.getAanestysID() > 0) {
	        // update
	        String sql = "UPDATE Aanestys SET Tunnus=?, AanestysNimi=?, Kuvaus=?, + WHERE AanestysID=? ";
	        jdbcTemplate.update(sql, aanestys.getTunnus(), aanestys.getAanestysNimi(), aanestys.getKuvaus(), aanestys.getAanestysID());
	    } else {
	        // insert
	        String sql = "INSERT INTO Aanestys (Tunnus, AanestysNimi, Kuvaus)"
	                    + " VALUES (?, ?, ?)";
	        jdbcTemplate.update(sql, aanestys.getTunnus(), aanestys.getAanestysNimi(),
	                aanestys.getKuvaus());
	    }
	 
	}

	public void delete(int AanestysID) {
		String sql = "DELETE FROM Aanestys WHERE AanestysID = ?";
		jdbcTemplate.update(sql, AanestysID);
		
	}
	
	public void poistaLuvatAanestys(int AanestysID) {
		String sql = "DELETE FROM Lupa WHERE AanestysID = ?";
		jdbcTemplate.update(sql, AanestysID);
	}

	
	public Aanestys get(int AanestysID) {
		String sql = "SELECT * FROM Aanestys WHERE AanestysID=" + AanestysID;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Aanestys>() {
			
			public Aanestys extractData(ResultSet rs) throws SQLException,
			DataAccessException {
				if(rs.next()) {
					Aanestys aanestys = new AanestysImpl();
					aanestys.setAanestysID(rs.getInt("AanestysID"));
					aanestys.setAanestysNimi(rs.getString("AanestysNimi"));
					return aanestys;
				}
				return null;
			}
		});
	}
	
	
	
	public List<Aanestys> lista() {
		String sql = "SELECT * FROM Aanestys";
		List<Aanestys> listaaAanestykset = jdbcTemplate.query(sql, new RowMapper<Aanestys>() {
			
			
			public Aanestys mapRow(ResultSet rs, int rowNum) throws SQLException {
				Aanestys aanestykset = new AanestysImpl();
				
				aanestykset.setAanestysID(rs.getInt("AanestysID"));
				aanestykset.setTunnus(rs.getString("Tunnus"));
				aanestykset.setAanestysNimi(rs.getString("AanestysNimi"));
				aanestykset.setKuvaus(rs.getString("Kuvaus"));
				
				return aanestykset;
		
			}
		});
		
		return listaaAanestykset;
}
	
}