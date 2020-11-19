package chaoking.java.allinone.learn.zookeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class MyZookeeperWatch implements Watcher {
    //定义链接地址
    private static String ADDRESS = "127.0.0.1:2181";
    //超时时间
    private static int TIMEOUT = 2000;

    ZooKeeper zk;
    //阻塞用户线程，用户必须等待连接成功
    private CountDownLatch countDownLatch = new CountDownLatch(1);

    public void createZkConnection(String address, int timeout){
        //第三个参数是事件通知。这里用的是本类，自定义的事件通知
        try {
            zk = new ZooKeeper(address, timeout, this);
            countDownLatch.countDown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 创建节点内容
     * @param path
     * @param data
     * @return
     */
    public boolean createNode(String path, String data){
        //第三个参数表示权限的，这里开放所有权限，不限制服务器
        //第四个参数表示节点的类型。用的持久节点
        try {
            //开启监听的方法。第二个参数表示是否开启监听
            zk.exists(path, true);
            String result = zk.create(path, data.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            System.out.println("创建节点成功！节点为："+path+",值为:"+data);
            System.out.println("创建结果为:"+result);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 修改节点内容
     * @param path
     * @param data
     * @return
     */
    public boolean updateNode(String path, String data){
        //第三个参数表示权限的，这里开放所有权限，不限制服务器
        //第四个参数表示节点的类型。用的持久节点
        try {
            //开启监听的方法。第二个参数表示是否开启监听
            zk.exists(path, true);
            //第三个参数表示版本号。  zk的数据版本默认从0开始，每次修改都会加1.   -1严格来说属于不合法的版本号。表示从最新版本进行更新
            Stat result = zk.setData(path, data.getBytes(), -1);
            System.out.println("修改节点成功！节点为："+path+",修改后为值为:"+data);
            System.out.println("修改节点成功，result:"+result);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteNode(String path){
        //第二个参数表示版本号
        try {
            //开启监听的方法。第二个参数表示是否开启监听
            zk.exists(path, true);
            //第二个参数表示版本号。 zk的数据版本默认从0开始，每次修改都会加1.   -1严格来说属于不合法的版本号。表示从最新版本进行更新
            zk.delete(path, -1);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println("事件通知开始前");
        //事件路径
        String path = event.getPath();
        //事件状态  即  连接不连接
        KeeperState state = event.getState();
        //事件类型
        EventType type = event.getType();
        System.out.println("事件路径"+path+",事件状态"+state+",事件类型"+type);
        if(KeeperState.SyncConnected == state){
            //事件类型  None表示是连接类型
            if(EventType.None == type){
                System.out.println("连接类型");
                //连接上zk服务器后放开信号量
                countDownLatch.countDown();
                System.out.println("=====ZK连接成功=====");
            }else if(EventType.NodeCreated == type){
                System.out.println("=====新增节点成功=====path:"+path);
            }else if(EventType.NodeDataChanged == type){
                System.out.println("=====修改节点成功=====path:"+path);
            }else if(EventType.NodeDeleted == type){
                System.out.println("=====删除节点成功=====path:"+path);
            }
        }
        System.out.println("事件通知开始后");
    }


    public static void main(String[] args) {
        MyZookeeperWatch w = new MyZookeeperWatch();
        w.createZkConnection(ADDRESS, TIMEOUT);
        //w.createNode("/zk01", "zk01-value");
        //w.updateNode("/zk01", "zk01-value2");
        w.deleteNode("/zk01");
    }
}
