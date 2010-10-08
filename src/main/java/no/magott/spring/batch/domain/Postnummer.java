package no.magott.spring.batch.domain;

public class Postnummer {
    private long id;
    private String postnummer;
    private String poststed;

    public Postnummer(String postnummer, String poststed) {
        this(0, postnummer, poststed);
    }
   
    public Postnummer(long id, String postnummer, String poststed) {
        this.id = id;
        this.postnummer = postnummer;
        this.poststed = poststed;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Postnummer [id=");
        builder.append(id);
        builder.append(", postnummer=");
        builder.append(postnummer);
        builder.append(", poststed=");
        builder.append(poststed);
        builder.append("]");
        return builder.toString();
    }

    public long getId() {
        return id;
    }

    public String getPostnummer() {
        return postnummer;
    }

    public String getPoststed() {
        return poststed;
    }
    
    
}
