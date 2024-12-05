public class Cache {
    private int[] dataCache;  // Data cache (simplified)
    private int cacheSize;     // Cache size (number of blocks)
    private int blockSize;     // Size of each cache block
    private int latency;       // Latency for cache access (in cycles)

    public Cache(int cacheSize, int blockSize, int latency) {
        this.cacheSize = cacheSize;
        this.blockSize = blockSize;
        this.latency = latency;
        this.dataCache = new int[cacheSize];
    }

    // Simulate cache access
    public int accessMemory(int address) {
        int cacheIndex = (address / blockSize) % cacheSize;
        // Simulate cache hit or miss
        if (dataCache[cacheIndex] == address) {
            return address;  // Cache hit
        } else {
            // Cache miss, we simulate fetching from memory
            // Here, simulate a delay (latency) for a cache miss
            return -1;  // Cache miss
        }
    }

    // Handle cache miss
    public void handleCacheMiss(int address) {
        int cacheIndex = (address / blockSize) % cacheSize;
        dataCache[cacheIndex] = address;  // Store new value in cache
    }
}
