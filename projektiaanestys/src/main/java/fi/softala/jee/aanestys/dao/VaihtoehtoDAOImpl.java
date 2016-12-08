package fi.softala.jee.aanestys.dao;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLFeatureNotSupportedException;
import java.util.List;
import java.util.logging.Logger;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void insert(Vaihtoehto vEhto) {
			String sql = "INSERT INTO Vaihtoehto (AanestysID, VaihtoehtoNimi, RyhmaID)" + " VALUES (?, ?, ?)";
			jdbcTemplate.update(sql, vEhto.getAanestysID(), vEhto.getVaihtoehtoNimi(), vEhto.getRyhmaTunnus());	
		
	}

	public void delete(int VaihtoehtoID) {
		String sql = "DELETE FROM Vaihtoehto WHERE VaihtoehtoID = ?";
		jdbcTemplate.update(sql, VaihtoehtoID);
		
	}
	
	public void deletet(int AanestysID) {
		String sql = "DELETE FROM Vaihtoehto WHERE AanestysID = ?";
		jdbcTemplate.update(sql, AanestysID);
		
	}
	
	public void poistaRyhmanVaihtoehdot(int ryhmaID){
		String poistokasky = "DELETE FROM Vaihtoehto WHERE RyhmaID=? ";
		jdbcTemplate.update(poistokasky, ryhmaID);
		
	}

	public Vaihtoehto get(final int Vaihtoehto) {
		String sql = "SELECT * FROM Vaihtoehto WHERE VaihtoehtoID =" + Vaihtoehto;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Vaihtoehto>() {
			
			public Vaihtoehto extractData(ResultSet rs) throws SQLException, DataAccessException {

				if(rs.next()){
					Vaihtoehto vEhto = new VaihtoehtoImpl();
					vEhto.setVaihtoehtoID(Vaihtoehto);
					vEhto.setAanestysID(rs.getInt("AanestysID"));
					vEhto.setVaihtoehtoNimi(rs.getString("VaihtoehtoNimi"));
					return vEhto;
				}
				return null;
			}
		});
			
	}

	public List<Vaihtoehto> haeVaihtoehdot(int AanestysID) {
		String sql = "SELECT * FROM Vaihtoehto WHERE AanestysID ="+AanestysID;
		List<Vaihtoehto> listaavEhto = jdbcTemplate.query(sql, new RowMapper<Vaihtoehto>() {
			
			
			public Vaihtoehto mapRow(ResultSet rs, int rowNum) throws SQLException {
				Vaihtoehto vEhto = new VaihtoehtoImpl();
				
				vEhto.setVaihtoehtoID(rs.getInt("vaihtoehtoID"));
				vEhto.setAanestysID(rs.getInt("aanestysID"));
				vEhto.setVaihtoehtoNimi(rs.getString("vaihtoehtoNimi"));
				
				return vEhto;
		
			}
		});
		
		return listaavEhto;
		
		
	}
}
