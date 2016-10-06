package fi.softala.jee.aanestys.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import fi.softala.jee.aanestys.bean.Aanestaja;
import fi.softala.jee.aanestys.bean.AanestajaImpl;

@Repository
public class AanestajaDAOImpl implements AanestajaDAO {
	
	@Inject
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	//Lis‰‰ kantaan ‰‰nest‰j‰n
	public void insert(Aanestaja aanestaja) {
		String sql ="INSERT INTO Aanestaja (Etunimi, Sukunimi)" + " VALUES (?,?)";
		jdbcTemplate.update(sql, aanestaja.getEtunimi(), aanestaja.getSukunimi());
		
	}
	
	public void delete(int AanestajaID) {
		// TODO Auto-generated method stub
		
	}

	public void deletet(int AanestysID) {
		String sql = "DELETE FROM Aanestaja WHERE AanestysID = ?";
		jdbcTemplate.update(sql, AanestysID);
	}

	public List<Aanestaja> lista() {
		String sql="SELECT * FROM Aanestaja";
		List<Aanestaja> lista=jdbcTemplate.query(sql, new RowMapper<Aanestaja>(){
			public Aanestaja mapRow (ResultSet rs, int rowNum) throws SQLException {
				Aanestaja hlo = new AanestajaImpl();
				hlo.setAanestajaID(rs.getInt("AanestajaID"));
				hlo.setEtunimi(rs.getString("Etunimi"));
				hlo.setSukunimi(rs.getString("Sukunimi"));
				
				return hlo;
			}
		});
		return lista;
	}
}
