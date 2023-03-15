/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatnt.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author HAU NUONG MO HANH
 */
public class CartObject implements Serializable{
    private Map<String, Integer> items;

    public Map<String, Integer> getItems() {
        return items;
    }    
    
    
    public void addItemToCart(String id){
        //1. Check item's id is existed
        if(id == null){
            return;
        }
        if(id.trim().isEmpty()){
            return;
        }
        //2. when item is existed, checking existed items
        if(this.items == null){
            this.items = new HashMap<>();
        }
        
        //3. when items has existed, checking existed id
        int quantity = 1;
        
        if(this.items.containsKey(id)){
            quantity = this.items.get(id) + 1;
        }
        
        //4. update items
        
        this.items.put(id, quantity);
    }
    
    public void removeItemFromCart(String id){
        
        if(id == null){
            return;
        }
        
        if(id.trim().isEmpty()){
            return;
        }
        //1. checking if cart existed
        if(this.items == null){
            return;
        }
        
        //2. when items existed, check id 
        if(this.items.containsKey(id)){
            this.items.remove(id);
            if(this.items.isEmpty()){
                this.items = null;
            }
        }
        
        
    }
    
//    public void addRemoveItemToTempCart(String id){
//        //1. Check item's id is existed
//        if(id == null){
//            return;
//        }
//        if(id.trim().isEmpty()){
//            return;
//        }
//        //2. when item is existed, checking existed items
//        if(this.items == null){
//            this.items = new HashMap<>();
//        }
//        
//        //3. when items has existed, checking existed id
//        int quantity = 1;
//        
//        if(this.items.containsKey(id)){
//            quantity = this.items.get(id) + 1;
//        }
//        
//        //4. update items
//        
//        this.items.put(id, quantity);
//    }
}
