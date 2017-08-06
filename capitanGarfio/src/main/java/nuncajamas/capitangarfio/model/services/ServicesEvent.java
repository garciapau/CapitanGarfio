package nuncajamas.capitangarfio.model.services;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ServicesEvent {

    private Identifier collector;
    private List<Service> services;

    public Identifier getCollector() {
        return collector;
    }

    public void setCollector(Identifier collector) {
        this.collector = collector;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public static class ServicesEventBuilder{

        private final ServicesEvent instance;

        private ServicesEventBuilder() {
           this.instance= new ServicesEvent();
        }

        public static ServicesEventBuilder builder() {
            return new ServicesEventBuilder();
        }

        public ServicesEventBuilder collector(String id, Optional<String> version) {
            Identifier identifier= Identifier.IdentifierBuilder.builder().id(id).version(version.orElse(null)).build();
            instance.setCollector(identifier);
            return this;
        }

        public ServicesEventBuilder collector(Identifier collector) {
            instance.setCollector(collector);
            return this;
        }

        public ServicesEventBuilder services(List<Service> services) {
            instance.setServices(services);
            return this;
        }

        public ServicesEventBuilder services(Service... services) {
            instance.setServices(Arrays.asList(services));
            return this;
        }

        public ServicesEvent build(){
            return instance;
        }
    }
}
