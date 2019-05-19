package com.exam.utlis;


import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.sun.deploy.net.HttpResponse;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import javax.servlet.http.HttpServletResponse;

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

            ftp.setDefaultTimeout(10 * 60 * 1000);
            // 设置连接超时时间
            ftp.setConnectTimeout(10 * 60 * 1000);
            // 设置数据超时时间
            ftp.setDataTimeout(10 * 60 * 1000);

            ftp.connect(host, port);// 连接FTP服务器
            // socket连接，设置socket连接超时时间
            ftp.setSoTimeout(10 * 60 * 1000);

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

                        FTPFile[] files = ftp.listFiles();

                        for (FTPFile f : files){

                            if (f.isFile()){
                                //System.out.println(f.getName()+"是文件");
                                ftp.deleteFile(dirPath+dirName+"/"+f.getName());

                            }else if (f.isDirectory()){
                                //System.out.println(f.getName()+"是文件夹");
                                ftp.removeDirectory(dirPath+dirName+"/"+f.getName());
                            }
                        }
                        ftp.removeDirectory(dirPath+dirName);
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

    /**
     *实现文件打包压缩下载
     * @param response 视图
     * @param zipName 压缩包的文件名不含.zip
     * @param dirPath ftp文件路径
     * @param mapList <ftp文件名:ftpName,下载文件名:downName 》集合
     *
     */
    public static void zipDownloadFile(HttpServletResponse response, String zipName, String dirPath, List<Map<String,String>> mapList) {


        byte[] buf = new byte[1024];

        try {

            ftp=openFTP();

            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);

            ftp.changeWorkingDirectory(dirPath);

            File zipFile=null;
            String os = System.getProperty("os.name");
            if(os.toLowerCase().startsWith("win")){
                System.out.println(os+ " 采用windows路径");
                zipFile = new File("D:/tempFile");//windows作为服务器
            }else {
                System.out.println(os+ " 采用linux路径");
                zipFile = new File("/home/tempFile");//linux作为服务器
            }


            ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));

            for (int i=0;i< mapList.size();i++) {

                Map<String,String> map=mapList.get(i);

                String answerPath=map.get("answerPath");
                String ftpName = answerPath.substring(answerPath.lastIndexOf("/") + 1);
                String suffix=ftpName.substring(ftpName.lastIndexOf("."));
                String downName=map.get("stuNumber")+suffix;

               // System.out.println("ftp名称"+ftpName+" 下载名称"+downName);

                ZipEntry entry = new ZipEntry(downName);
                zipOut.putNextEntry(entry);

                InputStream bis = ftp.retrieveFileStream(ftpName);

                if (bis != null) {
                    int readLen = -1;
                    while ((readLen = bis.read(buf, 0, 1024)) != -1) {
                        zipOut.write(buf, 0, readLen);
                    }
                    zipOut.closeEntry();
                    bis.close();
                    ftp.completePendingCommand();
                    //调用ftp.retrieveFileStream这个接口后，一定要手动close掉返回的InputStream，然后再调用completePendingCommand方法
                    // ，若不是按照这个顺序，则会导致后面对FTPClient的操作都失败
                }
            }
            zipOut.close();
            ftp.logout();
            //下载
            int len;
            FileInputStream zipInput =new FileInputStream(zipFile);

            OutputStream out = response.getOutputStream();
            response.setContentType("application/octet-stream");
            response.addHeader("Content-Disposition", "attachment; filename="
                    + URLEncoder.encode(zipName, "UTF-8") + ".zip");
           // OutputStream out=new FileOutputStream(new File("D:/HOME/test.zip"));

            while ((len=zipInput.read(buf))!= -1){
                out.write(buf,0,len);
            }
            zipInput.close();
            out.flush();
            out.close();
            //删除压缩包
            zipFile.delete();

            //关闭ftp
            closeFTP(ftp);

        } catch (Exception e) {
            // logger.error("文件打包下载有误: " + e.getLocalizedMessage());
            System.out.println("文件打包下载有误: " + e.getLocalizedMessage());
            e.printStackTrace();
        }

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
