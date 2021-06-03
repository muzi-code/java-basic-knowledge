package com.github.dev.muzi.base.concurrent.knowledge.working.address_process;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author lifuyi8
 * @since 2021/3/8 4:26 下午
 */
public class Test00 {

    private static final String REGX = "(?<province>北京|广东|山东|江苏|河南|上海|河北|浙江|陕西|湖南|重庆|福建|天津|云南|四川|" +
            "广西|安徽|海南|江西|湖北|山西|辽宁|黑龙江|内蒙古|贵州|甘肃|青海|新疆|西藏|吉林|宁夏)(?<cityProperPrefix>[.*?]{0,5}市|[.*?]{0,5}区)";

//    public static void main(String[] args) throws Exception{
////        System.out.println("北京朝阳区五环到六环之间三间房乡天泰北双苑11号楼1106-A（华联惠生超市旁）".replaceAll("([【\\|（\\|(\\|《]{1}.*?[】\\|）\\|)\\|》]{1})", ""));
////        System.out.println("北京朝阳区五环到六环之间三间房乡天泰北双苑11号楼1106-A".replaceAll("\\pP|\\pS|\\pC|\\pZ", ""));
////        String a = "北京昌平区某某街道".replaceAll("(?<province>北京|广东|山东|江苏|河南|上海|河北|浙江|陕西|湖南|" +
////                "重庆|福建|天津|云南|四川|广西|安徽|海南|江西|湖北|" +
////                "山西|辽宁|黑龙江|内蒙古|贵州|甘肃|青海|新疆|西藏|吉林|宁夏)" +
////                "", "");
//
//
//        // 编码
//        String yourJsonString = "{\"businessNo\":\"65887931\",\"systemSource\":\"0\"}";
//        String yourBase64String = Base64.getEncoder().encodeToString(yourJsonString.getBytes("utf-8"));
//
//        // 解码
//        byte[] asBytes = Base64.getDecoder().decode("eyJidXNpbmVzc05vIjoiNjU4ODc5MzEiLCJzeXN0ZW1Tb3VyY2UiOiIwIn0=");
//        System.out.println(new String(asBytes, "utf-8")); // 输出为: some string
//    }

    public static void main(String[] args) throws Exception {
//        Calendar c = Calendar.getInstance();
//        System.out.println(c.getTime().getDay());
//        int month = c.get(c.WEEK_OF_MONTH);
//        int week = c.get(c.WEEK_OF_YEAR);
//        int day1 = c.getTime().getDay();
//        int year = c.get(Calendar.YEAR);
//        int month1 = c.get(Calendar.MONTH) + 1;
//        int day = c.get(Calendar.DATE);
//        int hour = c.get(Calendar.HOUR);
////			int min= c.get(Calendar.MINUTE);
////			int sec= c.get(Calendar.SECOND);
//        String now = year + "-" + month1 + "-" + day;
//        System.out.println(now);//获取系统当前时间下面也是，但是有一个问题就是，时间是在特殊点显示不全如：2019-11-29 9:6:5意思是早上9点6分，5秒
//        Date today = new Date();
//        DateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String createTime = f.format(today);
//        String dateString = "2019-04-30 15:59:10";
//
//        System.out.println("今天是今年的第" + month1 + "月;");
//        System.out.println("今天是今年的第" + week + "周;");
//        System.out.println("今天是今月的第" + month + "周;");
//        System.out.println("今天是这周的第" + day1 + "天;");
        List linkedList = new LinkedList();

        for (int i = 0; i < 500000; i++) {
            linkedList.add(i);
        }
        long begin = System.currentTimeMillis();
        int i = 0;
        for (Object o : linkedList) {
            if (i == 400000){
                System.out.println(o);
            }
            i++;
        }
        long end = System.currentTimeMillis();
        System.out.println();
        System.out.println("50W长链表找到第40W个耗时：" + (end - begin));
        System.out.println();
//
//        List arrayList = new ArrayList(500000);
//        for (int i = 0; i < 500000; i++) {
//            arrayList.add(i);
//        }
//        long begin = System.currentTimeMillis();
//        System.out.println(arrayList.get(400000));
//        long end = System.currentTimeMillis();
//        System.out.println();
//        System.out.println("50W长数组找到第40W个耗时：" + (end - begin));
//        System.out.println();

    }
}
