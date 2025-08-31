package com.nky.nkyble.util;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.asm.Opcodes;
import com.nky.nkyble.App;

/* loaded from: classes.dex */
public class AppPrefsUtils {
    private static final String APP_ELECTRIC = "app_soc";
    public static final String APP_PREFERENCES_KEY = "profile";
    private static final String APP_THEMES = "app_themes";
    private static final String CANVASCOLOR = "canvasColor";
    private static final String ERASERWIDTH = "eraserWidth";
    private static final String KEY_ACCOUNT_NAME = "accountName";
    private static final String KEY_APP_LANGUAGE = "app_language";
    private static final String KEY_BIRTHDAY = "birthday";
    private static final String KEY_BLE_ADDRESS = "ble_address";
    private static final String KEY_BLE_DEVICE_TYPE = "ble_device_type";
    private static final String KEY_BLE_NAME = "ble_name";
    private static final String KEY_BOOST = "boost";
    private static final String KEY_DEVICE_LOG = "deviceSetLog";
    private static final String KEY_DEV_TYPE = "key_dev_type";
    private static final String KEY_DPRODUCTION_PATH = "ProductionDPath";
    private static final String KEY_ENEMY_ID = "enemyId";
    private static final String KEY_ENEMY_IMAGE = "enemyimage";
    private static final String KEY_ENEMY_SCORE = "EnemyScore";
    private static final String KEY_ENEMY_TOKEN = "enemyToken";
    private static final String KEY_ENEMY_USER_NAME = "enemyUserName";
    private static final String KEY_FONT = "font";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_ID = "userId";
    private static final String KEY_LEVEL = "level";
    private static final String KEY_MOBILE = "mobile";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_POWER_TOKEN = "cpowerToken";
    private static final String KEY_PRODUCTION_PATH = "productionPath";
    private static final String KEY_REGID = "regId";
    private static final String KEY_SCORE = "score";
    private static final String KEY_TEMPERATURE = "temperature";
    private static final String KEY_THREE_TYPE = "three_type";
    private static final String KEY_TOKEN = "token";
    private static final String KEY_TOTAL_DAYS = "total_days";
    private static final String KEY_TOTAL_NUMS = "total_nums";
    private static final String KEY_TOTAL_PIECE = "total_piece";
    private static final String KEY_USERIMAGE = "userimage";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_UUID = "uuid";
    private static final String KEY_VERIFYCODE = "verifyCode";
    private static final String KEY_WORK_ID = "workId";
    private static final String LOGIN_COOLIES = "login_coolies";
    private static final String PAINTCOLOR = "paintColor";
    private static final String PAINT_STROKEWIDTH = "paintStrokeWidth";
    private static final String PENCONNSTATUS = "penConnStatus";
    public static final SharedPreferences PREFERENCES = App.context.getSharedPreferences("myGro_userInfo", 0);
    public static final String SP_AUTO_LOGIN = "auto_login";
    private static final String SP_IS_FIRST_SMART_PV = "SP_IS_FIRST_SMART_PV";
    public static final String SP_REMEMBER_PASSWORD = "remember_password";
    public static final String SP_REMEMBER_PROTOCOL = "remember_Protocol";

    private static SharedPreferences getAppPreference() {
        return PREFERENCES;
    }

    private static SharedPreferences.Editor getEditor() {
        return getAppPreference().edit();
    }

    public static void setAppFlag(String str, boolean z) {
        getEditor().putBoolean(str, z).apply();
    }

    public static boolean getAppFlag(String str) {
        return getAppPreference().getBoolean(str, false);
    }

    public static void setBoostFlag(boolean z) {
        getEditor().putBoolean(KEY_BOOST, z).apply();
    }

    public static boolean getBoostFlag() {
        return getAppPreference().getBoolean(KEY_BOOST, false);
    }

    public static void setSendFlag(String str, boolean z) {
        getEditor().putBoolean(str, z).apply();
    }

    public static boolean getSendFlag(String str) {
        return getAppPreference().getBoolean(str, false);
    }

    public static void setAppProfile(String str) {
        getEditor().putString("profile", str).apply();
    }

    public static String getAppProfile() {
        return getAppPreference().getString("profile", null);
    }

    public static JSONObject getAppProfileJson() {
        return JSON.parseObject(getAppProfile());
    }

    private static void removeAppProfile() {
        getEditor().remove("profile").apply();
    }

    public static void clearAppPreferences() {
        getEditor().clear().apply();
    }

    public static void addCustomAppProfile(String str, String str2) {
        getEditor().putString(str, str2).apply();
    }

    public static String getCustomAppProfile(String str) {
        return getAppPreference().getString(str, "");
    }

    public static void setBleName(String str) {
        getEditor().putString(KEY_BLE_NAME, str).apply();
    }

    public static String getBleName() {
        return getAppPreference().getString(KEY_BLE_NAME, null);
    }

    public static void setDevType(String str) {
        getEditor().putString(KEY_DEV_TYPE, str).apply();
    }

    public static String getDevType() {
        return getAppPreference().getString(KEY_DEV_TYPE, null);
    }

    public static void setBleAddress(String str) {
        getEditor().putString(KEY_BLE_ADDRESS, str).apply();
    }

    public static String getBleAddress() {
        return getAppPreference().getString(KEY_BLE_ADDRESS, null);
    }

    public static void setAppLanguage(String str) {
        getEditor().putString(KEY_APP_LANGUAGE, str).apply();
    }

    public static String getAppLanguage() {
        return getAppPreference().getString(KEY_APP_LANGUAGE, "default");
    }

    public static void setBleDeviceType(int i) {
        getEditor().putInt(KEY_BLE_DEVICE_TYPE, i).apply();
    }

    public static int getBleDeviceType() {
        return getAppPreference().getInt(KEY_BLE_DEVICE_TYPE, 0);
    }

    public static int getSoc() {
        return getAppPreference().getInt(APP_ELECTRIC, 0);
    }

    public static void setSoc(int i) {
        getEditor().putInt(APP_ELECTRIC, i).apply();
    }

    public static int getThemes() {
        return getAppPreference().getInt(APP_THEMES, 0);
    }

    public static void setThemes(int i) {
        getEditor().putInt(APP_THEMES, i).apply();
    }

    public static void saveFirstSmartPv() {
        getEditor().putBoolean(SP_IS_FIRST_SMART_PV, true).apply();
    }

    public static boolean isFirstSmartPv() {
        return getAppPreference().getBoolean(SP_IS_FIRST_SMART_PV, false);
    }

    public static int getTemperatureType() {
        return getAppPreference().getInt(KEY_TEMPERATURE, 0);
    }

    public static void setTemperatureType(int i) {
        getEditor().putInt(KEY_TEMPERATURE, i).apply();
    }

    public static void setAccountName(String str) {
        getEditor().putString(KEY_ACCOUNT_NAME, str).apply();
    }

    public static String getAccountName() {
        return getAppPreference().getString(KEY_ACCOUNT_NAME, null);
    }

    public static void setPassword(String str) {
        getEditor().putString("password", str).apply();
    }

    public static String getPassword() {
        return getAppPreference().getString("password", null);
    }

    public static void setProtocol(boolean z) {
        getEditor().putBoolean(SP_REMEMBER_PROTOCOL, z).apply();
    }

    public static Boolean getProtocol() {
        return Boolean.valueOf(getAppPreference().getBoolean(SP_REMEMBER_PROTOCOL, false));
    }

    public static void setAutoLogin(boolean z) {
        getEditor().putBoolean(SP_AUTO_LOGIN, z).apply();
    }

    public static Boolean getAutoLogin() {
        return Boolean.valueOf(getAppPreference().getBoolean(SP_AUTO_LOGIN, false));
    }

    public static void setRememberPassword(boolean z) {
        getEditor().putBoolean(SP_REMEMBER_PASSWORD, z).apply();
    }

    public static Boolean getRememberPassword() {
        return Boolean.valueOf(getAppPreference().getBoolean(SP_REMEMBER_PASSWORD, false));
    }

    public static void setType(String str) {
        getEditor().putString(KEY_THREE_TYPE, str).apply();
    }

    public static void clearUserInfo() {
        setMobile("");
        setVerifyCode("");
        setUserName("");
        setToken("");
        setBirthday("");
        setGender(0);
        setLevel("儒生");
        setTotal_Days(0);
        setTotal_Nums(0);
        setTotal_Piece(0);
        setUserImage("");
        setDefaultFont("宋体");
        setEnemyScore("");
        setEnemyImage("");
        setEnemyUserName("");
        setEnemyToken("");
        setEnemyId("");
        setWorkId("");
        setProductionPath("");
        setScore("");
        setEnemyProductionPath("");
        setEnemyScore("");
    }

    public static int getPaintStrokeWidth() {
        int i = getAppPreference().getInt(PAINT_STROKEWIDTH, 10);
        if (i <= 0) {
            return 5;
        }
        return i;
    }

    public static void setPaintStrokeWidth(int i) {
        getEditor().putInt(PAINT_STROKEWIDTH, i).apply();
    }

    public static int getPaintColor() {
        return getAppPreference().getInt(PAINTCOLOR, Color.rgb(82, 99, Opcodes.RETURN));
    }

    public static void setPaintColor(int i) {
        getEditor().putInt(PAINTCOLOR, i).apply();
    }

    public static float getCanvasColor() {
        return getAppPreference().getFloat(CANVASCOLOR, 10.0f);
    }

    public static void setCanvasColor(float f) {
        getEditor().putFloat(CANVASCOLOR, f).apply();
    }

    public static int getEraserWidth() {
        return getAppPreference().getInt(ERASERWIDTH, 50);
    }

    public static void setEraserWidth(float f) {
        getEditor().putFloat(ERASERWIDTH, f).apply();
    }

    public static void setPenConnStatus(boolean z) {
        getEditor().putBoolean(PENCONNSTATUS, z).apply();
    }

    public static boolean getPenConnStatus() {
        return getAppPreference().getBoolean(PENCONNSTATUS, false);
    }

    public static int getUserId() {
        return getAppPreference().getInt(KEY_ID, 0);
    }

    public static void setUserId(int i) {
        getEditor().putInt(KEY_ID, i).apply();
    }

    public static void setMobile(String str) {
        getEditor().putString(KEY_MOBILE, str).apply();
    }

    public static String getMobile() {
        return getAppPreference().getString(KEY_MOBILE, null);
    }

    public static void setVerifyCode(String str) {
        getEditor().putString(KEY_VERIFYCODE, str).apply();
    }

    public static String getVerifyCode() {
        return getAppPreference().getString(KEY_VERIFYCODE, null);
    }

    public static void setUserImage(String str) {
        getEditor().putString(KEY_USERIMAGE, str).apply();
    }

    public static String getUserImage() {
        return getAppPreference().getString(KEY_USERIMAGE, null);
    }

    public static void setEnemyImage(String str) {
        getEditor().putString(KEY_ENEMY_IMAGE, str).apply();
    }

    public static String getEnemyImage() {
        return getAppPreference().getString(KEY_ENEMY_IMAGE, null);
    }

    public static void setEnemyUserName(String str) {
        getEditor().putString(KEY_ENEMY_USER_NAME, str).apply();
    }

    public static String getEnemyUserName() {
        return getAppPreference().getString(KEY_ENEMY_USER_NAME, null);
    }

    public static void setUserName(String str) {
        getEditor().putString(KEY_USERNAME, str).apply();
    }

    public static String getUserName() {
        String string = getAppPreference().getString(KEY_USERNAME, "昵称");
        return TextUtils.isEmpty(string) ? "昵称" : string;
    }

    public static void setToken(String str) {
        getEditor().putString("token", str).commit();
    }

    public static String getToken() {
        return getAppPreference().getString("token", "");
    }

    public static void setPowerToken(String str) {
        getEditor().putString(KEY_POWER_TOKEN, str).commit();
    }

    public static String getPowerToken() {
        return getAppPreference().getString(KEY_POWER_TOKEN, "");
    }

    public static void setEnemyToken(String str) {
        getEditor().putString(KEY_ENEMY_TOKEN, str).apply();
    }

    public static String getEnemyToken() {
        return getAppPreference().getString(KEY_ENEMY_TOKEN, null);
    }

    public static void setEnemyId(String str) {
        getEditor().putString(KEY_ENEMY_ID, str).apply();
    }

    public static String getEnemyId() {
        return getAppPreference().getString(KEY_ENEMY_ID, null);
    }

    public static void setWorkId(String str) {
        getEditor().putString(KEY_WORK_ID, str).apply();
    }

    public static String getWorkId() {
        return getAppPreference().getString(KEY_WORK_ID, null);
    }

    public static void setProductionPath(String str) {
        getEditor().putString(KEY_PRODUCTION_PATH, str).apply();
    }

    public static String getProductionPath() {
        return getAppPreference().getString(KEY_PRODUCTION_PATH, null);
    }

    public static void setScore(String str) {
        getEditor().putString(KEY_SCORE, str).apply();
    }

    public static String getScore() {
        return getAppPreference().getString(KEY_SCORE, null);
    }

    public static void setEnemyProductionPath(String str) {
        getEditor().putString(KEY_DPRODUCTION_PATH, str).apply();
    }

    public static String getEnemyProductionPath() {
        return getAppPreference().getString(KEY_DPRODUCTION_PATH, null);
    }

    public static void setEnemyScore(String str) {
        getEditor().putString(KEY_ENEMY_SCORE, str).apply();
    }

    public static String getEnemyScore() {
        return getAppPreference().getString(KEY_ENEMY_SCORE, null);
    }

    public static void setBirthday(String str) {
        getEditor().putString(KEY_BIRTHDAY, str).apply();
    }

    public static String getBirthday() {
        String string = getAppPreference().getString(KEY_BIRTHDAY, "1993-10-26");
        return TextUtils.isEmpty(string) ? "1993-10-26" : string;
    }

    public static void setGender(int i) {
        getEditor().putInt(KEY_GENDER, i).apply();
    }

    public static int getGender() {
        return getAppPreference().getInt(KEY_GENDER, 0);
    }

    public static void setLevel(String str) {
        getEditor().putString("level", str).apply();
    }

    public static String getLevel() {
        String string = getAppPreference().getString("level", "儒生");
        return TextUtils.isEmpty(string) ? "儒生" : string;
    }

    public static void setDefaultFont(String str) {
        getEditor().putString("font", str).apply();
    }

    public static String getDefaultFont() {
        String string = getAppPreference().getString("font", "宋体");
        return TextUtils.isEmpty(string) ? "宋体" : string;
    }

    public static void setTotal_Days(int i) {
        getEditor().putInt(KEY_TOTAL_DAYS, i).apply();
    }

    public static int getTotal_Days() {
        return getAppPreference().getInt(KEY_TOTAL_DAYS, 0);
    }

    public static void setTotal_Nums(int i) {
        getEditor().putInt(KEY_TOTAL_NUMS, i).apply();
    }

    public static int getTotal_Nums() {
        return getAppPreference().getInt(KEY_TOTAL_NUMS, 0);
    }

    public static void setTotal_Piece(int i) {
        getEditor().putInt(KEY_TOTAL_PIECE, i).apply();
    }

    public static int getTotal_Piece() {
        return getAppPreference().getInt(KEY_TOTAL_PIECE, 0);
    }

    public static String getRegId() {
        return getAppPreference().getString(KEY_REGID, null);
    }

    public static void setRegId(String str) {
        getEditor().putString(KEY_REGID, str).apply();
    }

    public static String getUUID() {
        return getAppPreference().getString(KEY_UUID, null);
    }

    public static void setUUID(String str) {
        getEditor().putString(KEY_UUID, str).apply();
    }

    public static String getCoolies() {
        return getAppPreference().getString(LOGIN_COOLIES, null);
    }

    public static void setCoolies(String str) {
        getEditor().putString(LOGIN_COOLIES, str).apply();
    }
}
