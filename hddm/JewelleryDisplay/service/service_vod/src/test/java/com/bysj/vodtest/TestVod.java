package com.bysj.vodtest;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import org.junit.Test;

import java.util.List;

public class TestVod {
    public static void main(String[] args) throws ClientException {
//

        //账号AK信息请填写(必选)
         String accessKeyId = "LTAI5tRN73U6T6roCgcWKXw1";
        //账号AK信息请填写(必选)
         String accessKeySecret = "Yaaz7yYhRxnzgOXPwBiFku7d6eCZDM";


        //1.音视频上传-本地文件上传
        //视频标题(必选)
        String title = "3 - How Does Project Submission Work - upload by sdk";
        //本地文件上传和文件流上传时，文件名称为上传文件绝对路径，如:/User/sample/文件名称.mp4 (必选)
        //文件名必须包含扩展名
        String fileName = "D:/6 - What If I Want to Move Faster.mp4";
        //本地文件上传
        UploadVideoRequest request = new UploadVideoRequest(accessKeyId, accessKeySecret, title, fileName);
        /* 可指定分片上传时每个分片的大小，默认为1M字节 */
        request.setPartSize(1 * 1024 * 1024L);
        /* 可指定分片上传时的并发线程数，默认为1，(注：该配置会占用服务器CPU资源，需根据服务器情况指定）*/
        request.setTaskNum(1);
    /* 是否开启断点续传, 默认断点续传功能关闭。当网络不稳定或者程序崩溃时，再次发起相同上传请求，可以继续未完成的上传任务，适用于超时3000秒仍不能上传完成的大文件。
        注意: 断点续传开启后，会在上传过程中将上传位置写入本地磁盘文件，影响文件上传速度，请您根据实际情况选择是否开启*/
        request.setEnableCheckpoint(false);

        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadVideoResponse response = uploader.uploadVideo(request);
        System.out.print("RequestId=" + response.getRequestId() + "\n");  //请求视频点播服务的请求ID
        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else {
            /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }

    }
    //根据视频id获取播放的地址
//        //创建初始化对象
//        DefaultAcsClient client = initObject.initVodClient("LTAI5tRN73U6T6roCgcWKXw1", "Yaaz7yYhRxnzgOXPwBiFku7d6eCZDM");
//
//        //创建获取视频地址request和response对象
//        GetPlayInfoRequest request = new GetPlayInfoRequest();
//
//        GetPlayInfoResponse response = new GetPlayInfoResponse();
//
//        request.setVideoId("aa1d5320bdcc71ed8ead6732b68e0102");
//
//        response=client.getAcsResponse(request);
//
//        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
//
//        for (GetPlayInfoResponse.PlayInfo playInfo:playInfoList){
//            System.out.print("PlayInfo.PlayURL = "+playInfo.getPlayURL()+"\n");
//        }
//
//        System.out.print("VideoBase.Title = "+response.getVideoBase().getTitle()+"\n");


}
