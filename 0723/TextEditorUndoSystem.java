import java.util.Stack;

public class TextEditorUndoSystem {
    
    // 使用 Stack 保存歷史狀態
    private Stack<String> history;
    private String currentText;
    
    // 建構子
    public TextEditorUndoSystem() {
        this.history = new Stack<>();
        this.currentText = "";
    }
    
    public static void main(String[] args) {
        System.out.println("=== 文字編輯器撤銷系統 ===");
        System.out.println("使用 Stack 保存編輯歷史\n");
        
        TextEditorUndoSystem editor = new TextEditorUndoSystem();
        
        // 測試1：新增文字
        System.out.println("=== 測試1：新增文字 ===");
        editor.appendText("Hello");
        editor.displayText();
        editor.appendText(" World");
        editor.displayText();
        editor.appendText("!");
        editor.displayText();
        
        // 測試2：刪除最後幾個字元
        System.out.println("\n=== 測試2：刪除最後幾個字元 ===");
        editor.deleteLast(6);
        editor.displayText();
        editor.deleteLast(3);
        editor.displayText();
        
        // 測試3：查看歷史
        System.out.println("\n=== 測試3：查看歷史 ===");
        editor.showHistory();
        
        // 測試4：撤銷操作（連續撤銷）
        System.out.println("\n=== 測試4：連續撤銷（第一次） ===");
        editor.undo();
        editor.displayText();
        
        System.out.println("\n=== 測試4：連續撤銷（第二次） ===");
        editor.undo();
        editor.displayText();
        
        System.out.println("\n=== 測試4：連續撤銷（第三次） ===");
        editor.undo();
        editor.displayText();
        
        // 測試5：查看歷史（撤銷後）
        System.out.println("\n=== 測試5：查看歷史（撤銷後） ===");
        editor.showHistory();
        
        // 測試6：繼續撤銷（測試空歷史）
        System.out.println("\n=== 測試6：繼續撤銷（測試空歷史） ===");
        editor.undo();
        editor.undo();
        editor.undo();
        editor.displayText();
        
        // 測試7：新增文字後再撤銷
        System.out.println("\n=== 測試7：新增文字後再撤銷 ===");
        editor.appendText("Java");
        editor.displayText();
        editor.appendText(" Programming");
        editor.displayText();
        editor.undo();
        editor.displayText();
        editor.undo();
        editor.displayText();
        
        // 測試8：複雜操作
        System.out.println("\n=== 測試8：複雜操作 ===");
        editor.appendText(" A");
        editor.displayText();
        editor.appendText(" B");
        editor.displayText();
        editor.deleteLast(2);
        editor.displayText();
        editor.undo();
        editor.displayText();
        editor.undo();
        editor.displayText();
        
        // 測試9：查看最終狀態
        System.out.println("\n=== 測試9：最終狀態 ===");
        editor.showHistory();
        editor.displayText();
        
        System.out.println("\n=== 所有測試完成 ===");
        System.out.println("總操作次數：9 次測試");
    }
    
    /**
     * 新增文字（保存修改前狀態）
     */
    public void appendText(String text) {
        if (text == null || text.isEmpty()) {
            System.out.println("  ⚠️ 沒有文字可以新增");
            return;
        }
        
        // 修改前保存狀態
        saveState();
        
        currentText += text;
        System.out.println("  ✅ 新增文字：" + text);
        System.out.println("  📊 歷史記錄數：" + history.size());
    }
    
    /**
     * 刪除最後幾個字元（保存修改前狀態）
     */
    public void deleteLast(int count) {
        if (count <= 0) {
            System.out.println("  ⚠️ 刪除數量必須大於 0");
            return;
        }
        
        if (currentText.isEmpty()) {
            System.out.println("  ⚠️ 目前沒有文字可以刪除");
            return;
        }
        
        if (count > currentText.length()) {
            System.out.println("  ⚠️ 刪除數量超過文字長度，將刪除全部文字");
            count = currentText.length();
        }
        
        // 修改前保存狀態
        saveState();
        
        String deletedText = currentText.substring(currentText.length() - count);
        currentText = currentText.substring(0, currentText.length() - count);
        System.out.println("  ✅ 刪除最後 " + count + " 個字元：" + deletedText);
        System.out.println("  📊 歷史記錄數：" + history.size());
    }
    
    /**
     * 保存當前狀態到歷史
     */
    private void saveState() {
        history.push(currentText);
    }
    
    /**
     * 撤銷操作（回到上一個狀態）
     */
    public void undo() {
        if (history.isEmpty()) {
            System.out.println("  ⚠️ 沒有歷史記錄可以撤銷！");
            return;
        }
        
        String previousState = history.pop();
        System.out.println("  ↩️ 撤銷操作（從 \"" + currentText + "\" 回到 \"" + previousState + "\"）");
        currentText = previousState;
        System.out.println("  📊 剩餘歷史記錄數：" + history.size());
    }
    
    /**
     * 顯示目前文字內容
     */
    public void displayText() {
        System.out.println("  📝 目前內容：\"" + currentText + "\"");
        System.out.println("  📊 字元數：" + currentText.length());
    }
    
    /**
     * 顯示歷史記錄
     */
    public void showHistory() {
        if (history.isEmpty()) {
            System.out.println("  📋 歷史記錄：空（沒有可撤銷的記錄）");
            return;
        }
        
        System.out.println("  📋 歷史記錄（從最近到最舊）：");
        // 複製堆疊以顯示內容（不影響原堆疊）
        @SuppressWarnings("unchecked")
        Stack<String> tempStack = (Stack<String>) history.clone();
        int position = 1;
        while (!tempStack.isEmpty()) {
            String state = tempStack.pop();
            System.out.println("     " + position + ". \"" + state + "\"");
            position++;
        }
        System.out.println("  📊 共 " + history.size() + " 筆記錄");
    }
}