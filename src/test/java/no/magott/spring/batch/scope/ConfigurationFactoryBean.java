package no.magott.spring.batch.scope;
import org.springframework.beans.factory.FactoryBean;


public class ConfigurationFactoryBean implements FactoryBean<Config> {

    private String magicString;
    
    public void setMagicString(String magicString) {
        this.magicString = magicString;
    }

    public Config getObject() throws Exception {
        return new Config(magicString);
    }

    public Class<?> getObjectType() {
        return Config.class;
    }

    public boolean isSingleton() {
        // TODO Auto-generated method stub
        return false;
    }
    
    
    
}
