package com.github.dev.muzi.base.concurrent.knowledge.working.address_process;

/**
 * @author lifuyi8
 * @since 2021/3/8 4:26 下午
 */
public class Test00 {

    private static final String REGX = "(?<province>北京|广东|山东|江苏|河南|上海|河北|浙江|陕西|湖南|重庆|福建|天津|云南|四川|" +
            "广西|安徽|海南|江西|湖北|山西|辽宁|黑龙江|内蒙古|贵州|甘肃|青海|新疆|西藏|吉林|宁夏)(?<cityProperPrefix>[.*?]{0,5}市|[.*?]{0,5}区)";

    public static void main(String[] args) {
        System.out.println("北京朝阳区五环到六环之间三间房乡天泰北双苑11号楼1106-A（华联惠生超市旁）".replaceAll("([【\\|（\\|(\\|《]{1}.*?[】\\|）\\|)\\|》]{1})", ""));
        System.out.println("北京朝阳区五环到六环之间三间房乡天泰北双苑11号楼1106-A".replaceAll("\\pP|\\pS|\\pC|\\pZ", ""));


        String a = "北京昌平区某某街道".replaceAll("(?<province>北京|广东|山东|江苏|河南|上海|河北|浙江|陕西|湖南|" +
                "重庆|福建|天津|云南|四川|广西|安徽|海南|江西|湖北|" +
                "山西|辽宁|黑龙江|内蒙古|贵州|甘肃|青海|新疆|西藏|吉林|宁夏)" +
                "", "");
    }
}
