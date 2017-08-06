package nuncajamas.capitangarfio.model.services;


import java.util.Optional;

public class Dependency {

    private String type;
    private String channel;
    private Identifier service;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Identifier getService() {
        return service;
    }

    public void setService(Identifier service) {
        this.service = service;
    }

    public static class DependencyBuilder {
        private Dependency instance;

        private DependencyBuilder() {
            instance = new Dependency();
        }

        public static DependencyBuilder builder(){
            return new DependencyBuilder();
        }

        public DependencyBuilder type(String type){
            instance.type=type;
            return this;
        }

        public DependencyBuilder channel(String channel){
            instance.setChannel(channel);
            return this;
        }

        public DependencyBuilder service(String id, Optional<String> version){
            Identifier identifier= Identifier.IdentifierBuilder.builder().id(id).version(version.orElse(null)).build();
            instance.setService(identifier);
            return this;
        }

        public DependencyBuilder service(Identifier service){
            instance.setService(service);
            return this;
        }

        public Dependency build(){
            return instance;
        }
    }
}
