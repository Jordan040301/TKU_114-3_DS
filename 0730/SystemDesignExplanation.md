# 資料結構選擇說明

## 系統選擇：活動報名與候補系統 (EventRegistrationSystem)

**系統檔案**：`EventRegistrationSystem.java`、`Registration.java`、`RegistrationAlgorithms.java`

---

## 功能一：儲存所有報名資料

**使用資料結構**：`ArrayList<Registration>`

**對應檔案與方法**：
- 檔案：`EventRegistrationSystem.java`
- 屬性：`private ArrayList<Registration> allRegistrations`
- 方法：`addRegistration()`、`cancelRegistration()`、`displayAllRegistrations()`

**選擇原因**：
1. **隨機存取效能佳**：ArrayList 提供 O(1) 的隨機存取時間，適合需要頻繁查詢特定編號報名的場景
2. **動態擴充**：報名人數不固定，ArrayList 可自動調整大小
3. **實作簡單**：Java 內建支援，程式碼撰寫與維護容易
4. **遍歷效率高**：使用 for-each 迴圈遍歷所有報名資料時效能良好

**未採用 LinkedList 的原因**：
1. **記憶體開銷較大**：每個節點需要額外儲存前後節點參考
2. **隨機存取效能差**：需要 O(n) 時間才能找到特定位置的元素
3. **不適合頻繁的索引操作**：本系統需要透過索引進行隨機存取（如排序後顯示）

---

## 功能二：候補順序管理

**使用資料結構**：`Queue<Registration>` (實作為 `LinkedList`)

**對應檔案與方法**：
- 檔案：`EventRegistrationSystem.java`
- 屬性：`private Queue<Registration> waitingQueue`
- 方法：`addRegistration()` (加入候補)、`cancelRegistration()` (取出候補)、`viewWaitingQueue()`

**選擇原因**：
1. **FIFO 特性**：Queue 的 First-In-First-Out 特性完美符合「先候補先遞補」的公平原則
2. **操作直觀**：`offer()` 加入佇列尾端，`poll()` 從佇列前端取出，符合候補管理邏輯
3. **語意清晰**：使用 Queue 介面讓程式碼意圖明確，其他開發者一看就懂

**未採用 Stack 的原因**：
1. **LIFO 不符合需求**：Stack 的 Last-In-First-Out 特性會導致最後候補的人先遞補，違反公平原則
2. **邏輯錯誤**：若使用 Stack，候補順序會完全顛倒，造成系統行為異常

---

## 功能三：取消記錄管理

**使用資料結構**：`Stack<Registration>`

**對應檔案與方法**：
- 檔案：`EventRegistrationSystem.java`
- 屬性：`private Stack<Registration> cancelledStack`
- 方法：`cancelRegistration()` (推入堆疊)、`viewLastCancelled()` (查看頂端)、`viewAllCancelled()` (顯示所有)

**選擇原因**：
1. **LIFO 特性**：Stack 的 Last-In-First-Out 特性適合「最近取消優先查看」的需求
2. **操作簡單**：`push()` 加入、`peek()` 查看頂端、`pop()` 取出，操作直覺
3. **符合使用情境**：管理人員通常最關心「最近一次取消」的記錄，Stack 的頂端正好提供此資訊

**未採用 Queue 的原因**：
1. **不符合查看習慣**：Queue 的 FIFO 會讓最早的取消記錄在最前面，不符合「查看最近取消」的需求
2. **語意不匹配**：Queue 代表「待處理」的概念，而取消記錄是「已處理」的歷史資料

---

## 功能四：依編號查詢報名

**使用演算法**：二分搜尋 (Binary Search)

**對應檔案與方法**：
- 檔案：`RegistrationAlgorithms.java`
- 方法：`public static Registration binarySearchById(Registration[] registrations, String registrationId)`
- 呼叫端：`EventRegistrationSystem.java` 的 `searchById()` 方法

**選擇原因**：
1. **時間複雜度 O(log n)**：在已排序的資料中，二分搜尋提供極快的查詢速度
2. **效能優異**：當資料量為 1024 筆時，最多只需 10 次比較即可找到目標
3. **適合大量資料**：隨著資料量增加，效能優勢更明顯
4. **有序資料的最佳選擇**：報名編號具有唯一性且可排序，非常適合二分搜尋

**未採用順序搜尋的原因**：
1. **時間複雜度 O(n)**：需要逐一檢查所有元素，在大量資料時效能差
2. **資源浪費**：當資料已排序時，不使用二分搜尋是一種資源浪費
3. **擴展性差**：當系統成長到數萬筆資料時，順序搜尋會嚴重影響效能

---

## 功能五：依姓名查詢報名

**使用演算法**：順序搜尋 (Sequential Search)

**對應檔案與方法**：
- 檔案：`RegistrationAlgorithms.java`
- 方法：`public static Registration[] sequentialSearchByName(Registration[] registrations, String participantName)`
- 呼叫端：`EventRegistrationSystem.java` 的 `searchByName()` 方法

**選擇原因**：
1. **支援多重結果**：同一姓名可能有多筆報名，順序搜尋可找出所有符合條件的資料
2. **不需要預先排序**：可直接在未排序的資料上執行
3. **實作簡單**：只需逐一檢查每個元素是否匹配
4. **適合小規模資料**：在報名人數不多的情況下，效能差異不明顯

**未採用二分搜尋的原因**：
1. **無法處理多重結果**：二分搜尋通常只回傳單一結果，無法找出所有同名報名
2. **需要額外排序**：二分搜尋要求資料已排序，需要先執行排序（O(n log n)）
3. **姓名可能重複**：二分搜尋設計用於唯一鍵值，不適合處理重複值的情況

---

## 功能六：依編號排序報名

**使用演算法**：歸併排序 (Merge Sort)

**對應檔案與方法**：
- 檔案：`RegistrationAlgorithms.java`
- 方法：`public static void mergeSortById(Registration[] registrations, int left, int right)`
- 呼叫端：`EventRegistrationSystem.java` 的 `sortById()` 和 `displayAllRegistrations()` 方法

**選擇原因**：
1. **穩定排序**：相同屬性的元素保持原有順序
2. **時間複雜度 O(n log n)**：無論資料順序如何，都能保證穩定的效能
3. **適合串列資料**：ArrayList 轉換為陣列後，歸併排序運作良好
4. **可預測的效能**：不會像快速排序在某些情況下退化為 O(n²)

**未採用選擇排序或插入排序的原因**：
1. **時間複雜度 O(n²)**：在大規模資料時效能不佳
2. **插入排序在亂序資料表現差**：當資料亂序時，插入排序需要大量比較和移動
3. **選擇排序不穩定**：相同元素的相對順序可能改變，影響資料一致性
4. **歸併排序在最壞情況下仍維持 O(n log n)**：提供最穩定的效能保證

---

## 功能七：報名狀態管理

**使用資料結構**：`String status` (搭配常數)

**對應檔案與方法**：
- 檔案：`Registration.java`
- 屬性：`private String status`
- 方法：`setStatus()`、`getStatus()`

**狀態值**：`CONFIRMED` (已確認)、`WAITING` (候補中)、`CANCELLED` (已取消)

**選擇 String 的原因**：
1. **可讀性高**：使用明確的文字描述狀態，易於理解和除錯
2. **擴展性強**：未來可輕鬆增加新狀態（如：`PENDING`、`REJECTED`）
3. **顯示方便**：可直接在 `toString()` 中使用，無需額外轉換

**未使用 Enum 的原因**：
1. **雖然 Enum 更嚴謹**：但 String 在小型系統中更靈活
2. **資料庫儲存方便**：String 可直接儲存，無需額外轉換
3. **簡單易懂**：初學者更容易理解和維護

---

## 功能八：顯示所有報名

**使用演算法**：歸併排序 + 遍歷

**對應檔案與方法**：
- 檔案：`EventRegistrationSystem.java`
- 方法：`displayAllRegistrations()`

**選擇原因**：
1. **有序顯示**：使用歸併排序將報名依編號排序後顯示，便於查看和管理
2. **使用者體驗佳**：排序後的資料更容易閱讀和尋找特定報名
3. **資料一致性**：顯示時排序，但原始資料保持新增順序，不影響其他功能

**未直接顯示 ArrayList 的原因**：
1. **順序混亂**：ArrayList 儲存順序為新增順序，不適合直接顯示
2. **管理困難**：管理人員需要有序的資料才能有效管理
3. **專業呈現**：排序後的顯示更符合專業系統的標準

---

## 總結比較表

| 功能 | 使用技術 | 時間複雜度 | 空間複雜度 | 主要優勢 |
|------|---------|-----------|-----------|---------|
| 儲存所有報名 | ArrayList | O(1) 存取 | O(n) | 隨機存取快、實作簡單 |
| 候補順序管理 | Queue | O(1) 入出佇列 | O(n) | FIFO 公平性 |
| 取消記錄管理 | Stack | O(1) 推入取出 | O(n) | LIFO 符合查看習慣 |
| 依編號查詢 | 二分搜尋 | O(log n) | O(1) | 快速、高效 |
| 依姓名查詢 | 順序搜尋 | O(n) | O(1) | 支援多重結果 |
| 依編號排序 | 歸併排序 | O(n log n) | O(n) | 穩定、可預測 |

---

## 結論

本系統透過 **ArrayList、Queue、Stack** 三種資料結構的組合運用，以及 **二分搜尋、順序搜尋、歸併排序** 三種演算法的搭配，實現了一個功能完整且高效能的活動報名與候補管理系統。每種資料結構和演算法的選擇都基於其最適合的使用場景，並充分考慮了系統需求、效能表現和維護便利性。