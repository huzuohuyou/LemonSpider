package com.lemon.javacv;

import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacpp.opencv_highgui;
//static imports
//import static org.bytedeco.javacpp.opencv_highgui.cvLoadImage;
//import static org.bytedeco.javacpp.opencv_highgui.cvSaveImage;

//non-static imports

public class Main2 {

	//color range of red like color
	public static void main(String[] args) {
		String dir = "C:/Users/Administrator/Desktop/test-images/";
		Main2 m = new Main2();
		m.test(dir);
	}
		
		
	public void test(String dir) {
		//read image
		IplImage img = opencv_highgui.cvLoadImage(dir+"1.bmp");
		System.out.println("step 1");
		//save
		opencv_highgui.cvSaveImage(dir+"threshold.jpg", img);
		System.out.println("step 2");
		//convert to video
//		FrameRecorder recorder = new OpenCVFrameRecorder("out.avi", 320, 240);
//
//		//Source: JavaCV Installation Tutorial -Part02 
//		// http://www.youtube.com/watch?v=-RqompUUX2o&noredirect=1 

	}
	
}