/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.accesodatos;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.codecs.configuration.CodecProvider;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

/**
 *
 * @author Zaira
 */
public class ManejadorConexiones {
    public static final String CADENA_CONEXION = "mongodb://localhost:27017";
    public static final String BASE_DATOS = "GestorDeHorarios";
    
    public static MongoClient crearConexion(){
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder()
                .automatic(true)
                .build();
        
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
       
        MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(new ConnectionString(CADENA_CONEXION))
            .codecRegistry(pojoCodecRegistry)
            .build();

        return MongoClients.create(settings);
    }
}
