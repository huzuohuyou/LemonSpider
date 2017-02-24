package com.lemon.javacv;

  
/** 
 *  
 * Use JavaCV/OpenCV to capture camera images 
 *  
 * There are two functions in this demo: 
 * 1) show real-time camera images  
 * 2) capture camera images by mouse-clicking anywhere in the JFrame,  
 * the jpg file is saved in a hard-coded path.  
 *  
 * @author ljs 
 * 2011-08-19 
 * 
 */  
public class CopyOfImageSet2Video {  
    private static final String dir = "./test-images/";
    
//    public static void main(String[] args) throws Exception {  
//        //open camera source  
//        OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);  
//        grabber.start();  
//        
//        Frame frame = grabber.grab();  
//        int width = frame.imageWidth;  
//        int height = frame.imageHeight;  
//        
//        Frame frame2 = new STFrame(dir); 
//        frame2.image = frame.image;
//        
//        String outmp4 = dir + "3.mp4";
//        FFmpegFrameRecorder videoRecorder = new FFmpegFrameRecorder(outmp4, width, height, 1);
////        videoRecorder.setFormat(recorderParameters.getVideoOutputFormat());
////        videoRecorder.setSampleRate(recorderParameters.getAudioSamplingRate());
////        videoRecorder.setFrameRate(recorderParameters.getVideoFrameRate());
////        videoRecorder.setVideoCodec(recorderParameters.getVideoCodec());
////        videoRecorder.setVideoQuality(recorderParameters.getVideoQuality());
////        videoRecorder.setAudioQuality(recorderParameters.getVideoQuality());
////        videoRecorder.setAudioCodec(recorderParameters.getAudioCodec());
//        videoRecorder.setVideoBitrate(1000000);
//        videoRecorder.setAudioBitrate(64000);
//        videoRecorder.start();
//        
//        int tick = 0;
//        //real-time image display
//        while(++tick < 200) {
//            videoRecorder.record(frame2);
//        }  
//          
//        System.out.println("bye");
//        //release resources  
////        opencv_highgui.cvRelease(image);
//        grabber.stop();
//        videoRecorder.stop();
//    }  
//  
}