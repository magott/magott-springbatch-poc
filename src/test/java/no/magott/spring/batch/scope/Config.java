package no.magott.spring.batch.scope;

public class Config {

    private String magicString;
    
    public Config(String magicString){
        this.magicString= magicString;
    }
    
    public String getMagicString() {
        return magicString;
    }
    
}
