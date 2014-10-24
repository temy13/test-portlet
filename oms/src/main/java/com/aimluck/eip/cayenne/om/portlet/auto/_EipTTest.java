package com.aimluck.eip.cayenne.om.portlet.auto;

/** Class _EipTTest was generated by Cayenne.
  * It is probably a good idea to avoid changing this class manually,
  * since it may be overwritten next time code is regenerated.
  * If you need to make any customizations, please use subclass.
  */
public class _EipTTest extends org.apache.cayenne.CayenneDataObject {

    public static final String CREATE_DATE_PROPERTY = "createDate";
    public static final String NOTE_PROPERTY = "note";
  //滝川
    public static final String TEST_URL_PROPERTY = "testURL";
    public static final String TEST_NAME_PROPERTY = "testName";
    public static final String UPDATE_DATE_PROPERTY = "updateDate";
    public static final String USER_ID_PROPERTY = "userId";
    public static final String TURBINE_USER_PROPERTY = "turbineUser";

    public static final String TEST_ID_PK_COLUMN = "TEST_ID";

    public void setCreateDate(java.util.Date createDate) {
        writeProperty("createDate", createDate);
    }
    public java.util.Date getCreateDate() {
        return (java.util.Date)readProperty("createDate");
    }


    public void setNote(String note) {
        writeProperty("note", note);
    }
    public String getNote() {
        return (String)readProperty("note");
    }

    public void setTestURL(String testURL) {
        writeProperty("testURL", testURL);
    }
    public String getTestURL() {
        return (String)readProperty("testURL");
    }



    public void setTestName(String testName) {
        writeProperty("testName", testName);
    }
    public String getTestName() {
        return (String)readProperty("testName");
    }


    public void setUpdateDate(java.util.Date updateDate) {
        writeProperty("updateDate", updateDate);
    }
    public java.util.Date getUpdateDate() {
        return (java.util.Date)readProperty("updateDate");
    }


    public void setUserId(Integer userId) {
        writeProperty("userId", userId);
    }
    public Integer getUserId() {
        return (Integer)readProperty("userId");
    }


    public void setTurbineUser(com.aimluck.eip.cayenne.om.security.TurbineUser turbineUser) {
        setToOneTarget("turbineUser", turbineUser, true);
    }

    public com.aimluck.eip.cayenne.om.security.TurbineUser getTurbineUser() {
        return (com.aimluck.eip.cayenne.om.security.TurbineUser)readProperty("turbineUser");
    }


}
