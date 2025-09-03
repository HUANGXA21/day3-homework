package pos.machine;

import java.util.*;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        return null;
    }

    public static boolean isMatch(List<String> barcodes) {
        List<Item> items = ItemsLoader.loadAllItems();
        Set<String> validBarcodes = new HashSet<>();
        for (Item item : items) {
            validBarcodes.add(item.getBarcode());
        }
        // 检查每个条码是否都存在于有效条码集合中
        for (String barcode : barcodes) {
            if (!validBarcodes.contains(barcode)) {
                return false; // 发现不存在的条码，返回false
            }
        }
        // 所有条码都存在，返回true
        return true;
    }

    public static Map<String, Integer> convertToCountMap(List<String> barcodes) {
        // 创建用于存储Map用于存储结果
        Map<String, Integer> countMap = new HashMap<>();
        // 遍历条码列表，统计每个条码出现的次数
        for (String barcode : barcodes) {
            // 如果Map中已存在该条码，则次数+1；否则初始化为1
            countMap.put(barcode, countMap.getOrDefault(barcode, 0) + 1);
        }
        return countMap;
    }


}
