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

	//Lisää kantaan äänestäjän
	public void insert(Aanestaja hlö) {
		String sql ="INSERT INTO Aanestaja (Etunimi, Sukunimi) values=(?,?)";
		jdbcTemplate.update(sql, hlö.getAanestajaEtunimi(), hlö.getAanestajaSukunimi());
		
	}

	public void delete(int AanestajaID) {
		
		
	}

	public List<Aanestaja> lista() {
		String sql="SELECT * FROM Aanestaja";
		List<Aanestaja> lista=jdbcTemplate.query(sql, new RowMapper<Aanestaja>(){
			public Aanestaja mapRow (ResultSet rs, int rowNum) throws SQLException {
				Aanestaja hlo = new AanestajaImpl();
				hlo.setAanestajaID(rs.getInt("AanestajaID"));
				hlo.setAanestajaEtunimi(rs.getString("Etunimi"));
				hlo.setAanestajaSukunimi(rs.getString("Sukunimi"));
				
				return hlo;
			}
		});
		return lista;
	}

}
