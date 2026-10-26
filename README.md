# GreenFood 綠色餐廳

<img src="android/app/src/main/res/drawable/api_placeholder.png" alt="GreenFood 綠色餐廳" width="140">

大學資料庫課程的分組專題，使用 Android App 查詢綠色餐廳，透過 PHP API 讀寫 MySQL 資料庫。

## 功能

- 依縣市、地區、餐廳風格、用餐方式與付款方式篩選餐廳。
- 查看地址、營業時間、價位、食材產地與餐廳設備資訊。
- 會員註冊與登入、個人資料修改、收藏與瀏覽紀錄。
- 餐廳星等評分與文字評論。
- 管理畫面可新增、修改、刪除餐廳及分類資料。

## 專案結構

| 資料夾 | 內容 |
|---|---|
| [android](android/) | Android Studio 專案、Java 程式、XML 畫面與餐廳圖片。 |
| [backend](backend/) | PHP API；[conn.php](backend/conn.php) 負責資料庫連線。 |
| [database](database/schema.sql) | MySQL 資料表結構，包含 16 張表與關聯。 |

## 開發環境

| 項目 | 設定 |
|---|---|
| Android | Java 8 語法、最低 SDK 21、compile／target SDK 32。 |
| 建置工具 | Android Gradle Plugin 7.2.1、Gradle 7.3.3；AGP 7.2 的 JDK 基準版本為 11。 |
| 後端 | PHP、mysqli、MySQL；原專案使用 XAMPP。 |

建置工具的版本相容性可參考 [Android 官方文件](https://developer.android.com/build/releases/agp-7-2-0-release-notes)。

## 連線設定

1. 將 [schema.sql](database/schema.sql) 匯入本機 MySQL，建立 `greenfood` 資料庫。檔案只有資料表結構，不附原始會員、評論與餐廳資料，查詢所需的分類及餐廳資料需自行建立。
2. 將 `backend` 內的 PHP 檔案放到 XAMPP 的 `htdocs/greenfood`，把 [config.example.php](backend/config.example.php) 複製為 `config.php`，填入自己的資料庫帳號與密碼。
3. 使用 Android Studio 開啟 `android` 資料夾。App 的 API 預設為 `http://10.0.2.2/greenfood/`，適用於 Android Emulator 連線到開發電腦上的後端。

使用實體手機或其他後端位置時，在個人的 Gradle 設定（`~/.gradle/gradle.properties`）加入 `GREENFOOD_API_BASE_URL`，值為自己的 API 根網址。網址會由 [ApiConfig.java](android/app/src/main/java/com/example/greenfood/ApiConfig.java) 統一套用，不需要逐支修改 Activity。模擬器位址的說明可參考 [Android 官方文件](https://developer.android.com/studio/run/emulator-networking-address)。

`backend/config.php` 不納入版本控制。管理者的課堂測試入口預設停用，只有在本機設定 `GREENFOOD_DEMO_ADMIN_ACCOUNT` 與 `GREENFOOD_DEMO_ADMIN_PASSWORD` 時才啟用；這個入口用於切換管理畫面，並不是後端權限控管。
