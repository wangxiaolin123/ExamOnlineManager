package com.exam.utlis;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

/**
 * ftp上传下载工具类
 * <p>Title: FtpUtil</p>
 * <p>Description: </p>
 */
public class FtpUtil {


    private static FTPClient ftp=null;
    private static String host="47.102.124.211";
    private static Integer port=21;
    private static  String username="uftp";
    private static String password="0000";


    public static FTPClient openFTP(){

        //获取ftp客户端
        ftp = new FTPClient();
        //设置编码
        ftp.setControlEncoding("UTF-8");
        ftp.setCharset(Charset.forName("UTF-8"));

        try {
            int reply;
            ftp.connect(host, port);// 连接FTP服务器
            // 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
            ftp.login(username, password);// 登录
            reply = ftp.getReplyCode();
            //采用被动模式
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return null;
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return ftp;
    }

    public static void closeFTP(FTPClient ftp){
        if (ftp.isConnected()) {
            try {
                ftp.disconnect();
            } catch (IOException ioe) {
            }
        }
    }

    /**
     * Description: 向FTP服务器上传文件
     * @param filePath FTP服务器文件存放路径。文件的路径为basePath+filePath
     * @param filename 上传到FTP服务器上的文件名
     * @param input    输入流
     * @return 成功返回true，否则返回false
     */
    public static boolean uploadFile(String filePath, String filename, InputStream input) {
        boolean result = false;
        //获取ftp客户端
        FTPClient ftp = openFTP();
        if(ftp==null)
            return result;
        try {
            //切换到上传目录
            if (!ftp.changeWorkingDirectory(filePath)) {
                //如果目录不存在创建目录
                ftp.makeDirectory(filePath);
                ftp.changeWorkingDirectory(filePath);
            }
            //设置上传文件的类型为二进制类型
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            //上传文件
            if (!ftp.storeFile(filename, input)) {
                return result;
            }
            input.close();
            ftp.logout();
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
           closeFTP(ftp);
        }
        return result;
    }

    /**
     * Description: 从FTP服务器下载文件
     *
     * @param remotePath FTP服务器上的相对路径
     * @param fileName   要下载的文件名
     * @param outputStream 输出流
     * @return
     */
    public static boolean downloadFile(String remotePath,String fileName, OutputStream outputStream) {
        boolean result = false;
        FTPClient ftp = openFTP();
        if(ftp==null)
            return result;
        try {

            ftp.changeWorkingDirectory(remotePath);// 转移到FTP服务器目录
            FTPFile[] fs = ftp.listFiles();
            for (FTPFile ff : fs) {
                if (ff.getName().equals(fileName)) {
                    ftp.retrieveFile(ff.getName(), outputStream);
                    outputStream.close();
                }
            }

            ftp.logout();
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeFTP(ftp);
        }
        return result;
    }

    public static boolean deleteFile(String remotePath,String fileName) {
        boolean result = false;
        FTPClient ftp = openFTP();
        if(ftp==null)
            return result;
        try {
            ftp.changeWorkingDirectory(remotePath);// 转移到FTP服务器目录
            FTPFile[] fs = ftp.listFiles();
            for (FTPFile ff : fs) {
                if (ff.getName().equals(fileName)) {
                    ftp.deleteFile(fileName);
                }
            }
            ftp.logout();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeFTP(ftp);
        }
        return result;
    }

    /**
     * Description: 从FTP服务器删除文件夹
     * @param dirPath 被删除文件夹的父目录
     * @param dirName 被删除文件夹的名称
     * @return boolean
     */
    public static boolean deleteDirectory(String dirPath, String dirName) {
        boolean result = false;
        FTPClient ftp =openFTP();
        if(ftp==null)
            return result;
        try {
            //切换FTP目录，还是当前目录
                        ftp.changeWorkingDirectory(dirPath);
                        ftp.sendCommand("");
                    /*    FTPFile[] files = ftp.listFiles(dirName);
                        for (FTPFile f : files){
                            if (f.isFile()){
                                ftp.deleteFile(dirPath+dirName+"/"+f.getName());

                            }else if (f.isDirectory()){
                                ftp.removeDirectory(dirPath+dirName+"/"+f.getName());
                            }
                        }
                        ftp.removeDirectory(dirPath+dirName);*/
            ftp.logout();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return result;
    }

    public static void main(String[] args) throws Exception {

			/*FileInputStream in=new FileInputStream(new File("D:/HOME/T1.xlsx"));
			boolean flag = uploadFile("/public/", "T1.xlsx", in);
			System.out.println(flag);*/
        //boolean flag = downloadFile("47.102.124.211", 21, "uftp", "0000", "/public/", "T1.xlsx", "D:/HOME/T/");
        boolean flag = deleteDirectory("/public/","T2");

        System.out.println(flag);

    }
}
