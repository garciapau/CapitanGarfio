package nuncajamas.shop.controller;

import nuncajamas.shop.client.product.ProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Shop {

	@Autowired
	private ProductClient productClient;

	@RequestMapping(value="/getProducts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getProducts(Model model) {
		return productClient.products();
	}

	@RequestMapping(value="/governance", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> getGovernance() {
	    /*
            { "info":{
              "serviceId": "pepe",
              "version": "1.0"
              },
              "consumes":[
                  {"type": "service",
                   "channel": "API",
                   "id":"pepa",
                   "version": "1.0"},
                  {"type": "resource",
                   "channel": "TOPIC",
                  "id": "topic.anna",
                  "version": "1.0"}
              ],
               "produces":[
                  {"type":"resource",
                  "channel": "TOPIC",
                  "id": "topic.pau",
                  "version": "1.0"},
                  {"channel": "API",
                   "type": "service",
                  "id":"pepa",
                  "version": "1.0"}
              ]
            }
	     */
		Map<String, Object> response = new HashMap<>();
		Map<String, Object> info = new HashMap<>();
		info.put("serviceId", "shop");
		info.put("version", "1.0.0");
		response.put("info", info);

		Map<String, Object> consumes = new HashMap<>();
		consumes.put("type", "service");
		consumes.put("channel", "api");
		consumes.put("id", "product");
		consumes.put("version", "1.0.0");
		response.put("consumes", consumes);

		return response;
	}}
