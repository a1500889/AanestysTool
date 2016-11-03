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

	public void insert(Aani Aani) {
		String kasky = "INSERT INTO Aani(AanestysID, VaihtoehtoID, AanestajaID) VALUES(?,?,?);";
		jdbcTemplate.update(kasky, Aani.getAanestysID(), Aani.getVaihtoehtoID(), 1);
		// TODO Auto-generated method stub
		
	}
	
	public void insert(Aani Aani, String etunimi, String sukunimi) {
		String kasky = "INSERT INTO Aani(AanestysID, VaihtoehtoID, AanestajaID) VALUES(?,?,?);";
		jdbcTemplate.update(kasky, Aani.getAanestysID(), Aani.getVaihtoehtoID(), 1);
		
		String kasky2 = "SELECT Aanestaja.AanestajaID FROM Aanestaja INNER JOIN Lupa ON Aanestaja.AanestajaID=Lupa.AanestajaID WHERE Aanestaja.Etunimi='"+etunimi+"' AND Aanestaja.Sukunimi='"+sukunimi+"' AND Lupa.Aanestanyt=false ORDER BY AanestajaID LIMIT 1;";
		Integer paskalista = jdbcTemplate.query(kasky2, new ResultSetExtractor<Integer>(){
		
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				int Id = 999;
					if(rs.next()){
						System.out.println(rs.getInt("AanestajaID"));
						Id = rs.getInt("AanestajaID");
					}
				
				return Id;
	
			}
		
		});
		
		System.out.println(paskalista);
		
		String kasky3 = "UPDATE Lupa SET Aanestanyt=true WHERE AanestysID=? AND AanestajaID= ?";
		jdbcTemplate.update(kasky3, Aani.getAanestysID(),paskalista);
				// TODO Auto-generated method stub
		
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
