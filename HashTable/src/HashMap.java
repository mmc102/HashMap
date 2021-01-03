import java.util.ArrayList;


public class HashMap {
    
    //the map itself 
    ArrayList<HashNode> hashMap = new ArrayList<HashNode>(10);


    //stores the loading factor and resizes when need be 
    //i.e .75 would be 75% full 
    private double loadingFactor;

    //maximum loading before a resize 
    private final double MAXLOADING = 0.65;

    //stores the number of items addded to determine the loading factor 
    private int itemsAdded = 0;

    //Constructor makes the inital hashMap
    HashMap(){

        //put a bunch of nulls in the hashmap 
        for(int i = 0; i <10; i++){
            hashMap.add(null);
        }
    }
    //Constructor makes the subsequent maps
    HashMap(int size){

        //put a bunch of nulls in the hashmap 
        for(int i = 0; i <size; i++){
            hashMap.add(null);
        }
    }

    /**
     * adds an item to the hash list 
     */
    public void addNode(int key, int value){

        //make a new node 
        HashNode node = new HashNode(key,value);

        //calculate the hash of the key 
        int hash = this.keyHasher(key);

        //try to put the item in the hashmap
        if(hashMap.get(hash) == null){
            hashMap.set(hash,node);
        }
        //linear probing 
        else{
            
            //keep trying until we find a location that is empty 
            //TODO add wrap around so we dont get out of bounds 
            while(hashMap.get(hash)!= null){
                hash++;
            }

            hashMap.set(hash, node);
        }


        this.itemsAdded ++;
        
        //if we are too full, expand the table 
        if (this.determineLoading() >= this.MAXLOADING){
            this.resizeTable();
        }
        
        
    }

   

    /**
     * doubles the size of the hashtable and rehashes all items from the previous table 
     */
    public void resizeTable(){
        
        System.out.println("resizing");

        //make a new instance of hashmap and use overloaded constructor to specify the size 
        HashMap newMap = new HashMap(2*this.hashMap.size());
        
        //reset the items added counter
        this.itemsAdded = 0;

        //transfer the items from the old table to the new one 
        for (HashNode node: this.hashMap){
            if(node!= null){
                newMap.addNode(node.getIntKey(), node.getIntValue());
            }
        }

        //set the current instance of the hashmap to the new instance of the hashmap 
        this.hashMap = newMap.hashMap;
    }

    public double determineLoading(){

        return (double) this.itemsAdded/hashMap.size();
    }

    public HashNode getNode(int key){
        
        //hash the key
        int hash = this.keyHasher(key);
        
        HashNode node = hashMap.get(hash);
        
        //double check for linear probing
        while(node.getIntKey() != key){
                hash ++;
                node = hashMap.get(hash);
        }
            
         
        System.out.println("FOUND IT! key:" + node.getIntKey() + " value:" + node.getIntValue());
        return node;
    }

    public int keyHasher(int key){
        
        //use modulo hash function 
        int hash = key % this.hashMap.size();

        return hash;
    }

    public void printMap(){
        for(HashNode node: this.hashMap){
            if(node == null){
                System.out.println("null");
            }
            else{
            System.out.println("key:" + node.getIntKey() + "value:" + node.getIntValue());
            }
        }
    }

    public static void main(String[] args) throws Exception {
        HashMap hashTable = new HashMap();

        

        hashTable.addNode(1,10);
        hashTable.addNode(2,20);
        hashTable.addNode(96 ,30);
        hashTable.addNode(46 ,30);
        

        hashTable.printMap();


        HashNode node = hashTable.getNode(96);
        HashNode node1 = hashTable.getNode(46);

        hashTable.addNode(3,10);
        hashTable.addNode(4,20);
        hashTable.addNode(95 ,30);
        hashTable.addNode(45 ,30);


        hashTable.printMap();


        HashNode node2 = hashTable.getNode(96);
        HashNode node3 = hashTable.getNode(46);
        
    }
}


