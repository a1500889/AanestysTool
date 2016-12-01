package fi.softala.jee.aanestys.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Excelreader {
	
	@SuppressWarnings("deprecation")
	public static ArrayList<AanestajaImpl> lueExcel() throws IOException{
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setCurrentDirectory(null);
		int result = fileChooser.showOpenDialog(fileChooser);
		if (result == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = fileChooser.getSelectedFile();
		    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
		
		
		
		ArrayList<AanestajaImpl> aanestajat = new ArrayList<AanestajaImpl>();

			//Aseta tähän polku mistä excel-tiedosto luetaan!!
//		String excelFilePath = "C:/Users/a1500949/Documents/testi1.xlsx";

		FileInputStream inputStream = new FileInputStream(selectedFile);

		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = firstSheet.iterator();

		while (iterator.hasNext()) {

			Row nextRow = iterator.next();

			Iterator<Cell> cellIterator = nextRow.cellIterator();

			AanestajaImpl aanestaja = new AanestajaImpl();

			while (cellIterator.hasNext()) {

				Cell cell = cellIterator.next();
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:

					if (aanestaja.getEtunimi() == null) {
						aanestaja.setEtunimi(cell.getStringCellValue());

					} else {
						aanestaja.setSukunimi(cell.getStringCellValue());

					}
					break;
				}

				if (aanestaja.getEtunimi() != null) {
					if (aanestaja.getSukunimi() != null) {
						aanestajat.add(aanestaja);
					}
				}
			}

			workbook.close();
			inputStream.close();
		}
		
		return aanestajat;

	}
		else{
			return null ;
		}
	}
}


