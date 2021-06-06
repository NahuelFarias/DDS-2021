package domain;

import domain.models.entities.EditorDeFotos;
import org.junit.Assert;
import org.junit.Test;
import java.io.File;

public class EditorDeFotosTest {

    @Test
    public void creaFotoConNuevasDimensiones(){
        EditorDeFotos editorDeFotos = new EditorDeFotos();
        File fotoRedimensionada = new File("src/main/resources/FotoRedimensionada.jpg");

        editorDeFotos.ajustarCalidad();
        Assert.assertTrue(fotoRedimensionada.exists());
    }
}