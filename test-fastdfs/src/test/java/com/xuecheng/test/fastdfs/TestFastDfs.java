package com.xuecheng.test.fastdfs;

import org.csource.fastdfs.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestFastDfs {

    @Test
    //上传文件
    public void testUpload(){

        try {
            //加载fastdfs-client.properties配置文件
            ClientGlobal.initByProperties("config/fastdfs-client.properties");
            //定义TrackerClient，用于请求TrackerServer
            TrackerClient trackerClient = new TrackerClient();
            //连接tracker
            TrackerServer trackerServer = trackerClient.getConnection();
            //获取Stroage
            StorageServer storeStorage = trackerClient.getStoreStorage(trackerServer);
            //创建stroageClient
            StorageClient1 storageClient1 = new StorageClient1(trackerServer,storeStorage);
            //向stroage服务器上传文件
            //本地文件的路径
            String filePath = "D:\\image_test\\scuec_scene.jpg";
            //上传成功后拿到文件Id
            String fileId = storageClient1.upload_file1(filePath, "jpg", null);
            //group1/M00/00/00/wKhlgF2MZGeALcZpAAZ50iLUdh0716.png
            //group1/M00/00/00/wKhlgF2MZnaADUXyAAZ50iLUdh0230.png
            System.out.println(fileId);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    //下载文件
    public void testDownload() {
        try {
            //加载fastdfs-client.properties配置文件
            ClientGlobal.initByProperties("config/fastdfs-client.properties");
            //定义TrackerClient，用于请求TrackerServer
            TrackerClient trackerClient = new TrackerClient();
            //连接tracker
            TrackerServer trackerServer = trackerClient.getConnection();
            //获取Stroage
            StorageServer storeStorage = trackerClient.getStoreStorage(trackerServer);
            //创建stroageClient
            StorageClient1 storageClient1 = new StorageClient1(trackerServer,storeStorage);
            byte[] bytes = storageClient1.download_file1("group1/M00/00/00/rBEADF6TEjuAK_E_AACOx4peEpE777.jpg");
            //使用输出流保存文件
            OutputStream outputStream = new FileOutputStream(new File("D:/image_test/scuec.jpg"));
            outputStream.write(bytes);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testQuery() throws Exception{
        //加载fastdfs-client.properties配置文件
        ClientGlobal.initByProperties("config/fastdfs-client.properties");
        //定义TrackerClient，用于请求TrackerServer
        TrackerClient trackerClient = new TrackerClient();
        //连接tracker
        TrackerServer trackerServer = trackerClient.getConnection();
        //获取Stroage
        StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
        StorageClient storageClient = new StorageClient(trackerServer,storageServer);

        FileInfo fileInfo = storageClient.query_file_info("group1", "M00/00/00/wKhlgF2MZGeALcZpAAZ50iLUdh0716.png");
        System.out.println(fileInfo);

    }
}
