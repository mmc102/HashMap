/** 
* stores tdhe key value pairs for the hash table. 
*/
public class HashNode {
    
   

     /**
      * instance variable that stores an int key 
      */
     private int intKey;

      /**
       * instance vairable that stores the int value 
       */
     private int intValue;

     

      /**
       * constructor that takes ints and updates instance variables 
       * @param key  key in the key value pair 
       * @param value value in the key value pair 
       */
      HashNode(int key, int value){
        this.intKey = key;
        this.intValue = value;
      }

      // gettters
      /**
       * gets the value, returns it
       * @return value
       */
      public int getIntValue(){
          return this.intValue;
      }

      /**
       * gets the key, returns it 
       * @return key 
       */
      public int getIntKey(){
          return this.intKey;
      }
             

}

