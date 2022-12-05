package guru.qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FilesParsingTest {

    ClassLoader cl = FilesParsingTest.class.getClassLoader();


    @Test
    void pdfTest() throws Exception {
        open("https://obshestvo-sytyh.ru/#2");
        File donwnloadPdf = $("a[href='https://drive.google.com/file/d/1cxT3T7VG1plN8RRNmw1FRkafwAl399R_/view']").download();
        PDF conntent = new PDF(donwnloadPdf);
        assertThat(conntent.text).contains("Гг. Гости и прекрасныя Гостьи!");

    }

    @Test
    void xslTest() throws Exception {
        try (InputStream res = cl.getResourceAsStream("files/sample-xlsx-file.xlsx")) {
            XLS content = new XLS(res);
            assertThat(content.excel.getSheetAt(0).getRow(20).getCell(6).getStringCellValue()).contains("Jeromy");

        }
    }

    @Test
    void csvTest() throws Exception {
        try (
                InputStream res = cl.getResourceAsStream("files/new_file.csv");
                CSVReader content = new CSVReader(new InputStreamReader(res))
        ) {
            List<String[]> contain = content.readAll();

            assertThat(contain.get(0)[0]).contains("Hello");
            assertThat(contain.get(1)[0]).contains("My");


        }
    }

    @Test
    void zipTest() throws Exception {

        ZipEntry entry;
        try (
                InputStream res = cl.getResourceAsStream("zip/sample.txt.zip");
                ZipInputStream zip = new ZipInputStream(res);
        ) {


            while ((entry = zip.getNextEntry()) != null) {
                if (entry.getName().contains(".pdf")) {
                    PDF conntent = new PDF(res);
                    assertThat(conntent.text).contains("Гг. Гости и прекрасныя Гостьи!");
                }

                if (entry.getName().contains(".xls")) {
                    XLS content = new XLS(res);
                    assertThat(content.excel.getSheetAt(0).getRow(20).getCell(6).getStringCellValue()).contains("Jeromy");

                }
                else {
                    CSVReader content = new CSVReader(new InputStreamReader(res));
                    List<String[]> contain = content.readAll();

                    assertThat(contain.get(0)[0]).contains("Hello");
                    assertThat(contain.get(1)[0]).contains("My");

                }
            }


        }
    }
}
