package com.github.dev.muzi.base.concurrent.knowledge.working.address_process;

import com.github.dev.muzi.base.concurrent.knowledge.working.FileUtil;
import org.apache.commons.collections.MapUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lifuyi8
 * @since 2021/3/8 3:11 下午
 */
public class CityProperProcess {
    /**
     * 剔除省市的规则
     */
    private static final String DEL_ADDRESS_REGX = "(?<province>北京|广东|山东|江苏|河南|上海|河北|浙江|陕西|湖南|" +
            "重庆|福建|天津|云南|四川|广西|安徽|海南|江西|湖北|山西|辽宁|黑龙江|内蒙古|贵州|甘肃|" +
            "青海|新疆|西藏|吉林|宁夏)(?<cityProperPrefix>[\\u4E00-\\u9FA5]{0,6}市|[\\u4E00-\\u9FA5]{0,6}区|[\\u4E00-\\u9FA5]{0,6}州)";
    private static final Pattern DEL_ADDRESS_REGX_PATTERN = Pattern.compile(DEL_ADDRESS_REGX);

    /**
     * 剔除符号标记的文本
     */
    private static final String DEL_RANGE_TEXT_REGX = "([【\\|（\\|(\\|《]{1}.*?[】\\|）\\|)\\|》]{1})";

    /**
     * 剔除手机号
     */
    private static final String DEL_PHONE_NUMBER_REGX = "(1)\\d{10}$*";

    /**
     * 剔除符号和一些提示
     */
    private static final String DEL_UNKNOW_TEXT_REGX = "([，\\/]+)|([提前联系]{4})|([=.*-]{2,20}[\\u4E00-\\u9FA5]{0,10})$";
    /**
     * 处理安维外呼的地址信息
     */
    private static String addressProcess(String address) {
        // 去除标记提示
        address = address.replaceAll(DEL_RANGE_TEXT_REGX, "");

        // 去除电话号码
        address = address.replaceAll(DEL_PHONE_NUMBER_REGX, "").trim();

        // 切分取出关键地址信息
        String[] addressArray = address.split("\\s+");
        boolean lengthEqThird = addressArray.length == 3;
        boolean afterImportant = addressArray.length == 2 && (addressArray[1].length() > addressArray[0].length() * 2);
        boolean lengthGtThird = addressArray.length > 3;
        if (lengthEqThird) {
            boolean firstBig = addressArray[0].length() > addressArray[1].length() && addressArray[0].length() > addressArray[2].length();
            boolean secondBig = addressArray[1].length() > addressArray[2].length();
            if (firstBig) {
                address = addressArray[0];
            } else if (secondBig) {
                address = addressArray[0] + addressArray[1];
            } else {
                address = addressArray[1] + addressArray[2];
            }
        } else if (afterImportant) {
            address = addressArray[0] + addressArray[1];
        } else if (lengthGtThird) {
            address = address.replaceAll("\\s+", "");
        } else {
            address = addressArray[0];
        }

        // 去除地址前两级
        Map<String, String> map = addressResolution(address);
        if (MapUtils.isNotEmpty(map)) {
            String cityProperPrefix = map.get("cityProperPrefix");
            String province = map.get("province");
            if (cityProperPrefix != null && province != null) {
                String prefix = province + cityProperPrefix;
                address = address.replaceAll(".*?" + prefix, "");
            }
        }
        // 后处理规则字符串
        address = address.replaceAll(DEL_UNKNOW_TEXT_REGX, "");
        return address;
    }

    private static Map<String, String> addressResolution(String address) {
        Matcher m = DEL_ADDRESS_REGX_PATTERN.matcher(address);
        String province = null, cityProperPrefix = null, county = null, town = null, village = null;
        Map<String, String> row = null;
        while (m.find()) {
            row = new LinkedHashMap<String, String>();
            province = m.group("province");
            row.put("province", province == null ? "" : province.trim());
            cityProperPrefix = m.group("cityProperPrefix");
            row.put("cityProperPrefix", cityProperPrefix == null ? "" : cityProperPrefix.trim());
        }
        return row;
    }

    public static void main(String[] args) {
        List<String> badcaseList = FileUtil.loadList("/Users/lifuyi8/code/github/java-basic-knowledge/java-basic-knowledge-working/src/main/resources/address/0310-线上地址数据2.txt");
        List<String> endLines = new ArrayList<>(badcaseList.size());
        for (String itemQuery : badcaseList) {
            String baseQuery = itemQuery;
            itemQuery = addressProcess(itemQuery);
            endLines.add(baseQuery + "$" + itemQuery);
        }
        FileUtil.writeLine("/Users/lifuyi8/code/github/java-basic-knowledge/java-basic-knowledge-working/src/main/resources/address/ending.txt", endLines);

//        System.out.println(processAddress("福建泉州市南安市洪梅镇仁科村井边31号 备注：18820286315、9号配送、送货联系：15880845630、提前电话联系"));
    }
}
