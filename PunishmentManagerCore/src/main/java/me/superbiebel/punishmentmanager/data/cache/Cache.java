package me.superbiebel.punishmentmanager.data.cache;

public interface Cache {
    /**
     * @throws Exception concludes all the exception that might be thrown by different databases.
     */
    void init() throws Exception;
    /**
     * @throws Exception concludes all the exception that might be thrown by different databases.
     */
    void transfer() throws Exception;
    /**
     * @throws Exception concludes all the exception that might be thrown by different databases.
     */
    void close() throws Exception;
}
