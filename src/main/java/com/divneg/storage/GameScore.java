package com.divneg.storage;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

@ParseClassName("GameScore")
public class GameScore extends ParseObject {
     
    public String getCookie() {
        return getString("cookie");
    }
     
    public void setCookie(String cookie) {
        put("cookie", cookie);
    }
    
    public String getCookieRate() {
        return getString("cookieRate");
    }
     
    public void setCookieRate(String cookieRate) {
        put("cookieRate", cookieRate);
    }
    
    public String getITEM1() {
        return getString("ITEM1");
    }
    
    public void setITEM1(String ITEM1) {
        put("ITEM1", ITEM1);
    }
    
    public String getITEM2() {
        return getString("ITEM2");
    }
    
    public void setITEM2(String ITEM2) {
        put("ITEM2", ITEM2);
    }
    public String getITEM3() {
        return getString("ITEM3");
    }
    
    public void setITEM3(String ITEM3) {
        put("ITEM3", ITEM3);
    }
    public String getITEM4() {
        return getString("ITEM4");
    }
    
    public void setITEM4(String ITEM4) {
        put("ITEM4", ITEM4);
    }
    public String getITEM5() {
        return getString("ITEM5");
    }
    
    public void setITEM5(String ITEM5) {
        put("ITEM5", ITEM5);
    }
    public String getITEM6() {
        return getString("ITEM6");
    }
    
    public void setITEM6(String ITEM6) {
        put("ITEM6", ITEM6);
    }
    public String getITEM7() {
        return getString("ITEM7");
    }
    
    public void setITEM7(String ITEM7) {
        put("ITEM7", ITEM7);
    }
    public String getITEM8() {
        return getString("ITEM8");
    }
    
    public void setITEM8(String ITEM8) {
        put("ITEM8", ITEM8);
    }
    public String getITEM9() {
        return getString("ITEM9");
    }
    
    public void setITEM9(String ITEM9) {
        put("ITEM9", ITEM9);
    }
    public String getITEM10() {
        return getString("ITEM10");
    }
    
    public void setITEM10(String ITEM10) {
        put("ITEM10", ITEM10);
    }
    public String getITEM11() {
        return getString("ITEM111");
    }
    
    public void setITEM11(String ITEM11) {
        put("ITEM11", ITEM11);
    }
     
    public ParseUser getUser() {
        return getParseUser("user");
    }
     
    public void setUser(ParseUser currentUser) {
        put("user", currentUser);
    }

     
    public static ParseQuery<GameScore> getQuery() {
        return ParseQuery.getQuery(GameScore.class);
    }
}
