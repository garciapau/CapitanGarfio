package nuncajamas.capitangarfio.model.services;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Service {
    private Identifier serviceIdentifier;
    private List<Dependency> consumes;
    private List<Dependency> produces;

    public Identifier getServiceIdentifier() {
        return serviceIdentifier;
    }

    public void setServiceIdentifier(Identifier serviceIdentifier) {
        this.serviceIdentifier = serviceIdentifier;
    }

    public List<Dependency> getConsumes() {
        return consumes;
    }

    public void setConsumes(List<Dependency> consumes) {
        this.consumes = consumes;
    }

    public List<Dependency> getProduces() {
        return produces;
    }

    public void setProduces(List<Dependency> produces) {
        this.produces = produces;
    }

    public static class ServiceBuilder{
        private Service instance;

        private ServiceBuilder() {
            this.instance =new Service();
        }

        public static ServiceBuilder builder(){
            return new ServiceBuilder();
        }

        public ServiceBuilder serviceIdentifier(String id, Optional<String> version){
            Identifier identifier= Identifier.IdentifierBuilder.builder().id(id).version(version.orElse(null)).build();
            instance.setServiceIdentifier(identifier);
            return this;
        }

        public ServiceBuilder serviceIdentifier(Identifier serviceIdentifier){
            instance.setServiceIdentifier(serviceIdentifier);
            return this;
        }

        public ServiceBuilder consumes(Dependency... dependencies) {
            instance.setConsumes(Arrays.asList(dependencies));
            return this;
        }

        public ServiceBuilder consumes(List<Dependency> dependencies) {
            instance.setConsumes(dependencies);
            return this;
        }

        public ServiceBuilder produces(Dependency... dependencies){
            instance.setProduces(Arrays.asList(dependencies));
            return this;
        }

        public ServiceBuilder produces(List<Dependency> dependencies){
            instance.setProduces(dependencies);
            return this;
        }

        public Service build(){
            return instance;
        }
    }
}
