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

import fi.softala.jee.aanestys.bean.Aanestaja;
import fi.softala.jee.aanestys.bean.AanestajaImpl;
import fi.softala.jee.aanestys.bean.Aani;
import fi.softala.jee.aanestys.bean.AaniImpl;
import fi.softala.jee.aanestys.bean.Ryhma;
import fi.softala.jee.aanestys.bean.RyhmaImpl;

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
	
	public void insert(Aani Aani, int kayttajaID) {
		
		//Tarkistaa jos k‰ytt‰j‰ yritt‰‰ ‰‰nest‰‰ omaa ryhm‰‰ns‰
		boolean tarkistus = tarkistaFusku(kayttajaID, Aani.getVaihtoehtoID());
		if(tarkistus){
			//OK, ‰‰nestet‰‰n anonyymisti.
			String kasky = "INSERT INTO Aani(AanestysID, VaihtoehtoID, AanestajaID) VALUES(?,?,?)";
			jdbcTemplate.update(kasky, Aani.getAanestysID(), Aani.getVaihtoehtoID(), 1);	
			
			String kasky3 = "UPDATE Lupa SET Aanestanyt=true WHERE AanestysID=? AND AanestajaID= ?";
			jdbcTemplate.update(kasky3, Aani.getAanestysID(),kayttajaID);
		}else{
			//Kusimutteri fuskaa, ‰‰nelle asetetaan t‰m‰n ID.
			String kasky = "INSERT INTO Aani(AanestysID, VaihtoehtoID, AanestajaID) VALUES(?,?,?);";
			jdbcTemplate.update(kasky, Aani.getAanestysID(), Aani.getVaihtoehtoID(), kayttajaID);	
			
			String kasky3 = "UPDATE Lupa SET Aanestanyt=true WHERE AanestysID=? AND AanestajaID= ?";
			jdbcTemplate.update(kasky3, Aani.getAanestysID(), kayttajaID);
		}
		
		
	}
	
	public boolean tarkistaFusku(int KayttajaID, int VaihtoehtoID){
		boolean onkoOK = true;
		String tarkastuskasky1 = "SELECT RyhmaID FROM Vaihtoehto WHERE VaihtoehtoID=?";
		String tarkastuskasky2 = "SELECT RyhmaID FROM Aanestaja WHERE AanestajaID=?";
		int VaihtoehtoRyhma = jdbcTemplate.queryForInt(tarkastuskasky1,VaihtoehtoID);
		int AanestajaRyhma = jdbcTemplate.queryForInt(tarkastuskasky2,KayttajaID);
		if(VaihtoehtoRyhma==AanestajaRyhma){
			onkoOK=false;
		}
		
		return onkoOK;
	}
	
	public List<Aanestaja> listaaKusimutterit(){
	String hakukasky = "SELECT k.Etunimi, k.Sukunimi, r.RyhmaNimi FROM Aani a JOIN Aanestaja k ON a.AanestajaID=k.AanestajaID JOIN Ryhma r ON k.RyhmaID=r.RyhmaID WHERE a.AanestajaID>2 ";
		List<Aanestaja> kusimutterit = jdbcTemplate.query(hakukasky, new RowMapper<Aanestaja>() {
		
			public Aanestaja mapRow(ResultSet rs, int rowNum) throws SQLException {
				Aanestaja a = new AanestajaImpl();
				Ryhma r = new RyhmaImpl();
				a.setEtunimi(rs.getString("Etunimi"));
				a.setSukunimi(rs.getString("Sukunimi"));
				r.setRyhmaNimi(rs.getString("RyhmaNimi"));
				a.setRyhma(r);
			
				return a;
			}
		});
	
		return kusimutterit;
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
	
	
	public int haeLupaSumma(int AanestysID){
		String hakukasky = "SELECT COUNT(AanestysID) AS 'Lupasumma' FROM Lupa WHERE AanestysID=?";
		int summa = jdbcTemplate.queryForObject(hakukasky, new Object[]{AanestysID}, Integer.class);	
		
		return summa;
	}
	
	public int haeAnnettujenAanienMaara(int AanestysID){
		String hakuOrderi = "SELECT COUNT(AanestysID) AS 'Annettuja ‰‰ni‰' FROM Lupa WHERE AanestysID=? AND Aanestanyt=1";
		return jdbcTemplate.queryForObject(hakuOrderi, new Object[]{AanestysID}, Integer.class);	
		
	}
}
