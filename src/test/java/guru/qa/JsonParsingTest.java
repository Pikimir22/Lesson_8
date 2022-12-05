package guru.qa;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.qa.model.Strukture;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;


public class JsonParsingTest {

    @Test
    void jsonParsTest() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        File file = new File("json/test.json");
        Strukture strukture = mapper.readValue(file,Strukture.class);

        assertThat(strukture.getID()).contains("SGML");
        assertThat(strukture.getTitle()).contains("example glossary");


    }
}
