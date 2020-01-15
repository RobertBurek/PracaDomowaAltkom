package pl.robertburek.db;

/**
 * Created by Robert Burek
 */
class ParametersDb {

    static final private String HOST_MY = "mn10.webd.pl";
    static final private int PORT = 3306;
    static final private String DB_NAME = "robbur70_BazaSamochodowa";
    static final private String USER_NAME = "robbur70_user";
    static final private String PASSWORD = "bazaCar";
    static final private String PARAM_STRING = "useSSL=false&serverTimezone=UTC" ;

    static int getPORT() {
        return PORT;
    }

    static String getHOST_MY() {
        return HOST_MY;
    }

    static String getDB_NAME() {
        return DB_NAME;
    }

    static String getUSER_NAME() {
        return USER_NAME;
    }

    static String getPASSWORD() {
        return PASSWORD;
    }

    static String getPARAM_STRING() {
        return PARAM_STRING;
    }
}
