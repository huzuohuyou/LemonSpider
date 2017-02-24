package com.lemon.javacv;

import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacpp.opencv_highgui;
import org.bytedeco.javacpp.opencv_highgui.CvVideoWriter;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.FrameRecorder;
import org.bytedeco.javacv.OpenCVFrameRecorder;
//static imports
//import static org.bytedeco.javacpp.opencv_highgui.cvLoadImage;
//import static org.bytedeco.javacpp.opencv_highgui.cvSaveImage;

//non-static imports

public class Main {

	//color range of red like color
	public static void main(String[] args) {
		String dir = "./test-images/";
		Main m = new Main();
		m.test(dir);
	}
	private static final int Width = 1280;
	private static final int Height = 720;
	
		
	public void test(String dir) {
		//read image
		IplImage img = opencv_highgui.cvLoadImage(dir+"1.bmp");
		System.out.println("step 1");
		//save
		opencv_highgui.cvSaveImage(dir+"threshold.jpg", img);
		System.out.println("step 2");
		
		final CanvasFrame canvas = new CanvasFrame("Demo");
//        canvas.showImage(img);
        canvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        
		//convert to video
		FrameRecorder recorder = new OpenCVFrameRecorder("out.mp4", Width, Height);
		
		CvVideoWriter writer = new CvVideoWriter();
		//Source: JavaCV Installation Tutorial -Part02 
		// http://www.youtube.com/watch?v=-RqompUUX2o&noredirect=1 

	}
	
}