/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.accesodatos;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author Zaira
 */
public interface IAccesoMongo {
    public abstract MongoDatabase recuperarBaseDatos(MongoClient cliente);
    public abstract MongoCollection recuperarColeccion(MongoDatabase baseDatos);
}
