package guru.qa.model;

public class Strukture {
    public String title;
    public String ID;
    public GlossDiv glossDiv;


    public static class GlossDiv {

    }

    public static class GlossSeeAlso {
        String[] b = {"glass", "xml"};
    }

    public String getTitle() {
        return title;
    }

    public String getID() {
        return ID;
    }

    public GlossDiv getGlossDiv() {
        return glossDiv;
    }
}

