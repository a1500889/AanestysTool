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
import fi.softala.jee.aanestys.bean.Ryhma;
import fi.softala.jee.aanestys.bean.RyhmaImpl;

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
		String sql ="INSERT INTO Aanestaja (Etunimi, Sukunimi, RyhmaID)" + " VALUES (?,?, 1)";
		jdbcTemplate.update(sql, aanestaja.getEtunimi(), aanestaja.getSukunimi());
		
	}
	
	public void delete(int AanestajaID) {
		String siistiAanet = "UPDATE Aani SET AanestajaID=1 WHERE AanestajaID=?";
		jdbcTemplate.update(siistiAanet, AanestajaID);
		String sql = "DELETE FROM Aanestaja WHERE AanestajaID = ?";
		jdbcTemplate.update(sql, AanestajaID);
		
	}
	
	public void poistaLuvatAanestaja (int AanestajaID) {
		String sql = "DELETE FROM Lupa WHERE AanestajaID = ?";
		jdbcTemplate.update(sql, AanestajaID);
	}

	public void deletet(int AanestysID) {
		String sql = "DELETE FROM Aanestaja WHERE AanestysID = ?";
		jdbcTemplate.update(sql, AanestysID);
	}

	public List<Aanestaja> lista() {
		String sql="SELECT AanestajaID, Etunimi, Sukunimi, r.RyhmaID, r.RyhmaNimi, r.RyhmaTunnus FROM Aanestaja a JOIN Ryhma r ON a.RyhmaID=r.RyhmaID WHERE AanestajaID<>1";
		//WHERE AanestajaID<>1
		List<Aanestaja> lista=jdbcTemplate.query(sql, new RowMapper<Aanestaja>(){
			public Aanestaja mapRow (ResultSet rs, int rowNum) throws SQLException {
				Aanestaja hlo = new AanestajaImpl();
				Ryhma hryh = new RyhmaImpl();
				hlo.setAanestajaID(rs.getInt("AanestajaID"));
				hlo.setEtunimi(rs.getString("Etunimi"));
				hlo.setSukunimi(rs.getString("Sukunimi"));
				hryh.setRyhmaID(rs.getInt("RyhmaID"));
				hryh.setRyhmaNimi(rs.getString("RyhmaNimi"));
				hryh.setRyhmaTunnus(rs.getString("RyhmaTunnus"));
				hlo.setRyhma(hryh);
				
				return hlo;
			}
		});
		return lista;
	}
	
	public void lisaaAanestysOikeudet(int[] aanestajat, int[]aanestykset){
		String lisaaOikeus = "INSERT INTO Lupa VALUES(?,?,0)";
		
		for (int i = 0; i < aanestykset.length; i++) {
			for (int j = 0; j < aanestajat.length; j++) {
				jdbcTemplate.update(lisaaOikeus,aanestajat[j], aanestykset[i]);
			}
			
		}
	}
	
	public List<String> listaaLuvalliset(int AanestysID){
		String sql = "SELECT Aanestaja.Etunimi, Aanestaja.sukunimi, r.RyhmaNimi FROM Lupa INNER JOIN Aanestaja ON Aanestaja.AanestajaID=Lupa.AanestajaID JOIN Ryhma r ON Aanestaja.RyhmaID=r.RyhmaID WHERE AanestysID ="+AanestysID+" AND Lupa.Aanestanyt=false;";
		List<String> luvallisetLista = jdbcTemplate.query(sql, new RowMapper<String>(){
			public final String mapRow (ResultSet rs, int rowNum) throws SQLException {
				String nimi = rs.getString("Etunimi")+" "+rs.getString("Sukunimi")+" "+rs.getString("RyhmaNimi");
				nimi=nimi.toLowerCase();
			
				return nimi;
			}
		});
		
		return luvallisetLista;
	}
	
	//hakee etunimen ja sukunimen perusteella ƒ‰nest‰j‰ID:n. Hakee ensimm‰isen, jos samoja nimi‰ on useita.
	public int haeVapaaAanestajaID(Aani Aani, String etunimi, String sukunimi, String RyhTun){
		String kasky2 = "SELECT Aanestaja.AanestajaID FROM Aanestaja INNER JOIN Lupa ON Aanestaja.AanestajaID=Lupa.AanestajaID JOIN Ryhma r ON Aanestaja.RyhmaID=r.RyhmaID WHERE Aanestaja.Etunimi='"+etunimi+"' AND Aanestaja.Sukunimi='"+sukunimi+"' AND r.RyhmaNimi='"+RyhTun+"' AND Lupa.Aanestanyt=false AND Lupa.AanestysID='"+Aani.getAanestysID()+"' ORDER BY AanestajaID LIMIT 1";
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
		oikeus = jdbcTemplate.query(tarkastuskasky, new ResultSetExtractor<Boolean>(){
		
			public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException {
				boolean oikeus = false;
					if(rs.next()){
						oikeus = rs.getBoolean("Aanestanyt");
					}
				
				return oikeus;
	
			}
		
		});
		
		return oikeus;
	}
	
	public List<Ryhma> haeRyhmat(){
		String order = "SELECT * FROM Ryhma";
		List<Ryhma> ryhmat = jdbcTemplate.query(order, new RowMapper<Ryhma>(){
			public Ryhma mapRow (ResultSet rs, int rowNum) throws SQLException {
				Ryhma r = new RyhmaImpl();
				r.setRyhmaID(rs.getInt("RyhmaID"));
				r.setRyhmaNimi(rs.getString("RyhmaNimi"));
				r.setRyhmaTunnus(rs.getString("RyhmaTunnus"));
				
				return r;
			}
		});
		return ryhmat;
		
	}
	
	public void lisaaRyhmiin(int ryhmaID, int[] aanestajalista){
		String kasky = "UPDATE Aanestaja SET RyhmaID=? WHERE AanestajaID=?";
		for (int i = 0; i < aanestajalista.length; i++) {
			jdbcTemplate.update(kasky, ryhmaID, aanestajalista[i]);
			
		}
		
	}
	
	public void lisaaRyhma(Ryhma ryhma){
		String ryhmanLisaysKasky = "INSERT INTO Ryhma (RyhmaNimi, RyhmaTunnus) VALUES(?, ?)";
		jdbcTemplate.update(ryhmanLisaysKasky, ryhma.getRyhmaNimi(), ryhma.getRyhmaTunnus());
		
	}
	
	public void poistaRyhma(int ryhmaID){
		VaihtoehtoDAOImpl vdao = new VaihtoehtoDAOImpl();
		vdao.poistaRyhmanVaihtoehdot(ryhmaID);
		String poistokysely = "DELETE FROM Ryhma WHERE RyhmaID=?";
		jdbcTemplate.update(poistokysely, ryhmaID);
		
	}

}
