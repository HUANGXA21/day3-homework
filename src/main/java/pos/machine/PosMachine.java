package pos.machine;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

}
