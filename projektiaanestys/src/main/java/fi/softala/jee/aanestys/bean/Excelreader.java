package fi.softala.jee.aanestys.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Excelreader {
	
	@SuppressWarnings("deprecation")
	public static ArrayList<AanestajaImpl> lueExcel() throws IOException{
		ArrayList<AanestajaImpl> aanestajat = new ArrayList<AanestajaImpl>();

		String excelFilePath = "C:/Users/a1500889/git/AanestysTool/projektiaanestys/testi.xlsx";
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

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

}


