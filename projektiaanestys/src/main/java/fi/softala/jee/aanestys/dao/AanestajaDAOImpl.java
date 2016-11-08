package fi.softala.jee.aanestys.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import fi.softala.jee.aanestys.bean.Aanestaja;
import fi.softala.jee.aanestys.bean.AanestajaImpl;
import fi.softala.jee.aanestys.bean.Aani;

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
		String sql = "DELETE FROM Aanestaja WHERE AanestajaID = ?";
		jdbcTemplate.update(sql, AanestajaID);
		
	}

	public void deletet(int AanestysID) {
		String sql = "DELETE FROM Aanestaja WHERE AanestysID = ?";
		jdbcTemplate.update(sql, AanestysID);
	}
	
	public void poistaLuvatAanestaja (int AanestajaID) {
		String sql = "DELETE FROM Lupa WHERE AanestajaID = ?";
		jdbcTemplate.update(sql, AanestajaID);
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
	
	public List<String> listaaLuvalliset(int AanestysID){
		String sql = "SELECT Aanestaja.Etunimi, Aanestaja.sukunimi FROM Lupa INNER JOIN Aanestaja ON Aanestaja.AanestajaID=Lupa.AanestajaID WHERE AanestysID ="+AanestysID+" AND Lupa.Aanestanyt=false;";
		List<String> luvallisetLista = jdbcTemplate.query(sql, new RowMapper<String>(){
			public final String mapRow (ResultSet rs, int rowNum) throws SQLException {
				String nimi = rs.getString("Etunimi")+" "+rs.getString("Sukunimi");

			
				return nimi;
			}
		});
		
		return luvallisetLista;
	}
	
	//hakee etunimen ja sukunimen perusteella ƒ‰nest‰j‰ID:n. Hakee ensimm‰isen, jos samoja nimi‰ on useita.
	public int haeVapaaAanestajaID(Aani Aani, String etunimi, String sukunimi){
		String kasky2 = "SELECT Aanestaja.AanestajaID FROM Aanestaja INNER JOIN Lupa ON Aanestaja.AanestajaID=Lupa.AanestajaID WHERE Aanestaja.Etunimi='"+etunimi+"' AND Aanestaja.Sukunimi='"+sukunimi+"' AND Lupa.Aanestanyt=false AND Lupa.AanestysID='"+Aani.getAanestysID()+"' ORDER BY AanestajaID LIMIT 1;";
		Integer paskalista = jdbcTemplate.query(kasky2, new ResultSetExtractor<Integer>(){
		
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				int Id = 999;
					if(rs.next()){
						Id = rs.getInt("AanestajaID");
					}
				
				return Id;
	
			}
		
		});
		return paskalista;
	}
	
	public boolean tarkistaAanestysoikeus(int kayttajaID, int aanestysID){
		boolean oikeus = false;
		String tarkastuskasky = "SELECT Aanestanyt FROM Lupa WHERE AanestajaID= '"+kayttajaID+"' AND AanestysID='"+aanestysID+"';";
		System.out.println(tarkastuskasky);
		oikeus = jdbcTemplate.query(tarkastuskasky, new ResultSetExtractor<Boolean>(){
		
			public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException {
				boolean oikeus = false;
					if(rs.next()){
						oikeus = rs.getBoolean("Aanestanyt");
						System.out.println("fragumentation:"+oikeus);
					}
				
				return oikeus;
	
			}
		
		});
		
		return oikeus;
	}
}
