package utils;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Locale;
import java.util.ResourceBundle;

public class JedisUtil {
    private static JedisPool pool = null;
    private static String pwd = null;
    static{
        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        ResourceBundle bundle =  ResourceBundle.getBundle("redis",new Locale("zh","CN"));
        int mixIdle = Integer.parseInt(bundle.getString("redis.mixIdle"));
        int maxIdle = Integer.parseInt(bundle.getString("redis.maxIdle"));
        int maxTotal = Integer.parseInt(bundle.getString("redis.maxTotal"));
        int maxWaitMillis = Integer.parseInt(bundle.getString("redis.maxWaitMillis"));
        int port = Integer.parseInt(bundle.getString("redis.port"));
        String  host =  bundle.getString("redis.host");
        pwd =  bundle.getString("redis.pwd");
        genericObjectPoolConfig.setMinIdle(mixIdle);
        genericObjectPoolConfig.setMaxTotal(maxTotal);
        genericObjectPoolConfig.setMaxIdle(maxIdle);
        genericObjectPoolConfig.setMaxWaitMillis(maxWaitMillis);
        pool = new JedisPool(genericObjectPoolConfig, host, port);
    }
    public static Jedis getConn(){
        Jedis resource = pool.getResource();
        return resource;
    }
    public void close(){
        pool.close();
    }

    public static void main(String[] args) {
        Jedis conn = getConn();
        conn.auth(pwd);
        conn.set("name","张飞");

    }
}
