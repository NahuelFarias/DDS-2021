package domain.models.entities.mascotas;

import services.EditorDeFotos;

public class Foto {

    private String URLfoto;

    public Foto editarFoto(){
        EditorDeFotos editor = new EditorDeFotos();
        return editor.ajustarCalidad(this);
    }

    public String getURLfoto() {
        return URLfoto;
    }

    public void setURLfoto(String URLfoto) {
        this.URLfoto = URLfoto;
    }
}
