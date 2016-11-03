package fi.softala.jee.aanestys.bean;

public class EnvBean {

    private String env;

    public EnvBean(String env){
    	this.env=env;
    }
    
    public EnvBean(){
    }
    
    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }
}
