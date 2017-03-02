package com.lemon.spider.kqyxyj;

import com.lemon.commons.ThreadSafeSleep;

/**
 * Created by bob on 2017/1/13.
 */
public class SpiderKQYXYJ {

    public static int PMC_Start = 32;
    public static int PMC_Stop =  950;
    public static String WWWROOTDir = "/Users/bob/Sites/med/";
    public static  String UrlPrefix4Spider = "/Jwk_kqyxyj/CN/abstract/abstract%d.shtml";
    public final static long APP_Start = System.currentTimeMillis();
//    public static ApplicationContext context = null;

    public static void main(String[] args) throws Exception {
        System.out.println("\nusage:\njava -jar lemontools.jar [start] [stop] [root] [threads] [left]");

        if(args.length < 1) {
            //System.out.println("please check your input.");
            //return;
//            Scanner scanner = new Scanner(System.in);
//            args =new  String[5];
//            System.out.println("please input [start]");
//            args[0]=scanner.nextLine();
//            System.out.println("please input [stop]");
//            args[1]=scanner.nextLine();
//            System.out.println("please input [root]");
//            args[2]=scanner.nextLine();
//            System.out.println("please input [threads]");
//            args[3]=scanner.nextLine();
//            System.out.println("please input [left]");
//            args[4]=scanner.nextLine();
        }
        int threads = 5;
        int left = -1;

        if(args.length >= 1) {
            PMC_Start = Integer.parseInt(args[0]);
        }
        if(args.length >= 2) {
            PMC_Stop = Integer.parseInt(args[1]);
        }
        if(args.length >= 3) {
            WWWROOTDir = args[2];
        }
        if(args.length >= 4) {
            threads = Integer.parseInt(args[3]);
        }
        if(args.length >= 5) {
            left = Integer.parseInt(args[4]);
        }

        System.out.println("\nusage:\njava -jar lemontools.jar start=" + PMC_Start + " stop=" + PMC_Stop + " root=" + WWWROOTDir + " threads=" + threads + " left=" + left);

        try {
//            context = new FileSystemXmlApplicationContext("classpath*:applicationContext.xml");
            if(left >= 0 && left < threads) {
                ParserPaperKQYXYJ main = new ParserPaperKQYXYJ(threads, left);
                main.start();
                ThreadSafeSleep.sleep(4000);
            } else {
                for (int i = 0; i < threads; i++) {
                    ParserPaperKQYXYJ main = new ParserPaperKQYXYJ(threads, i);
                    main.start();
                    ThreadSafeSleep.sleep(4000);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
