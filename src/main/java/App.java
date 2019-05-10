import Controller.AppController;
import Modelo.Item;
import Services.ItemService;
import com.google.gson.Gson;

import java.io.IOException;

import static Services.Connection.*;
import static Services.ItemService.*;
import static spark.Spark.*;

public class App {

    public static void main(String[] args) throws IOException {
        makeConnection();
        final AppController appController= new AppController();
        post("/item", ((request,response) -> {
            response.type("application/json");
            Item item=new Gson().fromJson(request.body(),Item.class);
            Item item1=  appController.addItem(item);

                response.status(201);
                return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,new Gson().toJsonTree(item1)));

        }));
        get("/item/:id",(((request, response) -> {
            response.type("application/json");
            String id = request.params("id");
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(appController.getItem(id))));
        })));

        put("/item", ((request, response) -> {
            response.type("application/json");
            Item item = new Gson().fromJson(request.body(),Item.class);
            Item itemEditado = appController.updateItem(item);
                return  new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
                        new Gson().toJsonTree(itemEditado)));

        }));

        delete("/item/:id",((request, response) -> {
            response.type("application/json");
                 if (appController.deleteItem(request.params(":id")) =="DELETED") {
                    return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, "Item borrado"));
                } else {
                     return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, "El item no se pudo borrar"));
                 }

        }));

        get("/item",(((request, response) -> {
            response.type("application/json");
            appController.getItems();
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, "Si"));
        })));

        /*
        System.out.println("Inserting a new Person with name Shubham...");
        Item item = new Item();
        item.setTitle("AutoFord");
        item = insertItem(item);
        System.out.println("Person inserted --> " + item);


        System.out.println("Changing name to `Shubham Aggarwal`...");
        item.setTitle("AutoBWM");

        updateItemById(item.getId(), item);
        System.out.println("Person updated  --> " + item);

        System.out.println("Getting Shubham...");
        Item itemFromDB = getItemById(item.getId());
        System.out.println("Person from DB  --> " + itemFromDB);

        System.out.println("Deleting Shubham...");
        deleteItemById(itemFromDB.getId());
        System.out.println("Person Deleted");
*/


    }
}
