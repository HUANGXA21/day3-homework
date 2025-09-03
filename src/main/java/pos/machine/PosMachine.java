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

    public static Map<String, Integer> processData(List<String> barcodes) {
        // 创建用于存储Map用于存储结果
        Map<String, Integer> countMap = new HashMap<>();
        // 遍历条码列表，统计每个条码出现的次数
        for (String barcode : barcodes) {
            // 如果Map中已存在该条码，则次数+1；否则初始化为1
            countMap.put(barcode, countMap.getOrDefault(barcode, 0) + 1);
        }
        return countMap;
    }
    public static String generateReceipt(Map<String, Integer> barcodesMap){
        int total =0;
        StringBuilder receipt = new StringBuilder();
        receipt.append("***<store earning no money>Receipt***").append("\n");
        for (Map.Entry<String, Integer> entry : barcodesMap.entrySet()) {
            String barcode = entry.getKey();
            int quantity = entry.getValue();
            // 调用单个计算方法
            int subtotal = calculateSubtotal(barcode, quantity);
            String barcodeList = generateBarcodeList(barcode, quantity, subtotal);
            receipt.append(barcodeList).append("\n");
            total=total+subtotal;
        }
        receipt.append("---------------------").append("\n");
        receipt.append("Total: ").append(total).append(" (yuan)");
        receipt.append("*********************");
        return receipt.toString();
    }
    public static int calculateSubtotal(String barcode, int quantity) {
        // 查找对应商品的单价
        List<Item> items = ItemsLoader.loadAllItems();
        for (Item item : items) {
            if (item.getBarcode().equals(barcode)) {
                // 计算并返回总价格（假设价格为整数）
                return item.getPrice() * quantity;
            }
        }
        // 未找到对应商品时返回0（可根据需求改为抛出异常）
        return 0;
    }
    public static String generateBarcodeList(String barcode, int quantity, int subtotal) {
        List<Item> items = ItemsLoader.loadAllItems();
        String name="initName";
        int price=0;
        for (Item item : items) {
            if (item.getBarcode().equals(barcode)) {
               name =  item.getName();
               price = item.getPrice();
                break;
            }
        }
        return String.format("Name: %s, Quantity: %d, Unit price: %d (yuan), Subtotal: %d (yuan)", name, quantity, price, subtotal);
    }
}
