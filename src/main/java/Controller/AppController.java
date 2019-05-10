package Controller;

import Modelo.Item;
import Services.ItemService;

import java.util.ArrayList;

import static Services.ItemService.insertItem;

public class AppController {

    public Item getItem(String id) {

        return ItemService.getItemById(id);
    }

    public Item addItem(Item item) {
        return insertItem(item);
    }

    public Item updateItem(Item item) {
        return ItemService.updateItemById(item.getId(), item);
    }

    public String deleteItem(String id) {
        return ItemService.deleteItemById(id);
    }

    public ArrayList<Item> getItems() {
        return ItemService.getItems();
    }
}
