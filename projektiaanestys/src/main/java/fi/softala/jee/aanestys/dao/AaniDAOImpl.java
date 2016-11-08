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

import fi.softala.jee.aanestys.bean.Aani;
import fi.softala.jee.aanestys.bean.AaniImpl;

@Repository
public class AaniDAOImpl implements AaniDAO{
	
	@Inject
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Inject
	public AanestajaDAO aandao;
	
	public void set (AanestajaDAO aadao){
		this.aandao=aadao;
	}

	public void insert(Aani Aani) {
		String kasky = "INSERT INTO Aani(AanestysID, VaihtoehtoID, AanestajaID) VALUES(?,?,?);";
		jdbcTemplate.update(kasky, Aani.getAanestysID(), Aani.getVaihtoehtoID(), 1);
		// TODO Auto-generated method stub
		
	}
	
	public void insert(Aani Aani, int kayttajaID) {
	
		String kasky = "INSERT INTO Aani(AanestysID, VaihtoehtoID, AanestajaID) VALUES(?,?,?);";
		jdbcTemplate.update(kasky, Aani.getAanestysID(), Aani.getVaihtoehtoID(), 1);	
		
		String kasky3 = "UPDATE Lupa SET Aanestanyt=true WHERE AanestysID=? AND AanestajaID= ?";
		jdbcTemplate.update(kasky3, Aani.getAanestysID(),kayttajaID);
		
	}

	public void delete(int AanestysID) {
		String sql = "DELETE FROM Aani WHERE AanestysID = ?";
		jdbcTemplate.update(sql, AanestysID);
		
	}

	public Aani get(int AaniInt) {
		String sql = "SELECT * FROM Aani WHERE AaniID="+AaniInt+";";
		return jdbcTemplate.query(sql, new ResultSetExtractor<Aani>() {
			
			public Aani extractData(ResultSet rs) throws SQLException, DataAccessException {
				if(rs.next()) {
					Aani aa = new AaniImpl();
					aa.setAaniID(rs.getInt("AaniID"));
					aa.setAanestysID(rs.getInt("AanestysID"));
					aa.setVaihtoehtoID(rs.getInt("VaihtoehtoID"));
					return aa;
				}
				return null;
			}
		});
		
	}

	public List<Aani> lista(int AanestId) {
		String sql = "SELECT * FROM Aani WHERE AanestysID="+AanestId;
		List<Aani> aaniLista = jdbcTemplate.query(sql, new RowMapper<Aani>(){
		
			public Aani mapRow(ResultSet rs, int rowNum) throws SQLException {
				Aani aa = new AaniImpl();
			
				aa.setAaniID(rs.getInt("AaniID"));
				aa.setAanestysID(rs.getInt("AanestysID"));
				aa.setVaihtoehtoID(rs.getInt("VaihtoehtoID"));
			
				return aa;
	
			}
		
		});
		return aaniLista;
	

	}
	
	public void aanestanyt(int AanestysID, int AanestajaID){
		String sql = "INSERT INTO Aanestanyt VALUES(?,?);";
		jdbcTemplate.update(sql,AanestajaID,AanestysID);
	}
	

	public void deletet(int AaniID) {
		// TODO Auto-generated method stub
		
	}
	
}
