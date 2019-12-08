import java.util.LinkedList;

/*
Cache generic object that has methods to get, add, remove objects, and to see if it contains, and can clear the cache
 */
public class Cache<T> {
    private LinkedList<T> cache;
    private int limit;

    //constructor for cache object
    public Cache(int cacheLimit){
        limit = cacheLimit;
        cache = new LinkedList<>();
    }

    //return the first object
    public T getObject(){
        T target = cache.getFirst();
        return target;
    }

    public T get(int i){
        return cache.get(i);
    }
    //adds passed in object to the top of the cache
    //checks the limit and if the cache already contains the object
    //will remove the object if it is already in the cache
    public void addObject(T bTreeObject){
        if(limit == 0)
            return;
            //if the cache is at maximum size
            if (cache.size() == limit) {
                //check if object is already in cache, if so move to top
                if (cache.contains(bTreeObject)) {
                    cache.remove(bTreeObject);
                } else {
                    cache.removeLast();
                }
                cache.addFirst(bTreeObject);
            } else { //if there is room in the cache
                //check if object is already in cache, if so move to top
                if (cache.contains(bTreeObject)) {
                    cache.remove(bTreeObject);
                }
                cache.addFirst(bTreeObject);
            }
    }

    //@Param T
    //takes in an object and checks if the cache contains it
    public boolean contains(T bTreeObject){
        if(cache.contains(bTreeObject)){
            return true;
        } else {
            return false;
        }
    }

    //@Param T
    //removes passed in object
    public void removeObject(T bTreeObject){
        cache.remove(bTreeObject);
    }

    //@Param T
    //clears the cache
    public void clearCache(T bTreeObject){
        cache.clear();
    }

    //for testing purposes
    @Override
    public String toString() {
        return cache.toString();
    }
}
