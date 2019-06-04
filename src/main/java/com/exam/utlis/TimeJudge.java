package com.exam.utlis;

import java.util.Date;

public class TimeJudge {


    /**
     * 判断输入时间与当前系统时间是否在某个时间段内，若在返回true
     * @param date
     * @param minutes
     * @return
     */
    public static boolean JudgeBucket(Date date,Integer minutes){

        long nd = 1000 * 24 * 60 * 60;

        long nh = 1000 * 60 * 60;

        long nm = 1000 * 60;

       // long ns = 1000;

        Date nowDate = new Date();

        long result= date.getTime()-nowDate.getTime();
        if(result<0)
            return false;

        long min = result % nd % nh / nm;

        //System.out.println("相差"+min+"分钟");

        if(minutes>=min)
            return true;

        return false;
    }


    public static void SleepTime(Date date){
        long millis;
        if(date==null){
            millis=0;
        }
        millis=Math.abs(new Date().getTime()-date.getTime());
        try {

            Thread.sleep(millis);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
