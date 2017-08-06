package nuncajamas.capitangarfio.model.services;


public class Identifier {

    private String id;
    private String version;

    public void setId(String id) {
        this.id = id;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getId() {
        return id;
    }

    public String getVersion() {
        return version;
    }

    public static class IdentifierBuilder{
        private Identifier instance;

        private IdentifierBuilder() {
        }

        public static IdentifierBuilder builder(){
            return new IdentifierBuilder();
        }

        public IdentifierBuilder id(String id){
            instance.setId(id);
            return this;
        }

        public IdentifierBuilder version (String version){
            instance.setVersion(version);
            return this;
        }

        public Identifier build(){
            return instance;
        }
    }
}
