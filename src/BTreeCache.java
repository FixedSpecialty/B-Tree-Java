import java.util.LinkedList;

public class BTreeCache <T> 
{
	private LinkedList<T> cache;
	private final int CACHE_SIZE;
	private int hits;
	private int miss;
	private int ref;
	public BTreeCache(int CACHE_SIZE)
	{
		this.CACHE_SIZE = CACHE_SIZE;
		cache = new LinkedList<T>();
	}
	public void clearCache()
	{
		cache.clear();
	}

	public void increaseHit()
	{
		hits++;
	}
	public void increaseMiss()
	{
		miss++;
	}
	public int getReferences()
	{
		ref = hits+miss;
		return ref;
	}
	public int getHit()
	{
		return hits;
	}
	public int getMiss()
	{
		return miss;
	}
	public int getSize()
	{
		return cache.size();
	}

}
